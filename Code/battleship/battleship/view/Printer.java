package battleship.view;

import battleship.model.Game;
import battleship.model.ShootResult;
import battleship.model.Board;

public class Printer {
   
	public void printGame(Game game) {
		
		System.out.println("\n                   CPU BOARD\n");
		printBoard(game.getPlayerBoard());
		System.out.println("\n                 PLAYER BOARD\n\n");
		printBoard(game.getAiBoard());
	}
  
  	public void printBoard(Board board) {
  		String strBoard = board.toString();
  		System.out.println(strBoard);
  	}

  	public void newOperation() {
  	}

  	public void displayResultOfShoot(ShootResult result) {
  	}
}
