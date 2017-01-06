package cn.bestsign.sdk.integration.utils.http;

import cn.bestsign.sdk.integration.Logger;

public class HttpLogger {
	public static void addToLog(Object message) {
		Logger.addToLog(message);
	}

	public static boolean isDebug() {
		return Logger.getDebugLevel() != Logger.DEBUG_LEVEL.NONE;
	}
}
