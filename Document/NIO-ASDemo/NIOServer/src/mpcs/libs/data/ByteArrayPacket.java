package mpcs.libs.data;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import mpcs.libs.configs.ServerConfig;
import mpcs.libs.utils.MoreUtil;

/**
 * ByteArray数据包
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-8
 */
public class ByteArrayPacket {
	
	private ByteBuffer buff;
	private int length;
	
	/**
	 * 默认构造方法
	 * 开辟1M的缓存区
	 */
	public ByteArrayPacket(){
		this(ServerConfig.BUFFER_SIZE);
	}
	
	/**
	 * 构造方法  指定缓存区大小
	 * @param size*
	 */
	public ByteArrayPacket(int size){
		length=size;
		buff = ByteBuffer.allocate(length);
	}
	
	/**
	 * 构造方法  指定ByteBuffer
	 * @param buffer*
	 */
	public ByteArrayPacket(ByteBuffer buffer){
		buff=buffer;
		length=buffer.limit();
	}
	
	// 写入数据
	public void writeChar(char value){
		buff.putChar(value);
	}
	public void writeByte(byte value){
		buff.put(value);
	}
	public void writeFloat(float value){
		buff.putFloat(value);
	}
	public void writeLong(long value){
		buff.putLong(value);
	}
	public void writeDouble(double value){
		buff.putDouble(value);
	}
	public void writeInt(int value){
		buff.putInt(value);
	}
	public void writeShort(short value){
		buff.putShort(value);
	}
	public void writeBytes(byte[] bytes){
		buff.put(bytes);
	}
	public void writeString(String str){
		byte[] str_bytes=str.getBytes();
		short len=(short)(str_bytes.length);
		writeShort(len);
		writeBytes(str_bytes);
	}
	public void writeString(String str,String charset){
		try {
			byte[] str_bytes=str.getBytes(charset);
			short len=(short)(str_bytes.length);
			writeShort(len);
			writeBytes(str_bytes);
		} catch (UnsupportedEncodingException e) {
			MoreUtil.trace("不能识别的字符编码");
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	// 读取数据
	public char readChar(){
		return buff.getChar();
	}
	public byte readByte(){
		return buff.get();
	}
	public float readFloat(){
		return buff.getFloat();
	}
	public long readLong(){
		return buff.getLong();
	}
	public double readDouble(){
		return buff.getFloat();
	}
	public int readInt(){
		return buff.getInt();
	}
	public short readShort(){
		return buff.getShort();
	}
	public String readString(){
		short len=buff.getShort();
		byte[] _bytes=new byte[len];
		buff.get(_bytes, 0, len);
		return new String(_bytes);
	}
	public String readString(String charset){
		short len=buff.getShort();
		byte[] _bytes=new byte[len];
		buff.get(_bytes, 0, len);
		try {
			return new String(_bytes,charset);
		} catch (UnsupportedEncodingException e) {
			MoreUtil.trace("不能识别的字符编码");
			e.printStackTrace();
			System.exit(0);
		}
		return new String(_bytes);
	}
	
	// ByteBuffer相关操作
	public ByteBuffer byteBuffer(){
		return buff;
	}
	public ByteBuffer pack(){
		int l=length();
		ByteBuffer buffer=ByteBuffer.allocate(l);
		if(position()>0){
			flip();
		}
		buffer.put(array(), 0, l);
		buffer.flip();
		return buffer;
	}
	public byte[] array(){
		return buff.array();
	}
	public int position(){
		return buff.position();
	}
	public void flip(){
		buff.flip();
	}
	public void clear(){
		buff.clear();
		length=0;
	}
	/**
	 * 实际存在有用数据的长度
	 * @return 数据的长度
	 */
	public int length(){
		return length-buff.remaining();
	}
	/**
	 * 预定义的长度
	 * @return
	 */
	public int totalSize(){
		return length;
	}
	
	/**
	 * 输出byte[]
	 * @param bytes
	 */
	public void outInfo(byte[] bytes){
		for(int i = 0; i < bytes.length; i++){
			MoreUtil.trace("--------- "+ bytes[i]);
		}
	}
}