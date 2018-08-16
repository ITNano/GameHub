package se.matzmatz.games.pingpong;

import javafx.scene.canvas.GraphicsContext;
import se.matzmatz.games.pingpong.model.Ball;
import se.matzmatz.games.pingpong.model.GameObject;
import se.matzmatz.games.pingpong.model.Pad;

public class UIFactory {

	public static void drawObject(GraphicsContext context, GameObject obj) {
		if(obj instanceof Ball) {
			drawBall(context, (Ball)obj);
		}else if(obj instanceof Pad) {
			drawPad(context, (Pad)obj);
		}
	}
	
	private static void drawBall(GraphicsContext context, Ball b) {
		context.fillOval(b.x().get(), b.y().get(), b.width().get(), b.height().get());
	}
	
	private static void drawPad(GraphicsContext context, Pad pad) {
		context.fillRect(pad.x().get(), pad.y().get(), pad.width().get(), pad.height().get());
	}

	
}
