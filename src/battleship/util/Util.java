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

	if ((coordinates.getX() < 0) || (coordinates.getX() >= Game.DIM_X))
	 {
		 correct = false;
	 }
	 if ((coordinates.getY() < 0) || (coordinates.getY() >= Game.DIM_Y))
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
  boolean valid;
  String orientation = null;
  valid = true;
  int coordinate = 0;
  
  if (from.getX() == to.getX())
  {
	  if (from.getY() > to.getY())
	  		orientation = "From left to right";
	  else
			orientation = "From right to left";
  }
  else if (from.getY() == to.getY())
  {
	  if (from.getX() < to.getX())
	  		orientation = "From up to down";
	  else
		  orientation = "From down to up";
		  
  }
  
  if (orientation == "From down to up" || orientation == "From up to down")
  {
	  coordinate = from.getX();
	  while ((coordinate < to.getX()) && valid)
	  {
		  valid = CheckArround(coordinate, from.getY(), board, orientation);
		  if (orientation == "From up to down")
			  coordinate--;
		  else
			  coordinate++;
	  }
  }
  else if (orientation == "From right to left" || orientation == "From left to right")
  {
	  coordinate = from.getY();
	  while ((coordinate < to.getY()) && valid)
	  {
		  valid = CheckArround(from.getX(), coordinate, board, orientation);
		  if (orientation == "From left to right")
			  coordinate++;
		  else
			  coordinate--;
	  }
  }

  return valid;
 }

/*static public boolean CheckArround (int x, int y, Board board)
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
}*/
	static public boolean CheckArround (int x, int y, Board board, String orientation) {
		boolean valid = true;
		
		if (board.cellValue(x, y) == Tile.BOAT)
			valid = false;
		else if (orientation == "From left to right") {		
			if (y < 9 && board.cellValue(x, y + 1) == Tile.BOAT)
				valid = false;
			if (x > 0 && board.cellValue(x - 1, y) == Tile.BOAT)
				valid = false;
			if (x < 9 && board.cellValue(x + 1, y) == Tile.BOAT)
				valid = false;
		}
		else if (orientation == "From right to left") {
			if (y > 0 && board.cellValue(x, y - 1) == Tile.BOAT)
					valid = false;
			if (x > 0 && board.cellValue(x - 1, y) == Tile.BOAT)
				valid = false;
			if (x < 9 && board.cellValue(x + 1, y) == Tile.BOAT)
				valid = false;
		}
		else if (orientation == "From up to down") {
			if (y > 0 && board.cellValue(x, y - 1) == Tile.BOAT)
				valid = false;
			if (y < 9 && board.cellValue(x, y + 1) == Tile.BOAT)
				valid = false;
			if (x < 9 && board.cellValue(x + 1, y) == Tile.BOAT)
				valid = false;
		}
		else if (orientation == "From down to up") {
			if (y > 0 && board.cellValue(x, y - 1) == Tile.BOAT)
				valid = false;
			if (y < 9 && board.cellValue(x, y + 1) == Tile.BOAT)
				valid = false;
			if (x > 0 && board.cellValue(x - 1, y) == Tile.BOAT)
				valid = false;
		}
		
		return valid;
	}
}
