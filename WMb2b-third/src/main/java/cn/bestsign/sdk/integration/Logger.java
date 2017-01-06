package cn.bestsign.sdk.integration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.alibaba.fastjson.JSONObject;

public class Logger {
	private static String logDir = loadDefaultLogDir();
	private static DEBUG_LEVEL debugLevel = DEBUG_LEVEL.INFO;
	
	public enum DEBUG_LEVEL {
		NONE, DEBUG, INFO
	}
	
	public static String getLogDir() {
		return logDir;
	}
	
	public static void setLogDir(String path) {
		File d = new File(path);
		if (!d.exists() || !d.isDirectory()) {
			return;
		}
		logDir = d.getAbsolutePath() + File.separator;
	}
	
	public static DEBUG_LEVEL getDebugLevel() {
		return debugLevel;
	}
	
	public static void setDebugLevel(DEBUG_LEVEL level) {
		debugLevel = level;
	}
	
	public static void addToLog(Object message) {
		if (debugLevel == DEBUG_LEVEL.NONE) {
			return;
		}
		
		int max = 1024;
		String split = "......";
		int splitLen = split.length();
		
		String log = "";
		if (message instanceof String) {
			log = (String) message;
			if (debugLevel == DEBUG_LEVEL.DEBUG) {
				writeLog(log);
			}
			else {
				if (log.length() > max) {
					log = log.substring(0, max - splitLen) + split;
				}
				writeLog(log);
			}
		}
		else if (message instanceof Integer) {
			log = message.toString();
			writeLog(log);
		}
		else if (message instanceof Long) {
			log = message.toString();	
			writeLog(log);
		}
		else if (message instanceof Float) {
			log = message.toString();
			writeLog(log);
		}
		else if (message instanceof Double) {
			log = message.toString();
			writeLog(log);
		}
		else if (message instanceof BigDecimal) {
			log = message.toString();
			writeLog(log);
		}
		else if (message instanceof Boolean) {
			log = message.toString();
			writeLog(log);
		}
		else if (message instanceof byte[]) {
			byte[] data = (byte[]) message;
			byte[] buffer = null;
			if (data.length > 256) {
				if (debugLevel == DEBUG_LEVEL.INFO) {
					buffer = new byte[256];
				}
				else {
					buffer = new byte[data.length];
				}
			}
			else {
				buffer = new byte[data.length];
			}
			System.arraycopy(data, 0, buffer, 0, buffer.length);
			
			StringBuffer line = new StringBuffer();
			for (int i = 0; i < buffer.length; i++) {
				byte b = buffer[i];
				int n = ((int) b) & 0xff;
				String hex = Integer.toHexString(n);
				if (hex.length() < 2) {
					hex = "0" + hex;
				}
				
				line.append(hex);
				if ((i + 1) % 16 == 0) {
					log = line.toString();
					line.delete(0, line.length());
					writeLog(log);
				}
				else {
					line.append(" ");
				}
			}
		}
		else {
			log = JSONObject.toJSONString(message);
			if (debugLevel == DEBUG_LEVEL.DEBUG) {
				writeLog(log);
			}
			else {
				if (log.length() > max) {
					log = log.substring(0, max - splitLen) + split;
				}
				writeLog(log);
			}
		}
	}
	
	private synchronized static void writeLog(String log) {
		String now = getNow("yyyy-MM-dd HH:mm:ss");
		String logFilePath = getLogFile();
		OutputStreamWriter outputStreamWriter = null;
		try {
			if (Constants.ENV_CHARSET == null || Constants.ENV_CHARSET.length() < 1) {
				outputStreamWriter = new OutputStreamWriter(new FileOutputStream(logFilePath, true));
			}
			else {
				outputStreamWriter = new OutputStreamWriter(new FileOutputStream(logFilePath, true), Constants.ENV_CHARSET);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		try {
			outputStreamWriter.write("[" + now + "] " + log + "\n");
			outputStreamWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStreamWriter.close();
			} catch (IOException e) {
				
			}
		}
	}
	
	private static String getLogFile() {
		String logFile = logDir + File.separator + getLogFileName();
		return logFile;
	}
	
	private static String getLogFileName() {
		String now = getNow("yyyyMMdd");
		String logFileName = "bestsign-sdk-logs." + now;
		return logFileName;
	}
	
	private static String getNow(String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		String now = simpleDateFormat.format(new Date());
		return now;
	}
	
	private static String loadDefaultLogDir() {
		String logDir = null;
		Properties props = new Properties();
		try {
			InputStream ins = Logger.class.getClassLoader().getResourceAsStream("wm-config.properties");
			props.load(ins);
			logDir = (String) props.get("ssq_log_dir");
			if (logDir != null && logDir.length() > 0) {
				return logDir;
			}
		} catch (Exception e) {
			logDir = null;
			e.printStackTrace();
		}
		 
		if(logDir == null){
			logDir = System.getProperty("catalina.home");
			if (logDir != null && logDir.length() > 0) {
				return logDir;
			}
			logDir = System.getProperty("java.io.tmpdir");
			if (logDir != null && logDir.length() > 0) {
				return logDir;
			}
		}
		return ".";
	}
	

	
}
