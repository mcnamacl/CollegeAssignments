
public class C4RandomAIPlayer extends ConnectPlayer {
	
	public C4RandomAIPlayer(int type, String species){
		super(type, species);
	}

	@Override
	public boolean putPiece(Connect4GameInterface gridInstance, int column) {
		boolean finished = false;
		for (int i = 0; i < 7 && !finished; i++) {
			if (gridInstance.isValidColumn(i)) {
				gridInstance.dropPiece(this, i);
				finished = true;
			}
		}
		return finished;
	}
}
