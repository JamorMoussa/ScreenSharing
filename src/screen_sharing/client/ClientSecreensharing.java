package screen_sharing.client;

import java.awt.AWTException;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class ClientSecreensharing {

	
	public static void main(String[] args) throws NumberFormatException, UnknownHostException, IOException, AWTException {
		
		String ip = JOptionPane.showInputDialog("Please enter server IP");
	    String port = JOptionPane.showInputDialog("Please enter server port");
	    
	    new ClientMain().initialize(ip, Integer.parseInt(port));
	}

}
