package chessFinal;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Square extends JPanel {

	boolean defaultColor; // black is 0, white is 1
	int posX, posY;
	Piece piece;
	JLabel img;
	// Statically declaration (resizing) for needed images, this class primarily
	// handles graphics
	private static ImageIcon WHITE_PAWN, WHITE_BISHOP, WHITE_ROOK, WHITE_KNIGHT, WHITE_QUEEN, WHITE_KING, BLACK_PAWN,
			BLACK_BISHOP, BLACK_ROOK, BLACK_KNIGHT, BLACK_QUEEN, BLACK_KING;

	public Square(boolean defaultColor, int posX, int posY) {

		this.defaultColor = defaultColor;
		this.posX = posX;
		this.posY = posY;
		img = new JLabel();
		// Set background color
		if (defaultColor)
			setBackground(Color.DARK_GRAY);
		else
			setBackground(Color.WHITE);
		setPiecePictures(); // Prevent nullptr
		add(img); // Add JLabel image for pieces
	}

	void setPiece(Piece piece) {
		this.piece = piece;
	}

	void repaintSquare() {

		int num = piece != null ? piece.type : -1;

		switch (num) {
		case -1:
			img.setIcon(null); // No piece, clear the icon
			break;
		case Piece.PAWN:
			img.setIcon(piece.isWhite() ? WHITE_PAWN : BLACK_PAWN);
			break;
		case Piece.BISHOP:
			img.setIcon(piece.isWhite() ? WHITE_BISHOP : BLACK_BISHOP);
			break;
		case Piece.ROOK:
			img.setIcon(piece.isWhite() ? WHITE_ROOK : BLACK_ROOK);
			break;
		case Piece.KNIGHT:
			img.setIcon(piece.isWhite() ? WHITE_KNIGHT : BLACK_KNIGHT);
			break;
		case Piece.QUEEN:
			img.setIcon(piece.isWhite() ? WHITE_QUEEN : BLACK_QUEEN);
			break;
		case Piece.KING:
			img.setIcon(piece.isWhite() ? WHITE_KING : BLACK_KING);
			break;
		default:
			img.setIcon(null); // In case of an undefined type, clear the icon
			break;
		}
	}
	// Should I move this to piece.java

	// Statically initializes each image, but can read instances
	public void setPiecePictures() {
		try { // Maybe we can use an array for images and a for loop to go through each file
			BufferedImage img = ImageIO.read(new File("images/white_pawn.png"));
			Image dimg = img.getScaledInstance(getWidth() - 10, getHeight() - 10, Image.SCALE_SMOOTH);
			WHITE_PAWN = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/black_pawn.png"));
			dimg = img.getScaledInstance(getWidth() - 10, getHeight() - 10, Image.SCALE_SMOOTH);
			BLACK_PAWN = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/white_bishop.png"));
			dimg = img.getScaledInstance(getWidth() - 10, getHeight() - 10, Image.SCALE_SMOOTH);
			WHITE_BISHOP = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/black_bishop.png"));
			dimg = img.getScaledInstance(getWidth() - 10, getHeight() - 10, Image.SCALE_SMOOTH);
			BLACK_BISHOP = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/white_knight.png"));
			dimg = img.getScaledInstance(getWidth() - 10, getHeight() - 10, Image.SCALE_SMOOTH);
			WHITE_KNIGHT = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/black_knight.png"));
			dimg = img.getScaledInstance(getWidth() - 10, getHeight() - 10, Image.SCALE_SMOOTH);
			BLACK_KNIGHT = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/white_rook.png"));
			dimg = img.getScaledInstance(getWidth() - 10, getHeight() - 10, Image.SCALE_SMOOTH);
			WHITE_ROOK = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/black_rook.png"));
			dimg = img.getScaledInstance(getWidth() - 10, getHeight() - 10, Image.SCALE_SMOOTH);
			BLACK_ROOK = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/white_queen.png"));
			dimg = img.getScaledInstance(getWidth() - 10, getHeight() - 10, Image.SCALE_SMOOTH);
			WHITE_QUEEN = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/black_queen.png"));
			dimg = img.getScaledInstance(getWidth() - 10, getHeight() - 10, Image.SCALE_SMOOTH);
			BLACK_QUEEN = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/white_king.png"));
			dimg = img.getScaledInstance(getWidth() - 10, getHeight() - 10, Image.SCALE_SMOOTH);
			WHITE_KING = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/black_king.png"));
			dimg = img.getScaledInstance(getWidth() - 10, getHeight() - 10, Image.SCALE_SMOOTH);
			BLACK_KING = new ImageIcon(dimg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
