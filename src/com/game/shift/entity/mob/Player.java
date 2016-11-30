package com.game.shift.entity.mob;

import com.game.shift.Screen;
import com.game.shift.entity.obstacle.Particle;
import com.game.shift.graficos.Sprite;
import com.game.shift.input.Keyboard;

public class Player extends Mob {
	protected Keyboard input;
	protected int points;
	protected final int MAX_POINT = 100;

	public Player(Keyboard input) {
		this.input = input;
		this.sprite = Sprite.player;
		this.points = MAX_POINT;
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		this.sprite = Sprite.player;
	}

	public void setPoints(int x) {
		points += x;
	}

	public int getPoints() {
		return points;
	}

	public String toString() {
		if (getPoints() < 0)
			return "0";
		return String.valueOf(getPoints());
	}

	public void render(Screen screen) {
		screen.renderMob(x, y, sprite);
	}

	public void move(int xa, int ya, Screen screen) {
		if (xa != 0 && y != 0) {
			move(xa, 0);
			move(ya, 0);
		}
		if (xa > 0)
			dir = 1;
		if (xa < 0)
			dir = 3;
		if (ya > 0)
			dir = 2;
		if (ya < 0)
			dir = 0;

		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		} else {
			setPoints(-5);
		}
	}

	public void move(int xa, int ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		if (xa > 0)
			dir = 1;
		if (xa < 0)
			dir = 3;
		if (ya > 0)
			dir = 2;
		if (ya < 0)
			dir = 0;

		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		} else
			setPoints(-5);

		xy_tile.setXY(x, y);
		// System.out.println("xt:"+ xy_tile.x()+ " yt:" + xy_tile.y());
		// System.out.println("area "+ xy_tile.getArea());
	}

	protected boolean collision(int xa, int ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 7) / 16;
			int yt = ((y + ya) + c / 2 * 7) / 16;
			if (level.getTile(xt, yt).solid())
				solid = true;
			;
		}
		return solid;
	}

	protected boolean collisionObstacles() {
		Particle o;
		boolean collision = false;
		for (int i = 0; i < level.getObstacle().size(); i++) {
			if (level.getObstacle().get(i).xy_tile.getArea() == this.xy_tile.getArea()) {
				o = level.getObstacle().get(i);
				if(this.x-o.getSpriteSIZE() <= o.x() && 
					(this.x)+this.sprite.SIZE+o.getSpriteSIZE() >= (o.x())+o.getSpriteSIZE()&& 
					this.y-o.getSpriteSIZE() <= o.y() &&
					(this.y)+this.sprite.SIZE+o.getSpriteSIZE() >= (o.y())+o.getSpriteSIZE()){
					/*System.out.println(" x - " + this.x + " o.x" + o.x());
					System.out.println(" xsize - " + (this.x+this.sprite.SIZE) + " o.xsize" + (o.x()+o.getSpriteSIZE()));
					System.out.println(" y - " + this.y + " o.y" + o.x());
					System.out.println(" ysize - " + (this.y+this.sprite.SIZE) + " o.ysize" + (o.y()+o.getSpriteSIZE()));*/
					//setPoints(-1);
					collision = true;
					}
				}
			}
		return collision;
	}
}
