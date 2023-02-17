package GameModel;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import userinterface.GamePanel;

public class State {
	protected GamePanel gamePanel;
	public int state = 1;
	final int Menu = 1;
	final int Game = 2;
	BufferedImage image = null;
	GameState gameState = null;
	MenuState menuState = null;

	public State(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		gameState = new GameState(gamePanel);
		menuState = new MenuState(gamePanel);
	}

	public void draw() {
		if(state == Game) {
			//menuState.remove();
			//gamePanel.setEnabled(false);
			if (gameState.lifes == 0)
				gameState.gameStage = gameState.LOSE;
			else if (gameState.Score == 219)
				gameState.gameStage = gameState.WIN;
			gameState.update();
			this.image = gameState.getBufferedImage();
			
			
			
		}
		else if (state == Menu) {

			

			//menuState.startGame();
			//state = 2;
//		

		}

	}

	public void setPressedButton(int code) {

		
			
			gameState.setPressedButton(code);
		
	}

	public BufferedImage getBufferedImage() {
		return image;
	}

}
