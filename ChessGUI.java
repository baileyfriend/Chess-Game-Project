/**********************************************************************
 * Main class of ChessGame, sets up JFrame, puts the ChessPanel
 * in it to handle GUI
 *********************************************************************/

package chess;

import java.awt.Color;

import javax.swing.JFrame;

public class ChessGUI {
	
	/******************************************************************
	 * Main that creates a JFrame that holds the MineSweeperPanel GUI
	 *****************************************************************/
	public static void main (String[] args)
	{
		JFrame frame = new JFrame ("MineSweeper");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		ChessPanel panel = new ChessPanel();
		
		frame.getContentPane().add(panel);
		frame.setSize(900, 900);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
