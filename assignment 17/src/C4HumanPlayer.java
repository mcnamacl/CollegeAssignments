
public class C4HumanPlayer extends ConnectPlayer {

	public C4HumanPlayer(int type, String species) {
		super(type, species);
	}

	@Override
	public boolean putPiece(Connect4GameInterface gridInstance, int column) {
		column = column - 1;
		if (gridInstance.isValidColumn(column)) {
			gridInstance.dropPiece(this, column);
			return true;
		} else {
			return false;
		}
	}
}
