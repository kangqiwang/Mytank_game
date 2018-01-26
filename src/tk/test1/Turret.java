package tk.test1;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Turret {
	int x;
	int y;
	int direct;
	int[] width;
	int[] height;
	BufferedImage[] ptpic;
	public Turret(int x,int y,int direct){
		this.x=x;
		this.y=y;
		this.direct=direct;
		ptpic=new BufferedImage[4];
		width=new int[4];
		height=new int[4];		
		try {
			ptpic[0]=ImageIO.read(new File("player/pt_up.png"));			
			width[0]=ptpic[0].getWidth();
			height[0]=ptpic[0].getHeight();			
			ptpic[1]=ImageIO.read(new File("player/pt_right.png"));			
			width[1]=ptpic[1].getWidth();
			height[1]=ptpic[1].getHeight();					
			ptpic[2]=ImageIO.read(new File("player/pt_down.png"));			
			width[2]=ptpic[2].getWidth();
			height[2]=ptpic[2].getHeight();		
			ptpic[3]=ImageIO.read(new File("player/pt_left.png"));
			width[3]=ptpic[3].getWidth();
			height[3]=ptpic[3].getHeight();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	

}
