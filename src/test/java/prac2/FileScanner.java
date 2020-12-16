package prac2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import BattleShip.ScannerInterface2;

public class FileScanner implements ScannerInterface2 {
	private Scanner scanner;
	
	public FileScanner(File file) throws FileNotFoundException {
		scanner = new Scanner(file);
	}

	@Override
	public String nextLine() {
		return scanner.nextLine();
	}

	@Override
	public int nextInt() {
		return scanner.nextInt();
	}

	@Override
	public String next() {
		return scanner.next();
	}

	@Override
	public void closeInnerWriter() throws IOException {
		
	}
}
