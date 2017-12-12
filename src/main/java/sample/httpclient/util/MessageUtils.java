package sample.httpclient.util;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

public final  class MessageUtils {

	/** メッセージプロパティファイル */
	private static final String MESSAGE_PROP_FILE = "message.properties";

	/** メッセージ設定 */
	private static Configuration  messageConfig;

	/**
	 * コンストラクタ
	 */
	private MessageUtils() {

	}

	/**
	 * メッセージの取得
	 *
	 * @param messageId メッセージID
	 * @return message メッセージ
	 * @throws ConfigurationException プロパティファイルの読込み例外
	 */
	public static String getMessage(String messageId) throws ConfigurationException {

		if(messageConfig == null) {

			Configurations configs = new Configurations();
			messageConfig = configs.properties(MESSAGE_PROP_FILE);
		}

		return messageConfig.getString(messageId);

	}
}
