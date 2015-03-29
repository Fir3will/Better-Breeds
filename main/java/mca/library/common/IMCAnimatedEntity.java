package mca.library.common;

import mca.library.common.animation.AnimationHandler;

public interface IMCAnimatedEntity
{
	public abstract AnimationHandler getAnimationHandler();

	public abstract boolean isTile();

	public abstract boolean isEntity();
}
