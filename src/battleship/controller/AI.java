package battleship.controller;

import java.util.Random;
import battleship.util.Army;
import battleship.util.Util;
import battleship.util.Vector;

public class AI {
		
	public static Vector aIShoot() { //Very rudimentary function just in order to have something for the first merge
		Random randomGenerator = new Random();
		Vector vector = new Vector (randomGenerator.nextInt(Util.SIZE_OF_BOARD), randomGenerator.nextInt(Util.SIZE_OF_BOARD));
		
		return vector;
	}
	
	public static Army generateShips() {
		Army armyOfShips = new Army(Util.SHIP_NUMBER);
		Integer boatNumber = new Integer(1);
		Integer boatDimension = new Integer(Util.SHIP_MAX_DIMENSION);
		int numberOfDimY = 1;
	    int y = 0;
		  		  
		  while(boatDimension >= Util.SHIP_MIN_DIMENSION){
			 generateShip(armyOfShips,boatDimension);
			 y++;
			 boatNumber--;
				  if (y == numberOfDimY)
				  {
					  boatDimension--;
					  numberOfDimY++;
					  y = 0;
					  boatNumber = numberOfDimY;
				  }
			  }		
		return armyOfShips;
	}
		  
	  private static void generateShip(Army armyOfShips, int boatDimension) {
		  Random randomGenerator = new Random();
		  Vector initialVector = new Vector(0, 0), finalVector = new Vector(0, 0);
		  Boolean valid = false;
		  int direction;
		  int randomX;
		  int randomY;
		  while (!valid) {
			  valid = true;
			  randomX = randomGenerator.nextInt(Util.SIZE_OF_BOARD);
			  randomY = randomGenerator.nextInt(Util.SIZE_OF_BOARD);
			  initialVector.setX(randomX);
			  initialVector.setY(randomY);
			  direction = randomGenerator.nextInt(2); //0 = Horizontal; 1 = Vertical
			  if (direction == 1) { //Vertical
				  finalVector.setX(randomX + boatDimension-1);
				  finalVector.setY(randomY);
			  }
			  else {//Horizontal
				  finalVector.setX(randomX);
				  finalVector.setY(randomY + boatDimension-1);
			  }
			  if (Util.validShip(initialVector, finalVector))
				  valid = Util.areValidArmyPositions(initialVector,finalVector,armyOfShips);
			  else
				  valid = false;
			  
			 }  		 
		  armyOfShips.appendShip(finalVector,initialVector);	  
	  }
	
	/*public static Army generateShips() {
		Army armyOfShips = new Army(Util.SIZE_OF_BOARD);
		Vector initialVector = new Vector(0, 0), finalVector = new Vector(0, 0);
		Random randomGenerator = new Random();
    
	    for (int i = 0; i < NUMBER_OF_DIFERENT_SHIPS; ++i) { //Look at this motherfucking beautiful code
			    for (int j = 0; j < i + 1; ++j) { //Seriously it's fucking beautiful
			    	do { //I fricikng love do while loops
			    		randomGenerator = new Random();
			    		initialVector = new Vector(0,0);
			    		initialVector.setX(randomGenerator.nextInt(Util.SIZE_OF_BOARD)); //It's so fucking beautiful I wanna jerk to it
			    		initialVector.setY(randomGenerator.nextInt(Util.SIZE_OF_BOARD)); //And you know you might
			    		finalVector = newRandomEndOfShip(initialVector, NUMBER_OF_DIFERENT_SHIPS + 1 - i);
			    	} while (!validShip(initialVector, finalVector)); //But hey I don't judge
			    	armyOfShips.appendShip(initialVector, finalVector); //Now you're gay for my code
			    } //PS: If you don't get this reference watch Silicon Valley, it's fucking great, also Pulp Fiction
		    }//Your so cool code doesn't work properly :). Fdo: Fran
		    return armyOfShips;
	  }
  
	private static Vector newRandomEndOfShip(Vector initialShipVector, int length) {
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
	*/
  
	  
	 
}
