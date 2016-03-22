package chess;

public class Bishop extends ChessPiece {

	public Bishop(Player player) {
		super(player);
		moved = false;
		// super(isValidMove()); -- want to call the super's isValidMove to
		// check for the conditions set in superClass ChessPiece - not exactly
		// sure how to do that yet
	}

	public String type() {
		return "bishop";
	}

	public void setMoved(boolean val) {
		moved = val;
	}

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
