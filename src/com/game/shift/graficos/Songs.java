package com.game.shift.graficos;

import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Songs{
	public static void playSongMenu() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("res/audio/mainmenu.wav"));
		Clip song = AudioSystem.getClip();
		song.open(inputStream);
		song.loop(Clip.LOOP_CONTINUOUSLY);
	}
}