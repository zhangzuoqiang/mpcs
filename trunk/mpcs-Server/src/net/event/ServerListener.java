package net.event;

import net.Request;
import net.Response;

/**
 * <p>Title: 服务器事件监听器</p>
 * <p>Description: 如果需要定义更多的事件，可在这里进行扩展</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public interface ServerListener {

   /**
    * <p>Title: 当客户端与服务器从连接开始到最后断开连接期间发生错误时触发该事件</p>
    * <p>Description: 通过该事件我们可以知道有什么错误发生。</p>
    * @param error 错误信息
    */
   public void onError(String error);

   /**
    * <p>Title: 当服务端收到客户端连接请求时，触发该事件。</p>
    * <p>Description: 通过该事件我们可以知道有新的客户端进入。该事件可用来控制服务端的负载。
    * 例如，服务器可设定同时只为一定数量客户端提供服务，当同时请求数超出数量时，
    * 可在响应该事件时直接抛出异常，以拒绝新的连接。</p>
    */
   public void onAccept() throws Exception;

   /**
    * <p>Title: 当客户端请求被服务器接受后触发该事件。该事件表明一个新的客户端与服务器正式建立连接。</p>
    * @param request 客户端请求
    */
   public void onAccepted(Request request) throws Exception;

   /**
    * <p>Title: 当客户端发来数据，并已被服务器控制线程正确读取时，触发该事件。</p>
    * <p>Description: 该事件通知各事件处理器可以对客户端发来的数据进行实际处理了。
    * 需要注意的是，在本模型中，客户端的数据读取是由控制线程交由读线程完成的，
    * 事件处理器不需要在该事件中进行专门的读操作，而只需将控制线程传来的数据进行直接处理即可。</p>
    * @param request 客户端请求
    */
   public void onRead(Request request) throws Exception;

   /**
    * <p>Title: 当客户端可以开始接受服务端发送数据时触发该事件</p>
    * <p>Description: 通过该事件，我们可以向客户端发送回应数据。在本模型中，事件处理器只需要在该事件中设置</p>
    * @param request 客户端请求
    * @param response 服务端回应
    */
   public void onWrite(Request request, Response response) throws Exception;

   /**
    * <p>Title: 当客户端与服务器断开连接时触发该事件</p>
    * <p>Description: 在sc.finishConnect();  sc.socket().close();  sc.close();前调用
    * <br/>例如在类Writer Line60中。</p>
    * @param request 客户端请求
    */
   public void onClosed(Request request) throws Exception;
}
