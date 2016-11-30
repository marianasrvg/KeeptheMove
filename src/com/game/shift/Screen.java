package com.game.shift;

import com.game.shift.graficos.Sprite;
import com.game.shift.graficos.SpriteSheet;
import com.game.shift.level.tiles.Tile;

public class Screen {
	public int width, height;
	private int xOffset, yOffset;
	public int[] pixels;
	
	public Screen(int width, int height){
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
	}
	
	public void clear(){
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = 0;
		}
	}
	
	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed){
		if(fixed){
			xp -= xOffset;
			yp -= yOffset;
		}
		for( int y = 0; y < sprite.getHeight(); y++){
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++){
				int xa = x + xp;
				if( xa < 0 || xa >= width || ya < 0 || ya>= height) continue;
				pixels[xa+ya*width] = sprite.pixels[x+y*sprite.getWidth()];
			}
		}
	}
	
	public void renderMob(int xp, int yp,  Sprite sprite){
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y< sprite.SIZE; y++){
			int ya= y+yp;
			for(int x = 0; x < sprite.SIZE; x++){
				int xa = x +xp;
				if(xa <-sprite.SIZE || xa >= width || ya < 0 || ya>= height) break;
				if(xa < 0) xa = 0;
				int col = sprite.pixels[x+y*sprite.SIZE];
				if (col != SpriteSheet.COLORES[4]) pixels[xa+ya*width] = col;
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset){
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void renderTile(int xp, int yp, Sprite sprite){
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < sprite.SIZE; y++){
			int ya = y + yp;
			for (int x = 0; x < sprite.SIZE; x++){
				int xa = x + xp;
				if(xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				pixels[xa+ya*width] = sprite.pixels[x+ y * sprite.SIZE];
			}
		}
	}
}

