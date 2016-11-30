package com.game.shift.level.tiles;

public class TileCoordinate {
	private int x, y;
	private static final int TILE_SIZE = 16;
	private int area;
	
	public TileCoordinate(int x, int y){
		//coordenadas de pixeles a tiles
		this.x = x / TILE_SIZE;
		this.y = y / TILE_SIZE;
		calculateArea();
	}
	
	public int x(){
		return x;
	}
	public int y(){
		return y;
	}
	public int[] xy(){
		int r[] = new int[2];
		r[0] = x;
		r[1] = y;
		return r;
	}
	public static int tileX(int x){
		//de pixeles a tile
		return x / TILE_SIZE;
	}
	public static int tileY(int y){
		//de pixeles a tile
		return y / TILE_SIZE;
	}
	public int getArea() {
		return area;
	}
	
	public void setXY(int x, int y){
		this.x = tileX(x);
		this.y = tileY(y);
		calculateArea();
	}
	protected void calculateArea(){
		if(	x >=0 && x < 5 && y >= 0 && y < 5) area = 1;
		if(	x >=0 && x < 5 && y >= 5 && y < 9) area = 3;
		if(	x >=5 && x < 9 && y >= 0 && y < 5) area = 2;
		if(	x >=5 && x < 9 && y >= 5 && y < 9) area = 4;
		if(	x >=9 && x < 14 && y >= 0 && y < 5) area = 5;
		if(	x >=9 && x < 14 && y >= 5 && y < 9) area = 7;
		if(	x >=14 && x < 18 && y >= 0 && y < 5) area = 6;
		if(	x >=14 && x < 18 && y >= 5 && y < 9) area = 8;
	}
}
