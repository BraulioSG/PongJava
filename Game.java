import java.awt.*;
import javax.swing.*;

public class Game extends JFrame{

    public Game(){
	initUI();
    }

    private void initUI(){
	add(new Pong());
	setResizable(false);
	pack();

	setTitle("Pong!");

	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String args[]){
	EventQueue.invokeLater(() -> {
	    Game w = new Game();
	    w.setVisible(true);
	});
    }
}
