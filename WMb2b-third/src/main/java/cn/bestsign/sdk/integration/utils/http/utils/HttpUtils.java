package cn.bestsign.sdk.integration.utils.http.utils;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import cn.bestsign.sdk.integration.utils.http.HttpLogger;
import cn.bestsign.sdk.integration.utils.http.vo.PostFile;

public class HttpUtils {
	/**
	 * 发送请求
	 *
	 * @param conn
	 * @param sendData
	 * @throws IOException
	 * @throws BadTypeException
	 */
	public static void send(HttpURLConnection conn, Object sendData) throws IOException {
		if (sendData == null) {
			return;
		}
		DataOutputStream dataOutputStream = new DataOutputStream(conn.getOutputStream());
		try {
			uploadData(dataOutputStream, sendData);
		} catch (IOException e) {
			throw e;
		} finally {
			dataOutputStream.close();
		}
	}

	/**
	 * 发送请求
	 *
	 * @param conn
	 * @param boundary
	 * @param sendData
	 * @param sendFiles
	 * @throws IOException
	 */
	public static void send(HttpURLConnection conn, String boundary, Map<String, Object> sendData, Map<String, Object> sendFiles) throws IOException {
		DataOutputStream dataOutputStream = new DataOutputStream(conn.getOutputStream());

		try {
			boolean isSend = false;

			if (sendData != null && sendData.size() > 0) {
				for (String sendName : sendData.keySet()) {
					Object sendValue = sendData.get(sendName);
					uploadData(dataOutputStream, boundary, sendName, sendValue);
					isSend = true;
				}
			}

			if (sendFiles != null && sendFiles.size() > 0) {
				for (String sendName : sendFiles.keySet()) {
					Object sendValue = sendFiles.get(sendName);
					uploadFile(dataOutputStream, boundary, sendName, sendValue);
					isSend = true;
				}
			}

			if (isSend) {
				dataOutputStream.writeBytes(boundary + "--\r\n");
			}

		} catch (IOException e) {
			throw e;
		} finally {
			dataOutputStream.close();
		}
	}

	/**
	 * uploadData
	 *
	 * @param dataOutputStream
	 * @param sendData
	 * @throws IOException
	 */
	private static void uploadData(DataOutputStream dataOutputStream, Object sendData) throws IOException {
		if (sendData instanceof byte[]) {
			dataOutputStream.write((byte[]) sendData);
			HttpLogger.addToLog(new String((byte[])sendData, "UTF-8"));
		} else {
			dataOutputStream.writeBytes((String) sendData);
			HttpLogger.addToLog(sendData);
		}
	}

	/**
	 * uploadData
	 *
	 * @param dataOutputStream
	 * @param sendName
	 * @param sendData
	 * @throws IOException
	 */
	private static void uploadData(DataOutputStream dataOutputStream, String boundary, String sendName, Object sendData) throws IOException {
		if (sendData == null) {
			return;
		}
		String sendValue;
		if (boundary == null || boundary.length() == 0) {

			if (sendData instanceof byte[]) {
				sendValue = urlencode(sendName) + "=" + urlencode(new String((byte[]) sendData));
				dataOutputStream.writeBytes(sendValue);
				HttpLogger.addToLog(sendValue);
			} else if (sendData instanceof String) {
				sendValue = urlencode(sendName) + "=" + urlencode((String) sendData);
				dataOutputStream.writeBytes(sendValue);
				HttpLogger.addToLog(sendValue);
			} else {
				HttpLogger.addToLog("sendData type not support, type is " + sendData.getClass().getName());
				HttpLogger.addToLog(sendData);
			}
		} else {
			if (sendData instanceof byte[]) {
				sendValue = boundary + "\r\n";
				dataOutputStream.writeBytes(sendValue);
				HttpLogger.addToLog(sendValue);

				sendValue = "Content-Disposition: form-data; name=\"" + urlencode(sendName) + "\"\r\n\r\n";
				dataOutputStream.writeBytes(sendValue);
				HttpLogger.addToLog(sendValue);

				dataOutputStream.write((byte[]) sendData);
				if (HttpLogger.isDebug()) {
					HttpLogger.addToLog(JSONObject.toJSONString(sendData));
				}
				dataOutputStream.writeBytes("\r\n");
				HttpLogger.addToLog("\r\n");
			} else if (sendData instanceof String) {
				sendValue = boundary + "\r\n";
				dataOutputStream.writeBytes(sendValue);
				HttpLogger.addToLog(sendValue);

				sendValue = "Content-Disposition: form-data; name=\"" + urlencode(sendName) + "\"\r\n\r\n";
				dataOutputStream.writeBytes(sendValue);
				HttpLogger.addToLog(sendValue);

				dataOutputStream.writeBytes((String) sendData);
				HttpLogger.addToLog(sendData);

				dataOutputStream.writeBytes("\r\n");
				HttpLogger.addToLog("\r\n");
			} else {
				HttpLogger.addToLog("sendData type not support, type is " + sendData.getClass().getName());
				HttpLogger.addToLog(sendData);
			}
		}
	}

	/**
	 * uploadFile
	 *
	 * @param dataOutputStream
	 * @param boundary
	 * @param sendName
	 * @param sendFile
	 * @throws IOException
	 */
	private static void uploadFile(DataOutputStream dataOutputStream, String boundary, String sendName, Object sendFile) throws IOException {
		if (sendFile == null) {
			return;
		}

		String fileName = null;
		String contentType = null;
		DataInputStream postStream = null;

		if (sendFile instanceof File) {
			fileName = ((File) sendFile).getName();
			Files.probeContentType(Paths.get(((File) sendFile).getPath()));
			postStream = new DataInputStream(new FileInputStream((File) sendFile));
		} else if (sendFile instanceof PostFile) {
			fileName = ((PostFile) sendFile).getName();
			contentType = ((PostFile) sendFile).getContentType();
			postStream = new DataInputStream(new ByteArrayInputStream(((PostFile) sendFile).getData()));
		} else if (sendFile instanceof byte[]) {
			postStream = new DataInputStream(new ByteArrayInputStream((byte[]) sendFile));
		} else {
			HttpLogger.addToLog("sendFile type not support, type is " + sendFile.getClass().getName());
			HttpLogger.addToLog(sendFile);
			return;
		}

		// get a default file name
		if (null == fileName || fileName.length() < 1) {
			fileName = sendName;
		}

		// get a default content-type
		if (null == contentType || contentType.length() < 1) {
			String extName = "";
			int a = fileName.lastIndexOf(".");
			if (a > 0) {
				extName = fileName.substring(a + 1).trim().toLowerCase();
				// if (Constants.MIME_LIST.containsKey(extName)) {
				// contentType = Constants.MIME_LIST.get(extName);
				// }
				contentType = "application/octet-stream-" + extName;
			}
			if (null == contentType) {
				contentType = "application/octet-stream";
			}
		}

		String sendValue = null;

		sendValue = boundary + "\r\n";
		dataOutputStream.writeBytes(sendValue);
		HttpLogger.addToLog(sendValue);

		sendValue = "Content-Disposition: form-data; name=\"" + urlencode(sendName) + "\"; filename=\"" + urlencode(fileName) + "\"\r\n";
		dataOutputStream.writeBytes(sendValue);
		HttpLogger.addToLog(sendValue);

		sendValue = "Content-Type: " + contentType + "\r\n\r\n";
		dataOutputStream.writeBytes(sendValue);

		HttpLogger.addToLog("Upload Stream......");
		byte[] buffer = new byte[4096];
		int read = 0;
		int total = 0;
		try {
			while ((read = postStream.read(buffer)) > 0) {
				dataOutputStream.write(buffer, 0, read);
				total += read;
			}
			HttpLogger.addToLog("Uploaded " + total + " Bytes!");
		} catch (IOException e) {
			HttpLogger.addToLog("Upload Stream IOException " + e.getMessage());
			throw e;
		} finally {
			HttpLogger.addToLog("Upload Stream End");
			postStream.close();
		}

		dataOutputStream.writeBytes("\r\n");
		HttpLogger.addToLog("\r\n");
	}

	/**
	 * urlencode
	 *
	 * @param data
	 * @return
	 */
	private static String urlencode(String data) {
		try {
			return URLEncoder.encode(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return data;
		}
	}
}
