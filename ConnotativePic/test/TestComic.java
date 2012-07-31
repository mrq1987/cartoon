

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

public class TestComic {
	public static void main(String[] args) throws Exception, IOException {
		HttpClient client = new HttpClient();
		HttpMethod method = null;
		for(int i=0;i<1000;i++){
			method = new GetMethod("http://localhost/ConnotativePic/comic?sno=1");
			client.executeMethod(method);
			System.out.println(method.getStatusLine());
			method.releaseConnection();
		}
	}
}
