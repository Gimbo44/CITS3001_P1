/**
 * Created by arun on 20/05/16.
 * Class designed to hold relevant information about a possible square group found inside a typical pylos board.
 */
public class Squares {



    private int level;
    private int roofRow;
    private int roofCol;
    private int roofLevel;
    private int[][] squarePositions;

    public Squares(int level, int roofRow, int roofCol, int roofLevel, int[][] squarePositions) {
        this.level = level;
        this.roofRow = roofRow;
        this.roofCol = roofCol;
        this.roofLevel = roofLevel;
        this.squarePositions = squarePositions;
    }

    /**
     * Getter for the class variable level
     * @return int
     */
    public int getLevel() {
        return level;
    }

    /**
     * Setter for the class variable level
     * @param level, int representing the level of the square.
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Getter for the class variable roofRow
     * @return int
     */
    public int getRoofRow() {
        return roofRow;
    }

    /**
     * Setter for the class variable roofRow
     * @param roofRow, int representing the row position of the supported piece.
     */
    public void setRoofRow(int roofRow) {
        this.roofRow = roofRow;
    }

    /**
     * Getter for the class variable roofCol
     * @return int
     */
    public int getRoofCol() {
        return roofCol;
    }

    /**
     * Setter for the class variable roofCol
     * @param roofCol, int representing the col position of the supported piece.
     */
    public void setRoofCol(int roofCol) {
        this.roofCol = roofCol;
    }

    /**
     * Getter for the class variable roofLevel
     * @return int
     */
    public int getRoofLevel() {
        return roofLevel;
    }

    /**
     * Setter for the class variable roofLevel
     * @param roofLevel ,int representing the squares support level (roof)
     */
    public void setRoofLevel(int roofLevel) {
        this.roofLevel = roofLevel;
    }

    /**
     * Getter for the class variable squarePositions
     * @return int[][]
     */
    public int[][] getSquarePositions() {
        return squarePositions;
    }

    /**
     * Setter for the class variable squarePositions
     * @param squarePositions, int[][] representing square positions (row,col)
     */
    public void setSquarePositions(int[][] squarePositions) {
        this.squarePositions = squarePositions;
    }


}
