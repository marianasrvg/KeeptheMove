package com.game.shift.entity.obstacle;

import java.util.ArrayList;
import java.util.List;
import com.game.shift.Screen;
import com.game.shift.Timing;
import com.game.shift.graficos.Background;
import com.game.shift.graficos.Sprite;
import com.game.shift.level.Level;

public class Bonus extends Particle {
	
	private List<Bonus> bonus = new ArrayList<Bonus>();
	private boolean active = false;
	private boolean taken = false;
	public Timing timer = new Timing();
	
	public Bonus(int x, int y, Level level){
		super(x, y, level);
		this.sprite = Sprite.bonus_t;
		speed = 1.5;
		sprite = Sprite.bonus_t;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	public Bonus(int x, int y, int amount, Level level){
		this(x, y, level);
		for(int i = 0; i < amount -1; i++){
			bonus.add(new Bonus(x, y, level));
		}
		bonus.add(this);	
	}
	
	public Bonus(int amount, Level level){
		this(randomX(), randomY(), level);
		for(int i = 0; i < amount-1; i++){
			bonus.add(new Bonus(randomX(), randomY(), level));
		}
	}
	
	private static int randomX(){
		return (int)(random.nextDouble()*(Background.getWidthS()-32)+16);
	}
	public void update(){
		move();
	}
	
	public void move(){
		if (level.tileCollision_Bonus(x, y, nx, ny, sprite.SIZE)){
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
		screen.renderMob((int)x, (int)y, sprite);	
	}
	
	public boolean isActive(){
		return active;
	}
	
	public void setActive(boolean b){
		this.active = b;
	}

	public boolean isTaken(){
		return taken;
	}
	
	public void setTaken(boolean b){
		this.taken = b;
	}

	public void activate(){
		if(timer.getTime() % 20 == 0){
			active = true;
			if(taken){
				x = randomX();
				y = randomY();
			}
		}
	}
}
