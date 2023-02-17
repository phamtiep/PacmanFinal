package GameModel;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import userinterface.GameFrame;

public class Pacman extends character {

	int currentFrame = 0;
	int countFrame = 7;
	int current_direction = 0;
	Image image = new ImageIcon("E:\\laptrinh\\java\\Pacman\\res\\animations.png").getImage();
	int i = 0;
	long startUndead = Long.MAX_VALUE;
	final int startY= GameFrame.SIZE_OF_BLOCK * (GameFrame.MAP_Y - 2);
	final int startX =GameFrame.SIZE_OF_BLOCK * (GameFrame.MAP_X - 2);
	boolean undeadMode;
	GameState gameState;
	Pacman(GameState gameState) {
		this.gameState = gameState;
		setXpx(startX);
		setYpx(startY);
		setHeight(GameFrame.SIZE_OF_BLOCK);
		setWidth(GameFrame.SIZE_OF_BLOCK);
		setSpeedX(0);
		setSpeedY(0);
		setSpeed(10);
		undeadMode = false;
	}

	@Override
	public void draw(Graphics2D g2) {
		
		// TODO Auto-generated method stub
		currentFrame = (currentFrame == countFrame - 1) ? 0 : currentFrame + 1;

		this.convSpeed();
		setXpx(getX() + getSpeedX());
		setYpx(getY() + getSpeedY());

		switch (getSpeedX()) {
		case GameFrame.SIZE_OF_BLOCK / 3:
			this.current_direction = 0;
			break;
		case -GameFrame.SIZE_OF_BLOCK / 3:
			this.current_direction = 180;
			break;
		default:
			switch (getSpeedY()) {
			case GameFrame.SIZE_OF_BLOCK / 3:
				this.current_direction = 90;
				break;
			case -GameFrame.SIZE_OF_BLOCK / 3:
				this.current_direction = 270;
				break;
			}
			break;
		}
//		
		g2.rotate(Math.toRadians(this.current_direction), this.getWidth() / 2 + getX(), this.getHeight() / 2 + getY());
//		

		g2.drawImage(image, getX(), getY(), getX() + this.getWidth(), getY() + this.getHeight(), currentFrame * 20, 0,
				currentFrame * 20 + 20, 20, null);
		g2.rotate(Math.toRadians(0), this.getWidth() / 2 + getX(), this.getHeight() / 2 + getY());
		eat();
	}

	public void convSpeed() {
		if (!this.isValidMove(getX() + getSpeedX(), getY() + getSpeedY())) {
			setSpeedX(0);
			setSpeedY(0);
		}
	}

	public void updateUndead() {
		if (undeadMode) {

			if (System.nanoTime() - startUndead > 5e9) {
				undeadMode = false;
				
			}
		
		}
	}
	public void updateStartUndead() 
	{
		startUndead = System.nanoTime();
	}
	

	public void eat() {
		if (GameState.map[getMapY()][getMapX()] == 2) {
			GameState.map[getMapY()][getMapX()] = 0;
			gameState.Score++;
		}
	}

}
