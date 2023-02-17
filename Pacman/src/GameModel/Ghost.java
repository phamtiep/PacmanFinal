package GameModel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import java.util.Random;

import userinterface.GameFrame;

public class Ghost extends character {
	Image face = null;

	double range = 0;
	int posOfFaceX;
	int posOfFaceY;
	Pacman pacman = null;

	public Ghost(Image face, int speed, int range, Pacman pacman, String name) {
		super();
		this.face = face;
		this.speed = speed;
		this.range = range * GameFrame.SIZE_OF_BLOCK;
		this.pacman = pacman;
		this.width = GameFrame.SIZE_OF_BLOCK;
		this.height = GameFrame.SIZE_OF_BLOCK;

		switch (name) {

		case ("Blinky"): {
			posOfFaceX = 0;
			posOfFaceY = 0;
			setXpx(7 * GameFrame.SIZE_OF_BLOCK);
			setYpx(10 * GameFrame.SIZE_OF_BLOCK);
			break;
		}
		case ("Pinky"): {
			posOfFaceX = 0;
			posOfFaceY = 120;
			setXpx(2 * GameFrame.SIZE_OF_BLOCK);
			setYpx(21 * GameFrame.SIZE_OF_BLOCK);
			break;
		}
		case ("Inky"): {
			posOfFaceX = 175;
			posOfFaceY = 0;
			setXpx(19 * GameFrame.SIZE_OF_BLOCK);
			setYpx(10 * GameFrame.SIZE_OF_BLOCK);
			break;
		}
		case ("Clyde"): {
			posOfFaceX = 175;
			posOfFaceY = 120;
			setXpx(2*GameFrame.SIZE_OF_BLOCK);
			setYpx((GameFrame.MAP_X - 2) * GameFrame.SIZE_OF_BLOCK);
			break;
		}
		}
	}

	public int countValid() {
		int count = 0;
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if ((i + j == 1 || i + j == -1) && (i != -speedX || j != -speedY)) {
					count += isValidMove(x + i * GameFrame.SIZE_OF_BLOCK, y + j * GameFrame.SIZE_OF_BLOCK) ? 1 : 0;

				}
			}
		}
		return count;
	}

	public double distancePac(int x, int y) {
		return Math.sqrt((x - pacman.getX()) * (x - pacman.getX()) + (y - pacman.getY()) * (y - pacman.getY()));
	}

	public void randomMove() {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if ((i + j == 1 || i + j == -1) && (i != -speedX || j != -speedY || countValid() == 0)) {
					if (isValidMove(x + i * GameFrame.SIZE_OF_BLOCK, y + j * GameFrame.SIZE_OF_BLOCK)) {
						setSpeedX(i);
						setSpeedY(j);
						return;

					}
				}
			}
		}
	}

	public void bestMovetoPac() {
		double min = 9999;
		int speedXtemp = 1;
		int speedYtemp = 1;
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if ((i + j == 1 || i + j == -1) && (i + speedX != 0 || j + speedY != 0 || countValid() == 0)) {

					if (isValidMove(x + i * GameFrame.SIZE_OF_BLOCK, y + j * GameFrame.SIZE_OF_BLOCK)) {
						if (min > distancePac(getX() + i * speed, getY() + j * speed)) {
							speedXtemp = i;
							speedYtemp = j;
							min = distancePac(getX() + i * speed, getY() + j * speed);
						}
					}
				}
			}
		}
		speedX = speedXtemp;
		speedY = speedYtemp;
	}

	public void moveProcess() {
		if (range < distancePac(x, y)) {
			randomMove();
		} else {
			bestMovetoPac();

		}
	}

	@Override
	public void draw(Graphics2D g2) {

		if (x % GameFrame.SIZE_OF_BLOCK == 0 && y % GameFrame.SIZE_OF_BLOCK == 0)
			if ((countValid() > 1) || (speedX == 0 && speedY == 0)
					|| !isValidMove(x + speedX * speed, y + speedY * speed)) {
				this.moveProcess();
			}
		// this.setXpx(x+speedX*speed);
		x += speedX * speed;
		// this.setYpx(y+speedY*speed);
		y += speedY * speed;
		g2.drawImage(face, x, y, x + GameFrame.SIZE_OF_BLOCK, y + GameFrame.SIZE_OF_BLOCK, posOfFaceX, posOfFaceY,
				posOfFaceX + 125, posOfFaceY + 116, null);
		g2.setColor(Color.CYAN);
		g2.drawOval(x - (int) range + this.width / 2, y - (int) range + this.width / 2, (int) range * 2,
				(int) range * 2);
	}
	public boolean checkCollisionPac() 
	{
		if(pacman.undeadMode) return false;
		return distancePac(this.x,this.y)<10;
		
	}

}
