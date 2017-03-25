package cn.slimsmart.fitnesse.feature.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * fitnesse http feature
 * 
 * @author slim
 */
public class HttpClient {

	public final static String CHARSET_UTF8 = "utf-8";
	protected OkHttpClient client = null;
	public static final MediaType JSON = MediaType
			.parse("application/json; charset=utf-8");

	public HttpClient() {
		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		builder.connectionPool(new ConnectionPool(200, 3, TimeUnit.MINUTES));
		builder.connectTimeout(2, TimeUnit.SECONDS);
		builder.readTimeout(30, TimeUnit.SECONDS);
		builder.writeTimeout(1, TimeUnit.MINUTES);
		builder.retryOnConnectionFailure(true);
		client = builder.build();
	}

	public String get(String requestUrl) {
		return get(requestUrl, null);
	}

	public int getResponseCode(String requestUrl) {
		return getResponseCode(requestUrl, null);
	}

	public String get(String requestUrl, Map<String, String> params) {
		return get(requestUrl, null, params);
	}

	public int getResponseCode(String requestUrl, Map<String, String> params) {
		return getResponseCode(requestUrl, null, params);
	}

	public String get(String requestUrl, Map<String, String> headers,
			Map<String, String> params) {
		Response response = getResponse(requestUrl, headers, params);
		if (response != null) {
			try {
				return response.body().string();
			} catch (IOException e) {
			}
		}
		return "";
	}

	public int getResponseCode(String requestUrl, Map<String, String> headers,
			Map<String, String> params) {
		Response response = getResponse(requestUrl, headers, params);
		if (response != null) {
			return response.code();
		}
		return -1;
	}

	private Response getResponse(String requestUrl,
			Map<String, String> headers, Map<String, String> params) {
		try {
			Request.Builder requestBuilder = new Request.Builder();
			if (headers != null && !headers.isEmpty()) {
				for (Entry<String, String> entry : headers.entrySet()) {
					if (entry.getValue() != null
							&& entry.getValue().trim().length() > 0) {
						requestBuilder.addHeader(entry.getKey(), entry
								.getValue().trim());
					}
				}
			}
			StringBuffer urlBuffer = new StringBuffer();
			urlBuffer.append(requestUrl);
			if (params != null && !params.isEmpty()) {
				if (!requestUrl.contains("?")) {
					urlBuffer.append("?");
				} else {
					if (!requestUrl.endsWith("?") && !requestUrl.endsWith("&")) {
						urlBuffer.append("&");
					}
				}
				urlBuffer.append(paramsToString(params));
			}
			requestBuilder.url(urlBuffer.toString());
			return client.newCall(requestBuilder.build()).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String paramsToString(Map<String, String> params)
			throws UnsupportedEncodingException {
		StringBuffer paramsBuffer = new StringBuffer();
		for (Entry<String, String> entry : params.entrySet()) {
			if (entry.getValue() != null
					&& entry.getValue().trim().length() > 0) {
				paramsBuffer
						.append(entry.getKey())
						.append("=")
						.append(URLEncoder.encode(entry.getValue().trim(),
								CHARSET_UTF8));
				paramsBuffer.append("&");
			}
		}
		return paramsBuffer.length() > 0 ? paramsBuffer.substring(0,
				paramsBuffer.length() - 1) : "";
	}

	private Response postResponse(String requestUrl,
			Map<String, String> headers, Map<String, String> params) {
		try {
			FormBody.Builder formBuilder = new FormBody.Builder();
			if (params != null && !params.isEmpty()) {
				for (Entry<String, String> entry : params.entrySet()) {
					formBuilder.addEncoded(entry.getKey(), entry.getValue());
				}
			}
			Request.Builder requestBuilder = new Request.Builder().url(
					requestUrl).post(formBuilder.build());
			if (headers != null && !headers.isEmpty()) {
				for (Entry<String, String> entry : headers.entrySet()) {
					if (entry.getValue() != null
							&& entry.getValue().trim().length() > 0) {
						requestBuilder.addHeader(entry.getKey(), entry
								.getValue().trim());
					}
				}
			}
			return client.newCall(requestBuilder.build()).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String post(String requestUrl,Map<String, String> headers, Map<String, String> params){
		Response response = postResponse(requestUrl, headers, params);
		if (response != null) {
			try {
				return response.body().string();
			} catch (IOException e) {
			}
		}
		return "";
	}
	public int postResponseCode(String requestUrl, Map<String, String> headers,
			Map<String, String> params) {
		Response response = postResponse(requestUrl, headers, params);
		if (response != null) {
			return response.code();
		}
		return -1;
	}
	
	public String post(String requestUrl, Map<String, String> params){
		return post(requestUrl, null, params);
	}
	public int postResponseCode(String requestUrl,Map<String, String> params) {
		return postResponseCode(requestUrl, null, params);
	}

	private Response postBodyResponse(String requestUrl,
			Map<String, String> headers, String body) {
		try {
			RequestBody requestBody = RequestBody.create(JSON, body);
			Request.Builder requestBuilder = new Request.Builder().url(
					requestUrl).post(requestBody);

			if (headers != null && !headers.isEmpty()) {
				for (Entry<String, String> entry : headers.entrySet()) {
					if (entry.getValue() != null
							&& entry.getValue().trim().length() > 0) {
						requestBuilder.addHeader(entry.getKey(), entry
								.getValue().trim());
					}
				}
			}
			return client.newCall(requestBuilder.build()).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String post(String requestUrl,Map<String, String> headers,String body){
		Response response = postBodyResponse(requestUrl, headers, body);
		if (response != null) {
			try {
				return response.body().string();
			} catch (IOException e) {
			}
		}
		return "";
	}
	public int postResponseCode(String requestUrl, Map<String, String> headers,String body) {
		Response response = postBodyResponse(requestUrl, headers, body);
		if (response != null) {
			return response.code();
		}
		return -1;
	}
	
	public String post(String requestUrl, String body){
		return post(requestUrl, null, body);
	}
	public int postResponseCode(String requestUrl,String body) {
		return postResponseCode(requestUrl, null, body);
	}
	
	
}
