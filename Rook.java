package chess;

public class Rook extends ChessPiece {

	private boolean moved;
	
	/**
     * Constructs piece and assigns a player
     * 
     * @param player player assigned to piece
     */
	public Rook(Player player) {
		super(player);
		moved = false;
	}
	/**
     * Returns the type of the piece
     * 
     * @return Returns the type of the piece as a string
     */
	public String type() {
		return "rook";
	}
	/**
     *Sets whether the piece has been moved - only used by some pieces
     * 
     * @param val sets whether the piece has been moved
     */
	public void setMoved(boolean val) {
		moved = val;
	}
	
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
	public
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		if(super.isValidMove(move, board)){
		if (move.fromColumn != move.toColumn &&
				move.fromRow == move.toRow){
			moved = true;
			return true;
		}
		else if (move.fromColumn == move.toColumn &&
				move.fromRow != move.toRow){
			moved = true;
			return true;
		}
		else if(board[move.toRow][move.toColumn].type() == "king" && moved != true){
			return true;
		}
		else
			return false;
		}
		return false;
	}
}
