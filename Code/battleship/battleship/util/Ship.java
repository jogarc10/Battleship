package battleship.util;

import battleship.util.Ship;

/** 
 *   
 *    Class to hold the ships while being received from UI
 */
public class Army {

  private Ship[] ships;

  	public Army(int n) {
  		this.ships = new Ship[n];
  	}
  
	public Ship[] getShips() {
		return ships;
	}
	
	public void setShipInPosition(int n, Vector to, Vector from)
	{
		this.ships[n] = new Ship(to, from);
	}
  
}
