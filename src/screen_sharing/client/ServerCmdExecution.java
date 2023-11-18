package screen_sharing.client;

import java.awt.Robot;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ServerCmdExecution extends Thread{

    Socket socket = null;
    Robot robot = null;
    
    boolean continueLoop = true;

	public ServerCmdExecution(Socket socket, Robot robot) {
		
		 this.socket = socket;
	     this.robot = robot;
	  
	}
	public void run(){
		
		try {
			
			System.out.println("Preparing InputStream");
			Scanner scanner = new Scanner(socket.getInputStream());
			while(continueLoop){
               
                System.out.println("Waiting for command");
                int command = scanner.nextInt();
                System.out.println("New command: " + command);
                switch(command){
                    case -1:
                        robot.mousePress(scanner.nextInt());
                    break;
                    case -2:
                        robot.mouseRelease(scanner.nextInt());
                    break;
                    case -3:
                        robot.keyPress(scanner.nextInt());
                    break;
                    case -4:
                        robot.keyRelease(scanner.nextInt());
                    break;
                    case -5:
                        robot.mouseMove(scanner.nextInt(), scanner.nextInt());
                    break;
                }
			} 
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		

   }
	
}
