package GameModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;

import javax.swing.ImageIcon;

import userinterface.GameFrame;
import userinterface.GamePanel;

public class GameState {

	static int[][] map = new int[GameFrame.MAP_Y][GameFrame.MAP_X];

	Pacman pacman = null;
	Ghost Pinky = null;
	Ghost Clyde = null;
	Ghost Blinky = null;
	Ghost Inky = null;
	int gameStage;
	final int PAUSE = 1;
	final int LOSE = 2;
	final int WIN = 3;
	final int IN_GAME = 4;
	int lifes;
	BufferedImage bufferedImage = null;
	GamePanel gamePanel = null;
	public static int Score = 0;
	String urlToNewMap = "E:\\laptrinh\\java\\Pacman\\res\\map.txt";

	public GameState(GamePanel gamePanel) {

		this.gamePanel = gamePanel;
		// TODO Auto-generated constructor stub
		bufferedImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		gameStage = IN_GAME;
		getMap(urlToNewMap);
		pacman = new Pacman();
		Image image = new ImageIcon("E:\\laptrinh\\java\\Pacman\\res\\ghost.png").getImage();

		lifes = 3;
		Pinky = new Ghost(image, 6, 10, pacman, "Pinky");
		Clyde = new Ghost(image, 5, 0, pacman, "Clyde");
		Inky = new Ghost(image, 3, 3, pacman, "Inky");
		Blinky = new Ghost(image, 5, 200, pacman, "Blinky");

	}

	public void draw() {
		Graphics2D g2 = (Graphics2D) bufferedImage.getGraphics();
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_WIDTH);

		if (g2 != null) {
			for (int y = 0; y < GameFrame.MAP_Y; y++) {
				for (int x = 0; x < GameFrame.MAP_X; x++) {
					if (map[y][x] == 1) {
						g2.setColor(Color.MAGENTA);
						g2.fillRect(x * GameFrame.SIZE_OF_BLOCK, y * GameFrame.SIZE_OF_BLOCK, GameFrame.SIZE_OF_BLOCK,
								GameFrame.SIZE_OF_BLOCK);
						g2.setColor(Color.black);
						if (x > 0)
							if (map[y][x - 1] == 1)
								g2.fillRect(x * GameFrame.SIZE_OF_BLOCK,
										y * GameFrame.SIZE_OF_BLOCK + GameFrame.THICKNESS_WALL,
										GameFrame.NULL_SPACE_WIDTH + GameFrame.THICKNESS_WALL,
										GameFrame.NULL_SPACE_WIDTH);
						if (x + 1 < GameFrame.MAP_X)
							if (map[y][x + 1] == 1)
								g2.fillRect(x * GameFrame.SIZE_OF_BLOCK + GameFrame.THICKNESS_WALL,
										y * GameFrame.SIZE_OF_BLOCK + GameFrame.THICKNESS_WALL,
										GameFrame.NULL_SPACE_WIDTH + GameFrame.THICKNESS_WALL,
										GameFrame.NULL_SPACE_WIDTH);
						if (y > 0)
							if (map[y - 1][x] == 1)
								g2.fillRect(x * GameFrame.SIZE_OF_BLOCK + GameFrame.THICKNESS_WALL,
										y * GameFrame.SIZE_OF_BLOCK, GameFrame.NULL_SPACE_WIDTH,
										GameFrame.NULL_SPACE_WIDTH + GameFrame.THICKNESS_WALL);
						if (y + 1 < GameFrame.MAP_Y)
							if (map[y + 1][x] == 1)
								g2.fillRect(x * GameFrame.SIZE_OF_BLOCK + GameFrame.THICKNESS_WALL,
										y * GameFrame.SIZE_OF_BLOCK + GameFrame.THICKNESS_WALL,
										GameFrame.NULL_SPACE_WIDTH,
										GameFrame.NULL_SPACE_WIDTH + GameFrame.THICKNESS_WALL);
					}
				}
			}
		}
		switch (gameStage) {
		case (IN_GAME): {
			drawInGame(g2);
			break;
		}
		case (PAUSE): {
			drawPauseStage(g2);
			break;
		}
		case (LOSE): {
			drawLoseStage(g2);
			break;
		}
		}
	}

	public BufferedImage getBufferedImage() {
		// TODO Auto-generated method stub
		return this.bufferedImage;
	}

	public void setPressedButton(int code) {
		switch (gameStage) {

		case (IN_GAME): {
			switch (code) {
			case KeyEvent.VK_RIGHT:
				if (!pacman.isValidMove(pacman.getX() + GameFrame.SIZE_OF_BLOCK / 4, pacman.getY())) {
					break;
				}
				pacman.setSpeedX(pacman.speed);
				pacman.setSpeedY(0);

				break;
			case KeyEvent.VK_UP:
				if (!pacman.isValidMove(pacman.getX(), pacman.getY() - GameFrame.SIZE_OF_BLOCK / 4)) {
					break;
				}

				pacman.setSpeedX(0);
				pacman.setSpeedY(-pacman.speed);

				break;
			case KeyEvent.VK_DOWN:
				if (!pacman.isValidMove(pacman.getX(), pacman.getY() + GameFrame.SIZE_OF_BLOCK / 4)) {
					break;
				}
				pacman.setSpeedX(0);
				pacman.setSpeedY(pacman.speed);
				break;
			case KeyEvent.VK_LEFT:
				if (!pacman.isValidMove(pacman.getX() - GameFrame.SIZE_OF_BLOCK / 4, pacman.getY())) {
					break;
				}
				pacman.setSpeedX(-pacman.speed);
				pacman.setSpeedY(0);
				break;
			default:
				break;
			}
			break;
		}
		case (PAUSE): {
			gameStage = IN_GAME;
			pacman.updateStartUndead();
			break;

		}
		case (LOSE): {
			gameStage = IN_GAME;
			pacman.updateStartUndead();
			pacman.undeadMode = true;
			break;
		}
		}
	}

	public void drawFood(Graphics g2) {
		g2.setColor(Color.ORANGE);
		for (int y = 0; y < GameFrame.MAP_Y; y++) {
			for (int x = 0; x < GameFrame.MAP_X; x++) {
				if (map[y][x] == 2)
					g2.fillRect(x * GameFrame.SIZE_OF_BLOCK + GameFrame.SIZE_OF_BLOCK / 3,
							y * GameFrame.SIZE_OF_BLOCK + GameFrame.SIZE_OF_BLOCK / 3, GameFrame.SIZE_OF_BLOCK / 3,
							GameFrame.SIZE_OF_BLOCK / 3);
			}
		}
	}

	public void drawScore(Graphics g2) {
		Font a = new Font("XYZ", Font.TRUETYPE_FONT, 30);

		g2.setFont(a);
		g2.drawString("SCORE : " + Score, GameFrame.SIZE_OF_BLOCK * 22 + 20, 100);
	}

	public boolean checkCollision() {
		return Pinky.checkCollisionPac() || Inky.checkCollisionPac() || Blinky.checkCollisionPac()
				|| Clyde.checkCollisionPac();

	}

	public void drawLifes(Graphics2D g2) {
		Image image1 = new ImageIcon("E:\\laptrinh\\java\\Pacman\\res\\animations.png").getImage();

		for (int i = 0; i < lifes; i++) {
//			g2.drawImage(image1, 0, 0, 20, 20,   GameFrame.SIZE_OF_BLOCK * i+30,
//					GameFrame.SIZE_OF_BLOCK * GameFrame.MAP_Y+60,GameFrame.SIZE_OF_BLOCK * i+30+20, GameFrame.SIZE_OF_BLOCK * GameFrame.MAP_Y+60+20, null);

			g2.drawImage(image1, i * GameFrame.SIZE_OF_BLOCK, GameFrame.MAP_Y * GameFrame.SIZE_OF_BLOCK,
					i * GameFrame.SIZE_OF_BLOCK + GameFrame.SIZE_OF_BLOCK,
					GameFrame.MAP_Y * GameFrame.SIZE_OF_BLOCK + GameFrame.SIZE_OF_BLOCK, 0, 0, 20, 20, null);

		}

//		g2.setColor(Color.gray);
//		g2.drawString("abcdasdasdasd",20, 20);
	}

	public void getMap(String url) {

		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(url);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

		for (int i = 0; i < GameFrame.MAP_Y; i++) {

			for (int j = 0; j < GameFrame.MAP_X; j++) {
				try {
					do {
						map[i][j] = bufferedReader.read() - '0';

					} while (map[i][j] != 0 && map[i][j] != 1 && map[i][j] != 2);
//				temp+= map[i][j]+" ";
				}

				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// System.out.println(temp);
		}
	}

	public void drawInGame(Graphics2D g2) {
		drawFood(g2);
		Clyde.draw(g2);
		Pinky.draw(g2);
		Blinky.draw(g2);
		Inky.draw(g2);
		drawScore(g2);
		drawLifes(g2);
		pacman.updateUndead();
		pacman.draw(g2);
		if (checkCollision()) {
			lifes--;
			pacman.undeadMode = true;
			if (lifes < 1) {
				gameStage = LOSE;
			} else {
				gameStage = PAUSE;
			}
		}
	}

	public void drawPauseStage(Graphics2D g2) {
		g2.setColor(Color.black);
		g2.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);

		g2.setColor(Color.red);

		Font temp = new Font("ABC", Font.TRUETYPE_FONT, 30);

		g2.setFont(temp);
		Image image1 = new ImageIcon("E:\\laptrinh\\java\\Pacman\\res\\animations.png").getImage();
		g2.drawImage(image1, GameFrame.SCREEN_WIDTH / 2 - 20, GameFrame.SCREEN_HEIGHT / 2 - 100,
				GameFrame.SCREEN_WIDTH / 2 + GameFrame.SIZE_OF_BLOCK - 20,
				GameFrame.SCREEN_HEIGHT / 2 - 100 + GameFrame.SIZE_OF_BLOCK, 0, 0, 20, 20, null);

		g2.drawString(" Press any key to continue ", GameFrame.SCREEN_WIDTH / 4 + 50, GameFrame.SCREEN_HEIGHT / 2);
	}

	public void drawLoseStage(Graphics2D g2) {
		g2.setColor(Color.black);
		g2.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);

		g2.setColor(Color.red);

		Font temp = new Font("ABC", Font.TRUETYPE_FONT, 30);

		g2.setFont(temp);
		g2.drawString(" Press any key to New game ", GameFrame.SCREEN_WIDTH / 4 + 50, GameFrame.SCREEN_HEIGHT / 2);
		g2.drawString("You Lose", GameFrame.SCREEN_WIDTH / 2 - 60, GameFrame.SCREEN_HEIGHT / 2 - 50);
		getMap(urlToNewMap);
		lifes = 3;
		Score = 0;
	}
}
