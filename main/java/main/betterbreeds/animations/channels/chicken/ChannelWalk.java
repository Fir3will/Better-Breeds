package main.betterbreeds.animations.channels.chicken;

import mca.library.common.animation.Channel;
import mca.library.common.animation.KeyFrame;
import mca.library.common.math.Quaternion;
import mca.library.common.math.Vector3f;

public class ChannelWalk extends Channel
{
	public ChannelWalk(String name, float framesPerSecond, int totalFrames, byte channelType)
	{
		super(name, framesPerSecond, totalFrames, channelType);
	}

	@Override
	protected void initializeAllFrames()
	{
		final KeyFrame frame0 = new KeyFrame();
		frame0.modelRenderersRotations.put("leftLeg", new Quaternion(0.0F, 0.0F, 0.0F, 1.0F));
		frame0.modelRenderersRotations.put("rightLeg", new Quaternion(0.0F, 0.0F, 0.0F, 1.0F));
		frame0.modelRenderersTranslations.put("leftLeg", new Vector3f(1.5F, -2.0F, 0.0F));
		frame0.modelRenderersTranslations.put("rightLeg", new Vector3f(-1.5F, -2.0F, 0.0F));
		keyFrames.put(0, frame0);

		final KeyFrame frame1 = new KeyFrame();
		frame1.modelRenderersRotations.put("leftLeg", new Quaternion(-0.2113248F, 0.0F, 0.0F, 0.9774159F));
		frame1.modelRenderersRotations.put("rightLeg", new Quaternion(0.3056953F, 0.0F, 0.0F, 0.9521294F));
		frame1.modelRenderersTranslations.put("leftLeg", new Vector3f(1.5F, -2.0F, 0.0F));
		frame1.modelRenderersTranslations.put("rightLeg", new Vector3f(-1.5F, -2.0F, 0.0F));
		keyFrames.put(1, frame1);

		final KeyFrame frame2 = new KeyFrame();
		frame2.modelRenderersRotations.put("leftLeg", new Quaternion(0.24953502F, 0.0F, 0.0F, 0.9683658F));
		frame2.modelRenderersRotations.put("rightLeg", new Quaternion(-0.34202012F, 0.0F, 0.0F, 0.9396926F));
		frame2.modelRenderersTranslations.put("leftLeg", new Vector3f(1.5F, -2.0F, 0.0F));
		frame2.modelRenderersTranslations.put("rightLeg", new Vector3f(-1.5F, -2.0F, 0.0F));
		keyFrames.put(2, frame2);

		final KeyFrame frame3 = new KeyFrame();
		frame3.modelRenderersRotations.put("leftLeg", new Quaternion(0.0F, 0.0F, 0.0F, 1.0F));
		frame3.modelRenderersRotations.put("rightLeg", new Quaternion(0.0F, 0.0F, 0.0F, 1.0F));
		frame3.modelRenderersTranslations.put("leftLeg", new Vector3f(1.5F, -2.0F, 0.0F));
		frame3.modelRenderersTranslations.put("rightLeg", new Vector3f(-1.5F, -2.0F, 0.0F));
		keyFrames.put(3, frame3);

	}
}