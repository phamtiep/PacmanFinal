package userinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {

	public static final int SCREEN_WIDTH = 900;
	public static final int SCREEN_HEIGHT = 900;
	public static final int SIZE_OF_BLOCK = 30;
	public static final int MAP_X = 21;
	public static final int MAP_Y = 23;
	public static final int NULL_SPACE_WIDTH = 20;
	public static final int THICKNESS_WALL = (SIZE_OF_BLOCK - NULL_SPACE_WIDTH) / 2;
	// private static Object[][] map;
	GamePanel gamePanel;
	// GamePanel dPanel ;

	public GameFrame() {
		super("PacMan");
		this.setBackground(Color.BLACK);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		this.setLocationRelativeTo(null);
		gamePanel = new GamePanel();
		//this.addKeyListener(gamePanel);

	}

	public void startGame() {
		
		gamePanel.startGame();
		// dPanel.startGame();
		
		this.add(gamePanel);
		gamePanel.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		this.setVisible(true);
		
		// gamePanel.setVisible(false);

	}

	public static void main(String[] Arg) {
		GameFrame gameFrame = new GameFrame();
		gameFrame.startGame();
		

	}
}
