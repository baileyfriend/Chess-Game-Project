package chess;

public class Pawn extends ChessPiece {
	private boolean moved;
		
	/**
     * Constructs piece and assigns a player
     * 
     * @param player player assigned to piece
     */
	public Pawn(Player player){
		super(player);
		moved = false;
	}
	/**
     * Returns the type of the piece
     * 
     * @return Returns the type of the piece as a string
     */
	public String type(){
		return "pawn";
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
	public boolean isValidMove(Move move, IChessPiece[][] board){
		if(super.isValidMove(move, board)){
		if (this.player() == Player.BLACK){
			
			try{
				if(board[move.toRow][move.toColumn].type() == null){
					
				}
				else if (move.fromRow + 1 == move.toRow &&
						(move.fromColumn - 1 == move.toColumn
						|| move.fromColumn + 1 == move.toColumn) &&
						board[move.toRow][move.toColumn].player()
						== Player.WHITE){
					moved = true;
					return true;
				}
			}
			catch(Exception NullPointerException){
				if (move.fromRow + 2 == move.toRow && 
						move.fromColumn == move.toColumn && !moved){
					moved = true;
					return true;
				}
				else if (move.fromRow + 1 == move.toRow &&
						move.fromColumn == move.toColumn){
					moved = true;
					return true;
				}
			
			}
			
		}else if (this.player() == Player.WHITE){
			
			try{
				if(board[move.toRow][move.toColumn].type() == null){
					
				}
				else if (move.fromRow - 1 == move.toRow &&
						(move.fromColumn + 1 == move.toColumn
						|| move.fromColumn - 1 == move.toColumn) &&
						board[move.toRow][move.toColumn].player()
						== Player.BLACK){
					moved = true;
					return true;
				}
			}
			catch(Exception NullPointerException){
				if (move.fromRow - 2 == move.toRow && 
						move.fromColumn == move.toColumn && !moved){
					moved = true;
					return true;
				}
				else if (move.fromRow - 1 == move.toRow &&
						move.fromColumn == move.toColumn){
					moved = true;
					return true;
				}
			
			}
			
		}
		}
		return false;
		
	}
	
}
