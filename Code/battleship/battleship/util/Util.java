package battleship.util;

import battleship.model.Game;

public class Util {	
	
  /** 
   *   
   *    Return true if all the ships in the baord have been sunk
   */
  static public boolean isInputCorrect(Vector coordinates) {
	  boolean correct = true;

	if ((coordinates.getX() < 0) || (coordinates.getX() > Game.DIM_X))
	 {
		 correct = false;
	 }
	 if ((coordinates.getY() < 0) || (coordinates.getY() > Game.DIM_Y))
	 {
		 correct = false;
	 }
	 return correct;
  }

  /** 
   *   
   *    Returns true if the placement of the Army is correct
   */
  static public boolean areValidArmyPositions(Army army) {
  /* {src_modifiers=static}*/

  return false;
  }
}
