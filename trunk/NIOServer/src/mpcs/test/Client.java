package mpcs.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import mpcs.utils.MoreUtils;

/**
 * <p>Title: 时间服务器 客户端测试程序</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-12
 */
public class Client {
    public Client() {
    }

    public static void main(String[] args) {
        Socket client = null;
        DataOutputStream out = null;
        DataInputStream in = null;
        try {
            client = new Socket("localhost", 5100);
            client.setSoTimeout(10000);
            out = new DataOutputStream( (client.getOutputStream()));

            String query = "GB";
            byte[] request = query.getBytes();
            out.writeShort(request.length);
            out.write(request);
            out.flush();
            client.shutdownOutput();

            in = new DataInputStream(client.getInputStream());
            byte[] reply = new byte[60];
            in.read(reply);
            MoreUtils.trace("Time: " + new String(reply, "UTF-8"));

            in.close();
            out.close();
            client.close();
        }
        catch (Exception e) {
        	MoreUtils.trace("Client Error: " + e.getMessage());
        }

    }

}
