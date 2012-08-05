package com.androidbrasilprojetos.games.spacehero;

/**
 * This software is distributed under the
 * GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
 * */
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.ui.activity.BaseGameActivity;

import android.util.Log;

import com.androidbrasilprojetos.games.spacehero.utils.SceneManager;
import com.androidbrasilprojetos.games.spacehero.utils.SceneManager.SceneType;

public class SpaceHero extends BaseGameActivity {

	// general game definitions

	// Define the scene dimension of the game
	public static int mCameraWidth = 480;
	public static int mCameraHeight = 800;

	// game camera
	private Camera mCamera;
	// scene Manager
	public static SceneManager mSceneManager;

	@Override
	public EngineOptions onCreateEngineOptions() {
		mCamera = new Camera(0, 0, mCameraWidth, mCameraHeight);
		final EngineOptions engineOptions = new EngineOptions(true,
				ScreenOrientation.PORTRAIT_FIXED, new FillResolutionPolicy(),
				mCamera);
		return engineOptions;
	}

	@Override
	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws Exception {

		// define the path of all images
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

		mSceneManager = SceneManager.getSceneManager(this);

		pOnCreateResourcesCallback.onCreateResourcesFinished();

	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws Exception {

		// Load the resources of all scenes...
		mSceneManager.loadAllResources();

		// in the end call the splash screen
		pOnCreateSceneCallback.onCreateSceneFinished(mSceneManager
				.getScene(SceneManager.SceneType.SPLASH));

	}

	@Override
	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
		int sceneTag = pScene.getTag();

		switch (SceneType.values()[sceneTag]) {
		case SPLASH:
			mEngine.registerUpdateHandler(new TimerHandler(4.5f,
					new ITimerCallback() {
						public void onTimePassed(
								final TimerHandler pTimerHandler) {
							mEngine.unregisterUpdateHandler(pTimerHandler);
							mSceneManager.getCurrentScene(
									SceneManager.SceneType.SPLASH).detachSelf();

							// load menu scene
							mEngine.setScene(mSceneManager
									.getScene(SceneManager.SceneType.MENU));
						}
					}));
			Log.d("mm", "entrou no splash");
			break;
		case SETTINGS:
			// pScene.reset();
			Log.d("mm", "entrou no setting");
			break;
		}

		pOnPopulateSceneCallback.onPopulateSceneFinished();

	}

}
