package com.game.shift.entity.obstacle;

import java.util.ArrayList;
import java.util.List;

import com.game.shift.Screen;
import com.game.shift.graficos.Background;
import com.game.shift.graficos.Sprite;
import com.game.shift.level.Level;

public class Bonus extends Particle {
	
	private List<Bonus> bonus = new ArrayList<Bonus>();
	
	public Bonus(int x, int y, Level level){
		super(x, y, level);
		this.sprite = Sprite.bonus_t;
		speed = 3;
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
		this.x += nx;
		this.y += ny;
	}
	
	
	
	public void render(Screen screen){
		screen.renderSprite((int)x, (int)y, sprite, false);
	}
	
}
