package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import config.ServerConfig;

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
            
            String query = "GB";
            byte[] request = query.getBytes();
            out.write(request);
            out.flush();
            client.shutdownOutput();
            
            in = new DataInputStream(client.getInputStream());
            byte[] reply = new byte[51];
            in.read(reply);
            System.out.println("Time: " + new String(reply, "UTF-8"));
            
            in.close();
            out.close();
            client.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
