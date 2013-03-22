package clueGame;

import java.awt.*;

import javax.swing.JFrame;

public class ControlGUI extends JFrame {

	public ControlGUI(){
		JFrame f = new JFrame();
		setSize(new Dimension(700, 300));
		setTitle("Clue Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//main panel
		MainPanel mainPanel = new MainPanel();
		f.getContentPane().setLayout(new BorderLayout());
		add(mainPanel, BorderLayout.WEST);
		
		
	}
	
	public static void main(String args[]){
		ControlGUI gui = new ControlGUI();
		gui.setVisible(true);
	}
}
