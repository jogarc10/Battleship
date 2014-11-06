package battleship.util;

import battleship.model.Board;
import battleship.model.Game;

public class Util {
	
	private static Game game;
  /** 
   *   
   *    Return true if all the ships in the baord have been sunk
   */
  static public boolean isInputCorrect(Vector coordinates) {
	  boolean correct = true;

	if ((coordinates.getX() < 0) || (coordinates.getX() > game.DIM_X))
	 {
		 correct = false;
	 }
	 if ((coordinates.getY() < 0) || (coordinates.getY() > game.DIM_Y))
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
