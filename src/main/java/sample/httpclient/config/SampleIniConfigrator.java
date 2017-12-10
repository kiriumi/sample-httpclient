package sample.httpclient.config;

import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.SubnodeConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

/**
 * Iniファイルの読込みクラス
 *
 * @author Kengo
 *
 */
public class SampleIniConfigrator {

	public static void main(String[] args) throws ConfigurationException {

		Configurations configs = new Configurations();

		INIConfiguration iniConfig = configs.ini("sample.ini");
		SubnodeConfiguration section = iniConfig.getSection("Section2");
		String value = section.getString("sample");

		System.out.println(value);

	}

}
