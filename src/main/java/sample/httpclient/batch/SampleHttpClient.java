package sample.httpclient.batch;

import java.io.File;
import java.io.IOException;

import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.Level;

import sample.httpclient.util.LogUtils;

/**
 * HttpClientのサンプルクラス
 *
 * @author Kengo
 *
 */
public class SampleHttpClient {

	public static void main(String[] args) throws ClientProtocolException, IOException, ConfigurationException {

		LogUtils.setLogLevel(Level.DEBUG);

		LogUtils.debug("sample");

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = createHttpPostWithPart();

		CloseableHttpResponse response = httpClient.execute(httpPost);

		StatusLine statusLine = response.getStatusLine();
		System.out.println(statusLine.getStatusCode());
		System.out.println(statusLine);

		response.close();

	}


	/**
	 * Partを追加したHttpPostの生成.
	 *
	 * @return HTTPポスト
	 */
	private static HttpPost createHttpPostWithPart() {

		HttpPost httpPost = new HttpPost("http://localhost:8080/sample-tomcat/hello");

		// Partの追加
		File file = new File("./misc/uploadFile.txt");

		FileBody fileBody = new FileBody(file, ContentType.DEFAULT_BINARY);
		StringBody stringBody1 = new StringBody("Message 1", ContentType.MULTIPART_FORM_DATA);
		StringBody stringBody2 = new StringBody("Message 2", ContentType.MULTIPART_FORM_DATA);

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addPart("upfile", fileBody);
		builder.addPart("text1", stringBody1);
		builder.addPart("text2", stringBody2);

		HttpEntity entity = builder.build();
		httpPost.setEntity(entity);

		return httpPost;
	}

}
