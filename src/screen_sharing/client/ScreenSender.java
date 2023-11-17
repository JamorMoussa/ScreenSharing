package screen_sharing.client;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;

public class ScreenSender extends Thread{
	
	    boolean continueLoop = true; 
		Socket socket ; 
		Robot robot; 
		Rectangle rectangle;

	    public ScreenSender(Socket socket, Robot robot, Rectangle rect) {
				

			this.socket = socket;
			this.robot = robot;
			rectangle = rect;
			
	   }
	    public void run()  {
	    	
	    	ObjectOutputStream oos =null;
		   
		   try {
			   
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(rectangle);
			
		      
		   while(continueLoop){
	    
	            BufferedImage image = robot.createScreenCapture(rectangle);
	            ImageIcon imageIcon = new ImageIcon(image);
	           
                oos.writeObject(imageIcon);
                
                oos.reset();
               
                
                Thread.sleep(100);
	       }
		   
		   } catch (InterruptedException | IOException e) {
				
			    e.printStackTrace();
		      }
		   
		   
	
	    }
	    }


