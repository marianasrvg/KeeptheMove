package com.game.shift.graficos;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.*;
import com.game.shift.Screen;
import com.game.shift.Timing;
import com.game.shift.entity.mob.PlayerOne;
import com.game.shift.entity.mob.PlayerTwo;
import com.game.shift.entity.obstacle.Particle;
import com.game.shift.entity.obstacle.Bonus;
import com.game.shift.entity.obstacle.Obstacles;
import com.game.shift.input.Keyboard;
import com.game.shift.level.L1_Ground;
import com.game.shift.level.Level;

public class Background extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	private static int width = 300;
	private static int height = 168;
	private static int scale = 3;
	public static String title = "Keep the MOVE!";
	private static final String loc1 = "res/files/score_p1.txt";
	private static final String loc2 = "res/files/score_p2.txt";
	

	private Thread thread;
	private boolean running = false;
	public Keyboard key;
	public JFrame frame;
	public PlayerOne playerone;
	public PlayerTwo playertwo;
	public Particle obstacles;
	public Bonus bonus;
	private Timing timer;
	public Level level;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	private Screen screen;
	private MainMenu myMenu;//Este si es necesario
	
	public Background(MainMenu menu){
		Dimension size = new Dimension(width*scale, height*scale);
		setPreferredSize(size);
		
		screen = new Screen(width,height);
		frame = new JFrame();
		key = new Keyboard();
		//level = Level.Ground;
		level = new L1_Ground("/levels/level.png");
		bonus = new Bonus(1, level);
		obstacles = new Obstacles(20, level);
		timer = new Timing();
		frameCaracteristicas();
		playerone = new PlayerOne(key, this);
		playerone.init(level);
		playertwo = new PlayerTwo(key, this);
		playertwo.init(level);
		myMenu = menu;		
	}
	
	private void frameCaracteristicas(){
		frame.addKeyListener(key);
		frame.setResizable(false);
		frame.setTitle(title);
		frame.add(this);
		frame.pack();
		frame.setBackground(Color.BLACK);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
	
	public static int getWindowWidth(){
		return width * scale;
	}
	public static int getWidthS(){
		return width;
	}
	public static int getHeightS(){
		return height;
	}
	public static int getWindowHeight(){
		return height * scale;
	}
	
	public synchronized void start(){
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	public synchronized void stop(){
		running = false;
		try{
			thread.join();
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public void run() {
		
		long lastTime = System.nanoTime();
		long game_time = System.currentTimeMillis(); 
		final double ns = 1000000000.0 / 60.0; 
		double delta = 0;
		int frames = 0;
		int updates = 0;
		//requestFocus();
		while(running){
			long now = System.nanoTime();
			delta+= (now-lastTime) /ns;
			lastTime = now;
			while (delta >= 1){
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
				if( System.currentTimeMillis() - game_time > 1000){ //lo va a hacer una vez por sec. 
					game_time += 1000;
					timer.secondLess();
					bonus.timer.secondLess();
					frame.setTitle(title+ " | "+ updates + "ups, " + frames + " fps");
					updates = 0;
					frames = 0;
				}
		}
	}
	
	public void update(){
		key.update();
		playerone.update();
		playertwo.update();
		level.update();
		bonus.activate();
		if(bonus.isActive()) bonus.update();
	}
	
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if ( bs == null){
			createBufferStrategy(3);
			return;
		}
		
		level.render(0, 0, screen);
		playerone.render(screen);
		playertwo.render(screen);
		if(bonus.isActive()) bonus.render(screen);
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = screen.pixels[i];
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(new Color(0xF2F6FF));
		g.setFont(new Font("Hyperspace", 0, 18));
		g.drawString("POINTS "+playerone.toString(), 30, 475);
		g.drawString(playertwo.toString()+ " POINTS", 780, 475);
		g.drawString(timer.timerString(), 440, 25);
		g.drawString(playerone.getNews(), 110, 25);
		g.drawString(playertwo.getNews(), 600, 25);
		g.dispose();
		bs.show();
		if(playerone.getPoints() < 0 || playertwo.getPoints() < 0 || timer .getTime() == 0){
			running = false;
			this.writeScores();
			this.showEnd();
		}
	}
	
	public void writeScores(){
		if(running == false){
			try{
				FileWriter fw1 = new FileWriter(loc1, true);
				FileWriter fw2 = new FileWriter(loc2, true);
				BufferedWriter bf1 = new BufferedWriter(fw1);
				BufferedWriter bf2 = new BufferedWriter(fw2);
				
				bf1.write(""+playerone.getPoints());
				bf1.newLine();
				bf2.write(""+playertwo.getPoints());
				bf2.newLine();
				bf1.close();
				bf2.close();
				fw1.close();
				fw2.close();
				
			}catch(Exception e){
				System.out.println(e.getStackTrace());
			}
		}
	}
	public void showEnd(){
		if(running == false){
			level.clearList();
			bonus.clearList();
			this.setVisible(false);
			frame.setVisible(false);
			new GameOver(myMenu, this).showMe();
		}
	}
}
