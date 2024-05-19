package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	
	GamePanel  gp;
	KeyHandler keyHandler;

	public Player(GamePanel gp, KeyHandler keyh){
		this.gp   = gp;
		this.keyHandler = keyh;
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		x = 100;
		y = 100;
		
		speed = 7;
		direction = "left";
	}
	public void getPlayerImage(){
		try {
			left0 = ImageIO.read(getClass().getResourceAsStream("/player/mc_wl_0.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/mc_wl_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/mc_wl_2.png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/player/mc_wl_3.png"));
			left4 = ImageIO.read(getClass().getResourceAsStream("/player/mc_wl_4.png"));
			left5 = ImageIO.read(getClass().getResourceAsStream("/player/mc_wl_5.png"));
			left6 = ImageIO.read(getClass().getResourceAsStream("/player/mc_wl_6.png"));
			
			right0 = ImageIO.read(getClass().getResourceAsStream("/player/mc_wr_0.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/mc_wr_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/mc_wr_2.png"));
			right3 = ImageIO.read(getClass().getResourceAsStream("/player/mc_wr_3.png"));
			right4 = ImageIO.read(getClass().getResourceAsStream("/player/mc_wr_4.png"));
			right5 = ImageIO.read(getClass().getResourceAsStream("/player/mc_wr_5.png"));
			right6 = ImageIO.read(getClass().getResourceAsStream("/player/mc_wr_6.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void update() {
		if(keyHandler.upPressed) {
			direction = "up";
			y -= speed;
		}
		else if(keyHandler.downPressed) {
			direction = "down";
			y += speed;
		}
		else if(keyHandler.leftPressed) {
			direction = "left";
			x -= speed;
		}
		else if(keyHandler.rightPressed) {
			direction = "right";
			x += speed;
		}
		
		spriteCounter++;
		if(spriteCounter > 5) {
			for(int x =0; x<=6; x++) {
				if(spriteNum == 6) {
					spriteNum = 0;
				}
				else if(spriteNum == x) {
					spriteNum = x+1;
				}
				
			}
		}
		
	}
	
	public void draw(Graphics2D g2) {
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		
		switch(direction) {
		case "left":
			image = left0;
			break;
		case "right":
			image = right0;
			break;
		}
		g2.drawImage(image,  x,  y,  gp.tileSize*3,gp.tileSize*3, null);
	}
}
