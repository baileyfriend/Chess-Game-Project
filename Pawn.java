package chess;

public class Pawn extends ChessPiece {
	private boolean moved;

	public Pawn(Player player) {
		super(player);
		moved = false;
	}

	public String type() {
		return "pawn";
	}

	public void setMoved(boolean val) {
		moved = val;
	}

	public boolean isValidMove(Move move, IChessPiece[][] board) {
		if (super.isValidMove(move, board)) {
			if (this.player() == Player.BLACK) {

				try {
					if (board[move.toRow][move.toColumn].type() == null) {

					} else if (move.fromRow + 1 == move.toRow
							&& (move.fromColumn - 1 == move.toColumn || move.fromColumn + 1 == move.toColumn)
							&& board[move.toRow][move.toColumn].player() == Player.WHITE) {
						return true;
					}
				} catch (Exception NullPointerException) {
					if (move.fromRow + 2 == move.toRow && move.fromColumn == move.toColumn && !moved && !super.exists(move.fromRow + 1, move.fromColumn, board)) {
						return true;
					} else if (move.fromRow + 1 == move.toRow && move.fromColumn == move.toColumn) {
						return true;
					}

				}

			} else if (this.player() == Player.WHITE) {

				try {
					if (board[move.toRow][move.toColumn].type() == null) {

					} else if (move.fromRow - 1 == move.toRow
							&& (move.fromColumn + 1 == move.toColumn || move.fromColumn - 1 == move.toColumn)
							&& board[move.toRow][move.toColumn].player() == Player.BLACK) {
						return true;
					}
				} catch (Exception NullPointerException) {
					if (move.fromRow - 2 == move.toRow && move.fromColumn == move.toColumn && !moved && !super.exists(move.fromRow - 1, move.fromColumn, board)) {
						return true;
					} else if (move.fromRow - 1 == move.toRow && move.fromColumn == move.toColumn) {
						return true;
					}

				}

			}
		}
		return false;

	}

}