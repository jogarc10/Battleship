package battleship.model;

import battleship.util.Vector;
import battleship.util.Army;

public class Board {

  private Cell[][] board;

  public Board(int dimx, int dimy, boolean hasFog) {

  }

  public String toString() {
  return null;
  }

  /** 
   *   Prints the board
   */
  public void print() {

  }

  /** 
   *   
   *    -> If fog is activated in this tile, deactivate it
   *    -> Mark the Tile as corresponds (hit, sunken...)
   */
  public ShootResult markShot(Vector coordinates) {
	return null;

  }

  /** 
   *   
   *    -> "Ship"  and "Army" are classes to format the input from user
   *    -> Update the board with newly placed ships
   */
  public void placeShips(Army ships) {

  }

  public boolean checkWinGame() {
  	boolean livingShips = false;
  	int i = 0;
  	int j = 0;
  	while ((i < DIMX) && !livingShips) {
  		while((j < DIMY) && !livingShips) {
  			if this.board[j][i].tile.equals(Tile.SHIP){
  				livingShips = true;
  			}
  			j++;
  		}
  		i++;
  	}  	

  	return !livingShips;
  }


  /** 
   *   
   *    Getter for private attribute Board
   */
  public Board getBoard() {
	  return this;
  }

	public void setBoard(Cell[][] board) {
		this.board = board;
	}
}