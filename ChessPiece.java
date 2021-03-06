package chess;


/**
 * Created by Nathan Hull and Bailey Freund on 3/16/16.
 */
public abstract class ChessPiece implements IChessPiece {

	private Player owner;
	public boolean moved;
	
	
	protected ChessPiece(Player player) {
		this.owner = player;
	}

	public abstract String type();

	public Player player() {
		return this.owner;
	}

	/**
	 * Returns whether the piece at location {@code [move.fromRow, move.fromColumn]} is allowed to move to location
	 * {@code [move.fromRow, move.fromColumn]}.
	 *
	 * Note:  Pieces don't store their own location (because doing so would be redundant).  Therefore,
	 * the {@code [move.fromRow, move.fromColumn]} component of {@code move} is necessary.
	 * {@code this} object must be the piece at location {@code [move.fromRow, move.fromColumn]}.
	 * (This method makes no sense otherwise.)
	 *
	 * @param move  a {@link W13project3.Move} object describing the move to be made.
	 * @param board the {@link W13project3.IChessBoard} in which this piece resides.
	 * @return {@code true} if the proposed move is valid, {@code false} otherwise.
	 * @throws IndexOutOfBoundsException if either {@code [move.fromRow, move.fromColumn]} or {@code [move.toRow,
	 *                                   move.toColumn]} don't represent valid locations on the board.
	 * @throws IllegalArgumentException  if {@code this} object isn't the piece at location {@code [move.fromRow, move.fromColumn]}.
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		// must check that starting and ending locations are diff, this piece is located at move.fromRow, move.fromColumn on the board, and verify that move.toRow, move.toCol does not contain a piece belonging to the same player.

		//checks to make sure that the move is within the board(8x8 - array starts at 0 so strictly less than).
		if(move.toColumn>8 || move.toRow>8 || move.toColumn<0 
				|| move.toRow<0) 
			return false;

		//if you are trying to move to the same place on the board, return false
		if(move.fromColumn == move.toColumn 
				&& move.fromRow == move.toRow)
			return false;

		//checks if chessPiece is owned by the current player or the next player
		try{
			if (board[move.toRow][move.toColumn].player() == this.owner)//player() != owner.next())
				return false;
		}
		catch(Exception NullPointerException){ //if the board space is null then there is no piece, so true should be returned
			return true;
		}

		//if none of the above are true, then the move must be valid.
			return true;
	}
	
	/**
	 * Checks whether a piece exists at the given row and column on the board
	 * 
	 * @param r Row of board being checked for piece
	 * @param c Column of board being checked for piece
	 * @param board This is the board
	 * 
	 * @return true if there is a piece in the given
	 * r and c
	 * @return false if there is no piece in the given
	 * r and c (meaning you have a null pointer)
	 */
	public static boolean exists(int r, int c, IChessPiece[][] board){
		try{
			board[r][c].type();
			return true;
		}
		catch(Exception NullPointerException){
			return false;
		}
	}

}

