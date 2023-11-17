package screen_sharing.server;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ScreenReciever extends Thread{
	
	private ObjectInputStream ois;
	private JPanel screen;
	
	public ScreenReciever(ObjectInputStream ois, JPanel screen) {
		this.ois = ois;
		this.screen = screen;		
		
	}
	
	private void drawImage() throws ClassNotFoundException, IOException {
        
		ImageIcon imageIcon = (ImageIcon) ois.readObject();
        System.out.println("New image recieved");
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(screen.getWidth(),screen.getHeight()
                                            ,Image.SCALE_SMOOTH);
        
        Graphics graphics = screen.getGraphics();
        graphics.drawImage(image, 0, 0, screen.getWidth(),screen.getHeight(),screen);
	}
	
	@Override
	public void run() {
		super.run();
		
		try {
			while(true){
				drawImage();
			}
		} catch (ClassNotFoundException | IOException e) {e.printStackTrace();}
		
	}
	

}
