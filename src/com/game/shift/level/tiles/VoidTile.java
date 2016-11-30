package com.game.shift.level.tiles;

import com.game.shift.Screen;
import com.game.shift.graficos.Sprite;

public class VoidTile extends Tile {

	public VoidTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen){
		screen.renderTile(x << 16, y << 16, this.sprite);
	}

}
