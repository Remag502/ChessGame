package chessReworked;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainDisplay extends JFrame {

	private JPanel contentPane;
	private static JPanel[] squares = new JPanel[64];
	private static JLabel[] squareLabels = new JLabel[64];
	private static Board board = new Board();
	private static char[] pieces = board.getPieces();
	private static ImageIcon white_pawn, white_bishop, white_rook, white_knight, white_queen, white_king, black_pawn,
			black_bishop, black_rook, black_knight, black_queen, black_king;
	public static int selectedPiece = -1;
	/**
	 * Create the frame.
	 */
	public MainDisplay() {
		
		for (int i = 0; i < 64; i++) {
			squares[i] = new ClickableSquare(i);
			squareLabels[i] = new JLabel();
		}
		
//		setPiecePictures();
		board.printBoard();
		
		setResizable(false);
		setTitle("Chess");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(8, 8, 0, 0));

		boolean color = true;

		for (int i = 0; i < 64; i++) {
			color = (i % 8 == 0) ? color : !color;
			squares[i].add(squareLabels[i]);
			if (color)
				squares[i].setBackground(Color.DARK_GRAY);
			contentPane.add(squares[i], i / 8, i % 8);
		}
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_LEFT)
					board.goBack();
				if(e.getKeyCode()==KeyEvent.VK_RIGHT)
					board.goForward();
				
			}
		});
		System.out.println("Finished setting up JFrame");
	}

	public static void updateDisplay() {
		// Makes sure that the thread running this is from the EventQueue thread and not
		// the main thread to avoid errors
		if (!EventQueue.isDispatchThread()) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					updateDisplay();
				}
			});
		}

		for (int i = 0; i < 64; i++) {
			
			switch (pieces[i]) {
			case ' ':
				squareLabels[i].setIcon(null);
				break;
			case 'P':
				squareLabels[i].setIcon(white_pawn);
				break;
			case 'p':
				squareLabels[i].setIcon(black_pawn);
				break;
			case 'R':
				squareLabels[i].setIcon(white_rook);
				break;
			case 'r':
				squareLabels[i].setIcon(black_rook);
				break;
			case 'N':
				squareLabels[i].setIcon(white_knight);
				break;
			case 'n':
				squareLabels[i].setIcon(black_knight);
				break;
			case 'B':
				squareLabels[i].setIcon(white_bishop);
				break;
			case 'b':
				squareLabels[i].setIcon(black_bishop);
				break;
			case 'Q':
				squareLabels[i].setIcon(white_queen);
				break;
			case 'q':
				squareLabels[i].setIcon(black_queen);
				break;
			case 'K':
				squareLabels[i].setIcon(white_king);
				break;
			case 'k':
				squareLabels[i].setIcon(black_king);
				break;
			}
			
			squares[i].repaint();

		}
	}

	public static void setPiecePictures() {
		try { // Maybe we can use an array for images and a for loop to go through each file
			BufferedImage img = ImageIO.read(new File("images/white_pawn.png"));
			Image dimg = img.getScaledInstance(squares[0].getWidth() - 10, squares[0].getHeight() - 10,
					Image.SCALE_SMOOTH);
			white_pawn = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/black_pawn.png"));
			dimg = img.getScaledInstance(squares[0].getWidth() - 10, squares[0].getHeight() - 10, Image.SCALE_SMOOTH);
			black_pawn = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/white_bishop.png"));
			dimg = img.getScaledInstance(squares[0].getWidth() - 10, squares[0].getHeight() - 10, Image.SCALE_SMOOTH);
			white_bishop = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/black_bishop.png"));
			dimg = img.getScaledInstance(squares[0].getWidth() - 10, squares[0].getHeight() - 10, Image.SCALE_SMOOTH);
			black_bishop = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/white_knight.png"));
			dimg = img.getScaledInstance(squares[0].getWidth() - 10, squares[0].getHeight() - 10, Image.SCALE_SMOOTH);
			white_knight = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/black_knight.png"));
			dimg = img.getScaledInstance(squares[0].getWidth() - 10, squares[0].getHeight() - 10, Image.SCALE_SMOOTH);
			black_knight = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/white_rook.png"));
			dimg = img.getScaledInstance(squares[0].getWidth() - 10, squares[0].getHeight() - 10, Image.SCALE_SMOOTH);
			white_rook = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/black_rook.png"));
			dimg = img.getScaledInstance(squares[0].getWidth() - 10, squares[0].getHeight() - 10, Image.SCALE_SMOOTH);
			black_rook = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/white_queen.png"));
			dimg = img.getScaledInstance(squares[0].getWidth() - 10, squares[0].getHeight() - 10, Image.SCALE_SMOOTH);
			white_queen = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/black_queen.png"));
			dimg = img.getScaledInstance(squares[0].getWidth() - 10, squares[0].getHeight() - 10, Image.SCALE_SMOOTH);
			black_queen = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/white_king.png"));
			dimg = img.getScaledInstance(squares[0].getWidth() - 10, squares[0].getHeight() - 10, Image.SCALE_SMOOTH);
			white_king = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/black_king.png"));
			dimg = img.getScaledInstance(squares[0].getWidth() - 10, squares[0].getHeight() - 10, Image.SCALE_SMOOTH);
			black_king = new ImageIcon(dimg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int[] inputToMove(String move) {
		int[] rv = new int[2];
		String[] s = move.replace(" ", "").split("");
		switch (s[0]) {
		case "a":
			rv[0] = 0;
			break;
		case "b":
			rv[0] = 1;
			break;
		case "c":
			rv[0] = 2;
			break;
		case "d":
			rv[0] = 3;
			break;
		case "e":
			rv[0] = 4;
			break;
		case "f":
			rv[0] = 5;
			break;
		case "g":
			rv[0] = 6;
			break;
		case "h":
			rv[0] = 7;
			break;
		}
		rv[0] += 8 * (Integer.parseInt(s[1]) - 1);
		switch (s[2]) {
		case "a":
			rv[1] = 0;
			break;
		case "b":
			rv[1] = 1;
			break;
		case "c":
			rv[1] = 2;
			break;
		case "d":
			rv[1] = 3;
			break;
		case "e":
			rv[1] = 4;
			break;
		case "f":
			rv[1] = 5;
			break;
		case "g":
			rv[1] = 6;
			break;
		case "h":
			rv[1] = 7;
			break;
		}
		rv[1] += 8 * (Integer.parseInt(s[3]) - 1);
		return rv;

	}
	
	public static void movePiece(int square1, int square2) {
		board.movePiece(square1, square2);
		board.printBoard();
	}
	
	/** 
	 * Given a position, this function will return the piece that is on the square
	 * @param position position for the piece (0-63)
	 * @return char representing the type of piece on the square
	 */
	public static char getPieceFromPos(int position) {
		return board.getPieces()[position];
	}
	
	public static boolean isSelectableSquare(int position) {
		char piece = getPieceFromPos(position);
		if(piece == ' ')
			return false;
		else if(Character.isUpperCase(piece) && !board.getWhiteTurn() 
				|| Character.isLowerCase(piece) && board.getWhiteTurn())
			return false;
		return true;
	}
	
	public static void changeSquareColor(int position, Color c) {
		((ClickableSquare) squares[position]).setColor(c);
	}

}


class ClickableSquare extends JPanel {

	private boolean isSelected;
	private int position;
	
	public void setColor(Color c) {
		setBackground(c);
	}

	public ClickableSquare(int pos) {
		position = pos;
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
//				if(isSelected = !isSelected)
//					setBackground(Color.RED);
			
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if(MainDisplay.selectedPiece == -1 && MainDisplay.isSelectableSquare(position)) {
					// Checks if there is already a piece selected
					// Makes sure there is a piece on the square
					MainDisplay.selectedPiece = pos; // Selects the piece
					setColor(Color.RED);
				}
				else if(MainDisplay.selectedPiece != -1){ // Will make the move because another piece is selected
					MainDisplay.movePiece(MainDisplay.selectedPiece, pos);
					if((MainDisplay.selectedPiece / 8 % 2 + MainDisplay.selectedPiece) % 8 % 2 == 0) MainDisplay.changeSquareColor(MainDisplay.selectedPiece, Color.DARK_GRAY);
					else MainDisplay.changeSquareColor(MainDisplay.selectedPiece, Color.WHITE);
					MainDisplay.selectedPiece = -1;
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
}
