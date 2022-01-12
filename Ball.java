import java.awt.*;

public class Ball{
    
    private double x, y, vx, vy;
    public final int SIZE = 25;

    public Ball(){
	this.x = Pong.SCREEN_WIDTH / 2 - this.SIZE / 2;
	this.y = Pong.SCREEN_HEIGHT / 2 - this.SIZE / 2;
	this.vx = (int)(Math.random() * 300) + 300;
	this.vy = (int)(Math.random() * 300);
    }

    public void draw(Graphics g){
	g.fillRect((int)this.x, (int)this.y, this.SIZE, this.SIZE);
    }

    public void update(double deltaTime){
	this.x += this.vx * deltaTime;
	this.y += this.vy * deltaTime;
	
	if(this.y <= 0 || (this.y + this.SIZE >= Pong.SCREEN_HEIGHT)){
	    if(this.y <= 0) this.y = 1;
	    else this.y = Pong.SCREEN_HEIGHT - this.SIZE -1;
	    this.vy = -this.vy;
	}

    }

    public void bounceX(){
	if(this.vx > 0){
	    this.vx = -((int)(Math.random() * 300) + 300);
	}
	else{
	    this.vx = (int)(Math.random() * 300) + 300;
	}
    }

    public int getPosX(){
	return (int)this.x;
    }

    public int getPosY(){
	return (int)this.y;
    }

    public int getVelY(){
	return (int)this.vy;
    }

    public void setPosX(int x){
	this.x = x;
    }

    public void setVelY(int vel){
	if(vel != 0)this.vy = vel;
    }

    public Rectangle getBoundaries(){
	return new Rectangle((int)this.x, (int)this.y, this.SIZE, this.SIZE);
    }
}
