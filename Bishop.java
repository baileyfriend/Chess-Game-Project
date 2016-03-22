package chess;

public class Bishop extends ChessPiece {

	/**
     * Constructs piece and assigns a player
     * 
     * @param player player assigned to piece
     */
	public Bishop(Player player){
		super(player);
		//super(isValidMove()); -- want to call the super's isValidMove to check for the conditions set in superClass ChessPiece - not exactly sure how to do that yet
	}
	/**
     * Returns the type of the piece
     * 
     * @return Returns the type of the piece as a string
     */
	public String type(){
		return "bishop";
	}
	/**
     *Sets whether the piece has been moved - only used by some pieces
     * 
     * @param val sets whether the piece has been moved
     */
	public void setMoved(boolean val) {
		moved = val;
	}//never used

	/**
	 * Returns whether the piece at location {@code [move.fromRow, move.fromColumn]} is allowed to move to location
	 * {@code [move.fromRow, move.fromColumn]}.
	 *
	 * @param move  a {@link W13project3.Move} object describing the move to be made.
	 * @param board the {@link W13project3.IChessBoard} in which this piece resides.
	 * @return {@code true} if the proposed move is valid, {@code false} otherwise.
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board){
		if(super.isValidMove(move, board)){ 
		 int rowDiff = Math.abs(move.fromRow - move.toRow); //checks what the absolute value of the difference between the fromrow to the torow is
		 int colDiff = Math.abs(move.fromColumn - move.toColumn);//checks what the absolute value of the difference between the fromcol to the tocol is
		    
		    
		    if(rowDiff == colDiff)//if the difference is equal, then the spot must be diagonal - meaning that the bishop may move there
		        return true;
		    else
		    	return false; //if not diagonal, bishop may not move there
		}
		return false;
	}
}

