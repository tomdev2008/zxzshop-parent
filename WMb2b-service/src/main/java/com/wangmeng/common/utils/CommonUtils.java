package com.wangmeng.common.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class CommonUtils {

	private static Logger log = Logger.getLogger(CommonUtils.class);
	private static Map<String, SheetCodeGenerate> sheetCode = new ConcurrentHashMap<>();

	/**
	 * 生成相应单号
	 * 
	 * @author 朱飞
	 * @creationDate. 2016-9-26 下午2:35:44
	 * @param type
	 * @return
	 */
	public static String generateSheetCode(int type) {
		StringBuffer sb = new StringBuffer();
		String sybol = null;
		switch (type) {
		case 1:// 询价
			sybol = "XJ";
			break;
		case 2:// 采购
			sybol = "CG";
			break;
		case 3:// 报价
			sybol = "BJ";
			break;
		case 4:// 订单
			sybol = "DD";
			break;
		case 5:// 服务
			sybol = "FW";
			break;
		case 6:// 协议
			sybol = "XY";
			break;
		case 7://流水号
		    sybol = "LS";
		    break;
		default:// 未知
			sybol = "WZ";
			break;
		}
		sb.append(sybol);
		String time = date2string(new Date(), "yyMMddHHmm");
		sb.append(time);
		synchronized (CommonUtils.class) {
			int count = 0;
			SheetCodeGenerate gen = null;
			if (!sheetCode.containsKey(sybol)) {
				gen = new SheetCodeGenerate();
				gen.setTag(time);
				gen.setCount(1);
				count = 1;
			} else {
				gen = sheetCode.get(sybol);
				if (gen.getTag().equals(time)) {
					count = gen.getCount();
					count++;
				} else {
					count = 1;
				}
				gen.setTag(time);
				gen.setCount(count);
			}
			sheetCode.put(sybol, gen);
			sb.append(String.format("%03d", count));
		}
		return sb.toString();
	}

	/**
	 * 字符转时间
	 * @author 朱飞
	 * @creationDate. 2016-11-4 下午2:43:06 
	 * @param dateStr
	 * @return
	 */
	public static Date dateFormat(String dateStr) {
		Date dt = null;
		if (dateStr != null && !dateStr.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat();
			if (dateStr.contains(":")) {
			    if(dateStr.split(":").length == 3){
			        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
			    }else{
			        sdf.applyPattern("yyyy-MM-dd HH:mm");			        
			    }
			} else {
				sdf.applyPattern("yyyy-MM-dd");
			}
			try {
				dt = sdf.parse(dateStr.trim());
			} catch (Exception e) {
				log.warn("Failed to transfer string to date,dateStr:" + dateStr
						+ ",error:" + e.toString() + ",msg:" + e.getMessage());
			}
		}
		return dt;
	}
	
	/**
	 * 根据样式将字符转为时间
	 * @author 朱飞
	 * @creationDate. 2016-11-4 下午2:47:31 
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static Date string2Date(String dateStr,String pattern){
		Date dt = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern(pattern);
			dt = sdf.parse(dateStr);
		} catch (Exception e) {
			writeLog(log, Level.WARN, "Failed to transfer string to date on pattern", e);
		}
		return dt;
	}

	/**
	 * 时间按样式转字符
	 * @author 朱飞
	 * @creationDate. 2016-11-4 下午2:43:20 
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String date2string(Date date, String formatStr) {
		if (date == null) {
			return null;
		}
		String sDate = null;
		try{
		    SimpleDateFormat format = new SimpleDateFormat(formatStr);
		    sDate = format.format(date);
		}catch (Exception e) {
           writeLog(log, null, "Failed to format the date", e);
        }
		return sDate;
	}

	/**
	 * 处理异常日志显示
	 * 
	 * @author 朱飞
	 * @creationDate. 2016-10-4 下午2:02:57
	 * @param log
	 *            日志对象
	 * @param level
	 *            日志级别
	 * @param msg
	 *            日志内容
	 * @param e
	 *            异常对象
	 */
	public static void writeLog(Logger log, Level level, String msg, Exception e) {
		String content = null;
		if (e != null) {
			content = "\n\n" + msg + ",error:" + e.toString() + ",msg:"
					+ e.getMessage() + "\n\n";
		} else {
			content = "\n\n" + msg + "\n\n";
		}
		if(level == null){
		    level = Level.WARN;
		}
		if (level == Level.WARN) {
			log.warn(content, e);
		} else if (level == Level.INFO) {
			log.info(content, e);
		} else if (level == Level.ERROR) {
			log.error(content, e);
		} else if (level == Level.FATAL) {
			log.fatal(content, e);
		} else {
			log.debug(content, e);
		}
	}

	/**
	 * 生成加密参数
	 * @author 朱飞
	 * @creationDate. 2016-11-4 下午2:43:38 
	 * @param map
	 * @param excepts
	 * @return
	 */
	public static String signContentGen(Map<String, Object> map,
			String[] excepts) {
		String result = null;
		StringBuffer sb = new StringBuffer();
		if (map != null) {
			sb = new StringBuffer();
			Set<String> keys = map.keySet();
			if (keys != null) {
				List<String> keyList = new ArrayList<String>(keys);
				Collections.sort(keyList, String.CASE_INSENSITIVE_ORDER);
				List<String> exceptKey = null;
				if (excepts != null && excepts.length > 0) {
					exceptKey = Arrays.asList(excepts);
				}
				for (String key : keyList) {
					if (exceptKey != null && exceptKey.contains(key)) {
						continue;
					}
					if (map.get(key) != null
							&& !map.get(key).toString().isEmpty()) {
						sb.append("&").append(key).append("=")
								.append(map.get(key));
					}
				}
				if (sb.length() > 0 && sb.toString().startsWith("&")) {
					sb = sb.deleteCharAt(0);
					result = sb.toString();
				}
			}
		}
		return result;
	}

	/**
	 * 读properties配置文件
	 * @author 朱飞
	 * @creationDate. 2016-11-4 下午2:43:53 
	 * @param propertiesName
	 * @param key
	 * @return
	 */
	public static Object readProperties(String propertiesName, String key) {
		Object content = null;
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(propertiesName);
			content = bundle.getObject(key);
		} catch (Exception e) {
		    writeLog(log, null, "Failed to read properties on file:"+propertiesName+",key:"+key, e);
		}
		return content;
	}

	/**
	 * 对象转map
	 * @author 朱飞
	 * @creationDate. 2016-11-4 下午2:44:08 
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> obj2map(Object obj) {
		Map<String, Object> map = null;
		try {
			String base = null;
			if(!(obj instanceof String)){
				JSONObject json = new JSONObject(obj);
				base = json.toString();
			}else{
				base = obj.toString();
			}
			map = JSON.parseObject(base,
					new TypeReference<Map<String, Object>>() {
					});
		} catch (Exception e) {
			writeLog(log, Level.WARN, "Failed to transfer obj to map", e);
		}
		return map;
	}

	/**
	 * map中移除null值
	 * @author 朱飞
	 * @creationDate. 2016-11-4 下午2:44:17 
	 * @param map
	 * @return
	 */
	public static Map<String, Object> removeNullValue(Map<String, Object> map) {
		if (map != null) {
			Set<String> set = map.keySet();
	        for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {  
	           String key = iterator.next();
	           if(key == null || key.isEmpty()){
	        	   iterator.remove();
	           }
	           if(map.get(key) == null){
	        	   iterator.remove();
	           }
	        }			
		}
		return map;
	}
	
	/**
	 * 处理double只保留2位小数
	 * @author 朱飞
	 * @creationDate. 2016-11-21 下午1:14:03 
	 * @param dt
	 * @return
	 */
	public static double dealWithDouble(Double dt){
	    double ret = 0;
	    try {
	    	if(dt!=null){
	    		DecimalFormat df = new DecimalFormat("#.##");
	 	        ret = Double.parseDouble(df.format(dt));
	    	}
        } catch (Exception e) {
            writeLog(log, Level.WARN, "Failed to change double date on '.##' style", e);
        }
	    return ret;
	}
	
	/**
	 * 以分计数人民币
	 * @author 朱飞
	 * @creationDate. 2016-11-21 上午9:55:16 
	 * @param money
	 * @return
	 */
	public static long moneyOnCent(Double money){
	    long ret = 0l;
	    try {
            double last = dealWithDouble(money);
            ret = (long)(last * 100);
        } catch (Exception e) {
            writeLog(log, Level.WARN, "Failed to change money base on cent", e);
        }
	    return ret;
	}
	
	/**
	 * 
	 * @author 朱飞
	 * @creationDate. 2016-12-13 下午3:56:08 
	 * @param size
	 * @return
	 */
	public static String getRandowCode(int size) {
        if(size<6){
            String result = generateRandom(size, false);
            return result;
        }
        String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z" };
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        int idx = uuid.length()/size;
        for (int i = 0; i < size; i++) {
            String str = uuid.substring(i * idx, (i+1) * idx);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }
	
	/**
	 * 生成随机数
	 * @author 朱飞
	 * @creationDate. 2016-11-21 下午1:19:41 
	 * @param size
	 * @param numOnly
	 * @return
	 */
	public static String generateRandom(int size, boolean numOnly) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        String base = null;
        if (numOnly) {
            base = "1234567890";
        } else {
            base = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        }
        for (int i = 0; i < size; i++) {
            int degree = random.nextInt() % (base.length());
            sb.append(base.charAt(Math.abs(degree)));
        }
        return sb.toString();
    }
	
	/**
	 * 对象转字符串
	 * @author 朱飞
	 * @creationDate. 2016-12-13 下午3:56:02 
	 * @param obj
	 * @return
	 */
	public static String obj2String(Object obj){
	    String ret = null;
	    try {
            Object json = com.alibaba.fastjson.JSONObject.toJSON(obj);
            ret = json.toString();
        } catch (Exception e) {
           writeLog(log, null, "Failed to transfer obj to string", e);
        }
	    return ret;
	}
	
	/**
	 * 字符转列表
	 * @author 朱飞
	 * @creationDate. 2016-12-13 下午3:55:56 
	 * @param str
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> json2List(String str, Class<T> clazz) {
		List<T> list = null;
		if (str != null && !str.isEmpty()) {
			try {
				list = com.alibaba.fastjson.JSONObject.parseArray(str, clazz);
			} catch (Exception e) {
				writeLog(log, null, "Failed to parse string to list", e);
			}
		}
		return list;
	}
	
	public static <T> T json2Obj(String str, Class<T> clazz) {
		T obj = null;
		if (str != null && !str.isEmpty()) {
			if (str.startsWith("{") && str.endsWith("}")) {
				try {
					obj = com.alibaba.fastjson.JSONObject.parseObject(str,
							clazz);
				} catch (Exception e) {
					writeLog(log, null, "Failed to transfer string to obj", e);
				}
			}
		}
		return obj;
	}
	
	public static Date dateDelay(Date dt,int field,int amount){
		Calendar ca = Calendar.getInstance();
		ca.setTime(dt);
		ca.add(field, amount);
		return ca.getTime();
	}
}

/**
 * 生成订单号的内部类
 * 
 * @author 朱飞<br/>
 *         [2016-9-27上午10:41:14] 新建 </p> <b>修改历史：</b><br/>
 *         </li> </ul>
 */
class SheetCodeGenerate {
	private String tag;// 标签
	private int count;// 计数

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
