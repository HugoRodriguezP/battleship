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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class AutomaticGamesTest {
	private final static String PATH = "./data/"; // Should end with a '/' or '\'.
		// Accept multiple sos path formats.
	private final static String[] GAMES_FILENAMES = new String[] {"game1.txt"};
	private final static String PARAMS_FILENAME = "games-parameters.csv";
	static public int N = 7;
	private static List<GameController> games;
	private static BufferedReader paramsBufferedReader;
	static final Lock lock = new ReentrantLock();
	static final Condition testsFinshed = lock.newCondition();
	private static Board player1Board;
	private static Board player2Board;
	
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
		games = new Vector<>();
		for(String filename: GAMES_FILENAMES) {
			File file = new File(PATH.concat(filename));
			if(!file.exists()) {
				throw new FileNotFoundException();
			}
			if(file.exists() && !file.canRead()) {
				throw new IOException();
			}
			games.add(createGame(new MockOurScanner(file)));
		}
	}
	
	public void testTest() {
		assertTrue(false);
	}
	
	@Test
	public void runGame() throws InterruptedException {
		for(GameController game: games) {
			game.play();
			try {
				lock.lock();
				testsFinshed.wait();
			}
			finally {
				lock.unlock();
			}
		}
		assertTrue(true);
	}
	private String[] filterLine(BufferedReader bufferedReader, 
			                        boolean endOfFile) throws IOException {
		String[] treathedLine = new String[N];
		String line;
		if((line = bufferedReader.readLine()) != null) {
			int i2 = -1;
			for(int i1 = 0; i1 < N - 1; i1++) {
				i2 = line.indexOf(";", i2 + 1);
				treathedLine[i1] = line.substring(0, i2);
			}
			treathedLine[N - 1] = line.substring(i2);
			endOfFile = false;
			return treathedLine;
		}
		else {
			endOfFile = true;
			return null;
		}
	}
	
	public class TestSuite1 {
		@Test
		public void boardEndOfGameTest() throws InterruptedException, IOException {
			boolean endOfFile;
			int i = 0;
			for(GameController game: games) {
				try {
					MockGameController.lock.lock();
					MockGameController.endOfGame.await();
				}
				finally {
					MockGameController.lock.unlock();
				}
				endOfFile = false;
				String[] param;
				while(true) {
					param = filterLine(paramsBufferedReader, endOfFile);
					if(param[0] != GAMES_FILENAMES[i] || endOfFile) {
						break;
					}
					boolean checkPlayer1Board = Boolean.getBoolean(param[1]);
					Board board;
					if(checkPlayer1Board) {
						board = player1Board;
					}
					else {
						board = player2Board;
					}
					char letter = param[2].charAt(0);
					int number = Integer.getInteger(param[3]);
					int iBoard = board.getPosition(new Square(letter, number));
					Square square = board.getBoard().get(iBoard);
					boolean occupied = Boolean.getBoolean(param[4]);
					boolean visited = Boolean.getBoolean(param[5]);
					boolean touched = Boolean.getBoolean(param[6]);
					assertEquals(occupied, square.getOccupied());
					assertEquals(visited, square.getVisited());
					assertEquals(touched, square.getTouched());
				}
				i++;
				try {
					lock.lock();
					testsFinshed.signal();
				}
				finally {
					lock.unlock();
				}
			}
		}
	}
}
