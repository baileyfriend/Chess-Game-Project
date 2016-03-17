/**
 * Created by baileyfreund on 3/16/16.
 */
public class ChessModel implements IChessModel {
    private IChessPiece[][] board;
    private Player player;
    // declare other instance variables as needed

    public ChessModel() {
        // complete this
    }

    public boolean isComplete() {
        return false;
    }

    public boolean isValidMove(Move move) {
        // complete this
    }

    public void move(Move move) {
        // complete this
    }

    public boolean inCheck(Player p) {
        return false;
    }

    public Player currentPlayer() {
        // complete this
    }

    public int numRows() {
        // complete this
    }

    public int numColumns() {
        // complete this
    }

    public IChessPiece pieceAt(int row, int column) {
        // complete this
    }

    // add other public or helper methods as needed
}

