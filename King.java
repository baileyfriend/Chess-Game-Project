package chess;

public class King extends ChessPiece {
	
	private boolean moved;

	public King(Player player) {
		super(player);
		moved = false;
	}

	public String type() {
		return "king";
	}

	public void setMoved(boolean val) {
		moved = val;
	}

	public boolean isValidMove(Move move, IChessPiece[][] board) {
		if(super.isValidMove(move, board)){
		if (Math.abs(move.toColumn - move.fromColumn) <= 1 &&
				Math.abs(move.toRow - move.fromRow) <= 1)
			return true;
		else
			return false;
		}
		return false;
	}
}
