package ConnectN;

import java.io.*;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/*
 * PS: sorry if there is a lot of repetition I didn't have much time to organize my code.
 */
public class ConnectNGame {

	private String playerOne; // Player1
	private String playerTwo; // Player2
	private char board[][]; // Actual board
	private String currentPlayer; // Who's turn it is
	private int n; // n
	private int col; // Column
	private int row; // row
	private int lastMoveCol; // last move for Column
	private int lastMoveRow; // Last move for rows
	private static String fileName; // File name
	private String players[] = new String[2]; // Player names
	private int amountOfPlays = 0; // Amount of plays
	private int amountOfPossiblePlays = 0; // Amount of possible plays
	private char checkerColour; // checker colours

	// Default constructor
	public ConnectNGame() {
		playerOne = "Unknown";
		playerTwo = "Unknown";
		n = 0;
		row = 0;
		col = 0;
		lastMoveCol = 0;
		lastMoveRow = 0;
	}// end of ConnectNGame()

	// Constructor which will set almost every required instance variable
	public ConnectNGame(String name1, String name2, int N, int rows, int columns) {
		playerOne = name1;
		playerTwo = name2;
		n = N;
		row = rows;
		col = columns;
		players[0] = playerOne;
		players[1] = playerTwo;
		initializeBoard(col, row); // Creates the board
		int randomNumber = (int) Math.round(Math.random()); // Randomly selects
															// which player is
															// going to start
		currentPlayer = players[randomNumber];
	}// end of ConnectNGame(String name1, String name2, int N, int rows, int
		// columns)

	// opens and reads the file in order to resume game
	public void openAndRead() {

		fileName = "currentGame.txt";
		File file = new File(fileName);
		Scanner fileReader;

		try {
			fileReader = new Scanner(file);
			fileReader.useDelimiter("~|\r?\n");
			int col;
			int row;
			int n;
			String playerOne;
			String playerTwo;
			String playersTurn;

			col = fileReader.nextInt();
			row = fileReader.nextInt();
			n = fileReader.nextInt();
			playerOne = fileReader.next();
			playerTwo = fileReader.next();
			playersTurn = fileReader.next();
			setColumn(col);
			setRow(row);
			initializeBoard(col, row);
			setPlayerOneName(playerOne);
			setPlayerTwoName(playerTwo);
			setN(n);
			currentPlayer = playersTurn;
			String answer = "";

			while (fileReader.hasNext()) {
				answer += fileReader.next();
			}

			int count = 0;
			while (count < answer.length()) {

				for (int i = 0; i < getColumn(); i++) {
					for (int x = 0; x < getRow(); x++) {
						board[i][x] = answer.charAt(count);
						count++;
					} // for
				} // for
			} // while
		} // try

		catch (FileNotFoundException e) {
			System.out.println("ERROR: " + fileName + " was not found.");
			System.exit(-1);
		} // catch
		catch (IOException e) {
			System.out.println("ERROR opening " + fileName + ": " + e.getMessage());
			System.exit(-2);
		} // catch
	}// end of openAndRead()

	// Method which safes and writes to the file all of the current information
	// on the board
	public void write(ConnectNGame object) {
		File file = new File("currentGame.txt");
		try {
			FileWriter out = new FileWriter(file, true);
			out.write(getColumn() + "\n" + getRow() + "\n" + getN() + "\n" + getPlayerOneName() + "\n"
					+ getPlayerTwoName() + "\n" + getCurrentPlayer() + "\n" + object.writeBoard());
			// out.write(object.writeBoard());
			out.close();
		} // try

		catch (IOException e) {
			System.out.println("ERROR: File " + file + "could not opened: " + e.getMessage());

		} // catch
	}// end of write(object)

	// Sets player 1's name
	public void setPlayerOneName(String name) {
		playerOne = name;
	}// end of setPlayerOneName(String)

	// returns player 1's name
	public String getPlayerOneName() {
		return playerOne;
	}// end of getPlayerOneName()

	// sets player 2's name
	public void setPlayerTwoName(String name) {
		playerTwo = name;
	}// end of setPlayerTwoName(String)

	// returns player two's name
	public String getPlayerTwoName() {
		return playerTwo;
	}// end of getPlayerTwoName()

	// sets the value of n
	public void setN(int nCheckers) {
		n = nCheckers;
	}// end of setN(int)

	// returns n
	public int getN() {
		return n;
	}// end of getN()

	// sets the row
	public void setRow(int rows) {
		row = rows;
	}// end of setRow(int)

	// returns the row
	public int getRow() {
		return row;
	}// End of getRow()

	// sets the column
	public void setColumn(int column) {
		col = column;
	}// end of setColumn(int)

	// returns the column
	public int getColumn() {
		return col;
	}// end of getColumn()

	public char getCheckerColour() {
		return checkerColour;
	}

	// Creates the board
	public void initializeBoard(int column, int rows) {
		board = new char[column][rows];

		for (int col = 0; col < board.length; col++) {
			for (int row = 0; row < board[col].length; row++) {
				board[col][row] = 'E';
				amountOfPossiblePlays++;
			} // for
		} // for
	}// end of initializeBoard()

	// Prints the board
	public void getBoard() {

		for (int col = 0; col < board.length; col++) {
			for (int row = 0; row < board[col].length; row++) {
				System.out.print(board[col][row] + " ");
			} // for
			System.out.println();
		} // for
	}// end of getBoard()

	// Writes the saved board into a string variable in order to write to the
	// file
	public String writeBoard() {
		String boardString = "";
		for (int col = 0; col < getColumn(); col++) {
			for (int row = 0; row < getRow(); row++) {
				boardString += (board[col][row] + "~");
			} // for

			boardString = boardString.substring(0, boardString.length() - 1);
			boardString += "\n";
		} // for
		return boardString;
	}// end of WritebBoard()

	// switches players turn
	public void takeTurn() {

		if (currentPlayer == playerOne) {
			currentPlayer = playerTwo;
			checkerColour = 'Y';
		} // if

		else {
			currentPlayer = playerOne;
			checkerColour = 'R';
		} // else
	} // end of takeTurn()

	// Marks the checker into the board
	public void markSquare(int col, int row) {

		takeTurn();
		if (validateMove(col, row) == true) {
			amountOfPlays++;
			lastMoveCol = col;
			lastMoveRow = row;
			board[convertColIndex(col)][convertRowIndex(row)] = checkerColour;
		}
	} // end of markSquare

	// Undos the players more
	public void undoMove() {
		board[convertColIndex(lastMoveCol)][convertRowIndex(lastMoveRow)] = 'E';
		takeTurn();
		System.out.println("\n" + currentPlayer + " your move has been undone!");
	}// End of undoMove()

	// validates to see if it's a valid move
	public boolean validateMove(int col, int row) {

		if (board[convertColIndex(col - 1)][convertRowIndex(row)] == 'E' && col != 1 && row >= 1) {
			System.err.println("You must place the checker on top of another checker. Please try again");
			takeTurn();
			return false;
		} // if

		else if (board[convertColIndex(col)][convertRowIndex(row)] == 'R'
				|| board[convertColIndex(col)][convertRowIndex(row)] == 'Y') {
			System.err.println("Quit cheating there's already a checker placed there");
			takeTurn();
			return false;
		} // else if

		else
			return true;
	}// end of validateMove()

	// Converts the column index to the right index
	public int convertColIndex(int col) {

		if (col == 1)
			col = board.length - 1;

		else if (col == 2)
			col = board.length - 2;

		else if (col == 3)
			col = board.length - 3;

		else if (col == 4)
			col = board.length - 4;

		else if (col == 5)
			col = board.length - 5;

		else if (col == 6)
			col = board.length - 6;

		else if (col == 7)
			col = board.length - 7;

		else if (col == 8)
			col = board.length - 8;

		else if (col == 9)
			col = board.length - 9;

		else if (col == 10)
			col = board.length - 10;

		else if (col == 11)
			col = board.length - 11;

		else if (col == 12)
			col = board.length - 12;

		return col;
	}// end of convertColIndex()

	// converts the row index
	public int convertRowIndex(int row) {
		return row - 1;
	}// end of convertRowIndex()

	// returns the players turn
	public String getCurrentPlayer() {
		return currentPlayer;
	}// end of getCurrentPlayer()

	public char[][] getBoardArray() {
		return board;
	}

	// Validates Horizontal,Vertical, and diagonals
	public char isGameOver() {

		if (checkColTop() == true)
			return 'W';

		/*
		 * THIS ALGORITHM WORKS FOR THE FRAME BUT NOT CLI FOR SOME REASON
		 * for(int col = 1; col <= board[1].length; col++){ for(int x = 1; x <=
		 * getColumn(); x++){
		 * 
		 * if(checkColBottom(col, x) == true) return
		 * board[convertColIndex(x)][convertRowIndex(col)];
		 * 
		 * 
		 * if(checkColTop(col) == true) return
		 * board[convertColIndex(getColumn())][convertRowIndex(col)]; }//if
		 * }//if
		 */
		for (int col = 1; col <= getColumn(); col++) {
			for (int x = 1; x <= board[1].length; x++) {

				if (checkRowLeft(board[convertColIndex(col)]) == true)
					return board[convertColIndex(1)][convertRowIndex(getRow())];

				if (checkRowRight(board[convertColIndex(col)], x) == true)
					return board[convertColIndex(col)][convertRowIndex(x)];

			} // for
		} // for

		for (int col = 1; col < getRow(); col++) {
			for (int x = 1; x < getColumn(); x++) {
				if (checkLeftToRightDiagonal(x, col) == true)
					return board[convertColIndex(x)][convertRowIndex(col)];
			} // for
		} // for

		for (int col = getRow(); col > 1; col--) {
			for (int x = 1; x < getColumn(); x++) {
				if (checkRightToleftDiagonal(x, col) == true)
					return board[convertColIndex(x)][convertRowIndex(col)];
			} // for
		} // for

		if (amountOfPlays == amountOfPossiblePlays)
			return 'T'; // tie
		else
			return 'C'; // continue playing
	}

	// checks the columns to see if ther's a win.
	public boolean checkColTop() {

		boolean win = false;
		int rCount = 0;
		int yCount = 0;

		for (int row = 1; row <= getRow(); row++) {
			for (int col = getColumn(); col >= 1; col--) {

				if (board[convertColIndex(col)][convertRowIndex(row)] == 'R')
					rCount++;
				else
					rCount = 0;

				if (rCount >= getN()) {
					win = true;
				}
			}
		}

		for (int row = 1; row <= getRow(); row++) {
			for (int col = getColumn(); col >= 1; col--) {

				if (board[convertColIndex(col)][convertRowIndex(row)] == 'Y')
					yCount++;
				else
					yCount = 0;

				if (yCount >= getN()) {
					win = true;
				}
			}
		}
		return win;
	}// end of checkColTop()

	// Checks left to right diagnonal
	public boolean checkLeftToRightDiagonal(int colCount, int count) {
		int start = board[convertColIndex(colCount)][convertRowIndex(count)];
		int nCount = 0;

		if (start == 'E')
			return false;

		for (int x = colCount; x < getColumn(); x++) {
			for (int i = 1; i <= getRow(); i++) {
				if (board[convertColIndex(x)][convertRowIndex(i)] == start) {
					nCount++;
					x++;
				} // if

				else
					nCount = 0;

				if (nCount == getN())
					return true;
			} // for
		} // for
		return false;
	} // end of checkLeftToRightDiagonal(char)

	// Checks the right to left diagnonal
	public boolean checkRightToleftDiagonal(int colCount, int count) {
		int start = board[convertColIndex(colCount)][convertRowIndex(count)];
		int nCount = 0;

		if (start == 'E')
			return false;

		for (int x = colCount; x < getColumn(); x++) {
			for (int i = getRow(); i >= 1; i--) {
				if (board[convertColIndex(x)][convertRowIndex(i)] == start) {
					nCount++;
					x++;
				} // if

				else
					nCount = 0;

				if (nCount == getN())
					return true;
			} // for
		} // for
		return false;
	} // end of checkRightToleftDiagonal()

	// This was my old algorithm for checking th col but it isn't as good a the
	// one im using. This algorith works perfectly for the frame but CLI it
	// works 4/5 times
	public boolean checkColBottom(int col, int count) {

		int start = board[convertColIndex(count)][convertRowIndex(col)];
		int nCount = 0;

		if (count == 2)
			nCount = 1;

		if (count == 3)
			nCount = 2;

		if (count == 4)
			nCount = 3;

		if (count == 5)
			nCount = 4;

		if (count == 6)
			nCount = 5;

		if (count == 7)
			nCount = 6;

		if (count == 8)
			nCount = 7;

		if (count == 9)
			nCount = 8;

		if (count == 10)
			nCount = 9;

		if (count == 11)
			nCount = 10;

		if (count == 12)
			nCount = 11;

		if (start == 'E')
			return false;

		for (int i = count; i <= n + nCount; i++) {
			if (board[convertColIndex(i)][convertRowIndex(col)] != start) {
				return false;
			} // if
		} // for
		return true;
	}// end of checkColBottom()

	// checks the column from the top
	public boolean checkColTop(int col) {

		int end = board[convertColIndex(getColumn())][convertRowIndex(col)];
		int amountOfColumn = getColumn();
		if (end == 'E')
			return false;

		for (int i = amountOfColumn; i < n; i--) {
			if (board[convertColIndex(i)][convertRowIndex(col)] != end) {
				return false;
			} // if
		} // for
		return true;
	}// end of checkColTop()

	// I was going to use this method in order to reduce repetition, but I
	// lacked time.
	public int nCountAdjustment(int count) {
		int nCount = count;

		if (count == 2)
			nCount = 1;

		if (count == 3)
			nCount = 2;

		if (count == 4)
			nCount = 3;

		if (count == 5)
			nCount = 4;

		if (count == 6)
			nCount = 5;

		if (count == 7)
			nCount = 6;

		if (count == 8)
			nCount = 7;

		if (count == 9)
			nCount = 8;

		if (count == 10)
			nCount = 9;

		if (count == 11)
			nCount = 10;

		if (count == 12)
			nCount = 11;

		return nCount;
	}// end of nCountAdjustment()

	// Checks the row form thr right
	public boolean checkRowRight(char[] boardRow, int count) {

		int start = boardRow[convertRowIndex(count)];

		if (start == 'E')
			return false;

		int nCount = 0;

		if (count == 2)
			nCount = 1;

		if (count == 3)
			nCount = 2;

		if (count == 4)
			nCount = 3;

		if (count == 5)
			nCount = 4;

		if (count == 6)
			nCount = 5;

		if (count == 7)
			nCount = 6;

		if (count == 8)
			nCount = 7;

		if (count == 9)
			nCount = 8;

		if (count == 10)
			nCount = 9;

		if (count == 11)
			nCount = 10;

		if (count == 12)
			nCount = 11;

		for (int i = count; i <= n + nCount; i++) {
			if (boardRow.length - count < n)
				return false;

			if (boardRow[convertRowIndex(i)] != start) {
				return false;
			} // if
		} // for

		return true;
	}// end of checkRowRight()

	// Checks the row from the left
	public boolean checkRowLeft(char[] boardRow) {

		int end = boardRow[convertRowIndex(getRow())];

		if (end == 'E')
			return false;

		int i = getRow();
		int count = 1;
		while (count <= n) {
			if (boardRow[convertRowIndex(i)] != end)
				return false;
			i--;
			count++;
		} // while
		return true;
	}// end of checkRowLeft()

	// Validates input from ConnectNCLI class
	public boolean verifyMove(String answer) {

		if (answer.indexOf(",") == -1) {
			System.err.println("Please make sure you have a comma with no spaces, and re enter");
			return false;
		} // if

		if (answer.length() > 4 || answer.length() < 3) {
			System.err.println("input must follow this format row,column. Don't put any spaces ex: 1,11\n");
			return false;
		} // if

		int colSelected = Integer.parseInt(answer.substring(0, 1));
		int rowSelected = Integer.parseInt(answer.substring(2));

		if (colSelected > getColumn() || colSelected < 1) {
			System.err.println("This column doesn't exist.");
			return false;
		} // if

		if (rowSelected > getRow() || rowSelected < 1) {
			System.err.println("This row doesn't exist.");
			return false;
		} // if

		return true;
	}// end of verifyMove()
}// End of ConnectNGame class
