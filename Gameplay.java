package snake;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon; 
import javax.swing.JPanel;
import javax.swing.Timer; 


//the class itself 
public class Gameplay extends JPanel implements KeyListener, ActionListener{
	//Initial definition of length - arrays. 
	private int[] snakexlength = new int[750];
	private int[] snakeylength = new int[750];
	
	//snake directions
	private boolean left = false; 
	private boolean right = false; 
	private boolean up = false; 
	private boolean down = false; 
	
	//images of the snake 
	private ImageIcon faceright; 
	private ImageIcon faceleft; 
	private ImageIcon faceup; 
	private ImageIcon facedown; 
	private ImageIcon body; 
	
	//image of the body
	private ImageIcon titleImage;
	
	//timer for the snake
	private Timer timer; 
	private int delay = 100;  
	
	//length of the snake
	private int lengthofsnake = 3; 
	
	// position for the "enemy"
	private int [] enemyxpos = { 75,100,125,150,175,200,225,250,275,300,325,350,
375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};					
	private int [] enemyypos = { 75,100,125,150,175,200,225,250,275,300,325,350,
375,400,425,450,475,500,525,550,575,600,625};	
	
	
	//draw the image in the game
		private ImageIcon enemy;
		private Random random = new Random();
	
		
		//create a random variable for the enemy
		private int xpos = random.nextInt(32);
		private int ypos = random.nextInt(21);
		
		//initial movement 
		private int moves = 0;
	
	
	
	private int score = 0; 
	
	//game constructor
	public Gameplay () {
		addKeyListener(this);
		setFocusable(true); 
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay,this); 
		timer.start();
	}

	//the graphics of the game
	public void paint (Graphics g) {
		
		if (moves ==0) {
			snakexlength[2] =50; 
			snakexlength[1] = 75; 
			snakexlength[0]= 100; 
			
			
			snakeylength[2] =100; 
			snakeylength[1] = 100; 
			snakeylength[0]= 100; 
		}
		//draw title + image border
		g.setColor(Color.white);
		g.drawRect(24, 11, 851, 55);
		
		//draw the title image 
		titleImage = new ImageIcon("snaketitle2.png");
		titleImage.paintIcon(this, g, 25, 11);
		
		//draw border for gameplay
		g.setColor(Color.white);
		g.drawRect(24, 74, 851, 577);
		
		//draw background for the gameplay 
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		//draw snake head in the initial position
		faceright = new ImageIcon("faceright.png");
		faceright.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		
		
		//draw the scores
		g.setColor(Color.white);
		g.setFont(new Font("Courier", Font.BOLD, 14));
		g.drawString("Scores: "+score ,800, 30);
		
		//draw snake length
		g.setColor(Color.white);
		g.setFont(new Font("Courier", Font.BOLD, 14));
		g.drawString("Length: "+lengthofsnake ,800, 50);
		
		//loop to draw the snake
		for (int a = 0; a < lengthofsnake; a++) {
			if (a==0 && right) {
				faceright = new ImageIcon("faceright.png");
				faceright.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if (a==0 && left) {
				faceleft = new ImageIcon("faceleft.png");
				faceleft.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if (a==0 && up) {
				faceup = new ImageIcon("faceup.png");
				faceup.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if (a==0 && down) {
				facedown = new ImageIcon("facedown.png");
				facedown.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if (a!=0) {
				body = new ImageIcon("body.png");
				body.paintIcon(this, g, snakexlength[a], snakeylength[a]);
				
			}
		}
	//enemy image
		enemy = new ImageIcon ("enemy.png"); 
		
		//check if the enemy is collided with the head of the snake ([0] position is for snake head
		//new position for enemy.
		if ((enemyxpos[xpos] == snakexlength[0] && enemyypos[ypos] == snakeylength[0])) {
			
			score++; 
			lengthofsnake++;
			xpos = random.nextInt(32);
			ypos = random.nextInt(21); 
		}
		
		enemy.paintIcon(this, g, enemyxpos[xpos], enemyypos[ypos]);
		
		
		for ( int b = 1; b < lengthofsnake; b++) {
			if (snakexlength[b] == snakexlength[0] && snakeylength[b] == snakeylength[0]) {
				right = false;
				left = false; 
				up = false; 
				down = false;
				
				g.setColor(Color.WHITE);
				g.setFont(new Font ("Courier", Font.BOLD, 50));
				g.drawString("Game Over", 300, 300);
				
				g.setFont(new Font ("Courier", Font.BOLD, 20));
				g.drawString("Space to Restart", 350, 340);
						
			}
		}
		g.dispose();
	}

	//user actions. setting the moves according to the keys 
	// using x and y to position snake limitations
	@Override
	public void actionPerformed(ActionEvent arg0) {
		timer.start();
		if (right) {
			for (int r = lengthofsnake-1; r>=0; r--) {
				snakeylength[r+1] = snakeylength[r];
			}
			for (int r = lengthofsnake; r>=0; r--) {
				
				if (r==0) {
					snakexlength[r] = snakexlength[r] + 25; 
				}
			
			 else {
				snakexlength[r] = snakexlength[r-1];
			} 
				if (snakexlength[r] > 850) {
					snakexlength[r] = 25; 
				}
				repaint();
	}
		}
		
		if (left) {
			for (int r = lengthofsnake-1; r>=0; r--) {
				snakeylength[r+1] = snakeylength[r];
			}
			for (int r = lengthofsnake; r>=0; r--) {
				
				if (r==0) {
					snakexlength[r] = snakexlength[r] - 25; 
				}
			
			 else {
				snakexlength[r] = snakexlength[r-1];
			} 
				if (snakexlength[r] < 25) {
					snakexlength[r] = 850; 
				}
				repaint();
			
		}
		}
		if (up) {
			for (int r = lengthofsnake-1; r>=0; r--) {
				snakexlength[r+1] = snakexlength[r];
			}
			for (int r = lengthofsnake; r>=0; r--) {
				
				if (r==0) {
					snakeylength[r] = snakeylength[r] - 25; 
				}
			
			 else {
				snakeylength[r] = snakeylength[r-1];
			} 
				if (snakeylength[r] < 75) {
					snakeylength[r] = 625; 
				}
				repaint();
		}
		}
		if (down) {
			for (int r = lengthofsnake-1; r>=0; r--) {
				snakexlength[r+1] = snakexlength[r];
			}
			for (int r = lengthofsnake; r>=0; r--) {
				
				if (r==0) {
					snakeylength[r] = snakeylength[r] + 25; 
				}
			
			 else {
				snakeylength[r] = snakeylength[r-1];
			} 
				if (snakeylength[r] > 625) {
					snakeylength[r] = 75; 
				}
			}
				repaint();
		}
	}
		
	
		
	 // defining user key. 

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			moves = 0;
			score = 0;
			lengthofsnake = 3; 
			repaint(); 
		}
	if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
		moves++;
		right = true;
		if (!left) {
			right = true; 
			
		}
		else {
			right = false;
			left = true;
		}
		up = false; 
		down = false; 
	}
	
	if (e.getKeyCode() == KeyEvent.VK_LEFT)
	{
		moves++; 
		left = true;
		if (!right) {
			left = true;
		}
		else {
			left = false; 
			right = true; 
		}
		up = false;
		down = false; 
	}
	
	if (e.getKeyCode() == KeyEvent.VK_UP) {
		moves++; 
		up = true; 
		if (!up) {
			down = true;
		}
		else {
			up = true;
			down = false;
		}
		left = false;
		right = false; 
	}
	
	if (e.getKeyCode() == KeyEvent.VK_DOWN) {
		moves++;
		down = true;
		if (!down) {
			up = true;
		}
		else {
			down = true;
			up = false; 
		}
		left = false; 
		right = false; 
	}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
