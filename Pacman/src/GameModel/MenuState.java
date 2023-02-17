package GameModel;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;

import userinterface.GameFrame;
import userinterface.GamePanel;

public class MenuState {

	GamePanel gamePanel = null;
	private JPanel gamePanelOfMenu = null;
	// Button a = new Button("abc");
	JButton newGame = null;
	

	MenuState(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		// menuThread = new Thread(this);
		gamePanelOfMenu = new JPanel();
		JLabel jlabel = new JLabel();
		ImageIcon image = new ImageIcon("E:\\laptrinh\\java\\Pacman\\res\\menu.jpg");

		
		gamePanelOfMenu.addKeyListener(gamePanel);
		gamePanel.add(gamePanelOfMenu);
		
		jlabel.setIcon(image);
		
		jlabel.setSize(image.getIconWidth(), image.getIconHeight());
		jlabel.setBounds(40, 0, jlabel.getWidth(), jlabel.getHeight());
		gamePanel.setBackground(Color.BLACK);
	
		//Border border = BorderFactory.createLineBorder(Color.GREEN,5);
		gamePanelOfMenu.setBackground(Color.BLACK);
		
		gamePanelOfMenu.setBounds(0, jlabel.getHeight(), GameFrame.SCREEN_WIDTH,
				GameFrame.SCREEN_HEIGHT - jlabel.getHeight());
		
		
		gamePanel.add(jlabel);
		
		gamePanelOfMenu.setLayout(null);
		
		
		 newGame = new JButton("New Game");
		JButton continueGame = new JButton("Continue");
		
	
		gamePanelOfMenu.add(newGame);
		newGame.setFont(new Font("abc", Font.ITALIC, 56));
		
		newGame.setBackground(Color.yellow);
		newGame.setBounds((GameFrame.SCREEN_WIDTH-400)/2, 50, 400, 100);
		
		
		gamePanelOfMenu.add(continueGame);
		continueGame.setFont(new Font("abc", Font.ITALIC, 56));
		ActionListener acnewgame = new ActionListener() 
		{

			@Override
			public synchronized  void actionPerformed(ActionEvent e) {
				System.out.println(Thread.currentThread().getName());
				gamePanel.state.state = 2 ;
				gamePanelOfMenu.removeAll();
				gamePanel.remove(gamePanelOfMenu);
				gamePanel.state.gameState.urlToMap = "E:\\laptrinh\\java\\Pacman\\res\\map.txt";
			
			}
			
		};
		ActionListener acContinuegame = new ActionListener() 
		{

			@Override
			public synchronized  void actionPerformed(ActionEvent e) {
				System.out.println(Thread.currentThread().getName());
				gamePanel.state.state = 2 ;
				gamePanelOfMenu.removeAll();
				gamePanel.remove(gamePanelOfMenu);
				gamePanel.state.gameState.urlToMap = "E:\\laptrinh\\java\\Pacman\\res\\map.txt";
			
			}
			
		};
//		
		newGame.addActionListener(acnewgame);
		continueGame.addActionListener(acContinuegame);
		continueGame.setBackground(Color.yellow);
		continueGame.setBounds((GameFrame.SCREEN_WIDTH-400)/2, 250, 400, 100);
		

	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public void setOnScreen(Boolean a) {
		
	}

}
