package tk.test1;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Player extends Tank {
	Turret pt;
	Vector<Bullet> blt=new Vector<Bullet>();
	Vector<EnemyTk> emy;
	Vector<Brick> brk=null;
	public Player(int x,int y,int direct){
		this.x=x;
		this.y=y;
		this.direct=direct;
		//instantiation 
		pt=new Turret(x,y,direct);
		tkpic=new BufferedImage[4];
		width=new int[4];
		height=new int[4];		
		try {
			tkpic[0]=ImageIO.read(new File("player/base_up.png"));			
			width[0]=tkpic[0].getWidth();
			height[0]=tkpic[0].getHeight();			
			tkpic[1]=ImageIO.read(new File("player/base_right.png"));			
			width[1]=tkpic[1].getWidth();
			height[1]=tkpic[1].getHeight();					
			tkpic[2]=ImageIO.read(new File("player/base_down.png"));			
			width[2]=tkpic[2].getWidth();
			height[2]=tkpic[2].getHeight();		
			tkpic[3]=ImageIO.read(new File("player/base_left.png"));
			width[3]=tkpic[3].getWidth();
			height[3]=tkpic[3].getHeight();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void drawTank(Graphics g, JPanel p){		
			switch(this.direct){
			case 0:
				g.drawImage(this.tkpic[0], x, y, p);
				g.drawImage(pt.ptpic[0], pt.x+(this.width[0]-pt.width[0])/2, pt.y, p);
				break;
			case 1:
				g.drawImage(this.tkpic[1], x, y, p);
				g.drawImage(pt.ptpic[1], pt.x+(this.width[1]-pt.width[1]), pt.y+(this.height[1]-pt.height[1])/2, p);
				break;
			case 2:
				g.drawImage(this.tkpic[2], x, y, p);
				g.drawImage(pt.ptpic[2], pt.x+(this.width[2]-pt.width[2])/2, pt.y+(this.height[2]-pt.height[2]), p);
				break;
			case 3:
				g.drawImage(this.tkpic[3], x, y, p);
				g.drawImage(pt.ptpic[3], pt.x, pt.y+(this.height[3]-pt.height[3])/2, p);
				break;
			}		
	}
	//whether touch each other
	public boolean isTouchTank(){
		boolean b=false;
		//whether users' tank touch others		
		switch(this.direct){
		case 0:			
			for(int i=0;i<emy.size();i++){
				EnemyTk tk=emy.get(i);				
				if(this.x+this.width[this.direct]>=tk.x && this.x<=tk.x+tk.width[tk.direct] && this.y>=tk.y && this.y<=tk.y+tk.height[tk.direct]){
					return true;
				}				
			}			
			break;
		case 1:			
			for(int i=0;i<emy.size();i++){
				EnemyTk tk=emy.get(i);
					if(this.x+this.width[this.direct]>=tk.x && this.x<=tk.x+tk.width[tk.direct] && this.y+this.height[this.direct]>=tk.y && this.y<=tk.y+tk.height[tk.direct]){
						return true;
					}
			}	
			break;
		case 2:
			for(int i=0;i<emy.size();i++){
				EnemyTk tk=emy.get(i);
					if(this.x+this.width[this.direct]>=tk.x && this.x<=tk.x+tk.width[tk.direct] && this.y+this.height[this.direct]>=tk.y && this.y<=tk.y+tk.height[tk.direct]){
						return true;
					}
			}		
			break;
		case 3:
			for(int i=0;i<emy.size();i++){
				EnemyTk tk=emy.get(i);
					if(this.x>=tk.x && this.x<=tk.x+tk.width[tk.direct] && this.y+this.height[this.direct]>=tk.y && this.y<=tk.y+tk.height[tk.direct]){
						return true;
					}
			}	
			break;
		}
		return b;
	}
	//whether computers' tanks hit the wall and others
	public boolean isTouchBrick(){
		boolean b=false;
		//		
		switch(this.direct){
		case 0:			
			for(int i=0;i<brk.size();i++){
				Brick bk=brk.get(i);
				if(this.x+this.width[this.direct]>=bk.x && this.x<=bk.x+bk.w && this.y>=bk.y && this.y<=bk.y+bk.h){
					return true;				
				}
			}			
			break;
		case 1:			
			for(int i=0;i<brk.size();i++){
				Brick bk=brk.get(i);				
				if(this.x+this.width[this.direct]>=bk.x && this.x<=bk.x+bk.w && this.y+this.height[this.direct]>=bk.y && this.y<=bk.y+bk.h){
					return true;				
				}
			}	
			break;
		case 2:
			for(int i=0;i<brk.size();i++){
				Brick bk=brk.get(i);				
					if(this.x+this.width[this.direct]>=bk.x && this.x<=bk.x+bk.w && this.y+this.height[this.direct]>=bk.y && this.y<=bk.y+bk.h){
						return true;				
				}
			}		
			break;
		case 3:
			for(int i=0;i<brk.size();i++){
				Brick bk=brk.get(i);				
					if(this.x>=bk.x && this.x<=bk.x+bk.w && this.y+this.height[this.direct]>=bk.y && this.y<=bk.y+bk.h){
						return true;				
				}
			}	
			break;
		}
		return b;
	}
	public void move(){
		switch(this.direct){
		case 0:
			this.y-=this.speed;
			this.pt.y-=this.speed;
			break;
		case 1:
			this.x+=this.speed;
			this.pt.x+=this.speed;
			break;
		case 2:
			this.y+=this.speed;
			this.pt.y+=this.speed;
			break;
		case 3:
			this.x-=this.speed;
			this.pt.x-=this.speed;			
			break;		
		}
	}

}
