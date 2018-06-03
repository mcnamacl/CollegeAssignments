import java.util.Scanner;

/* SELF ASSESSMENT 
1. clearBoard:
Did I use the correct method definition?
Mark out of 5:5
Comment: yes I did
Did I use loops to set each position to the BLANK character?
Mark out of 5:5
Comment: yes that is what happens
2. printBoard
Did I use the correct method definition?
Mark out of 5: 5
Comment: yes
Did I loop through the array and prints out the board in a way that it looked like a board?
Mark out of 5: 5
Comment: yes that is what happens
3. canMakeMove
Did I have the correct function definition and returned the correct item?
Mark out of 5: 5
Comment: yes it is a boolean
Did I check if a specified location was BLANK?
Mark out of 5: 5
Comment: yes the function does that
4. makeMove
Did I have the correct function definition?
Mark out of 5: 5
Comment: yes it is void
Did I set the  currentPlayerPiece in the specified location?
Mark out of 5: 5
Comment:    it does
5. isBoardFull
Did I have the correct function definition and returned the correct item?
Mark out of 5: 5
Comment:       yes it is a boolean  
Did I loop through the board to check if there are any BLANK characters?
Mark out of 5: 5
Comment: yes that is what it does
6. winner
Did I have the correct function definition and returned the winning character
Mark out of 5: 5
Comment:     yes it is a char function
Did I identify all possible horizontal, vertical and diagonal winners  
Mark out of 15: 15
Comment: yes it identifies everything 
7.main

Did I create a board of size 3 by 3 and use the clearBoard method to set all the positions to the BLANK character ('  ')?
Mark out of 3: 3
Comments: the board is 3x3 but the array itself is 4x4 to account for a coordinate system
Did I loop asking the user for a location until wither the board was full or there was a winner?
Mark out of 5: 5
Comments:  yes it only ends when the game ends
Did I call all of the methods above?
Mark out of 5:5
Comments: yes it calls all of the methods above
Did I handle incorrect locations provided by the user (either occupied or invalid locations)?
Mark out of 3: 3
Comments: yes there is error checking
Did I switch the current player piece from cross to nought and vice versa after every valid move?
Mark out of 3: 3
Comments: yes I set up a modulus 2 system to do this with alternating colours (player 1 = red, player 2 = green)
Did I display the winning player piece or a draw at the end of the game?
Mark out of 3: 3
Comments: yes it tells you who wins or if there was a draw

8. Overall
Is my code indented correctly?
Mark out of 3: 3
Comments: yes it is indented correctly
Do my variable names and Constants (at least four of them) make sense?
Mark out of 3: 3
Comments: yes all my variable names have meaning 
Do my variable names, method names and class name follow the Java coding standard
Mark out of 2: 2
Comments: yes they do
   Total Mark out of 100 (Add all the previous marks): 100
*/

public class NoughtsAndCrosses {

	public static char NOUGHT = 'O';
	public static char CROSS = 'X';
	public static char BLANK = ' ';
	public static int TOTAL = 16;
	// reference:
	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";

	public static void main(String[] args) {
		boolean finishedGame = false;
		boolean check = false;
		char userInputPlayerOne = BLANK;
		char userInputPlayerTwo = BLANK;
		String playerOne = null;
		String playerTwo = null;
		System.out.println("What is the name of player 1?");
		Scanner input = new Scanner(System.in);
		if (input.hasNext()) {
			playerOne = input.nextLine();
			System.out.println("What is the name of player 2?");
		}
		input = new Scanner(System.in);
		if (input.hasNext()) {
			playerTwo = input.nextLine();
		}
		while (!check) {
			System.out.println(ANSI_RED + playerOne + " would you like to be an X or an O?");
			input = new Scanner(System.in);
			if (input.hasNext("X") || input.hasNext("x")) {
				userInputPlayerOne = CROSS;
				System.out.println(ANSI_GREEN + playerTwo + ", you are 'O'");
				userInputPlayerTwo = NOUGHT;
				check = true;
			} else if (input.hasNext("O") || input.hasNext("o")) {
				userInputPlayerOne = NOUGHT;
				System.out.println(ANSI_GREEN + playerTwo + ", you are 'X'");
				userInputPlayerTwo = CROSS;
				check = true;
			} else {
				System.out.println(ANSI_BLUE + "Sorry, invalid input, please try again.");
			}
		}
		char[][] board = new char[4][4];
		clearBoard(board);
		int count = -1;
		while (!finishedGame) {
			printBoard(board);
			count++;
			if (count % 2 == 0) {
				System.out.println(ANSI_RED + playerOne + ", what coordinates would you like to input?");
				input = new Scanner(System.in);
				if (input.hasNextInt()) {
					int row = input.nextInt();
					int column = input.nextInt();
					if (canMakeMove(board, row, column)) {
						makeMove(board, row, column, userInputPlayerOne);
					} else {
						System.out.println(ANSI_BLUE + "Sorry invalid coordinates, please try again.");
						count--;
					}
				} else {
					System.out.println(ANSI_BLUE + "Sorry invalid input, please enter a valid number.");
					count--;
				}
			} else {
				System.out.println(ANSI_GREEN + playerTwo + ", what coordinates would you like to input?");
				input = new Scanner(System.in);
				if (input.hasNextInt()) {
					int row = input.nextInt();
					int column = input.nextInt();
					if (canMakeMove(board, row, column)) {
						makeMove(board, row, column, userInputPlayerTwo);
					} else {
						System.out.println(ANSI_BLUE + "Sorry invalid coordinates, please try again.");
						count--;
					}
				} else {
					System.out.println(ANSI_BLUE + "Sorry invalid input, please enter a valid number.");
					count--;
				}

			}
			if (winner(board) != BLANK) {
				printBoard(board);
				System.out.println(winner(board) + " Wins!");
				finishedGame = true;
			} else if (isBoardFull(board)) {
				printBoard(board);
				finishedGame = true;
				System.out.println("GAME OVER! outcome of game: a draw");
			}
		}
		input.close();
	}

	// sets 1 2 3 along the borders and ' ' in the inside of the square
	public static void clearBoard(char[][] board) {
		for (int columnNumber = 1; columnNumber <= 3; columnNumber++) {
			int columnNumberAscii = columnNumber + 48;
			board[columnNumber][0] = (char) columnNumberAscii;
			columnNumberAscii -= 48;
		}
		for (int rowNumber = 1; rowNumber <= 3; rowNumber++) {
			int rowNumberAscii = rowNumber + 48;
			board[0][rowNumber] = (char) rowNumberAscii;
			rowNumberAscii -= 48;
		}
		int row = 1;
		while (row < board.length) {
			for (int column = 1; column < board[row].length; column++) {
				board[row][column] = BLANK;
			}
			row++;
		}
	}

	// prints the board array while also printing the lines to create the box
	public static void printBoard(char[][] board) {
		for (char[] row : board) {
			for (char column : row) {
				System.out.print(ANSI_PURPLE + column + " |");
			}
			System.out.println();
			for (int line = 0; line < 7; line++) {
				System.out.print("--");
			}
			System.out.println();
		}
	}

	// checks if the move is allowed to be made
	public static boolean canMakeMove(char[][] board, int row, int column) {
		boolean makeMove = false;
		if (row <= 3 && row > 0 && column > 0 && column <= 3) {
			if (board[row][column] == BLANK) {
				makeMove = true;
			}
		}
		return makeMove;
	}

	// puts the character into the box at the coordinates specified
	public static void makeMove(char[][] board, int row, int column, char userInput) {
		board[row][column] = userInput;
	}

	// iterates through the array, if there are no ' ' then the board is full and it
	// is a draw
	public static boolean isBoardFull(char[][] board) {
		int count = 0;
		for (char[] row : board) {
			for (char column : row) {
				if (column != BLANK) {
					count++;
				}
			}
		}
		if (count == TOTAL) {
			return true;
		} else {
			return false;
		}
	}

	// checks if the noughts or crosses have made 3 in a row
	public static char winner(char[][] board) {
		char winner = BLANK;
		for (int row = 1; row < board.length; row++) {
			if (board[row][1] == board[row][2] && board[row][1] == board[row][3]) {
				winner = board[row][1];
			}
		}
		for (int column = 1; column < board[1].length; column++) {
			if (board[1][column] == board[2][column] && board[3][column] == board[2][column]) {
				winner = board[1][column];
			}
		}
		if (winner == BLANK && board[1][1] == board[2][2] && board[3][3] == board[1][1]) {
			winner = board[1][1];
		} else if (winner == BLANK && board[1][3] == board[2][2] && board[3][1] == board[2][2]) {
			winner = board[3][1];
		}
		return winner;
	}
}
