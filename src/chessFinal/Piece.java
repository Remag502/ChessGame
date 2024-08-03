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

	public int type;
	boolean isWhite;

	public Piece(int type, boolean side) {
		this.type = type;
		this.isWhite = side;
	}

	public boolean isWhite() {
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
		default:
			return null;
		}
		
		return rv;
	}

	public ArrayList<Point> pawnMoves(int x, int y) {
		ArrayList<Point> moves = new ArrayList<Point>();
		if (isWhite) {
			moves.add(new Point(x+1, y));
			if (x == 1)
				moves.add(new Point(x+2, y));
		} else {
			moves.add(new Point(x-1, y));
			if (x == 6)
				moves.add(new Point(x-2, y));
		}
		
		return moves;
	}

	public ArrayList<Point> knightMoves(int x, int y) {
		ArrayList<Point> moves = new ArrayList<Point>();
		return null;
	}

	public ArrayList<Point> bishopMoves(int x, int y) {
		ArrayList<Point> moves = new ArrayList<Point>();
		return null;
	}

	public ArrayList<Point> rookMoves(int x, int y) {
		ArrayList<Point> moves = new ArrayList<Point>();
		return null;
	}

	public ArrayList<Point> queenMoves(int x, int y) {
		ArrayList<Point> moves = new ArrayList<Point>();
		return null;
	}
	
	// Special cases: pawn capture, en pessant, pawn promotion, castle

}