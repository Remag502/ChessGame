package chessFinal;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class Board {

	// Class stores data of JPanels
	// Handles logical components of game
	private static int trackMoves = 0;
	
	private Square[][] squares;
	private boolean whiteTurn;
	private int moves;

	public Board() {
		whiteTurn = true;
		squares = new Square[8][8];
		moves = 0;
	}

	// Uses given JPanel to display contents of board
	public Board(JPanel boardPanel) {

		whiteTurn = true;
		squares = new Square[8][8];
		moves = 0;
		boardPanel.setBorder(new EmptyBorder(0, 5, 5, 5));
		boardPanel.setLayout(new GridLayout(8, 8, 0, 0)); // Chess layout
		initializeBackground(boardPanel);
		setupBoard();
	}

	private void initializeBackground(JPanel boardPanel) {

		int number = 0; // Used for alternating colors
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {
				// Tinkered w/ line under to get correct color order
				boolean defaultColor = ((i + number) % 2 == 1) ? false : true;
				squares[j][i] = new Square(defaultColor, j, i); // Follows x,y format with gui (Tinkered)
				number++;
				// Add square to our JFrame
				boardPanel.add(squares[j][i]);

			}
		}

	}

	private void setupBoard() {

		squares[0][0].setPiece(new Piece(Piece.ROOK, true));
		squares[1][0].setPiece(new Piece(Piece.KNIGHT, true));
		squares[2][0].setPiece(new Piece(Piece.BISHOP, true));
		squares[3][0].setPiece(new Piece(Piece.QUEEN, true));
		squares[4][0].setPiece(new Piece(Piece.KING, true));
		squares[5][0].setPiece(new Piece(Piece.BISHOP, true));
		squares[6][0].setPiece(new Piece(Piece.KNIGHT, true));
		squares[7][0].setPiece(new Piece(Piece.ROOK, true));
		squares[0][1].setPiece(new Piece(Piece.PAWN, true));
		squares[1][1].setPiece(new Piece(Piece.PAWN, true));
		squares[2][1].setPiece(new Piece(Piece.PAWN, true));
		squares[3][1].setPiece(new Piece(Piece.PAWN, true));
		squares[4][1].setPiece(new Piece(Piece.PAWN, true));
		squares[5][1].setPiece(new Piece(Piece.PAWN, true));
		squares[6][1].setPiece(new Piece(Piece.PAWN, true));
		squares[7][1].setPiece(new Piece(Piece.PAWN, true));
		// Black pieces
		squares[0][7].setPiece(new Piece(Piece.ROOK, false));
		squares[1][7].setPiece(new Piece(Piece.KNIGHT, false));
		squares[2][7].setPiece(new Piece(Piece.BISHOP, false));
		squares[3][7].setPiece(new Piece(Piece.QUEEN, false));
		squares[4][7].setPiece(new Piece(Piece.KING, false));
		squares[5][7].setPiece(new Piece(Piece.BISHOP, false));
		squares[6][7].setPiece(new Piece(Piece.KNIGHT, false));
		squares[7][7].setPiece(new Piece(Piece.ROOK, false));
		squares[0][6].setPiece(new Piece(Piece.PAWN, false));
		squares[1][6].setPiece(new Piece(Piece.PAWN, false));
		squares[2][6].setPiece(new Piece(Piece.PAWN, false));
		squares[3][6].setPiece(new Piece(Piece.PAWN, false));
		squares[4][6].setPiece(new Piece(Piece.PAWN, false));
		squares[5][6].setPiece(new Piece(Piece.PAWN, false));
		squares[6][6].setPiece(new Piece(Piece.PAWN, false));
		squares[7][6].setPiece(new Piece(Piece.PAWN, false));
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

		if (!legalMove(square, x, y))
			return;
		// Check special cases
		checkSpecialCases(square.getPiece(), x, y);
		// Change piece
		Display.getBoard().squares[x][y].setPiece(square.getPiece());
		square.setPiece(null);

		square.repaintSquare();
		Display.getBoard().squares[x][y].repaintSquare();

		addBoardToHistory();
	}

	public static void forceMovePiece(Square square, int x, int y) {

		// Change piece
		Display.getBoard().squares[x][y].setPiece(square.getPiece());
		square.setPiece(null);

		square.repaintSquare();
		Display.getBoard().squares[x][y].repaintSquare();
	}

	public static Point findSquareCoordsWithLabel(JLabel label) {
		int maxIntersectionArea = 0;
		Point bestMatch = null;
		// Get the bounds of the JLabel
		Rectangle labelBounds = SwingUtilities.convertRectangle(label.getParent(), label.getBounds(),
				Display.getBoard().squares[0][0].getParent());

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				// Get the bounds of the JPanel
				Rectangle panelBounds = Display.getBoard().squares[i][j].getBounds();
				// Calculate the intersection area
				Rectangle intersection = labelBounds.intersection(panelBounds);
				int intersectionArea = intersection.width * intersection.height;

				// Check if this is the largest intersection so far
				if (!intersection.isEmpty() && intersectionArea > maxIntersectionArea) {
					maxIntersectionArea = intersectionArea;
					bestMatch = new Point(i, j);
				}
			}
		}

		return bestMatch;
	}

	private static boolean legalMove(Square square, int x, int y) {

		// Check if is its the player's turn
		if (square.getPiece().side() != Display.getBoard().whiteTurn)
			return false;
		// Get piece moves in Point array
		ArrayList<Point> moves = square.getPiece().getPieceMoves(square.getPosX(), square.getPosY());
		// Add special cases

		// Loop through possible moves and check if legalMove is within it
		for (int i = 0; i < moves.size(); i++) {
//			System.out.println("Legal moves: " + moves.get(i).x + " " + moves.get(i).y);
			if (moves.get(i).x == x && moves.get(i).y == y) {
				Display.getBoard().whiteTurn = !Display.getBoard().whiteTurn;
				return true; // Legal move found!
			}
		}
		return false;
	}

	public static boolean freeSquare(int x, int y, boolean isWhite) {
		// Check is empty
		if (emptySquare(x, y))
			return true;
		// Check if enemy piece
		if (capturableSquare(x, y, isWhite))
			return true;
		// Has same side piece
		return false;
	}

	public static boolean emptySquare(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7)
			return false; // Bounds check
		if (Display.getBoard().squares[x][y].getPiece() == null)
			return true;
		return false;
	}

	public static boolean capturableSquare(int x, int y, boolean isWhite) {

		if (x < 0 || x > 7 || y < 0 || y > 7)
			return false; // Bounds check

		Piece piece = Display.getBoard().squares[x][y].getPiece();
		if (piece == null)
			return false; // null check

		if ((piece.side() == Piece.BLACK && isWhite) || (piece.side() == Piece.WHITE && !isWhite))
			return true;
		return false;
	}

	private static void checkSpecialCases(Piece piece, int x, int y) {
		if (piece.type == Piece.KING) {

			if (!Piece.isWhiteKingMoved() && (x == 6 && y == 0)) {
				forceMovePiece(Display.getBoard().squares[7][0], 5, 0);
//				squares[5][0].repaintSquare();
			} else if (!Piece.isWhiteKingMoved() && (x == 2 && y == 0))
				forceMovePiece(Display.getBoard().squares[0][0], 3, 0);
			else if (!Piece.isBlackKingMoved() && (x == 6 && y == 7))
				forceMovePiece(Display.getBoard().squares[7][7], 5, 7);
			else if (!Piece.isBlackKingMoved() && (x == 2 && y == 7))
				forceMovePiece(Display.getBoard().squares[0][7], 3, 7);
			else if (piece.side() == Piece.WHITE)
				Piece.whiteKingMoved = true;
			else
				Piece.blackKingMoved = true;
		}
	}

	public static void rollBack() {
		if (trackMoves <= 0) return;
		trackMoves--;
	    Piece[][] boardState = Display.getMoveHistory().get(trackMoves);
	    restoreBoardState(boardState);
	}
	
	public static void rollForward() {
		if (trackMoves+1 == Display.getMoveHistory().size())
			return;
		trackMoves++;
		Piece[][] boardState = Display.getMoveHistory().get(trackMoves);
		restoreBoardState(boardState);
	}
	
	public static void restoreBoardState(Piece[][] boardState) {
	    Board board = Display.getBoard();
	    for (int i = 0; i < 8; i++) {
	        for (int j = 0; j < 8; j++) {
	            board.squares[i][j].setPiece(boardState[i][j] != null ? boardState[i][j].clone() : null);
	        }
	    }
	    board.repaintBoard();
	    board.moves = trackMoves;
	    board.whiteTurn = (board.moves % 2 == 0) ? true: false;
	}

	public static void addBoardToHistory() {
		// Remove moves until caught up to current position
		System.out.println(Display.getMoveHistory().size() + " " + trackMoves);
		
		while (Display.getMoveHistory().size() != trackMoves+1 && Display.getMoveHistory().size() != 0)
			Display.getMoveHistory().pop();
			
		Piece[][] boardState = new Piece[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				boardState[i][j] = Display.getBoard().squares[i][j].getPiece() != null
						? Display.getBoard().squares[i][j].getPiece().clone() // Ensure deep copy
						: null;
			}
		}
		Display.getMoveHistory().add(boardState);
		Display.getBoard().moves = Display.getMoveHistory().size()-1; // Update the moves variable
		trackMoves = Display.getBoard().moves;
	}

	public static void changeBoard(Board board) {
		Board currentBoard = Display.getBoard();
		// Copy over contents of squares array
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				currentBoard.squares[i][j].setPiece(
						board.squares[i][j].getPiece() != null ? board.squares[i][j].getPiece().clone() : null);
//	            currentBoard.squares[i][j].repaintSquare(); // Ensure the UI reflects the new data
			}
		}
		currentBoard.whiteTurn = board.whiteTurn;
		currentBoard.moves = board.moves;
//	    currentBoard.repaintBoard();
	}

	// Create a deep copy
	@Override
	public Board clone() {
		Board rv = new Board(), original = Display.getBoard();
		// Copy over contents of array
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				rv.squares[i][j] = (Square) original.squares[i][j].clone();
//				rv.squares[i][j] = original.squares[i][j];
			}
		}
		rv.whiteTurn = original.whiteTurn;
		rv.moves = original.moves;
		return rv;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int y = 7; y >= 0; y--) { // Print from top to bottom (row 7 to 0)
			for (int x = 0; x < 8; x++) {
				Piece piece = squares[x][y].getPiece();
				if (piece == null) {
					sb.append(". ");
				} else {
					char pieceChar = ' ';
					switch (piece.type) {
					case Piece.PAWN:
						pieceChar = 'P';
						break;
					case Piece.ROOK:
						pieceChar = 'R';
						break;
					case Piece.KNIGHT:
						pieceChar = 'N'; // N for Knight to avoid conflict with King
						break;
					case Piece.BISHOP:
						pieceChar = 'B';
						break;
					case Piece.QUEEN:
						pieceChar = 'Q';
						break;
					case Piece.KING:
						pieceChar = 'K';
						break;
					}
					sb.append(piece.side() ? pieceChar : Character.toLowerCase(pieceChar)).append(" ");
				}
			}
			sb.append("\n"); // New line after each row
		}
		return sb.toString();
	}

	// May implement later, useful for checks?
//	public static boolean isTargeted(int x, int y, boolean isWhite)
}
