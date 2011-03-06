package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import mpcs.config.ServerConfig;

/**
 * 时间查询服务 客户端程序
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class Client {
	
    public Client() {
    }
    
    public static void main(String[] args) 
    {
    	Socket client = null;
    	DataOutputStream out = null;
    	DataInputStream in = null;
    	
        try {
            client = new Socket(ServerConfig.SERVER_ADDR, ServerConfig.LISTENNING_PORT);
            client.setSoTimeout(ServerConfig.CONNECT_TIMEOUT);
            out = new DataOutputStream( (client.getOutputStream()));
            
            out.writeBytes("100101");
            out.writeBytes("-");
            out.writeBytes("0");
            out.writeBytes("-");
            out.writeBytes("0");
            out.writeBytes("-");
            out.writeBytes("csdn.eric@gmail.com");
            out.writeBytes("-");
            out.writeBytes("15992827226"); 
            out.flush();
            client.shutdownOutput();
            
            in = new DataInputStream(client.getInputStream());
            byte[] reply = new byte[51];
            in.read(reply);
            System.out.println("Time: " + new String(reply, "UTF-8"));
            
//            in.close();
//            out.close();
//            client.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}