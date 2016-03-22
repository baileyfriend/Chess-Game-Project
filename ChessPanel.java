package chess;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

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

	private void displayBoard() {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {

				// paint-specific commands
				board[row][col].setOpaque(true);
				board[row][col].setBorderPainted(false);

				if ((row + col) % 2 == 0) {
					board[row][col].setBackground(Color.RED);
				} else
					board[row][col].setBackground(Color.WHITE);

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

		Move tempMove1 = new Move(initialRow, initialCol, initialRow, initialCol - 3);
		Move tempMove2 = new Move(initialRow, initialCol, initialRow, initialCol + 4);
		if (model.containsPiece(initialRow, initialCol)) {
			if (model.pieceAt(initialRow, initialCol).type().equals("king") && model.canCastle(tempMove1)) {
				board[initialRow][initialCol - 3].setBackground(Color.YELLOW);
			}
			if (model.pieceAt(initialRow, initialCol).type().equals("king") && model.canCastle(tempMove2)) {
				board[initialRow][initialCol + 4].setBackground(Color.YELLOW);
			}
		}
	}

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
									System.out.println("It worked");
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
							model.move(move);
							// in check?
							if (model.inCheck(model.currentPlayer())) {
								Move back = new Move(move.toRow, move.toColumn, move.fromRow, move.fromColumn);
								model.move(back);
								System.out.println("CHECK");
							}
							// move is good, taking destination piece
							else {
								moving = false;
								model.addToTaken(piece);
								piece = null;
								model.setNextPlayer();
							}
						}
					}

				}
			}

//			if (model.pawnAtEnd().type().equals("pawn")) {
//				JOptionPane.showInputDialog("Choose the piece to bring back.", model.takenBlack);
//			}

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