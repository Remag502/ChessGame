package chessFinal;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Board {

	// Class stores data of JPanels
	// Handles logical components of game
	private static Square[][] squares;

	// Uses given JPanel to display contents of board
	public Board(JPanel boardPanel) {
		
		squares = new Square[8][8];
		boardPanel.setBorder(new EmptyBorder(0, 5, 5, 5));
		boardPanel.setLayout(new GridLayout(8, 8, 0, 0)); // Chess layout
		initializeBackground(boardPanel);
		setupBoard();
	}

	private void initializeBackground(JPanel boardPanel) {

		int number = 0; // Used to have alternating colors
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				// Tinkered w/ line under to get correct color order
				boolean defaultColor = ((i + number) % 2 == 1) ? false : true;
				squares[i][j] = new Square(defaultColor, i, j);
				number++;
				// Add square to our JFrame
				boardPanel.add(squares[i][j], i, j);

			}
		}

	}

	private void setupBoard() {
		// White pieces
		squares[0][0].setPiece(new Piece(Piece.ROOK, true));
		squares[0][1].setPiece(new Piece(Piece.KNIGHT, true));
		squares[0][2].setPiece(new Piece(Piece.BISHOP, true));
		squares[0][3].setPiece(new Piece(Piece.QUEEN, true));
		squares[0][4].setPiece(new Piece(Piece.KING, true));
		squares[0][5].setPiece(new Piece(Piece.BISHOP, true));
		squares[0][6].setPiece(new Piece(Piece.KNIGHT, true));
		squares[0][7].setPiece(new Piece(Piece.ROOK, true));
		squares[1][0].setPiece(new Piece(Piece.PAWN, true));
		squares[1][1].setPiece(new Piece(Piece.PAWN, true));
		squares[1][2].setPiece(new Piece(Piece.PAWN, true));
		squares[1][3].setPiece(new Piece(Piece.PAWN, true));
		squares[1][4].setPiece(new Piece(Piece.PAWN, true));
		squares[1][5].setPiece(new Piece(Piece.PAWN, true));
		squares[1][6].setPiece(new Piece(Piece.PAWN, true));
		squares[1][7].setPiece(new Piece(Piece.PAWN, true));
		// Black pieces
		squares[7][0].setPiece(new Piece(Piece.ROOK, false));
		squares[7][1].setPiece(new Piece(Piece.KNIGHT, false));
		squares[7][2].setPiece(new Piece(Piece.BISHOP, false));
		squares[7][3].setPiece(new Piece(Piece.QUEEN, false));
		squares[7][4].setPiece(new Piece(Piece.KING, false));
		squares[7][5].setPiece(new Piece(Piece.BISHOP, false));
		squares[7][6].setPiece(new Piece(Piece.KNIGHT, false));
		squares[7][7].setPiece(new Piece(Piece.ROOK, false));
		squares[6][0].setPiece(new Piece(Piece.PAWN, false));
		squares[6][1].setPiece(new Piece(Piece.PAWN, false));
		squares[6][2].setPiece(new Piece(Piece.PAWN, false));
		squares[6][3].setPiece(new Piece(Piece.PAWN, false));
		squares[6][4].setPiece(new Piece(Piece.PAWN, false));
		squares[6][5].setPiece(new Piece(Piece.PAWN, false));
		squares[6][6].setPiece(new Piece(Piece.PAWN, false));
		squares[6][7].setPiece(new Piece(Piece.PAWN, false));
	}
	
	public void resizeBoard(JPanel boardPanel) {
//		 Gets a random square to resize each image
		boardPanel.setBounds(0, 0, Display.layeredPane.getWidth(), Display.layeredPane.getHeight());
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
