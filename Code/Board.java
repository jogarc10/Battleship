import java.util.Vector;

public class Board {

  private Cell[][] board;

  public Board(int dimx, int dimy) {
  }

  public String toString() {
  return null;
  }

  /** 
   *  Prints the board
   */
  public void print() {
  }

  /** 
   *  -> If fog is activated in this tile, deactivate it
   *  -> Mark the Tile as corresponds (hit, sunken...)
   */
  public void markShot(Vector coordinates) {
  }

  /** 
   *  -> "Ship"  and "Army" are classes to format the input from user
   *  -> Update the board with newlu placed ships
   */
  public void placeShips(Army ships) {
  }

  /** 
   *  Getter for private attribute Board
   */
  public Board getBoard() {
  return null;
  }
}