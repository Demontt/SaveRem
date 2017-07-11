package com.entity;

import imgs.Images;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.view.MainFrame;

public class MyFish {
	public static final int XSPEED = 15;
	public static final int YSPEED = 15;
	public int w = 30;
	public int h = 20;
	public int x = 100;
	public int y = 100;
	public int count = 10;
	public int dirx = 6;//定义x方向
	public int[] fw = {30,50,80,120,150};//定义大小级别数组
	public int[] fh = {30,50,80,120,150};
	public int big = 0;//定义大小级别
	public MainFrame mf = null;
	public Bullet bullet = null;
	int fps = 0;//定义画第几幅图片 
	public int delay = 0;
	public MyFish(MainFrame mf) {
		this.mf = mf;
	}
	public void drawMySelf(Graphics g){
		if(fps>2){
			fps = 0;
		}
		if(count<30){
			w = fw[big];
			h = fh[big];
			big=0;
		}else if(count>=30 &&count<120){
			w = fw[big];
			h = fh[big];
			big=1;
		}else if(count>=120 &&count<350){
			w = fw[big];
			h = fh[big];
			big=2;
		}else if(count>=350){
			w = fw[big];
			h = fh[big];
			big=3;
		}else if(count>=500){
			w = fw[big];
			h = fh[big];
			big=4;
		}
		g.drawImage(Images.myFish[fps], x, y, w, h, mf);
		delay++;
		if(delay>1){
			fps++;
			delay = 0;
		}
		
	}
	public void move(int dir){
		if(x>854-w){
			x=854-w;
		}
		if(y>480-h){
			y=480-h;
		}
		if(x<0){
			x=0;
		}
		if(y<30){
			y=30;
		}
		switch(dir) {
		case 4:
			x -= XSPEED;
			dirx = 4;
			break;
		case 1:
			x -= XSPEED;
			y -= YSPEED;
			dirx = 4;
			break;
		case 2:
			y -= YSPEED;
			break;
		case 3:
			x += XSPEED;
			y -= YSPEED;
			dirx = 6;
			break;
		case 6:
			x += XSPEED;
			dirx = 6;
			break;
		case 9:
			x += XSPEED;
			y += YSPEED;
			dirx = 6;
			break;
		case 8:
			y += YSPEED;
			break;
		case 7:
			x -= XSPEED;
			y += YSPEED;
			dirx = 4;
			break;
		case 5:
			break;
		}
	}
	//得到主角头部的封装矩形
	public Rectangle getRectangle(){
		if(dirx == 6){
			return new Rectangle(x+w-10, y, 10, h);
		}else{
			return new Rectangle(x, y, 10, h);
		}
		
	}
	//发射子弹
	public void fire(){
		if(bullet == null){
			bullet = new Bullet(mf, this);
		}else {
			if(bullet.live == false){
				bullet.live = true;
				bullet.dirx = this.dirx;
				bullet.x = this.x;
				bullet.y = this.y+(this.h/2);
				if(this.dirx==6){
					bullet.x = this.x+this.w-1;
				}else {
					bullet.x = this.x-20;
				}
			}

		}
		
	}
}
