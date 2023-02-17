package GameModel;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import userinterface.GamePanel;

public   class State {
	protected GamePanel gamePanel;
	int state = 2;
	final int Menu = 1;
	final int Game = 2 ;
	BufferedImage image = null;
	GameState gameState=null;
	MenuState menuState = null;
	public State (GamePanel gamePanel) 
	{
		gameState = new GameState(gamePanel);
		 menuState = new MenuState(gamePanel);
	
		
	}
	
	public void draw()
	{
		switch (state) 
		{
		case(Menu):
		{
			Boolean a = true;
			menuState.draw();
			menuState.setOnScreen(a);
//			state = 2;
//			try {
//				Thread.sleep(20000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			break;
		}
		case(Game):
		{
			gameState.draw();
			image = gameState.getBufferedImage();
			break;
		}
		}
		
		
	}
	public void setPressedButton(int code ) 
	{
		gameState.setPressedButton(code);
	}
	
	
	
	
	
	
	
	
	
	
    public   BufferedImage getBufferedImage() {
		return image;
	}
    
    
   
}
