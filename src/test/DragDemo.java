package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DragDemo extends JFrame {

    private JLabel draggableLabel;
    private JPanel panel;
    private JLayeredPane layeredPane;

    public DragDemo() {
        // Setup the JFrame
        setTitle("Drag JLabel Outside JPanel");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Absolute layout for demo purposes

        // Create a JLayeredPane to handle multiple layers
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 500, 500);
        add(layeredPane);

        // Create a JPanel
        panel = new JPanel();
        panel.setBounds(50, 50, 300, 300);
        panel.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);
        panel.setLayout(null);

        // Create a JLabel
        draggableLabel = new JLabel("Drag Me");
        draggableLabel.setOpaque(true);
        draggableLabel.setBackground(Color.RED);
        draggableLabel.setBounds(50, 50, 100, 50);
        panel.add(draggableLabel);

        // Add MouseListener and MouseMotionListener for dragging
        draggableLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startDrag(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                stopDrag();
            }
        });

        draggableLabel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                moveLabel(e);
            }
        });

        setVisible(true);
    }

    private void startDrag(MouseEvent e) {
        // Remove the label from the JPanel and add it to the layeredPane
        panel.remove(draggableLabel);
        layeredPane.add(draggableLabel, JLayeredPane.DRAG_LAYER);
        draggableLabel.setLocation(SwingUtilities.convertPoint(panel, draggableLabel.getLocation(), layeredPane));
        layeredPane.repaint();
    }

    private void stopDrag() {
        // Place the label back into the JPanel or keep it on the layeredPane
        Point labelLocation = draggableLabel.getLocation();
        if (panel.getBounds().contains(labelLocation)) {
            // If the label is dropped inside the panel bounds, add it back to the panel
            layeredPane.remove(draggableLabel);
            draggableLabel.setLocation(SwingUtilities.convertPoint(layeredPane, labelLocation, panel));
            panel.add(draggableLabel);
            panel.repaint();
        } else {
            // Otherwise, leave it on the layeredPane
            draggableLabel.setLocation(labelLocation);
        }
    }

    private void moveLabel(MouseEvent e) {
        Point p = SwingUtilities.convertPoint(draggableLabel, e.getPoint(), layeredPane);
        draggableLabel.setLocation(p.x - draggableLabel.getWidth() / 2, p.y - draggableLabel.getHeight() / 2);
        layeredPane.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DragDemo());
    }
}
