package se.matzmatz.games.pingpong.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class MovableGameObject extends GameObject {
	
	private DoubleProperty speedX, speedY;
	
	protected MovableGameObject(double x, double y, double width, double height, double speedX, double speedY) {
		super(x, y, width, height);
		this.speedX = new SimpleDoubleProperty(speedX);
		this.speedY = new SimpleDoubleProperty(speedY);
	}
	
	public DoubleProperty speedX() {
		return speedX;
	}
	
	public DoubleProperty speedY() {
		return speedY;
	}

	public void move(double timeDelta, int maxWidth, int maxHeight) {
		x().set(x().get()+speedX.get()*timeDelta);
		y().set(y().get()+speedY.get()*timeDelta);
		if(x().get() <= 0 || x().get()+width().get() >= maxWidth) {
			speedX.set(-speedX.get());;
		}
		if(y().get() < 0 || y().get()+height().get() >= maxHeight) {
			speedY.set(-speedY.get());
		}
	}

}
