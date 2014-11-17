package battleship.view;

import battleship.model.Actor;
import battleship.model.Game;
import battleship.model.ShootResult;
import battleship.model.Board;

public class Printer {
   
	public void printGame(Game game) {
		
		System.out.println("\n\n\n                  PLAYER  BOARD\n");
		printBoard(game.getPlayerBoard(), Actor.PLAYER);
		System.out.println("\n                 CPU BOARD\n");
		printBoard(game.getAiBoard(), Actor.AI);
	}
  
  	public void printBoard(Board board, Actor turn) {
  		String strBoard = board.toString(turn);
  		System.out.println(strBoard);
  	}

  	public void displayResultOfShoot(ShootResult result) {
  		
  		switch(result) {
  			case HIT: {
	  			System.out.println("\n\n   __   __  ___   _______  __  ");
	  			System.out.println("  |  | |  ||   | |       ||  |  ");	
	  			System.out.println("  |  | |  ||   | |       ||  | ");
	  			System.out.println("  |       ||   |   |   |  |  | ");
	  			System.out.println("  |       ||   |   |   |  |__| ");
	  			System.out.println("  |   _   ||   |   |   |   __  ");
	  			System.out.println("  |__| |__||___|   |___|  |__| \n");
  			}break;
  			case MISS: {

  			//
  			//
  			//
  			//
  			//
  			//

  				System.out.println("\n\n   __   __  ___   _______  _______ ");
	  			System.out.println("  |  |_|  ||   | |       ||       |");	
	  			System.out.println("  |       ||   | |  _____||  _____|");
	  			System.out.println("  |       ||   | | |_____ | |_____ ");
	  			System.out.println("  |       ||   | |_____  ||_____  |");
	  			System.out.println("  | ||_|| ||   |  _____| | _____| |");
	  			System.out.println("  |_|   |_||___| |_______||_______|\n");
  			}break;
  			case WIN: {
  				System.out.println(" SUNK ALL THE BOATS! CONGRATULATIONS");
  			}break;
  			case ERROR: {
  				System.out.println(" YOU HAVE ALREADY SHOT THIS POSITION. TRY AGAIN");
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
  	
  	
public static void displayTitle() {
		System.out.println ("\n\n\n  888888b.            888    888    888                   888      d8b          ");
		System.out.println ("  888  `88b           888    888    888                   888      Y8P          ");
		System.out.println ("  888  .88P           888    888    888                   888                   ");
		System.out.println ("  8888888K.   8888b.  888888 888888 888  .d88b.  .d8888b  88888b.  888 88888b.  ");
		System.out.println ("  888    888 .d888888 888    888    888 88888888 ´Y8888b. 888  888 888 888  888 ");
		System.out.println ("  888   d88P 888  888 Y88b.  Y88b.  888 Y8b.          X88 888  888 888 888 d88P ");
		System.out.println ("  8888888P`  `Y888888  `Y888  `Y888 888  `Y8888   88888P` 888  888 888 88888P`  ");
		System.out.println ("                                                                       888      ");
		System.out.println ("                                                                       888      ");
		System.out.println ("                                                                       888      \n");
		System.out.println ("                                                             Developed by iTeam\n\n\n");
		
  	}  	
}
