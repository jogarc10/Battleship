import java.util.Vector;

public class Game {
	
  //Constants
	
	public final static short DIM_X = 10;
	public final static short DIM_Y = 10;
		
  //Attributes
	
  private Actor winner;

  private boolean isFinished;

  private Board aiBoard;

  private Board playerBoard;

  private Actor turn;

  private ShootResult result;
  
  //Constructor

  public Game() {
	  this.winner = null;
	  this.isFinished = false;
	  this.aiBoard =  new AIBoard (DIM_X,DIM_Y);
	  this.playerBoard =  new PlayerBoard (DIM_X,DIM_Y);
	  this.turn = Actor.PLAYER; //Player always starts to play
	  this.result = null; 	  
  }
  
  //Methods

  public void printGame() { //  Calls methods in the subclasses of Board to print them */
	  playerBoard.print();
	  aiBoard.print();	  
  } 

 
  public boolean placeShips(Army ships, Actor actor) { //CAMBIO DE VOID A BOOLEAN!!
	   // -> Verifies the correctness of the locations 
	  boolean validPositions = Util.areValidArmyPositions(ships);
	  //  -> Places the Ships in the corresponding board 
	  if (validPositions) {
		  if(actor == Actor.AI)
			  this.aiBoard.placeShips(ships);
		  else if (actor == Actor.PLAYER)
			  this.playerBoard.placeShips(ships);			  
	  }
	  return validPositions;   
  }

 
  public ShootResult shoot(Vector shot) { //Executes the shoot and returns the result
	  ShootResult result;
	  if (Util.isInputCorrect(shot)) {  //NECESITO QUE ESTA FUNCIÓN ACEPTE VECTOR COMO PARAMETRO, NO BOARD!!
		 result = aiBoard.markShot(shot); //NECESITO QUE ESTA FUNCIÓN ME RETORNE SI HA SIDO AGUA/TOCADO/HUNDIDO 
		 if (Util.checkWinGame(aiBoard)) {
			 result = ShootResult.WIN;
		 }
	  }
	  else {
		  result = ShootResult.ERROR;		    
	  }  
  return result;
  }
}
