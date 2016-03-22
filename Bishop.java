package chess;

public class Bishop extends ChessPiece {

	/**
     * Constructs piece and assigns a player
     * 
     * @param player player assigned to piece
     */
	public Bishop(Player player) {
		super(player);
		moved = false;
	}
	/**
     * Returns the type of the piece
     * 
     * @return Returns the type of the piece as a string
     */
	public String type() {
		return "bishop";
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
	 * Returns whether the piece at location {@code [move.fromRow, move.fromColumn]} is allowed to move to location
	 * {@code [move.fromRow, move.fromColumn]}.
	 *
	 * @param move  a {@link W13project3.Move} object describing the move to be made.
	 * @param board the {@link W13project3.IChessBoard} in which this piece resides.
	 * @return {@code true} if the proposed move is valid, {@code false} otherwise.
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		if (super.isValidMove(move, board)) {
			int rowDiff = Math.abs(move.fromRow - move.toRow); 
			// checks what the absolute value of the difference between the from row to the to row is
		
			int colDiff = Math.abs(move.fromColumn - move.toColumn);
			// checks what the absolute value of the difference between the from col to the to col is

			int fr = move.fromRow;
			int tr = move.toRow;
			int fc = move.fromColumn;
			int tc = move.toColumn;

			if (rowDiff != colDiff)// if the difference is equal, then the spot
									// must be diagonal - meaning that the
									// bishop may move there
				return false;
			else{
				if(fr < tr){
					if(fc < tc){
						for(int x = 1; x < rowDiff; x++){
							if(super.exists(fr + x, fc + x, board)){
								return false;
							}
						}
						return true;
					}else{
						for(int x = 1; x < rowDiff; x++){
							if(super.exists(fr + x, fc - x, board)){
								return false;
							}
						}
						return true;
					}
				}else{
					if(fc < tc){
						for(int x = 1; x < rowDiff; x++){
							if(super.exists(fr - x, fc + x, board)){
								return false;
							}
						}
						return true;
					}else{
						for(int x = 1; x < rowDiff; x++){
							if(super.exists(fr - x, fc - x, board)){
								return false;
							}
						}
						return true;
					}
				}
			}
		}
		return false;
	}
}
