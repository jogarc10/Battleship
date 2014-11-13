package battleship.util;

import battleship.util.Ship;

/** 
 *   
 *    Class to hold the ships while being received from UI
 */
public class Army {

  private Ship[] ships;
  private int count;

  	public Army(int n) {
  		this.ships = new Ship[n];
  		this.count = 0;
  	}
  
	public Ship[] getShips() {
		return ships;
	}
	
	public void appendShip(Vector to, Vector from)
	{
		this.ships[this.count] = new Ship(from, to);
		this.setCount(this.getCount() + 1);
	}

	public void popShip() {
		this.setCount(this.getCount() - 1);
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
  
}
