/**
 * Created by baileyfreund on 3/16/16.
 */
public class Move {

    public int fromRow, fromColumn, toRow, toColumn;

    public Move() {
    }

    /****
     * Moves the piece from selected spot to selected spot
     * @param fromRow Row from which the piece is moving
     * @param fromColumn Col from which the piece is moving
     * @param toRow Row to which the piece is moving
     * @param toColumn Col to which the piece is moving
     */
    public Move(int fromRow, int fromColumn, int toRow, int toColumn) {
        this.fromRow = fromRow;
        this.fromColumn = fromColumn;
        this.toRow = toRow;
        this.toColumn = toColumn;
    }
}
