package ConnectN;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ConnectNFrame extends JFrame implements ActionListener, SwingConstants { // Class

	private JMenuBar menuBar = new JMenuBar(); // Creating menu bar
	private JMenu menu = new JMenu("Menu"); // Creating menu
	private JMenuItem newGame = new JMenuItem("New Game"); // Creating new game
															// menu
	private JMenuItem saveGame = new JMenuItem("Save Game"); // Creating save
																// game menu
	private JMenuItem undoMove = new JMenuItem("Undo Move"); // Creating undo
																// move menu
	private JMenuItem exit = new JMenuItem("Exit"); // Creating exit menu
	private JMenu help = new JMenu("Help"); // Creating Help menu
	private JMenuItem instructions = new JMenuItem("Instructions"); // Creating
																	// instruction
																	// menu
	private JMenuItem about = new JMenuItem("About"); // Creating about menu
	private JLabel whoseTurn; // Whose turn label
	private JPanel connectNPanel = new JPanel(); // Creating panel
	private char currentPlayer; // Current player char
	private ConnectNGame board; // Creating ConnectNGame object
	private JButton connectNButton[][]; // Creating an array of buttons
	private JPanel contentPane; // Creating contentpane panel
	private int lastMoveCol; // last move column
	private int lastMoveRow; // last move Row
	private int amountOfPlays; // number of plays
	private int amountOfPossiblePlays = 0; // total plays
	private int plays;
	private char board1[][]; // creating an array which will store char values
								// for the play(not necessary)

	// ConnectNFrame constructor which sets everything from the
	// ConnectNMenuFrame information
	public ConnectNFrame(String name1, String name2, int n, int row, int col, boolean bValue) {
		setJMenuBar(menuBar);
		menuBar.add(menu);
		menu.add(newGame);
		menu.add(saveGame);
		menu.add(undoMove);
		menu.add(exit);
		menuBar.add(help);
		help.add(instructions);
		help.add(about);
		this.setSize(new Dimension(400, 300));
		this.setTitle("Connect N");
		board = new ConnectNGame(name1, name2, n, row, col);
		connectNButton = new JButton[board.getColumn()][board.getRow()];
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		whoseTurn = new JLabel(board.getCurrentPlayer() + " starts");
		whoseTurn.setHorizontalAlignment(CENTER);
		whoseTurn.setFont(new Font("label", Font.BOLD, 14));
		this.getContentPane().add(whoseTurn, "North");
		initializeBoard();
		this.getContentPane().add(connectNPanel, "Center");
		newGame.addActionListener(this);
		saveGame.addActionListener(this);
		undoMove.addActionListener(this);
		exit.addActionListener(this);
		board1 = new char[col][row];
		about.addActionListener(this);
		instructions.addActionListener(this);
		if (bValue == true) // Resumes the game is bValue = true
			openAndRead();
	}// ConnectNFrame()

	// Creates the board and buttons
	private void initializeBoard() {
		board.initializeBoard(board.getColumn(), board.getRow()); // this
		connectNPanel.setLayout(new GridLayout(board.getColumn(), board.getRow()));
		amountOfPossiblePlays = board.getColumn() * board.getRow();
		for (int col = 0; col < board.getColumn(); ++col)
			for (int row = 0; row < board.getRow(); ++row) {
				connectNButton[col][row] = new JButton("E");
				connectNButton[col][row].setFont(new Font("board", Font.BOLD, 24));
				//amountOfPossiblePlays++;
				
				connectNPanel.add(connectNButton[col][row]);
				connectNButton[col][row].addActionListener(this);
			} // for
	}// initializeBoard()

	// Every time a move has been done this method mark it and call methods to
	// verify if there's a win
	private void markSquare(int col, int row) {
		if (validateMove(col, row) == true) {
			board.markSquare(convertColIndex(col), convertRowIndex(row)); // this
			whoseTurn.setText("It's " + board.getCurrentPlayer() + "' turn");
			// board.takeTurn();

			lastMoveCol = col;
			lastMoveRow = row;
			amountOfPlays++;
			connectNButton[col][row].setText(String.valueOf(board.getCheckerColour()));
			connectNButton[col][row].setEnabled(false);

			if (board.getCheckerColour() == 'Y') {
				connectNButton[col][row].setBackground(Color.YELLOW);
			} // if

			if (board.getCheckerColour() == 'R') {
				connectNButton[col][row].setBackground(Color.RED);
			} // if

			play(col, row, board.getCheckerColour());

			if (isGameOver() == 'T') {
				JOptionPane.showMessageDialog(this, "It's a tie", "tie", JOptionPane.ERROR_MESSAGE);
				newGame();
			} // if
			if (isGameOver() == 'W') {
				JOptionPane.showMessageDialog(this, board.getCheckerColour() + " won", "won",
						JOptionPane.ERROR_MESSAGE);
				newGame();
			} // if
		} // if
	}// markSquare()

	// validates verifies if theres a checker on top of another. Basically
	// verifies if the move is valid
	public boolean validateMove(int col, int row) {
		if (col != board.getColumn() - 1) {
			if (connectNButton[col + 1][row].getText().equals("E")) {
				JOptionPane.showMessageDialog(this, "You must place a checker on top of another", "Can't palce here",
						JOptionPane.ERROR_MESSAGE);
				return false;
			} // if
		} // if
		return true;
	}// end of validateMove()

	// Undo's the previous move
	public void undoMove() {
		if(plays == 0)
			JOptionPane.showMessageDialog(this, "You must do a move first", "Can't undo",
					JOptionPane.ERROR_MESSAGE);
		else{
		connectNButton[lastMoveCol][lastMoveRow].setText("E");
		connectNButton[lastMoveCol][lastMoveRow].setEnabled(true);
		connectNButton[lastMoveCol][lastMoveRow].setBackground(null);
		whoseTurn.setText("It's " + board.getCurrentPlayer() + "' turn");
		board.undoMove();
		JOptionPane.showMessageDialog(this, board.getCurrentPlayer() + " Your move has been undone", "Undo move",
				JOptionPane.ERROR_MESSAGE);
		whoseTurn.setText("It's " + board.getCurrentPlayer() + "' turn");
		}
	}// End of undoMove()

	// Verifies the amount of plays done.
	public void play(int col, int row, char turn) {
		++plays;
		board1[col][row] = turn; // This is useless. I didn't want to delete it
									// because I wanted to do something with it,
									// but didn't have the time for it
	} // play()

	// This method calls the validation methods to see if there was a win or a
	// tie
	public char isGameOver() {
		char boardFromGame[][] = board.getBoardArray();

		/*
		 * for(int row = 1; row <= board.getRow(); row++){ for(int col = 1; col
		 * < board.getColumn(); col++){ if(board.checkColBottom(row, col) ==
		 * true) return 'W'; } }
		 */

		if (board.checkColTop() == true)
			return 'W';

		for (int col = 1; col < board.getColumn(); col++) {
			for (int x = 1; x <= board.getRow(); x++) {
				if (board.checkRowLeft(boardFromGame[col]) == true)
					return 'W';
				if (board.checkRowRight(boardFromGame[col], x) == true)
					return 'W';
			}
		}

		for (int col = 1; col < board.getRow(); col++) {
			for (int x = 1; x < board.getColumn(); x++) {
				if (board.checkLeftToRightDiagonal(x, col) == true)
					return 'W';
			}
		}

		for (int col = board.getRow(); col > 1; col--) {
			for (int x = 1; x < board.getColumn(); x++) {
				if (board.checkRightToleftDiagonal(x, col) == true)
					return 'W';
			}
		}

		if (amountOfPlays  == amountOfPossiblePlays)
			return 'T'; // tie
		else
			return 'C'; // continue playing

	} // isGameOver()

	@Override
	public void actionPerformed(ActionEvent arg0) {

		// shows the creator of the program and school information
		if (arg0.getSource() == about) {
			JOptionPane.showMessageDialog(this, new ConnectNMenuFrame_AboutPanel(), "About", JOptionPane.PLAIN_MESSAGE);
		} // if

		// Shows instructions
		if (arg0.getSource() == instructions) {
			JOptionPane.showMessageDialog(this, new ConnectNMenuFrame_InstructionPanel(), "Instructions",
					JOptionPane.PLAIN_MESSAGE);
		} // if

		// saves the game
		if (arg0.getSource() == saveGame) {
			write(board);
			JOptionPane.showMessageDialog(this, "Your board has been saved", "Saved", JOptionPane.ERROR_MESSAGE);
		} // if

		// exits the game
		if (arg0.getSource() == exit)
			System.exit(0);

		// creates a new board
		if (arg0.getSource() == newGame) {
			newGame();
			board.takeTurn();
			amountOfPlays = 0;
			whoseTurn.setText("It's " + board.getCurrentPlayer() + "' turn");
		} // if

		// adds action performed/listener to every button
		for (int col = 0; col < board.getColumn(); col++) {
			for (int row = 0; row < board.getRow(); row++) {
				if (arg0.getSource() == connectNButton[col][row]) {
					markSquare(col, row);
					return;
				} // if
			} // for
		} // for

		// undo's the last move
		if (arg0.getSource() == undoMove)
			undoMove();
	}// actionPerformed()

	// Creates a new board
	public void newGame() {
		board.initializeBoard(board.getColumn(), board.getRow()); // thiss
		plays = 0;
		amountOfPlays = 0;
		for (int row = 0; row < board1.length; row++) {
			for (int col = 0; col < board1[row].length; col++) {
				board1[row][col] = ' ';
				connectNButton[row][col].setText("E");
				connectNButton[row][col].setEnabled(true);
				connectNButton[row][col].setBackground(null);
			} // for
		} // for
	} // newGame()

	// convert column index
	public int convertColIndex(int col) {

		if (col == board.getColumn() - 1)
			col = 1;

		else if (col == board.getColumn() - 2)
			col = 2;

		else if (col == board.getColumn() - 3)
			col = 3;

		else if (col == board.getColumn() - 4)
			col = 4;

		else if (col == board.getColumn() - 5)
			col = 5;

		else if (col == board.getColumn() - 6)
			col = 6;

		else if (col == board.getColumn() - 7)
			col = 7;

		else if (col == board.getColumn() - 8)
			col = 8;

		else if (col == board.getColumn() - 9)
			col = 9;

		else if (col == board.getColumn() - 10)
			col = 10;

		else if (col == board.getColumn() - 11)
			col = 11;

		else if (col == board.getColumn() - 12)
			col = 12;

		return col;
	}// end of convertColIndex()

	// converts row index
	public int convertRowIndex(int row) {
		return row + 1;
	}// end of convertRowIndex()

	// opens the file and resumes the game
	public void openAndRead() {

		String fileName = "currentGame.txt";
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
			board.setColumn(col);
			board.setRow(row);
			board.initializeBoard(col, row);
			board.setPlayerOneName(playerOne);
			board.setPlayerTwoName(playerTwo);
			board.setN(n);
			currentPlayer = playersTurn.charAt(0);
			String answer = "";

			while (fileReader.hasNext()) {
				answer += fileReader.next();
			} // while

			char boardFromGame[][] = board.getBoardArray();
			int count = 0;
			while (count < answer.length()) {

				for (int i = 0; i < board.getColumn(); i++) {
					for (int x = 0; x < board.getRow(); x++) {
						// board1[i][x] = answer.charAt(count);
						boardFromGame[i][x] = answer.charAt(count);
						connectNButton[i][x].setText(answer.valueOf(answer.charAt(count)));
						if (connectNButton[i][x].getText().equals("Y") || connectNButton[i][x].getText().equals("R")) {
							connectNButton[i][x].setEnabled(false);

							if (connectNButton[i][x].getText().equals("Y"))
								connectNButton[i][x].setBackground(Color.YELLOW);

							else
								connectNButton[i][x].setBackground(Color.RED);
						} // if
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
			out.write(board.getColumn() + "\n" + board.getRow() + "\n" + board.getN() + "\n" + board.getPlayerOneName()
					+ "\n" + board.getPlayerTwoName() + "\n" + board.getCurrentPlayer() + "\n" + writeBoard());
			// out.write(object.writeBoard());
			out.close();
		} // try

		catch (IOException e) {
			System.out.println("ERROR: File " + file + "could not opened: " + e.getMessage());

		} // catch
	}// end of write(object)

	// writes the board in a specific format in order to save it properly
	public String writeBoard() {
		String boardString = "";
		for (int col = 0; col < board.getColumn(); col++) {
			for (int row = 0; row < board.getRow(); row++) {

				boardString += (connectNButton[col][row].getText() + "~");
			} // for

			boardString = boardString.substring(0, boardString.length() - 1);
			boardString += "\n";
		} // for
		return boardString;
	}// end of WritebBoard()

	public static void main(String[] args) {
	}// main
}// ConectNFrame
