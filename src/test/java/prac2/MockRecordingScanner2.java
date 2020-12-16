package prac2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Scanner;

import BattleShip.OurScanner;
import BattleShip.ScannerInterface2;

public class MockRecordingScanner2 implements ScannerInterface2 {
	private Scanner scanner;
	private boolean record;
	private FileWriter fileWriter;
	
	
	public MockRecordingScanner2(File file, boolean record) throws IOException {
		super();
		if (record) {
			this.record = true;
			scanner = new Scanner(System.in);
			fileWriter = new FileWriter(file);
		}
		else {
			this.record = false;
		}
	}

	@Override
	public String nextLine() {
		String line = scanner.nextLine();
		if(record) {
			try {
				fileWriter.write(line.concat(System.lineSeparator()));
			} catch (IOException e) {
				System.exit(-1);
			}
		}
		return line;
	}

	@Override
	public int nextInt() {
		String line = scanner.nextLine();
		line = line.concat(System.lineSeparator());
		if(record) {
			try {
				fileWriter.write(line);
			} catch (IOException e) {
				System.exit(-1);
			}
		}
		return new Scanner(line).nextInt();
	}

	@Override
	public String next() {
		String line = scanner.nextLine();
		line = line.concat(System.lineSeparator());
		if(record) {
			try {
				fileWriter.write(line);
			} catch (IOException e) {
				System.exit(-1);
			}
		}
		return new Scanner(line).next();
	}
	
	public void closeInnerWriter() throws IOException {
		fileWriter.flush();
		fileWriter.close();
	}
}
