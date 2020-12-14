package prac2;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class TestReader {
	static private String readLineNoEOF(BufferedReader bufferedReader) throws IOException {
		String line;
		if((line = bufferedReader.readLine()) != null) {
			return line;
		}
		else {
			throw new IOException("Invalid file format.");
		}
	}
	static public List<Object> getFilemapFromFile(BufferedReader bufferedReader) 
			                                  throws IOException {
		List<Object> games = new Vector<>();
		String line;
		while((line = bufferedReader.readLine()) != null) {
			Map<String, Object> game = new HashMap<>();
			readLineNoEOF(bufferedReader);
			game.put("name1", readPlayerName(bufferedReader));
			game.put("name2", readPlayerName(bufferedReader));
			for(int i = 0; i < AutomaticGamesTest.M; i++) {
				readLineNoEOF(bufferedReader);
			}
			Map<String, Object> ships = readShipsPositions(bufferedReader);
			game.put("ships1", ships);
			for(int i = 0; i < AutomaticGamesTest.M; i++) {
				readLineNoEOF(bufferedReader);
			}
			ships = readShipsPositions(bufferedReader);
			game.put("ships2", ships);
			List<Object> plays = readPlays(bufferedReader);
			game.put("plays1", plays);
			plays = readPlays(bufferedReader);
			game.put("plays2", plays);
		}
		return games;
	}
	static private String readPlayerName(BufferedReader bufferedReader)
			                             throws IOException {
		String line = readLineNoEOF(bufferedReader);
		return line.substring(0, line.indexOf(";"));
	}
	static private Map<String, Object> readShipPositions(BufferedReader bufferedReader) 
			throws IOException {
		Map<String, Object> ship = new HashMap<>();
		String line = readLineNoEOF(bufferedReader);
		String field = line.substring(0, line.indexOf(";"));
		ship.put("initPos.letter", field);
		field = line.substring(0, line.indexOf(";"));
		ship.put("initPos.number", field);
		field = line.substring(0, line.indexOf(";"));
		ship.put("finPos.letter", field);
		field = line.substring(0, line.indexOf(";"));
		ship.put("finPos.number", field);
		return ship;
	}
	static private Map<String,Object> readShipsPositions(BufferedReader bufferedReader) 
			                 throws IOException {
		Map<String, Object> ships = new HashMap<>();
		ships.put("aircraftCarrier", readShipPositions(bufferedReader));
		ships.put("destroyer", readShipPositions(bufferedReader));
		ships.put("submarine", readShipPositions(bufferedReader));
		ships.put("landingCraft", readShipPositions(bufferedReader));
		return ships;
	}
	static private List<Object> readPlays(BufferedReader bufferedReader) throws IOException {
		List<Object> plays = new Vector();
		readLineNoEOF(bufferedReader);
		String line, field;
		Map<String, String> coordinate; 
		while(true) {
			line = readLineNoEOF(bufferedReader);
			field = line.substring(0, line.indexOf(";"));
			if(field.endsWith("</plays>")) {
				break;
			}
			coordinate = new HashMap<>();
			coordinate.put("letter", field);
			field = line.substring(0, line.indexOf(";"));
			coordinate.put("number", field);
			plays.add(coordinate);
		}
		return plays;
	}
}
