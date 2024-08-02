package test;

import javax.swing.*;
import java.awt.*;

public class Display extends JFrame {

    public Display() {
        // Set up JFrame features
        setTitle("JLayeredPane Background Color Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800); // Set size before making visible

        // Initialize JLayeredPane
        JLayeredPane layeredPane = new JLayeredPane() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Set background color explicitly
                g.setColor(Color.CYAN); // Example color
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        layeredPane.setLayout(null); // Use null layout for manual positioning

        // Add a dummy component to ensure visibility of the background color
        JPanel dummyPanel = new JPanel();
        dummyPanel.setOpaque(false); // Ensure the dummy panel does not obscure the background
        dummyPanel.setBounds(0, 0, 800, 800); // Set bounds to match the layeredPane
        layeredPane.add(dummyPanel, JLayeredPane.DEFAULT_LAYER);

        // Add JLayeredPane to the JFrame
        add(layeredPane);

        // Make JFrame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Display::new);
    }
}
