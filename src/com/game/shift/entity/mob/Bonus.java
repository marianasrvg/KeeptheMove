package com.game.shift.entity.mob;

import com.game.shift.Screen;
import com.game.shift.graficos.Background;
import com.game.shift.graficos.Sprite;

public class Bonus extends Mob {

	public boolean taken = false;
	public boolean active = false;
	
	public Bonus(){}
	

	public Bonus(Background world) {
		this.world = world;
		spawnRand();
		activate();
	}

	public void update(Screen screen) {
		
		move(screen);
		collisionPlayer(screen);
	}

	public void move(Screen screen) {
		if (x + vx > Background.width_p - Sprite.bonus_t.SIZE)
			vx *= -1;
		if (x + vx <= Sprite.BORDE)
			vx *= -1;
		if (y + vy > Background.height_p - Sprite.bonus_t.SIZE)
			vy *= -1;
		if (y + vy <= Sprite.INFO)
			vy *= -1;

		x += vx;
		y += vy;
	}

	private void spawnRand() {
		x = (int) (Math.random() * (Sprite.BORDE - (Background.width_p-Sprite.BORDE)) + (Background.width_p-Sprite.BORDE));
		y = (int) (Math.random() * ((Sprite.BORDE+Sprite.INFO)-(Background.height_p - Sprite.BORDE ))+ (Background.height_p - Sprite.BORDE ));
	}

	public void render(Screen screen) {
		screen.renderMob(x, y, Sprite.bonus_t, 6);
	}

	public void activate() {
		if(world.timer.getTime() % 1200 == 0){
			active = true;
			if(taken)
				spawnRand();
				taken = false;
		}
	}

	public void collisionPlayer(Screen screen) {
		try {
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 6; j++) {
					if (this.x == world.playerone.x ){
						if(!taken){
						world.playerone.taken = true;
						world.playerone.active = false;
						taken = true;
						active = false;
						}
					}
					if (this.x == world.playertwo.x ) {
						if(!taken){
						world.playertwo.taken = true;
						world.playertwo.active = false;
						taken = true;
						active = false;
						}
					}
				}
			}
		} catch (Exception e) {

		}
	}
}
