package chess;

import java.util.*;

/**
 * Created by Nathan Hull and Bailey Freund on 3/16/16.
 */
public class ChessModel implements IChessModel {
	private IChessPiece[][] board;
	private Player player;
	ArrayList<IChessPiece> takenWhite;
	ArrayList<IChessPiece> takenBlack;

	public ChessModel() {
		board = new IChessPiece[8][8];

		// create and place pawns
		for (int x = 0; x < 8; x++) {
			board[1][x] = new Pawn(Player.BLACK);
		}
		for (int x = 0; x < 8; x++) {
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

		player = Player.WHITE;
		takenWhite = new ArrayList<IChessPiece>();
		takenBlack = new ArrayList<IChessPiece>();
	}

	public boolean isComplete() {
		return false;
	}

	/**
	 * Returns whether the piece at location
	 * {@code [move.fromRow, move.fromColumn]} is allowed to move to location
	 * {@code [move.fromRow, move.fromColumn]}.
	 *
	 * @param move
	 *            a {@link W13project3.Move} object describing the move to be
	 *            made.
	 * @return {@code true} if the proposed move is valid, {@code false}
	 *         otherwise.
	 * @throws IndexOutOfBoundsException
	 *             if either {@code [move.fromRow, move.fromColumn]} or
	 *             {@code [move.toRow,
	 *                                   move.toColumn]} don't represent valid
	 *             locations on the board.
	 */
	public boolean isValidMove(Move move) {
		
		return board[move.fromRow][move.fromColumn].isValidMove(move, board);
		
	}

	public void move(Move move) {
		board[move.toRow][move.toColumn] = pieceAt(move.fromRow, move.fromColumn);
		pieceAt(move.toRow, move.toColumn).setMoved(true);
		board[move.fromRow][move.fromColumn] = null;
	}

	public boolean inCheck(Player p) {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (this.containsPiece(x, y) && board[x][y].player() != p) {
					for (int z = 0; z < this.possibleMoves(x, y).size(); z++) {
						int tRow = this.possibleMoves(x, y).get(z).toRow;
						int tCol = this.possibleMoves(x, y).get(z).toColumn;
						if (this.containsPiece(tRow, tCol)) {
							if (board[tRow][tCol].type() == "king" && board[tRow][tCol].player() == p)
								return true;
						}
					}
				}
			}
		}

		return false;
	}

	public ArrayList<Move> possibleMoves(int r, int c) {
		ArrayList<Move> results = new ArrayList<Move>();
		Move test;

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				test = new Move(r, c, x, y);
				if (this.isValidMove(test)) {
					results.add(test);
				}
			}
		}

		return results;
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

	public boolean containsPiece(int row, int column) {// checks to see whether
														// the row and column
														// contains a piece -
														// returns true if there
														// is a piece there, and
														// false if not

		try {
			if (board[row][column].type() == null)
				return false;
			else
				return true;
		} catch (Exception NullPointerException) {
			return false;
		}

	}

	public Player setNextPlayer() {
		player = player.next();
		return player;
	}
	
	public boolean canCastle(Move move){
		if (move.fromRow == 7
				&& move.toColumn == 7) {
			if (((King) pieceAt(move.fromRow, move.fromColumn)).getMoved() == false
					&& ((Rook) pieceAt(move.toRow, move.toColumn)).getMoved() == false) {
				return true;
			}
		}
		
		if (move.fromRow == 7
				&& move.toColumn == 0) {
			if (((King) pieceAt(move.fromRow, move.fromColumn)).getMoved() == false
					&& ((Rook) pieceAt(move.toRow, move.toColumn)).getMoved() == false) {
				return true;
			}
		}
		
		if (move.fromRow == 0
				&& move.toColumn == 0) {
			if (((King) pieceAt(move.fromRow, move.fromColumn)).getMoved() == false
					&& ((Rook) pieceAt(move.toRow, move.toColumn)).getMoved() == false) {
				return true;
			}
		}
		
		if (move.fromRow == 0
				&& move.toColumn == 7) {
			if (((King) pieceAt(move.fromRow, move.fromColumn)).getMoved() == false
					&& ((Rook) pieceAt(move.toRow, move.toColumn)).getMoved() == false) {
				return true;
			}
		}
		
		return false;
	}

	public boolean castle(Move move) {
		// white long castling
		if (move.fromRow == 7
				&& move.toColumn == 7) {
			if (((King) pieceAt(move.fromRow, move.fromColumn)).getMoved() == false
					&& ((Rook) pieceAt(move.toRow, move.toColumn)).getMoved() == false) {
				Move kingMove = new Move(7, 3, 7, 5);
				Move rookMove = new Move(7, 7, 7, 4);
				move(kingMove);
				move(rookMove);
				return true;
			}
		}

		// white short castling
		if (move.fromRow == 7
				&& move.toColumn == 0) {
			if (((King) pieceAt(move.fromRow, move.fromColumn)).getMoved() == false
					&& ((Rook) pieceAt(move.toRow, move.toColumn)).getMoved() == false) {
				Move kingMove = new Move(7, 3, 7, 1);
				Move rookMove = new Move(7, 0, 7, 2);
				move(kingMove);
				move(rookMove);
				return true;
			}
		}

		// black short castling
		if (move.fromRow == 0
				&& move.toColumn == 0) {
			if (((King) pieceAt(move.fromRow, move.fromColumn)).getMoved() == false
					&& ((Rook) pieceAt(move.toRow, move.toColumn)).getMoved() == false) {
				Move kingMove = new Move(0, 3, 0, 1);
				Move rookMove = new Move(0, 0, 0, 2);
				move(kingMove);
				move(rookMove);
				return true;
			}
		}

		// black long castling
		if (move.fromRow == 0
				&& move.toColumn == 7) {
			if (((King) pieceAt(move.fromRow, move.fromColumn)).getMoved() == false
					&& ((Rook) pieceAt(move.toRow, move.toColumn)).getMoved() == false) {
				Move kingMove = new Move(0, 3, 0, 5);
				Move rookMove = new Move(0, 7, 0, 4);
				move(kingMove);
				move(rookMove);
				return true;

			}
		}

		return false;
	}

	public void addToTaken(IChessPiece piece) {

		if (piece.type() != null && Player.WHITE.equals(currentPlayer())) {
			takenWhite.add(piece);
		} else if (piece.type() != null && Player.BLACK.equals(currentPlayer())) {
			takenBlack.add(piece);
		}
	}

	public ArrayList<IChessPiece> getTakenArrays() {
		if (currentPlayer().equals(Player.WHITE)) {
			return takenWhite;
		} else {
			return takenBlack;
		}
	}
}
