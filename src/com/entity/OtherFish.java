package com.entity;

import imgs.Images;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.view.MainFrame;

public class OtherFish {
	Random r = new Random();//随即数
	private int w ;//宽
	private int h ;//高
	private int x = r.nextInt(800);//随即坐标
	private int y = r.nextInt(400);
	private int diry = 8;//y方向
	private int dirx = 6;
	MainFrame mf = null;
	MyFish my = null;
	int fps = 0;
	public OtherFish(MainFrame mf,int w,int h,MyFish my) {
		// TODO Auto-generated constructor stub
		this.w = w;
		this.h = h;
		this.mf = mf;
		this.my = my;
	}
	public void drawMySelf(Graphics g){

		if(fps>2){
			fps = 0;
		}
		g.drawImage(Images.otherFish[fps], x, y, w, h, mf);
		fps++;
	}
	public void move(int s){
		switch (s) {
		case 0:
			{
				if(diry == 8){
					y+=2;
					if(y>(900)){
						diry = 2;
					}
				}else if(diry == 2){
					y-=4;
					if(y<-180){
						diry = 8;
					}
				}
				if(dirx == 6){
					x+=4;
					if(x>1000){
						dirx = 4;
					}
				}else if(dirx == 4){
					x-=6;
					if(x<-100){
						dirx = 6;
					}
				}	
			}
			break;
			case 1:
				{
					dirx = 4;
					if(x<-w){
						x = 854;
					}
					x-=2;
				}
			break;	
			case 2:
			{
				dirx = 6;
				if(x>854){
					x = 0;
				}
				x+=2;
			}
		break;
		default:
			break;
		}

	}
	
	public void beichi(MyFish my){
		if(this.getRectangle().intersects(my.getRectangle()) ){
			if( my.w>this.w){
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						mf.play("music/game_eat.mp3");
					}
				}).start();
				
				mf.otherFishes.remove(this);
				my.count+=8;
				mf.otherFishes.add(returnRom());
			}else {
				my.count-=3;
			}
			
		}
	}
	//产生一个随机位置大小的鱼
	public OtherFish returnRom(){
		int ron = r.nextInt(4);
		if(ron == 1){
			return new OtherFish(mf,40,40,my);
		}else if(ron == 0){
			return new OtherFish(mf,20,20,my);
		}else if(ron == 2){
			return new OtherFish(mf,60,60,my);
		}else if(ron == 3){
			return new OtherFish(mf,90,90,my);
		}else if(ron == 4){
			return new OtherFish(mf,130,130,my);
		}
		return null;
	}
	//被大虎鲸吃
	public void beiHujingChi(HuJing hj){
		if(this.getRectangle().intersects(hj.getRectangle())){
			mf.otherFishes.remove(this);
			mf.otherFishes.add(returnRom());
		}
	}
	//被主角发出的子弹打中
	public void aim(MyFish my){
		if(my.bullet != null){
			if(this.getRectangle().intersects(my.bullet.getRectangle())){
				mf.otherFishes.remove(this);
				my.bullet.live = false;
				mf.otherFishes.add(returnRom());
			}
		}
	}
	public Rectangle getRectangle(){
		return new Rectangle(x, y, w, h);
	}
}
