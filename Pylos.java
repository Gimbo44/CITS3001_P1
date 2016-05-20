import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pylos {

	public static void main(String[] args) {	
		PylosBoard board = new PylosBoard();
		AiPlayerGreedy1 aiPlayer = new AiPlayerGreedy1();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean winner = false;
		
		while (!winner) {
			try {
				System.out.print("Enter your move in the format: level row column ");
				String input = br.readLine();
				
				int[] values = checkInput(input);
				if (values == null) {
					System.out.println("The entered move in invalid, please try again");
					continue;
				}
				
				int placePieceReturnValue = board.placePiece(1, values[0], values[1], values[2]);
				if (placePieceReturnValue == -1) {
					System.out.println("The move is invalid, please try again");
					continue;
				}
				else if (placePieceReturnValue == 1) {
					System.out.println("You may remove 1 or 2 of your pieces");
					boolean remove = true;
					
					while (remove) {
						System.out.print("Enter the first piece you want to remove in the format: level row column ");
						input = br.readLine();
						
						values = checkInput(input);
						if (values == null) {
							System.out.println("The entered move in invalid, please try again");
							continue;
						}

						
						if (!board.removePiece(1, values[0], values[1], values[2])) {
							System.out.println("The move is invalid, please try again");
							continue;
						}
						remove = false;
					}
					
					remove = true;
					
					while (remove) {
						System.out.print("Enter the second piece you want to remove in the format (leave blank to remove no piece): level row column ");
						input = br.readLine();
						if (input.length() == 0) {
							break;
						}
						
						values = checkInput(input);
						if (values == null) {
							System.out.println("The entered move in invalid, please try again");
							continue;
						}
						
						if (!board.removePiece(1, values[0], values[1], values[2])) {
							System.out.println("The move is invalid, please try again");
							continue;
						}
						remove = false;
					}
				}

				if (board.isWinner() == 1) {
					endGame(true, board);
					return;
				}
				else if(board.isWinner() == 2) {
					endGame(false, board); //I think this is impossible
					return;
				}
				
				int aiMoveReturn = -1;
				while(aiMoveReturn == -1) {
					//keep looping until it finds a valid move. If the ai was smart and not completely random maybe this would be unnecessary as the ai could only select a valid move?
					int[] aiMove = aiPlayer.makeMove(board);
					aiMoveReturn = board.placePiece(2, aiMove[0], aiMove[1], aiMove[2]);
					if (aiMoveReturn != -1) {
						System.out.println("AI placed a piece at level " + aiMove[0] + " row " + (aiMove[1] + 1) + " column " + (aiMove[2] + 1));
					}
				}

				if (aiMoveReturn == 1) { //AI must remove piece or two
					while (true) {					
						int[] aiMove = aiPlayer.makeMove(board);
						if (board.removePiece(2, aiMove[0], aiMove[1], aiMove[2])) {
							System.out.println("AI removed a piece at level " + aiMove[0] + " row " + (aiMove[1] + 1) + " column " + (aiMove[2] + 1));
							break;
						}
					}
					while (true) {
						if (!aiPlayer.removeTwo()) { //the second remove is optional
							break;
						}
						int[] aiMove = aiPlayer.makeMove(board);
						if (board.removePiece(2, aiMove[0], aiMove[1], aiMove[2])) {
							System.out.println("AI removed a piece at level " + aiMove[0] + " row " + (aiMove[1] + 1) + " column " + (aiMove[2] + 1));
							break;
						}
					}
				}

				if (board.isWinner() == 1) {
					endGame(true, board);
					return;
				}
				else if(board.isWinner() == 2) {
					endGame(false, board); //I think this is impossible
					return;
				}
				
				board.printBoard();
				
			} 
			catch (IOException e) {
				//System.in failed?
				//What to do
			}
		}
	}
	
	/**
	 * This method will check the input to ensure the values are valid. It will not check if the move is valid.
	 * @param input Entered string
	 * @return An array of integers [level, row, column] corresponding to the entered values
	 */
	private static int[] checkInput(String input) {
		if (input.split(" ").length != 3) {
			return null;
		}
		
		int level, row, column;
		try {
			level = Integer.valueOf(input.split(" ")[0]);
			row = Integer.valueOf(input.split(" ")[1]);
			column = Integer.valueOf(input.split(" ")[2]);
		} catch (NumberFormatException e) {
			return null;
		}
		
		if (level < 1 || level > 4) {
			return null;
		}
		if (level == 1 && (row < 1 || row > 4 || column < 1 || column > 4)) {
			return null;
		}
		if (level == 2 && (row < 1 || row > 3 || column < 1 || column > 3)) {
			return null;
		}
		if (level == 3 && (row < 1 || row > 2|| column < 1 || column > 2)) {
			return null;
		}
		if (level == 4 && (row != 1 || column != 1)) {
			return null;
		}
		
		return new int[] {level, row - 1, column - 1}; //Row and Column to the user and indexed from 1 (as per unit web page) but the array in 0-indexed in  Java
	}
	
	private static void endGame(boolean win, PylosBoard board) {
		System.out.println();
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("GAME OVER");
		if (win) {
			System.out.println("You win, congratulations!"); 
		}
		else {
			System.out.println("You lose, better luck next time");
		}
		System.out.println("The final board looks like:");
		board.printBoard();
	}
}
