package chess;

public class Bishop extends ChessPiece {


	public Bishop(Player player){
		super(player);
		//super(isValidMove()); -- want to call the super's isValidMove to check for the conditions set in superClass ChessPiece - not exactly sure how to do that yet
	}

	public String type(){
		return "bishop";
	}



	public boolean isValidMove(Move move, IChessPiece[][] board){
		if(super.isValidMove(move, board)){ 
		 int rowDiff = Math.abs(move.fromRow - move.toRow); //checks what the absolute value of the difference between the fromrow to the torow is
		 int colDiff = Math.abs(move.fromColumn - move.toColumn);//checks what the absolute value of the difference between the fromcol to the tocol is
		    
		    
		    if(rowDiff == colDiff)//if the difference is equal, then the spot must be diagonal - meaning that the bishop may move there
		        return true;
		    else
		    	return false; //if not diagonal, bishop may not move there
		}
		return false;
	}
}

