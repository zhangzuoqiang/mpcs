package utils;

import java.awt.TextArea;

/**
 * 显示服务器启动消息
 * @author zhangzuoqiang
 * <br/>2011-3-2
 */
public class OutTxt extends TextArea{
	
    private static final long serialVersionUID = 1L;
    
    private static OutTxt thisClass=null;
   
    public static OutTxt getSingleOutTxt(){
        if(thisClass==null){
            thisClass=new OutTxt();
        }
        return thisClass;
    }
}

