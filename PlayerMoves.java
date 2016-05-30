import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by arun on 21/05/16.
 * This class was designed with the intention of recording player moves to allow the AI to review play history
 * and extract relevant information.
 */
public class PlayerMoves {

    private Map<Integer,ArrayList<String>> humanPlayerMoves;
    private Map<Integer,ArrayList<String>> AiPlayerMoves;
    private PylosBoard pylosBoard;

    /**
     * A simple constructor.
     * @param board ,PylosBoard object representing the current state of the board.
     */
    public PlayerMoves(PylosBoard board) {
        this.pylosBoard = board;

        // Initializing the playerMoves object.
        humanPlayerMoves = new HashMap<>();
        humanPlayerMoves.put(1,new ArrayList<>());
        humanPlayerMoves.put(2,new ArrayList<>());
        humanPlayerMoves.put(3,new ArrayList<>());
        humanPlayerMoves.put(4,new ArrayList<>());

        AiPlayerMoves = new HashMap<>();
        AiPlayerMoves.put(1,new ArrayList<>());
        AiPlayerMoves.put(2,new ArrayList<>());
        AiPlayerMoves.put(3,new ArrayList<>());
        AiPlayerMoves.put(4,new ArrayList<>());
    }

    /**
     * A simple getter for the playMoves object, focusing on the AI player moves. Allows users the ability
     * to select a particular level of interest.
     * @param level, integer representing a level on a pylos board.
     * @return Arraylist<String>
     */
    public ArrayList<String> getAiMoves(int level){
        if(level > 0 && level <= 4){
            return AiPlayerMoves.get(level);
        }else{
            return null;
        }
    }

    /**
     * A simple getter for the playMoves object, focusing on the human player moves. Allows users the ability
     * to select a particular level of interest.
     * @param level, integer representing a level on a pylos board.
     * @return Arraylist<String>
     */
    public ArrayList<String> getPlayerMoves(int level){
        if(level > 0 && level <= 4){
            return humanPlayerMoves.get(level);
        }else{
            return null;
        }
    }

    /**
     * A function/method dedicated to recording a players moves. Stores the unique move signature which is a concatenation
     * of the level, row and column values. This is to reduce unnecessary looping.
     * @param moveSignature, String representing a unique move signature (level + row + col)
     * @param player    ,boolean value representing which player to record to (true == human, false == AI)
     */
    public void recordMove(String moveSignature, boolean player){
        int level = Integer.parseInt(String.valueOf(moveSignature.toCharArray()[0]));
        if(player){
            if(!humanPlayerMoves.get(level).contains(moveSignature)){
                humanPlayerMoves.get(level).add(moveSignature);
            }
        }else{
            if(!AiPlayerMoves.get(level).contains(moveSignature)){
                AiPlayerMoves.get(level).add(moveSignature);
            }
        }
    }

    /**
     * A function/method dedicated to removing a players move.
     * @param moveSignature, String representing a unique move signature (level + row + col)
     * @param player    ,boolean value representing which player to record to (true == human, false == AI)
     */
    public void deleteMove(String moveSignature, boolean player){
        int level = Integer.parseInt(String.valueOf(moveSignature.toCharArray()[0]));
        if(player){
            if(humanPlayerMoves.get(level).contains(moveSignature)){
                humanPlayerMoves.get(level).remove(moveSignature);
            }
        }else{
            if(AiPlayerMoves.get(level).contains(moveSignature)){
                AiPlayerMoves.get(level).remove(moveSignature);
            }
        }
    }

    /**
     * Method is used by the AI player to generate a list of removable items. Orders them based on level (lowest first,
     * highest last). Preforms this ordering to comply with the greedy1 algorithm.
     * @param player    ,boolean value representing which player to record to (true == human, false == AI)
     * @return ArrayList<String> representing a list of possible pieces to remove.
     */
    public ArrayList<String> getRemovableList(boolean player){
        ArrayList<String> output = new ArrayList<>();
        ArrayList<String> levelMoves;
        int piece;
        for(int level = 1; level <= 3; level++){
            if(player){
                levelMoves = humanPlayerMoves.get(level);
                piece = 1;
            }else{
                levelMoves = AiPlayerMoves.get(level);
                piece = 2;
            }
            for(String play: levelMoves){
                int movelvl = Integer.parseInt(String.valueOf(play.toCharArray()[0]));
                int moveRow = Integer.parseInt(String.valueOf(play.toCharArray()[1]));
                int moveCol = Integer.parseInt(String.valueOf(play.toCharArray()[2]));

                if(this.pylosBoard.notSupporting(piece, movelvl,moveRow, moveCol)){
                    output.add(play);
                }
            }
        }

        return output;
    }


}
