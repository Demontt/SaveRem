package com.view;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.swing.JFrame;

import com.view.MainFrame.DrawService;

import imgs.Images;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class StoryFrame  extends JFrame{
	ClassLoader loader=MainFrame.class.getClassLoader();
	InputStream is=loader.getResourceAsStream("music/ss.mp3");
	BufferedInputStream buffer = new BufferedInputStream(is);
	Player player ;
	public int sy[] ={540};				//方框高度

	public static void main(String[] args) {
		new StoryFrame();
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(Images.story, 0, 0, 580, 700, this);
		g.drawImage(Images.selected, 280, sy[0], 250, 65, this);	
	}
	
	public  StoryFrame() {
		
		setTitle("故事前提 by Maple");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation((int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth()-854)/2), (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight()-640)/2));
		setSize(580,700);
		setVisible(true);
		try {
			player = new Player(buffer);
		} catch (JavaLayerException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_ENTER){
					player.close();
					dispose();
					new MainFrame();
				}	
			}
			private void addWindowListener(WindowAdapter windowAdapter) {
				// TODO 自动生成的方法存根
			}
		});	
		
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
		
	}

	

}
