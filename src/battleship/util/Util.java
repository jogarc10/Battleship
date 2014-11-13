package battleship.util;

import battleship.model.Board;
import battleship.model.Tile;

public class Util {	
	
	
	//Constants of the game
	public static final short SHIP_NUMBER = 10;
	public final static int SIZE_OF_BOARD = 10;
	public static final short DIM_X = SIZE_OF_BOARD;
    public static final short DIM_Y = SIZE_OF_BOARD;
    public final static int SHIP_MAX_DIMENSION = 5;
    public final static int SHIP_MIN_DIMENSION = 2;

	
  /** 
   *   
   *    Return true if all the ships in the baord have been sunk
   */
  static public boolean isInputCorrect(Vector coordinates) {
	  boolean correct = true;

	if ((coordinates.getX() < 0) || (coordinates.getX() >= DIM_X))
	 {
		 correct = false;
	 }
	 if ((coordinates.getY() < 0) || (coordinates.getY() >= DIM_Y))
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

  static public boolean areValidArmyPositions(Vector from, Vector to, Army army) {
	  boolean valid;
	  Board tmpBoard = new Board(10,10,false);
	  tmpBoard.placeShips(army);
	  valid = areValidArmyPositions(from, to, tmpBoard);	  
	  return valid;
  }  
  
  public static boolean validShip(Vector begging, Vector end) { 
		boolean valid = false; 

		 if (Util.isInputCorrect(begging) && Util.isInputCorrect(end)) 
			 valid = true; 
		
		return valid; 
	}
  
  
  
static public boolean CheckArround (int x, int y, Board board) {
	boolean valid = true;
	
	if (x > 0)
	{
		if (board.cellValue(x - 1, y) == Tile.BOAT)
		{
			valid = false;
		}
	}
	if  (x < SIZE_OF_BOARD -1)
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
	if ( y < SIZE_OF_BOARD -1)
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
