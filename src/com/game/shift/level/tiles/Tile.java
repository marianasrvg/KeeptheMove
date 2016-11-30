package com.game.shift.level.tiles;

import com.game.shift.Screen;
import com.game.shift.graficos.Sprite;

public class Tile {
	public 	int x, y;
	public Sprite sprite;
	
	public static Tile universe = new UniverseTile(Sprite.universe);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public static Tile top_border = new BorderTile(Sprite.top_border);
	public static Tile down_border = new BorderTile(Sprite.down_border);
	public static Tile left_border = new BorderTile(Sprite.left_border);
	public static Tile right_border = new BorderTile(Sprite.right_border);
	public static Tile corner_ld = new BorderTile(Sprite.corner_ld);
	public static Tile corner_rd = new BorderTile(Sprite.corner_rd);
	public static Tile corner_lu = new BorderTile(Sprite.corner_lu);
	public static Tile corner_ru = new BorderTile(Sprite.corner_ru);
	public static Tile wall = new WallTile(Sprite.wall);
	
	public static final int C_UNIVERSE =  0xFFFF00DC;
	public static final int C_DOWNBORDER = 0xFFB6FF00;
	public static final int C_LEFTBORDER = 0xFFFF0000;
	public static final int C_TOPBORDER = 0xFFFFD800;
	public static final int C_RIGHTBORDER = 0xFFFF6A00;
	public static final int C_C_LD = 0xFF7F0000;
	public static final int C_C_RD = 0xFF7F6A00;
	public static final int C_C_LU = 0xFF5B7F00;
	public static final int C_C_RU = 0xFF7F3300;
	public static final int C_WALL = 0xFF0094FF;
	
	
	public Tile (Sprite sprite){
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen){
	}
	
	public boolean solid(){
		return false;
	}
}
