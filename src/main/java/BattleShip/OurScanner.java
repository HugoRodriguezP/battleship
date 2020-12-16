package BattleShip;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OurScanner implements ScannerInterface{

	protected ScannerInterface2 sc;
	
	public OurScanner(){
		this.sc = new OurScanner2();
	}
	
	public String nextLine() {
		String name = this.sc.nextLine();
		return name;
	}
	
	public int nextInt() {
		int num = 0;
		do {			
			  try {
			    num = sc.nextInt();
			  } catch (InputMismatchException ime){
				  System.out.println("Error. Try again.");
				  sc.next();
			  }
			} while (num==0);
		return num;
	}
	
	public char nextChar() {
		return this.sc.next().charAt(0);
	}
	
	public int nextInt2() {
		return this.sc.nextInt();
	}
	
	public char nextChar2() {
		return this.sc.next().charAt(0);
	}

	public void setLine(String name) {
		// TODO Auto-generated method stub	
	}

	public void setInt(int num) {
		// TODO Auto-generated method stub	
	}

	public void setChar(char letter) {
		// TODO Auto-generated method stub	
	}


	public void setInt2(int num) {
		// TODO Auto-generated method stub	
	}


	public void setChar2(char letter) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void closeInnerWriter() throws IOException {
		sc.closeInnerWriter();
	}
	
}
