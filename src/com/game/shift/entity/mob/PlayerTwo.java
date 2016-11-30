package com.game.shift.entity.mob;

import com.game.shift.Screen;
import com.game.shift.graficos.Background;
import com.game.shift.graficos.Sprite;
import com.game.shift.input.Keyboard;
import com.game.shift.level.tiles.TileCoordinate;

public class PlayerTwo extends Player {
	
	public PlayerTwo(Keyboard input){
		super(input);
		posRandom();
		xy_tile = new TileCoordinate(x, y);
	}
	
	public PlayerTwo(int x, int y,  Keyboard input){
		super(x, y, input);
	}
		
	public void update(Screen screen){
		int xa = 0, ya = 0;
		if(input.up2) ya--;
		if(input.down2) ya++;
		if(input.left2) xa--;
		if(input.right2) xa++;	
		
		if(xa!= 0 || ya!= 0) move (xa, ya); 
		
	}
		
		
	private void posRandom(){
		x = 3*Background.getWidthS()/4;
		y = Background.getHeightS()/2;
	}
	
}
