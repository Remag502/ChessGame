package chessFinal;

import java.awt.EventQueue;

public class Main {
	
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					
					Display frame = new Display();
					frame.boardPanel.setBounds(0, 0, 784, 761);
					frame.setVisible(true); // Display and squares needs to be set so the size of each panel can be measured for the pieces
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
		
	}
	
}
