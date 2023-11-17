package screen_sharing.server;

import java.awt.Rectangle;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.JPanel;

public class ScreenShare extends Thread{
	
	private Socket user;
	private JPanel screen;
	private Rectangle userScreenDim;
	private ObjectInputStream ois;

	public ScreenShare(Socket user, JPanel screen) {
		this.user = user;
		this.screen = screen;
		
		 try {
			ois = new ObjectInputStream(user.getInputStream());
			userScreenDim =(Rectangle) ois.readObject();
			
			
		} catch (ClassNotFoundException | IOException e) {e.printStackTrace();}
	}
	
	@Override
	public void run() {
		super.run();
		
		
		new ScreenReciever(ois,screen).start();
		
		new CommandSender(user, screen, userScreenDim).start();
		
	}
}








