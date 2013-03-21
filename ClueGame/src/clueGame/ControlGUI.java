package clueGame;

import java.awt.*;

import javax.swing.JFrame;

public class ControlGUI extends JFrame {

	public ControlGUI(){
		setSize(new Dimension(800, 300));
		setTitle("Clue Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//main panel
		MainPanel mainPanel = new MainPanel();
		add(mainPanel, BorderLayout.NORTH);
		
	}
	
	public static void main(String args[]){
		ControlGUI gui = new ControlGUI();
		gui.setVisible(true);
	}
}