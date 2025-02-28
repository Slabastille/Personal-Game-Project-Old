package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable{
	
	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale;
	
	final int maxScreenCol = 18;
	final int maxScreenRow = 14;
	
	final int screenWidth  = tileSize * maxScreenCol;
	final int screenHeight = tileSize * maxScreenRow;
	
	int FPS = 60; 
	
	KeyHandler keyHandler = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this, keyHandler);
	
	int playerX 	= 100;
	int playerY 	= 100;
	int playerSpeed = 4;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
//	public void run() {
//		// TODO Auto-generated method stub
//		double drawInterval = 1000000000/FPS;
//		double nextDrawTime = System.nanoTime() + drawInterval;
//		
//		while (gameThread != null) {
//			
//			update();
//			
//			repaint();	
//			
//			
//			try {
//				
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime = remainingTime/1000000;
//				if(remainingTime < 0) {
//					remainingTime = 0;
//				}
//				Thread.sleep((long) remainingTime);
//				
//				nextDrawTime += drawInterval; 
//			}
//			catch(InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		
//	}
	
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		long timer =0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			
			timer += (currentTime -lastTime);
			
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta --;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
				System.out.println("FPS:" + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
		
	}
	
	public void update() {
		player.update();
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		player.draw(g2);
		
		g2.dispose();
	}
	
	
	
	
	
	
	
	
	
	
}
