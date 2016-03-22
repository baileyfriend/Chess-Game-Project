package chess;

public class Knight extends ChessPiece {
	/**
     * Constructs piece and assigns a player
     * 
     * @param player player assigned to piece
     */
	public Knight(Player player){
		super(player);
		moved = false;
		//super(isValidMove()); -- want to call the super's isValidMove to check for the conditions set in superClass ChessPiece - not exactly sure how to do that yet
	}
	/**
     * Returns the type of the piece
     * 
     * @return Returns the type of the piece as a string
     */
	public String type(){
		return "knight";
	}
	/**
     *Sets whether the piece has been moved - only used by some pieces
     * 
     * @param val sets whether the piece has been moved
     */
	public void setMoved(boolean val){
		moved = val;
	}

	/**
	 * Returns whether the piece at location {@code [move.fromRow, move.fromColumn]} is allowed to move to location
	 * {@code [move.fromRow, move.fromColumn]}.
	 *
	 * @param move  a {@link W13project3.Move} object describing the move to be made.
	 * @param board the {@link W13project3.IChessBoard} in which this piece resides.
	 * @return {@code true} if the proposed move is valid, {@code false} otherwise.
	 */
	public
	public boolean isValidMove(Move move, IChessPiece[][] board){
		
		if(super.isValidMove(move, board)){

		if(move.toRow == move.fromRow -2 && move.toColumn == move.fromColumn-1)
			return true;
		
		if(move.toRow == move.fromRow -1 && move.toColumn == move.fromColumn-2)
			return true;
		
		if(move.toRow == move.fromRow -2 && move.toColumn == move.fromColumn+1)
			return true;

		if(move.toRow == move.fromRow -1 && move.toColumn == move.fromColumn+2)
			return true;

		if(move.toRow == move.fromRow +1 && move.toColumn == move.fromColumn-2)
			return true;

		if(move.toRow == move.fromRow +2 && move.toColumn == move.fromColumn-1)
			return true;

		if(move.toRow == move.fromRow +1 && move.toColumn == move.fromColumn+2)
			return true;
		
		if(move.toRow == move.fromRow +2 && move.toColumn == move.fromColumn+1)
			return true;
		
		
		else
			return false;
		}
		return false;

	}
}

