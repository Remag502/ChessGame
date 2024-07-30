package chessReworked;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

	private boolean whiteTurn = true, whiteInCheck, whiteKingMoved = false, blackKingMoved = false; // May have a use
																									// for later
	private char[] pieces = new char[64];
	private List<char[]> positions = new ArrayList<char[]>(); // Should switch to linked list
	private int move;
	// Conventions for each piece
	// R = White Rook, N = White Knight,
	// r = Black Rook,

	public Board() {
		for (int i = 0; i < pieces.length; i++)
			pieces[i] = ' ';
		pieces[0] = 'R';
		pieces[1] = 'N';
		pieces[2] = 'B';
		pieces[3] = 'Q';
		pieces[4] = 'K';
		pieces[5] = 'B';
		pieces[6] = 'N';
		pieces[7] = 'R';
		pieces[56] = 'r';
		pieces[57] = 'n';
		pieces[58] = 'b';
		pieces[59] = 'q';
		pieces[60] = 'k';
		pieces[61] = 'b';
		pieces[62] = 'n';
		pieces[63] = 'r';
		for (int i = 0; i < 8; i++) {
			pieces[8 + i] = 'P';
			pieces[48 + i] = 'p';
		}
		positions.add((char[]) pieces.clone());
	}

	public char[] getPieces() {
		return pieces;
	}

	public boolean getWhiteTurn() {
		return whiteTurn;
	}

	public void goBack() {
		if (move == 0)
			return;
		move--;
		whiteTurn = !whiteTurn;
		setBoardPosition(positions.get(move));
		printBoard();
	}

	public void goForward() {
		if (move == positions.size() - 1)
			return;
		System.out.println("Reac");
		move++;
		whiteTurn = !whiteTurn;
		setBoardPosition(positions.get(move));
		printBoard();
	}

	private void setBoardPosition(char[] pos) {
		for (int i = 0; i < pieces.length; i++)
			pieces[i] = pos[i];
	}

	public synchronized void printBoard() {
//		System.out.println(
//				"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
//		for (int i = 56; i >= 0; i -= 8) {
//			for (int j = 0; j < 8; j++)
//				System.out.print(pieces[j + i] + "\t");
//			System.out.print("\n\n");
//		}
		MainDisplay.updateDisplay();
	}

	public void movePiece(int square1, int square2) {
		// Can throw InvalidMove, IllegalMoveException, StaleMateException,
		// FiftyMoveRuleException, ThreeMoveRepitionException, CheckMateException
		boolean validMove = false;
		List<Integer> moves = getMoves(square1);
		if (moves == null) {
			System.out.println("Null check");
			return;
		}
		if ((pieces[square1] == 'K' || pieces[square1] == 'k') && (!whiteKingMoved || !blackKingMoved)) {
			for (int move : moves) {
				if (move == 2 || move == 6 || move == 58 || move == 62) {
					if (square2 == move) {
						castlePieces(square2);
						whiteTurn = !whiteTurn;
						return;
					}
				}
			}
		}

		for (int move : moves) {
			if (square2 == move) {
				validMove = true;
				break;
			}
		}
		if (validMove == true) {
			if (pieces[square1] == 'K')
				whiteKingMoved = true;
			if (pieces[square1] == 'k')
				blackKingMoved = true;
			pieces[square2] = pieces[square1];
			pieces[square1] = ' ';
			whiteTurn = !whiteTurn;
			positions.add((char[]) pieces.clone());
//			if(move != positions.size()-1) {
//				System.out.println("Cleaning");
//				for(int i = move+1; i < positions.size();)
//					positions.remove(i);
//			}
			move++;
		} else {
			System.out.println("Invalid Move");
		}
	}

	private void castlePieces(int type) {
		switch (type) {
		case 6:
			pieces[4] = ' ';
			pieces[5] = 'R';
			pieces[6] = 'K';
			pieces[7] = ' ';
			whiteKingMoved = true;
			break;
		case 2:
			pieces[0] = ' ';
			pieces[1] = ' ';
			pieces[2] = 'K';
			pieces[3] = 'R';
			pieces[4] = ' ';
			whiteKingMoved = true;
			break;
		case 62:
			pieces[60] = ' ';
			pieces[61] = 'r';
			pieces[62] = 'k';
			pieces[63] = ' ';
			blackKingMoved = true;
			break;
		case 58:
			pieces[56] = ' ';
			pieces[57] = ' ';
			pieces[58] = 'k';
			pieces[59] = 'r';
			pieces[60] = ' ';
			blackKingMoved = true;
			break;
		}
		positions.add((char[]) pieces.clone());
	}

	private List<Integer> getMoves(int square) {
		// Gets all possible positions a piece can go depending on its square
		// Will not account for blocking pieces, checks, etc..
		// Returns moves assuming empty board
		List<Integer> moves = null; // Makes this a class instance variable
		if (whiteTurn) {
			switch (pieces[square]) {
			case 'P':
				moves = pawnMoves(square);
				break;
			case 'N':
				moves = knightMoves(square);
				break;
			case 'B':
				moves = bishopMoves(square);
				break;
			case 'R':
				moves = rookMoves(square);
				break;
			case 'Q':
				moves = queenMoves(square);
				break;
			case 'K':
				moves = kingMoves(square);
				break;
			default: // Throws InvalidMove Exception
				System.out.println("Invalid move");
				return null;
			}
		} else {
			switch (pieces[square]) {
			case 'p':
				moves = pawnMoves(square);
				break;
			case 'n':
				moves = knightMoves(square);
				break;
			case 'b':
				moves = bishopMoves(square);
				break;
			case 'r':
				moves = rookMoves(square);
				break;
			case 'q':
				moves = queenMoves(square);
				break;
			case 'k':
				moves = kingMoves(square);
				break;
			default: // Throws InvalidMove Exception
				System.out.println("Invalid move");
				return null;
			}

		}

		// Loop removes moves that are outside of board
		for (int i = 0; i < moves.size(); i++) {
			// 
			if (moves.get(i) == Integer.MAX_VALUE)
				continue;
//			{
//				moves.remove(i);
//				i -= 1;
//			}
			else if (moves.get(i) < 0 || moves.get(i) > 63) {
				moves.remove(i);
				i -= 1;
			}
		}
		// This loop is gonna be complicated
		boolean removeElements = false;
		char piece;
		for (int i = 0; i < moves.size(); i++) {
			if (moves.get(i) == Integer.MAX_VALUE) {
				moves.remove(i);
				i -= 1;
				removeElements = false;
				continue;
			}
			if (removeElements) {
				moves.remove(i);
				i -= 1;
				continue;
			}
			piece = pieces[moves.get(i)];
			if (piece == ' ')
				continue;
			if (Character.toUpperCase(piece) == piece && whiteTurn
					|| Character.toLowerCase(piece) == piece && !whiteTurn) {
				// Checks if the piece on square is the same color from the turn
				removeElements = true;
				moves.remove(i);
				i -= 1;
			} else if (Character.toUpperCase(piece) == piece && !whiteTurn
					|| Character.toLowerCase(piece) == piece && whiteTurn) {
				// Checks if the piece on square is a different color from the turn
				removeElements = true;
				if (('P' == piece || 'p' == piece) && Math.abs(moves.get(i) - square) == 8) {
					moves.remove(i);
					i -= 1;
				}
			}
		}
		System.out.println(Arrays.toString(moves.stream().mapToInt(i -> i).toArray()));
		return moves; // Calls appropriate method and removes invalid values such as pieces that are
						// blocked by others and outside of the board
	}

	private List<Integer> knightMoves(int square) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(square + 17);
		list.add(2147483647);
		list.add(square + 15);
		list.add(2147483647);
		list.add(square + 10);
		list.add(2147483647);
		list.add(square + 6); // Conditions where this would not be proper
		list.add(2147483647);
		list.add(square - 17);
		list.add(2147483647);
		list.add(square - 15);
		list.add(2147483647);
		list.add(square - 10);
		list.add(2147483647);
		list.add(square - 6);
		list.add(2147483647);
		return list;
	}

	public static List<Integer> bishopMoves(int square) {
		List<Integer> list = new ArrayList<Integer>();
		int possiblePosition = square;
		while (possiblePosition % 8 != 7) {
			list.add(possiblePosition + 9);
			possiblePosition += 9;
		}
		list.add(2147483647);
		possiblePosition = square;
		while (possiblePosition % 8 != 0) {
			list.add(possiblePosition - 9);
			possiblePosition -= 9;
		}
		list.add(2147483647);
		possiblePosition = square;
		while (possiblePosition % 8 != 0) {
			list.add(possiblePosition + 7);
			possiblePosition += 7;
		}
		list.add(2147483647);
		possiblePosition = square;
		while (possiblePosition % 8 != 7 && possiblePosition > -1) {
			list.add(possiblePosition - 7);
			possiblePosition -= 7;
		}
		list.add(2147483647);
		return list;
	}

	private List<Integer> rookMoves(int square) {
		List<Integer> list = new ArrayList<Integer>();
		int possiblePosition = square;
		while (possiblePosition / 8 < 8) {
			list.add(possiblePosition + 8);
			possiblePosition += 8;
		}
		list.add(2147483647);
		possiblePosition = square;
		while (possiblePosition > -1) {
			list.add(possiblePosition - 8);
			possiblePosition -= 8;
		}
		list.add(2147483647);
		possiblePosition = square;
		while (possiblePosition % 8 != 7) {
			list.add(possiblePosition + 1);
			possiblePosition += 1;
		}
		list.add(2147483647);
		possiblePosition = square;
		while (possiblePosition % 8 != 0) {
			list.add(possiblePosition - 1);
			possiblePosition -= 1;
		}
		list.add(2147483647);
		return list;
	}

	private List<Integer> queenMoves(int square) {
		List<Integer> list = bishopMoves(square);
		list.addAll(rookMoves(square));
		return list;
	}

	private List<Integer> kingMoves(int square) {
		List<Integer> list = new ArrayList<Integer>();
		addCastleMoves(list);
		list.add(square + 1);
		list.add(2147483647);
		list.add(square - 1);
		list.add(2147483647);
		list.add(square + 8);
		list.add(2147483647);
		list.add(square - 8);
		list.add(2147483647);
		list.add(square + 9);
		list.add(2147483647);
		list.add(square - 9);
		list.add(2147483647);
		list.add(square + 7);
		list.add(2147483647);
		list.add(square - 7);
		list.add(2147483647);
		return list;
	}

	private void addCastleMoves(List<Integer> list) {
		System.out.println("Reached");
		if (pieces[4] == 'K' && !whiteKingMoved) {
			System.out.println("Reached");
			if (pieces[7] == 'R' && pieces[5] == ' ' && pieces[6] == ' ') {
				System.out.println("Adding move");
				list.add(6);
				list.add(2147483647);
			}
			if (pieces[0] == 'R' && pieces[1] == ' ' && pieces[2] == ' ' && pieces[3] == ' ') {
				list.add(2);
				list.add(2147483647);
			}
		} else if (pieces[60] == 'k' && !blackKingMoved) {
			if (pieces[63] == 'r' && pieces[61] == ' ' && pieces[62] == ' ') {
				list.add(62);
				list.add(2147483647);
			}
			if (pieces[56] == 'r' && pieces[57] == ' ' && pieces[58] == ' ' && pieces[59] == ' ') {
				list.add(58);
				list.add(2147483647);
			}
		}
	}

	private List<Integer> pawnMoves(int square) {
		List<Integer> list = new ArrayList<Integer>();
		if (pieces[square] == 'P') {
			list.add(square + 8);
			if (square > 7 && square < 16)
				list.add(square + 16);
			list.add(2147483647);
			if (pieces[square + 9] != ' ' && square % 8 != 7)
				list.add(square + 9);
			list.add(2147483647);
			if (pieces[square + 7] != ' ' && square % 8 != 0)
				list.add(square + 7);
		} else {
			list.add(square - 8);
			if (square > 47 && square < 56)
				list.add(square - 16);
			list.add(2147483647);
			if (pieces[square - 7] != ' ' && square % 8 != 7)
				list.add(square - 7);
			list.add(2147483647);
			if (pieces[square - 9] != ' ' && square % 8 != 0)
				list.add(square - 9);
		} // Add diagnol capture
		return list;
	}

	private boolean checkIfInCheck(char[] position, boolean whiteTurn) {
		// Will be called after the move has been made
		// movePiece() might have a try catch
		// One idea is to clone board: char[] and make the move on there, then check if
		// its in check, if not then make the move on the actual board, else it will
		// throw the exception
		// Throws InvalidMoveException if this returns true
		return false;
	}

	private List<Integer> checkPaths(int pos) {
		return null; // Might remove
	}

}