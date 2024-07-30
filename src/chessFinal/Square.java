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
	// Statically declaration (resizing) for needed images, this class primarily handles graphics
	private static ImageIcon white_pawn, white_bishop, white_rook, white_knight, white_queen, white_king, black_pawn,
	black_bishop, black_rook, black_knight, black_queen, black_king;
	
	public Square(boolean defaultColor, int posX, int posY) {
		
		this.defaultColor = defaultColor;
		this.posX = posX;
		this.posY = posY;
		img = new JLabel();
		// Set background color
		if (defaultColor) setBackground(Color.DARK_GRAY);
		else setBackground(Color.WHITE);
		setPiecePictures(); // Prevent nullptr
		add(img); // Add JLabel image for pieces
	}
	
	void updatePiece(Piece piece) {
		
		this.piece = piece;
		int num = piece.type;
		
		switch (piece.type) {
		default:
			img.setIcon(white_pawn);
		}
	}
	
	// Should I move this to piece.java
	
	// Statically initializes each image, but can read instances
	public void setPiecePictures() {
		try { // Maybe we can use an array for images and a for loop to go through each file
			BufferedImage img = ImageIO.read(new File("images/white_pawn.png"));
			Image dimg = img.getScaledInstance(getWidth() - 10, getHeight() - 10,
					Image.SCALE_SMOOTH);
			white_pawn = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/black_pawn.png"));
			dimg = img.getScaledInstance(getWidth() - 10, getHeight() - 10, Image.SCALE_SMOOTH);
			black_pawn = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/white_bishop.png"));
			dimg = img.getScaledInstance(getWidth() - 10, getHeight() - 10, Image.SCALE_SMOOTH);
			white_bishop = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/black_bishop.png"));
			dimg = img.getScaledInstance(getWidth() - 10, getHeight() - 10, Image.SCALE_SMOOTH);
			black_bishop = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/white_knight.png"));
			dimg = img.getScaledInstance(getWidth() - 10, getHeight() - 10, Image.SCALE_SMOOTH);
			white_knight = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/black_knight.png"));
			dimg = img.getScaledInstance(getWidth() - 10, getHeight() - 10, Image.SCALE_SMOOTH);
			black_knight = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/white_rook.png"));
			dimg = img.getScaledInstance(getWidth() - 10, getHeight() - 10, Image.SCALE_SMOOTH);
			white_rook = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/black_rook.png"));
			dimg = img.getScaledInstance(getWidth() - 10, getHeight() - 10, Image.SCALE_SMOOTH);
			black_rook = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/white_queen.png"));
			dimg = img.getScaledInstance(getWidth() - 10, getHeight() - 10, Image.SCALE_SMOOTH);
			white_queen = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/black_queen.png"));
			dimg = img.getScaledInstance(getWidth() - 10, getHeight() - 10, Image.SCALE_SMOOTH);
			black_queen = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/white_king.png"));
			dimg = img.getScaledInstance(getWidth() - 10, getHeight() - 10, Image.SCALE_SMOOTH);
			white_king = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/black_king.png"));
			dimg = img.getScaledInstance(getWidth() - 10, getHeight() - 10, Image.SCALE_SMOOTH);
			black_king = new ImageIcon(dimg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
