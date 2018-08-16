package se.matzmatz.games.pingpong.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Model {
	
	private static final int UPDATE_INTERVAL = 20;
	private static final double UPDATE_DELTA = 1/((double)UPDATE_INTERVAL);
	
	private Timer clockTimer;
	private Timer gameTimer;
	private IntegerProperty time;
	private List<GameObject> objects;
	private int windowWidth, windowHeight;

	public Model(int width, int height) {
		this.time = new SimpleIntegerProperty(0);
		this.windowWidth = width;
		this.windowHeight = height;
		this.objects = new ArrayList<GameObject>();
		this.clockTimer = new Timer();
		this.gameTimer = new Timer();
	}
	
	public void startGame() {
		createGameObjects();
		startClock();
		startGameTimer();
	}

	public IntegerProperty clockTime() {
		return time;
	}
	
	public List<GameObject> getGameObjects(){
		return this.objects;
	}
	
	public void stopGame() {
		this.clockTimer.cancel();
		this.gameTimer.cancel();
	}
	
	
	
	private void createGameObjects() {
		objects.add(new Pad(windowWidth-5, windowHeight/2-30, 5, 60));
		objects.add(new Ball(windowWidth/2, 40, 40, 40, 60, 60));
		objects.add(new Pad(0, windowHeight/2-30, 5, 60));
	}
	
	private void tick(double delta) {
		for(GameObject obj : objects) {
			if(obj instanceof MovableGameObject) {
				((MovableGameObject)obj).move(delta, windowWidth, windowHeight);
			}
		}
	}	
	
	private void startGameTimer() {
		this.gameTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				tick(UPDATE_DELTA);
			}
		}, 0, UPDATE_INTERVAL);
	}
	
	private void startClock() {
		this.clockTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				time.set(time.get()+1);
			}
		}, 0, 1000);
	}
}
