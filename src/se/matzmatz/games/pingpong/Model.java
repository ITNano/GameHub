package se.matzmatz.games.pingpong;

import java.util.Timer;
import java.util.TimerTask;

public class Model {
	
	private Timer clockTimer;
	private int time;

	public Model() {
		this.time = 0;
		this.clockTimer = new Timer();
	}
	
	public int getClockTime() {
		return time;
	}
	
	public void startGame() {
		startClock();
	}
	
	public void terminate() {
		stopClock();
	}
	
	
	private void startClock() {
		this.clockTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				time += 1;
			}
		}, 0, 1000);
	}
	
	private void stopClock() {
		this.clockTimer.cancel();
	}
}
