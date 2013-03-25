package petter.bjelm;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class MyLine extends JComponent {
	public MyLine() {

	}
	//draws a black line
	protected void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		g.drawLine(0, 0, 0, getHeight());
	}

}
