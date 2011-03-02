import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

import thread.ChatThread;
import utils.OutTxt;

/**
 * 多线程Socket服务器 主方法
 * ipAddr: 172.25.135.248
 * @author zhangzuoqiang
 * <br/>2011-3-2
 */
public class Main extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private ServerSocket server;
	private int serverPort=3000;
	
	public static void main(String[] args){
		new Main(3000).start();
	}
	
	public Main(int port){
		serverPort=port;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(550,400);
		this.add(OutTxt.getSingleOutTxt());
	}
	
	public void start(){
		this.setVisible(true);
		try{
			server=new ServerSocket(serverPort);
			OutTxt.getSingleOutTxt().append(">>服务器已经启动\n");
			while(true){
				handleComing(server.accept());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void handleComing(Socket s){
		new ChatThread(s).start();
	}
}
