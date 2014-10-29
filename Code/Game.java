import java.util.Vector;

public class Game {

  private Actor winner;

  private bool isFinished;

  private Board aiBoard;

  private Board playerBoard;

  private Actor turn;

  private ShootResult result;

  public Game() {
  }

  /** 
   *  Calls methods in the subclasses of Board to print them
   */
  public void printGame() {
  }

  /** 
   *  -> Verifies the correctness of the locations (Util)
   *  -> Places the Ships in the corresponding board (user this.turn)
   */
  public void placeShips(Army ships, Actor actor) {
  }

  /** 
   *  Executes the shoot and returns the result
   */
  public ShootResult shoot(Vector shot) {
  return null;
  }
}