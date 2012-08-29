/**
 * 
 */
package com.androidbrasilprojetos.games.spacehero.entities;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.AnimationData;
import org.andengine.entity.sprite.vbo.ITiledSpriteVertexBufferObject;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.view.animation.Animation;

/**
 * @author WiLLStenico <willstenico@gmail.com>
 * 
 */
public class Enemy extends AnimatedSprite {

	// ===========================================================
	// Fields
	// ===========================================================
	private int lifeBar = 100;
	private boolean isDead = false;

	/**
	 * @param pX
	 * @param pY
	 * @param pTiledTextureRegion
	 * @param pTiledSpriteVertexBufferObject
	 */
	public Enemy(final float pX,final float pY, final ITiledTextureRegion pTiledTextureRegion,
			final VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
	}

}
