package prac2;

import java.io.File;
import java.io.IOException;

import BattleShip.OurScanner;

public class MockOurScanner extends OurScanner {
	public MockOurScanner(File file, boolean record) throws IOException {
		super.sc = new MockRecordingScanner2(file, record);
	}
	
	public void closeInnerWriter() throws IOException {
		super.sc.closeInnerWriter();
	}
}
