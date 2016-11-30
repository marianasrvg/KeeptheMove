package com.game.shift.graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private String path;
	public final int SIZE;
	public int[] pixels;
	// BACKGROUND - BARRA - ROSA - VERDE - BLANCO - PLAYER - BONUS - INFO - ARROW
	public static final int COLORES[] = 
			{0x00000000, //background
			0xFF56E0FF, //barra
			0xFFFF6890,  //rosa
			0xFF7FFF8E,  //verde
			0xFFFFFFFF,  //blanco
			0xFFFEFFC4,  //amarillo
			0x00FF7F16, 	//bonus
			0x00404040,  //info
			0x00FF265C,	//arrow
			0xFFFFE438, //rosa-amarillo
			}; 
	
	public static SpriteSheet tiles = new SpriteSheet("/textures/spritesheet.png", 256);
	public static SpriteSheet bolitas = new SpriteSheet("/textures/obstaclesheet.png", 256);
	public static SpriteSheet player = new SpriteSheet("/textures/playersheet.png", 256);
	public static SpriteSheet bonus = new SpriteSheet("/textures/bonusheet.png", 256);
	
	public SpriteSheet(String path, int size){
		this.path = path;
		this.SIZE = size;
		pixels = new int[SIZE*SIZE];
		load();
	}
	
	private void load(){
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0,0,w,h,pixels,0,w);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
