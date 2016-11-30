package com.game.shift.entity.obstacle;

import java.util.*;

import com.game.shift.Screen;
import com.game.shift.entity.Entity;
import com.game.shift.entity.mob.Player;
import com.game.shift.graficos.Sprite;
import com.game.shift.level.Level;
import com.game.shift.level.tiles.TileCoordinate;

public class Particle extends Entity {
	
	protected double angle;
	protected double nx, ny;
	protected double x, y;
	protected double speed;
	protected static final Random random = new Random();
	protected Sprite sprite;
	
	
	public Particle(){}
	
	public Particle(int x, int y, Level level){
		//this.angle = dir;
		this.x = x;
		this.y = y;
		this.init(level);
		xy_tile = new TileCoordinate(x, y);
		this.nx = random.nextGaussian();
		this.ny = random.nextGaussian();
		this.angle = Math.atan2(nx, ny);
		
	}
	
	public Particle(int x, int y, int amount, Level level){
		this(x,y, level);
		for(int i = 0; i < amount-1; i++){
			addObstacle(new Particle(x, y, level));
		}
		addObstacle(this);
	}
	
	public void addObstacle(Particle o){
		level.addObstacle(o);
	}
	
	public Sprite getSprite(){
		return sprite;
	}
	public int getSpriteSIZE(){
		return sprite.SIZE;
	}
	protected void clear(){
		for(int i = 0; i < level.getObstacle().size(); i++){
			Particle o = level.getObstacle().get(i);
			if(o.isRemoved()) level.getObstacle().remove(i);
		}
	}
	public void update(){
		clear();
		move();
	}	
	public void move(){
		if (level.tileCollision(x, y, nx, ny, sprite.SIZE)){
			double xangle = 90 - this.angle;
			this.angle = -xangle;
			nx = speed * Math.cos(angle);
			ny = speed * Math.sin(angle);
		}
		x += nx;
		y += ny;
		
		xy_tile.setXY((int)x, (int)y);
	}
	
	public void render(Screen screen){
		screen.renderTile((int)x, (int)y, sprite);
	}

	public int x(){
		return (int)x;
	}
	public int y(){
		return (int)y;
	}
}
