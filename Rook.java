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

	public boolean getMoved() {
		return moved;
	}

	public boolean isValidMove(Move move, IChessPiece[][] board) {
		if (super.isValidMove(move, board)) {

			int fr = move.fromRow;
			int tr = move.toRow;
			int fc = move.fromColumn;
			int tc = move.toColumn;

			if (fr != tr && fc == tc) {
				if (fr > tr) {
					for (int x = tr + 1; x < fr; x++) {
						if (super.exists(x, fc, board)) {
							return false;
						}
					}
					return true;
				} else {
					for (int x = fr + 1; x < tr; x++) {
						if (super.exists(x, fc, board)) {
							return false;
						}
					}
					return true;
				}
			} else if (fr == tr && fc != tc) {
				if (fc > tc) {
					for (int x = tc + 1; x < fc; x++) {
						if (super.exists(fr, x, board)) {
							return false;
						}
					}
					return true;
				} else {
					for (int x = fc + 1; x < tc; x++) {
						if (super.exists(fr, x, board)) {
							return false;
						}
					}
					return true;
				}
			} else {
				return false;
			}
		}
		return false;
	}
}