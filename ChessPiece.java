/**
 * Created by baileyfreund on 3/16/16.
 */
public abstract class ChessPiece implements IChessPiece {

    private Player owner;

    protected ChessPiece(Player player) {
        this.owner = player;
    }

    public abstract String type();

    public Player player() {
        // complete this
    }

    public boolean isValidMove(Move move, IChessPiece[][] board) {
        // complete this
        // must check that starting and ending locations are diff, this piece is located at move.fromRow, move.fromColumn on the board, and verify that move.toRow, move.toCol does not contain a piece belonging to the same player.

        if(move.toColumn>8 || move.toRow>8)//checks to make sure that the move is within the board(8x8 - array starts at 0 so strictly less than).
            return false;
        if(move.fromColumn == move.toColumn && move.fromRow == move.toRow)
            return false;
        else if (this.ChessPiece.location != move.fromColumn || this.Chespiece.location != move.fromRow) // this won't work - just not yet sure what the "location" variable would be quite yet
            return false;
        else if()//the move.toColumn and move.toRow contain a piece that is the player(making the move) return false. Need to figure out where these vars are located - maybe ChessModel??
            return false;
        else
            return true; //if none of the above are true, then the move must be valid.
    }


