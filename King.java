package chess;

public class King extends ChessPiece {
	
	private boolean moved;
	/**
     * Constructs piece and assigns a player
     * 
     * @param player player assigned to piece
     */	public King(Player player) {
		super(player);
		moved = false;
	}
	/**
     * Returns the type of the piece
     * 
     * @return Returns the type of the piece as a string
     */
	public String type() {
		return "king";
	}
	/**
     *Sets whether the piece has been moved - only used by some pieces
     * 
     * @param val sets whether the piece has been moved
     */
	public void setMoved(boolean val) {
		moved = val;
	}
	/**
     *Gets whether the piece has been moved - only used by some pieces
     * 
     * @return moved - whether the piece has been moved yet this game
     */
	public boolean getMoved(){
		return moved;
	}
	/**
	 * Returns whether the piece at location {@code [move.fromRow, move.fromColumn]} is allowed to move to location
	 * {@code [move.fromRow, move.fromColumn]}.
	 *
	 * @param move  a {@link W13project3.Move} object describing the move to be made.
	 * @param board the {@link W13project3.IChessBoard} in which this piece resides.
	 * @return {@code true} if the proposed move is valid, {@code false} otherwise.
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		if(super.isValidMove(move, board)){
		if (Math.abs(move.toColumn - move.fromColumn) <= 1 &&
				Math.abs(move.toRow - move.fromRow) <= 1){
			return true;
		}
		else
			return false;
		}
		return false;
	}
}
