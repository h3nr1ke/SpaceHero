package com.androidbrasilprojetos.games.spacehero.scenes;

/**
 * This software is distributed under the
 * GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
 * */

import javax.microedition.khronos.opengles.GL10;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.modifier.IModifier;
import org.apache.http.entity.EntityTemplate;



import com.androidbrasilprojetos.games.spacehero.enemies.Smurf;
import com.androidbrasilprojetos.games.spacehero.entities.*;



import com.androidbrasilprojetos.games.spacehero.utils.AbsScene;
import com.androidbrasilprojetos.games.spacehero.utils.SceneManager.SceneType;

public class SplashScene extends AbsScene {

	// scene variables...
	private BitmapTextureAtlas splashTextureAtlas;
	private ITextureRegion splashTextureRegion;
	private ITextureRegion abpSplashTextureRegion;
	private Sprite splash;
	private Sprite abpSplash;
	
	private EntitiesTexturesManager textures;

	public SplashScene(Engine pEngine, BaseGameActivity pGame) {		
		super(pEngine, pGame);
		
		this.setBackground(new Background(0, 0, 0));
		this.setTag(SceneType.SPLASH.ordinal());
		
		//TODO: EXAMPLE REMOVE IT!!!!!!
		Smurf smurf = new Smurf(0, 100, textures.getTextureRegion(EntityType.SMURF), pGame.getVertexBufferObjectManager());
		smurf.run();
		this.attachChild(smurf);
	}

	@Override
	public void init() {
		splash = new Sprite(0, 0, splashTextureRegion,
				this.mEngine.getVertexBufferObjectManager()) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
			}
		};
		abpSplash = new Sprite(0, 0, abpSplashTextureRegion,
				this.mEngine.getVertexBufferObjectManager()) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
			}
		};

		splash.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		abpSplash.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		splash.setPosition(
				(this.mEngine.getCamera().getWidth() - splash.getWidth()) * 0.5f,
				(this.mEngine.getCamera().getHeight() - splash.getHeight()) * 0.5f);
		splash.setAlpha(0);
		
		
		AlphaModifier alpha = new AlphaModifier(2,0,1, new IEntityModifierListener() {
			
			@Override
			public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
				// TODO Auto-generated method stub
				abpSplash.registerEntityModifier(new AlphaModifier(2, 1, 0));
				splash.registerEntityModifier(new AlphaModifier(2, 0, 1));
			}
		});
		abpSplash.setPosition(
				(this.mEngine.getCamera().getWidth() - abpSplash.getWidth()) * 0.5f,
				(this.mEngine.getCamera().getHeight() - abpSplash.getHeight()) * 0.5f);
		abpSplash.setAlpha(0);
		abpSplash.registerEntityModifier(alpha);
		
		
		this.attachChild(abpSplash);
		this.attachChild(splash);
	}

	@Override
	public void loadResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		splashTextureAtlas = new BitmapTextureAtlas(mEngine.getTextureManager(),
				1024, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		splashTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(splashTextureAtlas, mGame, "spacehero.png", 0, 0);
		
		abpSplashTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(splashTextureAtlas, mGame, "logo_ABP.png", 505, 0);		
		
		//TODO: Examples REMOVE IT!!!!!!!!!!!!!!!!!
		this.textures = new EntitiesTexturesManager(mGame);
		this.textures.LoadResources(EntityType.SMURF);
		
		splashTextureAtlas.load();
	}

}
