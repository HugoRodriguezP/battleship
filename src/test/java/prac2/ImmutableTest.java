package prac2;

import static org.junit.Assert.assertTrue;

import java.util.Map;

public class ImmutableTest {
	private final Map<String, Object> param;
	
	public ImmutableTest(Map<String, Object> param) {
		this.param = param;
	}
	
	public void testTest() {
		assertTrue(true);
	}
}
