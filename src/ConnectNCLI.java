package ConnectN;
import java.util.Scanner;
/*
 * Joseph Bitar
 * Sandra Stark
 * Programming 2
 * The goal of this program is to have the user play a friendly game of connect N.
 * The user will have full control of how many columns and rows the board will have.
 */
public class ConnectNCLI {
	
	private static ConnectNGame game;			//Creating static instance variable which is an object

	
	//statis method which displays the board
	public static void displayBoard(){
		System.out.println();
		game.getBoard();
	}//End displayBoard()
	
	
	
	//Main method promp for information which will later be used to create a board and to set the row columns
	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);		
		char again= 'Y';
		
		
		while(again == 'Y'){
		
		System.out.println("Welcome to Heritage Connect-N");
		System.out.println("Please enter N to start a new game or R to resue the game stored in currentGame.txt");
		String firstReply = keyboard.next();
		
		
		while(!(firstReply.toUpperCase().equals("N") || firstReply.toUpperCase().equals("R"))){
			System.err.println("Invalid input, you must either start a new game 'N' or resume 'R'");
			firstReply = keyboard.next();
		}// end of while
		
		if(firstReply.toUpperCase().equals("R")){
			game = new ConnectNGame();
			game.openAndRead();
			
		}// end of if
			
		if(!(firstReply.toUpperCase().equals("R"))){
		System.out.println("Enter the number of col on the game board:");
		int col = keyboard.nextInt();
		while(col < 4 || col > 12){
			System.err.println("Invalid column, column must be between 4 and 12");
			col = keyboard.nextInt();
		}// end of while
		
		
		System.out.println("Enter the number of rows on the game board:");
		int row = keyboard.nextInt();
		while(row < 4 || row > 12){
			System.err.println("Invalid row, row must be between 4 and 12");
			row = keyboard.nextInt();
		}// end of while
		
		
		System.out.println("Enter the value for N, the number of checkers in a row for a win:");
		int n = keyboard.nextInt();
		while(n < 3 || n > 8){
			System.err.println("Invalid n, n must be between 3 and 8");
			n = keyboard.nextInt();
		} // end of while
		
		
		System.out.println("Enter the name of Player 1, yellow:");
		String player1 = keyboard.next();
		
		System.out.println("Enter the name of Player 2, red:");
		String player2 = keyboard.next();
		
		game = new ConnectNGame(player1, player2, n, row, col);
		} // end of if
		
		System.out.println("Type Q at any time to exit the game, S to save the game or U to undo your last move");		
		displayBoard();
		
		
		while(game.isGameOver() == 'C'){
			
			System.out.println(game.getCurrentPlayer() + " enter square number(row, column) of your move");
			String answer = keyboard.next();
		
			if(answer.toUpperCase().charAt(0) == 'Q' || answer.toUpperCase().charAt(0) == 'S' || answer.toUpperCase().charAt(0) == 'U'){	
				
				if(answer.toUpperCase().charAt(0) == 'Q'){
					System.out.println("Thank you for playing good bye");
					System.exit(-1);
				}// end of if	
				
				if(answer.toUpperCase().charAt(0) == 'S'){
					game.write(game);
					System.out.println("Board has been saved to the file.\n");
				}// end of if
				
				if(answer.toUpperCase().charAt(0) == 'U'){
					game.undoMove();
					displayBoard();
				}// end of if
				
				System.out.println(game.getCurrentPlayer() + " enter square number(row, column) of your move");
					answer = keyboard.next();
					
			} // end of if
			
			while(game.verifyMove(answer) == false){
				System.out.println(game.getCurrentPlayer() + " enter square number(row, column) of your move");
				answer = keyboard.next();
			}// end of while
			
			int colSelected = Integer.parseInt(answer.substring(0, 1));
			int rowSelected = Integer.parseInt(answer.substring(2));
			
			game.markSquare(colSelected, rowSelected);
			displayBoard();
		}// end of while
		
		if(game.isGameOver() == 'T')
			System.out.println("It's a tie!!!!!!");
		
		else {
			game.takeTurn();
			System.out.println(game.getCurrentPlayer() + " has won the game!");
			}// end of else
		System.out.println("Would you like to play again? Press 'Y' if so");
		again = keyboard.next().toUpperCase().charAt(0);
		}// end of while
		System.out.println("Bye bye!!");
	}// main
}//ConnectNCli class
