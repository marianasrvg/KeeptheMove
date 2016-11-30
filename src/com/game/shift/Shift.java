package com.game.shift;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.game.shift.graficos.*;

public class Shift {
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		/*Background game = new Background();
		game.start();
		*/
		new MainMenu();

		//new GameOver(new MainMenu(), new Background());
		Songs.playSongMenu();

	}
}
