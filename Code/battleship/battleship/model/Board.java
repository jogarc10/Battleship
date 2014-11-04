package battleship.model;

import battleship.util.Ship;
import battleship.util.Vector;
import battleship.util.Army;

public class Board {
	private int width;
	private int height;
	private Cell[][] board;

	public Board(int dimx, int dimy, boolean hasFog) {
		width = dimx;
	 	height = dimy;
	 	board = new Cell[dimx][dimy];
	 	initializeFog(hasFog);
	}
  
	public void initializeFog(boolean withFog) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (withFog) {
					board[i][j].setFog(true);
				}
				else {
					board[i][j].setFog(false);
				}
			}
		}
	}

	public String toString() {
		String strBoard = null;
		Tile t = Tile.EMPTY;
	  
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
			  
				t = board[i][j].getTile();
			  
				if (t == Tile.EMPTY) {
					strBoard = strBoard + " ";
				}
				else if (t == Tile.WATER) {
					strBoard = strBoard + "W";
				}
				else if (t == Tile.BOAT) {
					strBoard = strBoard + "B";
				}			  
			}
			strBoard = strBoard + "\n";
		}  
		return strBoard;
	}

  /** 
   *   
   *    -> If fog is activated in this tile, deactivate it
   *    -> Mark the Tile as corresponds (hit, sunken...)
   *    if the shoot hunde el barco, marcar las celdas como hundido
   */
	public ShootResult markShot(Vector coordinates) {
		int x, y;
		Tile tile = Tile.EMPTY;
		ShootResult result = ShootResult.ERROR;
	  
		x = coordinates.getX();
		y = coordinates.getY();
	  
		if (board[x][y].isFog()) {
	
			board[x][y].setFog(false); // Deactive the fog
			tile = board[x][y].getTile(); // Get the title
			
			if (tile == Tile.BOAT) {
				// Como hay un barco, ahora habría que:
				// 1. Comprobar si se ha hundido el barco
				//   (ver si el resto de celdas están con fog = false)
				// 2. En caso de que se haya hundido, marcarlo en el tablero.
				result = ShootResult.HIT;
				setSunkenShip(coordinates);
			}
			else if (tile == Tile.WATER) {
				result = ShootResult.MISS; // Miss!!
			}			
		}
		else if(!board[x][y].isFog()) {
			result = ShootResult.ERROR; // The tile was shown before. sError!
		}
	  return result;
	}
	
	/** Function para saber si el barco se ha hundido o no **/
	/** Y en caso de hundido, marcar las celdas **/
	
	public void setSunkenShip(Vector coords) {
		int x, y;
		
		x = coords.getX();
		y = coords.getY();
		
		horizontalShip(x, y); // updates the board with the sunken ships
		verticalShip(x, y); // updates the board with the sunken ships
		
	}
	
	/**
	 *	-> Checks if it's formed an horizontal Ship
	 *	-> If it's formed, then marked on the board
	*/
	
	public void horizontalShip(int x, int y) {
		Tile tile;
		int tileCounter = 1, shipColumn;
		boolean horizontalShipLeft = true, horizontalShipRight = true;
		
		shipColumn = y; // Columna más a la izquierda donde empieza el barco 
		tile = board[x][y].getTile();
		
		for (int i = 1; i <= 5; i++) {

			// Si la celda de la izquierda es un barco
			// y además no hay fog. Entonces suma uno al contador y
			// guarda la columna para saber dónde empieza el barco.
			
			if ((horizontalShipLeft) && (y - i >= 0)) 
			{
				if ((board[x][y - i].getTile() == tile) && !(board[x][y - i].isFog())) {
					tileCounter++;
					shipColumn = y - i;
				}
				else {
					horizontalShipLeft = false;
				}				
			}
			else {
				horizontalShipLeft = false; // no hay un barco a la izquierda
			}
			
			// Si la celda de la derecha es un barco
			// y además no hay fog. Entonces suma uno al contador.
			
			if ((horizontalShipRight) && (y + i < width)) {
				if ((board[x][y + i].getTile() == tile) && !(board[x][y + i].isFog())) {
					tileCounter++;
				}
				else {
					horizontalShipRight = false; 
				}				
			}
			else {
				horizontalShipRight = false; // no hay un barco a la derecha
			}	
		}	

		// Si el contador es mayor o igual que dos. Entonces se ha formado un barco.
		// Por lo tanto empezando desde el punto donde empieza el barco. Marcará el resto de 
		// celdas como hundidas.
		
		if (tileCounter >= 2) {
			for (int i = 0; i < tileCounter; i++) {
				board[x][shipColumn + i].setTile(Tile.SUNKEN);
			}
		}
	}
	
	/**
	 *	-> Checks if it's formed a vertical Ship
	 *	-> If it's formed, then marked on the board
	*/
	
	public void verticalShip(int x, int y) {
		Tile tile;
		int tileCounter = 1, shipRow;
		boolean shipTop = true, shipBottom = true;
		
		shipRow = x; // Fila superior donde empieza el barco 
		tile = board[x][y].getTile();
		
		for (int i = 1; i <= 5; i++) {

			// Si la celda de la izquierda es un barco
			// y además no hay fog. Entonces suma uno al contador y
			// guarda la columna para saber dónde empieza el barco.
			
			if ((shipTop) && (x - i >= 0)) 
			{
				if ((board[x - i][y].getTile() == tile) && !(board[x - i][y].isFog())) {
					tileCounter++;
					shipRow = x - i;
				}
				else {
					shipTop = false;
				}				
			}
			else {
				shipTop = false; // no hay un barco a la izquierda
			}
			
			// Si la celda de la derecha es un barco
			// y además no hay fog. Entonces suma uno al contador.
			
			if ((shipBottom) && (x + i < height)) {
				if ((board[x + i][y].getTile() == tile) && !(board[x + i][y].isFog())) {
					tileCounter++;
				}
				else {
					shipBottom = false; 
				}				
			}
			else {
				shipBottom = false; // no hay un barco a la derecha
			}	
		}	

		// Si el contador es mayor o igual que dos. Entonces se ha formado un barco.
		// Por lo tanto empezando desde el punto donde empieza el barco. Marcará el resto de 
		// celdas como hundidas.
		
		if (tileCounter >= 2) {
			for (int i = 0; i < tileCounter; i++) {
				board[x][shipRow + i].setTile(Tile.SUNKEN);
			}
		}
	}
	
	/**
	 *	-> "Ship"  and "Army" are classes to format the input from user
	 *	-> Update the board with newly placed ships
	*/
	public void placeShips(Army ships) {
		int originX, originY, endX, endY, shipLength;
		boolean horizontalShip, verticalShip;
		Ship[] armyShips = ships.getShips();
		
		for (int i = 0; i < height; i++) {
			shipLength = 0;
			originX = armyShips[i].getTo.getX();
			originY = armyShips[i].getTo.getY();
			endX = armyShips[i].getFrom.getX();
			endY = armyShips[i].getFrom.getX();
			horizontalShip = false;
			verticalShip = false;
			
			// Knows if the board is horizontal or vertical
			if (originX - endX == 0) {
				horizontalShip = true; // Ship with the same x
				shipLength =  endY - originY + 1; // Example: (2,2) y (2,4) > 4 - 2 = 2 + 1 = 3
			}
			else if (originY - endY == 0) {
				verticalShip = true; // Ship with the same y
				shipLength =  endY - originY + 1; // Example: (1,1) y (5,1) > 5 - 1 = 4 + 1 = 5 
			}
			
			// Place the Ships on the board
			for (int shipIndex = 0; shipIndex < shipLength; shipIndex++) {
				if (horizontalShip) {
					board[originX + shipIndex][originY].setTile(Tile.BOAT); /* Inicializar celda a Boat */
				}
				else if (verticalShip) {
					board[originX][originY  + shipIndex].setTile(Tile.BOAT); /* Inicializar celda a Boat */
				}
			}			
		}
	}

	/**
	 *	checkWinGame. Borja's code
	 **/

	public boolean checkWinGame() {
	 	boolean livingShips = false;
	  	int i = 0;
	  	int j = 0;
	  	while ((i < width) && !livingShips) {
	  		while((j < height) && !livingShips) {
	  			if (board[j][i].getTile() == Tile.BOAT) {
	  				livingShips = true;
	  			}			
	  			j++;
	  		}
	  		i++;
	  	}  	
	
	  	return !livingShips;
	}
	
	/**
	 *	Getter for private attribute Board
	 **/
	
	 public Board getBoard() {
		 return this;
	 }

	 public void setBoard(Cell[][] board) {
		 this.board = board;
	 }
}
