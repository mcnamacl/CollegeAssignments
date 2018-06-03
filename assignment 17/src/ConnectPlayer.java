
public abstract class ConnectPlayer {

	private int type;
	String species;

	public ConnectPlayer(int type, String species) {
		this.type = type;
		this.species = species;
	}

	public abstract boolean putPiece(Connect4GameInterface grid, int column);

	public int getType() {
		return type;
	}

	public String getSpecies() {
		return species;
	}

}
