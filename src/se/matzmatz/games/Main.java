package se.matzmatz.games;

public class Main {

	public static void main(String[] args) {
		if(ProgramUpdater.checkForUpdates()) {
			ProgramUpdater.doUpdate();
		}
		
		GameFactory.getRandomGame().playGame();
	}
	
}
