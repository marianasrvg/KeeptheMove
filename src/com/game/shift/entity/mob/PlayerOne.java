package com.game.shift.entity.mob;

import com.game.shift.graficos.Background;
import com.game.shift.input.Keyboard;
import com.game.shift.level.tiles.TileCoordinate;

public class PlayerOne extends Player {
	
	public PlayerOne(Keyboard input, Background world){
		super(input, world);
		posRandom();
		xy_tile = new TileCoordinate(x, y);
	}
	
	public PlayerOne(int x, int y,  Keyboard input){
		super(x, y, input);
	}
		
	public void update(){
		if(collisionObstacles()) setPoints(minusObstacle);
		
		if(collisionBonus()) chooseBonus();
		if (bonus_active && timing == 0){
			reverseBonus();
		}
		else if (bonus_active){
			timing--;
		}
		
		int xa = 0, ya = 0;
		if(input.up1) ya--;
		if(input.down1) ya++;
		if(input.left1) xa--;
		if(input.right1) xa++;	
		
		if(xa!= 0 || ya!= 0) move (xa, ya); 
		
	}
		
	private void posRandom(){
		x = Background.getWidthS()/4;
		y = Background.getHeightS()/2;
	}
	
	protected void ChangeMap() {
		world.level.loadLevel("/levels/level_1.png");
	}
	
	protected void ColorChange() {
		
	}
}
