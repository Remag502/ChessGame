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

	public int type;
	private boolean isWhite;

	public Piece(int type, boolean side) {
		this.type = type;
		this.isWhite = side;
	}

	public boolean side() {
		return isWhite;
	}

	// Returns possible moves from piece given a location, assuming an empty board,
	// in a point ArrayList
	public ArrayList<Point> emptyBoardMoves(int x, int y) {

		ArrayList<Point> rv = null;
		switch (type) {
		case PAWN:
			rv = pawnMoves(x, y);
			break;
		case KNIGHT:
			rv = knightMoves(x, y);
			break;
		case BISHOP:
			rv = bishopMoves(x, y);
			break;
		case ROOK:
			rv = rookMoves(x, y);
			break;
		case QUEEN:
			rv = queenMoves(x, y);
			break;
		case KING:
			rv = kingMoves(x, y);
			break;
		default:
			return null;
		}

		return rv;
	}

	public ArrayList<Point> pawnMoves(int x, int y) {

		ArrayList<Point> moves = new ArrayList<Point>();

		if (isWhite) {
			addMove(moves, x, y + 1);
			if (y == 1)
				addMove(moves, x, y + 2);
		} else {
			addMove(moves, x, y - 1);
			if (y == 6)
				addMove(moves, x, y - 2);
		}

		return moves;
	}

	public ArrayList<Point> knightMoves(int x, int y) {

		ArrayList<Point> moves = new ArrayList<Point>();

		addMove(moves, x - 1, y + 2);
		addMove(moves, x + 1, y + 2);
		addMove(moves, x - 2, y + 1);
		addMove(moves, x + 2, y + 1);
		addMove(moves, x - 2, y - 1);
		addMove(moves, x + 2, y - 1);
		addMove(moves, x - 1, y - 2);
		addMove(moves, x + 1, y - 2);

//		moves.add(new Point(x-1, y+2));
//		moves.add(new Point(x+1, y+2));
//		moves.add(new Point(x-2, y+1));
//		moves.add(new Point(x+2, y+1));
//		moves.add(new Point(x-2, y-1));
//		moves.add(new Point(x+2, y-1));
//		moves.add(new Point(x-1, y-2));
//		moves.add(new Point(x+1, y-2));

		return moves;
	}

	public ArrayList<Point> bishopMoves(int x, int y) {

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

	public ArrayList<Point> rookMoves(int x, int y) {

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

	public ArrayList<Point> queenMoves(int x, int y) {

		ArrayList<Point> moves = new ArrayList<Point>();

		moves.addAll(rookMoves(x, y));
		moves.addAll(bishopMoves(x, y));

		return moves;
	}

	public ArrayList<Point> kingMoves(int x, int y) {

		ArrayList<Point> moves = new ArrayList<Point>();

		addMove(moves, x + 1, y);
		addMove(moves, x + 1, y + 1);
		addMove(moves, x, y + 1);
		addMove(moves, x - 1, y + 1);
		addMove(moves, x - 1, y);
		addMove(moves, x - 1, y - 1);
		addMove(moves, x, y - 1);
		addMove(moves, x + 1, y - 1);

//		moves.add(new Point(x+1, y));
//		moves.add(new Point(x+1, y+1));
//		moves.add(new Point(x, y+1));
//		moves.add(new Point(x-1, y+1));
//		moves.add(new Point(x-1, y));
//		moves.add(new Point(x-1, y-1));
//		moves.add(new Point(x, y-1));
//		moves.add(new Point(x+1, y-1));

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

}