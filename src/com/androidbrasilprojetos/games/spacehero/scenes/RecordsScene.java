package com.androidbrasilprojetos.games.spacehero.scenes;

/**
 * This software is distributed under the
 * GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
 * */

import org.andengine.engine.Engine;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;

import com.androidbrasilprojetos.games.spacehero.utils.AbsScene;
import com.androidbrasilprojetos.games.spacehero.utils.AutoVerticalParallaxBackground;
import com.androidbrasilprojetos.games.spacehero.utils.SceneManager.SceneType;
import com.androidbrasilprojetos.games.spacehero.utils.VerticalParallaxBackground.VerticalParallaxEntity;

public class RecordsScene extends AbsScene {
	
	//parallax object based in this link...
	//https://github.com/nicolasgramlich/AndEngineExamples/blob/GLES2/src/org/andengine/examples/AutoParallaxBackgroundExample.java

	private BitmapTextureAtlas mAutoParallaxBackgroundTexture;

	private ITextureRegion mParallaxLayerBack;

	// util
	private float mW;
	private float mH;

	public RecordsScene(Engine pEngine, BaseGameActivity pGame) {
		super(pEngine, pGame);
		this.setTag(SceneType.RECORDS.ordinal());

		// get the screen dimensions
		this.mW = this.mEngine.getCamera().getWidth();
		this.mH = this.mEngine.getCamera().getHeight();
	}

	@Override
	public void init() {
		// create teh vertical background...
		// the last parameter is the speed of update
		final AutoVerticalParallaxBackground autoParallaxBackground = new AutoVerticalParallaxBackground(
				0, 0, 0, 1.0f);

		final VertexBufferObjectManager vertexBufferObjectManager = this.mGame
				.getVertexBufferObjectManager();

		// include the background in the parallax object, the last parameter is
		// the direction 1 = down, -1 = up
		autoParallaxBackground
				.attachVerticalParallaxEntity(new VerticalParallaxEntity(2.0f,
						new Sprite(0, -800, this.mParallaxLayerBack,
								vertexBufferObjectManager), 1));

		this.setBackground(autoParallaxBackground);
	}

	@Override
	public void loadResources() {
		// TODO Auto-generated method stub

		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		this.mAutoParallaxBackgroundTexture = new BitmapTextureAtlas(
				this.mGame.getTextureManager(), 2048, 2048);
		
		//there are 5 differents background totry in the gfx folder...
		this.mParallaxLayerBack = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mAutoParallaxBackgroundTexture,
						this.mGame, "background03.jpg", 0, 0);
		this.mAutoParallaxBackgroundTexture.load();

	}

}
