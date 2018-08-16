package se.matzmatz.games.pingpong;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class UIController {

	@FXML
	private Label player1_score;
	
	@FXML
	private Label player2_score;
	
	@FXML
	private Label time;
	
	@FXML
	private Button startButton;
	
	private Model model;
	private boolean started = false;
	
	public UIController() {
		model = new Model();
	}
	
	public void playGame() {
		if(!started) {
			this.started = true;
			this.startButton.setDisable(true);
			bindToTime(time);
			model.startGame();
		}
	}
	
	
	private void bindToTime(Label label) {
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent actionEvent) {
						int time = model.getClockTime();
						label.setText(doubleDigit(time/60)+":"+doubleDigit(time%60));
					}
				}
						),
				new KeyFrame(Duration.seconds(1))
				);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
	
	private String doubleDigit(int num) {
		return (num<10 ? "0"+num : ""+(num%100));
	}
}
