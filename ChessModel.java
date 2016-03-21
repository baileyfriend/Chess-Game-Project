package chess;

/**
 * Created by Nathan Hull and Bailey Freund on 3/16/16.
 */
public class ChessModel implements IChessModel {
    private IChessPiece[][] board;
    private Player player;
//    public boolean taken;
    // declare other instance variables as needed

    public ChessModel() {
    	board = new IChessPiece[8][8];
    	
//create and place pawns
for(int x = 0; x < 8; x++){
	board[1][x] = new Pawn(Player.BLACK);
}
for(int x = 0; x < 8; x++){
	board[6][x] = new Pawn(Player.WHITE);
}

board[0][0] = new Rook(Player.BLACK);
board[0][7] = new Rook(Player.BLACK);
board[7][0] = new Rook(Player.WHITE);
board[7][7] = new Rook(Player.WHITE);

board[0][1] = new Bishop(Player.BLACK);
board[0][6] = new Bishop(Player.BLACK);
board[7][1] = new Bishop(Player.WHITE);
board[7][6] = new Bishop(Player.WHITE);

board[0][2] = new Knight(Player.BLACK);
board[0][5] = new Knight(Player.BLACK);
board[7][2] = new Knight(Player.WHITE);
board[7][5] = new Knight(Player.WHITE);

board[0][3] = new King(Player.BLACK);
board[0][4] = new Queen(Player.BLACK);
board[7][3] = new King(Player.WHITE);
board[7][4] = new Queen(Player.WHITE);


    }

    public boolean isComplete() {
        return false;
    }
    /**
     * Returns whether the piece at location {@code [move.fromRow, move.fromColumn]} is allowed to move to location
     * {@code [move.fromRow, move.fromColumn]}.
     *
     * @param move a {@link W13project3.Move} object describing the move to be made.
     * @return {@code true} if the proposed move is valid, {@code false} otherwise.
     * @throws IndexOutOfBoundsException if either {@code [move.fromRow, move.fromColumn]} or {@code [move.toRow,
     *                                   move.toColumn]} don't represent valid locations on the board.
     */
    public boolean isValidMove(Move move) {
        // complete this
    	//what exactly is this method doing..? Is this referring to the ChessPiece isValidMove() method?
    	//call the specific piece which calls the super class
    	IChessPiece piece = board[move.fromRow][move.fromColumn];
    	return piece.isValidMove(move, board);
    }

    public void move(Move move) {
        // complete this
    	board[move.toRow][move.toColumn] = pieceAt(move.fromRow,move.fromColumn);
    	board[move.fromRow][move.fromColumn] = null;	
    }

    public boolean inCheck(Player p) {
        return false;
    }

    public Player currentPlayer() { 
    	return player;
    	
    }

    public int numRows() {
    	return 8;
    }

    public int numColumns() {
      return 8;
    }

    public IChessPiece pieceAt(int row, int column) {
    	return board[row][column];
    }
    
    public boolean containsPiece(int row, int column){//checks to see whether the row and column contains a piece - returns true if there is a piece there, and false if not
    	
    	if(board[row][column].type() == null)
    		return false;
    	else
    		return true;
    	
    }
    
    public Player setNextPlayer(){
    	player = player.next();
    	return player;
    }
        public boolean castle(Move move){
    	if(pieceAt(move.fromRow,move.fromColumn).type() == "king" && pieceAt(move.toRow,move.toColumn).type() == "rook"){
    		if(((King) pieceAt(move.fromRow,move.fromColumn)).getMoved() == false && ((Rook) pieceAt(move.toRow,move.toColumn)).getMoved() == false){
    			Move kingMove = new Move(7,4,7,6); 
    			Move rookMove = new Move(7,7,7,5);
    			move(kingMove);
    			move(rookMove);
    			return true;
    		}
    	}
    		
    return false;	
    }
}
    
//    public void takePiece(Move move){
//    	
//        if(pieceAt(move.toRow,move.toColumn).player() != currentPlayer()){
//        	((ChessPiece) pieceAt(move.toRow,move.toColumn)).setTaken(true);
//        }
//        }
//        	
//        }
    

    // add other public or helper methods as needed


