public class Connect4Grid2DArray implements Connect4GameInterface {

	int[][] grid;

	Connect4Grid2DArray() {
		emptyGrid();
	}

	@Override
	public void emptyGrid() {
		grid = new int[6][7];
	}

	@Override
	public boolean isValidColumn(int column) {
		return !isColumnFull(column);
	}

	@Override
	public boolean isColumnFull(int column) {
		int counter = 0;
		for (int i = 0; i < 6; i++) {
			if (grid[i][column] != 0) {
				counter++;
			}
		}
		return counter == 6;
	}

	@Override
	public void dropPiece(ConnectPlayer player, int column) {
		int i;
		for (i = 0; i < grid.length; i++) {
			if (grid[i][column] != 0) {
				break;
			}
		}
		grid[i - 1][column] = player.getType();
	}

	@Override
	public boolean didLastPieceConnect4() {
		boolean result = false;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if (grid[i][j] != 0 && i <= 2 && grid[i][j] == grid[i + 1][j] && grid[i + 1][j] == grid[i + 2][j]
						&& grid[i + 2][j] == grid[i + 3][j]) {
					result = true;
					break;
				}
				if (grid[i][j] != 0 && j <= 2 && grid[i][j] == grid[i][j + 1] && grid[i][j + 1] == grid[i][j + 2]
						&& grid[i][j + 2] == grid[i][j + 3]) {
					result = true;
					break;
				}
				if (grid[i][j] != 0 && i <= 2 && j <= 2 && grid[i][j] == grid[i + 1][j + 1]
						&& grid[i + 1][j + 1] == grid[i + 2][j + 2] && grid[i + 2][j + 2] == grid[i + 3][j + 3]) {
					result = true;
					break;
				}
				if (grid[i][j] != 0 && i >= 2 && j >= 2 && grid[i][j] == grid[i - 1][j - 1]
						&& grid[i - 1][j - 1] == grid[i - 2][j - 2] && grid[i - 2][j - 2] == grid[i - 3][j - 3]) {
					result = true;
					break;
				}

			}
		}
		return result;
	}

	@Override
	public boolean isGridFull() {
		int counter = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 0) {
					counter++;
				}
			}
		}
		return counter == 0;
	}
	
	@Override
	public String toString() {
		String gridString = "";
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				gridString = gridString + grid[i][j] + " ";
			}
			gridString = gridString + "\n";
		}
		return gridString;
	}
}
