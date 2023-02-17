package GameModel;

import java.awt.Graphics2D;
import java.awt.Image;

import userinterface.GameFrame;

public abstract class character {

	protected int speedX;
	protected int speedY;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int direction;
	int speed;
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public int getX() {
		return x;
	}

	public void setXpx(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setYpx(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isValidMove(int x, int y) {
		return (x >= 0) && (y >= 0) && (x < GameFrame.SCREEN_WIDTH) && (y < GameFrame.SCREEN_HEIGHT)
				&& ((GameState.map[(int) ((double) y / GameFrame.SIZE_OF_BLOCK + 0.9999)][(int) ((double) x
						/ GameFrame.SIZE_OF_BLOCK + 0.99999)] != 1)

						&& (GameState.map[(int) ((double) y / GameFrame.SIZE_OF_BLOCK + 0.99999)][(int) ((double) x
								/ GameFrame.SIZE_OF_BLOCK)] != 1)
						&& (GameState.map[(int) ((double) y / GameFrame.SIZE_OF_BLOCK)][(int) ((double) x
								/ GameFrame.SIZE_OF_BLOCK + 0.99999)] != 1)
						&& (GameState.map[(int) ((double) y / GameFrame.SIZE_OF_BLOCK)][(int) ((double) x
								/ GameFrame.SIZE_OF_BLOCK)] != 1));
	}

	public abstract void draw(Graphics2D g2);

	public int getMapX() {
		return x / GameFrame.SIZE_OF_BLOCK;
	}

	public int getMapY() {
		return y / GameFrame.SIZE_OF_BLOCK;
	}
}
