package chess;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ChessPanel extends JPanel {

	private JButton[][] board;
	private ChessModel model;
	private IChessPiece piece;

	private int SIZE = 8;
	private boolean moving;
	private Move move;
	private int initialRow;
	private int initialCol;

	private JButton butQuit;
	private JButton butReset;

	private ButtonListener buttonListener = new ButtonListener();

	
	/**
     * Constructs the ChessPanel and all necessary components
     * including the board (8x8)
     */
	
	public ChessPanel() {
		JPanel center = new JPanel();
		JPanel top = new JPanel();
		JPanel bottom = new JPanel();

		center.setLayout(new GridLayout(SIZE, SIZE));
		board = new JButton[SIZE][SIZE];
		model = new ChessModel();

		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				board[row][col] = new JButton("");
				board[row][col].addActionListener(buttonListener);
				board[row][col].setPreferredSize(new Dimension(80, 80));
				board[row][col].setOpaque(true);
				center.add(board[row][col]);
			}
		}

		bottom.setLayout(new GridLayout(4, 1));

		butQuit = new JButton("Quit");
		butQuit.addActionListener(buttonListener);
		bottom.add(butQuit);

		butReset = new JButton("Reset");
		butReset.addActionListener(buttonListener);
		bottom.add(butReset);

		add(top, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(bottom, BorderLayout.SOUTH);

		moving = false;
		model.currentPlayer();

		piece = null;
		move = null;

		displayBoard();
	}

	
	/**
     * Displays the board to the GUI by use of a double nested
     * for loop which sets the color and the image icons
     */
	
	private void displayBoard() {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {

				// paint-specific commands
				board[row][col].setOpaque(true);
				board[row][col].setBorderPainted(false);

				if ((row + col) % 2 == 0) {
					board[row][col].setBackground(Color.RED);
				} else
					board[row][col].setBackground(Color.GRAY);

				if (model.containsPiece(row, col)) {
					Image img = null;
					try {
						img = ImageIO.read(getClass().getResource("/resources/" + model.pieceAt(row, col).type()
								+ model.pieceAt(row, col).player() + ".png"));
						board[row][col].setIcon(new ImageIcon(img));
					} catch (IOException e) {
						System.out.println("IO EXCEPTION ERROR");
						e.printStackTrace();
					}
					board[row][col].setIcon(new ImageIcon(img));
				} else
					board[row][col].setIcon(null);

				if (model.pieceAt(row, col) == piece && piece != null) {
					board[row][col].setBackground(Color.GREEN);
				}
			}
		}

		for (int x = 0; x < model.possibleMoves(initialRow, initialCol).size(); x++) {
			board[model.possibleMoves(initialRow, initialCol).get(x).toRow][model.possibleMoves(initialRow, initialCol)
					.get(x).toColumn].setBackground(Color.YELLOW);
		}

		try {
			if (piece.type().equals("king")) {
				Move tempMove1 = new Move(initialRow, initialCol, initialRow, initialCol - 3);
				Move tempMove2 = new Move(initialRow, initialCol, initialRow, initialCol + 4);
				if (model.canCastle(tempMove1)) {
					board[initialRow][initialCol - 3].setBackground(Color.BLUE);
				}
				if (model.canCastle(tempMove2)) {
					board[initialRow][initialCol + 4].setBackground(Color.BLUE);
				}
			}
		} catch (Exception NullPointerException) {

		}
	}

	
	/**
     * Creates the ButtonListener which listens for input from
     * the user and determines which methods to call from the 
     * ChessModel class.
     * 
     */
	
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			// identifies which tile was selected
			for (int row = 0; row < SIZE; row++) {
				for (int col = 0; col < SIZE; col++) {
					// if user selects piece
					if (board[row][col] == event.getSource() && model.containsPiece(row, col)
							&& model.pieceAt(row, col).player() == model.currentPlayer()) {

						// checks for castle
						if (piece != null)
							if (piece.type().equals("king") && model.pieceAt(row, col).type().equals("rook")) {
								move = new Move(initialRow, initialCol, row, col);
								if (model.castle(move)) {
									moving = false;
									piece = null;
									model.setNextPlayer();
								}
							}

						// piece selected, recorded
						piece = model.pieceAt(row, col);
						initialRow = row;
						initialCol = col;
						moving = true;
					}
					// piece already selected, get where to move
					else if (board[row][col] == event.getSource() && moving) {

						move = new Move(initialRow, initialCol, row, col);

						// makes sure move is valid
						if (model.isValidMove(move)) {
							if (model.containsPiece(row, col)) {
								if (model.pieceAt(row, col).player() != model.currentPlayer()
										&& !model.pieceAt(row, col).type().equals("pawn")) {
									model.getNextTaken().add(model.pieceAt(row, col));
								}
							}
							IChessPiece temp = model.pieceAt(row, col);
							model.move(move);
							// in check?
							if (model.inCheck(model.currentPlayer())) {
								Move back = new Move(move.toRow, move.toColumn, move.fromRow, move.fromColumn);
								model.move(back);
								model.setPiece(temp, row, col);
								JOptionPane.showMessageDialog(null, "Move invalid: in check.");
								model.getNextTaken().remove(model.getNextTaken().size() - 1);
							}
							// move is good, taking destination piece
							else {
								moving = false;
								piece = null;
								model.setNextPlayer();
								// if the move would put them in check, reverse everything
								if (model.inCheck(model.currentPlayer())) {
									String checkMessage = "";
									checkMessage += model.currentPlayer() + " is in check.";
									JOptionPane.showMessageDialog(null, checkMessage);
									// check if checkMate
									if (model.isComplete()) {
										String winnerString = "";
										winnerString = model.setNextPlayer() + " has won!";
										JOptionPane.showMessageDialog(null, winnerString);
										for(int d = 0; d < 8; d++){
											for(int e = 0; e < 8; e++){
												board[d][e].setEnabled(false);
											}
										}
									}
								}

								// if a pawn made it to the end
								if (model.pawnAtEnd()) {
									ArrayList<IChessPiece> promotionChoices = new ArrayList<IChessPiece>();
									// creates arrayList of available pieces
									for (int x = 0; x < model.getNextTaken().size(); x++) {
										promotionChoices.add(model.getNextTaken().get(x));
									}
									// puts arrayList into array for formatting
									IChessPiece[] arrayChoices = new IChessPiece[promotionChoices.size()];
									promotionChoices.toArray(arrayChoices);
									IChessPiece promotion = (IChessPiece) JOptionPane.showInputDialog(null,
											"Promote your pawn to:", "Pawn Promotion", JOptionPane.PLAIN_MESSAGE, null,
											arrayChoices, arrayChoices);

									// if they hit cancel
									if (promotion == null) {
										JOptionPane.showMessageDialog(null, "Your pawn will promote to null.");
										model.pawnSwap(promotion, row, col);
										displayBoard();
									} else {
										JOptionPane.showMessageDialog(null,
												"You pawn will be promoted to a " + promotion.type());
										model.pawnSwap(promotion, row, col);
										displayBoard();
									}
								}
							}
						}
					}

				}
			}

			// button events
			if (event.getSource() == butQuit)
				System.exit(0);

			if (event.getSource() == butReset) {
				model = new ChessModel();
				ChessGUI.reset();
			}

			displayBoard();
		}
	}
}