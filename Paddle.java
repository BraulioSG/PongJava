import java.awt.*; 
import java.awt.event.*;

public class Paddle{

    private double x, y;
    private double vy;
    private boolean up, down;

    public int w, h;

    private final int VELOCITY = 500;

    public Paddle(int x){
	this.w = 25;
	this.h = 100;
	this.x = x - w/2;
	this.y = Pong.SCREEN_HEIGHT/2 - h/2;
    }

    public void update(double dt){
	this.y += this.vy * dt;

	if(!up && !down){
	    this.vy = 0;
	}
	if(up) moveUp();
	if(down) moveDown();

	if(this.y <= 0){
	    this.vy = 0;
	    this.y = 1;
	    this.up = false;
	}
	else if(this.y >= Pong.SCREEN_HEIGHT){
	    this.vy = 0;
	    this.y = Pong.SCREEN_HEIGHT - this.h - 1;
	    this.down = false;
	}
    }

    public void draw(Graphics g){
	g.fillRect((int)this.x, (int)this.y, this.w, this.h);
    }

    private void moveUp(){
	this.vy = -VELOCITY; 
    }

    private void moveDown(){
	this.vy = VELOCITY;
    }

    public int getPosY(){
	return (int)this.y;
    }

    public int getPosX(){
	return (int)this.x;
    }

    public int getVel(){
	return (int)this.vy;
    }

    public Rectangle getBoundaries(){
	return new Rectangle((int)this.x, (int)this.y, this.w, this.h);
    }

    public void setUp(boolean status){
	this.up = status;
    }
    public void setDown(boolean status){
	this.down = status;
    }

    //Key Listeners
    public void keyPressed(KeyEvent e){
	int key = e.getKeyCode(); 
	
	if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W){
	    up = true;
	}
	
	if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S){
	    down = true;
	}
    }
    
    public void keyReleased(KeyEvent e){
	int key = e.getKeyCode(); 

	if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W){
	    up = false;
	}

	if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S){
	    down = false;
	}
    }
}
