package screen_sharing.server;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JPanel;

public class CommandSender extends Thread implements KeyListener,
MouseMotionListener,MouseListener {
	
    private Socket user;
    private JPanel screen ;
    private PrintWriter writer ;
    private Rectangle userScreenDim ;
	
	public CommandSender(Socket user, JPanel screen, Rectangle userScreenDim) {
		
		this.user = user;
		this.screen = screen;
		this.userScreenDim = userScreenDim;
		
        screen.addKeyListener(this);
        screen.addMouseListener(this);
        screen.addMouseMotionListener(this);
        
        try {
			writer = new PrintWriter(user.getOutputStream());
			
			
		} catch (IOException e) {e.printStackTrace();}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
        double xScale = userScreenDim.getWidth()/screen.getWidth();
        double yScale = userScreenDim.getHeight()/screen.getHeight();
        writer.println(-5);
        writer.println((int)(e.getX() * xScale));
        writer.println((int)(e.getY() * yScale));
        writer.flush();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
        
        writer.println(-1);
        
        int button = e.getButton();
		
        int xButton = 16;
        if (button == 3) // if right button is clciked
		{
            xButton = 4;
        }
		
		writer.println(xButton);
        writer.flush();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
        writer.println(-2);
        
        int button = e.getButton();

		int xButton = 16;
        if (button == 3) {
            xButton = 4;
        }
        
        writer.println(xButton);
        writer.flush();
	
	}




	@Override
	public void keyPressed(KeyEvent e) {
		
        writer.println(-3);
        writer.println(e.getKeyCode());
        writer.flush();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
       
		writer.println(-4);
        writer.println(e.getKeyCode());
        writer.flush();
		
	}

	@Override
	public void mouseExited(MouseEvent e){}
	
	@Override
	public void mouseDragged(MouseEvent e){}
	
	@Override
	public void mouseEntered(MouseEvent e){}
	
	@Override
	public void keyTyped(KeyEvent e){}
	
}
