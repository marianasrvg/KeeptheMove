package com.game.shift.level;


import java.util.*;

import com.game.shift.Screen;
import com.game.shift.entity.Entity;
import com.game.shift.entity.obstacle.Particle;
import com.game.shift.level.tiles.Tile;
import com.game.shift.level.tiles.WallTile;

public class Level {
	
	protected int width, height;
	private int[] tilesInt;
	protected int[] tiles;
	public static Level Ground = new L1_Ground("/levels/level.png");
	
	private List<Entity> entities = new ArrayList<Entity>();
	public List<Particle> obstacles = new ArrayList<Particle>();

	
	public Level(int width, int height){
		this.width = width;
		this.height = height;
		tilesInt = new int[width*height];
		generateLevel();
	}
	
	public Level(String path){
		loadLevel(path);
		generateLevel();
		
	}
	
	protected void generateLevel(){}
	
	protected void loadLevel(String path){}
	
	public void update(){
		for(int i = 0; i < entities.size(); i++)
			entities.get(i).update();
		for(int i = 0; i < obstacles.size(); i++)
			obstacles.get(i).update();
	}
	
	private void time(){}
	
	public void render(int xScroll, int yScroll, Screen screen){
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;
		
		for(int y = y0; y < y1; y++){
			for(int x = x0; x < x1; x++){
				getTile(x, y).render(x, y, screen);
			}
		}
		for(int i = 0; i < entities.size(); i++)
			entities.get(i).render(screen);
		for(int i = 0; i < obstacles.size(); i++){
			obstacles.get(i).render(screen);
		}
		
	}
	
	public Tile getTile(int x, int y){
		if( x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x+y*width] == Tile.C_UNIVERSE) return Tile.universe;
		if (tiles[x+y*width] == Tile.C_DOWNBORDER) return Tile.down_border;
		if (tiles[x+y*width] == Tile.C_LEFTBORDER) return Tile.left_border;
		if (tiles[x+y*width] == Tile.C_TOPBORDER) return Tile.top_border;
		if (tiles[x+y*width] == Tile.C_RIGHTBORDER) return Tile.right_border;
		if (tiles[x+y*width] == Tile.C_C_LD) return Tile.corner_ld;
		if (tiles[x+y*width] == Tile.C_C_RD) return Tile.corner_rd;
		if (tiles[x+y*width] == Tile.C_C_LU) return Tile.corner_lu;
		if (tiles[x+y*width] == Tile.C_C_RU) return Tile.corner_ru;
		if (tiles[x+y*width] == Tile.C_WALL) return Tile.wall;
		return Tile.voidTile;
	}

	public void add(Entity e){
		entities.add(e);
	}
	public void addObstacle(Particle o){
		obstacles.add(o);	
	}
	public List<Particle> getObstacle(){
		return obstacles;
	}
	
	public boolean tileCollision(double x, double y, double xa, double ya, int size){
		boolean solid = false;
		for(int c = 0; c <4; c++){
			int xt = (((int)x+(int)xa) + c % 2 * size)/16;
			int yt = (((int)y+(int)ya) + c / 2 * size)/16;
			if(getTile(xt,yt).solid()) solid = true;;
		}		
		return solid;
	}
}
