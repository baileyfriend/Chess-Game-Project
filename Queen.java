package chess;

public class Queen extends ChessPiece {

	public Queen(Player player) {
		super(player);
		moved = false;
		// super(isValidMove()); -- want to call the super's isValidMove to
		// check for the conditions set in superClass ChessPiece - not exactly
		// sure how to do that yet
	}

	public String type() {
		return "queen";
	}

	public void setMoved(boolean val) {
		moved = val;
	}

	public boolean isValidMove(Move move, IChessPiece[][] board) {
		if (super.isValidMove(move, board)) {
			int rowDiff = Math.abs(move.fromRow - move.toRow);
			// checks what the absolute value of the difference between the from
			// row to the to row is

			int colDiff = Math.abs(move.fromColumn - move.toColumn);
			// checks what the absolute value of the difference between the from
			// col to the to col is

			int fr = move.fromRow;
			int tr = move.toRow;
			int fc = move.fromColumn;
			int tc = move.toColumn;

			if (rowDiff == colDiff) {
				if (fr < tr) {
					if (fc < tc) {
						for (int x = 1; x < rowDiff; x++) {
							if (super.exists(fr + x, fc + x, board)) {
								return false;
							}
						}
						return true;
					} else {
						for (int x = 1; x < rowDiff; x++) {
							if (super.exists(fr + x, fc - x, board)) {
								return false;
							}
						}
						return true;
					}
				} else if (fr > tr) {
					if (fc < tc) {
						for (int x = 1; x < rowDiff; x++) {
							if (super.exists(fr - x, fc + x, board)) {
								return false;
							}
						}
						return true;
					} else {
						for (int x = 1; x < rowDiff; x++) {
							if (super.exists(fr - x, fc - x, board)) {
								return false;
							}
						}
						return true;
					}
				}
			}

			fr = move.fromRow;
			tr = move.toRow;
			fc = move.fromColumn;
			tc = move.toColumn;

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
