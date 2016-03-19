package chess;

public class Pawn extends ChessPiece {
	private boolean moved;
	
	public Pawn(Player player){
		super(player);
		moved = false;
	}
	
	public String type(){
		return "pawn";
	}
	
	public void setMoved(boolean val){
		moved = val;
	}
	
	public boolean isValidMove(Move move, IChessPiece[][] board){
		if(super.isValidMove(move, board)){
		if (this.player() == Player.BLACK){
			
			if (move.fromColumn + 2 == move.toColumn && 
					move.fromRow == move.toRow && !moved)
				return true;
			else if (move.fromColumn + 1 == move.toColumn &&
					move.fromRow == move.toRow)
				return true;
			else if (move.fromColumn + 1 == move.toColumn &&
					move.fromRow + 1 == move.toRow || move.fromRow - 1
					== move.toRow && board[move.toRow][move.toColumn]
							.player() == Player.WHITE)
				return true;
			else
				return false;
			
		}else if (this.player() == Player.WHITE){
			
			if (move.fromColumn - 2 == move.toColumn && 
					move.fromRow == move.toRow && !moved)
				return true;
			else if (move.fromColumn - 1 == move.toColumn &&
					move.fromRow == move.toRow)
				return true;
			else if (move.fromColumn - 1 == move.toColumn &&
					move.fromRow + 1 == move.toRow || move.fromRow - 1
					== move.toRow && board[move.toRow][move.toColumn]
							.player() == Player.BLACK)
				return true;
			else
				return false;
			
		}
		}
		return false;
	}
}