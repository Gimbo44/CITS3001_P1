public class PylosBoard {
	
	private int[][] level1; // 0 for no piece, 1 for Human, 2 for AI
	private int[][] level2;
	private int[][] level3;
	private int level4; // a one by one array is pointless, just use an int. Also this place will never be occupied
	private int playerPieces;
	private int aiPieces;

	/**
	 * Create a new Pylos Board
	 */
	public PylosBoard() {
		level1 = new int[4][4];
		level2 = new int[3][3];
		level3 = new int[2][2];
		level4 = 0;
		playerPieces = 15;
		aiPieces = 15;
	}
	
	/**
	 * Place the piece on the board. Parameters are not checked as valid as they are checked in the Pylos.checkInput method
	 * @param piece Which player is making the move
	 * @param level Level on the board
	 * @param row Row on the level
	 * @param column Column on the level
	 * @return -1 if the move is invalid, 1 if a piece was placed and the player may now remove a piece, or 0 otherwise
	 */
	public int placePiece(int piece, int level, int row, int column) {	
		//check move is valid
		if (!validMove(level, row, column)) {
			return -1;
		}

		//place the piece
		switch (level){
			case 1:
				level1[row][column] = piece;
				break;
			case 2:
				level2[row][column] = piece;
				break;
			case 3:
				level3[row][column] = piece;
				break;
			case 4:
				level4 = piece;
				break;
		}
		
		recordPieces(piece, true);
				
		if (mayRemovePiece(piece, level, row, column)) {
			return 1;
		}
		
		return 0;
	}
	
	/**
	 * Checks if a move is legal or not. Checks the place is not already occupied, 
	 * and checks if the 4 places immediately below it are occupied for levels 2-4
	 * @param level Level on the board
	 * @param row Row on the level
	 * @param column Column on the level
	 * @return true if the move is valid, false if not
	 */
	public boolean validMove(int level, int row, int column) {
		//TODO when raising a piece, a player may remove one already on the board to do so... Not implemented yet
		
		switch (level){
		case 1:
			//Is place is already occupied
			if (level1[row][column] != 0) { 
				return false;
			}
			break;
		case 2:
			//Is place is already occupied
			if (level2[row][column] != 0) { 
				return false;
			}
			//Is there 4 pieces under it 
			if (level1[row][column] == 0 || level1[row + 1][column] == 0 || level1[row][column + 1] == 0 || level1[row + 1][column + 1] == 0 ) {
				return false;
			}
			break;
	
		case 3:
			//Is place is already occupied
			if (level3[row][column] != 0) { 
				return false;
			}
			//Is there 4 pieces under it 
			if (level2[row][column] == 0 || level2[row + 1][column] == 0 || level2[row][column + 1] == 0 || level2[row + 1][column + 1] == 0 ) {
				return false;
			}
			break;
		case 4:
			//Is place is already occupied
			if (level4 != 0) { 
				return false;
			}
			//Is there 4 pieces under it 
			if (level3[row][column] == 0 || level3[row + 1][column] == 0 || level3[row][column + 1] == 0 || level3[row + 1][column + 1] == 0 ) {
				return false;
			}
			break;
		}
		return true;
	}

	/**
	 * Removes a piece from the board
	 * @param level Level on the board
	 * @param row Row on the level
	 * @param column Column on the level
	 * @return true if piece removed, false if not
	 */
	public boolean removePiece(int piece, int level, int row, int column) {
		switch (level){
			case 1:
				//Check for pieces above //I'm sure it could be done lots better without catching exceptions, but it works
				try {
					if (level2[row][column] != 0) {
						return false;
					}
				} catch (ArrayIndexOutOfBoundsException e) {/*Ignore*/}
				try {
					if (level2[row - 1][column] != 0) {
						return false;
					}
				} catch (ArrayIndexOutOfBoundsException e) {/*Ignore*/}
				try {
					if (level2[row][column - 1] != 0) {
						return false;
					}
				} catch (ArrayIndexOutOfBoundsException e) {/*Ignore*/}
				try {
					if (level2[row - 1][column - 1] != 0) {
						return false;
					}
				} catch (ArrayIndexOutOfBoundsException e) {/*Ignore*/}
				
				if (level1[row][column] == piece) { //checks player is removing their own piece
					level1[row][column] = 0;
					recordPieces(piece, false);
					return true;
				}
				break;
			case 2:
				//Check for pieces above //I'm sure it could be done lots better without catching exceptions, but it works
				try {
					if (level3[row][column] != 0) {
						return false;
					}
				} catch (ArrayIndexOutOfBoundsException e) {/*Ignore*/}
				try {
					if (level3[row - 1][column] != 0) {
						return false;
					}
				} catch (ArrayIndexOutOfBoundsException e) {/*Ignore*/}
				try {
					if (level3[row][column - 1] != 0) {
						return false;
					}
				} catch (ArrayIndexOutOfBoundsException e) {/*Ignore*/}
				try {
					if (level3[row - 1][column - 1] != 0) {
						return false;
					}
				} catch (ArrayIndexOutOfBoundsException e) {/*Ignore*/}
				
				if (level2[row][column] == piece) { //checks player is removing their own piece
					level2[row][column] = 0;
					recordPieces(piece, false);
					return true;
				}
				break;
			case 3:
				if (level4 != 0) {
					//Check for pieces above
					return false;
				}
				if (level3[row][column] == piece) { //checks player is removing their own piece
					level3[row][column] = 0;
					recordPieces(piece, false);
					return true;
				}
				break;
			case 4: //impossible
				if (level4 == piece) { //checks player is removing their own piece
					level4 = 0;
					recordPieces(piece, false);
					return true;
				}
				break;
		}
		return false;
	}
	
	/**
	 * Checks if a player may remove a piece
	 * @param piece Player whose turn it is
	 * @param level Level on the board
	 * @param row Row on the level
	 * @param column Column on the level
	 * @return true if the player may remove a piece, false if not
	 */
	public boolean mayRemovePiece(int piece, int level, int row, int column) {
		switch (level){
			case 1:
				boolean removeRow = true; 
				boolean removeCol = true;
				
				//Check for a row of 4
				for (int i : new int[] {0, 1, 2, 3}) {
					if (level1[row][i] != piece) {
						removeRow =  false;
					}
				}
				//Check for a column of 4
				for (int i : new int[] {0, 1, 2, 3}) {
					if (level1[i][column] != piece) {
						removeCol =  false;
					}
				}
				
				//Check for a square
				//I think it would be better to check for boundary cases rather than just catching and ignoring exceptions, but this works
				try {
					if (level1[row][column] == piece && level1[row][column + 1] == piece && level1[row + 1][column] == piece && level1[row + 1][column + 1] == piece) {
						return true;
					}
				} 
				catch (ArrayIndexOutOfBoundsException e) {}
				try {
					if (level1[row][column] == piece && level1[row][column - 1] == piece && level1[row + 1][column] == piece && level1[row + 1][column - 1] == piece) {
						return true;
					}
				} 
				catch (ArrayIndexOutOfBoundsException e) {}
				try {
					if (level1[row][column] == piece && level1[row][column + 1] == piece && level1[row - 1][column] == piece && level1[row - 1][column + 1] == piece) {
						return true;
					}
				} 
				catch (ArrayIndexOutOfBoundsException e) {}
				try {
					if (level1[row][column] == piece && level1[row][column - 1] == piece && level1[row - 1][column] == piece && level1[row - 1][column - 1] == piece) {
						return true;
					}
				} 
				catch (ArrayIndexOutOfBoundsException e) {}
				
				return removeRow || removeCol;
			case 2:
				removeRow = true; 
				removeCol = true;
				
				//Check for a row of 3
				for (int i : new int[] {0, 1, 2}) {
					if (level2[row][i] != piece) {
						removeRow =  false;
					}
				}
				//Check for a column of 3
				for (int i : new int[] {0, 1, 2}) {
					if (level2[i][column] != piece) {
						removeCol =  false;
					}
				}
				
				//Check for a square
				//I think it would be better to check for boundary cases rather than just catching and ignoring exceptions, but this works
				try {
					if (level2[row][column] == piece && level2[row][column + 1] == piece && level2[row + 1][column] == piece && level2[row + 1][column + 1] == piece) {
						return true;
					}
				} 
				catch (ArrayIndexOutOfBoundsException e) {/*Ignore*/}
				try {
					if (level2[row][column] == piece && level2[row][column - 1] == piece && level2[row + 1][column] == piece && level2[row + 1][column - 1] == piece) {
						return true;
					}
				} 
				catch (ArrayIndexOutOfBoundsException e) {/*Ignore*/}
				try {
					if (level2[row][column] == piece && level2[row][column + 1] == piece && level2[row - 1][column] == piece && level2[row - 1][column + 1] == piece) {
						return true;
					}
				} 
				catch (ArrayIndexOutOfBoundsException e) {/*Ignore*/}
				try {
					if (level2[row][column] == piece && level2[row][column - 1] == piece && level2[row - 1][column] == piece && level2[row - 1][column - 1] == piece) {
						return true;
					}
				} 
				catch (ArrayIndexOutOfBoundsException e) {/*Ignore*/}
				
				return removeRow || removeCol;
			case 3:
				//Square on level 3
				//Don't need to check if the currently added piece is in it, it has to be
				if (level3[0][0] == piece && level3[0][1] == piece && level3[1][0] == piece && level3[1][1] == piece) {
					return true;
				}
				return false;
			case 4:
				return false;
		}
		return false;
	}
	
	/**
	 * Record the number of pieces a player has remaining
	 * @param piece Player who just placed or removed a piece
	 * @param placed true if a piece was placed, false if a piece was removed
	 */
	public void recordPieces(int piece, boolean placed) {
		if (piece == 1) {
			if (placed) {
				playerPieces--;
			}
			else {
				playerPieces++;
			}
		}
		else {
			if (placed) {
				aiPieces--;
			}
			else {
				aiPieces++;
			}
		}
	}
	
	/**
	 * Checks if there is a winner
	 * @return 1 if the player wins, 2 if the ai wins
	 */
	public int isWinner() {
		//There is no point in checking the level4 place, someone has to run out of pieces to make it available
		if (playerPieces == 0) {
			return 2; //ai wins
		}
		else if (aiPieces == 0){
			return 1; //player wins
		}
		return 0;
	}
	
	/**
	 * Prints the board to stdout
	 */
	public void printBoard() {
		System.out.println("Level 1:");
		System.out.println(level1[0][0] + " " + level1[0][1] + " " + level1[0][2] + " " + level1[0][3]);
		System.out.println(level1[1][0] + " " + level1[1][1] + " " + level1[1][2] + " " + level1[1][3]);
		System.out.println(level1[2][0] + " " + level1[2][1] + " " + level1[2][2] + " " + level1[2][3]);
		System.out.println(level1[3][0] + " " + level1[3][1] + " " + level1[3][2] + " " + level1[3][3]);
		
		System.out.println("Level 2:");
		System.out.println(level2[0][0] + " " + level2[0][1] + " " + level2[0][2]);
		System.out.println(level2[1][0] + " " + level2[1][1] + " " + level2[1][2]);
		System.out.println(level2[2][0] + " " + level2[2][1] + " " + level2[2][2]);
		
		System.out.println("Level 3:");
		System.out.println(level3[0][0] + " " + level3[0][1]);
		System.out.println(level3[1][0] + " " + level3[1][1]);
		
		System.out.println("Level 4:");
		System.out.println(level4);
		System.out.println();
	}
}
