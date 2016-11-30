package com.game.shift;

public class Timing {
	private long time = 120; 
	
	public void secondLess(){
		time--;
	}

	public long getTime(){
		return time;
	}
	
	
	public String timerString(){
			return String.valueOf(time);
	}
}
