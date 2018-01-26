package tk.test1;

import java.awt.Color;

public class Bullet implements Runnable {
	int x;
	int y;
	int direct;
	int speed=3;
	int width=8;
	int height=8;
	Color color=Color.blue;
	boolean isLive=true;
	public Bullet(int x,int y,int direct){
		this.x=x;
		this.y=y;
		this.direct=direct;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			if(this.x<0 || this.x>640 || this.y<0 || this.y>480){
				this.isLive=false;
				break;
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch(this.direct){
			case 0:
				this.y-=speed;
				break;
			case 1:
				this.x+=speed;
				break;
			case 2:
				this.y+=speed;
				break;
			case 3:
				this.x-=speed;
				break;
			}
		}
	}

}
