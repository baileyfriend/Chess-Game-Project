package chess;


/**
 * Created by Nathan Hull and Bailey Freund on 3/16/16.
 */
public abstract class ChessPiece implements IChessPiece {

	private Player owner;
	private ChessModel model;
	//    public boolean taken;

	//private ChessModel thisChessModel;

	//    public boolean isTaken() {
	//		return taken;
	//	}
	//
	//	public void setTaken(boolean taken) {
	//		this.taken = taken;
	//	}

	protected ChessPiece(Player player) {
		this.owner = player;
		//this.thisChessModel;
	}

	public abstract String type();

	public Player player() {
		return this.owner;

	}

	public boolean isPieceBetween(Move move, IChessPiece[][] board){

		if(board[move.fromRow][move.fromColumn].type() != "bishop"){
			for(int fr = move.fromRow; fr<move.toRow; fr++){
				for(int fc = move.fromColumn; fc<move.toColumn; fc++){
					if(board[fr][fc].type()!=null)
						return true;
				}
			}
			return false;
		}
		else if(board[move.fromRow][move.fromColumn].type() == "bishop"){
			int rowDiff = move.fromRow - move.toRow; //checks what the absolute value of the difference between the fromrow to the torow is
			int colDiff = move.fromColumn - move.toColumn;
			boolean downRight = rowDiff>0 && colDiff>0 ? true:false;
			boolean downLeft = rowDiff> 0 && colDiff<0 ? true:false;
			boolean upLeft = rowDiff<0 && colDiff<0 ? true:false;
			boolean upRight = rowDiff<0 && colDiff>0 ? true:false;
			int theRow = move.fromRow;
			int theCol = move.fromColumn;
			int toRow = move.toRow;
			int toCol = move.toColumn;
			if(downRight==true){
				while(theRow<toRow){
					while(theCol<toCol){
						if(model.pieceAt(theRow,theCol).type() != null)
							return true;
						theRow++;
						theCol++;
					}
				}
			}
			if(downLeft==true){
				while(theRow<toRow){
					while(theCol>toCol){
						if(model.pieceAt(theRow,theCol).type() != null)
							return true;
						theRow++;
						theCol--;
					}
				}
			}
			if(upRight==true){
				while(theRow>toRow){
					while(theCol>toCol){
						if(model.pieceAt(theRow,theCol).type() != null)
							return true;
						theRow--;
						theCol++;
					}
				}
			}
			if(upLeft==true){
				while(theRow>toRow){
					while(theCol>toCol){
						if(model.pieceAt(theRow,theCol).type() != null)
							return true;
						theRow--;
						theCol--;
					}
				}
			}

		}
		return false;


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

		//if there is a piece between the the move.from and the move.to then return false
		else if(isPieceBetween(move, board) == true 
				&& board[move.fromRow][move.fromColumn].type() != "knight") 
			return false;

		//checks if chesspiece is owned by the current player or the next player
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




}


