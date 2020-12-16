package prac2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import BattleShip.GameController;
import BattleShip.GameView;
import BattleShip.PlayerInterface;
import BattleShip.ScannerInterface;

public class MockGameController extends GameController {
	static final Lock lock = new ReentrantLock();
	static final Condition endOfGame = lock.newCondition();
	
	public MockGameController(PlayerInterface player1, PlayerInterface player2, GameView view,
			ScannerInterface scanner) {
		super(player1, player2, view, scanner);
	}
	@Override
	public void play() {
		super.play();
		try {
			lock.lock();
			endOfGame.signal();
		}
		finally {
			lock.unlock();
		}
	}
}
