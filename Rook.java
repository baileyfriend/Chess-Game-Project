package chess;

public class Rook extends ChessPiece {

	private boolean moved;

	public Rook(Player player) {
		super(player);
		moved = false;
	}

	public String type() {
		return "rook";
	}

	public void setMoved(boolean val) {
		moved = val;
	}

	public boolean isValidMove(Move move, IChessPiece[][] board) {

		if (move.fromColumn != move.toColumn &&
				move.fromRow == move.toRow)
			return true;
		else if (move.fromColumn == move.toColumn &&
				move.fromRow != move.toRow)
			return true;
		else
			return false;
		
	}
}
