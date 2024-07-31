package chessFinal;

import javax.swing.JPanel;

public class Board {

	// Class stores data of JPanels
	// Handles logical components of game
	private static Square[][] squares = new Square[8][8];

	// Uses given JPanel to display contents of board
	public Board(JPanel contentPane) {
		initializeBackground(contentPane);
	}

	private void initializeBackground(JPanel contentPane) {

		int number = 0; // Used to have alternating colors
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				// Tinkered w/ line under to get correct color order
				boolean defaultColor = ((i + number) % 2 == 1) ? false : true;
				squares[i][j] = new Square(defaultColor, i, j);
				number++;
				// Add square to our JFrame
				contentPane.add(squares[i][j], i, j);

			}
		}

	}

	public void resizeImages() {
//		 Gets a random square to resize each image
		if (squares[0][0] != null)
			squares[0][0].setPiecePictures();
		repaintBoard();
	}

	public void repaintBoard() {
		// test code
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				squares[i][j].repaintSquare();
			}
		}

	}

}
