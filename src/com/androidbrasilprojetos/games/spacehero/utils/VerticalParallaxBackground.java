package com.androidbrasilprojetos.games.spacehero.utils;

/**
 * http://www.andengine.org/forums/gles2/vertical-parallax-with-gles2-t6806.html
 * */

import java.util.ArrayList;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.background.ParallaxBackground;
import org.andengine.entity.shape.IAreaShape;
import org.andengine.opengl.util.GLState;

public class VerticalParallaxBackground extends ParallaxBackground {
	public static int SCROLL_DOWN = -1;
	public static int SCROLL_UP = 1;
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final ArrayList<VerticalParallaxEntity> mParallaxEntities = new ArrayList<VerticalParallaxEntity>();
	private int mParallaxEntityCount;

	protected float mParallaxValue;

	// ===========================================================
	// Constructors
	// ===========================================================

	public VerticalParallaxBackground(float red, float green, float blue) {
		super(red, green, blue);
		// TODO Auto-generated constructor stub
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public void setParallaxValue(final float pParallaxValue) {
		this.mParallaxValue = pParallaxValue;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onDraw(final GLState pGLState, final Camera pCamera) {
		super.onDraw(pGLState, pCamera);

		final float parallaxValue = this.mParallaxValue;
		final ArrayList<VerticalParallaxEntity> parallaxEntities = this.mParallaxEntities;
		// Log.d("VAPB", "VAPB onDraw pre entity");
		for (int i = 0; i < this.mParallaxEntityCount; i++) {
			parallaxEntities.get(i).onDraw(pGLState, pCamera, parallaxValue);
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void attachVerticalParallaxEntity(
			final VerticalParallaxEntity pParallaxEntity) {
		this.mParallaxEntities.add(pParallaxEntity);
		this.mParallaxEntityCount++;
	}

	public boolean detachVerticalParallaxEntity(
			final VerticalParallaxEntity pParallaxEntity) {
		this.mParallaxEntityCount--;
		final boolean success = this.mParallaxEntities.remove(pParallaxEntity);
		if (success == false) {
			this.mParallaxEntityCount++;
		}
		return success;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	public static class VerticalParallaxEntity {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Fields
		// ===========================================================

		final float mParallaxFactor;
		final IAreaShape mShape;
		private int direction;

		// ===========================================================
		// Constructors
		// ===========================================================

		public VerticalParallaxEntity(final float pParallaxFactor,
				final IAreaShape pShape) {
			this.mParallaxFactor = pParallaxFactor;
			this.mShape = pShape;
			this.direction = VerticalParallaxBackground.SCROLL_DOWN;
		}

		public VerticalParallaxEntity(final float pParallaxFactor,
				final IAreaShape pShape, int direction) {
			this.mParallaxFactor = pParallaxFactor;
			this.mShape = pShape;
			this.direction = direction;
		}

		// ===========================================================
		// Getter & Setter
		// ===========================================================

		// ===========================================================
		// Methods for/from SuperClass/Interfaces
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================

		public void onDraw(final GLState pGL, final Camera pCamera,
				final float pParallaxValue) {

			pGL.pushModelViewGLMatrix();
			final float cameraHeight = pCamera.getHeight();
			final float shapeHeightScaled = this.mShape.getHeightScaled();
			float baseOffset = (pParallaxValue * this.mParallaxFactor)
					% shapeHeightScaled;
			while (baseOffset > 0) {
				baseOffset -= shapeHeightScaled;
			}
			pGL.translateModelViewGLMatrixf(0, (direction * baseOffset), 0);
			float currentMaxY = baseOffset;
			do {
				this.mShape.onDraw(pGL, pCamera);
				pGL.translateModelViewGLMatrixf(0,
						(direction * shapeHeightScaled), 0);
				currentMaxY += shapeHeightScaled;
			} while (currentMaxY < (cameraHeight + shapeHeightScaled));
			// Added shapeHeightScaled to cameraHeight so the drawing flows in
			// instead of popping in.

			pGL.popModelViewGLMatrix();

		}
		// ===========================================================
		// Inner and Anonymous Classes
		// ===========================================================
	}
}
