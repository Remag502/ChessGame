package chessFinal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class Display extends JFrame {

	private static final double ASPECT_RATIO = 1.0; // Keeps the window squared
//	private static Square[][] squares = new Square[8][8];
	public static JLayeredPane layeredPane;
	public static JPanel boardPanel;
	private static Board board;
	private static Stack<Board> moveHistory;

	public Display() {

		// Sets up JFrame features
		setTitle("Chess");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupSizing();
		// Setup content pane for viewing items
		layeredPane = new JLayeredPane();
//		setContentPane(contentPane);
		setLayout(new BorderLayout());
//		add(layeredPane, BorderLayout.CENTER);
		setContentPane(layeredPane);
		boardPanel = new JPanel();
		// Sets up the board JPanel to be on the back layer of JLayeredPane
		moveHistory = new Stack<Board>();
		handleInput();
		board = new Board(boardPanel);
		Board.addBoardToHistory();
		layeredPane.add(boardPanel, JLayeredPane.DEFAULT_LAYER);
	}

	void setupSizing() {

		setResizable(true);
		setBounds(400, 200, 800, 800);
		setVisible(true);

		// Add a component listener to handle resizing events
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int width = getWidth();
				int height = (int) (width / ASPECT_RATIO);
				setSize(width, height);
				// Can only access square object in board
				// Resizes icons to new window size
				board.resizeBoard(boardPanel);
			}
		});

		addWindowStateListener(new WindowStateListener() {
			@Override
			public void windowStateChanged(WindowEvent e) {
				if ((e.getNewState() & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
					int width = getHeight();
					int height = width;
					setSize(width, height);
					board.resizeBoard(boardPanel);
				}
			}
		});

	}

	public static Stack<Board> getMoveHistory() {
		return moveHistory;
	}

//	public static void setBoard(Board board) {
//		Display.board = board;
//	}

	public static Board getBoard() {
		return board;
	}

	private void handleInput() {

		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				
				int keyCode = e.getKeyCode();
				switch (keyCode) {
				
				case KeyEvent.VK_LEFT:
					// Call method to rollback a move
					System.out.println("Left arrow key pressed");
					Board.rollBack();
					break;
				case KeyEvent.VK_RIGHT:
					// Call method to roll forward a move
					System.out.println("Right arrow key pressed");
					Board.rollForward();
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

	}

}
