package com.game.shift.graficos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class MainMenu extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel mainTitle = new JLabel("Keep the move!"); // Titulo del juego
	private JLabel ver = new JLabel("ver 0.1"); //Version del juego
	private JButton play = new JButton("PLAY!"); //Boton para jugar
	private JButton howTo = new JButton("How to play"); //Boton para saber como se juega
	//private JButton bye = new JButton("Exit game"); //Boton para salir del juego (en realidad este es inutil)
	private JButton scores = new JButton("High Scores"); //Boton para ver los 
	private JLabel animation1 = new JLabel("");
	private JLabel animation2 = new JLabel("");
	private JLabel animation3 = new JLabel("");
	private JLabel arrow1 = new JLabel("");
	private JLabel arrow2 = new JLabel("");
	private JLabel arrow3 = new JLabel("");
	
	public MainMenu() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
	//	ImageIcon myIcon = new ImageIcon("res/textures/mainIcon.gif");
	//	this.setIconImage(myIcon.getImage());
		this.setTitle("Keep the Move!");
		this.setSize(900,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setUndecorated(false);
		this.setLocationRelativeTo(null);
		this.initComponents();
		this.setVisible(true);
		
	}

	
	private void initComponents() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
	
		JPanel main = (JPanel) getContentPane();
		
		ImageIcon mainIcon = new ImageIcon("res/textures/mainTitle.gif");
		ImageIcon anim1 = new ImageIcon("res/textures/rebote1.gif");
		ImageIcon anim2 = new ImageIcon("res/textures/rebote2.gif");
		ImageIcon anim3 = new ImageIcon("res/textures/spiral.gif");
		ImageIcon anim4 = new ImageIcon("res/textures/arrow.gif");
		
		main.setLayout(null);
		main.setBackground(Color.CYAN);
		mainTitle.setBounds(355, 50, 180, 120);
		mainTitle.setIcon(mainIcon);
		//mainTitle.setFont();
		animation1.setBounds(50, 50, 90, 90);
		animation1.setIcon(anim1);
		animation2.setBounds(800, 50, 90, 90);
		animation2.setIcon(anim2);
		animation3.setBounds(130, 50, 60, 60);
		animation3.setIcon(anim3);
		arrow1.setIcon(anim4);
		arrow2.setIcon(anim4);
		arrow3.setIcon(anim4);
		arrow1.setBounds(370, 215, 20, 20);
		arrow2.setBounds(350, 275, 20, 20);
		arrow3.setBounds(340, 335, 20, 20);
		ver.setBounds(820, 450, 80, 30);
		play.setBounds(400, 200, 80, 50);
		play.setBackground(Color.LIGHT_GRAY);
		howTo.setBounds(375, 260, 130, 50);
		howTo.setBackground(Color.LIGHT_GRAY);
		//bye.setBounds(400, 400, 80, 50);
		scores.setBounds(365, 320, 150, 50);
		scores.setBackground(Color.LIGHT_GRAY);
		
		play.addActionListener(this);
		howTo.addActionListener(this);
		scores.addActionListener(this);
		
		
		main.add(mainTitle);
		main.add(animation1);
		main.add(animation2);
		main.add(animation3);
		main.add(arrow1);
		main.add(arrow2);
		main.add(arrow3);
		main.add(ver);
		main.add(play);
		main.add(howTo);
		//main.add(bye);
		main.add(scores);
		
		//Songs.playSongMenu();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton j = (JButton) e.getSource();
		if(j.equals(play)){
			new Background(this).start();
			this.setVisible(false);
		}
		if(j.equals(howTo)){
			new HowToWindow(this).setHelpVisible();
			this.setVisible(false);
			
		}
		if(j.equals(scores)){
			new ScoresWindow(this).showScores();
			this.setVisible(false);
		}
		
	}
	
}
