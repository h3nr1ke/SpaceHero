package com.androidbrasilprojetos.games.spacehero.scenes;

/**
 * This software is distributed under the
 * GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
 * */

import org.andengine.engine.Engine;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.scene.IOnAreaTouchListener;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.modifier.ease.EaseStrongOut;

import android.view.MotionEvent;

import com.androidbrasilprojetos.games.spacehero.SpaceHero;
import com.androidbrasilprojetos.games.spacehero.utils.AbsScene;
import com.androidbrasilprojetos.games.spacehero.utils.SceneManager;
import com.androidbrasilprojetos.games.spacehero.utils.SceneManager.SceneType;

public class MainMenuScene extends AbsScene implements IOnAreaTouchListener {

	// buttons

	// Text Button
	private Text mNewGame;
	private Text mAbout;
	private Text mRecords;
	private Text mConfig;
	private Text mTutorial;
	private Text mTitle;

	// button ids
	private final int NEWGAME = 0;
	private final int ABOUT = NEWGAME + 1;
	private final int RECORDS = NEWGAME + 2;
	private final int CONFIG = NEWGAME + 3;
	private final int TUTORIAL = NEWGAME + 4;

	// util
	private float mW;
	private float mH;

	// screen definitions
	private float mButtonW = 0.8f;
	private float mButtonH = 0.1f;
	private float mButtonSpace = 0.15f;
	private float mRightPadding = 15;
	private float mLeftPadding = -250;

	// font
	private Font mFont;

	public MainMenuScene(Engine pEngine, BaseGameActivity pGame) {
		super(pEngine, pGame);

		this.setTag(SceneType.MENU.ordinal());

		this.setBackground(new Background(0, 0, 0));

		// get the screen dimensions
		this.mW = this.mEngine.getCamera().getWidth();
		this.mH = this.mEngine.getCamera().getHeight();
	}

	@Override
	public void init() {

		// add the buttons in the scene
		// this.attachChild(this.mRecTop);

		this.attachChild(this.mNewGame);
		this.attachChild(this.mRecords);
		this.attachChild(this.mConfig);
		this.attachChild(this.mAbout);
		this.attachChild(this.mTitle);
		this.attachChild(this.mTutorial);

		this.registerTouchArea(this.mNewGame);
		this.registerTouchArea(this.mRecords);
		this.registerTouchArea(this.mConfig);
		this.registerTouchArea(this.mAbout);
		this.registerTouchArea(this.mTutorial);

		this.setOnAreaTouchListener(this);
	}

	@Override
	public void loadResources() {
		// new button position
		float nbX = (mW - (mW * 0.8f)) * 0.5f;
		float nbY = (mH - (mH * 0.2f)) * 0.5f;

		// button dimensions
		float bW = (mW * mButtonW);
		float bH = (mH * mButtonH);

		// load font
		FontFactory.setAssetBasePath("fonts/");

		final ITexture fontTexture = new BitmapTextureAtlas(
				mEngine.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mFont = FontFactory.createFromAsset(mEngine.getFontManager(),
				fontTexture, mGame.getAssets(), "Sujeta/Sujeta.ttf", 62, true,
				android.graphics.Color.WHITE);
		this.mFont.load();

		final VertexBufferObjectManager vertexBufferObjectManager = mEngine
				.getVertexBufferObjectManager();
		
		mNewGame = new Text(mLeftPadding, nbY, this.mFont, "New Game", new TextOptions(
				HorizontalAlign.RIGHT), vertexBufferObjectManager);

		mRecords = new Text(mLeftPadding, nbY + bH + (bH * mButtonSpace),
				this.mFont, "Records", new TextOptions(HorizontalAlign.RIGHT),
				vertexBufferObjectManager);

		mConfig = new Text(mLeftPadding, nbY + (2 * bH) + (2 * bH * mButtonSpace),
				this.mFont, "Settings", new TextOptions(HorizontalAlign.RIGHT),
				vertexBufferObjectManager);

		mAbout = new Text(mLeftPadding, nbY + (3 * bH) + (3 * bH * mButtonSpace),
				this.mFont, "About", new TextOptions(HorizontalAlign.RIGHT),
				vertexBufferObjectManager);
		
		mTutorial = new Text(mLeftPadding, nbY + (4 * bH) + (4 * bH * mButtonSpace),
				this.mFont, "Tutorial", new TextOptions(HorizontalAlign.RIGHT),
				vertexBufferObjectManager);

		mTitle = new Text(0, mLeftPadding, this.mFont, "Space\nHero", new TextOptions(
				HorizontalAlign.CENTER), vertexBufferObjectManager);
		
		mTitle.setScale(1.4f);

		mTitle.setX((mW - mTitle.getWidth()) / 2);

		this.mNewGame.setTag(NEWGAME);
		this.mRecords.setTag(RECORDS);
		this.mConfig.setTag(CONFIG);
		this.mAbout.setTag(ABOUT);
		this.mTutorial.setTag(TUTORIAL);

		this.textInitPos();
		this.bindModifiers();

	}

	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			ITouchArea pTouchArea, float pTouchAreaLocalX,
			float pTouchAreaLocalY) {

		// get the pressed object
		Text text = (Text) pTouchArea;

		// verify if is the right moment
		switch (pSceneTouchEvent.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// change the button color
			text.setColor(1f, 1f, 1f);
			break;
		case MotionEvent.ACTION_UP:
			// finalize the menu scene
			this.detachSelf();
			SceneType target = SceneManager.SceneType.ABOUT;

			switch (text.getTag()) {
			case NEWGAME:
				target = SceneManager.SceneType.GAME;
				break;
			case ABOUT:
				target = SceneManager.SceneType.ABOUT;
				break;
			case RECORDS:
				target = SceneManager.SceneType.RECORDS;
				break;
			case CONFIG:
				target = SceneManager.SceneType.SETTINGS;
				break;
			}

			mEngine.setScene(SpaceHero.mSceneManager.getCurrentScene(target));
			break;
		}
		
		return false;
	}

	public void bindModifiers() {
		mNewGame.registerEntityModifier(new MoveModifier(1f, mNewGame.getX(),
				this.mW - this.mNewGame.getWidthScaled() - mRightPadding,
				mNewGame.getY(), mNewGame.getY(), EaseStrongOut.getInstance()));
		mRecords.registerEntityModifier(new MoveModifier(1.4f, mRecords.getX(),
				this.mW - this.mRecords.getWidthScaled() - mRightPadding,
				mRecords.getY(), mRecords.getY(), EaseStrongOut.getInstance()));
		mConfig.registerEntityModifier(new MoveModifier(1.8f, mConfig.getX(),
				this.mW - this.mConfig.getWidthScaled() - mRightPadding,
				mConfig.getY(), mConfig.getY(), EaseStrongOut.getInstance()));
		mAbout.registerEntityModifier(new MoveModifier(2.0f, mAbout.getX(),
				this.mW - this.mAbout.getWidthScaled() - mRightPadding, mAbout
						.getY(), mAbout.getY(), EaseStrongOut.getInstance()));
		mTutorial.registerEntityModifier(new MoveModifier(2.2f, mTutorial.getX(),
				this.mW - this.mTutorial.getWidthScaled() - mRightPadding, mTutorial
						.getY(), mTutorial.getY(), EaseStrongOut.getInstance()));
		
		mTitle.registerEntityModifier(new MoveModifier(2.0f, mTitle.getX(),mTitle.getX(),
				mLeftPadding, 30, EaseStrongOut.getInstance()));

	}

	public void clearModifiers() {
		// remove the modifiers
		mNewGame.clearEntityModifiers();
		mRecords.clearEntityModifiers();
		mConfig.clearEntityModifiers();
		mAbout.clearEntityModifiers();
		mTitle.clearEntityModifiers();
		mTutorial.clearEntityModifiers();

		// put the text in the original position outside the screen
		this.textInitPos();

	}

	public void textInitPos() {
		mNewGame.setX(mLeftPadding);
		mRecords.setX(mLeftPadding);
		mConfig.setX(mLeftPadding);
		mAbout.setX(mLeftPadding);
		mTitle.setY(mLeftPadding);
		mTutorial.setX(mLeftPadding);
		
	}

}
