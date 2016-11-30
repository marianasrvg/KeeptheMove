package com.game.shift.entity.mob;

import com.game.shift.Screen;
import com.game.shift.graficos.Background;
import com.game.shift.graficos.Sprite;

public class Wall extends Mob {
	static int posX = (Background.width_p/2 - 8);
	
	
	public Wall(Sprite sprite){
		this.x = posX;
		setSprite(sprite);
	}
	
	public static void changePosX(int pos){
		posX = pos;
	}
	
	public Wall(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void update(){}
	
	public void move(int xa, int ya){}	

	public void render(Screen screen){
		for (int i = Sprite.INFO; i < Background.height_p; i = i+16){
			screen.renderMob(x, y+i, sprite, 16);
		}
	}
}
