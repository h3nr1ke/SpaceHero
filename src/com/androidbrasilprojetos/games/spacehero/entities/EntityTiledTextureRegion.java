/**
 * 
 */
package com.androidbrasilprojetos.games.spacehero.entities;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.debug.Debug;

/**
 * @author WiLLStenico <willstenico@gmail.com>
 *
 */
public class EntityTiledTextureRegion {
	
	// ===========================================================
	// Fields
	// ===========================================================
		private final BaseGameActivity BGA;

		private TextureOptions textureOptionsDefault = TextureOptions.DEFAULT;

		private final int textureWidth, textureHeight;

		private final int tileColumns, tileRows;

		private final String assetName;

		private TiledTextureRegion entityTextureRegion;		

		private boolean isLoadedResources = false;

		/**
		 * 
		 */
		public EntityTiledTextureRegion(BaseGameActivity bga, int textureWidth, int textureHeight,
				int tileColumns, int tileRows, String assetName) {
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
			this.BGA = bga;
			this.textureWidth = textureWidth;
			this.textureHeight = textureHeight;
			this.tileColumns = tileColumns;
			this.tileRows = tileRows;
			this.assetName = assetName;
			
			LoadResources();
		}

		public void LoadResources() {
			BuildableBitmapTextureAtlas mBitmapTextureAtlas = new BuildableBitmapTextureAtlas(
					BGA.getTextureManager(), this.textureWidth, this.textureHeight,
					this.textureOptionsDefault);

			this.entityTextureRegion = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(mBitmapTextureAtlas, this.BGA, assetName,
							this.tileColumns, this.tileRows);

			try {
				mBitmapTextureAtlas
						.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
								0, 0, 1));
				mBitmapTextureAtlas.load();
			} catch (TextureAtlasBuilderException e) {
				Debug.e(e);
				isLoadedResources = true;
				return;
			}
			// mBitmapTextureAtlas.load();
			isLoadedResources = true;
			return;
			
		}		
		
		public TiledTextureRegion getTextureRegion(){
			if(!isLoadedResources){
				LoadResources();
			}
			return this.entityTextureRegion;
		}

}
