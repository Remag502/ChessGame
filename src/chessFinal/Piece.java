package chessFinal;

import java.awt.Point;
import java.util.ArrayList;

public class Piece {

	public static final int PAWN = 0;
	public static final int KNIGHT = 1;
	public static final int BISHOP = 2;
	public static final int ROOK = 3;
	public static final int QUEEN = 4;
	public static final int KING = 5;

	public static final boolean WHITE = true;
	public static final boolean BLACK = false;

	public static boolean whiteKingMoved = false, blackKingMoved = false; // move to board?
	
	private boolean isWhite;
	public int type;

	public Piece(int type, boolean side) {
		this.type = type;
		this.isWhite = side;
	}

	public boolean side() {
		return isWhite;
	}
	
	// Returns possible moves from piece given a location,
	// in a point ArrayList
	public ArrayList<Point> getPieceMoves(int x, int y) {

		ArrayList<Point> moves = null;
		switch (type) {
		case PAWN:
			moves = pawnMoves(x, y);
			moves.addAll(pawnCapture(x, y));
//			moves.addAll(enPessant(x, y));
//			moves.addAll(pawnPromotion(x, y));
			break;
		case KNIGHT:
			moves = knightMoves(x, y);
			break;
		case BISHOP:
			moves = bishopMoves(x, y);
			break;
		case ROOK:
			moves = rookMoves(x, y);
			break;
		case QUEEN:
			moves = queenMoves(x, y);
			break;
		case KING:
			moves = kingMoves(x, y);
			moves.addAll(castleKing(x, y));
			break;
		default:
			return null;
		}

		moves.addAll(pawnCapture(x, y));

		return moves;
	}

	private ArrayList<Point> pawnMoves(int x, int y) {

		ArrayList<Point> moves = new ArrayList<Point>();

		if (isWhite) {
			if (!Board.capturableSquare(x, y + 1, isWhite))
				addMove(moves, x, y + 1);
			if (y == 1 && Board.freeSquare(x, y + 1, isWhite)) {
				if (!Board.capturableSquare(x, y + 2, isWhite))
					addMove(moves, x, y + 2);
			}
		} else {
			if (!Board.capturableSquare(x, y - 1, isWhite))
				addMove(moves, x, y - 1);
			if (y == 6 && Board.freeSquare(x, y - 1, isWhite)) {
				if (!Board.capturableSquare(x, y - 2, isWhite))
					addMove(moves, x, y - 2);
			}
		}

		return moves;
	}

	private ArrayList<Point> knightMoves(int x, int y) {

		ArrayList<Point> moves = new ArrayList<Point>();

		addMove(moves, x - 1, y + 2);
		addMove(moves, x + 1, y + 2);
		addMove(moves, x - 2, y + 1);
		addMove(moves, x + 2, y + 1);
		addMove(moves, x - 2, y - 1);
		addMove(moves, x + 2, y - 1);
		addMove(moves, x - 1, y - 2);
		addMove(moves, x + 1, y - 2);

		return moves;
	}

	private ArrayList<Point> bishopMoves(int x, int y) {

		ArrayList<Point> moves = new ArrayList<Point>();
		int i = x, j = y;

		// Positive slope decreasing
		i = x;
		j = y;
		while (i > 0 && j > 0) { // Within bounds
			i--;
			j--;

			if (Board.capturableSquare(i, j, isWhite)) {
				moves.add(new Point(i, j));
				break;
			}
			if (!Board.freeSquare(i, j, isWhite))
				break;

			moves.add(new Point(i, j));
		} // Repeat
			// Positive slope increasing
		i = x;
		j = y;
		while (i < 7 && j < 7) {
			i++;
			j++;
			// Adds square than can be captured, does not add beyond
			if (Board.capturableSquare(i, j, isWhite)) {
				moves.add(new Point(i, j));
				break;
			} // Does not add square with same side piece and beyond
			if (!Board.freeSquare(i, j, isWhite))
				break;
			// Add possible move
			moves.add(new Point(i, j));
		}
		// Negative slope left
		i = x;
		j = y;
		while (i > 0 && j < 7) {

			i--;
			j++;

			if (Board.capturableSquare(i, j, isWhite)) {
				moves.add(new Point(i, j));
				break;
			}
			if (!Board.freeSquare(i, j, isWhite))
				break;

			moves.add(new Point(i, j));
		}
		// Negative slope right
		i = x;
		j = y;
		while (i < 7 && j > 0) {
			i++;
			j--;

			if (Board.capturableSquare(i, j, isWhite)) {
				moves.add(new Point(i, j));
				break;
			}
			if (!Board.freeSquare(i, j, isWhite))
				break;

			moves.add(new Point(i, j));
		}

		return moves;
	}

	private ArrayList<Point> rookMoves(int x, int y) {

		ArrayList<Point> moves = new ArrayList<Point>();
		// Rook moves vertical down
		for (int i = y - 1; i >= 0; i--) {
			// Remove illegal squares
			if (Board.capturableSquare(x, i, isWhite)) {
				moves.add(new Point(x, i));
				break;
			}
			if (!Board.freeSquare(x, i, isWhite))
				break;
			addMove(moves, x, i);
		}
		// Rook moves vertical up
		for (int i = y + 1; i < 8; i++) {
			// Remove illegal squares
			if (Board.capturableSquare(x, i, isWhite)) {
				moves.add(new Point(x, i));
				break;
			}
			if (!Board.freeSquare(x, i, isWhite))
				break;
			addMove(moves, x, i);
		}

		// Rook moves horizontal left
		for (int i = x - 1; i >= 0; i--) {
			// Remove illegal squares
			if (Board.capturableSquare(i, y, isWhite)) {
				moves.add(new Point(i, y));
				break;
			}
			if (!Board.freeSquare(i, y, isWhite))
				break;
			addMove(moves, i, y);
		}
		// Rook moves moves horizontal right
		for (int i = x + 1; i < 8; i++) {
			// Remove illegal squares
			if (Board.capturableSquare(i, y, isWhite)) {
				moves.add(new Point(i, y));
				break;
			}
			if (!Board.freeSquare(i, y, isWhite))
				break;
			addMove(moves, i, y);
		}

		return moves;
	}

	private ArrayList<Point> queenMoves(int x, int y) {

		ArrayList<Point> moves = new ArrayList<Point>();

		moves.addAll(rookMoves(x, y));
		moves.addAll(bishopMoves(x, y));

		return moves;
	}

	private ArrayList<Point> kingMoves(int x, int y) {

		ArrayList<Point> moves = new ArrayList<Point>();

		addMove(moves, x + 1, y);
		addMove(moves, x + 1, y + 1);
		addMove(moves, x, y + 1);
		addMove(moves, x - 1, y + 1);
		addMove(moves, x - 1, y);
		addMove(moves, x - 1, y - 1);
		addMove(moves, x, y - 1);
		addMove(moves, x + 1, y - 1);

		return moves;

	}

	// Checks if square if empty then adds to array list
	private void addMove(ArrayList<Point> moves, int x, int y) {
		if (x >= 8 || y >= 8 || x < 0 || y < 0)
			return;
		if (Board.freeSquare(x, y, isWhite))
			moves.add(new Point(x, y));
	}

	// Special cases: pawn capture, en pessant, pawn promotion, castle
	private ArrayList<Point> pawnCapture(int x, int y) {

		ArrayList<Point> moves = new ArrayList<Point>();

		if (isWhite) {
			if (Board.capturableSquare(x + 1, y + 1, isWhite))
				moves.add(new Point(x + 1, y + 1)); // captures right diagnol
			if (Board.capturableSquare(x - 1, y + 1, isWhite))
				moves.add(new Point(x - 1, y + 1)); // captures left diagnol
		} else {
			if (Board.capturableSquare(x + 1, y - 1, isWhite))
				moves.add(new Point(x - 1, y - 1)); // captures left diagnol
			if (Board.capturableSquare(x - 1, y - 1, isWhite))
				moves.add(new Point(x - 1, y - 1)); // captures right diagnol
		}
		return moves;
	}

	private ArrayList<Point> enPessant(int x, int y) {
		return null;
	}

	private ArrayList<Point> pawnPromotion(int x, int y) {
		return null;
	}

	private ArrayList<Point> castleKing(int x, int y) {

		ArrayList<Point> moves = new ArrayList<Point>();

		if (isWhite && !whiteKingMoved) {
			// Castle right
			if (Board.emptySquare(5, 0) && Board.emptySquare(6, 0)) {
				moves.add(new Point(6, 0));
			} else if (Board.emptySquare(1, 0) && Board.emptySquare(2, 0) && Board.emptySquare(3, 0)) { // Castle left
				moves.add(new Point(2, 0));
			}
		} else if (!blackKingMoved) {
			// Castle right
			if (Board.emptySquare(5, 7) && Board.emptySquare(6, 7)) {
				moves.add(new Point(6, 7));
			} else if (Board.emptySquare(1, 7) && Board.emptySquare(2, 7) && Board.emptySquare(3, 7)) { // Castle left
				moves.add(new Point(2, 7));
			}
		}

		return moves;
	}
	
	public static boolean isWhiteKingMoved() {
		return whiteKingMoved;
	}

	public static boolean isBlackKingMoved() {
		return blackKingMoved;
	}
	
	@Override
	public Piece clone() {
		return new Piece(this.type, this.side());
	}

}