package battleship.controller;

import battleship.model.Game;

import java.util.Scanner;
import java.lang.StringBuilder;

import battleship.util.Army;
import battleship.util.Util;
import battleship.util.Vector;

public class UserInterface {

  private Scanner scanner = new Scanner (System.in);
  private static Game game;
  private Util utilities;
  private final int shipNumber = 10;
  

  /** 
   *   
   *    Initialize the game and run the main loop
   */
  static public void Main(String[] args) {
	  System.out.println("Do you want to play battleship");
	  Scanner input = new Scanner(System.in);
	  String startTheGame = input.next();
	  if ((startTheGame == "yes") || (startTheGame == "Yes"))
	  {
		  game = new Game();
	  }
  }

  /** 
   *   
   *    -> Prompt the user for the locations of his/her ships
   *    -> Return an instance of Army with those locations
   */
  public void askBoatPlaces() {//was type Army before but i think is better void
	  Army army = new Army(shipNumber);
	  int boatNumber = 0;
	  int BoatDimension = 5;
	  Vector from, to;
	  from = new Vector(0,0);
	  to = new Vector(0,0);
	  boolean error = false;
	  StringBuilder stringbuilder;
	  
	  int numberOfDimY = 1;
	  int y = 0;
	  
	  System.out.println("Insertyour positions for the ships");
	  
	  while(!error)
		{
		  System.out.println("Ship dimension");
		  stringbuilder = new StringBuilder(BoatDimension);
		  System.out.println(stringbuilder.toString());
		  askForCoordinatesVectors(from, to);
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
  }
  
  //Asks for the cordinates of both extremes of a ship.
  public void askForCoordinatesVectors(Vector from, Vector to ) {
	  int firstX, firstY, lastX, lastY;
	  firstX = firstY = lastX = lastY = 0;
	  String firstYString, lastYString;
	  
	  System.out.println("First Coordinate X");
	  firstX = scanner.nextInt();
	  System.out.println("First Coordinate Y");
	  firstYString = scanner.next();
	  firstY = translate(firstYString);
	  from = new Vector(firstX, firstY);
	  
	  System.out.println("Second Coordinate X");
	  lastX = scanner.nextInt();
	  System.out.println("Second Coordinate Y");
	  lastYString = scanner.next();
	  lastY = translate(lastYString);
	  to = new Vector(lastX, lastY);
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
	  
	  System.out.println("Enter the coordinates x and y to shot");
	  x = scanner.nextInt();
	  yString = scanner.next();
	  y = translate(yString);
	  shot = new Vector(x,y);
	  
	  return null;
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
}
