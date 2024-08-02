package chessFinal;

import java.awt.Color;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class Square extends JPanel {

	private boolean defaultColor; // black is 0, white is 1
	private int posX, posY;
	private Piece piece;
	private JLabel img;
	private Square squareInstance;
	// Statically declaration (resizing) for needed images, this class primarily
	// handles graphics
	private static ImageIcon WHITE_PAWN, WHITE_BISHOP, WHITE_ROOK, WHITE_KNIGHT, WHITE_QUEEN, WHITE_KING, BLACK_PAWN,
			BLACK_BISHOP, BLACK_ROOK, BLACK_KNIGHT, BLACK_QUEEN, BLACK_KING;

	public Square(boolean defaultColor, int posX, int posY) {
		
		this.defaultColor = defaultColor;
		this.posX = posX;
		this.posY = posY;
		img = new JLabel();
		squareInstance = this;
		// Set background color
		if (defaultColor)
			setBackground(Color.DARK_GRAY);
		else
			setBackground(Color.WHITE);
		setPiecePictures(); // Prevent nullptr
		add(img); // Add JLabel image for pieces
		handleInput();
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	public void repaintSquare() {

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


	// Statically initializes each image, but can read instances
	public void setPiecePictures() {
		int width = getWidth();
		int height = width; // maintain square shape
		try { // Maybe we can use an array for images and a for loop to go through each file
			BufferedImage img = ImageIO.read(new File("images/white_pawn.png"));
			Image dimg = img.getScaledInstance(width - 10, height - 10, Image.SCALE_SMOOTH);
			WHITE_PAWN = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/black_pawn.png"));
			dimg = img.getScaledInstance(width - 10, height - 10, Image.SCALE_SMOOTH);
			BLACK_PAWN = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/white_bishop.png"));
			dimg = img.getScaledInstance(width - 10, height - 10, Image.SCALE_SMOOTH);
			WHITE_BISHOP = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/black_bishop.png"));
			dimg = img.getScaledInstance(width - 10, height - 10, Image.SCALE_SMOOTH);
			BLACK_BISHOP = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/white_knight.png"));
			dimg = img.getScaledInstance(width - 10, height - 10, Image.SCALE_SMOOTH);
			WHITE_KNIGHT = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/black_knight.png"));
			dimg = img.getScaledInstance(width - 10, height - 10, Image.SCALE_SMOOTH);
			BLACK_KNIGHT = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/white_rook.png"));
			dimg = img.getScaledInstance(width - 10, height - 10, Image.SCALE_SMOOTH);
			WHITE_ROOK = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/black_rook.png"));
			dimg = img.getScaledInstance(width - 10, height - 10, Image.SCALE_SMOOTH);
			BLACK_ROOK = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/white_queen.png"));
			dimg = img.getScaledInstance(width - 10, height - 10, Image.SCALE_SMOOTH);
			WHITE_QUEEN = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/black_queen.png"));
			dimg = img.getScaledInstance(width - 10, height - 10, Image.SCALE_SMOOTH);
			BLACK_QUEEN = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/white_king.png"));
			dimg = img.getScaledInstance(width - 10, height - 10, Image.SCALE_SMOOTH);
			WHITE_KING = new ImageIcon(dimg);
			img = ImageIO.read(new File("images/black_king.png"));
			dimg = img.getScaledInstance(width - 10, height - 10, Image.SCALE_SMOOTH);
			BLACK_KING = new ImageIcon(dimg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void handleInput() {
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				startImageFollowing(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				Point pos = Board.findSquareCoordsWithLabel(img);
				stopImageFollowing();
				if (pos != null)
					Board.movePiece(squareInstance, pos.x, pos.y);
//				Board.movePiece(Board.squares[1][4], 4, 4);
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
		
		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				moveImageWithCursor(e);
			}

			@Override
			public void mouseMoved(MouseEvent e) {}
		});
	}
	
	 // Method to start following the cursor with the image
    private void startImageFollowing(MouseEvent e) {
        img.setVisible(true);
        remove(img); // Remove from the current square
        Display.layeredPane.add(img, JLayeredPane.DRAG_LAYER); // Add to the layered pane
        moveImageWithCursor(e); // Move the image to the cursor position
    }

    // Method to stop following the cursor with the image
    private void stopImageFollowing() {
        img.setVisible(true); // Keep the image visible when the mouse is released
        Display.layeredPane.remove(img); // Remove from the layered pane
        add(img); // Add back to the square
        img.setLocation(5, 5); // Reset the position in the square
        Display.layeredPane.repaint();
        repaint();
    }

    // Method to move the image with the cursor
    private void moveImageWithCursor(MouseEvent e) {
        Point p = SwingUtilities.convertPoint(this, e.getPoint(), Display.layeredPane);
        img.setLocation(p.x - img.getWidth() / 2, p.y - img.getHeight() / 2);
        Display.layeredPane.repaint();
    }
}

