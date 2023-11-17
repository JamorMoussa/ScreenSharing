package screen_sharing.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JPanel;

public class Server {
	
	private ServerSocket server;
	private JPanel screen;
	
	public Server(int port, JPanel screen) throws IOException {
		server = new ServerSocket(port);
	}
	
	public void acceptUsers() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						Socket user = server.accept();
						
						new ScreenShare(user,screen).start();
						
					} catch (IOException e) {e.printStackTrace();}
				}
			}
		}).start();
	}

	public static void main(String[] args) {
		
	}

}
