package chessFinal;

public class Piece {
	
	public static final int PAWN = 0;
	public static final int KNIGHT = 1;
	public static final int BISHOP = 2;
	public static final int ROOK = 3;
	public static final int QUEEN = 4;
	public static final int KING = 5;
	
	
	public int type;
	boolean side;
	
	public Piece(int type, boolean side) {
		this.type = type;
		this.side = side;
	}
	
	public boolean isWhite() {
		return side;
	}
	
}