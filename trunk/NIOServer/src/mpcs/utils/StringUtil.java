package mpcs.utils;

import java.security.MessageDigest;

/**
 * <p>Title: String相关工具类</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-14
 */
public class StringUtil {
	
	
	/**
	 * 文本加密，基于MD5
	 * @param str
	 * @return
	 */
	public static String md5Encoder( String str ) {
		try {
			MessageDigest md = MessageDigest.getInstance( "MD5" );
			md.update( str.getBytes() );
			
			byte[] b = md.digest();
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < b.length; i++){
				int v = (int)b[i];
				v = v < 0 ? 0x100 + v : v;
				String cc = Integer.toHexString(v);
				if( cc.length()==1 )
					sb.append( '0' );
				sb.append( cc );
			}
			return sb.toString();
		} catch( Exception e ) {
		}
		return "";
	}
	
	
	/**
	 * base64Decoder 解密
	 * @param string
	 * @return
	 */
	public static String base64Encoder( String string ){
		return BASE64.encode(string);
	}
	
	/**
	 * base64Decoder 解密
	 * @param string
	 * @return
	 */
	public static String base64Decoder( String string ){
		return BASE64.decodeAsString(string);
	}
	
	/**
	 * 文本替换
	 * @param origin
	 * @param src
	 * @param dest
	 * @return
	 */
	public static String replace( String origin, String src, String dest ) {
        if( origin == null ) 
        	return null;
        
        StringBuffer sb = new StringBuffer( origin.length() );
        int srcLength = src.length();
        int preOffset = 0;
        int offset = 0;
        while( (offset=origin.indexOf( src, preOffset ))!=-1 ) {
            sb.append( origin.substring( preOffset,offset ) );
            sb.append( dest );
            preOffset = offset + srcLength;
        }
        sb.append( origin.substring( preOffset, origin.length() ) );

        return sb.toString();
    }
}
