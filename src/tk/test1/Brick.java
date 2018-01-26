package tk.test1;

import java.awt.Color;
import java.awt.Graphics;

public class Brick {
	int x,y;
	int w=20;
	int h=20;
	Color color=Color.orange;
	boolean isLive=true;
	Brick(int x,int y){
		this.x=x;
		this.y=y;
	}
	public void drawBrick(Graphics g){
		g.setColor(color);
		g.fillRect(x, y, w, h);
		g.setColor(Color.black);
		g.drawRect(x, y, w, h);
	}

}
