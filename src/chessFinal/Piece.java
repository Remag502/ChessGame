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
			moves.add(new Point(x, y+1));
			if (y == 1)
				moves.add(new Point(x, y+2));
		} else {
			moves.add(new Point(x, y-1));
			if (y == 6)
				moves.add(new Point(x, y-2));
		}
		
		return moves;
	}

	public ArrayList<Point> knightMoves(int x, int y) {
		
		ArrayList<Point> moves = new ArrayList<Point>();
		
		moves.add(new Point(x-1, y+2));
		moves.add(new Point(x+1, y+2));
		moves.add(new Point(x-2, y+1));
		moves.add(new Point(x+2, y+1));
		moves.add(new Point(x-2, y-1));
		moves.add(new Point(x+2, y-1));
		moves.add(new Point(x-1, y-2));
		moves.add(new Point(x+1, y-2));
		
		return moves;
	}

	public ArrayList<Point> bishopMoves(int x, int y) {
		
		ArrayList<Point> moves = new ArrayList<Point>();
		int i=x, j=y;
		
		// Positive slope decreasing
		i = x;
		j = y;
		while (i > 0 || j > 0) {
			i--;
			j--;
			moves.add(new Point(i, j));
		}
		// Positive slope increasing
		i = x;
		j = y;
		while (i < 7 || j < 7) {
			i++;
			j++;
			moves.add(new Point(i, j));
		}
		// Negative slope left
		i = x;
		j = y;
		while (i > 0 || j < 7) {
			i--;
			j++;
			moves.add(new Point(i, j));
		}
		// Negative slope
		i = x;
		j = y;
		while (i < 7 || j > 0) {
			i++;
			j--;
			moves.add(new Point(i, j));
		}
		
		return moves;
	}

	public ArrayList<Point> rookMoves(int x, int y) {
		
		ArrayList<Point> moves = new ArrayList<Point>();
		
		for (int i = 0; i < 8; i++) {
			if (i==y)
				continue;
			moves.add(new Point(x, i));
		}
		for (int i = 0; i < 8; i++) {
			if (i==x)
				continue;
			moves.add(new Point(i, y));
		}
		
		return moves;
	}

	public ArrayList<Point> queenMoves(int x, int y) {
		
		ArrayList<Point> moves = new ArrayList<Point>();
		
		moves.addAll(rookMoves(x,y));
		moves.addAll(bishopMoves(x,y));
		
		return moves;
	}

	public ArrayList<Point> kingMoves(int x, int y) {
		
		ArrayList<Point> moves = new ArrayList<Point>();
		
		moves.add(new Point(x+1, y));
		moves.add(new Point(x+1, y+1));
		moves.add(new Point(x, y+1));
		moves.add(new Point(x-1, y+1));
		moves.add(new Point(x-1, y));
		moves.add(new Point(x-1, y-1));
		moves.add(new Point(x, y-1));
		moves.add(new Point(x+1, y-1));
		
		return moves;
		
	}
	
	// Special cases: pawn capture, en pessant, pawn promotion, castle

}