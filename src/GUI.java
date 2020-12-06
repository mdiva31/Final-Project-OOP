import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class GUI {
	
	static JFrame f1;
	static JPanel p1;
	private Snake snake;
	private Rules rules;
	private Food food;
	private Font fontMenu;
	private Font fontHeader;
	private Font fontQwerty;
	
	public GUI() {
    	rules = new Rules();
    	rules.setRules(rules);
	}

	public void createGameWindow() {
		
		f1 = new JFrame("Snake Game (Swing)");
		f1.setSize(600, 600);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		f1.setLocationRelativeTo(null);
		p1 = new Draw();
		p1.setLayout(null);
		f1.add(p1);
		
		//Create Fonts
	    fontMenu = new Font( Font.SANS_SERIF, Font.PLAIN, 12 );
		fontHeader = new Font( Font.SANS_SERIF, Font.BOLD, 16 );
		fontQwerty = new Font( "Comic Sans MS", Font.BOLD | Font.ITALIC, 48 );
		
		f1.requestFocus();	
		f1.repaint();
		f1.setVisible(true);

		f1.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(rules.isStarted()==true) {
					
					//Pause Menu
					if(rules.isPause()==true){
						
						checkPauseMenuInputs(e);
						
					//Current Game	
					}else if(rules.isPause()==false) {

						checkCurrentGameInputs(e);
						
					}
					
				//Game Over Menu	
				}else if(rules.isFinished()==true) {
					
					checkGameOverInputs(e);
					
				//Start Menu	
				}else if(rules.isStartmenu()==true) {

					checkStartMenuInputs(e);
					
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
		});	

	}
	

	class Draw extends JPanel {
		
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			
			if(rules.isStarted() == true) {
				
				if(rules.isPause()==true) {
					
					drawPauseMenu(g2);
					
				}else if(rules.isPause()==false) {

					drawCurrentGame(g2);
					
				}
				
			}else if(rules.isFinishedBlink1() == true){

				drawBlinkAnimation1(g2);
				
			}else if(rules.isFinishedBlink2()==true) {

				drawBlinkAnimation2(g2);
				
			}else if(rules.isFinished() == true) {
				
				drawGameOver(g2);
				
			}else if(rules.isStartmenu()==true) {

				drawStartMenu(g2);
				
			}
				
		}
		
	}
	
	// -----------KeyListener-Methods---------------------
	
	public void checkPauseMenuInputs(KeyEvent e) {
		//ESC
		if(e.getKeyCode()==27) {
			rules.setPause(false);
		//Up	
		}else if(e.getKeyCode()==38) {
			if(rules.getMenuSelection().equals("Exit game")) {
				rules.setMenuSelection("Back to main menu");
				GUI.f1.repaint();
			}else if(rules.getMenuSelection().equals("Back to main menu")) {
				rules.setMenuSelection("Restart game");
				GUI.f1.repaint();
			}
		//Down	
		}else if(e.getKeyCode()==40) {
			if(rules.getMenuSelection().equals("Restart game")) {
				rules.setMenuSelection("Back to main menu");
				GUI.f1.repaint();
			}else if(rules.getMenuSelection().equals("Back to main menu")) {
				rules.setMenuSelection("Exit game");
				GUI.f1.repaint();
			}	
		//Space	
		}else if(e.getKeyCode()==32) {
			switch(rules.getMenuSelection()) {
			case "Restart game":
				rules.restartGame();
				break;
			case "Exit game":
				System.exit(0);
				break;
			case "Back to main menu":
				rules.setPause(false);
				rules.setStarted(false);
				rules.restartGame();
				rules.setStartmenu(true);
				rules.setMenuSelection("Start game");
				GUI.f1.repaint();
				break;
			}
		}
	}
	
	public void checkCurrentGameInputs(KeyEvent e) {
		if(e.getKeyCode()==38) {
			if(snake.isSnakeDown()==false) {
				snake.setSnakeDown(false);
				snake.setSnakeRight(false);
				snake.setSnakeLeft(false);
				snake.setSnakeUp(true);
			}
		}else if(e.getKeyCode()==40) {
			if(snake.isSnakeUp()==false) {
				snake.setSnakeUp(false);
				snake.setSnakeRight(false);
				snake.setSnakeLeft(false);
				snake.setSnakeDown(true);
			}
		}else if(e.getKeyCode()==37) {
			if(snake.isSnakeRight()==false) {
				snake.setSnakeUp(false);
				snake.setSnakeRight(false);
				snake.setSnakeDown(false);
				snake.setSnakeLeft(true);
			}
		}else if(e.getKeyCode()==39) {
			if(snake.isSnakeLeft()==false) {
				snake.setSnakeUp(false);
				snake.setSnakeLeft(false);
				snake.setSnakeDown(false);
				snake.setSnakeRight(true);
			}
		}else if(e.getKeyCode()==27) {
				rules.setPause(true);	
		}
	}
	
	public void checkGameOverInputs(KeyEvent e) {
		//Up
		if(e.getKeyCode()==38) {
			if(rules.getMenuSelection().equals("Exit game")) {
				rules.setMenuSelection("Restart game");
				GUI.f1.repaint();
			}	
		//Down	
		}else if(e.getKeyCode()==40) {
			if(rules.getMenuSelection().equals("Restart game")) {
				rules.setMenuSelection("Exit game");
				GUI.f1.repaint();
			}	
		//Space
		}else if(e.getKeyCode()==32) {
			switch(rules.getMenuSelection()) {
			case "Restart game":
				rules.restartGame();
				rules.startGame(rules);
				break;
			case "Exit game":
				System.exit(0);
				break;
			}
		}
	}
	
	public void checkStartMenuInputs(KeyEvent e) {
		//Up
		if(e.getKeyCode()==38) {
			if(rules.getMenuSelection().equals("Exit game")) {
				rules.setMenuSelection("Difficulty");
				GUI.f1.repaint();
			}else if(rules.getMenuSelection().equals("Difficulty")) {
				rules.setMenuSelection("Start game");
				GUI.f1.repaint();
			}		
		//Down	
		}else if(e.getKeyCode()==40) {
			if(rules.getMenuSelection().equals("Start game")) {
				rules.setMenuSelection("Difficulty");
				GUI.f1.repaint();
			}else if(rules.getMenuSelection().equals("Difficulty")) {
				rules.setMenuSelection("Exit game");
				GUI.f1.repaint();
			}	
		//Select Difficult right
		}else if(e.getKeyCode()==39) {
			if(rules.getMenuSelection().equals("Difficulty") && rules.getDifficultyLevel()!=2) {
				rules.setDifficultyLevel(rules.getDifficultyLevel()+1);
				GUI.f1.repaint();
			}
		//Select Difficult left	
		}else if(e.getKeyCode()==37) {
			if(rules.getMenuSelection().equals("Difficulty") && rules.getDifficultyLevel()!=0) {
				rules.setDifficultyLevel(rules.getDifficultyLevel()-1);
				GUI.f1.repaint();
			}
		}
		//Space
		else if(e.getKeyCode()==32) {
		switch(rules.getMenuSelection()) {
		case "Start game":
			rules.startGame(rules);
			break;
		case "Exit game":
			System.exit(0);
			break;
		}
	}
	}
	
	// -----------Draw-Methods---------------------

	public void drawPauseMenu(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, 600, 600);
		g2.setColor(Color.WHITE);
		g2.setFont(fontHeader);
		g2.drawString("Pause menu", 250, 100);
		g2.setFont(fontMenu);
		g2.drawString("Restart game", 250, 200);
		g2.drawString("Back to main menu", 250, 250);
		g2.drawString("Exit game", 250, 300);
		
		switch (rules.getMenuSelection()) {
		  case "Restart game":
			g2.fillOval(200, 190, 10, 10);
			break; 
		  case "Back to main menu":
			g2.fillOval(200, 240, 10, 10);
			break; 
		  case "Exit game":
				g2.fillOval(200, 290, 10, 10);
				break; 	
		}	
	}
	
	public void drawCurrentGame(Graphics2D g2) {
		// -------------------------------------------------------------------------------------------
		// Draw current game (left part of the game)
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, 400, 350);
		//draw walls
		g2.setColor(Color.BLACK);
		g2.fillRect(390, 0, 10, 360);
		g2.fillRect(0, 0, 10, 360);		
		g2.fillRect(0, 0, 400, 5);
		g2.fillRect(0, 360, 400, 10);
		g2.setColor(Color.RED);
		g2.fillRect(410,0,190,250);
		g2.setColor(Color.WHITE);
		g2.setFont(fontMenu);
		g2.drawString("Ini merupakan game Snake", 410, 40);
		g2.drawString("menggunakan Java Swing", 410, 60);
		g2.drawString("Made By : QWERTY", 410, 90);
		g2.drawString("Team : ", 410, 110);
		g2.drawString("Azhar Jauharul Umam", 410, 140);
		g2.drawString("M.Diva Eka Andriana", 410, 180);
		g2.drawString("M.Fadlan Fasya", 410, 220);

		g2.setColor(Color.BLUE);
		g2.fillRect(0,370,600,600);
		g2.setColor(Color.WHITE);
		g2.setFont(fontQwerty);
		g2.drawString("Q",10,480);
		g2.drawString("W",110,480);
		g2.drawString("E",210,480);
		g2.drawString("R",310,480);
		g2.drawString("T",410,480);
		g2.drawString("Y",510,480);
	
		//draw snake
		g2.setColor(Color.BLACK);
		for(int i=1; i<=snake.getList().size(); i++) { 
			g2.fillRect(snake.getList().get(i-1).x,snake.getList().get(i-1).y, 6, 6);
		}
		
		
		
		if(food.isFoodPlaced() == true) {
			g2.setColor(Color.GREEN);
			g2.fillRect(food.getFoodX(), food.getFoodY(), 6, 6);
		}
		
		
		// -------------------------------------------------------------------------------------------
		
		// -------------------------------------------------------------------------------------------
		// draw game-info (right)
		
		
		g2.setColor(Color.BLACK);
		g2.setFont(fontMenu);
		g2.drawString("LIVE POPUP SCORE", 420, 270);
		if(rules.getMinutes()<10 && rules.getSeconds()<10) {								//05:05
			
			g2.drawString("0"+rules.getMinutes()+":0"+rules.getSeconds(), 420, 290);
			
		}else if(rules.getMinutes()<10 && rules.getSeconds()>9) {							//05:55
			
			g2.drawString("0"+rules.getMinutes()+":"+rules.getSeconds(), 420, 290);
			
		}else if(rules.getMinutes()>9 && rules.getSeconds()>9) {							//55:55
			
			g2.drawString(rules.getMinutes()+":"+rules.getSeconds(), 420, 290);
			
		}else if(rules.getMinutes()>9 && rules.getSeconds()<10) {							//55:05
			
			g2.drawString(rules.getMinutes()+":0"+rules.getSeconds(), 420, 290);
			
		}
		
		g2.drawString("Score: "+rules.getScore(), 420, 310);
		g2.drawString("Max score left: "+rules.getMaxScoreLeft(), 420, 330);
		
		
		// -------------------------------------------------------------------------------------------
	}
	
	public void drawBlinkAnimation1(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, 400, 350);
		//draw walls
		g2.setColor(Color.BLACK);
		g2.fillRect(390, 0, 10, 360);
		g2.fillRect(0, 0, 10, 360);		
		g2.fillRect(0, 0, 400, 5);
		g2.fillRect(0, 360, 400, 10);
		

		//draw snake
		for(int i=1; i<=snake.getList().size(); i++) { 
			g2.fillRect(snake.getList().get(i-1).x,snake.getList().get(i-1).y, 6, 6);
		}
		

		if(food.isFoodPlaced() == true) {
			g2.setColor(Color.GREEN);
			g2.fillRect(food.getFoodX(), food.getFoodY(), 6, 6);
		}
	}
	
	public void drawBlinkAnimation2(Graphics2D g2) {
		//draw walls
		g2.setColor(Color.BLACK);
		g2.fillRect(390, 0, 10, 360);
		g2.fillRect(0, 0, 10, 360);		
		g2.fillRect(0, 0, 400, 5);
		g2.fillRect(0, 360, 400, 10);
		// for blinking animation
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, 400, 400);
		
		//draw walls
		g2.setColor(Color.BLACK);
		g2.fillRect(390, 0, 10, 360);
		g2.fillRect(0, 0, 10, 360);		
		g2.fillRect(0, 0, 400, 5);
		g2.fillRect(0, 360, 400, 10);
	}
	
	public void drawGameOver(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, 600, 600);
		g2.setColor(Color.WHITE);
		g2.setFont(fontHeader);
		g2.drawString("Game over! \n Your score: "+rules.getScore(), 250, 100);
		g2.setFont(fontMenu);
		g2.drawString("Restart game", 250, 200);
		g2.drawString("Exit game", 250, 250);
		
		switch (rules.getMenuSelection()) {

		  case "Restart game":
			g2.fillOval(200, 190, 10, 10);
			break; 
		  case "Exit game":
			g2.fillOval(200, 240, 10, 10);
			break;
		  case "Difficulty":
			  g2.fillOval(200, 290, 10, 10);
			  break;
		}	
	}
	
	public void drawStartMenu(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, 600, 600);
		g2.setColor(Color.WHITE);
		g2.setFont(fontHeader);
		g2.drawString("Snake Game By: QWERTY", 250, 100);
		g2.setFont(fontMenu);
		g2.drawString("Start game", 250, 200);
		g2.drawString("Difficulty:", 250, 250);
		g2.drawString("Exit game", 250, 300);
		g2.drawString("Easy", 305, 250);
		g2.drawString("Normal", 355, 250);
		g2.drawString("Hard", 405, 250);

		
		switch (rules.getMenuSelection()) {

		  case "Start game":
			g2.fillOval(200, 190, 10, 10);
			break; 
		  case "Difficulty":
			g2.fillOval(200, 240, 10, 10);
			break; 
		  case "Exit game":
				g2.fillOval(200, 290, 10, 10);
				break; 
		}	
		
		
		switch(rules.getDifficultyLevel()) {
		case 0:
			g2.drawRect(305, 239, 31, 14);		
			break;
		case 1:
			g2.drawRect(355, 239, 45, 14);
			break;
		case 2:
			g2.drawRect(405, 239, 32, 14);
			break;

		}
	}

	public Rules getRules() {
		return rules;
	}


	public void setRules(Rules rules) {
		this.rules = rules;
	}


	public Food getFood() {
		return food;
	}


	public void setFood(Food food) {
		this.food = food;
	}
	
	public Snake getSnake() {
		return snake;
	}


	public void setSnake(Snake snake) {
		this.snake = snake;
	}
	

}
