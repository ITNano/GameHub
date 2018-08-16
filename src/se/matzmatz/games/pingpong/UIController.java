package se.matzmatz.games.pingpong;

import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import se.matzmatz.games.pingpong.model.Ball;
import se.matzmatz.games.pingpong.model.GameObject;
import se.matzmatz.games.pingpong.model.Model;

public class UIController {

	private static final int WIDTH = 800;
	private static final int HEIGHT = 500;

	@FXML
	private Label player1_score;
	
	@FXML
	private Label player2_score;
	
	@FXML
	private Label time;
	
	@FXML
	private Button startButton;
	
	@FXML
	private Canvas gameArea;
	
	private Model model;
	private boolean started = false;
	
	public UIController() {
		model = new Model(WIDTH, HEIGHT);
	}
	
	public void playGame() {
		if(!started) {
			this.started = true;
			this.startButton.setDisable(true);
			// this.time.textProperty().bind(model.clockTime().asString());
			model.startGame();
			new Timer().scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					updateScreen();
				}
			}, 0, 20);
		}
	}
	
	private void updateScreen() {
		gameArea.getGraphicsContext2D().clearRect(0, 0, WIDTH, HEIGHT);
		for(GameObject obj : model.getGameObjects()) {
			UIFactory.drawObject(gameArea.getGraphicsContext2D(), obj);
		}
	}
	
	private String doubleDigit(int num) {
		return (num<10 ? "0"+num : ""+(num%100));
	}
}
