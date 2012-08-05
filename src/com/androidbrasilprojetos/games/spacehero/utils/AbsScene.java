package com.androidbrasilprojetos.games.spacehero.utils;

/**
 * This software is distributed under the
 * GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
 * */

import org.andengine.engine.Engine;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;

/**
 * Base scene to be used together with the SceneManager
 * 
 * @author - Henrique Deodato <h3nr1ke@gmail.com>
 * */
public abstract class AbsScene extends Scene {
	protected Engine mEngine;
	protected BaseGameActivity mGame;
	protected int mSceneId;

	// creator
	public AbsScene(Engine pEngine, BaseGameActivity pGame) {
		this.mEngine = pEngine;
		this.mGame = pGame;

		this.loadResources();
		this.init();
	}
	
	// init the scene
	public abstract void init();

	// load all the resources from a scene
	public abstract void loadResources();

	// get the scene id to identify which is being loaded
	public int getSceneId() {
		return mSceneId;
	};

	public void setSceneId(int pSceneId) {
		this.mSceneId = pSceneId;
	};

}
