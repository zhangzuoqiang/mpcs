package nio.core;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import nio.util.LangUtil;

import mpcs.interfaces.IRead;
import mpcs.interfaces.IWrite;
import mpcs.utils.MoreUtils;

/**
 * <p>Title: ByteArray数据处理类</p>
 * <p>Description: 解析来自客户端的Byte数据，
 * 同时包装服务器发送给客户端的数据</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class Packet implements IRead , IWrite{
	
	private ByteBuffer buff;
	private int length = 128;//初始长度
	
	/**
	 * 默认构造方法
	 * 开辟缓存区1024=1M
	 */
	public Packet(){
		buff = ByteBuffer.allocate(length);
	}
	/**
	 * 构造方法  指定缓存区大小
	 * @param size*
	 */
	public Packet(int size){
		length = size;
		buff = ByteBuffer.allocate(length);
	}
	/**
	 * 构造方法  指定ByteBuffer
	 * @param size*
	 */
	public Packet(ByteBuffer buffer){
		buff = buffer;
		length = buffer.limit();
	}
	
	//写入数据
	public void writeChar(char value){
		buff.putChar(value);
		length += 2;
	}
	public void writeByte(byte value){
		buff.put(value);
		length += 1;
	}
	public void writeFloat(float value){
		buff.putFloat(value);
		length += 4;
	}
	public void writeLong(long value){
		buff.putLong(value);
		length += 8;
	}
	public void writeDouble(double value){
		buff.putDouble(value);
		length += 8;
	}
	public void writeInt(int value){
		buff.putInt(value);
		length += 4;
	}
	public void writeShort(short value){
		buff.putShort(value);
		length += 2;
	}
	public void writeBytes(byte[] bytes){
		buff.put(bytes);
		length += bytes.length;
	}
	
	public void writeUTF(String str){
		byte[] str_bytes;
		try {
			str_bytes = str.getBytes("utf-8");
			short len = (short)(str_bytes.length);
			writeShort(len);
			writeBytes(str_bytes);
			length += len;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 写字符串时，先写长度，再写值
	 * @param str
	 */
	public void writeString(String str){
		byte[] str_bytes = str.getBytes();
		short len = (short)(str_bytes.length);
		writeShort(len);
		writeBytes(str_bytes);
		length += len;
	}
	public void writeString(String str,String charset){
		try {
			byte[] str_bytes = str.getBytes(charset);
			short len = (short)(str_bytes.length);
			writeShort(len);
			writeBytes(str_bytes);
			length += len;
		} catch (UnsupportedEncodingException e) {
			MoreUtils.trace(LangUtil.get("10002"));
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	//读取数据
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
	/**
	 * 读字符串时，先写长度，再写值
	 */
	public String readString(){
		short len = buff.getShort();
		byte[] _bytes = new byte[len];
		buff.get(_bytes, 0, len);
		return new String(_bytes);
	}
	public String readString(String charset){
		short len = buff.getShort();
		byte[] _bytes = new byte[len];
		buff.get(_bytes, 0, len);
		try {
			return new String(_bytes, charset);
		} catch (UnsupportedEncodingException e) {
			MoreUtils.trace(LangUtil.get("10002"));
			e.printStackTrace();
			System.exit(0);
		}
		return new String(_bytes);
	}
	
	
	public ByteBuffer byteBuffer(){
		return buff;
	}
	public ByteBuffer pack(){
		int l = length();
		ByteBuffer buffer = ByteBuffer.allocate(l);
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
	 * @return
	 */
	public int length(){
		return length - buff.remaining();
	}
	/**
	 * 预定义的长度
	 * @return
	 */
	public int totalSize(){
		return length;
	}
	
	/**
	 * 输出bytes
	 * @param bytes
	 */
	public void printInfo(byte[] bytes){
		System.out.print("Rec: ");
		for(int i =0 ; i < bytes.length ; i++){
			System.out.print(" " + bytes[i]);
		}
		MoreUtils.trace("");
	}
}
