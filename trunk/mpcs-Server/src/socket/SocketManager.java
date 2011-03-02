package socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;

/**
 * �̹߳�����
 * @author zhangzuoqiang
 * <br/>2011-3-2
 */

public class SocketManager extends Vector<Socket>{
   
    private static final long serialVersionUID = 1L;
   
    private static SocketManager thisClass=null;
   
    /**
    * ��ô��൥һʵ��
    * @return
    */
    public static SocketManager getSingleSocketManager(){
        if(thisClass==null){
            thisClass=new SocketManager();
        }
        return thisClass;
    }
   
    public SocketManager(){
       
    }
   
    /**
    * �����пͻ��˹㲥һ����Ϣ
    * @param value ��Ϣ����
    */
    public synchronized void sendToAll(String value){
        PrintStream ps;
        Socket s;
       
        for(int i=0;i< this.size();i++){
            s=(Socket)this.elementAt(i);
            try {
                ps=new PrintStream(s.getOutputStream());
                ps.print(value);
                ps=null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
