package chessFinal;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;

public class Display extends JFrame {

    private static final double ASPECT_RATIO = 1.0; // Keeps the window squared

    public Display() {
        setResizable(true);
        setTitle("Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

}