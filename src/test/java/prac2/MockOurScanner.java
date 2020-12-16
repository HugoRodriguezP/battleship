package prac2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import BattleShip.OurScanner;

public class MockOurScanner extends OurScanner {
	public MockOurScanner(File file, boolean record) throws IOException {
		super.sc = new RecordingScanner(file, record);
	}
	public MockOurScanner(File file) throws FileNotFoundException {
		super.sc = new FileScanner(file);
	}
	public void closeInnerWriter() throws IOException {
		super.sc.closeInnerWriter();
	}
}
