import java.util.Random;

public class AiPlayerGreedy2 {
	
	private Random random;
	
	//This class is for the ai player, I'm not sure if the code will be big enough to warrant its 
	//own class, but I think it might be for the best. At the moment it is pretty empty..
	public AiPlayerGreedy2() {
		random = new Random();
	}
	
//RANDOM
	/*
	public int[] makeMove(boolean remove, PylosBoard board) { //parameters are meaningless for a random player, but are there so it can be swapped in
		int level = random.nextInt(4) + 1; //random.nextInt(max - min + 1) + 1  https://stackoverflow.com/a/20389923
		return new int[] {level, random.nextInt(5 - level), random.nextInt(5 - level)};
	}
	
	public boolean removeTwo() {
		if (random.nextInt(2) == 1) {
			return true;
		}
		return false;
	}*/
	
//Greedy - always try to remove pieces
	public int[] makeMove(boolean remove, PylosBoard board) {
		if (remove) {
			//systematically look for pieces to remove, does nothing clever here. problem, what if piece can not be removed?
			for (int i : new int[] {0, 1}) {
				for (int j : new int[] {0, 1}) {
					//check nothing above
					if (board.getLevel4() != 0) {
						continue;
					}
					
					//check correct player
					if (board.getLevel3()[i][j] == 2) {
						return new int[] {3, i, j};
					}
				}
			}
			for (int i : new int[] {0, 1, 2}) {
				for (int j : new int[] {0, 1, 2}) {
					//check nothing above
					try {
						if (board.getLevel3()[i][j] != 0) {
							continue;
						}
					} catch (ArrayIndexOutOfBoundsException e) {/*Ignore*/}
					try {
						if (board.getLevel3()[i - 1][j] != 0) {
							continue;
						}
					} catch (ArrayIndexOutOfBoundsException e) {/*Ignore*/}
					try {
						if (board.getLevel3()[i][j - 1] != 0) {
							continue;
						}
					} catch (ArrayIndexOutOfBoundsException e) {/*Ignore*/}
					try {
						if (board.getLevel3()[i - 1][j - 1] != 0) {
							continue;
						}
					} catch (ArrayIndexOutOfBoundsException e) {/*Ignore*/}

					//check correct player
					if (board.getLevel2()[i][j] == 2) {
						return new int[] {2, i, j};
					}
				}
			}
			for (int i : new int[] {0, 1, 2, 3}) {
				for (int j : new int[] {0, 1, 2, 3}) {
					//check nothing above
					try {
						if (board.getLevel2()[i][j] != 0) {
							continue;
						}
					} catch (ArrayIndexOutOfBoundsException e) {/*Ignore*/}
					try {
						if (board.getLevel2()[i - 1][j] != 0) {
							continue;
						}
					} catch (ArrayIndexOutOfBoundsException e) {/*Ignore*/}
					try {
						if (board.getLevel2()[i][j - 1] != 0) {
							continue;
						}
					} catch (ArrayIndexOutOfBoundsException e) {/*Ignore*/}
					try {
						if (board.getLevel2()[i - 1][j - 1] != 0) {
							continue;
						}
					} catch (ArrayIndexOutOfBoundsException e) {/*Ignore*/}

					//check correct player
					if (board.getLevel1()[i][j] == 2) {
						return new int[] {1, i, j};
					}
				}
			}			
			return new int[] {4, 1, 1};
		}
		else {
			//just tries to make a square/line as soon as possible, does not consider some might be better than other
			
			//level 3
			if (board.getLevel3()[0][0] == 2 && board.getLevel3()[0][1] == 2 && board.getLevel3()[1][0] == 2 && board.getLevel3()[1][1] == 0) {
				return new int[] {3, 1, 1};
			}
			if (board.getLevel3()[0][0] == 2 && board.getLevel3()[0][1] == 2 && board.getLevel3()[1][0] == 0 && board.getLevel3()[1][1] == 2) {
				return new int[] {3, 1, 0};
			}
			if (board.getLevel3()[0][0] == 2 && board.getLevel3()[0][1] == 0 && board.getLevel3()[1][0] == 2 && board.getLevel3()[1][1] == 2) {
				return new int[] {3, 0, 1};
			}
			if (board.getLevel3()[0][0] == 0 && board.getLevel3()[0][1] == 2 && board.getLevel3()[1][0] == 2 && board.getLevel3()[1][1] == 2) {
				return new int[] {3, 0, 0};
			}
			
			//level 2
			for (int i : new int[] {0, 1}) { //check squares
				for (int j : new int[] {0, 1}) {
					if (board.getLevel2()[i][j] == 2 && board.getLevel2()[i][j+1] == 2 && board.getLevel2()[i+1][j] == 2 && board.getLevel2()[i+1][j+1] == 0) {
						return new int[] {2, i+1, j+1};
					}
					if (board.getLevel2()[i][j] == 2 && board.getLevel2()[i][j+1] == 2 && board.getLevel2()[i+1][j] == 0 && board.getLevel2()[i+1][j+1] == 2) {
						return new int[] {2, i+1, j};
					}
					if (board.getLevel2()[i][j] == 2 && board.getLevel2()[i][j+1] == 0 && board.getLevel2()[i+1][j] == 2 && board.getLevel2()[i+1][j+1] == 2) {
						return new int[] {2, i, j+1};
					}
					if (board.getLevel2()[i][j] == 0 && board.getLevel2()[i][j+1] == 2 && board.getLevel2()[i+1][j] == 2 && board.getLevel2()[i+1][j+1] == 2) {
						return new int[] {2, i, j};
					}
				}
			}
			for (int i : new int[] {0, 1, 2}) { //check rows
				if (board.getLevel2()[i][0] == 2 && board.getLevel2()[i][1] == 2 && board.getLevel2()[i][2] == 0) {
					return new int[] {2, i, 2};
				}
				if (board.getLevel2()[i][0] == 2 && board.getLevel2()[i][1] == 0 && board.getLevel2()[i][2] == 2) {
					return new int[] {2, i, 1};
				}
				if (board.getLevel2()[i][0] == 0 && board.getLevel2()[i][1] == 2 && board.getLevel2()[i][2] == 2) {
					return new int[] {2, i, 0};
				}
			}
			for (int i : new int[] {0, 1, 2}) { //check columns
				if (board.getLevel2()[0][i] == 2 && board.getLevel2()[1][i] == 2 && board.getLevel2()[2][i] == 0) {
					return new int[] {2, 2, i};
				}
				if (board.getLevel2()[0][i] == 2 && board.getLevel2()[1][i] == 0 && board.getLevel2()[2][i] == 2) {
					return new int[] {2, 1, i};
				}
				if (board.getLevel2()[0][i] == 0 && board.getLevel2()[1][i] == 2 && board.getLevel2()[2][i] == 2) {
					return new int[] {2, 0, i};
				}
			}
			
			//level1
			for (int i : new int[] {0, 1, 2}) { //check squares
				for (int j : new int[] {0, 1, 2}) {
					if (board.getLevel1()[i][j] == 2 && board.getLevel1()[i][j+1] == 2 && board.getLevel1()[i+1][j] == 2 && board.getLevel1()[i+1][j+1] == 0) {
						return new int[] {1, i+1, j+1};
					}
					if (board.getLevel1()[i][j] == 2 && board.getLevel1()[i][j+1] == 2 && board.getLevel1()[i+1][j] == 0 && board.getLevel1()[i+1][j+1] == 2) {
						return new int[] {1, i+1, j};
					}
					if (board.getLevel1()[i][j] == 2 && board.getLevel1()[i][j+1] == 0 && board.getLevel1()[i+1][j] == 2 && board.getLevel1()[i+1][j+1] == 2) {
						return new int[] {1, i, j+1};
					}
					if (board.getLevel1()[i][j] == 0 && board.getLevel1()[i][j+1] == 2 && board.getLevel1()[i+1][j] == 2 && board.getLevel1()[i+1][j+1] == 2) {
						return new int[] {1, i, j};
					}
				}
			}
			for (int i : new int[] {0, 1, 2}) { //check rows
				if (board.getLevel1()[i][0] == 2 && board.getLevel1()[i][1] == 2 && board.getLevel1()[i][2] == 2 && board.getLevel1()[i][3] == 0) {
					return new int[] {1, i, 3};
				}
				if (board.getLevel1()[i][0] == 2 && board.getLevel1()[i][1] == 2 && board.getLevel1()[i][2] == 0 && board.getLevel1()[i][3] == 2) {
					return new int[] {1, i, 2};
				}
				if (board.getLevel1()[i][0] == 2 && board.getLevel1()[i][1] == 0 && board.getLevel1()[i][2] == 2 && board.getLevel1()[i][3] == 2) {
					return new int[] {1, i, 1};
				}
				if (board.getLevel1()[i][0] == 0 && board.getLevel1()[i][1] == 2 && board.getLevel1()[i][2] == 2 && board.getLevel1()[i][3] == 2) {
					return new int[] {1, i, 0};
				}
			}
			for (int i : new int[] {0, 1, 2}) { //check columns
				if (board.getLevel1()[0][i] == 2 && board.getLevel1()[1][i] == 2 && board.getLevel1()[2][i] == 2 && board.getLevel1()[3][i] == 0) {
					return new int[] {1, 3, i};
				}
				if (board.getLevel1()[0][i] == 2 && board.getLevel1()[1][i] == 2 && board.getLevel1()[2][i] == 0 && board.getLevel1()[3][i] == 2) {
					return new int[] {1, 2, i};
				}
				if (board.getLevel1()[0][i] == 2 && board.getLevel1()[1][i] == 0 && board.getLevel1()[2][i] == 2 && board.getLevel1()[3][i] == 2) {
					return new int[] {1, 1, i};
				}
				if (board.getLevel1()[0][i] == 0 && board.getLevel1()[1][i] == 2 && board.getLevel1()[2][i] == 2 && board.getLevel1()[3][i] == 2) {
					return new int[] {1, 0, i};
				}
			}
			
			//none available, choose something else. Just goes through the options in order until it finds an available space
			for (int i : new int[] {0, 1, 2, 3}) {
				for (int j : new int[] {0, 1, 2, 3}) {
					if (board.getLevel1()[i][j] == 0) {
						return new int[] {1, i, j};
					}
				}
			}
			for (int i : new int[] {0, 1, 2}) {
				for (int j : new int[] {0, 1, 2}) {
					if (board.getLevel2()[i][j] == 0) {
						return new int[] {2, i, j};
					}
				}
			}
			for (int i : new int[] {0, 1}) {
				for (int j : new int[] {0, 1}) {
					if (board.getLevel3()[i][j] == 0) {
						return new int[] {3, i, j};
					}
				}
			}
			
			//will never get here
			System.err.println("Error makeMove greedy-take pieces. This is impossible");
			return new int[] {4, 1, 1};
		}
	}
	
	public boolean removeTwo() {
		return true;
	}
}