public class Vector {
	
	//Attributes
	
	private int x;
	private int y;	
	
	//Constructor
	
	public Vector (int tx, int ty) {
		x = tx;
		y = ty;		
	}
	
	//Methods and functions
	
	public int getX () {
		return x;
	}

	public int getY () {
		return y;
	}
	
	public void setX (int tx) {
		x = tx;		
	}	
	
	public void setY (int ty) {
		y = ty;		
	}
}
