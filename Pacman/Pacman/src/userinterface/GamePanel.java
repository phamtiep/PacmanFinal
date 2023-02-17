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

public class GamePanel extends JPanel implements Runnable {
	Thread gameThread;

	HashSet<Integer> pressedKeys = new HashSet<Integer>();
	public State state = null;
	Action upRealse;
	Action upPress;

	Action downRealse;
	Action downPress;

	Action leftRealse;
	Action leftPress;

	Action rightRealse;
	Action rightPress;

	// InputManager inputManager;
	// JLabel label = new JLabel(4543453+"",JLabel.CENTER);
	public GamePanel() {
		// this.setBackground(Color.red);
		// gameState = new MenuState(this);
		this.setSize(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
		state = new State(this);
		new Timer(100, new ActionListener() {
			String rs= "";
			@Override
			
			public void actionPerformed(ActionEvent arg0) {
				
				if (!pressedKeys.isEmpty()) {
					Iterator<Integer> i = pressedKeys.iterator();
					
					if(i.hasNext()) 
					{
					state.setPressedButton(i.next());
					//	System.out.println(i.next()+"," +i.next());
					//System.out.println(i.toString());
//					rs+= i.next() +",";
					}
				}
//				System.out.println(rs);
			}
		}).start();

		// inputManager = new InputManager(gameState);
		upRealse = new UpRelease();
		upPress = new UpPress();
		downRealse = new DownRelease();
		downPress = new DownPress();

		leftRealse = new LeftRelease();
		leftPress = new LeftPress();

		rightRealse = new RightRelease();
		rightPress = new RightPress();
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "upActionRelease");
		this.getActionMap().put("upActionRelease", upRealse);
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "upActionPress");
		this.getActionMap().put("upActionPress", upPress);
		
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "downActionRelease");
		this.getActionMap().put("downActionRelease", downRealse);
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "downActionPress");
		this.getActionMap().put("downActionPress", downPress);
		
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "leftActionRelease");
		this.getActionMap().put("leftActionRelease", leftRealse);
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "leftActionPress");
		this.getActionMap().put("leftActionPress", leftPress);
		
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "rightActionRelease");
		this.getActionMap().put("rightActionRelease", rightRealse);
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "rightActionPress");
		this.getActionMap().put("rightActionPress", rightPress);
		
		
		
		
		
		
		
		

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

	public class UpPress extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			pressedKeys.add(KeyEvent.VK_UP);
			//System.out.println("da press");
		}
	}

	public class UpRelease extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//System.out.println("da release");
			pressedKeys.remove(KeyEvent.VK_UP);
		}
	}

	public class DownPress extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			pressedKeys.add(KeyEvent.VK_DOWN);
		}
	}

	public class DownRelease extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			pressedKeys.remove(KeyEvent.VK_DOWN);
		}

	}

	public class LeftPress extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			pressedKeys.add(KeyEvent.VK_LEFT);
		}
	}

	public class LeftRelease extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			pressedKeys.remove(KeyEvent.VK_LEFT);
		}
	}

	public class RightPress extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			pressedKeys.add(KeyEvent.VK_RIGHT);
		}
	}

	public class RightRelease extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			pressedKeys.remove(KeyEvent.VK_RIGHT);
		}
	}

}

//	public class DownAction extends AbstractAction{
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			label.setLocation(label.getX(), label.getY()+10);	
//		}		
//	}
//	public class LeftAction extends AbstractAction{
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			label.setLocation(label.getX()-10, label.getY());	
//		}		
//	}
//	public class RightAction extends AbstractAction{
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			label.setLocation(label.getX()+10, label.getY());
//		}		
//	}
//}
