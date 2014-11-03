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
	  
	  System.out.println("First Coordinate X");
	  firstX = scanner.nextInt();
	  System.out.println("First Coordinate Y");
	  firstX = scanner.nextInt();
	  from = new Vector(firstX, firstY);
	  
	  System.out.println("Second Coordinate X");
	  firstX = scanner.nextInt();
	  System.out.println("Second Coordinate Y");
	  firstX = scanner.nextInt();
	  to = new Vector(lastX, lastY);
  }
  
  /** 
   *   
   *    -> Prompt the user for the location of the bullet
   *    -> Return a bidimensional vector with the location of the bullet
   */
  
  public battleship.util.Vector askForShot() {
	  return null;
  }
}
