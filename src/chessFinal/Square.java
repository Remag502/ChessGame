package chessFinal;

import java.awt.Color;

import javax.swing.JPanel;

public class Square extends JPanel {
	
	boolean defaultColor; // black is 0, white is 1
	int posX, posY;
	
	public Square(boolean defaultColor, int posX, int posY) {
		
		this.defaultColor = defaultColor;
		this.posX = posX;
		this.posY = posY;
		
		if (defaultColor) setBackground(Color.DARK_GRAY);
		else setBackground(Color.WHITE);
		
	}
	
}
