package tk.test1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Vector;

import javax.swing.JPanel;

public class GamePlat extends JPanel implements Runnable, KeyListener {
	Player p1;
	Vector<EnemyTk> enemy=new Vector<EnemyTk>();
	Vector<Bomb> bom=new Vector<Bomb>();//explosion
	Vector<Brick> brk=new Vector<Brick>();//the walls
	public GamePlat(){
		p1=new Player(400,400,0);
		Random rdm=new Random(); 
		for(int i=0;i<7;i++){
			EnemyTk etk=new EnemyTk(i*70+20,rdm.nextInt(100)+10,rdm.nextInt(4));
			new Thread(etk).start();
			etk.setEmy(enemy);
			etk.setBrk(brk);
			etk.setPler(p1);
			etk.shoot();
			enemy.add(etk);
		}
		p1.emy=enemy;
		p1.brk=brk;
		//create walls and put them into class Brick
		for(int i=0;i<2;i++){
			for(int j=0;j<16;j++){
				Brick bk=new Brick(620-j*20,i*20+180);
				brk.add(bk);
			}	
			for(int j=0;j<16;j++){
				Brick bk=new Brick(j*20,i*20+320);
				brk.add(bk);
			}
		}
		for(int i=0;i<4;i++){
			/*for(int j=0;j<2;j++){
				Brick bk=new Brick(340-j*20, 160-i*20);
				brk.add(bk);
			}*/
			for(int j=0;j<2;j++){
				Brick bk=new Brick(280+j*20, i*20+360);
				brk.add(bk);
			}
		}
		//listen the mouse 
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				//judge whether click your own tank
				switch(p1.direct){
				case 0:	
					if(e.getX()>p1.x && e.getX()<p1.x+p1.width[0] && e.getY()>p1.y && e.getY()<p1.y+p1.height[0]){
						p1.pt.y+=5;
						GamePlat.this.repaint();
					}
					break;
				case 1:	
					if(e.getX()>p1.x && e.getX()<p1.x+p1.width[1] && e.getY()>p1.y && e.getY()<p1.y+p1.height[1]){
						p1.pt.x-=5;
						GamePlat.this.repaint();
					}
					break;
				case 2:	
					if(e.getX()>p1.x && e.getX()<p1.x+p1.width[2] && e.getY()>p1.y && e.getY()<p1.y+p1.height[2]){
						p1.pt.y-=5;
						GamePlat.this.repaint();
					}
					break;
				case 3:	
					if(e.getX()>p1.x && e.getX()<p1.x+p1.width[3] && e.getY()>p1.y && e.getY()<p1.y+p1.height[3]){
						p1.pt.x+=5;
						GamePlat.this.repaint();
					}
					break;
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				if(p1==null || !p1.isLive){
					return;
				}
				switch(p1.direct){
				case 0:	
					if(e.getX()>p1.x && e.getX()<p1.x+p1.width[0] && e.getY()>p1.y && e.getY()<p1.y+p1.height[0]){
						p1.pt.y-=5;
						Bullet bt=new Bullet(p1.x+(p1.width[0]-8)/2,p1.y,0); 
						new Thread(bt).start();
						p1.blt.add(bt);
						GamePlat.this.repaint();
					}
					break;
				case 1:	
					if(e.getX()>p1.x && e.getX()<p1.x+p1.width[1] && e.getY()>p1.y && e.getY()<p1.y+p1.height[1]){
						p1.pt.x+=5;
						Bullet bt=new Bullet(p1.x+p1.width[1],p1.y+(p1.height[1]-8)/2,1); 
						new Thread(bt).start();
						p1.blt.add(bt);
						GamePlat.this.repaint();
					}
					break;
				case 2:	
					if(e.getX()>p1.x && e.getX()<p1.x+p1.width[2] && e.getY()>p1.y && e.getY()<p1.y+p1.height[2]){
						p1.pt.y+=5;
						Bullet bt=new Bullet(p1.x+(p1.width[2]-8)/2,p1.y+p1.height[2],2); 
						new Thread(bt).start();
						p1.blt.add(bt);
						GamePlat.this.repaint();
					}
					break;
				case 3:	
					if(e.getX()>p1.x && e.getX()<p1.x+p1.width[3] && e.getY()>p1.y && e.getY()<p1.y+p1.height[3]){
						p1.pt.x-=5;
						Bullet bt=new Bullet(p1.x,p1.y+(p1.height[3]-8)/2,3); 
						new Thread(bt).start();
						p1.blt.add(bt);
						GamePlat.this.repaint();
					}
					break;
				}
			}
			
		});
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 640, 480);
		g.setColor(Color.black);
		g.drawRect(0, 0, 640, 480);
		if(p1!=null){
			if(p1.isLive){
				p1.drawTank(g, this);
			}
		}
		//draw the walls
		for(int i=0;i<brk.size();i++){
			Brick bk=brk.get(i);
			if(bk.isLive){
				bk.drawBrick(g);
			}
			else{
				brk.remove(bk);
			}
		}
		//draw the computers' tanks
		for(int i=0;i<enemy.size();i++){
			EnemyTk etk=enemy.get(i);
			if(etk.isLive){
				etk.drawTank(g, this);
			}
			else{
				enemy.remove(etk);				
			}
		}
		//draw the computers' bullet
		for(int i=0;i<enemy.size();i++){
			EnemyTk etk=enemy.get(i);
			for(int j=0;j<etk.blt.size();j++){
				Bullet bt=etk.blt.get(j);
				if(bt!=null && bt.isLive){
					g.setColor(bt.color);
					g.fillOval(bt.x, bt.y, bt.width, bt.height);
				}
				else{
					etk.blt.remove(bt);
					//System.out.println("the nummber of bullet"+p1.blt.size());
				}
			}
			
		}
		//draw your own tanks and bullets
		for(int i=0;i<p1.blt.size();i++){
			Bullet bt=p1.blt.get(i);
			if(bt!=null && bt.isLive){
				g.setColor(bt.color);
				g.fillOval(bt.x, bt.y, bt.width, bt.height);
			}
			else{
				p1.blt.remove(bt);
				//System.out.println("×Óµ¯¸öÊý£º"+p1.blt.size());
			}
		}
		//draw the image of explosion
		for(int i=0;i<bom.size();i++){
			Bomb bm=bom.get(i);			
			if(bm.live>8){
				g.drawImage(bm.bmpic[0], bm.x, bm.y, 50, 50, this);
			}else if(bm.live>4){
				g.drawImage(bm.bmpic[1], bm.x, bm.y, 50, 50, this);
			}
			else{
				g.drawImage(bm.bmpic[2], bm.x, bm.y,50,50, this);
			}
			bm.subLive();
			if(bm.live<=0){
				bom.remove(bm);
			}
		}
		
	}
	//judge whether the bullet hit the walls
		public void hitBrick(Bullet bt,Brick bk){
			if(bt==null || bk==null){
				return;
			}
			switch(bt.direct){
			case 0:
				if(bt.x+bt.width/2>=bk.x && bt.x+bt.width/2<=bk.x+bk.w && bt.y>=bk.y && bt.y<=bk.y+bk.h){
					bt.isLive=false;
					bk.isLive=false;
				}
				break;
			case 1:
				if(bt.x+bt.width>=bk.x && bt.x<=bk.x+bk.w && bt.y+bt.height/2>=bk.y && bt.y+bt.height/2<=bk.y+bk.h){
					bt.isLive=false;
					bk.isLive=false;					
				}
				break;
			case 2:
				if(bt.x+bt.width/2>=bk.x && bt.x+bt.width/2<=bk.x+bk.w && bt.y+bt.height>=bk.y && bt.y<=bk.y+bk.h){
					bt.isLive=false;
					bk.isLive=false;
				}
				break;
			case 3:
				if(bt.x+bt.width>=bk.x && bt.x<=bk.x+bk.w && bt.y+bt.height/2>=bk.y && bt.y+bt.height/2<=bk.y+bk.h){
					bt.isLive=false;
					bk.isLive=false;
				}
				break;
			}
		}
	//whether bullet hits the computers' tanks
	public void hitTanks(Bullet bt,Tank tk){
		if(bt==null || tk==null){
			return;
		}
		switch(bt.direct){
		case 0:
			if(bt.x>=tk.x && bt.x+bt.width<=tk.x+tk.width[tk.direct] && bt.y>=tk.y && bt.y<=tk.y+tk.height[tk.direct]){
				bt.isLive=false;
				tk.isLive=false;
				Bomb b=new Bomb(tk.x,tk.y);
				bom.add(b);
			}
			break;
		case 1:
			if(bt.x+bt.width>=tk.x && bt.x<=tk.x+tk.width[tk.direct] && bt.y>=tk.y && bt.y+bt.height<=tk.y+tk.height[tk.direct]){
				bt.isLive=false;
				tk.isLive=false;
				Bomb b=new Bomb(tk.x,tk.y);
				bom.add(b);
			}
			break;
		case 2:
			if(bt.x>=tk.x && bt.x+bt.width<=tk.x+tk.width[tk.direct] && bt.y+bt.height>=tk.y && bt.y<=tk.y+tk.height[tk.direct]){
				bt.isLive=false;
				tk.isLive=false;
				Bomb b=new Bomb(tk.x,tk.y);
				bom.add(b);
			}
			break;
		case 3:
			if(bt.x+bt.width>=tk.x && bt.x<=tk.x+tk.width[tk.direct] && bt.y>=tk.y && bt.y+bt.height<=tk.y+tk.height[tk.direct]){
				bt.isLive=false;
				tk.isLive=false;
				Bomb b=new Bomb(tk.x,tk.y);
				bom.add(b);
			}
			break;
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			//whether user hit their enemy
			if(p1!=null){
				for(int i=0;i<p1.blt.size();i++){
					Bullet bt=p1.blt.get(i);
					for(int j=0;j<enemy.size();j++){
						this.hitTanks(bt, enemy.get(j));
					}
					//or hit the walls
					for(int j=0;j<brk.size();j++){
						this.hitBrick(bt, brk.get(j));						
					}
				}
			}		
			
			//computers' tanks can shoot the bullet and check if it hits users'
			for(int i=0;i<enemy.size();i++){
				EnemyTk etk=enemy.get(i);
				if(etk.blt.size()<1){
					etk.shoot();
				}
				if(p1==null || !p1.isLive){
					break;
				}
					
				for(int j=0;j<etk.blt.size();j++){
					Bullet bt=etk.blt.get(j);
					this.hitTanks(bt, p1);	
					//or hits walls
					for(int n=0;n<brk.size();n++){
						this.hitBrick(bt, brk.get(n));						
					}
				}
			}
			this.repaint();
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			p1.direct=0;
			if(!p1.isTouchBrick() && !p1.isTouchTank())
				p1.move();
			else{
				p1.y+=6;
				p1.pt.y+=6;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			p1.direct=1;
			if(!p1.isTouchBrick() && !p1.isTouchTank())
				p1.move();
			else{
				p1.x-=6;
				p1.pt.x-=6;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			p1.direct=2;
			if(!p1.isTouchBrick() && !p1.isTouchTank())
				p1.move();
			else{
				p1.y-=6;
				p1.pt.y-=6;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			p1.direct=3;
			if(!p1.isTouchBrick() && !p1.isTouchTank())
				p1.move();
			else{
				p1.x+=6;
				p1.pt.x+=6;
			}
		}
		if(e.getKeyChar()==KeyEvent.VK_SPACE){
			Bullet bt=null;
			switch(p1.direct){
			case 0:
				bt=new Bullet(p1.x+(p1.width[0]-8)/2,p1.y,0); 
				new Thread(bt).start();
				p1.blt.add(bt);
				break;
			case 1:
				bt=new Bullet(p1.x+p1.width[1],p1.y+(p1.height[1]-8)/2,1); 
				new Thread(bt).start();
				p1.blt.add(bt);
				break;
			case 2:
				bt=new Bullet(p1.x+(p1.width[2]-8)/2,p1.y+p1.height[2],2); 
				new Thread(bt).start();
				p1.blt.add(bt);
				break;
			case 3:
				bt=new Bullet(p1.x,p1.y+(p1.height[3]-8)/2,3); 
				new Thread(bt).start();
				p1.blt.add(bt);
				break;
			}
		}
		this.repaint();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
