package com.game.shift.entity.mob;

import com.game.shift.Screen;
import com.game.shift.entity.obstacle.Particle;
import com.game.shift.graficos.Background;
import com.game.shift.graficos.Sprite;
import com.game.shift.graficos.SpriteSheet;
import com.game.shift.input.Keyboard;

public class Player extends Mob {
	protected Keyboard input;
	protected int points;
	
	public Player(Keyboard input){
		this.input = input;
		this.sprite = Sprite.player;
	}
	
	public Player(int x, int y, Keyboard input){
		this.x = x;
		this.y = y;
		this.input = input;
		this.sprite = Sprite.player;
	}
		
	public void setPoints(int x){
			points += x;
	}
	
	public int getPoints(){
		return points;
	}
	
	public String toString(){
		if (getPoints()<0) return "0";
		return String.valueOf(getPoints());
	}
	
	public void render(Screen screen){
		screen.renderMob(x, y, sprite);
	}

	public void move(int xa, int ya, Screen screen){
		if(xa != 0 && y != 0){
			move(xa, 0);
			move(ya, 0);
		}
		if(xa > 0) dir = 1;
		if(xa < 0) dir = 3;
		if(ya > 0) dir = 2;
		if(ya < 0) dir = 0;
		
		if(!collision(xa, ya)){
			x += xa;
			y += ya;
		} else{
			setPoints(-5);
		}
	}
	public void move(int xa, int ya){
		if(xa != 0 && ya != 0){
			move(xa, 0);
			move(0, ya);
			return;
		}
		if(xa > 0) dir = 1;
		if(xa < 0) dir = 3;
		if(ya > 0) dir = 2;
		if(ya < 0) dir = 0;
		
		if(!collision(xa, ya)){
			x += xa;
			y += ya;
		} 
	}
	
	protected boolean collision(int xa, int ya){
		boolean solid = false;
		for(int c = 0; c <4; c++){
			int xt = ((x+xa) + c % 2 * 7)/16;
			int yt = ((y+ya) + c / 2 * 7)/16;
			if(level.getTile(xt,yt).solid()) solid = true;;
		}		
		return solid;
	}

	
}
