package com.game.shift.level.tiles;

import com.game.shift.Screen;
import com.game.shift.graficos.Sprite;

public class BorderTile extends Tile{

	public BorderTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen){
		screen.renderTile(x << 4, y << 4, this.sprite);
	}
	
	public boolean solid(){
		return true;
	}
}
