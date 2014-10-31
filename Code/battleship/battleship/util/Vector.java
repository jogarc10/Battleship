package battleship.util;

public class Vector {

  private int x;

  private int y;
   
  public Vector(int tx, int ty) {
		x = tx;
		y = ty;
  }

  public int getX() {
		return x;
  }

  public int getY() {
		return y;
  }

  public void setX(int tx) {
		x = tx;
  }

  public void setY(int ty) {
		y = ty;
  }
}