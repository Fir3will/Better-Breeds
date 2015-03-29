package mca.library.common.animation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import mca.library.client.MCAModelRenderer;
import mca.library.common.IMCAnimatedEntity;
import mca.library.common.math.Quaternion;
import mca.library.common.math.Vector3f;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class AnimationHandler
{
	public static AnimTickHandler animTickHandler;
	/** Owner of this handler. */
	private final IMCAnimatedEntity animatedEntity;
	/** List of all the activate animations of this Entity. */
	public ArrayList<Channel> animCurrentChannels = new ArrayList<Channel>();
	/** Previous time of every active animation. */
	public HashMap<String, Long> animPrevTime = new HashMap<String, Long>();
	/** Current frame of every active animation. */
	public HashMap<String, Float> animCurrentFrame = new HashMap<String, Float>();
	/** Contains the unique names of the events that have been already fired during each animation.
	 * It becomes empty at the end of every animation. The key is the animation name and the value is the list of already-called events. */
	private final HashMap<String, ArrayList<String>> animationEvents = new HashMap<String, ArrayList<String>>();

	public AnimationHandler(IMCAnimatedEntity entity)
	{
		if (animTickHandler == null)
		{
			animTickHandler = new AnimTickHandler();
		}
		animTickHandler.addEntity(entity);

		animatedEntity = entity;
	}

	public IMCAnimatedEntity getEntity()
	{
		return animatedEntity;
	}

	public void activateAnimation(HashMap<String, Channel> animChannels, String name, float startingFrame)
	{
		if (animChannels.get(name) != null)
		{
			final Channel selectedChannel = animChannels.get(name);
			final int indexToRemove = animCurrentChannels.indexOf(selectedChannel);
			if (indexToRemove != -1)
			{
				animCurrentChannels.remove(indexToRemove);
			}

			animCurrentChannels.add(selectedChannel);
			animPrevTime.put(name, System.nanoTime());
			animCurrentFrame.put(name, startingFrame);
			if (animationEvents.get(name) == null)
			{
				animationEvents.put(name, new ArrayList<String>());
			}
		}
		else
		{
			System.out.println("The animation called " + name + " doesn't exist!");
		}
	}

	public abstract void activateAnimation(String name, float startingFrame);

	public void stopAnimation(HashMap<String, Channel> animChannels, String name)
	{
		final Channel selectedChannel = animChannels.get(name);
		if (selectedChannel != null)
		{
			final int indexToRemove = animCurrentChannels.indexOf(selectedChannel);
			if (indexToRemove != -1)
			{
				animCurrentChannels.remove(indexToRemove);
				animPrevTime.remove(name);
				animCurrentFrame.remove(name);
				animationEvents.get(name).clear();
			}
		}
		else
		{
			System.out.println("The animation called " + name + " doesn't exist!");
		}
	}

	public abstract void stopAnimation(String name);

	public void animationsUpdate()
	{
		for (final Iterator<Channel> it = animCurrentChannels.iterator(); it.hasNext();)
		{
			final Channel anim = it.next();
			final float prevFrame = animCurrentFrame.get(anim.name);
			final boolean animStatus = updateAnimation(animatedEntity, anim, animPrevTime, animCurrentFrame);
			if (animCurrentFrame.get(anim.name) != null)
			{
				fireAnimationEvent(anim, prevFrame, animCurrentFrame.get(anim.name));
			}
			if (!animStatus)
			{
				it.remove();
				animPrevTime.remove(anim.name);
				animCurrentFrame.remove(anim.name);
				animationEvents.get(anim.name).clear();
			}
		}
	}

	public boolean isAnimationActive(String name)
	{
		boolean animAlreadyUsed = false;
		for (final Channel anim : animatedEntity.getAnimationHandler().animCurrentChannels)
		{
			if (anim.name.equals(name))
			{
				animAlreadyUsed = true;
				break;
			}
		}

		return animAlreadyUsed;
	}

	private void fireAnimationEvent(Channel anim, float prevFrame, float frame)
	{
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
		{
			fireAnimationEventClientSide(anim, prevFrame, frame);
		}
		else
		{
			fireAnimationEventServerSide(anim, prevFrame, frame);
		}
	}

	@SideOnly(Side.CLIENT)
	public abstract void fireAnimationEventClientSide(Channel anim, float prevFrame, float frame);

	public abstract void fireAnimationEventServerSide(Channel anim, float prevFrame, float frame);

	/** Check if the animation event has already been called. */
	public boolean alreadyCalledEvent(String animName, String eventName)
	{
		if (animationEvents.get(animName) == null)
		{
			System.out.println("Cannot check for event " + eventName + "! Animation " + animName + "does not exist or is not active.");
			return true;
		}
		return animationEvents.get(animName).contains(eventName);
	}

	/** Set the animation event as "called", so it won't be fired again. */
	public void setCalledEvent(String animName, String eventName)
	{
		if (animationEvents.get(animName) != null)
		{
			animationEvents.get(animName).add(eventName);
		}
		else
		{
			System.out.println("Cannot set event " + eventName + "! Animation " + animName + "does not exist or is not active.");
		}
	}

	/** Update animation values. Return false if the animation should stop. */
	public static boolean updateAnimation(IMCAnimatedEntity entity, Channel channel, HashMap<String, Long> prevTimeAnim, HashMap<String, Float> prevFrameAnim)
	{
		if (FMLCommonHandler.instance().getEffectiveSide().isServer() || FMLCommonHandler.instance().getEffectiveSide().isClient() && !isGamePaused())
		{
			if (!(channel.mode == Channel.CUSTOM))
			{
				final long prevTime = prevTimeAnim.get(channel.name);
				final float prevFrame = prevFrameAnim.get(channel.name);

				final long currentTime = System.nanoTime();
				final double deltaTime = (currentTime - prevTime) / 1000000000.0;
				final float numberOfSkippedFrames = (float) (deltaTime * channel.fps);

				final float currentFrame = prevFrame + numberOfSkippedFrames;

				if (currentFrame < channel.totalFrames)
				{
					prevTimeAnim.put(channel.name, currentTime);
					prevFrameAnim.put(channel.name, currentFrame);
					return true;
				}
				else
				{
					if (channel.mode == Channel.LOOP)
					{
						prevTimeAnim.put(channel.name, currentTime);
						prevFrameAnim.put(channel.name, 0F);
						return true;
					}
					return false;
				}
			}
			else return true;
		}
		else
		{
			final long currentTime = System.nanoTime();
			prevTimeAnim.put(channel.name, currentTime);
			return true;
		}
	}

	@SideOnly(Side.CLIENT)
	private static boolean isGamePaused()
	{
		final net.minecraft.client.Minecraft MC = net.minecraft.client.Minecraft.getMinecraft();
		return MC.isSingleplayer() && MC.currentScreen != null && MC.currentScreen.doesGuiPauseGame() && !MC.getIntegratedServer().getPublic();
	}

	/** Apply animations if running or apply initial values.
	 * Must be called only by the model class.
	 */
	@SideOnly(Side.CLIENT)
	public static void performAnimationInModel(HashMap<String, MCAModelRenderer> parts, IMCAnimatedEntity entity)
	{
		for (final Map.Entry<String, MCAModelRenderer> entry : parts.entrySet())
		{
			final String boxName = entry.getKey();
			final MCAModelRenderer box = entry.getValue();

			boolean anyRotationApplied = false;
			boolean anyTranslationApplied = false;
			boolean anyCustomAnimationRunning = false;

			for (final Channel channel : entity.getAnimationHandler().animCurrentChannels)
			{
				if (channel.mode != Channel.CUSTOM)
				{
					final float currentFrame = entity.getAnimationHandler().animCurrentFrame.get(channel.name);

					//Rotations
					final KeyFrame prevRotationKeyFrame = channel.getPreviousRotationKeyFrameForBox(boxName, entity.getAnimationHandler().animCurrentFrame.get(channel.name));
					final int prevRotationKeyFramePosition = prevRotationKeyFrame != null ? channel.getKeyFramePosition(prevRotationKeyFrame) : 0;

					final KeyFrame nextRotationKeyFrame = channel.getNextRotationKeyFrameForBox(boxName, entity.getAnimationHandler().animCurrentFrame.get(channel.name));
					final int nextRotationKeyFramePosition = nextRotationKeyFrame != null ? channel.getKeyFramePosition(nextRotationKeyFrame) : 0;

					float SLERPProgress = (currentFrame - prevRotationKeyFramePosition) / (nextRotationKeyFramePosition - prevRotationKeyFramePosition);
					if (SLERPProgress > 1F || SLERPProgress < 0F)
					{
						SLERPProgress = 1F;
					}

					if (prevRotationKeyFramePosition == 0 && !(nextRotationKeyFramePosition == 0))
					{
						final Quaternion currentQuat = new Quaternion();
						currentQuat.slerp(parts.get(boxName).getDefaultRotationAsQuaternion(), nextRotationKeyFrame.modelRenderersRotations.get(boxName), SLERPProgress);
						box.getRotationMatrix().set(currentQuat).transpose();

						anyRotationApplied = true;
					}
					else if (!(prevRotationKeyFramePosition == 0) && !(nextRotationKeyFramePosition == 0))
					{
						final Quaternion currentQuat = new Quaternion();
						currentQuat.slerp(prevRotationKeyFrame.modelRenderersRotations.get(boxName), nextRotationKeyFrame.modelRenderersRotations.get(boxName), SLERPProgress);
						box.getRotationMatrix().set(currentQuat).transpose();

						anyRotationApplied = true;
					}

					//Translations
					final KeyFrame prevTranslationKeyFrame = channel.getPreviousTranslationKeyFrameForBox(boxName, entity.getAnimationHandler().animCurrentFrame.get(channel.name));
					final int prevTranslationsKeyFramePosition = prevTranslationKeyFrame != null ? channel.getKeyFramePosition(prevTranslationKeyFrame) : 0;

					final KeyFrame nextTranslationKeyFrame = channel.getNextTranslationKeyFrameForBox(boxName, entity.getAnimationHandler().animCurrentFrame.get(channel.name));
					final int nextTranslationsKeyFramePosition = nextTranslationKeyFrame != null ? channel.getKeyFramePosition(nextTranslationKeyFrame) : 0;

					float LERPProgress = (currentFrame - prevTranslationsKeyFramePosition) / (nextTranslationsKeyFramePosition - prevTranslationsKeyFramePosition);
					if (LERPProgress > 1F)
					{
						LERPProgress = 1F;
					}

					if (prevTranslationsKeyFramePosition == 0 && !(nextTranslationsKeyFramePosition == 0))
					{
						final Vector3f startPosition = parts.get(boxName).getPositionAsVector();
						final Vector3f endPosition = nextTranslationKeyFrame.modelRenderersTranslations.get(boxName);
						final Vector3f currentPosition = new Vector3f(startPosition);
						currentPosition.interpolate(endPosition, LERPProgress);
						box.setRotationPoint(currentPosition.x, currentPosition.y, currentPosition.z);

						anyTranslationApplied = true;
					}
					else if (!(prevTranslationsKeyFramePosition == 0) && !(nextTranslationsKeyFramePosition == 0))
					{
						final Vector3f startPosition = prevTranslationKeyFrame.modelRenderersTranslations.get(boxName);
						final Vector3f endPosition = nextTranslationKeyFrame.modelRenderersTranslations.get(boxName);
						final Vector3f currentPosition = new Vector3f(startPosition);
						currentPosition.interpolate(endPosition, LERPProgress);
						box.setRotationPoint(currentPosition.x, currentPosition.y, currentPosition.z);

						anyTranslationApplied = true;
					}
				}
				else
				{
					anyCustomAnimationRunning = true;

					((CustomChannel) channel).update(parts, entity);
				}
			}

			//Set the initial values for each box if necessary
			if (!anyRotationApplied && !anyCustomAnimationRunning)
			{
				box.resetRotationMatrix();
			}
			if (!anyTranslationApplied && !anyCustomAnimationRunning)
			{
				box.resetRotationPoint();
			}
		}
	}
}
