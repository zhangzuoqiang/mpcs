package thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import socket.SocketManager;
import utils.OutTxt;

/**
 * 为每一个连接开辟一个线程
 * @author zhangzuoqiang
 * <br/>2011-3-2
 */

public class ChatThread extends Thread{
	
    private BufferedReader br;
    private PrintStream ps;
    private Socket inSocket;
    private SocketManager sm=SocketManager.getSingleSocketManager();
    private String theLoginName="匿名用户";
   
    /**
    * 消息头
    */
    private String MSG_HEAD="msg:";
   
    /**
    * 在线人数信息头
    */
    private String ONLINE_NUM="online:";
   
    /**
    * 服务器广播信息头
    */
    private String POST="post:";
   
    /**
    * 登陆信息头
    */
    private String LOGIN="login:";
   
    public ChatThread(Socket s){
        inSocket=s;
        sm.add(inSocket);
       
        String st="["+this.ONLINE_NUM+sm.size()+"]";
        sm.sendToAll(st);
        OutTxt.getSingleOutTxt().append(st);
    }
   
    public void run(){
        try {
            br=new BufferedReader(new InputStreamReader(inSocket.getInputStream(),"gbk"));
            ps=new PrintStream(inSocket.getOutputStream());
           
            String line;
            while((line=br.readLine())!=null){
                OutTxt.getSingleOutTxt().append(line+"\n");
               
                //用户登陆
                String loginName=null;
                loginName=this.getBodyByHead(this.LOGIN, line);
                if(loginName!=null){
                    this.theLoginName=loginName;
                    sm.sendToAll("["+this.POST+loginName+" 登陆了]");
                }
               
                //用户发言
                String msg=null;
                msg=this.getBodyByHead(this.MSG_HEAD, line);
                if(msg!=null){
                    sm.sendToAll("["+this.MSG_HEAD+this.theLoginName+":"+msg+"]");
                }
            }
        } catch (IOException e) {
           
        }finally{
            try {
                br.close();
                ps.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            br=null;
            ps=null;
           
            sm.remove(inSocket);
            String sendInfo="["+this.POST+this.theLoginName+" 离开了"+"]["+this.ONLINE_NUM+sm.size()+"]";
            sm.sendToAll(sendInfo);
            OutTxt.getSingleOutTxt().append(sendInfo+"\n");
            this.interrupt();
        }
    }
   
    /**
    * 用于获得消息内容
    * @param head 消息头
    * @param totalString 全部消息内容
    * @return 消息体
    */
    private String getBodyByHead(String head,String totalString){
        String returnString=null;
   
        int headIndex=totalString.indexOf("["+head);
   
        if(headIndex!=-1){
            returnString=totalString.substring(headIndex+head.length()+1,totalString.indexOf("]",headIndex));
        }
       
        return returnString;
    }
}

