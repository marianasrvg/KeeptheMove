package com.game.shift.entity.mob;

import com.game.shift.entity.Entity;
import com.game.shift.graficos.Background;
import com.game.shift.graficos.Sprite;

public abstract class Mob extends Entity{
	
	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;
	public int vx = 1, vy = 1;
	protected Background world;
	
	
	
	public void move(){}
	public void update(){}
	public void render(){}
	public void setSprite(Sprite sprite){
		this.sprite = sprite;
	}
	public Sprite getSprite(){
		return sprite;
	}
	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}
	protected boolean collision(){
		return false;
	}
	
}
