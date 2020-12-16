package prac2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import BattleShip.Board;
import BattleShip.GameController;
import BattleShip.GameView;
import BattleShip.Player;
import BattleShip.PlayerInterface;
import BattleShip.ScannerInterface;
import BattleShip.Square;

public class GamesPlayer {
	private static final String PATH = "./data/"; // Should end with a '/' or '\'.
	   							// Accept multiple sos path formats.
	private final static List<String> FILENAMES = 
			Arrays.asList(new String[] {"game1.txt"});
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		for(String filename: FILENAMES) {
			File file = new File(PATH.concat(filename));
			if(!file.exists()) {
				throw new FileNotFoundException();
			}
			if(file.exists() && !file.canRead()) {
				throw new IOException();
			}
			GameRecorder.createGame(new MockOurScanner(file));
		}
	}
}
