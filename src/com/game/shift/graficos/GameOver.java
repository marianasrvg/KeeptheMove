package com.game.shift.graficos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOver extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel gameTitle = new JLabel("");
	private JLabel pScoreJ1 = new JLabel("Score P1");
	private JLabel sJ1 = new JLabel("");
	private JLabel sJ2 = new JLabel("");
	private JLabel pScoreJ2 = new JLabel("Score P2");
	private JButton goBack = new JButton("Back to main menu");
	private MainMenu goMenu;
	private Background bck;
	
	public GameOver(MainMenu thatMenu, Background game){
		goMenu = thatMenu;
		bck = game;
		this.setTitle("Game over");
		this.setSize(600,250);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setUndecorated(false);
		this.setLocationRelativeTo(null);
		this.initComponents();
		this.setVisible(true);
	}
	
	private void initComponents(){
		JPanel main = (JPanel) getContentPane();
		
		ImageIcon icon = new ImageIcon("res/textures/GameOver.png");
		
		main.setLayout(null);
		main.setBackground(Color.CYAN);
		gameTitle.setBounds(235, 30, 120, 40);
		gameTitle.setIcon(icon);
		pScoreJ1.setBounds(120, 90, 80, 30);
		sJ1.setBounds(120, 120, 80, 30);
		sJ1.setText(bck.playerone.toString());
		pScoreJ2.setBounds(400, 90, 80, 30);
		sJ2.setBounds(400, 120, 80, 30);
		sJ2.setText(bck.playertwo.toString());
		goBack.setBounds(210, 190, 160, 25);
		goBack.setEnabled(true);
		goBack.addActionListener(this);
		
		main.add(gameTitle);
		main.add(pScoreJ1);
		main.add(pScoreJ2);
		main.add(sJ1);
		main.add(sJ2);
		main.add(goBack);
		
	}
	
	public void showMe(){
		bck.setVisible(false);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton j = (JButton) e.getSource();
		if(j.equals(goBack)){
			goMenu.setVisible(true);
			this.setVisible(false);
		}
		
	}
	
}
