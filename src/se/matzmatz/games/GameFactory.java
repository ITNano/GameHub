package se.matzmatz.games;

import se.matzmatz.games.pingpong.PingpongUI;

public class GameFactory {
	
	public static Game getRandomGame() {
		// Well, we only have one game so far
		return getGame("pingpong");
	}

	public static Game getGame(String name) {
		switch(name) {
			case "pingpong": return new PingpongUI();
			default: 		 return null;
		}
	}
	
}
