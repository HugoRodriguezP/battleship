package BattleShip;

import java.io.IOException;
import java.util.Scanner;

public interface ScannerInterface {
	
	public String nextLine();
	
	public int nextInt();
	
	public char nextChar();
	
	public int nextInt2();
	
	public char nextChar2();
	
	public void setLine(String name);
	
	public void setInt(int num);
	
	public void setChar(char letter);
	
	public void setInt2(int num);
	
	public void setChar2(char letter);
	
	public void closeInnerWriter() throws IOException;
}
