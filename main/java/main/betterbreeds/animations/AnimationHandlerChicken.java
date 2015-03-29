package main.betterbreeds.animations;

import java.util.HashMap;
import mca.library.common.IMCAnimatedEntity;
import mca.library.common.animation.AnimationHandler;
import mca.library.common.animation.Channel;

public class AnimationHandlerChicken extends AnimationHandler
{
	public static HashMap<String, Channel> animChannels = new HashMap<String, Channel>();

	public AnimationHandlerChicken(IMCAnimatedEntity entity)
	{
		super(entity);
	}

	@Override
	public void activateAnimation(String name, float startingFrame)
	{
		super.activateAnimation(animChannels, name, startingFrame);
	}

	@Override
	public void stopAnimation(String name)
	{
		super.stopAnimation(animChannels, name);
	}

	@Override
	public void fireAnimationEventClientSide(Channel anim, float prevFrame, float frame)
	{}

	@Override
	public void fireAnimationEventServerSide(Channel anim, float prevFrame, float frame)
	{}
}