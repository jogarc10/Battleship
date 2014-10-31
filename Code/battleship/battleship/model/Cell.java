package battleship.model;

public class Cell {

	private boolean fog;

	private Tile tile;

	public boolean isFog() {
		return fog;
	}
	
	public void setFog(boolean fog) {
		this.fog = fog;
	}
	
	public Tile getTile() {
		return tile;
	}
	
	public void setTile(Tile tile) {
		this.tile = tile;
	}
}