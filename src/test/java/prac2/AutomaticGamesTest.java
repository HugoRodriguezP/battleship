/*
 * Games File Format
 * 
 */
package prac2;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import BattleShip.Board;
import BattleShip.GameController;
import BattleShip.GameView;
import BattleShip.MockScanner;
import BattleShip.OurScanner;
import BattleShip.Player;
import BattleShip.PlayerInterface;
import BattleShip.ScannerInterface;
import BattleShip.Square;


public class AutomaticGamesTest {
	private final String PATH = "../../data/"; // Should end with a '/' or '\'.
								   // Accept multiple sos path formats.
	private final String FILENAME = "games.csv";
	static public int M = 6; // N has the same value.
	private List<Object> filemap;

	/*
	public void loadAllTests() {
		testTest = DynamicTest.dynamicTest("a", () -> assertTrue(true));
		testTest = DynamicTest.dynamicTest("a", AutomaticGamesTest::testTest);
	}
	*/
	public void createGame(ScannerInterface scanner) {
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
		
		PlayerInterface player1 = new Player("", board1);
		PlayerInterface player2 = new Player("", board2);
		
		GameView view = new GameView();
		
		GameController game = new GameController(player1, player2, view, scanner);
		
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
		
		game.play();
	}
	
	@BeforeAll
	public void setup() throws FileNotFoundException, IOException {
		File file = new File(PATH.concat(FILENAME));
		if(!file.exists() || (file.exists() && !file.canRead())) {
			throw new FileNotFoundException();
		}
		BufferedReader br = new BufferedReader(new FileReader(file));
		filemap = TestReader.getFilemapFromFile(br);
	}
	
	@TestFactory
	public Collection<DynamicNode> allGamesTest() {
		List<DynamicNode> gamesCollection = new Vector<>();
		ScannerInterface scanner;
		for(int i = 0; i < filemap.size(); i++) {
			scanner = new MockScanner();
			createGame(scanner);
		}
	}
}
