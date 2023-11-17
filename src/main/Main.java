package main;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import screen_sharing.server.*;



public class Main extends JFrame{
	
	public Main() {
		
		setSize(new Dimension(800, 500));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	
	}

	public static void main(String[] args){
		
		Main win  = new Main();
		JPanel screen = new JPanel();
		
		win.getContentPane().add(screen);
		win.setVisible(true);
		
		System.out.println(screen.getWidth());
		
		try {
			Server server = new Server(12345, screen);
			server.acceptUsers();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
