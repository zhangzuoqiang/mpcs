package net;

import java.nio.channels.SocketChannel;
import java.nio.ByteBuffer;
import java.io.IOException;

/**
 * <p>Title: 回应器</p>
 *  <p>Description: 用于向客户端发送数据</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class Response {
	
    private SocketChannel sc;

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
}
