package chessFinal;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
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

	public static void movePiece(Square square, int x, int y) {
		
		squares[x][y].setPiece(square.getPiece());
		square.setPiece(null);
		
		square.repaintSquare();
		squares[x][y].repaintSquare();
	}
	
//	public static Point findSquareCoordsWithLabel(JLabel label) {
//		
//		int i, j=0;
//		boolean found = false;
//		Rectangle labelBounds = SwingUtilities.convertRectangle(label.getParent(), label.getBounds(), squares[0][0].getParent());
//		// Get the bounds of the JLabel
//		System.out.println("Optimize test: " + labelBounds);
//		
//		for (i = 0; i < 8; i++) {
//			for (j = 0; j < 8; j++) {
//				
//			    // Get the bounds of the JPanel
//			    Rectangle panelBounds = squares[i][j].getBounds();
//			    // Check if the bounds intersect
//			    if (labelBounds.intersects(panelBounds)) {
//			    	found = true;
//			    	break; // Breaks on overlap
//			    }
//			}
//			
//			if (found) break;	
//		}
//		
////		System.out.println(i + " " + j);
//		return (found) ? new Point(i, j): null;
//	}

	public static Point findSquareCoordsWithLabel(JLabel label) {
	    int maxIntersectionArea = 0;
	    Point bestMatch = null;
	    // Get the bounds of the JLabel
	    Rectangle labelBounds = SwingUtilities.convertRectangle(label.getParent(), label.getBounds(), squares[0][0].getParent());

	    for (int i = 0; i < 8; i++) {
	        for (int j = 0; j < 8; j++) {
	            
	            // Get the bounds of the JPanel
	            Rectangle panelBounds = squares[i][j].getBounds();
	            // Calculate the intersection area
	            Rectangle intersection = labelBounds.intersection(panelBounds);
	            int intersectionArea = intersection.width * intersection.height;
	            System.out.println(intersectionArea + " " + i + " " + j);

	            // Check if this is the largest intersection so far
	            if (!intersection.isEmpty() && intersectionArea > maxIntersectionArea) {
	                maxIntersectionArea = intersectionArea;
	                bestMatch = new Point(i, j);
	            }
	        }
	    }

	    // Debug output
	    if (bestMatch != null) {
	        System.out.println("Best match found at: " + bestMatch.x + ", " + bestMatch.y + " with area: " + maxIntersectionArea);
	    } else {
	        System.out.println("No intersection found.");
	    }

	    return bestMatch;
	}
	
}
