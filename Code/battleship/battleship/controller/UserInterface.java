package battleship.controller;

import battleship.model.Actor;
import battleship.model.Game;
import battleship.model.ShootResult;

import java.util.Scanner;
import java.lang.StringBuilder;

import battleship.util.Army;
import battleship.util.Util;
import battleship.util.Vector;
import battleship.view.Printer;

public class UserInterface {
	
  private final int SHIP_NUMBER = 10;

  private Scanner scanner;
  private static Game game;
  private Util utilities;
  
  //Constructor 
  
  public UserInterface (){
	  scanner = new Scanner (System.in);  
  }

  /** 
   *   
   *    Initialize the game and run the main loop
   */
  static public void main(String[] args) {
	  UserInterface UI = new UserInterface();
	  //System.out.println("Do you want to play battleship");
	  //String startTheGame = UI.scanner.next();
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
	  Army army = new Army(SHIP_NUMBER);
	  int boatNumber = 0;
	  int BoatDimension = 5;
	  Vector from, to;
	  from = new Vector(0,0);
	  to = new Vector(0,0);
	  boolean error = false;
	  StringBuilder stringbuilder;
	  
	  int numberOfDimY = 1;
	  int y = 0;
	  
	  System.out.println("Insert your positions for the ships");
	  
	  while(!error)
		{
		  System.out.println("Ship dimension");
		  stringbuilder = new StringBuilder(BoatDimension);
		  System.out.println(stringbuilder.toString());
		  askForCoordinatesVectors(from, to, BoatDimension);
		  if (!utilities.isInputCorrect(from) || !utilities.isInputCorrect(to))
		  {
			  	error = true;
			  	System.out.println("The coordinates are not valid");
		  }
		  else
		  {
			  army.setShipInPosition(boatNumber, to, from);
			  boatNumber++;
			  y++;
			  if (y == numberOfDimY)
			  {
				  BoatDimension--;
				  numberOfDimY++;
				  y = 0;
			  }
		  }
		}
	  return army;	  
  }
  
  //Asks for the cordinates of both extremes of a ship.
  public void askForCoordinatesVectors(Vector from, Vector to, int lengthShip ) {
	  int firstX, firstY;
	  firstX = firstY = 0;
	  String firstYString, dirString;
	  
	  System.out.println("First Coordinate X");
	  firstX = scanner.nextInt();
	  System.out.println("First Coordinate Y");
	  firstYString = scanner.next();
	  firstY = translate(firstYString);
	  from = new Vector(firstX, firstY);
	  
	  System.out.println("Direction(vertical or horizontal):");
	  dirString = scanner.next();
	  if(dirString == "vertical")
	  {
		  to = new Vector(firstX, firstY + lengthShip);
	  }
	  else if (dirString == "horizontal")
	  {
		  to = new Vector(firstX  + lengthShip, firstY);
	  }
	  
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
		  x = scanner.nextInt();
		  yString = scanner.next();
		  y = translate(yString);
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
	  if (string == "a")
	  {
		  number = 0;
	  }
	  else if (string == "b")
	  {
		  number = 1;
	  }
	  else if (string == "c")
	  {
		  number = 2;
	  }
	  else if (string == "d")
	  {
		  number = 3;
	  }
	  else if (string == "e")
	  {
		  number = 4;
	  }
	  else if (string == "f")
	  {
		  number = 5;
	  }
	  else if (string == "g")
	  {
		  number = 6;
	  }
	  else if (string == "h")
	  {
		  number = 7;
	  }
	  else if (string == "i")
	  {
		  number = 8;
	  }
	  else if (string == "j")
	  {
		  number = 9;
	  }
	  
	  return number;
  }

  //Controller of the game
  public void battleship () {
	  Game game = new Game();
	  Printer printer = new Printer();
	  Vector shot;
	  ShootResult shotResult;
	  
	  printer.printGame(game);
	  Army playerArmy = askBoatPlaces();
	  
	  game.placeShips(playerArmy, Actor.PLAYER);
	  while (game.isFinished()) {
		  printer.printGame(game);
		  shot = askForShot();
		  shotResult = game.shoot(shot);
		  printer.printGame(game);
		  printer.displayResultOfShoot(shotResult);			  
	  };	 	  
  }
 }
