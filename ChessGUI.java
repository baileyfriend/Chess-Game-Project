/**********************************************************************
 * Main class of ChessGame, sets up JFrame, puts the ChessPanel
 * in it to handle GUI
 *********************************************************************/

package chess;

import javax.swing.JFrame;

public class ChessGUI {
	
	private static JFrame frame;
	
	/******************************************************************
	 * Main that creates a JFrame that holds the Chess GUI
	 *****************************************************************/
	public static void main (String[] args)
	{
		frame = new JFrame ("Chess");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		ChessPanel panel = new ChessPanel();
		
		frame.getContentPane().add(panel);
		frame.setSize(900, 900);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	/******************************************************************
	 * Sets up the reset of the GUI with the reset button
	 *****************************************************************/
	public static void reset(){
		frame.dispose();
		JFrame frame = new JFrame ("Chess");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		ChessPanel panel = new ChessPanel();
		
		frame.getContentPane().add(panel);
		frame.setSize(900, 900);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
