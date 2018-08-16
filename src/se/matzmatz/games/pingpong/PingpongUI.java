package se.matzmatz.games.pingpong;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.matzmatz.games.Game;

public class PingpongUI extends Application implements Game{
	
	@Override
	public void playGame(String... args) {
		PingpongUI.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("pingpong.fxml"));
		Scene scene = new Scene(root, 800, 600);
		
		stage.setTitle("Pingpong Game");
		stage.setScene(scene);
		stage.show();
	}

}
