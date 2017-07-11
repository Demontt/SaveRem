package com.view;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.swing.JFrame;

import imgs.Images;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Fail  extends JFrame{
	
	ClassLoader loader=MainFrame.class.getClassLoader();
	InputStream is=loader.getResourceAsStream("music/fail.mp3");
	BufferedInputStream buffer = new BufferedInputStream(is);
	Player player ;

	public static void main(String[] args) {
		new Fail();
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(Images.fail, 0, 0, 800, 600, this);
	}
	public Fail()
	{
		setTitle("������ķ by Demon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation((int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth()-854)/2), (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight()-640)/2));
		setSize(800, 600);
		setVisible(true);
		try {
			player = new Player(buffer);
		} catch (JavaLayerException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_ENTER){
					player.close();
					dispose();
					new  FirstFrame();
				}
			}
			private void addWindowListener(WindowAdapter windowAdapter) {
				// TODO �Զ����ɵķ������
				
			}
		});	
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
					try {
						player.play();
					} catch (JavaLayerException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
			}
		}).start();
	}

}