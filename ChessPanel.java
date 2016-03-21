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
	
	
	
	public ChessPanel(){
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
				board[row][col].setPreferredSize(new Dimension(80,80));
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
		
		displayBoard();
	}
	
	
	
	private void displayBoard(){
		for(int row = 0; row < SIZE; row++){
			for(int col = 0; col < SIZE; col++){
				
				//paint-specific commands
				board[row][col].setOpaque(true);
				board[row][col].setBorderPainted(false);
				
				if((row + col) % 2 == 0){
					board[row][col].setBackground(Color.RED);
				}
				else
					board[row][col].setBackground(Color.WHITE);
					
				if(model.containsPiece(row, col)){
					Image img = null;
					try {
						img = ImageIO.read(getClass().
								getResource("/resources/" +
								model.pieceAt(row, col).type() +
								model.pieceAt(row, col).
								player() +".png"));
						board[row][col].setIcon(new ImageIcon(img));
					} catch (IOException e) {
						System.out.println("IO EXCEPTION ERROR");
						e.printStackTrace();
					}
					board[row][col].setIcon(new ImageIcon(img));
				}else
					board[row][col].setIcon(null);
				
				if(model.pieceAt(row, col) == piece && piece != null){
					board[row][col].setBackground(Color.GREEN);
				}
			}
		}
	}
	
	
	
	private class ButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent event){
			
			// identifies which tile was selected
			for (int row = 0; row < SIZE; row++){
				for (int col = 0; col < SIZE; col++){
					if (board[row][col] == event.getSource() && 
							model.containsPiece(row, col) && 
							model.pieceAt(row, col).player() == 
							model.currentPlayer()){
						// selects piece, is moving
						piece = model.pieceAt(row, col);
						initialRow = row;
						initialCol = col;
						moving = true;
						//CASTLING TO DO
//						if(piece.type().equals("king") && model
//								.pieceAt(row, col).type().equals("rook")){
//							if(model.castle(move)){
//								moving = false;
//								piece = null;
//								model.setNextPlayer();
//								System.out.println("It worked");
//							}
//						}
					}
					else if (board[row][col] == event.getSource()
							&& moving){
						move = new Move(initialRow, initialCol, row,
								col);

						if(model.isValidMove(move)){
							model.move(move);
							if(model.inCheck(model.currentPlayer())){
					        	Move back = new Move(move.toRow, move.toColumn, 
					        			move.fromRow, move.fromColumn);
					    		model.move(back);
					    		System.out.println("CHECK");
					    	}
							else{
								moving = false;
								piece = null;
								model.setNextPlayer();
							}
						}
					}
				}
			}
			
			//button events
			if(event.getSource() == butQuit)
				System.exit(0);
			
			if(event.getSource() == butReset){
				model = new ChessModel();
			}
			
			displayBoard();
		}
	}
}