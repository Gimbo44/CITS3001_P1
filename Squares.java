/**
 * Created by arun on 20/05/16.
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRoofRow() {
        return roofRow;
    }

    public void setRoofRow(int roofRow) {
        this.roofRow = roofRow;
    }

    public int getRoofCol() {
        return roofCol;
    }

    public void setRoofCol(int roofCol) {
        this.roofCol = roofCol;
    }

    public int getRoofLevel() {
        return roofLevel;
    }

    public void setRoofLevel(int roofLevel) {
        this.roofLevel = roofLevel;
    }

    public int[][] getSquarePositions() {
        return squarePositions;
    }

    public void setSquarePositions(int[][] squarePositions) {
        this.squarePositions = squarePositions;
    }


}
