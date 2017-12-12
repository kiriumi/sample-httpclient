package sample.httpclient.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

public final class LogUtils {

	/** ロガー */
	private static Logger logger = LogManager.getLogger();

	/**
	 * コンストラクタ
	 */
	private LogUtils() {

	}

	/**
	 * ログレベルを設定する
	 * @param level ログレベル
	 */
	public static void setLogLevel(Level level) {

		LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);
		Configuration configuration = loggerContext.getConfiguration();
		LoggerConfig loggerConfig = configuration.getLoggerConfig(CommonUtils.getClassName());
		loggerConfig.setLevel(level);
		loggerContext.updateLoggers();

		logger = LogManager.getLogger();
	}

	/**
	 * デバッグログの出力
	 *
	 * @param messageId メッセージID
	 * @param params パラメータ
	 * @throws ConfigurationException 設定ファイルの読込み例外
	 */
	public static void debug(String messageId, Object... params) throws ConfigurationException {
		logger.debug(getLogMessage(messageId), params);
	}

	/**
	 * 情報ログの出力
	 *
	 * @param messageId メッセージID
	 * @param params パラメータ
	 * @throws ConfigurationException 設定ファイルの読込み例外
	 */
	public static void info(String messageId, Object... params) throws ConfigurationException {
		logger.info(getLogMessage(messageId), params);
	}

	/**
	 * 警告ログの出力
	 *
	 * @param messageId メッセージID
	 * @param params パラメータ
	 * @throws ConfigurationException 設定ファイルの読込み例外
	 */
	public static void warn(String messageId, Object... params) throws ConfigurationException {
		logger.warn(getLogMessage(messageId), params);
	}

	/**
	 * エラーログの出力
	 *
	 * @param messageId メッセージID
	 * @param params パラメータ
	 * @throws ConfigurationException 設定ファイルの読込み例外
	 */
	public static void error(String messageId, Object... params) throws ConfigurationException {

		for (Object param : params) {

			if (param instanceof Throwable) {
				Throwable e = (Throwable) param;
				param = getFullStackTrace(e);
			}
		}

		logger.error(getLogMessage(messageId), params);
	}
	/**
	 * フルスタックトレースの取得
	 *
	 * @param e 例外
	 * @return フルスタックトレース
	 */
	public static String getFullStackTrace(Throwable e) {

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		pw.flush();

		return sw.toString();
	}

	/**
	 * ログメッセージの取得
	 *
	 * @param messageId メッセージID
	 * @param params パラメータ
	 * @throws ConfigurationException 設定ファイルの読込み例外
	 */
	private static String getLogMessage(String messageId) throws ConfigurationException {
		return MessageUtils.getMessage(messageId);
	}


}
