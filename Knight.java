package chess;

public class Knight extends ChessPiece {

	public Knight(Player player){
		super(player);
		moved = false;
		//super(isValidMove()); -- want to call the super's isValidMove to check for the conditions set in superClass ChessPiece - not exactly sure how to do that yet
	}

	public String type(){
		return "knight";
	}

	public void setMoved(boolean val){
		moved = val;
	}

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

