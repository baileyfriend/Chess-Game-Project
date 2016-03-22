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
     */	/**
     * Constructs piece and assigns a player
     * 
     * @param player player assigned to piece
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
	/**
     *Gets whether the piece has been moved - only used by some pieces
     * 
     * @return moved - whether the piece has been moved yet this game
     */
	public boolean getMoved() {
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
		if (super.isValidMove(move, board)) {

			int fr = move.fromRow;
			int tr = move.toRow;
			int fc = move.fromColumn;
			int tc = move.toColumn;

			if (fr != tr && fc == tc) {
				if (fr > tr) {
					for (int x = tr + 1; x < fr; x++) {
						if (super.exists(x, fc, board)) {
							return false;
						}
					}
					return true;
				} else {
					for (int x = fr + 1; x < tr; x++) {
						if (super.exists(x, fc, board)) {
							return false;
						}
					}
					return true;
				}
			} else if (fr == tr && fc != tc) {
				if (fc > tc) {
					for (int x = tc + 1; x < fc; x++) {
						if (super.exists(fr, x, board)) {
							return false;
						}
					}
					return true;
				} else {
					for (int x = fc + 1; x < tc; x++) {
						if (super.exists(fr, x, board)) {
							return false;
						}
					}
					return true;
				}
			} else {
				return false;
			}
		}
		return false;
	}
}
