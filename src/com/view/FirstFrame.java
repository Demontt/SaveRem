package com.view;

import imgs.Images;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class FirstFrame extends JFrame  {
	public int sy[] ={120,190,245};				//方框高度
	public int selected = 0;
	public static void main(String[] args) {
		new FirstFrame();
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(Images.welcome, 0, 0, 700, 640, this);
		g.drawImage(Images.selected, 50, sy[selected], 230, 65, this);	
	}

	public FirstFrame() {
		setTitle("拯救蕾姆");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation((int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth()-854)/2), (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight()-640)/2));
		setSize(700, 640);
		setVisible(true);
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_DOWN){
					if(selected>=2){
						selected = 2;
					}else {
						selected++;
					}
					repaint();
				}else if(key == KeyEvent.VK_UP){
					if(selected<=0){
						selected = 0;
					}else {
						selected--;
					}
					repaint();
				}else if(key == KeyEvent.VK_ENTER){
					switch (selected) {
					case 0:
					{
						dispose();
						new MainFrame();
						break;
					}	
					case 1:
					{
						dispose();
						new StoryFrame();
						break;
					}
					case 2:
					{
						dispose();
						System.exit(0);
						break;
					}	
					
					default:
						break;
					}
				}
			}

			private void addWindowListener(WindowAdapter windowAdapter) {
				// TODO 自动生成的方法存根
				
			}
			
		});	
	}
}
