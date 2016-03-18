package chess;

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
    	return 8;
    }

    public int numColumns() {
      return 8;
    }

    public IChessPiece pieceAt(int row, int column) {
        // complete this
    	
    	return board[row][column];
    }
    
    public boolean containsPiece(int row, int column){
    	
    	if(board[row][column].type() == null)
    		return false;
    	else
    		return true;
    	
    }
    

    // add other public or helper methods as needed
}

