package com.entity;

import imgs.Images;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.view.MainFrame;
public class GoldFish {
	Random r = new Random();
	private int w ;
	private int h ;
	private int x = r.nextInt(800);
	private int y = r.nextInt(400);
	private int diry = 8;
	private int dirx = 6;
	MainFrame mf = null;
	MyFish my = null;
	int fps = 0;
	int count = 0;
	public boolean close = false;
	public GoldFish(MainFrame mf,int w,int h,MyFish my) {
		this.w = w;
		this.h = h;
		this.mf = mf;
		this.my = my;
	}
	public void drawMySelf(Graphics g){

		if(fps>4){
			fps = 0;
		}
		g.drawImage(Images.goldFish[fps], x, y, w, h, mf);
		
		fps++;
	}
	public void move(int s){
		if(Math.abs(my.x-x)<100 && Math.abs(my.y-y)<60 && w<my.w){
			close = true;
			if(my.dirx == 6 && dirx == 4 && (my.x-x)<0){
				dirx = 6;
			}else if(my.dirx == 4 && dirx == 6 && (my.x-x)>0){
				dirx = 4;
			}
			else if(my.dirx == 4 && dirx == 4 && (my.x-x)>0){
				dirx = 4;
			}else if(my.dirx == 4 && dirx == 4 && (my.x-x)<0){
				dirx = 6;
			}
			else if(my.dirx == 6 && dirx == 6 && (my.x-x)>0){
				dirx = 4;
			}else if(my.dirx == 6 && dirx == 6 && (my.x-x)<0){
				dirx = 6;
			}
		}else {
			count++;
			if(count>50){
				close = false;
				count = 0;
			}
		}
		if(!close){
			switch (s) {
			case 0:
				{
					if(diry == 8){
						y+=1;
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
						x+=2;
						if(x>1000){
							dirx = 4;
						}
					}else if(dirx == 4){
						x-=8;
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
		}else {
			if(dirx == 6){
				dirx = 6;
				if(x>854){
					x = 0;
				}
				x+=11;
			}else {
				if(x<-w){
					x = 854;
				}
				x-=11;
			}	
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
				mf.goldFishes.remove(this);
				my.count+=10;
				int ron = r.nextInt(4);
				if(ron == 1){
					mf.goldFishes.add(new GoldFish(mf,35,35,my));
				}else if(ron == 0){
					mf.goldFishes.add(new GoldFish(mf,15,15,my));
				}else if(ron == 2){
					mf.goldFishes.add(new GoldFish(mf,55,55,my));
				}else if(ron == 3){
					mf.goldFishes.add(new GoldFish(mf,85,85,my));
				}else if(ron == 4){
					mf.goldFishes.add(new GoldFish(mf,125,125,my));
				}
			}else {
				my.count-=5;
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
		return new Rectangle(x, y, w, h);
	}
	
}
