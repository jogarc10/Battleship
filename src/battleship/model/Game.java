package battleship.model;

import battleship.util.Util;
import battleship.util.Vector;
import battleship.util.Army;

public class Game {

  private boolean isFinished;

  private Actor winner;

  private Board aiBoard;

  private Board playerBoard;

  private Actor turn;

  private ShootResult result;
  

  public Game() {
	  this.winner = null;
	  this.isFinished = false;
	  this.aiBoard =  new Board (Util.DIM_X,Util.DIM_Y, true); //PONER FALSE PARA VER EL TABLERO DE LA CPU, PERO NO SE PUEDE JUGAR! TRUE PARA OCULTARLO Y JUGAR
	  this.playerBoard =  new Board (Util.DIM_X,Util.DIM_Y, false);
	  this.turn = Actor.PLAYER; //Player always starts to play
	  this.result = null;
  }

  public boolean placeShips(Army ships, Actor actor) {
	   // -> Verifies the correctness of the locations 
	  boolean validPositions = true;//TODO: battleship.util.Util.areValidArmyPositions(ships);
	  //  -> Places the Ships in the corresponding board 
	  if (validPositions) {
		  if(actor == Actor.AI)
			  this.aiBoard.placeShips(ships);
		  else if (actor == Actor.PLAYER)
			  this.playerBoard.placeShips(ships);			  
	  }
	  return validPositions;
  }

  public ShootResult shoot(Vector shot, Actor turn) {
 //Executes the shoot and returns the result
	  ShootResult result;
	  Board tmpboard = new Board();
	  if (battleship.util.Util.isInputCorrect(shot)) {
		 if (turn == Actor.AI) {
			 tmpboard = this.playerBoard;
		 } else if (turn == Actor.PLAYER) {
			 tmpboard = this.aiBoard;
		 }
		 result = tmpboard.markShot(shot,turn); 
		 if (tmpboard.checkWinGame()) {
			result = ShootResult.WIN;		
		 }
	  }
	  else {
		  result = ShootResult.ERROR;		    
	  }  
  return result;
  }
  
  //Getters and Setters

public boolean isFinished() {
	return this.isFinished;
}

public void setFinished(boolean isFinished) {
	this.isFinished = isFinished;
}

public Actor getWinner() {
	return this.winner;
}

public void setWinner(Actor winner) {
	this.winner = winner;
}

public Board getAiBoard() {
	return this.aiBoard;
}

public void setAiBoard(Board aiBoard) {
	this.aiBoard = aiBoard;
}

public Board getPlayerBoard() {
	return this.playerBoard;
}

public void setPlayerBoard(Board playerBoard) {
	this.playerBoard = playerBoard;
}

public Actor getTurn() {
	return this.turn;
}

public void setTurn(Actor turn) {
	this.turn = turn;
}

public ShootResult getResult() {
	return this.result;
}

public void setResult(ShootResult result) {
	this.result = result;
}
public void changeTurn(){
	  if (this.turn == Actor.PLAYER) {
		  this.turn = Actor.AI;
	  } else if (this.turn == Actor.AI) {
		  this.turn = Actor.PLAYER;
	  }
}
}