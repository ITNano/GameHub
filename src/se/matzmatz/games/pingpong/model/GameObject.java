package se.matzmatz.games.pingpong.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class GameObject {
	
	private DoubleProperty x, y, width, height;
	
	protected GameObject(double x, double y, double width, double height) {
		this.x = new SimpleDoubleProperty(x);
		this.y = new SimpleDoubleProperty(y);
		this.width = new SimpleDoubleProperty(width);
		this.height = new SimpleDoubleProperty(height);
	}

	public DoubleProperty x() { return x; }
	public DoubleProperty y() { return y; }
	public DoubleProperty width() { return width; }
	public DoubleProperty height() { return height; }
	
	public HitBox getHitBox() {
		return new HitBox(x.get(), y.get(), width.get(), height.get());
	}
	
	
	public class HitBox {
		private double x, y, width, height;
		public HitBox(double x, double y, double width, double height) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}
		
		public double getX() { return this.x; }
		public double getY() { return this.y; }
		public double getWidth() { return this.width; }
		public double getHeight() { return this.height; }
		
		public int getOverlaps(HitBox box) {
			boolean xMatches = (x < box.x && x+width > box.x) || (x > box.x && x < box.x+box.width) || (this.x == box.x);
			boolean yMatches = (y < box.y && y+height > box.y) || (y > box.y && y < box.y+box.height) || (this.y == box.y); 
			if(xMatches && yMatches) {
				return 3;
			}
			return 0;
		}
	}
}
