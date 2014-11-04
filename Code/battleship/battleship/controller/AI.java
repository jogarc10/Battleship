package battleship.controller;

import java.util.Random;

import battleship.util.Army;
import battleship.util.Util;
import battleship.util.Vector;

public class AI {
	final int NUMBER_OF_DIFERENT_SHIPS = 4;
	final int SIZE_OF_BOARD = 10;
	
	public Vector aIShoot() { //Very rudimentary function just in order to have something for the first merge
		Random randomGenerator = new Random();
		Vector vector = new Vector (randomGenerator.nextInt(SIZE_OF_BOARD), randomGenerator.nextInt(SIZE_OF_BOARD));
		
		return vector;
	}
	
	public Army generateShips() {
		Army armyOfShips = new Army(SIZE_OF_BOARD);
		Vector initialVector = new Vector(0, 0), finalVector = new Vector(0, 0);
		Random randomGenerator = new Random();
    
	    for (int i = 0; i < NUMBER_OF_DIFERENT_SHIPS; ++i) { //Look at this motherfucking beautiful code
			    for (int j = 0; j < i + 1; ++j) { //Seriously it's fucking beautiful
			    	do { //I fricikng love do while loops
			    		initialVector.setX(randomGenerator.nextInt(SIZE_OF_BOARD)); //It's so fucking beautiful I wanna jerk to it
			    		initialVector.setY(randomGenerator.nextInt(SIZE_OF_BOARD)); //And you know you might
			    		finalVector = newRandomEndOfShip(initialVector, NUMBER_OF_DIFERENT_SHIPS + 1 - i);
			    	} while (!validShip(initialVector, finalVector)); //But hey I don't judge
			    	armyOfShips.setShipInPosition(j, initialVector, finalVector); //Now you're gay for my code
			    } //PS: If you don't get this reference watch Silicon Valley, it's fucking great, also Pulp Fiction
		    }
		    return armyOfShips;
	  }
  
	private Vector newRandomEndOfShip(Vector initialShipVector, int length) {
		Vector finalShipVector = new Vector(initialShipVector.getX(), initialShipVector.getY());
		Random randomGenerator = new Random();
		int i =  randomGenerator.nextInt(4);
		
		if (i == 0) //Ship will go down
			finalShipVector.setX(finalShipVector.getX() + length);
		else if (i == 1) //Ship will go up
			finalShipVector.setX(finalShipVector.getX() - length);
		else if (i == 2) //Ship will go left
			finalShipVector.setY(finalShipVector.getY() + length);
		else if (i == 3) //Ship will go right
			finalShipVector.setY(finalShipVector.getY() - length);
		  
		return finalShipVector;
	}
  
	private boolean validShip(Vector begging, Vector end) { //This function could probably go into util
		boolean valid = false; //I know it's awesome

		 if (Util.isInputCorrect(begging) && Util.isInputCorrect(end)) //Simple yet powerful
			 valid = true; //Elegant yet it does the job
		
		return valid; //Don't be mad Borja
	}
}
