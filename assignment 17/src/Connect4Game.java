
/* SELF ASSESSMENT

Connect4Game class (35 marks) 35
My class creates references to the Connect 4 Grid and two Connect 4 Players. It asks the user whether he/she would like to play/quit inside a loop. If the user decides to play then: 1. Connect4Grid2DArray is created using the Connect4Grid interface, 2. the two players are initialised - must specify the type to be ConnectPlayer, and 3. the game starts. In the game, I ask the user where he/she would like to drop the piece. I perform checks by calling methods in the Connect4Grid interface. Finally a check is performed to determine a win. 
Comment: yes there is a reference to a connect 4 grid and 2 players. the game will keep going unless the player types "exit". Through each player the checks are performed to see that the column is valid. The playGame method
is the one the checks if the grid is full/ someone has won each time someone has played.

Connect4Grid interface (10 marks) 10
I define all 7 methods within this interface.
Comment: all 7 methods are defined within the interface.

Connect4Grid2DArray class (25 marks) 25
My class implements the Connect4Grid interface. It creates a grid using a 2D array Implementation of the method to check whether the column to drop the piece is valid. It provides as implementation of the method to check whether the column to drop the piece is full. It provides as implementation of the method to drop the piece.  It provides as implementation of the method to check whether there is a win.
Comment: my Connect4Grid2DArray class performs all of these methods: it checks to see as to whether the grid is full/empty, whether the last piece connected 4, whether the column is valid or not and returns the grid as a string.

ConnectPlayer abstract class (10 marks) 10
My class provides at least one non-abstract method and at least one abstract method. 
Comment: yes, the abstract method is to place the piece in the grid and the two non abstract methods are to return the type/species.

C4HumanPlayer class (10 marks) 10
My class extends the ConnectPlayer class and overrides the abstract method(s). It provides the Human player functionality.
Comment: the class extends ConnectPlayer and overrides the abstract method putPiece. This method places a piece if it can and if not will return false;

C4RandomAIPlayer class (10 marks) 10
My class extends the ConnectPlayer class and overrides the abstract method(s). It provides AI player functionality. 
Comment: the class extends ConnectPlayer and overrides the abstract method putPiece. This method places a piece if it can and if not will return false;

Total Marks out of 100: 100

*/

import java.util.Scanner;

public class Connect4Game {

	public static void main(String[] args) {
		int type = 0;
		System.out.println("Would you like to play connect 4? or else type exit.");
		Scanner input = new Scanner(System.in);
		String response = input.nextLine();
		if (!response.toLowerCase().equals("exit")) {
			Connect4GameInterface gridInstance = new Connect4Grid2DArray();
			
			System.out.println("Who would you like player one to be?");
			input = new Scanner(System.in);
			String species = input.nextLine();
			ConnectPlayer player1 = species(species, type += 1);

			System.out.println("Who would you like player two to be?");
			input = new Scanner(System.in);
			species = input.nextLine();

			ConnectPlayer player2 = species(species, type += 1);

			playGame(player1, player2, gridInstance);
		}
		input.close();
	}

	public static void playGame(ConnectPlayer player1, ConnectPlayer player2, Connect4GameInterface grid) {
		boolean finished = false;
		boolean won = false;
		int turnCounter = 2;
		while (!finished) {
			if (turnCounter % 2 == 0) {
				if (!player1.getSpecies().equals("AI")) {
					if (!human(player1, grid)) {
						finished = true;
					}
					if (grid.didLastPieceConnect4()) {
						System.out.println(player1.getSpecies() + " wins!");
						won = true;
						finished = true;
					}
					if (grid.isGridFull()) {
						System.out.println("The grid is full.");
						finished = true;
					}
				} else {
					System.out.println("It is the AIs turn.");
					player1.putPiece(grid, 0);
					if (grid.didLastPieceConnect4()) {
						System.out.println(player1.getSpecies() + " wins!");
						won = true;
						finished = true;
					}
					if (grid.isGridFull()) {
						System.out.println("The grid is full.");
						finished = true;
					}
				}
			} else {
				if (!player2.getSpecies().equals("AI")) {
					if (!human(player2, grid)) {
						finished = true;
					}
					if (grid.didLastPieceConnect4()) {
						System.out.println(player2.getSpecies() + " wins!");
						won = true;
						finished = true;
					}
					if (grid.isGridFull()) {
						System.out.println("The grid is full.");
						finished = true;
					}
				} else {
					System.out.println("It is the AIs turn.");
					player2.putPiece(grid, 0);
					if (grid.didLastPieceConnect4()) {
						System.out.println(player2.getSpecies() + " wins!");
						won = true;
						finished = true;
					}
					if (grid.isGridFull()) {
						System.out.println("The grid is full.");
						finished = true;
					}
				}
			}
			turnCounter++;
			if (!finished && !won || won) {
				String printGrid = grid.toString();
				System.out.println(printGrid);
			}
		}
	}

	public static ConnectPlayer species(String species, int type) {
		ConnectPlayer player;
		if (species.equals("AI")) {
			player = new C4RandomAIPlayer(type, species);
		} else {
			player = new C4HumanPlayer(type, species);
		}
		return player;
	}


	public static boolean isValidInput(String input) {
		return input.matches("[0-9]+");
	}

	public static boolean human(ConnectPlayer player, Connect4GameInterface grid) {
		boolean valid = false;
		boolean unfinished = false;
		while (!valid) {
			System.out.println("Which column would " + player.getSpecies() + " like to select? or else type exit");
			Scanner input = new Scanner(System.in);
			String testInput = input.nextLine();
			if (testInput.toLowerCase().equals("exit")) {
				valid = true;
				unfinished = false;
			} else if (isValidInput(testInput)) {
				int column = Integer.parseInt(testInput);
				if (!player.putPiece(grid, column)) {
					System.out.println("Sorry invalid input please try again.");
				} else {
					valid = true;
					unfinished = true;
				}
			}
		}
		return unfinished;
	}
}
