package com.game.shift.graficos;

public class Sprite {
	public final int SIZE;
	private int x, y;
	private int width, height;
	public int[] pixels;
	private SpriteSheet sheet;
	public int id;
	public static final int BORDE = 3;
	public static final int INFO = 10;
	
	public static Sprite voidSprite = new Sprite (16, 0, 0, SpriteSheet.tiles);
	public static Sprite universe = new Sprite (16, 0, 0, SpriteSheet.tiles);
	public static Sprite left_border = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite right_border = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite down_border = new Sprite(16, 3, 0, SpriteSheet.tiles);
	public static Sprite top_border = new Sprite(16, 4, 0, SpriteSheet.tiles);
	public static Sprite corner_ld = new Sprite(16, 5, 0, SpriteSheet.tiles);
	public static Sprite corner_rd = new Sprite(16, 6, 0, SpriteSheet.tiles);
	public static Sprite corner_lu = new Sprite(16, 7, 0, SpriteSheet.tiles);
	public static Sprite corner_ru = new Sprite(16, 8, 0, SpriteSheet.tiles);
	public static Sprite wall = new Sprite(16, 9, 0, SpriteSheet.tiles);

	public static Sprite player = new Sprite(8, 0, 0, SpriteSheet.player, 5);
	
	
	public static Sprite obstaculo_green = new Sprite (4, 1, 0, SpriteSheet.bolitas);
	public static Sprite obstaculo_pink = new Sprite (4, 2, 0, SpriteSheet.bolitas, 2);
	public static Sprite obstaculo_yellow = new Sprite (4, 2, 2, SpriteSheet.bolitas, 9);
	public static Sprite bonus_t = new Sprite(6,0,0,SpriteSheet.bonus, 6);
	
	public Sprite(int size, int colour){
		this.SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE*SIZE];
		setColour(colour);
	}
	
	public Sprite(int width, int height, int colour){
		SIZE = width*height;
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
		setColour(colour);
	}
	
	public Sprite(int size, int x, int y, SpriteSheet sheet){
		SIZE = size;
		pixels = new int [SIZE*SIZE];
		this.x = x*SIZE;
		this.y = y*SIZE;
		this.width = size;
		this.height = size;
		this.sheet = sheet;
		load();
	}
	
	public Sprite(int size, int x, int y, SpriteSheet sheet, int id){
		SIZE = size;
		pixels = new int [SIZE*SIZE];
		this.x = x*SIZE;
		this.y = y*SIZE;
		this.sheet = sheet;
		this.id = id;
		load();
	}
	
	public void load(){
		for ( int y = 0; y < SIZE; y++){
			for (int  x = 0; x < SIZE; x++){
				pixels[x+y*SIZE] = sheet.pixels[(x+this.x)+(y+this.y)*sheet.SIZE];
			}
		}
	}
	
	private void setColour(int colour){
		for (int i = 0; i < width*height; i++){
			pixels[i] = colour;
		}
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
}
