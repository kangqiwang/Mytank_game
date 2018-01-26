package tk.test1;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {
	GamePlat gamePt=null;
	JButton bt_start;
	JButton bt_stop;
	JButton bt_exit;
	JPanel pn2;
	GmStartPn pn_start;
	public GameFrame(){
		bt_start=new JButton("开始");
		bt_start.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if(gamePt!=null){
					gamePt.keyTyped(e);
				}				
			}
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(gamePt!=null)
					gamePt.keyPressed(e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(gamePt!=null)
					gamePt.keyReleased(e);
			}			
		});
		bt_start.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(gamePt==null){
					GameFrame.this.remove(pn_start);
					gamePt=new GamePlat();//游戏主面板
					new Thread(gamePt).start();
					GameFrame.this.addKeyListener(gamePt);	
					GameFrame.this.add(gamePt);
					GameFrame.this.setVisible(true);
					
				}
				for(int i=0;i<gamePt.enemy.size();i++){
					EnemyTk tk=gamePt.enemy.get(i);
					for(int j=0;j<tk.blt.size();j++){
						Bullet bt=tk.blt.get(j);
						bt.speed=2;
					}
					tk.speed=1;
				}	
				for(int i=0;i<gamePt.p1.blt.size();i++){
					Bullet bt=gamePt.p1.blt.get(i);
					bt.speed=2;					
				}
			}
			
		});
		bt_stop=new JButton("暂停");
		bt_stop.addKeyListener(new KeyListener(){
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub	
				if(gamePt!=null)
					gamePt.keyTyped(e);
			}
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(gamePt!=null)
					gamePt.keyPressed(e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(gamePt!=null)
					gamePt.keyReleased(e);
			}			
		});
		bt_stop.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(int i=0;i<gamePt.enemy.size();i++){
					EnemyTk tk=gamePt.enemy.get(i);
					for(int j=0;j<tk.blt.size();j++){
						Bullet bt=tk.blt.get(j);
						bt.speed=0;
					}
					tk.speed=0;
				}
				for(int i=0;i<gamePt.p1.blt.size();i++){
					Bullet bt=gamePt.p1.blt.get(i);
					bt.speed=0;					
				}
			}			
		});
		bt_exit=new JButton("退出");
		bt_exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
		bt_exit.addKeyListener(new KeyListener(){
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub	
				if(gamePt!=null)
					gamePt.keyTyped(e);
			}
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(gamePt!=null)
					gamePt.keyPressed(e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(gamePt!=null)
					gamePt.keyReleased(e);
			}
			
		});
		pn2=new JPanel();
		pn2.add(bt_start);
		pn2.add(bt_stop);
		pn2.add(bt_exit);
		pn_start=new GmStartPn();//开始的界面面板
		this.add(pn_start);
		//gamePt=new GamePlat();//游戏主面板
		//new Thread(gamePt).start();
		/*this.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_UP){
					gamePt.p1.direct=0;
					gamePt.p1.move();
					//System.out.println("up");
				}
				if(e.getKeyCode()==KeyEvent.VK_RIGHT){
					gamePt.p1.direct=1;
					gamePt.p1.move();
				}
				if(e.getKeyCode()==KeyEvent.VK_DOWN){
					gamePt.p1.direct=2;
					gamePt.p1.move();
				}
				if(e.getKeyCode()==KeyEvent.VK_LEFT){
					gamePt.p1.direct=3;
					gamePt.p1.move();
				}
				gamePt.repaint();
			}
		});*/
		//this.addKeyListener(gamePt);		
		//this.add(gamePt);
		this.add(pn2,BorderLayout.SOUTH);
		
		this.setSize(800, 600);
		int x=(Toolkit.getDefaultToolkit().getScreenSize().width-this.getWidth())/2;
		int y=(Toolkit.getDefaultToolkit().getScreenSize().height-this.getHeight())/2;
		this.setLocation(x, y);
		this.setTitle("坦克大战游戏1.0");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);		
	}
	
	 public static void main(String[] args) {
		new GameFrame();
	}
}
