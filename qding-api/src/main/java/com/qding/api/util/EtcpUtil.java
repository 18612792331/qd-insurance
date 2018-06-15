package com.qding.api.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class EtcpUtil {

	private static SSLContext sslCtx = null;
	static {
		try {
			sslCtx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] xcs,
						String string) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] xcs,
						String string) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			sslCtx.init(null, new TrustManager[] { tm }, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}

		});
		HttpsURLConnection.setDefaultSSLSocketFactory(sslCtx.getSocketFactory());
	}

	// 设置body体
	public static void setBodyParameter(StringBuilder sb, HttpURLConnection conn)
			throws IOException {
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		out.writeBytes(sb.toString());
		out.flush();
		out.close();
	}

	// 添加签名header
	public static HttpURLConnection CreatePostHttpConnection(String uri) throws MalformedURLException,
			IOException, ProtocolException {
		return CreatePostHttpConnection(uri,"application/x-www-form-urlencoded");
	}

	public static HttpURLConnection CreateJsonPostHttpConnection(String uri) throws MalformedURLException,
			IOException, ProtocolException {
		return CreatePostHttpConnection(uri,"application/json");
	}
	
	public static void setBodyParameter(String str, HttpURLConnection conn)
			throws IOException {
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		out.write(str.getBytes("utf-8"));
		out.flush();
		out.close();
	}
	
	private static HttpURLConnection CreatePostHttpConnection(String uri, String contentType) throws IOException{
		URL url = new URL(uri);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setInstanceFollowRedirects(true);
		conn.setConnectTimeout(30000);
		conn.setReadTimeout(30000);
		conn.setRequestProperty("Content-Type", contentType);
		return conn;
	}
	
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream=null;
		try{
			outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			byte[] data = outStream.toByteArray();
			return data;
		}catch(Exception ex){
			throw ex;
		}finally{
			if(outStream!=null){
				outStream.close();
			}
			if(inStream!=null){
				inStream.close();
			}
		}
	}
	
	public static String getResult(HttpURLConnection conn) throws Exception, IOException{
		InputStream input = null;
		if (conn.getResponseCode() == 200) {
			input = conn.getInputStream();
		} else {
			input = conn.getErrorStream();
		}
		return new String(readInputStream(input), "UTF-8");
	}

	
	 
}
