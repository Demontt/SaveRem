package com.entity;

import imgs.Images;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.view.MainFrame;

public class Bullet {
	public static final int XSPEED = 15;
	public boolean live = true;
	public MyFish my;
	public int w = 20;
	public int h = 13;
	public int x ;
	public int y ;
	public int dirx;
	MainFrame mf = null;
	int fps = 0;
	public Bullet(MainFrame mf,MyFish my) {
		// TODO Auto-generated constructor stub
		this.mf = mf;
		this.my = my;
		this.dirx = my.dirx;
		this.y = my.y+(my.h/2);
		if(my.dirx == 6){
			this.x = my.x+my.w-1;
		}else {
			this.x = my.x-20;
		}
		
		
	}
	public void drawMySelf(Graphics g){
		if(live){
			if(dirx == 6){
				g.drawImage(Images.rbullet, x, y, w, h, mf);
			}else {
				g.drawImage(Images.lbullet, x, y, w, h, mf);
			}
		}
	}
	public void move(){
		if(live){
			if(dirx == 6){
				x += 40;
			}else{
				x -= 40;
			}
			if(x>854 || x<0){
				live = false;
			}
		}
	}
	public Rectangle getRectangle(){
		return new Rectangle(x, y, w, h);
	}
}
