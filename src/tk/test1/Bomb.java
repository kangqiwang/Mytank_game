package tk.test1;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Bomb {
	int x;
	int y;
	int live=12;
	BufferedImage[] bmpic=new BufferedImage[3];
	public Bomb(int x,int y){
		this.x=x;
		this.y=y;
		try {
			bmpic[0]=ImageIO.read(new File("bomb/bomb_3.gif"));
			bmpic[1]=ImageIO.read(new File("bomb/bomb_2.gif"));
			bmpic[2]=ImageIO.read(new File("bomb/bomb_1.gif"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}	
	public void subLive(){
		this.live--;
	}

}
