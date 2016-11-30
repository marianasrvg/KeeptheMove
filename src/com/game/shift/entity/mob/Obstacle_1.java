package com.game.shift.entity.mob;

import com.game.shift.Screen;
import com.game.shift.graficos.Background;
import com.game.shift.graficos.Sprite;
import com.game.shift.graficos.SpriteSheet;

public class Obstacle_1 extends Mob {
	private int lado_terreno = 1;
	
	public Obstacle_1(int lado, Sprite sprite, Background world){
		this.lado_terreno = lado;
		setSprite(sprite);
		this.world = world;
		posRandom();
	}


	public void update(Screen screen){
		move(screen);
	}
	
	public void move(Screen screen){
		if(collisionObstacle(screen)) {
			vx*= -1;
			vy *= -1;
		}
		if(collisionBarra(screen)) {
			vx*= -1;
		}
		if( x + vx >= Background.width_p - (Sprite.BORDE+2))  vx *= -1;
		if( x + vx <= 0)  vx *= -1;
		if( y + vy >= Background.height_p - (Sprite.BORDE+2))  vy *= -1;
		if( y + vy <= Sprite.INFO)  vy *= -1;

		x += vx;
		y += vy;	
	}
	
	protected boolean collisionBarra(Screen screen){
		try{
			if(screen.pixels[x+y*Background.width_p] == SpriteSheet.COLORES[world.wall.sprite.id])
				return true;
		} catch (Exception e){
			return true;
		}
		return false;
	}
	
	protected boolean collisionObstacle(Screen screen){
		try{
			
			if(screen.pixels[(x)+(y)*Background.width_p] == SpriteSheet.COLORES[world.obstacle_l[0].sprite.id]) 
				return true;
			if(screen.pixels[(x)+(y)*Background.width_p] == SpriteSheet.COLORES[world.obstacle_r[0].sprite.id]) 
				return true;
			if(screen.pixels[(x)+(y)*Background.width_p] == SpriteSheet.COLORES[Sprite.player_b.id]) 
				return true;
		} catch (Exception e){
			return true;
		}
		return false;
	}
	
	private void posRandom(){
		if(lado_terreno == 1){
			x = (int)(Math.random()*(Sprite.BORDE - (Wall.posX-world.wall.sprite.SIZE)) + Wall.posX-Sprite.obstaculo_blue.SIZE);
			if (x >= Background.width_p/4 && x <= (Background.width_p/4)+Sprite.obstaculo_blue.SIZE)
				posRandom();
		}
		if(lado_terreno == 2)
			x = (int)(Math.random()* ((Wall.posX+Sprite.obstaculo_pink.SIZE+8)-(Background.width_p-Sprite.BORDE))+ Background.width_p-Sprite.BORDE);
			if( x >= 3*Background.width_p/4 && x <= (3*Background.width_p/4)+Sprite.obstaculo_blue.SIZE)
				posRandom();
		y =(int)(Math.random()*((Sprite.INFO+Sprite.BORDE)-(Background.height_p-Sprite.BORDE*2))+ Background.height_p-Sprite.BORDE*2);
		if( y >= Background.height_p/2 && y <= (Background.height_p/2)+Sprite.obstaculo_blue.SIZE)
			posRandom();
	}
	
	public void render(Screen screen){
			screen.renderMob(x, y, this.sprite, 4);
	}
}