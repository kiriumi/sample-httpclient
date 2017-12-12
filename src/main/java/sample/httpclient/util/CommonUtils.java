package sample.httpclient.util;

public class CommonUtils {

	/**
	 * コンストラクタ
	 */
	private CommonUtils() {

	}

	/**
	 * クラス名を取得する
	 *
	 * @return クラス名
	 */
	public static String getClassName() {
		return Thread.currentThread().getStackTrace()[2].getClassName();
	}

	/**
	 * クラス名を取得する
	 *
	 * @return クラス名
	 */
	public static String getSimpleClassName() {
		return Thread.currentThread().getStackTrace()[2].getClass().getSimpleName();
	}
}
