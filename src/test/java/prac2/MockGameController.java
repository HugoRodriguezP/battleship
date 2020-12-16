package prac2;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

import BattleShip.GameController;
import BattleShip.GameView;
import BattleShip.PlayerInterface;
import BattleShip.ScannerInterface;

public class MockGameController extends GameController {
	public MockGameController(PlayerInterface player1, PlayerInterface player2, GameView view,
			ScannerInterface scanner) {
		super(player1, player2, view, scanner);
	}
	@Override
	public void play() {
		super.play();
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));
		junit.run(TestClass1.class);
	}
}
