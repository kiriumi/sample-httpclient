package sample.httpclient.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class LogUtils {

	/** ロガー */
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * コンストラクタ
	 */
	private LogUtils() {

	}

	/**
	 * デバッグログの出力
	 *
	 * @param messageId メッセージID
	 * @param params パラメータ
	 * @throws ConfigurationException 設定例外
	 */
	public static void debug(String messageId, Object... params) throws ConfigurationException {
		LOGGER.debug(getLogMessage(messageId), params);
	}

	/**
	 * 情報ログの出力
	 *
	 * @param messageId
	 * @param params
	 * @throws ConfigurationException
	 */
	public static void info(String messageId, Object... params) throws ConfigurationException {
		LOGGER.info(getLogMessage(messageId), params);
	}

	/**
	 * 警告ログの出力
	 *
	 * @param messageId
	 * @param params
	 * @throws ConfigurationException
	 */
	public static void warn(String messageId, Object... params) throws ConfigurationException {
		LOGGER.warn(getLogMessage(messageId), params);
	}

	/**
	 * エラーログの出力
	 *
	 * @param messageId
	 * @param params
	 * @throws ConfigurationException
	 */
	public static void error(String messageId, Object... params) throws ConfigurationException {

		for (Object param : params) {

			if (param instanceof Throwable) {
				Throwable e = (Throwable) param;
				param = getFullStackTrace(e);
			}
		}

		LOGGER.error(getLogMessage(messageId), params);
	}

	/**
	 * ログメッセージの取得
	 *
	 * @param messageId
	 * @return message
	 * @throws ConfigurationException
	 */
	private static String getLogMessage(String messageId) throws ConfigurationException {
		return MessageUtils.getMessage(messageId);
	}

	/**
	 * フルスタックトレースの取得
	 *
	 * @param e
	 * @return fullStackTrace
	 */
	private static String getFullStackTrace(Throwable e) {

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		pw.flush();

		return sw.toString();
	}
}
