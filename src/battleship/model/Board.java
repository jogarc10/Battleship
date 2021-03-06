package battleship.model;

import battleship.util.Ship;
import battleship.util.Vector;
import battleship.util.Army;

public class Board {
	private int width;
	private int height;
	private Cell[][] board;
	/*
	 static public void main(String args[]) {
		Board board = new Board(10, 10, false);
		Army army = new Army(1);
		Printer printer = new Printer();
		army.appendShip(new Vector(7, 3), new Vector(7, 2));
		
		board.placeShips(army);
		printer.printBoard(board);
	}
*/
	public Board(int dimx, int dimy, boolean hasFog) {
		width = dimx;
	 	height = dimy;
	 	board = new Cell[dimx][dimy];
	 	for (int i = 0; i < dimx;i++)
	 		for (int j = 0; j < dimy;j++)
	 			board[i][j] = new Cell();
	 	initializeFog(hasFog);
	}
  
	public Board() {
		// TODO Auto-generated constructor stub		
		this.height = 0;
		this.width = 0;
		this.board = new Cell[0][0];		
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

	public String toString(Actor turn) {	
		
		String strBoard = "";
		Tile t = Tile.EMPTY;
		boolean hasFog;
		
		//Numbers on the top
		
		strBoard += "     ";
		for (int j = 0; j < width; j++)
			strBoard += " " + (char)(j+65) + "  ";
		strBoard += "\n     ";
		
		//First line
		
		for (int j = 0; j < width-1; j++)
			strBoard += "____";
		strBoard += "___\n";
		
		//Body
		
		for (int i = 0; i < height; i++) {
			strBoard += "  " + (i+1);
			if(i + 1 < 10) strBoard = strBoard + " ";
			for (int j = 0; j < width; j++) {
				hasFog = false;
				if(board[i][j].isFog())
					hasFog = true;
				t = board[i][j].getTile();
				
				strBoard += "|";
			  
				if ((hasFog && turn == Actor.AI) ||t == Tile.EMPTY ) {
					strBoard = strBoard + "_ _";
				}
				else if (t == Tile.WATER) {
					strBoard = strBoard + "_W_";
				}
				else if (t == Tile.BOAT) {
					strBoard = strBoard + "_B_";
				}
				else if (t == Tile.HIT) {
					strBoard = strBoard + "_H_";
				}
				else if (t == Tile.SUNKEN) {
					strBoard = strBoard + "_X_";
				}
				
				
			}
			strBoard += "|\n";
		}  
		
	
		
		return strBoard;
	}

  /** 
   *   
   *    -> If fog is activated in this tile, deactivate it
   *    -> Mark the Tile as corresponds (hit, sunken...)
   *    if the shoot hunde el barco, marcar las celdas como hundido
   */
	public ShootResult markShot(Vector coordinates, Actor turn) {
		int x, y;

		Tile tile = Tile.EMPTY;
		ShootResult result = ShootResult.ERROR;
	  
		x = coordinates.getX();
		y = coordinates.getY();
	  
		if (board[x][y].isFog() || turn == Actor.AI) { 
	
			board[x][y].setFog(false); // Disable the fog if player is playing
			tile = board[x][y].getTile(); // Get the title
			
			if (tile == Tile.BOAT) {
				// Como hay un barco, ahora habría que:
				// 1. Comprobar si se ha hundido el barco
				//   (ver si el resto de celdas están con fog = false)
				// 2. En caso de que se haya hundido, marcarlo en el tablero.
				result = ShootResult.HIT;
				board[x][y].setTile(Tile.HIT);
				//setSunkenShip(coordinates);
			}
			else if (tile == Tile.EMPTY) {
				result = ShootResult.MISS; // Miss!!
				board[x][y].setTile(Tile.WATER);
			}			
		}
		else {
			result = ShootResult.ERROR; // The tile was shown before. sError!
		}
	  return result;
	}
	
	/** Function para saber si el barco se ha hundido o no **/
	/** Y en caso de hundido, marcar las celdas **/
	
	public boolean setSunkenShip(Vector coords) {
		int x, y;
		boolean sunken = false;
		
		x = coords.getX();
		y = coords.getY();
		
		sunken = horizontalShip(x, y); // updates the board with the sunken ships
		sunken = verticalShip(x, y); // updates the board with the sunken ships
		
		return sunken;
	}
	
	/**
	 *	-> Checks if it's formed an horizontal Ship
	 *	-> If it's formed, then marked on the board
	*/
	
	public boolean horizontalShip(int x, int y) {
		boolean sunken = false;
		Tile tile;
		int tileCounter = 1, shipColumn;
		boolean horizontalShipLeft = true, horizontalShipRight = true;
		
		shipColumn = y; // Columna mÃ¡s a la izquierda donde empieza el barco 
		tile = board[x][y].getTile();
		
		if (tile != Tile.WATER && tile != Tile.BOAT) {
			for (int i = 1; i <= 5; i++) {

				// Si la celda de la izquierda es un barco
				// y ademas no hay fog. Entonces suma uno al contador y
				// guarda la columna para saber dÃ³nde empieza el barco.
				
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
				// y ademÃ¡s no hay fog. Entonces suma uno al contador.
				
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
			// Por lo tanto empezando desde el punto donde empieza el barco. MarcarÃ¡ el resto de 
			// celdas como hundidas.
			
			if (tileCounter >= 2) {
				for (int i = 0; i < tileCounter; i++) {
					board[x][shipColumn + i].setTile(Tile.SUNKEN);
				}
				sunken = true;
			}
		}	
		return sunken;
	}
	
	/**
	 *	-> Checks if it's formed a vertical Ship
	 *	-> If it's formed, then marked on the board
	*/
	
	public boolean verticalShip(int x, int y) {
		Tile tile;
		boolean sunken = true;
		int tileCounter = 1, shipRow;
		boolean shipTop = true, shipBottom = true;
		
		shipRow = x; // Fila superior donde empieza el barco 
		tile = board[x][y].getTile();
		
		if (tile != Tile.WATER && tile != Tile.BOAT) {
			for (int i = 1; i <= 5; i++) {
	
				// Si la celda de la izquierda es un barco
				// y ademÃ¡s no hay fog. Entonces suma uno al contador y
				// guarda la columna para saber dÃ³nde empieza el barco.
				
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
				// y ademÃ¡s no hay fog. Entonces suma uno al contador.
				
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
			// Por lo tanto empezando desde el punto donde empieza el barco. MarcarÃ¡ el resto de 
			// celdas como hundidas.
			
			if (tileCounter >= 2) {
				for (int i = 0; i < tileCounter; i++) {
					board[shipRow + i][y].setTile(Tile.SUNKEN);
				}
				sunken = true;
			}
		}
		return sunken;
	}
	
	/**
	 *	-> "Ship"  and "Army" are classes to format the input from user
	 *	-> Update the board with newly placed ships
	*/
	public void placeShips(Army ships) {
		int originX, originY, endX, endY, shipLength;
		boolean horizontalShip, verticalShip;
		Ship[] armyShips = ships.getShips();
		
		for (int i = 0; i < ships.getCount(); i++) {
			shipLength = 0;
			
			originX = armyShips[i].getFrom().getX();
			originY = armyShips[i].getFrom().getY();
			endX = armyShips[i].getTo().getX();
			endY = armyShips[i].getTo().getY();

			horizontalShip = false;
			verticalShip = false;
			
			// Knows if the ship is horizontal or vertical
			if (originX  == endX) {
				horizontalShip = true; // Ship with the same x
				shipLength =  endY - originY + 1; // Example: (2,2) y (2,4) > 4 - 2 = 2 + 1 = 3
			}
			else if (originY  == endY) {
				verticalShip = true; // Ship with the same y
				shipLength =  endX - originX + 1; // Example: (1,1) y (5,1) > 5 - 1 = 4 + 1 = 5 
			}
			
			// Place the Ships on the board
			for (int shipIndex = 0; shipIndex < shipLength; shipIndex++) {
				if (horizontalShip) {
					board[originX][originY + shipIndex].setTile(Tile.BOAT); /* Inicializar celda a Boat */
				}
				else if (verticalShip) {
					board[originX + shipIndex][originY].setTile(Tile.BOAT); /* Inicializar celda a Boat */
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
	  			if (this.board[j][i].getTile() == Tile.BOAT) {
	  				livingShips = true;
	  			}			
	  			j++;
	  		}
	  		j = 0;
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
	 
	 public Tile cellValue(int x,int y)
	 {
		return this.board[x][y].getTile(); 
	 }
}
