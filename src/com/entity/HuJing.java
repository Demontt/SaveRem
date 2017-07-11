package com.entity;

import imgs.Images;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import com.view.MainFrame;

public class HuJing {
	Random r = new Random();
	private int w ;
	private int h ;
	private int x = 0;
	private int y = r.nextInt(200);
	private int diry = 8;
	private int dirx = 6;
	MainFrame mf = null;
	MyFish my = null;
	int fps = 0;
	int count = 0;
	public Image[] img = Images.hujingImage();
	public boolean live = false;
	public HuJing(MainFrame mf,int w,int h,MyFish my) {
		// TODO Auto-generated constructor stub
		this.w = w;
		this.h = h;
		this.mf = mf;
		this.my = my;
	}
	public void drawMySelf(Graphics g){
		if(live){
			if(fps>2){
				fps = 0;
			}
			g.drawImage(img[fps], x, y, 256, 100, mf);
			count++;
			if(count>4){
				fps ++;
				count = 0;
			}
		}	
	}
	public void move(){
		if(live){
			x+=5;
			y+=2;
			if(x>854){
				live = false;
				x = 0;
				y = r.nextInt(200);
			}
		}
		
	}
	
	
	public void aim(MyFish my){
		if(my.bullet != null){
			if(this.getRectangle().intersects(my.bullet.getRectangle())){
				mf.goldFishes.remove(this);
				my.bullet.live = false;
			}
		}
		
	}
	public Rectangle getRectangle(){
		return new Rectangle(x, y, 256, 100);
	}
}
