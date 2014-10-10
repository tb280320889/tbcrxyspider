/**
 * IntelliJ IDEA
 * Created by 280320889@qq.com
 * Date : 14-9-30
 * Time : 上午11:02
 */

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

public class Testtb {

	HttpClient httpclient = new DefaultHttpClient();

	public static void main(String[] args) throws Exception {

		Testtb t = new Testtb();
		t.init();
		t.queryData();
	}

	private void init() {
		HttpHost proxy = new HttpHost("172.168.1.110", 8080, "http");
		httpclient = wrapClient(httpclient);
		httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
				proxy);
		httpclient.getParams()
				.setIntParameter("http.socket.timeout", 30 * 1000);
	}

	/**
	 *
	 * @throws Exception
	 */
	private void queryData() throws Exception {
		HttpGet httpget = new HttpGet("https://localhost/test.jsp");
		HttpResponse response = httpclient.execute(httpget);

		System.out.println("Login form get: " + response.getStatusLine());
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity);
		System.out.println(result);
		EntityUtils.consume(entity);

	}

	/**
	 * 获取可信任https链接，以避免不受信任证书出现peer not authenticated异常
	 *
	 * @param base
	 * @return
	 */
	public static HttpClient wrapClient(HttpClient base) {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] xcs,
											   String string) {
				}

				public void checkServerTrusted(X509Certificate[] xcs,
											   String string) {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			ctx.init(null, new TrustManager[] { tm }, null);
			SSLSocketFactory ssf = new SSLSocketFactory(ctx);
			ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			ClientConnectionManager ccm = base.getConnectionManager();
			SchemeRegistry sr = ccm.getSchemeRegistry();
			sr.register(new Scheme("https", ssf, 443));
			return new DefaultHttpClient(ccm, base.getParams());
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}