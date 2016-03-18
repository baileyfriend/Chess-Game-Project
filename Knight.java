package chess;

public class Knight extends ChessPiece {
	
	public Knight(Player player) {
		super(player);
	}

	public String type() {
		return "knight";
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
