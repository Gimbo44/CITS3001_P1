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

    public boolean isFull(int[][] level){
        for(int row = 0; row < level.length; row++){
            for(int col = 0; col < level[row].length; col++){
                if(level[row][col] == 0){
                    return false;
                }
            }
        }
        return true;
    }

    private int[][] getLevel(PylosBoard board, int level){
        int[][] lvl = null;
        switch(level){
            case 1:
                lvl =  board.getLevel1();
                break;
            case 2:
                lvl = board.getLevel2();
                break;
            case 3:
                lvl = board.getLevel3();
                break;
            case 4:
                lvl = new int[][]{{board.getLevel4()}};
                break;
        }
        return lvl;
    }

    public int[] makeMove(PylosBoard board ) {
        //int level = random.nextInt(4) + 1; //random.nextInt(max - min + 1) + 1  https://stackoverflow.com/a/20389923
        int[][] grid;
        int[] move = null;
        for(int level = 4; level > 0; level--){

            grid = this.getLevel(board,level);

            for(int row = 0; row < grid.length; row++){
                for(int col = 0; col < grid.length; col++){
                    if(board.validMove(level,row,col)){
                        move =  new int[] {level,row, col};
                        break;
                    }
                }
                if(move != null){
                    break;
                }
            }
            if(move != null){
                break;
            }

        }

        return move;
    }


    public boolean removeTwo() {
        return false;
    }
}