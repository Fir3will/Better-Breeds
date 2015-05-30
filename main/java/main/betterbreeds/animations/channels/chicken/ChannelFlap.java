package main.betterbreeds.animations.channels.chicken;

import mca.library.common.animation.Channel;
import mca.library.common.animation.KeyFrame;
import mca.library.common.math.Quaternion;
import mca.library.common.math.Vector3f;

public class ChannelFlap extends Channel
{
	public ChannelFlap(String name, float framesPerSecond, int totalFrames, byte channelType)
	{
		super(name, framesPerSecond, totalFrames, channelType);
	}

	@Override
	protected void initializeAllFrames()
	{
		final KeyFrame frame0 = new KeyFrame();
		frame0.modelRenderersRotations.put("leftWing", new Quaternion(0.0F, 0.0F, 0.0F, 1.0F));
		frame0.modelRenderersRotations.put("rightWing", new Quaternion(0.0F, 0.0F, 0.0F, 1.0F));
		frame0.modelRenderersTranslations.put("leftWing", new Vector3f(3.0F, 4.0F, 4.0F));
		frame0.modelRenderersTranslations.put("rightWing", new Vector3f(-3.0F, 4.0F, 4.0F));
		keyFrames.put(0, frame0);

		final KeyFrame frame1 = new KeyFrame();
		frame1.modelRenderersRotations.put("leftWing", new Quaternion(0.0F, 0.0F, 0.70028657F, 0.7138618F));
		frame1.modelRenderersRotations.put("rightWing", new Quaternion(0.0F, 0.0F, -0.70028657F, 0.7138618F));
		frame1.modelRenderersTranslations.put("leftWing", new Vector3f(3.0F, 4.0F, 4.0F));
		frame1.modelRenderersTranslations.put("rightWing", new Vector3f(-3.0F, 4.0F, 4.0F));
		keyFrames.put(1, frame1);

		final KeyFrame frame2 = new KeyFrame();
		frame2.modelRenderersRotations.put("leftWing", new Quaternion(0.0F, 0.0F, 0.0F, 1.0F));
		frame2.modelRenderersRotations.put("rightWing", new Quaternion(0.0F, 0.0F, 0.0F, 1.0F));
		frame2.modelRenderersTranslations.put("leftWing", new Vector3f(3.0F, 4.0F, 4.0F));
		frame2.modelRenderersTranslations.put("rightWing", new Vector3f(-3.0F, 4.0F, 4.0F));
		keyFrames.put(2, frame2);

	}
}