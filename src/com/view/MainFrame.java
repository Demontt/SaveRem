package com.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import com.entity.BackGround;
import com.entity.GoldFish;
import com.entity.HuJing;
import com.entity.MyFish;
import com.entity.OtherFish;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MainFrame extends Frame {
	ClassLoader loader=MainFrame.class.getClassLoader();
	InputStream is=loader.getResourceAsStream("music/bg.mp3");
	BufferedInputStream buffer = new BufferedInputStream(is);
	Player player ;
	private boolean bL=false, bU=false, bR=false, bD = false;
	int dir = 5;
	int style1 = 0;
	int style = 0;
	int style2 = 0;

	int count = 0 ;
	int count1 = 0;
	int count2 = 0;
	//白鲸出来的间隔
	int delay = 100000;
	private Image bufferImage;
	private BackGround bg;
	private MyFish myFish;
	//private OtherFish otherFish;
	public List<OtherFish> otherFishes = new ArrayList<OtherFish>();
	public List<GoldFish> goldFishes = new ArrayList<GoldFish>();
	public List<HuJing> huJings = new ArrayList<HuJing>();
	public HuJing hujing;
	public String state="being";
	
	public static void main(String[] args) {
		new MainFrame();
	}
	
	public MainFrame(){
		bg = new BackGround(this);
		
		myFish = new MyFish(this);
		try {
			player = new Player(buffer);
		} catch (JavaLayerException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		hujing = new HuJing(this, 100, 50, myFish);
		
		Random r = new Random();
		//产生其它怠惰
		for(int i=0;i<15;i++){
			int ron = r.nextInt(4);
			if(i<15){
				if(ron == 1){
					otherFishes.add(new OtherFish(this,20,20,myFish));
				}else if(ron == 0){
					otherFishes.add(new OtherFish(this,40,40,myFish));
				}else if(ron == 2){
					otherFishes.add(new OtherFish(this,60,60,myFish));
				}else if(ron == 3){
					otherFishes.add(new OtherFish(this,90,90,myFish));
				}else if(ron == 4){
					otherFishes.add(new OtherFish(this,140,140,myFish));
				}
			}
			otherFishes.add(new OtherFish(this,myFish.w-10,myFish.h-4,myFish));
		}
		//产生蓝色怠惰
		for(int i=0;i<8;i++){
			int ron = r.nextInt(4);
			if(i<8){
				if(ron == 1){
					goldFishes.add(new GoldFish(this,6,4,myFish));
				}else if(ron == 0){
					goldFishes.add(new GoldFish(this,15,10,myFish));
				}else if(ron == 2){
					goldFishes.add(new GoldFish(this,20,15,myFish));
				}else if(ron == 3){
					goldFishes.add(new GoldFish(this,60,40,myFish));
				}else if(ron == 4){
					goldFishes.add(new GoldFish(this,280,200,myFish));
				}
			}
			goldFishes.add(new GoldFish(this,myFish.w-10,myFish.h-4,myFish));
			
		}
		setSize(860, 490);
		setLocation((int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth()-854)/2), (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight()-640)/2));
		setTitle("拯救雷姆之旅 by Demon");
		setVisible(true);
		setResizable(false);
	
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int key = e.getKeyCode();
				switch(key) {
				case KeyEvent.VK_LEFT :
					bL = true;
					break;
				case KeyEvent.VK_UP :
					bU = true;
					break;
				case KeyEvent.VK_RIGHT :
					bR = true;
					break;
				case KeyEvent.VK_DOWN :
					bD = true;
					break;
				case KeyEvent.VK_F:
					myFish.fire();
					break;
				}
				if(state.equals("victory"))
				{
					player.close();
					dispose();
					new Victory();
				}
				else if(state.equals("fail"))
				{
					player.close();
					dispose();
					new Fail();
				}
				locateDirection();
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				int key = e.getKeyCode();
				switch(key) {
				case KeyEvent.VK_CONTROL:
					break;
				case KeyEvent.VK_LEFT :
					bL = false;
					break;
				case KeyEvent.VK_UP :
					bU = false;
					break;
				case KeyEvent.VK_RIGHT :
					bR = false;
					break;
				case KeyEvent.VK_DOWN :
					bD = false;
					break;
				}
				if(state.equals("victory"))
				{
					player.close();
					dispose();
					new Victory();
				}
				else if(state.equals("fail"))
				{
					player.close();
					dispose();
					new Fail();
				}
				locateDirection();
			}
		});
		
		new Thread(new DrawService()).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
					try {
						player.play();
					} catch (JavaLayerException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
			}
		}).start();
		
		 //milliseconds
		  ActionListener taskPerformer = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		          hujing.live = true;
		      }
		  };
		  new Timer(delay, taskPerformer).start();
	}
	@Override
	public void paint(Graphics g) {
		bg.drawMySelf(g);
		myFish.drawMySelf(g);
		myFish.move(dir);
		if(hujing.live == true){
			hujing.drawMySelf(g);
			hujing.move();
		}
		//随机产生怠惰移动方式
		for(int i = 0;i<otherFishes.size();i++){

			if(i%2 == 0){
				count++;
				otherFishes.get(i).drawMySelf(g);
				if(count > 800){
				    style1 = new Random().nextInt(4);
					count = 0;
				}
				otherFishes.get(i).move(style1);
				otherFishes.get(i).beichi(myFish);
				if(hujing.live){
					otherFishes.get(i).beiHujingChi(hujing);
				}
				otherFishes.get(i).aim(myFish);
			}else if(i%3 == 0){
				otherFishes.get(i).drawMySelf(g);
				count1++;
				if(count1 > 1000){
					style = new Random().nextInt(3);
					count1 = 0;
				}
				otherFishes.get(i).move(style);
				otherFishes.get(i).beichi(myFish);
				otherFishes.get(i).aim(myFish);
				if(hujing.live){
					otherFishes.get(i).beiHujingChi(hujing);
				}
			}else {
				count2++;
				otherFishes.get(i).drawMySelf(g);
				if(count2 > 200){
					style2 = new Random().nextInt(3);
					count2 = 0;
				}
				otherFishes.get(i).move(style2);
				otherFishes.get(i).beichi(myFish);
				otherFishes.get(i).aim(myFish);
				if(hujing.live){
					otherFishes.get(i).beiHujingChi(hujing);
				}
			}
					
		}
		//随机蓝色怠惰游动方式
		for(int i = 0;i<goldFishes.size();i++){
			if(i%2 == 0){
				count++;
				goldFishes.get(i).drawMySelf(g);
				if(count > 800){
					style1 = new Random().nextInt(3);
					count = 0;
				}
				goldFishes.get(i).move(style1);
				goldFishes.get(i).beichi(myFish);
				goldFishes.get(i).aim(myFish);
				if(hujing.live){
					otherFishes.get(i).beiHujingChi(hujing);
				}
			}else if(i%3 == 0){
				goldFishes.get(i).drawMySelf(g);
				count1++;
				if(count1 > 1000){
					style = new Random().nextInt(3);
					count1 = 0;
				}
				goldFishes.get(i).move(style);
				goldFishes.get(i).beichi(myFish);
				goldFishes.get(i).aim(myFish);
				if(hujing.live){
					otherFishes.get(i).beiHujingChi(hujing);
				}
			}else {
				count2++;
				goldFishes.get(i).drawMySelf(g);
				if(count2 > 200){
					style2 = new Random().nextInt(3);
					count2 = 0;
				}
				goldFishes.get(i).move(style2);
				goldFishes.get(i).beichi(myFish);
				goldFishes.get(i).aim(myFish);
				if(hujing.live){
					otherFishes.get(i).beiHujingChi(hujing);
				}
			}
					
		}
		if(myFish.bullet != null){
			myFish.bullet.drawMySelf(g);
			myFish.bullet.move();
		}
		g.setColor(Color.red);
		Font f = new Font("微软雅黑", Font.BOLD, 20);
		g.setFont(f);
		g.drawString("Maple的得分是: "+myFish.count+"    等级："+myFish.big, 10, 50);
		
		if(myFish.count>=520)
		{
			//游戏胜利
			state="victory";
		}
		else if(myFish.count<-86)
		{
			//游戏失败
			state="fail";
		}
		
	}

	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		if(bufferImage == null){
			bufferImage = createImage(854, 480);
		}
		Graphics imagePen = bufferImage.getGraphics();
		paint(imagePen);
		g.drawImage(bufferImage, 0, 0, 854, 480, this);
	}

	//监听键盘按键获得主角方向
	public void locateDirection() {
		if(bL && !bU && !bR && !bD) dir = 4;
		else if(bL && bU && !bR && !bD) dir = 1;
		else if(!bL && bU && !bR && !bD) dir = 2;
		else if(!bL && bU && bR && !bD) dir = 3;
		else if(!bL && !bU && bR && !bD) dir = 6;
		else if(!bL && !bU && bR && bD) dir = 9;
		else if(!bL && !bU && !bR && bD) dir = 8;
		else if(bL && !bU && !bR && bD) dir = 7;
		else if(!bL && !bU && !bR && !bD) dir = 5;
		
	}
	//线程不断重画
	public class DrawService implements Runnable{
		public void run() {
			// TODO Auto-generated method stub
			try{
				while(true){
					repaint();
					Thread.sleep(50);
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public  void play(String string) {
		try {
			ClassLoader loader=MainFrame.class.getClassLoader();
			InputStream is=loader.getResourceAsStream(string);
			BufferedInputStream buffer = new BufferedInputStream(is);
			Player player = new Player(buffer);
			player.play();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
