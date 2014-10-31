package battleship.model;

import battleship.util.Vector;
import battleship.util.Army;

public class Game {

  public static final short DIM_X = 10;
  /* {src_modifiers=static}*/

  public static final short DIM_Y = 10;
  /* {src_modifiers=static}*/

  private boolean isFinished;

  private Actor winner;

  private Board aiBoard;

  private Board playerBoard;

  private Actor turn;

  private ShootResult result;
  

  public Game() {
	  this.winner = null;
	  this.isFinished = false;
	  this.aiBoard =  new Board (DIM_X,DIM_Y, true);
	  this.playerBoard =  new Board (DIM_X,DIM_Y, false);
	  this.turn = Actor.PLAYER; //Player always starts to play
	  this.result = null;
  }

  public boolean placeShips(Army ships, Actor actor) {
 //CAMBIO DE VOID A BOOLEAN!!
	   // -> Verifies the correctness of the locations 
	  boolean validPositions = battleship.util.Util.areValidArmyPositions(ships);
	  //  -> Places the Ships in the corresponding board 
	  if (validPositions) {
		  if(actor == Actor.AI)
			  this.aiBoard.placeShips(ships);
		  else if (actor == Actor.PLAYER)
			  this.playerBoard.placeShips(ships);			  
	  }
	  return validPositions;
  }

  public ShootResult shoot(Vector shot) {
 //Executes the shoot and returns the result
	  ShootResult result;
	  if (battleship.util.Util.isInputCorrect(shot)) {  
		 result = aiBoard.markShot(shot); 
		 if (battleship.util.Util.checkWinGame(aiBoard)) {
			 result = ShootResult.WIN;
		 }
	  }
	  else {
		  result = ShootResult.ERROR;		    
	  }  
  return result;
  }
}