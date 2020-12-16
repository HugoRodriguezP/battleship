package BattleShip;

import java.io.IOException;
import java.util.Scanner;

public class OurScanner2 implements ScannerInterface2 {
	private Scanner scanner;
	
	public OurScanner2() {
		scanner = new Scanner(System.in);
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
