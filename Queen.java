package chess;

public class Queen extends ChessPiece {


	public Queen(Player player){
		super(player);
		//super(isValidMove()); -- want to call the super's isValidMove to check for the conditions set in superClass ChessPiece - not exactly sure how to do that yet
	}

	public String type(){
		return "queen";
	}



	public boolean isValidMove(Move move, IChessPiece[][] board){
		int rowDiff = Math.abs(move.fromRow - move.toRow); //checks what the absolute value of the difference between the fromrow to the torow is
		 int colDiff = Math.abs(move.fromColumn - move.toColumn);//checks what the absolute value of the difference between the fromcol to the tocol is
		    
		
		 if(super.isValidMove(move, board)){
		
		if (move.fromColumn != move.toColumn && //checks if queen is moving within row (straight)
				move.fromRow == move.toRow)
			return true;
		else if (move.fromColumn == move.toColumn && //checks if queen is moving within col (straight)
				move.fromRow != move.toRow)
			return true;
	    if(rowDiff == colDiff)//if the difference is equal, then the spot must be diagonal - meaning that the bishop may move there
	        return true;
			else
			return false;
		 }
		 return false;
	}
}

