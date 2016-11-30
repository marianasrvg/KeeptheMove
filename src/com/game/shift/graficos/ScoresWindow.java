package com.game.shift.graficos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ScoresWindow extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String location1 = "res/files/score_p1.txt";
	private static final String location2 = "res/files/score_p2.txt";
	private JLabel score = new JLabel("Scores");
	private JLabel p1score = new JLabel("Player 1 Score");
	private JLabel p2score = new JLabel("Player 2 Score");
	private Vector<String> readP1File = new Vector<String>();
	private Vector<String> readP2File = new Vector<String>();
	private JList<String> scores_1 = new JList<String>(readP1File);
	private JList<String> scores_2 = new JList<String>(readP2File);
	private JScrollPane show1 = new JScrollPane(scores_1);
	private JScrollPane show2 = new JScrollPane(scores_2);
	private JButton back = new JButton("<-Back");
	private MainMenu mainM;

	public ScoresWindow(MainMenu main) {
		this.setTitle("Scores");
		this.setSize(900, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setBackground(Color.DARK_GRAY);
		this.setResizable(false);
		this.setUndecorated(false);
		this.setLocationRelativeTo(null);
		this.initComponents();
		this.setVisible(true);
		mainM = main;
	}

	private void initComponents() {
		JPanel main = (JPanel) getContentPane();

		main.setLayout(null);
		main.setBackground(Color.CYAN);
		score.setBounds(420, 15, 80, 30);
		p1score.setBounds(250, 40, 100, 30);
		p2score.setBounds(550, 40, 100, 30);
		show1.setBounds(250, 80, 80, 200);
		show1.setBackground(Color.LIGHT_GRAY);
		show2.setBounds(550, 80, 80, 200);
		back.setBounds(10, 10, 80, 30);
		back.addActionListener(this);
		main.add(score);
		main.add(p1score);
		main.add(p2score);
		main.add(show1);
		main.add(show2);
		main.add(back);
	}

	public void showScores() {
		this.readFiles();
		mainM.setVisible(false);
		this.setVisible(true);
	}

	private void readFiles() {
		try {
			FileReader fr1 = new FileReader(location1);
			FileReader fr2 = new FileReader(location2);
			BufferedReader bf1 = new BufferedReader(fr1);
			BufferedReader bf2 = new BufferedReader(fr2);

			String line1 = bf1.readLine();
			while (line1 != null) {
				readP1File.add(line1);
				line1 = bf1.readLine();
			}
			String line2 = bf2.readLine();
			while (line2 != null) {
				readP2File.add(line2);
				line2 = bf2.readLine();
			}

			bf1.close();
			bf2.close();

			fr1.close();
			fr2.close();

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		scores_1.setListData(readP1File);
		scores_2.setListData(readP2File);
		show1.repaint();
		show2.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton j = (JButton) e.getSource();
		if (j.equals(back)) {
			this.setVisible(false);
			mainM.setVisible(true);
		}
	}
}
