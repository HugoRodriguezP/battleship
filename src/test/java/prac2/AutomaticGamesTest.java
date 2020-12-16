/*
 * Games File Format
 * 
 */
package prac2;

import BattleShip.Board;
import BattleShip.GameController;
import BattleShip.GameView;
import BattleShip.Player;
import BattleShip.PlayerInterface;
import BattleShip.ScannerInterface;
import BattleShip.Square;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class AutomaticGamesTest {
	private final static String PATH = "./data/"; // Should end with a '/' or '\'.
		// Accept multiple sos path formats.
	public final static String[] GAMES_FILENAMES = new String[] {"game1.txt"};
	public final static String PARAMS_FILENAME = "games-parameters.csv";
	public static BufferedReader paramsBufferedReader;
	static final Lock lock = new ReentrantLock();
	static final Condition testsFinshed = lock.newCondition();
	public static Board player1Board;
	public static Board player2Board;
	
	private static GameController game;
	private static int i;
	
	public static GameController createGame(ScannerInterface scanner) {
		ArrayList<Square> squares1 = new ArrayList<Square>();
		ArrayList<Square> squares2 = new ArrayList<Square>();
		
		for(int i = 1; i < 7; i++) {
			Square square1 = new Square('A',i);
			Square square2 = new Square('B',i);
			Square square3 = new Square('C',i);
			Square square4 = new Square('D',i);
			Square square5 = new Square('E',i);
			Square square6 = new Square('F',i);
			squares1.add(square1);
			squares1.add(square2);
			squares1.add(square3);
			squares1.add(square4);
			squares1.add(square5);
			squares1.add(square6);
		}
		
		for(int i = 1; i < 7; i++) {
			Square square1 = new Square('A',i);
			Square square2 = new Square('B',i);
			Square square3 = new Square('C',i);
			Square square4 = new Square('D',i);
			Square square5 = new Square('E',i);
			Square square6 = new Square('F',i);
			squares2.add(square1);
			squares2.add(square2);
			squares2.add(square3);
			squares2.add(square4);
			squares2.add(square5);
			squares2.add(square6);
		}
		
		Board board1 = new Board(squares1);
		Board board2 = new Board(squares2);
		
		player1Board = board1;
		player2Board = board2;
		
		PlayerInterface player1 = new Player("", board1);
		PlayerInterface player2 = new Player("", board2);
		
		GameView view = new GameView();
		
		GameController game = new MockGameController(player1, player2, view, scanner);
		
		game.setDataPlayerName(player1);
		game.setDataPlayerName(player2);
		
		game.setDataPlayerAirCraftCarrier(player1);
		game.setDataPlayerDestroyer(player1);
		game.setDataPlayerSubmarine(player1);
		game.setDataPlayerLandingCraft(player1);
		
		game.setDataPlayerAirCraftCarrier(player2);
		game.setDataPlayerDestroyer(player2);
		game.setDataPlayerSubmarine(player2);
		game.setDataPlayerLandingCraft(player2);
		
		return game;
	}
	
	@BeforeAll
	public static void setup() throws FileNotFoundException, IOException {
		File paramsFile = new File(PATH.concat(PARAMS_FILENAME));
		if(!paramsFile.exists()) {
			throw new FileNotFoundException();
		}
		if(paramsFile.exists() && !paramsFile.canRead()) {
			throw new IOException();
		}
		paramsBufferedReader = new BufferedReader(new FileReader(paramsFile));
		AutomaticGamesTest.i = 0;
		for(String filename: GAMES_FILENAMES) {
			File file = new File(PATH.concat(filename));
			if(!file.exists()) {
				throw new FileNotFoundException();
			}
			if(file.exists() && !file.canRead()) {
				throw new IOException();
			}
			AutomaticGamesTest.game = createGame(new MockOurScanner(file));
			game.play();
			AutomaticGamesTest.i++;
		}
	}
	@Test 
	void testTest() {
		assertTrue(true);
	}
}
