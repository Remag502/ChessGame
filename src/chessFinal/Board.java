package chessFinal;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
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

//	public static void movePiece(Square square, int x, int y) {
//
//		if (!potentialMove(square, x, y)) // Flips turn
//			return;
//		// Change piece
//		Square backup = Display.getBoard().squares[x][y].clone();
//		Display.getBoard().squares[x][y].setPiece(square.getPiece());
//		square.setPiece(null);
//		// Check if position has king in check
//		if (isKingInCheck(!Display.getBoard().whiteTurn)) { // Pass in NOT turn, because its already changed
//			// Undo the move by swapping pieces
//			Piece storage = Display.getBoard().squares[x][y].getPiece();
//			Display.getBoard().squares[x][y].setPiece(backup.getPiece());
//			square.setPiece(storage);
//			// Change turn back to previous
//			Display.getBoard().whiteTurn = !Display.getBoard().whiteTurn;
//		}
//		// Check special cases
//		checkSpecialCases(Display.getBoard().squares[x][y].getPiece(), x, y);
//		
//		if (isKingInCheck(Piece.WHITE) || isKingInCheck(Piece.BLACK)) {
//			System.out.println("King is in check");
//			if (checkForMate(Display.getBoard().whiteTurn))
//				System.out.println("Checkmate");
//		}
//			
//		
//		square.repaintSquare();
//		Display.getBoard().squares[x][y].repaintSquare();
//
//		addBoardToHistory();
//		
//	}
	public static void movePiece(Square square, int x, int y) {
	    // Check if the move is legal
	    if (!legalMove(square, x, y)) {
	        return; // Move is illegal, so do nothing
	    }
	    // Perform the move
	    Display.getBoard().squares[x][y].setPiece(square.getPiece());
	    square.setPiece(null);
	    Display.getBoard().whiteTurn = !Display.getBoard().whiteTurn;

	    // Check for special cases such as check, checkmate, castling, or pawn promotion
	    checkSpecialCases(Display.getBoard().squares[x][y].getPiece(), x, y);

	    // Display if a king is in check
	    if (isKingInCheck(Piece.WHITE) || isKingInCheck(Piece.BLACK)) {
	        System.out.println("King is in check");
	        if (checkForMate(Display.getBoard().whiteTurn)) {
	            System.out.println("Checkmate");
	        }
	    }

	    // Repaint squares to update the GUI
	    square.repaintSquare();
	    Display.getBoard().squares[x][y].repaintSquare();

	    // Add the board state to history for undo/redo functionality
	    addBoardToHistory();
	}
	
	public static boolean legalMove(Square square, int targetX, int targetY) {
		if (!potentialMove(square, targetX, targetY)) // Flips turn
			return false;
		
	    // Clone the current board state to backup for later restoration
	    Square backup = Display.getBoard().squares[targetX][targetY].clone();
	    Piece movedPiece = square.getPiece();

	    // Make the move
	    Display.getBoard().squares[targetX][targetY].setPiece(movedPiece);
	    square.setPiece(null);

	    // Check if this move puts the king in check
	    boolean isKingInCheck = isKingInCheck(movedPiece.side());

	    // Undo the move by restoring the previous board state
	    square.setPiece(movedPiece);
	    Display.getBoard().squares[targetX][targetY].setPiece(backup.getPiece());

	    // Return true if the move does not put the king in check, otherwise false
	    return !isKingInCheck;
	}
	
	// Finds moves that are within current turn and according to the piece
	private static boolean potentialMove(Square square, int x, int y) {

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
//				Display.getBoard().whiteTurn = !Display.getBoard().whiteTurn;
				return true; // Legal move found!
			}
		}
		return false;
	}
	
	public static void forceMovePiece(Square square, int x, int y) {

		// Change piece
		Display.getBoard().squares[x][y].setPiece(square.getPiece());
		square.setPiece(null);

		square.repaintSquare();
		Display.getBoard().squares[x][y].repaintSquare();
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
		if (piece == null)
			return; // In check, piece got moved back
		if (piece.type == Piece.KING) {
			castleKing(piece, x, y);
		} else if (piece.type == Piece.PAWN && (y == 0 || y == 7)) // Checks if pawn is on end rows
			promotePawn(piece);
	}
	
	
	private static void castleKing(Piece piece, int x, int y) {
		// Castle white right
		if (!Piece.isWhiteKingMoved() && (x == 6 && y == 0)) { // On castle location
			if (!(piece.onTargetedSquare(4, 0) || piece.onTargetedSquare(5, 0))) // Castle spots are not in check
				forceMovePiece(Display.getBoard().squares[7][0], 5, 0); // Move the rook
			else {
				forceMovePiece(Display.getBoard().squares[6][0], 4, 0); // Set the king back
				Display.getBoard().whiteTurn = !Display.getBoard().whiteTurn;
			}
		// Castle white left
		} else if (!Piece.isWhiteKingMoved() && (x == 2 && y == 0)) {
			if (!(piece.onTargetedSquare(4, 0) || piece.onTargetedSquare(3, 0) || piece.onTargetedSquare(2, 0)))
				forceMovePiece(Display.getBoard().squares[0][0], 3, 0);
			else {
				forceMovePiece(Display.getBoard().squares[2][0], 4, 0);
				Display.getBoard().whiteTurn = !Display.getBoard().whiteTurn;
			}
		}
		// Castle black right
		else if (!Piece.isBlackKingMoved() && (x == 6 && y == 7)) {
			if (!(piece.onTargetedSquare(4, 7) || piece.onTargetedSquare(5, 7)))
				forceMovePiece(Display.getBoard().squares[7][7], 5, 7);
			else {
				forceMovePiece(Display.getBoard().squares[6][7], 4, 7);
				Display.getBoard().whiteTurn = !Display.getBoard().whiteTurn;
			}
		}
		// Castle black left
		else if (!Piece.isBlackKingMoved() && (x == 2 && y == 7)) {
			if (!(piece.onTargetedSquare(4, 7) || piece.onTargetedSquare(3, 7) && piece.onTargetedSquare(2, 7)))
				forceMovePiece(Display.getBoard().squares[0][7], 3, 7);
			else {
				forceMovePiece(Display.getBoard().squares[2][7], 4, 7);
				Display.getBoard().whiteTurn = !Display.getBoard().whiteTurn;
			}
		}
		// Piece has moved
		else if (piece.side() == Piece.WHITE)
			Piece.whiteKingMoved = true;
		else
			Piece.blackKingMoved = true;
	}
	
	private static void promotePawn(Piece piece) {
		// Prompt the player to choose a piece
	    String[] options = {"Queen", "Rook", "Bishop", "Knight"};
	    int choice = JOptionPane.showOptionDialog(null, 
	        "Choose a piece to promote your pawn to:", 
	        "Pawn Promotion", 
	        JOptionPane.DEFAULT_OPTION, 
	        JOptionPane.QUESTION_MESSAGE, 
	        null, 
	        options, 
	        options[0]);
	    
	    switch (choice) {
	        case 0: // Queen
	            piece.type = Piece.QUEEN;
	            break;
	        case 1: // Rook
	        	piece.type = Piece.ROOK;
	            break;
	        case 2: // Bishop
	        	piece.type = Piece.BISHOP;
	            break;
	        case 3: // Knight
	        	piece.type = Piece.KNIGHT;
	            break;
	        default:
	            return; // No valid choice
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

	private static boolean isKingInCheck(boolean isWhite) {
		
		Point location = findKing(isWhite);
		Piece king = Display.getBoard().squares[location.x][location.y].getPiece();
		if (king.onTargetedSquare(location.x, location.y)) 
			return true;
		
		return false;
	}
	
	public static void highlightPossibleMoves(Square square) {
    	Board board = Display.getBoard();
    	for (int i = 0; i < 8; i++) {
    		for (int j = 0; j < 8; j++) {
    			if (legalMove(square, i, j)) {
    				
    				Display.getBoard().squares[i][j].setBackground(Color.red);
    			}
        	}
    	}
    	
    }
    
	public static void unhighlightPossibleMoves(Square square) {
		Board board = Display.getBoard();
    	for (int i = 0; i < 8; i++) {
    		for (int j = 0; j < 8; j++) {
    			if (legalMove(square, i, j)) {
//    				Display.getBoard().whiteTurn = !Display.getBoard().whiteTurn;
    				Display.getBoard().squares[i][j].defaultBackground();
    			}
        	}
    	}
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

	
	public static Point findKing(boolean isWhite) {
	    for (int x = 0; x < 8; x++) {
	        for (int y = 0; y < 8; y++) {
	            Piece piece = Display.getBoard().squares[x][y].getPiece();
	            if (piece != null && piece.type == Piece.KING && piece.side() == isWhite) {
	                return new Point(x, y);
	            }
	        }
	    }
	    return null; // King not found (shouldn't happen if the board is properly set up)
	}
	
	private static boolean checkForMate(boolean isWhite) {
		
		Board board = Display.getBoard();
		ArrayList<Point> moves = new ArrayList<Point>();
		Piece piece;
		Point originalPosition, movePosition;
		int k = 0;
		// Get all possible moves for on side
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				piece = Display.getBoard().squares[i][j].getPiece();
				// Avoids empty squares, and other side pieces
//				System.out.println("reached " + i + " " + j + " " + k++);
				if (piece == null || piece.side() != isWhite) 
					continue;
				// Get possible moves for square
				originalPosition = new Point(i, j);
				moves.addAll(piece.getPieceMoves(i, j));
				// Check if moves result in check after placement
				for (Point move: moves) {
					movePosition = new Point(move.x, move.y);
					// Simulate move
					Piece storage = board.squares[move.x][move.y].getPiece();
					board.squares[move.x][move.y].setPiece(piece);
					board.squares[i][j].setPiece(null);
					// Check if the king is still in check
	                boolean inCheck = isKingInCheck(isWhite);
	                if (!inCheck)
	                	System.out.println(Display.getBoard());
	                // Undo the move
	                board.squares[i][j].setPiece(piece);
	                board.squares[move.x][move.y].setPiece(storage);
	                // If at least one move gets the king out of check, it's not checkmate
	                if (!inCheck) {
	                    return false;
	                }
				}
//				System.out.println(i + " " + j + " " + moves.size() + " " + piece.side());
				// Piece failed to find move that saves king, remove moves from list
				moves.clear();
			}
		}
		// No move gets king out of check, its checkmate!
		return true;
	}
	
	public Square[][] getSquares() {
		return squares;
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
