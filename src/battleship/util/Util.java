package battleship.util;

import battleship.model.Board;
import battleship.model.Game;
import battleship.model.Tile;

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
  static public boolean areValidArmyPositions(Vector from, Vector to, Board board) {
  boolean vertical, horizontal, valid;
  vertical = horizontal = false;
  valid = true;
  int coordinate = 0;
  
  if (from.getX() == to.getX())
  {
	  horizontal = true;
  }
  else if (from.getY() == to.getY())
  {
	  vertical = true;
  }
  
  if (vertical)
  {
	  coordinate = from.getX();
	  while ((coordinate <= to.getX()) && valid)
	  {
		  valid = CheckArround(coordinate, from.getY(), board);
		  coordinate++;
	  }
  }
  else if (horizontal)
  {
	  coordinate = from.getY();
	  while ((coordinate <= to.getY()) && valid )
	  {
		  valid = CheckArround(from.getX(), coordinate, board);
		  coordinate++;
	  }
  }

  return valid;
 }

static public boolean CheckArround (int x, int y, Board board)
{
	boolean valid = true;
	
	if (x > 0)
	{
		if (board.cellValue(x - 1, y) == Tile.BOAT)
		{
			valid = false;
		}
	}
	if  (x < 9)
	{
		if (board.cellValue(x + 1 , y) == Tile.BOAT)
		{
			valid = false;
		}
	}
	if (y > 0)
	{
		if (board.cellValue(x , y - 1) == Tile.BOAT)
		{
			valid = false;
		}
	}
	if ( y < 9)
	{
		if (board.cellValue(x , y + 1) == Tile.BOAT)
		{
			valid = false;
		}
	}
	if (board.cellValue(x , y ) == Tile.BOAT)
	{
		valid = false;
	}
	
	return valid;
}
}
