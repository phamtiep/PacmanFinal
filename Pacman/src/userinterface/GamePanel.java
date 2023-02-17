package userinterface;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Iterator;
//import java.util.Timer;
import java.util.TimerTask;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import GameModel.GameState;
import GameModel.State;

public class GamePanel extends JPanel implements KeyListener,Runnable {
	Thread gameThread;
	HashSet<Integer> pressedKeys = new HashSet<Integer>();
	public State state = null;
	

	// InputManager inputManager;
	// JLabel label = new JLabel(4543453+"",JLabel.CENTER);
	public GamePanel() {
		// this.setBackground(Color.red);
		// gameState = new MenuState(this);
		
		this.setSize(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
		state = new State(this);
		new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (!pressedKeys.isEmpty()) {
					Iterator<Integer> i = pressedKeys.iterator();
					state.setPressedButton(i.next());
					System.out.println(Thread.currentThread().getName()); 
				}
				
				
			}
		}).start();

		// inputManager = new InputManager(gameState);
		
		
	}

	public void startGame() {
		gameThread = new Thread(this);
		gameThread.start();

	}

	public boolean isRunning = true;

	@Override
	public void paint(Graphics g) {
		// URL a = GamePanel.class.getResource("dsa.jpg");
		// Image img =
		// Toolkit.getDefaultToolkit().createImage(GamePanel.class.getResource("/dsa.jpg"));

		//this.update(g);
		super.paint(g);
		Graphics2D gg = (Graphics2D) g;
		gg.drawImage(state.getBufferedImage(), 0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, null);
		
	}

	@Override
	public void run() {
		long FPS = 15;
		
		long previousTime = System.nanoTime();
		long currentTime;
		long sleepTime;

		long period = (long) 1e9 / FPS;

		while (isRunning) {
			// gameState.Render();

			state.draw();

			repaint();
			currentTime = System.nanoTime();
			sleepTime = period - (currentTime - previousTime);
			try {

				if (sleepTime > 0)
					Thread.sleep(sleepTime / 1000000);
				else
					Thread.sleep(period / 2000000);

			} catch (Exception e) {
			}

			previousTime = System.nanoTime();

			// isRunning = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		pressedKeys.add(e.getKeyCode());
		//state.setPressedButton(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		pressedKeys.remove(e.getKeyCode());
	}
}
	