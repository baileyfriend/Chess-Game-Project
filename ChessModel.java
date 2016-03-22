package chess;

import java.util.*;

/**********************************************************************
 * Created by Nathan Hull and Bailey Freund on 3/16/16.
 *********************************************************************/
public class ChessModel implements IChessModel {
	
	
	/**
	 * 8x8 Board of IChessPieces, actual game
	 */
	private IChessPiece[][] board;
	
	
	/**
	 * Current player
	 */
	private Player player;
	
	
	/**
	 * ArrayList to keep track of taken White piees
	 */
	public ArrayList<IChessPiece> takenWhite;
	
	
	/**
	 * ArrayList to keep track of taken Black pieces
	 */
	public ArrayList<IChessPiece> takenBlack;

	
	/******************************************************************
     *Constructor for ChessModel - instantiates all pieces and their
     *respective locations on the board as well as the taken ArrayLists
     *to empty.
     *****************************************************************/
	
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

		board[0][1] = new Knight(Player.BLACK);
		board[0][6] = new Knight(Player.BLACK);
		board[7][1] = new Knight(Player.WHITE);
		board[7][6] = new Knight(Player.WHITE);

		board[0][2] = new Bishop(Player.BLACK);
		board[0][5] = new Bishop(Player.BLACK);
		board[7][2] = new Bishop(Player.WHITE);
		board[7][5] = new Bishop(Player.WHITE);

		board[0][4] = new King(Player.BLACK);
		board[0][3] = new Queen(Player.BLACK);
		board[7][4] = new King(Player.WHITE);
		board[7][3] = new Queen(Player.WHITE);

		player = Player.WHITE;
		takenWhite = new ArrayList<IChessPiece>();
		takenBlack = new ArrayList<IChessPiece>();
	}
	
	
	/******************************************************************
     * Returns whether the game is complete based on whether the player
     * is in CheckMate.
     *
     * @return {@code true} if complete, {@code false} otherwise.
     *****************************************************************/
	
	public boolean isComplete() {

		Move tempMove;
		IChessPiece tempPiece = null;
		boolean memory = false;

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (ChessPiece.exists(x, y, board)) {
					if (this.pieceAt(x, y).player() == this.currentPlayer()) {
						for (int z = 0; z < this.possibleMoves(x, y).size(); z++) {
							tempMove = new Move(this.possibleMoves(x, y).get(z).toRow,
									this.possibleMoves(x, y).get(z).toColumn, this.possibleMoves(x, y).get(z).fromRow,
									this.possibleMoves(x, y).get(z).fromColumn);
							if (ChessPiece.exists(this.possibleMoves(x, y).get(z).toRow,
									this.possibleMoves(x, y).get(z).toColumn, board)) {
								tempPiece = this.pieceAt(this.possibleMoves(x, y).get(z).toRow,
										this.possibleMoves(x, y).get(z).toColumn);
								memory = true;
							}
							this.move(this.possibleMoves(x, y).get(z));
							if (!this.inCheck(currentPlayer()))
								return false;
							this.move(tempMove);
							if (memory) {
								this.setPiece(tempPiece, x, y);
								memory = false;
							}
						}
					}
				}
			}
		}
		return true;
	}

	
	/******************************************************************
	 * Returns whether the piece at location
	 * {@code [move.fromRow, move.fromColumn]} is allowed to move to location
	 * {@code [move.fromRow, move.fromColumn]}.
	 *
	 * @param move a {@link W13project3.Move} object describing the move to be made.
	 * @return {@code true} if the proposed move is valid, {@code false}
	 *         otherwise.
	 * @throws IndexOutOfBoundsException
	 *             if either {@code [move.fromRow, move.fromColumn]} or
	 *             {@code [move.toRow,move.toColumn]} don't represent valid
	 *             locations on the board.
	 *****************************************************************/
	
	public boolean isValidMove(Move move) {
		boolean result = false;

		try {
			result = board[move.fromRow][move.fromColumn].isValidMove(move, board);
		} catch (Exception NullPointerException) {
			return false;
		}
		return result;

	}

	
	/******************************************************************
     * Moves the piece from location {@code [move.fromRow, move.fromColumn]} to location {@code [move.fromRow,
     * move.fromColumn]}.
     *
     * @param move a {@link W13project3.Move} object describing the move to be made.
     * @throws IndexOutOfBoundsException if either {@code [move.fromRow, move.fromColumn]} or {@code [move.toRow,
     *         move.toColumn]} don't represent valid locations on the board.
     *****************************************************************/
	
	public void move(Move move) {
		board[move.toRow][move.toColumn] = pieceAt(move.fromRow, move.fromColumn);
		pieceAt(move.toRow, move.toColumn).setMoved(true);
		board[move.fromRow][move.fromColumn] = null;
	}

	
	/******************************************************************
     * Report whether the current player p is in check.
     * @param  p {@link W13project3.Move} the Player being checked
     * @return {@code true} if the current player is in check, {@code false} otherwise.
     *****************************************************************/
	
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

	
	/******************************************************************
     * Creates an ArrayList of possible moves for every piece
     * loops on every spot on the board to see whether the piece at r c
     * will be able to move there
     * 
     * @param r The row of the piece being checked
     * @param c The column of piece being checked
     * @return results The ArrayList of possible moves of the piece in
     * question
     *****************************************************************/
	
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

	
	/******************************************************************
     * Report who the current player is
     * 
     * @return player the current player
     *****************************************************************/
	
	public Player currentPlayer() {
		return player;
	}

	
	/******************************************************************
     * Report number of rows
     * 
     * @return 8 - num of rows is always 8
     *****************************************************************/
	
	public int numRows() {
		return 8;
	}

	
	/******************************************************************
     * Report number of columns
     * 
     * @return 8 - num of columns is always 8
     *****************************************************************/
	
	public int numColumns() {
		return 8;
	}

	
	/******************************************************************
     * Returns the piece at the selected row and column
     * 
     * @return the piece at the selected row and column on the 2d array
     * of IChessPieces
     *****************************************************************/
	
	public IChessPiece pieceAt(int row, int column) {
		return board[row][column];
	}

	
	/******************************************************************
     * checks to see whether the row and column contains a piece 
     * - returns true if there is a piece there, 
     * and false if not
     * 
     * @return true if there is a piece in the selected spot
     * @return false if there is no piece in the seleceted spot
     *****************************************************************/
	
	public void setPiece(IChessPiece temp, int row, int col) {
		board[row][col] = temp;
	}

	
	/******************************************************************
	 * checks whether or not a cell contains a piece
	 * 
	 * @param row of desired cell
	 * @param column of desired cell
	 * @return whether or not there is a piece
	 */
	public boolean containsPiece(int row, int column) {
		// checks to see whether the row and column contains a piece - returns
		// true if there is a piece there, and false if not

		try {
			if (board[row][column].type() == null)
				return false;
			else
				return true;
		} catch (Exception NullPointerException) {
			return false;
		}

	}

	
	/******************************************************************
     * Changes player
     * 
     * @return player that is next
     *****************************************************************/
	
	public Player setNextPlayer() {
		player = player.next();
		return player;
	}

	
	/******************************************************************
     * Checks whether the king can castle on it's respective side
     * Checks for both sides, long and short castling
     * 
     * @return true if castling is possible
     * @return false if castling is not possible
     *****************************************************************/
	
	public boolean canCastle(Move move) {
		try {
			if (this.inCheck(this.currentPlayer())) {
				return false;
			}
			if (move.fromColumn > move.toColumn) {
				for (int x = move.fromColumn - 1; x > move.toColumn; x--) {
					if (ChessPiece.exists(move.fromRow, x, board)) {
						return false;
					}
				}
			} else if (move.fromColumn < move.toColumn) {
				for (int x = move.fromColumn + 1; x < move.toColumn; x++) {
					if (ChessPiece.exists(move.fromRow, x, board)) {
						return false;
					}
				}
			}
			if (move.fromRow == 7 && move.toColumn == 7) {
				if (((King) pieceAt(move.fromRow, move.fromColumn)).getMoved() == false
						&& ((Rook) pieceAt(move.toRow, move.toColumn)).getMoved() == false) {
					return true;
				}
			}

			if (move.fromRow == 7 && move.toColumn == 0) {
				if (((King) pieceAt(move.fromRow, move.fromColumn)).getMoved() == false
						&& ((Rook) pieceAt(move.toRow, move.toColumn)).getMoved() == false) {
					return true;
				}
			}

			if (move.fromRow == 0 && move.toColumn == 0) {
				if (((King) pieceAt(move.fromRow, move.fromColumn)).getMoved() == false
						&& ((Rook) pieceAt(move.toRow, move.toColumn)).getMoved() == false) {
					return true;
				}
			}

			if (move.fromRow == 0 && move.toColumn == 7) {
				if (((King) pieceAt(move.fromRow, move.fromColumn)).getMoved() == false
						&& ((Rook) pieceAt(move.toRow, move.toColumn)).getMoved() == false) {
					return true;
				}
			}
		} catch (Exception NullPointerException) {
			return false;
		}

		return false;
	}

	
	/******************************************************************
     * This method does the castling maneuver and returns whether
     * it is possible to do the castle maneuver
     * 
     * @return true if you can castle
     * @return false if you cannot castle
     *****************************************************************/
	
	public boolean castle(Move move) {

		if (this.inCheck(this.currentPlayer())) {
			return false;
		}
		if (move.fromColumn > move.toColumn) {
			for (int x = move.fromColumn - 1; x > move.toColumn; x--) {
				if (ChessPiece.exists(move.fromRow, x, board)) {
					return false;
				}
			}
		} else if (move.fromColumn < move.toColumn) {
			for (int x = move.fromColumn + 1; x < move.toColumn; x++) {
				if (ChessPiece.exists(move.fromRow, x, board)) {
					return false;
				}
			}
		}

		// white long castling
		if (move.fromRow == 7 && move.toColumn == 7) {
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
		if (move.fromRow == 7 && move.toColumn == 0) {
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
		if (move.fromRow == 0 && move.toColumn == 0) {
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
		if (move.fromRow == 0 && move.toColumn == 7) {
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

	
	/******************************************************************
     * Adds taken pieces to an ArrayList of their respective color
     * 
     * @param piece The piece that has been taken and added to the array
     * list
     *****************************************************************/
	
	public ArrayList<IChessPiece> getCurrentTaken() {
		if (currentPlayer().equals(Player.WHITE)) {
			return takenWhite;
		} else {
			return takenBlack;
		}
	}

	
	/******************************************************************
     * Gets the arrayList of the Taken pieces and returns it
     * 
     * @return takenWhite array of taken white pieces if player is white
     * @return takenBlack array of taken white pieces if player is black
     *****************************************************************/
	
	public ArrayList<IChessPiece> getNextTaken() {
		if (currentPlayer().equals(Player.WHITE)) {
			return takenBlack;
		} else {
			return takenWhite;
		}
	}

	
	/******************************************************************
     * Loops to find if there is a pawn in one of the "end zones"
     * if a pawn is in one of the end zones, then returns true, if not
     * returns false. Also calls the getTakenArrays before returning so
     * that the panel can get the necessary data to display to the 
     * user
     * 
     * @return player the current player
     *****************************************************************/
	
	public boolean pawnAtEnd() {
		int row = 0;
		int col = 0;
		try {
			while (col < 8) {
				if (ChessPiece.exists(row, col, board)) {
					if (board[row][col].type().equals("pawn")) {
						return true;
					}
				}
				col++;
			}

			row = 7;
			col = 0;

			while (col < 8) {
				if (ChessPiece.exists(row, col, board)) {
					if (board[row][col].type().equals("pawn")) {
						return true;
					}
				}
				col++;
			}
		} catch (

		ArrayIndexOutOfBoundsException e)

		{
			return false;
		} catch (

		NullPointerException e)

		{
			return false;
		}
		return false;

	}
	

	/******************************************************************
     * Swaps the pawn with the chosen chesspiece
     * 
     * @param piece piece chosen by user to swap
     * @param r row of piece being swapped
     * @param c col of piece being swapped
     *****************************************************************/
	
	public void pawnSwap(IChessPiece piece, int r, int c) {
		board[r][c] = piece;
	}
}
