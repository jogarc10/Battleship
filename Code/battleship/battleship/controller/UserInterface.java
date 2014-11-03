package battleship.controller;

import battleship.model.Game;

import java.util.Scanner;

import battleship.util.Army;
import battleship.util.Ship;
import battleship.util.Util;

import battleship.util.Vector;

public class UserInterface {

  private Scanner scanner = new Scanner (System.in);
  private static Game game;
  private Util utilities;
  

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
  public Army askBoatPlaces() {
	  Army army = new Army(10);
	  int boatNumber = 0;
	  Vector from, to;
	  from = new Vector(0,0);
	  to = new Vector(0,0);
	  
	  System.out.println("Insertyour positions for the ships");
	  System.out.println("Ship dimension 5");
	  askForCoordinatesVectors(from, to);
	  army.setShipInPosition(boatNumber, to, from);
	  boatNumber++;
	  
	  System.out.println("First ship dimension 4");
	  askForCoordinatesVectors(from, to);
	  army.setShipInPosition(boatNumber, to, from);
	  boatNumber++;
	  
	  System.out.println("Second ship dimension 4");
	  askForCoordinatesVectors(from, to);
	  army.setShipInPosition(boatNumber, to, from);
	  boatNumber++;
	  
	  System.out.println("First ship dimension 3");
	  askForCoordinatesVectors(from, to);
	  army.setShipInPosition(boatNumber, to, from);
	  boatNumber++;
	  
	  System.out.println("Second ship dimension 3");
	  askForCoordinatesVectors(from, to);
	  army.setShipInPosition(boatNumber, to, from);
	  boatNumber++;
	  
	  System.out.println("Third ship dimension 3");
	  askForCoordinatesVectors(from, to);
	  army.setShipInPosition(boatNumber, to, from);
	  boatNumber++;
	  
	  System.out.println("First ship dimension 2");
	  askForCoordinatesVectors(from, to);
	  army.setShipInPosition(boatNumber, to, from);
	  boatNumber++;
	  
	  System.out.println("Second ship dimension 2");
	  askForCoordinatesVectors(from, to);
	  army.setShipInPosition(boatNumber, to, from);
	  boatNumber++;
	  
	  System.out.println("Third ship dimension 2");
	  askForCoordinatesVectors(from, to);
	  army.setShipInPosition(boatNumber, to, from);
	  boatNumber++;
	  
	  System.out.println("Fourth ship dimension 2");
	  askForCoordinatesVectors(from, to);
	  army.setShipInPosition(boatNumber, to, from);
	  boatNumber++;
	  
	  
	  return null;
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
