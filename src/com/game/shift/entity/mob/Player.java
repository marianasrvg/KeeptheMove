package com.game.shift.entity.mob;


import com.game.shift.Screen;
import com.game.shift.Timing;
import com.game.shift.entity.obstacle.Bonus;
import com.game.shift.entity.obstacle.Particle;
import com.game.shift.graficos.Background;
import com.game.shift.graficos.Sprite;
import com.game.shift.input.Keyboard;
import com.game.shift.level.Level;

public class Player extends Mob {
	protected Keyboard input;
	protected int points;
	protected final int MAX_POINT = 100;
	protected Background world;
	protected int minusObstacle = 0;
	protected int minusWall = -5;
	protected Timing timer_b = new Timing();
	protected int timing = 600;
	protected boolean bonus_active = false;

	public Player(Keyboard input, Background world) {
		this.input = input;
		this.sprite = Sprite.player;
		this.points = MAX_POINT;
		this.world = world;
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

	public void move(int xa, int ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		if (xa > 0) dir = 1;
		if (xa < 0) dir = 3;
		if (ya > 0) dir = 2;
		if (ya < 0) dir = 0;

		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		} else
			setPoints(minusWall);

		xy_tile.setXY(x, y);
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
					collision = true;
					}
				}
			}
		return collision;
	}
	
	protected boolean collisionBonus(){
		if(world.bonus.isTaken()) return false;
		Bonus b;
		for(int i = 0; i < world.bonus.getBonus().size(); i++){
			if (world.bonus.getBonus().get(i).xy_tile.getArea() == this.xy_tile.getArea()) {
				b = world.bonus.getBonus().get(i);
				if(this.x-b.getSpriteSIZE() <= b.x() && 
					(this.x)+this.sprite.SIZE+b.getSpriteSIZE() >= (b.x())+b.getSpriteSIZE()&& 
					this.y-b.getSpriteSIZE() <= b.y() &&
					(this.y)+this.sprite.SIZE+b.getSpriteSIZE() >= (b.y())+b.getSpriteSIZE()){
					world.bonus.setActive(false);
					world.bonus.setTaken(true);
					return true;
					}
				}
			}
		return false;
		}

	protected void chooseBonus(){
		int r = (int)(random.nextDouble()*3);
		switch (r){
		case 0:
			BonusMorePoints();
			break;
		case 1:
			Immunity();
			bonus_active = true;
			break;
		case 2:
			ChangeMap();
			bonus_active = true;
			break;
		case 3:
			ColorChange();
			bonus_active = true;
			break;
		}
		
	}

	protected void ColorChange() {
	}

	protected void Immunity() {
		minusWall = 0;
		minusObstacle = 0;
		System.out.println("BONUS IMMUNITY");
	}

	protected void ChangeMap() {
		world.level.loadLevel("/levels/level_1.png");
	}

	protected void BonusMorePoints() {
		setPoints(10);
		System.out.println("BONUS + 10");
	}
	
	protected void reverseBonus(){
		timing = 600;
		minusWall = -5;
		minusObstacle = 0;
		world.level.loadLevel("/levels/level.png");
	}

}
