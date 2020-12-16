package prac2;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.Test;

import BattleShip.Board;
import BattleShip.Square;

public class TestClass1 {
	static public int N = 7;
	
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
	@Test
	public void boardEndOfGameTest() throws InterruptedException, IOException {
		boolean endOfFile;
		int i = 0;
			endOfFile = false;
			String[] param;
			while(true) {
				param = filterLine(AutomaticGamesTest.paramsBufferedReader, endOfFile);
				if(param[0] != AutomaticGamesTest.GAMES_FILENAMES[i] || endOfFile) {
					break;
				}
				boolean checkPlayer1Board = Boolean.getBoolean(param[1]);
				Board board;
				if(checkPlayer1Board) {
					board = AutomaticGamesTest.player1Board;
				}
				else {
					board = AutomaticGamesTest.player2Board;
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
	}
}
