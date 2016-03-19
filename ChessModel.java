package chess;

/**
 * Created by Nathan Hull and Bailey Freund on 3/16/16.
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
    /**
     * Returns whether the piece at location {@code [move.fromRow, move.fromColumn]} is allowed to move to location
     * {@code [move.fromRow, move.fromColumn]}.
     *
     * @param move a {@link W13project3.Move} object describing the move to be made.
     * @return {@code true} if the proposed move is valid, {@code false} otherwise.
     * @throws IndexOutOfBoundsException if either {@code [move.fromRow, move.fromColumn]} or {@code [move.toRow,
     *                                   move.toColumn]} don't represent valid locations on the board.
     */
    public boolean isValidMove(Move move) {
        // complete this
    	//what exactly is this method doing..? Is this referring to the ChessPiece isValidMove() method?
    	//call the specific piece which calls the super class
    	IChessPiece piece = board[move.fromRow][move.fromColumn];
    	return piece.isValidMove(move, board);
    }

    public void move(Move move) {
        // complete this
    	board[move.toRow][move.toColumn] = pieceAt(move.fromRow,move.fromColumn);
    	board[move.fromRow][move.fromColumn] = null;	
    }

    public boolean inCheck(Player p) {
        return false;
    }

    public Player currentPlayer() { 
    	return player;
    	
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
    
    public boolean containsPiece(int row, int column){//checks to see whether the row and column contains a piece - returns true if there is a piece there, and false if not
    	
    	if(board[row][column].type() == null)
    		return false;
    	else
    		return true;
    	
    }
    
    public Player setNextPlayer(){
    	return player.next();
    }
    

    // add other public or helper methods as needed
}

