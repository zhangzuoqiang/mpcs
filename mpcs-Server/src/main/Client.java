package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import mpcs.config.ServerConfig;

/**
 * 客户端测试程序
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class Client {
	
	private static Socket client;
	private static DataOutputStream out ;
	private static DataInputStream in;
	
    public Client() {
    }
    
    public static void main(String[] args) 
    {    	
        try {
            client = new Socket(ServerConfig.SERVER_ADDR, ServerConfig.LISTENNING_PORT);
            client.setSoTimeout(ServerConfig.CONNECT_TIMEOUT);
            out = new DataOutputStream( (client.getOutputStream()));
            //================ 测试发送消息 ================
            out.writeBytes("100102");
    	    out.writeBytes("-");
    	    out.writeBytes("0");
    	    out.writeBytes("-");
    	    out.writeBytes("0");
    	    out.writeBytes("-");
    	    out.writeBytes("csdn_eric@gmail.com");
    	    out.writeBytes("-");
    	    out.writeBytes("1230"); 
    	    out.flush();
    	    client.shutdownOutput();
    	    //================ 测试发送消息 ================
    	    
            in = new DataInputStream(client.getInputStream());
            byte[] reply = new byte[10];
            in.read(reply);
            System.out.println("Response: " + new String(reply, "UTF-8"));
            
            in.close();
            out.close();
            client.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
