package server.event;

import server.Request;
import server.Response;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-12
 */
public interface ServerListener {
	
   public void onError(String error);
   
   public void onAccept() throws Exception;
   
   public void onAccepted(Request request) throws Exception;
   
   public void onRead(Request request) throws Exception;
   
   public void onWrite(Request request, Response response) throws Exception;
   
   public void onClosed(Request request) throws Exception;
}
