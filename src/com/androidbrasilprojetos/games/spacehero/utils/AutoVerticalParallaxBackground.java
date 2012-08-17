package com.androidbrasilprojetos.games.spacehero.utils;

/**
 * http://www.andengine.org/forums/gles2/vertical-parallax-with-gles2-t6806.html
 * */
public class AutoVerticalParallaxBackground extends VerticalParallaxBackground {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private boolean paused;
	private final float mParallaxChangePerSecond;

	// ===========================================================
	// Constructors
	// ===========================================================

	public AutoVerticalParallaxBackground(final float pRed, final float pGreen,
			final float pBlue, final float pParallaxChangePerSecond) {
		super(pRed, pGreen, pBlue);
		this.mParallaxChangePerSecond = pParallaxChangePerSecond;
		this.paused = false;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public void setPaused(boolean b) {
		this.paused = b;

	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onUpdate(final float pSecondsElapsed) {
		if (!this.paused) {
			super.onUpdate(pSecondsElapsed);

			this.mParallaxValue += this.mParallaxChangePerSecond
					* pSecondsElapsed;
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
