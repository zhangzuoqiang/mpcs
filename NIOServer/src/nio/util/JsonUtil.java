package nio.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>Title: 将各种数据类型转换为Json的工具类</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-30
 */
public class JsonUtil {
	
	/**
	 * Object对象转为json格式
	 * @param obj
	 * @return
	 */
	public static String object2JSON(Object obj) {
		StringBuilder json = new StringBuilder();
        if (obj == null) {
        	json.append("\"\"");
        } else if (obj instanceof String || obj instanceof Byte) {
        	json.append("\"").append(string2JSON(obj.toString())).append("\"");
        }else if (obj instanceof Integer || obj instanceof Float || obj instanceof Boolean || obj instanceof Short 
        		|| obj instanceof Double || obj instanceof Long || obj instanceof BigDecimal || obj instanceof BigInteger) {
        	json.append(string2JSON(obj.toString()));
		} else if (obj instanceof Object[]) {
        	json.append(array2JSON((Object[]) obj));
        } else if (obj instanceof List) {
        	json.append(list2JSON((List<?>) obj));
        } else if (obj instanceof Map) {
        	json.append(map2JSON((Map<?, ?>) obj));
        } else if (obj instanceof Set) {
        	json.append(set2JSON((Set<?>) obj));
        } else {
        	json.append(bean2JSON(obj));
        }
        return json.toString();
    }
	
	/**
	 * 实体bean转为json对象
	 * @param bean
	 * @return
	 */
	public static String bean2JSON(Object bean) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		PropertyDescriptor[] props = null;
		try {
			props = Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors();
		} catch (IntrospectionException e) {}
		if (props != null) {
			for (int i = 0; i < props.length; i++) {
				try {
					String name = object2JSON(props[i].getName());
					String value = object2JSON(props[i].getReadMethod().invoke(bean));
					json.append(name);
					json.append(":");
					json.append(value);
					json.append(",");
				} catch (Exception e) {}
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}
	
	/**
	 * List列表转为json对象
	 * @param list
	 * @return
	 */
	public static String list2JSON(List<?> list) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				json.append(object2JSON(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}
	
	/**
	 * 转换数组为json对象
	 * @param array
	 * @return
	 */
	public static String array2JSON(Object[] array) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (array != null && array.length > 0) {
			for (Object obj : array) {
				json.append(object2JSON(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}
	
	/**
	 * 转换Map为json对象
	 * @param map
	 * @return
	 */
	public static String map2JSON(Map<?, ?> map) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		if (map != null && map.size() > 0) {
			for (Object key : map.keySet()) {
				json.append(object2JSON(key));
				json.append(":");
				json.append(object2JSON(map.get(key)));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}
	
	/**
	 * 转换set集合为json对象
	 * @param set
	 * @return
	 */
	public static String set2JSON(Set<?> set) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (set != null && set.size() > 0) {
			for (Object obj : set) {
				json.append(object2JSON(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}
	
	/**
	 * 转换string类型为json对象(加入转义字符)
	 * @param s
	 * @return
	 */
	public static String string2JSON(String s) {
		if (s == null)
			return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
				case '"':
					sb.append("\"");
					break;
				case '\\':
					sb.append("\\\\");
					break;
				case '\b':
					sb.append("\\b");
					break;
				case '\f':
					sb.append("\\f");
					break;
				case '\n':
					sb.append("\\n");
					break;
				case '\r':
					sb.append("\\r");
					break;
				case '\t':
					sb.append("\\t");
					break;
				case '/':
					sb.append("\\/");
					break;
				case '{':
					sb.append("{\n");
					break;
				case '}':
					sb.append("\n}\n");
					break;
				case ',':
					sb.append(",\n");
					break;
				case '[':
					sb.append("[\n");
					break;
				case ']':
					sb.append("]");
					break;
				default:
					if (ch >= '\u0000' && ch <= '\u001F') {//\u0000到\u00FF表示ASCII/ANSI字符
						String ss = Integer.toHexString(ch);
						sb.append("\\u");
						for (int k = 0; k < 4 - ss.length(); k++) {
							sb.append('0');
						}
						sb.append(ss.toUpperCase());
					} else {
						sb.append(ch);
					}
				}
		}
		return sb.toString();
	}
}