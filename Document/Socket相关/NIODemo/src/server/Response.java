package server;

import java.nio.channels.SocketChannel;
import java.nio.ByteBuffer;
import java.io.IOException;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-12
 */
public class Response {
    private SocketChannel sc;

    public Response(SocketChannel sc) {
        this.sc = sc;
    }
    
    public void send(byte[] data) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(data.length);
        buffer.put(data, 0, data.length);
        buffer.flip();
        sc.write(buffer);
    }
}
