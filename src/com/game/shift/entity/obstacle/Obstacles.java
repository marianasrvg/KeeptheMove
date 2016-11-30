package com.game.shift.entity.obstacle;

import com.game.shift.graficos.Background;
import com.game.shift.graficos.Sprite;
import com.game.shift.level.Level;

public class Obstacles extends Particle{
		
	public Obstacles(int x, int y, Level level) {
		super(x, y, level);
		speed = 1.2;
		sprite = Sprite.obstaculo_blue;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	public Obstacles(int x, int y, int amount, Level level){
		this(x,y, level);
		for(int i = 0; i < amount-1; i++){
			addObstacle(new Obstacles(x, y, level));
		}
		addObstacle(this);
	}
	
	public Obstacles(int amount, Level level){
		this(randomX(true), randomY(), level);
		for(int i = 0; i < (amount)/2; i++){
			addObstacle(new Obstacles(randomX(true), randomY(), level));
			addObstacle(new Obstacles(randomX(false), randomY(), level));
		}
		addObstacle(this);
	}
	
	private static int randomX(boolean field){
		if(field)
			return (int)(random.nextDouble()*(150-32)+16);
		return (int)(random.nextDouble()*(Background.getWidthS()/2-32) + (Background.getWidthS()/2+16));
	}
	private static int randomY(){
		return (int)(random.nextDouble()*(Background.getHeightS()-40)+ 16);
	}
	public void addObstacle(boolean field){
		addObstacle(new Obstacles(randomX(field),randomY(), this.level));
	}

	

}
