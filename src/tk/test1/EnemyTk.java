package tk.test1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class EnemyTk extends Tank implements Runnable{
	Vector<Bullet> blt=new Vector<Bullet>();
	Vector<EnemyTk> emy=null;
	Vector<Brick> brk=null;
	Player pler;
	public void setEmy(Vector<EnemyTk> emy){
		this.emy=emy;
	}
	public void setPler(Player pler){
		this.pler=pler;
	}
	public void setBrk(Vector<Brick> brk){
		this.brk=brk;
	}
	public EnemyTk(int x,int y,int direct){
		this.x=x;
		this.y=y;
		this.direct=direct;
		tkpic=new BufferedImage[4];
		width=new int[4];
		height=new int[4];		
		try {
			tkpic[0]=ImageIO.read(new File("enemy/enemy_up.png"));			
			width[0]=tkpic[0].getWidth();
			height[0]=tkpic[0].getHeight();			
			tkpic[1]=ImageIO.read(new File("enemy/enemy_right.png"));			
			width[1]=tkpic[1].getWidth();
			height[1]=tkpic[1].getHeight();					
			tkpic[2]=ImageIO.read(new File("enemy/enemy_down.png"));			
			width[2]=tkpic[2].getWidth();
			height[2]=tkpic[2].getHeight();		
			tkpic[3]=ImageIO.read(new File("enemy/enemy_left.png"));
			width[3]=tkpic[3].getWidth();
			height[3]=tkpic[3].getHeight();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	//attack
	public void shoot(){
		Bullet bt=null;
		switch(this.direct){
		case 0:
			bt=new Bullet(this.x+(this.width[0]-8)/2,this.y,this.direct);			
			break;
		case 1:
			bt=new Bullet(this.x+this.width[1],this.y+(this.height[1]-8)/2,this.direct);
			break;
		case 2:
			bt=new Bullet(this.x+(this.width[2]-8)/2,this.y+this.height[2],this.direct);
			break;
		case 3:
			bt=new Bullet(this.x,this.y+(this.height[3]-8)/2,this.direct);			
			break;
		}
		if(bt!=null){
			bt.color=Color.red;
			new Thread(bt).start();
			blt.add(bt);
		}
		
	}
	public void drawTank(Graphics g,JPanel p){
		switch(this.direct){
		case 0:
			g.drawImage(this.tkpic[0], x, y, p);
			break;
		case 1:
			g.drawImage(this.tkpic[1], x, y, p);
			break;
		case 2:
			g.drawImage(this.tkpic[2], x, y, p);
			break;
		case 3:
			g.drawImage(this.tkpic[3], x, y, p);
			break;
		
		}
	}
	//decide on whether computers' tanks crash into each others
	public boolean isTouchTank(){
		boolean b=false;
		//whether your own tank hit others		
		switch(this.direct){
		case 0:
			//whether tanks touch each other
			if(pler==null){
				return false;
			}
			if(this.x+this.width[this.direct]>=pler.x && this.x<=pler.x+pler.width[pler.direct] && this.y>=pler.y && this.y<=pler.y+pler.height[pler.direct]){
				return true;
			}
			for(int i=0;i<emy.size();i++){
				EnemyTk tk=emy.get(i);
				if(tk!=this){
					if(this.x+this.width[this.direct]>=tk.x && this.x<=tk.x+tk.width[tk.direct] && this.y>=tk.y && this.y<=tk.y+tk.height[tk.direct]){
						return true;
					}
				}
			}			
			break;
		case 1:
			//whether computers' tanks hit your own tank
			if(pler==null){
				return false;
			}
			if(this.x+this.width[this.direct]>=pler.x && this.x<=pler.x+pler.width[pler.direct] && this.y+this.height[this.direct]>=pler.y && this.y<=pler.y+pler.height[pler.direct]){
				return true;
			}
			for(int i=0;i<emy.size();i++){
				EnemyTk tk=emy.get(i);
				if(tk!=this){
					if(this.x+this.width[this.direct]>=tk.x && this.x<=tk.x+tk.width[tk.direct] && this.y+this.height[this.direct]>=tk.y && this.y<=tk.y+tk.height[tk.direct]){
						return true;
					}
				}
			}	
			break;
		case 2:
			if(pler==null){
				return false;
			}
			if(this.x+this.width[this.direct]>=pler.x && this.x<=pler.x+pler.width[pler.direct] && this.y+this.height[this.direct]>=pler.y && this.y<=pler.y+pler.height[pler.direct]){
				return true;
			}
			for(int i=0;i<emy.size();i++){
				EnemyTk tk=emy.get(i);
				if(tk!=this){
					if(this.x+this.width[this.direct]>=tk.x && this.x<=tk.x+tk.width[tk.direct] && this.y+this.height[this.direct]>=tk.y && this.y<=tk.y+tk.height[tk.direct]){
						return true;
					}
				}
			}		
			break;
		case 3:
			if(pler==null){
				return false;
			}
			if(this.x>=pler.x && this.x<=pler.x+pler.width[pler.direct] && this.y+this.height[this.direct]>=pler.y && this.y<=pler.y+pler.height[pler.direct]){
				return true;
			}
			for(int i=0;i<emy.size();i++){
				EnemyTk tk=emy.get(i);
				if(tk!=this){
					if(this.x>=tk.x && this.x<=tk.x+tk.width[tk.direct] && this.y+this.height[this.direct]>=tk.y && this.y<=tk.y+tk.height[tk.direct]){
						return true;
					}
				}
			}	
			break;
		}
		return b;
	}
	//whether computers' tanks hit the walls
	public boolean isTouchBrick(){
		boolean b=false;
		//decide on the direction		
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
	@Override
	public void run() {
		// TODO Auto-generated method stub		
		Random rdm=new Random();		
		while(true){
			int t_speed=this.speed;
			if(this.speed==0){
				t_speed=1;
			}
			int bs=0;
			if(!this.isLive){
				break;
			}
			switch(this.direct){
			case 0:
				if(this.y>0){
					bs=rdm.nextInt(this.y)/t_speed;
					for(int i=0;i<bs;i++){
						if(this.isTouchTank()||this.isTouchBrick()){
							break;
						}
						this.y-=speed;
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				break;
			case 1:
				if(this.x<(640-this.width[1])){
					bs=rdm.nextInt(640-this.x-this.width[1])/t_speed;
					for(int i=0;i<bs;i++){
						if(this.isTouchTank()||this.isTouchBrick()){
							break;
						}
						this.x+=speed;
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				break;
			case 2:
				if(this.y<(480-this.height[2])){
					bs=rdm.nextInt(480-this.y-this.height[2])/t_speed;
					for(int i=0;i<bs;i++){
						if(this.isTouchTank()||this.isTouchBrick()){
							break;
						}
						this.y+=speed;
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				break;
			case 3:
				if(this.x>0){
					bs=rdm.nextInt(this.x)/t_speed;
					for(int i=0;i<bs;i++){
						if(this.isTouchTank()||this.isTouchBrick()){
							break;
						}
						this.x-=speed;
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				break;
			}
			//System.out.println("direct="+direct+"  x="+x+"  y="+y+"  bs="+bs);
			if(this.speed!=0){
				try {					
					Thread.sleep(400);
					this.direct=rdm.nextInt(4);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}				
			}
		}
	}

}
