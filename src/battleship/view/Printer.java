package battleship.view;

import battleship.model.Actor;
import battleship.model.Game;
import battleship.model.ShootResult;
import battleship.model.Board;

public class Printer {
   
	public void printGame(Game game) {
		
		System.out.println("                  PLAYER  BOARD\n");
		printBoard(game.getPlayerBoard());
		System.out.println("\n                 CPU BOARD\n\n");
		printBoard(game.getAiBoard());
	}
  
  	public void printBoard(Board board) {
  		String strBoard = board.toString();
  		System.out.println(strBoard);
  	}

  	public void newOperation() {
  	}

  	public void displayResultOfShoot(ShootResult result) {
  		
  		switch(result) {
  			case HIT: {
  				System.out.println(" HIT A BOAT!");
  			}break;
  			case MISS: {
  				System.out.println(" MISSED THE SHOT!");
  			}break;
  			case WIN: {
  				System.out.println(" SUNK ALL THE BOATS! CONGRATULATIONS");
  			}break;
  			case ERROR: {
  				System.out.println(" HAVVE ALREADY SHOT THIS POSITION. TRY AGAIN");
  			};
  		}
  		System.out.println("\n \n");
  		
  	}
  	
  	public void displayTurn(Actor turn) {
  		if (turn == Actor.PLAYER)
  			System.out.print("\nPLAYER ");
  		else 
  			System.out.print("\nCPU ");
  		System.out.print("PLAYS\n");
  	}
  	
  	
}
