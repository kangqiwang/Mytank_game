package tk.test1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GmStartPn extends JPanel {
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 640, 480);
		g.setColor(Color.black);
		g.drawRect(0, 0, 640, 480);
		g.setColor(Color.blue);
		g.setFont(new Font("bold",Font.BOLD, 30));
		g.drawString("MY Tank Game", 200, 200);
	}

}
