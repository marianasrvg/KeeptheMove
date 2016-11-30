package com.game.shift.graficos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class HowToWindow extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel title = new JLabel("How to play");
	private JLabel instructions1 = new JLabel("->Dont touch the little squares!!");
	private JLabel instructions2 = new JLabel("->Dont touch the borders of the screen ...");
	private JLabel instructions3 = new JLabel("->Always try to catch the orange square!! (it gives you good bonuses)");
	private JLabel instructions4 = new JLabel("->And remember to keep moving");
	private JButton back = new JButton("Back to main menu");
	private MainMenu backTo;
	
	public HowToWindow(MainMenu menu){
		this.setTitle("How should i play this?");
		this.setSize(900,500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.setBackground(Color.DARK_GRAY);
		this.setResizable(false);
		this.setUndecorated(false);
		this.setLocationRelativeTo(null);
		this.initComponents();
		this.setVisible(true);
		backTo = menu;
	}
	
	private void initComponents(){
		JPanel main = (JPanel) getContentPane();
		/*
		String top = "Dont touch the little squares!\n";
		String mid = "Dont touch the borders of the screen ...\n";
		String bot = "Always try to catch the orange square!! (it gives you good bonuses)\n";
		String last = "And remember to keep moving\n";
		
		String s = top + mid + bot + last;
		*/
		main.setLayout(null);
		main.setBackground(Color.DARK_GRAY);
		title.setBounds(400, 10, 80, 30);
		instructions1.setBounds(300, 100, 250, 30);
		instructions2.setBounds(300, 130, 350, 30);
		instructions3.setBounds(300, 160, 450, 30);
		instructions4.setBounds(300, 190, 250, 30);
		back.setBounds(100, 350, 190, 30);
		back.setEnabled(true);
		back.addActionListener(this);
		
		main.add(title);
		main.add(instructions1);
		main.add(instructions2);
		main.add(instructions3);
		main.add(instructions4);
		main.add(back);
		
	}
	
	
	public void setHelpVisible(){
		backTo.setVisible(false);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton j = (JButton) e.getSource();
		if(j.equals(back)){
			this.setVisible(false);
			backTo.setVisible(true);
		}
		
	}
}
