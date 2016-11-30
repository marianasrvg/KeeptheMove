package com.game.shift;

public class Timing {
	private long time = 12000;
	private long now = 0;
	public static int morePoints = 1000;  
	
	public Timing(){
		now = System.currentTimeMillis();
	}

	public long getTime(){
		return time;
	}
	
	public void render(){
		if(System.currentTimeMillis() - now > 1000){
			time = ((time-1)<=0)?0: time - 1;
		}
		
	}
	
	public String timerString(){
		try{
			return String.valueOf(time).substring(0, 3);
		} catch (Exception e){
			return "000";
		}
		}
}
