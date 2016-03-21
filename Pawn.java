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
			
			try{
				if(board[move.toRow][move.toColumn].type() == null){
					
				}
				else if (move.fromRow - 1 == move.toRow &&
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