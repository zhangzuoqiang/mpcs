package nio.net;

import java.nio.channels.SocketChannel;
import java.nio.ByteBuffer;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * <p>Title: 回应器</p>
 *  <p>Description: 用于向客户端发送数据</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class Response {
	
    private SocketChannel sc;
	private DataOutputStream dataOutput;

    public Response(SocketChannel sc) {
        this.sc = sc;
    }

    /**
     * <p>Title: 向客户端写数据</p>
     * @param data byte[] 待回应数据
     */
    public void send(byte[] data) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(data.length);
        buffer.put(data, 0, data.length);
        // 将缓冲区准备为数据传出状态
        buffer.flip();
        sc.write(buffer);
    }
    
    /**
     * <p>发送消息头给客户端</p>
     * 客户端解析：
	 * <br/>h1: 返回结果为0表示成功;9表示失败（全局错误号）
	 * <br/>h2: 对应全局消息号
	 * <br/>h3: 扩展位，默认为0
     * @param globalError 全局错误号
     * @param globalCst 全局消息号
     * @param extension 默认为0
     * @throws IOException 
     */
    public void sendHeadMsgToClient(int globalError, int globalCst, int extension) throws IOException{
    	dataOutput = new DataOutputStream(sc.socket().getOutputStream());
    	//编写数据的长度
		dataOutput.writeInt(globalError);
		dataOutput.writeInt(globalCst);
		dataOutput.writeInt(extension);
		dataOutput.flush();
    }
    
    /**获得SocketChannel**/
    public SocketChannel getSocketChannel() {
		return sc;
	}
}
