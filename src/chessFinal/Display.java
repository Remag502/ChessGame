package chessFinal;

import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Display extends JFrame {

	private static final double ASPECT_RATIO = 1.0; // Keeps the window squared
	private JPanel contentPane;

	public Display() {
		
		// Sets up JFrame features
		setTitle("Chess");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupSizing();
		// Setup content pane for viewing items
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(8, 8, 0, 0)); // Chess layout
		initializeBackground();

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
			}
		});

	}

	void initializeBackground() {
		
		int number = 0; // Used to have alternating colors
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				// Tinkered w/ line under to get correct color order
				boolean defaultColor = ((i + number) % 2 == 1) ? false : true; 
				Square square = new Square(defaultColor, i, j);
				number++;
				// Add square to our JFrame
				contentPane.add(square, i, j);
			}
		}
		
	}

}