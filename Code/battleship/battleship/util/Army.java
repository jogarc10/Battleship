package battleship.util;

import battleship.util.Ship;

/** 
 *   
 *    Class to hold the ships while being received from UI
 */
public class Army {

  private Ship[] ships;

	public Ship[] getShips() {
		return ships;
	}
	
	public void setShips(Ship[] ships) {
		this.ships = ships;
	}
  
}