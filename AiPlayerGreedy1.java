import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class AiPlayerGreedy1 {

    /*
        This AI player has been designed around the greedy algorithm. In the case of Pylos, a player can win by:
            1)  Getting to the top
            2)  The opponent has no more peices to play.
        This greedy implementation will focus around goal # 1 (get to the top!). In order to do so, the AI should avoid
        removing any unnecessary peices from the board. A player can remove 1-2 peices if:
            -   4 in a row vertically or horizontally (not diagonally)
            -   Making a square of the same colours
            -   Making a line of 3 on level two OR a square
        We need to avoid the above three conditions as best as we can. Worst case we will take 1 off only.
*/

    private Random random;

    private Squares[] level1;
    private Squares[] level2;
    private Squares[] level3;


    public AiPlayerGreedy1() {
        this.level1 = new Squares[]{
                new Squares(1,0,0,2, new int[][]{{0,0},{0,1},{1,0},{1,1}}),
                new Squares(1,0,1,2, new int[][]{{0,1},{0,2},{1,1},{1,2}}),
                new Squares(1,0,2,2, new int[][]{{0,2},{0,3},{1,2},{1,3}}),
                new Squares(1,1,0,2, new int[][]{{1,0},{1,1},{2,0},{2,1}}),
                new Squares(1,1,1,2, new int[][]{{1,1},{1,2},{2,1},{2,2}}),
                new Squares(1,1,2,2, new int[][]{{1,2},{1,3},{2,2},{2,3}}),
                new Squares(1,2,0,2, new int[][]{{2,0},{2,1},{3,0},{3,1}}),
                new Squares(1,2,1,2, new int[][]{{2,1},{2,2},{3,1},{3,2}}),
                new Squares(1,2,2,2, new int[][]{{2,2},{2,3},{3,2},{3,3}})
        };
        this.level2 = new Squares[]{
                new Squares(2,0,0,3, new int[][]{{0,0},{0,1},{1,0},{1,1}}),
                new Squares(2,0,1,3, new int[][]{{0,1},{0,2},{1,1},{1,2}}),
                new Squares(2,1,0,3, new int[][]{{1,0},{1,1},{2,0},{2,1}}),
                new Squares(2,1,1,3, new int[][]{{1,1},{1,2},{2,1},{2,2}})
        };
        this.level3 = new Squares[]{
                new Squares(3,0,0,4, new int[][]{{0,0},{0,1},{1,0},{1,1}})
        };

        this.random = new Random();


    }

    /**
     * A complex algorithm to return the next "optimal" move for the AI player.
     *
     * @param remove    ,Boolean value indicating whether a removal is required or not.
     * @param board     , PylosBoard object representing the current state of the board.
     * @return  int[] with the following format: int[] { level, row, column}. This represents a move.
     */
    public int[] makeMove(boolean remove, PylosBoard board ) {
        int[] move = null;
        if (remove) {
            PlayerMoves playerMoves = board.getPlayerMoves();
            ArrayList<String> options = playerMoves.getRemovableList(false);
            if(options.size() > 0){
                int level = Integer.parseInt(String.valueOf(options.get(0).toCharArray()[0]));
                int row = Integer.parseInt(String.valueOf(options.get(0).toCharArray()[1]));
                int col = Integer.parseInt(String.valueOf(options.get(0).toCharArray()[2]));
                move = new int[] {level,row,col};
            }


        }else {
            for (Squares square : this.level3) {
                int counter = 0;
                for (int j = 0; j < square.getSquarePositions().length; j++) {
                    int[] place = square.getSquarePositions()[j];

                    if (board.getLevel3()[place[0]][place[1]] != 0) {
                        counter++;
                    }
                }
                if (counter == 4 && board.getLevel2()[square.getRoofRow()][square.getRoofCol()] == 0) {  // all four supports are set!
                    move = new int[]{4, square.getRoofRow(), square.getRoofCol()};
                }
            }
            if (move == null) {
                for (Squares square : this.level2) {
                    int counter = 0;
                    for (int j = 0; j < square.getSquarePositions().length; j++) {
                        int[] place = square.getSquarePositions()[j];

                        if (board.getLevel2()[place[0]][place[1]] != 0) {
                            counter++;
                        }
                    }
                    if (counter == 4 && board.getLevel2()[square.getRoofRow()][square.getRoofCol()] == 0) {   // all four supports are set!
                        move = new int[]{3, square.getRoofRow(), square.getRoofCol()};
                    }
                }
            }

            if (move == null) {
                for (Squares square : this.level1) {
                    int counter = 0;
                    for (int j = 0; j < square.getSquarePositions().length; j++) {
                        int[] place = square.getSquarePositions()[j];

                        if (board.getLevel1()[place[0]][place[1]] != 0) {
                            counter++;
                        }
                    }
                    if (counter == 4 && board.getLevel2()[square.getRoofRow()][square.getRoofCol()] == 0) {   // all four supports are set!
                        move = new int[]{2, square.getRoofRow(), square.getRoofCol()};
                    }
                }
            }

            if (move == null) {
                ArrayList<String> moveOptions = board.getMoves();
                String option = moveOptions.get(random.nextInt(moveOptions.size()));
                move = new int[]{
                        Integer.parseInt(String.valueOf(option.charAt(0))),  //So damn messy..
                        Integer.parseInt(String.valueOf(option.charAt(1))),
                        Integer.parseInt(String.valueOf(option.charAt(2)))
                };

            }
        }


        return move;
    }

    /**
     * Class method intended to return the answer to "Does the AI remove more than one piece?"
     * @return true if returning more than one, false otherwise (false permanent for this implementation)
     */
    public boolean removeTwo() {
        return false;
    }
}