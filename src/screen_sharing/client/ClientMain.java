package screen_sharing.client;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMain {
	
	
	public void initialize(String ip, int port) throws UnknownHostException, IOException, AWTException {
		
         Socket socket = new Socket(ip,port);
        
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	     Rectangle rectangle = new Rectangle(dim);
         
         Robot robot = new Robot();
         
         new ScreenSender(socket,robot,rectangle).start();
         new ServerCmdExecution(socket,robot).start();
	}

}
