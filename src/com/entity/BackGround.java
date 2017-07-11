package com.entity;

import imgs.Images;

import java.awt.Graphics;

import com.view.MainFrame;

public class BackGround {
	MainFrame mf = null;
	public BackGround(MainFrame mf) 
	{
		this.mf = mf;
	}
	public void drawMySelf(Graphics g){
		g.drawImage(Images.back, 0, 0, 860,490, mf);
		g.drawImage(Images.rom, 0, 300, 200, 200, mf);
		g.drawImage(Images.lam, 660, 300, 200, 200, mf);
	}
}
