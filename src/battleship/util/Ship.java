package battleship.util;

public class Ship {
  
  private Vector from;
  private Vector to;
  
  public Ship(Vector from, Vector to) {
    this.from = from;
    this.to = to;
  }
  
  public Vector getTo() {
    return this.to;
  }
  
  public void setTo(Vector vector) {
    this.to = vector;
  }
  
  public Vector getFrom() {
    return this.from;
  }
  
  public void setFrom(Vector vector) {
    this.from = vector;
  }

}

