package chessFinal;

import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Display extends JFrame {

	private static final double ASPECT_RATIO = 1.0; // Keeps the window squared
//	private static Square[][] squares = new Square[8][8];
	private JPanel contentPane;
	private Board board;

	public Display() {
		
		// Sets up JFrame features
		setTitle("Chess");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupSizing();
		// Setup content pane for viewing items
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(8, 8, 0, 0)); // Chess layout
		// Creates a board to be setup on a given JPanel
		board = new Board(contentPane);
//		setupBoard(); // has uneven images that gets fixed on component listener during repaint
	}

	void setupSizing() {

		setResizable(true);
		setBounds(100, 100, 800, 800);
		

		// Add a component listener to handle resizing events
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int width = getWidth();
				int height = (int) (width / ASPECT_RATIO);
				setSize(width, height);
				// Can only access square object in board
				// Resizes icons to new window size
				board.resizeImages();
			}
		});

	}
	

}