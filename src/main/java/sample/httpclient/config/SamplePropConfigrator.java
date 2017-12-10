package sample.httpclient.config;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

/**
 * Propertiesファイルの読込みクラス
 * @author Kengo
 *
 */
public class SamplePropConfigrator {

	public static void main(String[] args) throws ConfigurationException {

		Configurations configs = new Configurations();
		Configuration  propConfig = configs.properties("sample.properties");
		String value = propConfig.getString("sample.key");

		System.out.println(value);

	}

}
