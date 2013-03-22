package petter.bjelm;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class MyLine extends JComponent {
	private Color c;
	public MyLine() {
		// TODO Auto-generated constructor stub
	}
	
	protected void paintComponent(Graphics g){
		if (c==null){
			c = Color.BLUE;	
		}

		//g.setColor(Color.BLUE);
		g.setColor(c);
		
		//g.draw3DRect(0, 0, getWidth(), getHeight(), true);

		g.setColor(Color.BLACK);
		g.drawLine(0, 0, 0, getHeight());
		//behövs inte här
		super.paintComponent(g);
	}

}
