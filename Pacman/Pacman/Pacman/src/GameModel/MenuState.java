package GameModel;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import userinterface.GameFrame;
import userinterface.GamePanel;

public class MenuState {
	
	GamePanel gamePanel = null;
	private JPanel gamePanelOfMenu = null;
	Button a  = new Button("abc");
	MenuState(GamePanel gamePanel)
	{
		this.gamePanel  = gamePanel;
		
		gamePanelOfMenu = new JPanel() 
		{
		   @Override
		   public void paintComponent(Graphics g) 
		   {
			   super.paintComponent(g);
			   g.setColor(Color.GRAY);
			   g.drawString("abc", 0, 0);
			   Image image = new ImageIcon("E:\\laptrinh\\java\\Pacman\\res\\animations.png").getImage();
				g.fillRect(0, 0, 100, 100);
				g.drawImage(image, 0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, null);
				gamePanelOfMenu.add(a);
		   }
		   
		};
		this.gamePanel.setBackground(Color.red);
		this.gamePanel.add(gamePanelOfMenu);
		//this.gamePanel.setSize(400, 500);
		
//		gamePanelOfMenu.setVisible(true);
//		gamePanelOfMenu.setBackground(Color.red);
//		gamePanelOfMenu.setSize(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
		
	}
	public void draw() 
	{
		gamePanel.revalidate();
		gamePanelOfMenu.repaint();
		
	}
	
	
	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	public void setOnScreen(Boolean a) 
	{
		gamePanelOfMenu.setVisible(true);
	}
	
	
	

}
