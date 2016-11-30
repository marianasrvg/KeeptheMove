package com.game.shift.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.game.shift.level.tiles.Tile;

public class L1_Ground extends Level {
	
	public L1_Ground(String path) {
		super(path);
	}

	protected void loadLevel(String path){
		try{
			BufferedImage image = ImageIO.read(L1_Ground.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w*h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e){
			e.printStackTrace();
			System.out.println("Exception! Could not load level file!");
		}
	}
	
	protected void generateLevel(){
	}
}
