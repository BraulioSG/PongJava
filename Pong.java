import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Pong extends JPanel implements Runnable{
    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = (int)(SCREEN_WIDTH*3/4);

    private Thread animator;
    private final int Delay = 10; 

    private Paddle player, opponent;
    private Ball ball;

    public Pong(){
	initBoard();
    }

    private void initBoard(){
	setBackground(Color.BLACK);
	setFocusable(true);
	setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
	addKeyListener(new UserInput());
    }

    private void resetElements(){
	player = new Paddle(50);
	opponent = new Paddle(SCREEN_WIDTH - 50);
	ball = new Ball(); 
    }

    private class UserInput extends KeyAdapter{
	@Override
	public void keyReleased(KeyEvent e){
	    player.keyReleased(e);
	}
	public void keyPressed(KeyEvent e){
	    player.keyPressed(e);
	}
    }

    private void update(double deltaTime){
	player.update(deltaTime);
	opponent.update(deltaTime);
	ball.update(deltaTime);

	if (ball.getPosX() > SCREEN_WIDTH) resetElements();
	else if (ball.getPosX() < 0) resetElements();

	Rectangle ballCollider = ball.getBoundaries();
	
	if (ball.getPosX() > (SCREEN_WIDTH / 2)){
	    if(ball.getPosY()-ball.SIZE < opponent.getPosY()){
		opponent.setUp(true);
	    }
	    else{
		opponent.setUp(false);
	    }

	    if(ball.getPosY()+ball.SIZE > opponent.getPosY() + opponent.h){
		opponent.setDown(true);
	    }
	    else{
		opponent.setDown(false);
	    }
	    
	    Rectangle opponentCollider = opponent.getBoundaries();

	    if(ballCollider.intersects(opponentCollider)){
		ball.bounceX();
		ball.setPosX(opponent.getPosX() - ball.SIZE - 1);
		ball.setVelY(opponent.getVel());
	    }
	}
	else{
	    opponent.setUp(false);
	    opponent.setDown(false);
	    Rectangle playerCollider = player.getBoundaries();

	    if(ballCollider.intersects(playerCollider)){
		ball.bounceX();
		ball.setPosX(player.getPosX() + ball.SIZE + 1);
		ball.setVelY(player.getVel());
	    }
	}
    }

    @Override
    public void paintComponent(Graphics g){
	super.paintComponent(g);
	g.setColor(Color.WHITE);
	player.draw(g);
	opponent.draw(g);
	ball.draw(g);
    }

    @Override
    public void addNotify(){
	super.addNotify();

	animator = new Thread(this);
	animator.start();
    }

    @Override
    public void run(){
	Time.InitTime();
	resetElements();
	while(true){
	    update(Time.getDeltaTime());
	    repaint();
	    Time.CalcDeltaTime();
	}
    }
}
