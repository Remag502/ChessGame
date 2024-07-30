package chessReworked;

import java.awt.EventQueue;
import java.util.Scanner;

import javax.swing.JLabel;

public class Main {
	
	public static void main(String[] args) {

//		MainDisplay.setSquares(); // Main thread
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainDisplay frame = new MainDisplay();
					frame.setVisible(true); // Display and squares needs to be set so the size of each panel can be measured for the pieces
					MainDisplay.setPiecePictures(); // EventQueue thread
					MainDisplay.updateDisplay();
					System.out.println("test");

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println("test");

//		Scanner sc = new Scanner(System.in);
//		while (true) {
//			String in = sc.nextLine();
//			int moves[] = inputToMove(in);
//			board.movePiece(moves[0], moves[1]);
//			board.printBoard();
//		} 
		// Find a way to implement this
		// Make JFrame a dynamic class and make it a JPanel instead
	}

	
}
