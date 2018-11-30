package com.wjz.http;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public abstract class HttpHelper {
	
	public static String request() {
		CloseableHttpClient client = HttpClients.createDefault();
		return null;
	}

}
