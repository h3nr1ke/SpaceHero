package com.androidbrasilprojetos.games.spacehero.utils;

/**
 * This software is distributed under the
 * GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
 * */

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;

import com.androidbrasilprojetos.games.spacehero.scenes.MainMenuScene;
import com.androidbrasilprojetos.games.spacehero.scenes.RecordsScene;
import com.androidbrasilprojetos.games.spacehero.scenes.SplashScene;

/**
 * Manage all scenes inside the game
 * 
 * @author Henrique Deodato <h3nr1ke@gmail.com>
 * @see http://stuartmct.co.uk/?p=52
 * */

public class SceneManager {
	public enum SceneType {
		SPLASH, MENU, SETTINGS, ABOUT, RECORDS, GAME
	}

	// singleton variable
	private static SceneManager mSceneManager;

	// variables
	private SceneType currentScene = SceneType.SPLASH;
	private SceneType previousScene = SceneType.SPLASH;
	private BaseGameActivity mActivity;
	private Engine mEngine;
	private Camera mCamera;

	// scenes inside the manager
	private MainMenuScene mMenu;
	private SplashScene mSplash;
	private RecordsScene mRecords;

	/**
	 * Create the object scene manager
	 * 
	 * @param activity
	 *            - Main activity in the android app
	 * @param engine
	 *            - Engine created to handle game work
	 * @param camera
	 *            - Game camera
	 * */
	public SceneManager(BaseGameActivity pActivity) {
		this.mActivity = pActivity;
		this.mEngine = pActivity.getEngine();
		this.mCamera = this.mEngine.getCamera();

		this.previousScene = SceneType.SPLASH;

		// initialize the scenes
		this.createScenes();

	}

	public static SceneManager getSceneManager(BaseGameActivity pActivity) {
		if (mSceneManager == null) {
			mSceneManager = new SceneManager(pActivity);
		}
		return mSceneManager;
	}

	public void createScenes() {
		mMenu = new MainMenuScene(this.mEngine, this.mActivity);
		mSplash = new SplashScene(this.mEngine, this.mActivity);
		mRecords = new RecordsScene(this.mEngine, this.mActivity);
	}

	public void loadSceneResources(SceneType pScene) {
		switch (pScene) {
		case MENU:
			mMenu.loadResources();
			break;
		case SPLASH:
			mSplash.loadResources();
			break;
		case RECORDS:
			mRecords.loadResources();
			break;
		}
	}

	public void loadAllResources() {
		mMenu.loadResources();
		mSplash.loadResources();
		mRecords.loadResources();
	}

	public void releaseObjects() {
		mMenu.reset();
		mSplash.reset();
		mRecords.reset();
	}

	public void backScene() {
		// destroy the actual scene
		this.getCurrentScene().detachSelf();

		// load the previous one
		mEngine.setScene(this.getCurrentScene(previousScene));
	}

	public Scene getCurrentScene() {
		switch (currentScene) {
		default:
		case SPLASH:
			return mSplash;
		case MENU:
			return mMenu;
		case RECORDS:
			return mRecords;
		}
	}

	public Scene getCurrentScene(SceneType pScene) {

		// save the previous scene and update the actual
		previousScene = currentScene;
		currentScene = pScene;

		switch (pScene) {
		default:
		case SPLASH:
			return mSplash;
		case RECORDS:
			return mRecords;
		case MENU:
			mMenu.clearModifiers();
			mMenu.bindModifiers();
			return mMenu;
		}
	}

	public Scene getScene(SceneType pScene) {

		// save the previous scene and update the actual
		previousScene = currentScene;
		currentScene = pScene;

		switch (currentScene) {
		default:
		case SPLASH:
			mSplash.init();
			return mSplash;
		case MENU:
			mMenu.init();
			return mMenu;
		case RECORDS:
			mRecords.init();
			return mMenu;
		}
	}

}
