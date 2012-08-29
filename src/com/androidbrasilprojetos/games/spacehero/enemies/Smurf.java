/**
 * 
 */
package com.androidbrasilprojetos.games.spacehero.enemies;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.vbo.ITiledSpriteVertexBufferObject;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.androidbrasilprojetos.games.spacehero.entities.Enemy;

/**
 * @author WiLLStenico <willstenico@gmail.com>
 *
 */
public class Smurf extends Enemy{

	/**
	 * @param pX
	 * @param pY
	 * @param pTiledTextureRegion
	 * @param pTiledSpriteVertexBufferObject
	 */
	public Smurf(final float pX,final float pY, final ITiledTextureRegion pTiledTextureRegion,
			final VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
		
	}
	
	public void walk(){		
		super.animate(60);		
	}

	public void run(){		
		super.animate(30);		
	}

	
	
	

}
