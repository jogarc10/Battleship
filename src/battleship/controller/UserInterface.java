package battleship.controller;

import battleship.model.Actor;
import battleship.model.Game;
import battleship.model.ShootResult;
import java.util.Scanner;
import battleship.util.Army;
import battleship.util.Util;
import battleship.util.Vector;
import battleship.view.Printer;

public class UserInterface {
	
  private Scanner scanner;
  private Game game;
  private Printer printer;
  private Vector to;
  private Vector from;
  
  //Constructor   
  public UserInterface (){
	  this.scanner = new Scanner (System.in);
	  this.game = new Game();
	  this.printer = new Printer();
  }

  /** 
   *   
   *    Initialize the game and run the main loop
   */
  static public void main(String[] args) {
	  UserInterface UI = new UserInterface();
	  //System.out.println("Do you want to play battleship");
	  //String startTheGame = UI.scanner.next();
	  //startTheGame.toLowerCase();
	  //if ((startTheGame == "yes") || (startTheGame == "Yes"))
	  //{
		  UI.battleship();
	  //}
  }
  
  /** 
   *   
   *    -> Prompt the user for the locations of his/her ships
   *    -> Return an instance of Army with those locations
   */
  public Army askBoatPlaces() {//was type Army before but i think is better void
	  Army army = new Army(Util.SHIP_NUMBER);
	  Integer boatNumber = new Integer(1);
	  Integer boatDimension = new Integer(5);
	  this.from = new Vector(0,0);
	  this.to = new Vector(0,0);
	  int numberOfDimY = 1;
	  int y = 0;
	  
	  System.out.println("Insert your positions for the ships");
	  
	  while(boatDimension > 1){//TODO: change for 1, placeholder for debugging
		  System.out.println("Ship dimension: " + boatDimension.toString() + 
				  				". You have " + boatNumber.toString() + 
				  				" boat(s) still to place of this size.");
		  askForCoordinatesVectors(boatDimension);
		  if (!Util.isInputCorrect(this.from) || !Util.isInputCorrect(this.to))
		  {
			  	System.out.println("The coordinates are not valid");
			  	System.out.println("Try again...");
			  	System.out.println("-----------------------------");
		  }
		  else if (!Util.areValidArmyPositions(from, to, game.getPlayerBoard()))
		  {
			  System.out.println("The ship cant be in contact with another one placed");
			  System.out.println("Try again...");
			  System.out.println("-----------------------------");
		  }
		  else
		  {
			  army.appendShip(this.to, this.from);
			  this.game.placeShips(army, this.game.getTurn());
			  this.printer.printGame(this.game);
			  y++;
			  boatNumber--;
			  if (y == numberOfDimY)
			  {
				  boatDimension--;
				  numberOfDimY++;
				  y = 0;
				  boatNumber = numberOfDimY;
			  }
		  }
		  
		}
	  return army;	  
  }
  
  //Asks for the cordinates of both extremes of a ship.
  public void askForCoordinatesVectors(int lengthShip ) {
	  int firstX, firstY;
	  firstX = 0;
	  firstY = 0;
	  String dirString, firstYString;
	 boolean validCord;
	 
	 do {
		 validCord = true;
	  System.out.println("First Coordinate X (a...j): ");
	  firstYString = scanner.next();
	  System.out.println("First Coordinate Y (1...10): ");
	  firstX = scanner.nextInt() - 1;
	  firstYString = firstYString.toLowerCase();
	  firstY = translate(firstYString);
	  this.from = new Vector(firstX, firstY);
	  scanner.nextLine();
	  validCord = Util.isInputCorrect(from);
	  if (!validCord)
		  System.out.println("Coordinates out of bounds. Please, try again \n");
	  else {System.out.println("Direction(vertical or horizontal): ");
		  dirString = scanner.nextLine();
		  dirString = dirString.toLowerCase();
		  if(dirString.equals("vertical")) {
			  if (firstX + lengthShip -1 < Util.DIM_Y)
				  this.to = new Vector(firstX + lengthShip-1, firstY);
			  else {
				  validCord = false;
			  	  System.out.println("A portion of the ship is placed out of bounds.Try again \n ");
			  }
		  }
		  else if (dirString.equals("horizontal")){
			  if (firstY + lengthShip -1 < Util.DIM_X)
				  this.to = new Vector(firstX, firstY + lengthShip-1);
			  else {
				  validCord = false;
				  System.out.println("A portion of the ship is placed out of bounds.Try again \n ");
			  }
		  }
	  }
	 } while (!validCord);
	  
	  
	  
  }
  
  /** 
   *   
   *    -> Prompt the user for the location of the bullet
   *    -> Return a bidimensional vector with the location of the bullet
   */
  
  public Vector askForShot() {
	  Vector shot;
	  int x = 0, y = 0;
	  String yString;
	  boolean valid;
	  
	  do {
		  System.out.println("Enter the coordinates x and y to shot");
		  yString = scanner.next();
		  yString = yString.toLowerCase();
		  y = translate(yString);
		  x = scanner.nextInt()-1;
		  shot = new Vector(x,y);
		  valid = Util.isInputCorrect(shot);
		  if (!valid)
			  System.out.println("Invalid coordinates. Try again \n");
	  } while (!valid);
	  
	  return shot;
  }
    
  public int translate(String string)
  {
	  int number = 0;
	  if (string.equals("a")){
		  number = 0;
	  }
	  else if (string.equals("b")){
		  number = 1;
	  }
	  else if (string.equals("c")){
		  number = 2;
	  }
	  else if (string.equals("d")){
		  number = 3;
	  }
	  else if (string.equals("e")){
		  number = 4;
	  }
	  else if (string.equals("f")){
		  number = 5;
	  }
	  else if (string.equals("g")){
		  number = 6;
	  }
	  else if (string.equals("h")){
		  number = 7;
	  }
	  else if (string.equals("i")){
		  number = 8;
	  }
	  else if (string.equals("j")){
		  number = 9;
	  }
	  
	  return number;
  }

  //Controller of the game
  public void battleship () {	  
	  Vector shot;
	  ShootResult shotResult;
	  
	  
	  while (startMenu()){
		  printer.printGame(game);
		  Army playerArmy = askBoatPlaces();
		  
		  game.placeShips(playerArmy, Actor.PLAYER);
		  
		  Army aiArmy = battleship.controller.AI.generateShips();
		  game.placeShips(aiArmy, Actor.AI);
		  
		  while (!game.isFinished()) {
			  printer.displayTurn(game.getTurn());
			  if (this.game.getTurn() == Actor.PLAYER) {
			  	shot = askForShot();
			  }
			  else {
				  shot = AI.aIShoot();
				  System.out.println ("CPU is playing...");			  
			  }
			  shotResult = game.shoot(shot, this.game.getTurn());
			  System.out.print (game.getTurn());	
			  printer.displayResultOfShoot(shotResult);
			  pause();
			  if (shotResult != ShootResult.ERROR)
				  this.game.changeTurn();	
			  printer.printGame(game);		  
		  }
	  }
  }
  
	public boolean startMenu() {
  		boolean play = false, valid = false;
  		int option;
  		while(!valid) {
	  		Printer.displayTitle();
	  		System.out.println ("\n                       SELECT AN OPTION \n");
	  		System.out.println ("                       1.PLAY BATTLESHIP");
	  		System.out.println ("                       2.PLAY HALF LIFE 3 (CODING...)");
	  		System.out.println ("                       0.EXIT\n");
	  		System.out.print ("                       OPTION: ");
	  		option = scanner.nextInt();
	  		if (option == 1) {
	  			play = true;
	  			valid = true;
	  		}
	  		else if (option == 0) {
	  			play = false;
	  			valid = true;
	  		}
	  		for (int i = 0; i < 15; ++i) System.out.println(".");
  		}		
  		return play;  		
  	}
	
	 public void pause() {
		 System.out.print("PRESS ENTER TO CONTINUE.....");
		 scanner.nextLine();
		 scanner.nextLine();
		 System.out.println();
	 }
 }