package com.example.TestWeb18.util;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author chenyang
 * @date 2018/11/12 10:08
 * @Description HttpClient请求工具类
 */
public class HttpClientUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);

    private static HttpClient httpClient = HttpClientBuilder.create().build();

    /**
     * @param url           请求路径
     * @param params        请求参数
     * @param headerMap     请求头参数
     * @param isPrintLogger 是否打印请求信息
     * @return 响应实体
     */
    public static HttpEntity doGet(String url, List<NameValuePair> params, Map<String, String> headerMap, boolean isPrintLogger) {
        HttpEntity entity = null;
        try {
            if (params != null) {
                String paramStr = EntityUtils.toString(new UrlEncodedFormEntity(params), Consts.UTF_8);
                url += "?" + paramStr;
            }
            HttpGet httpGet = new HttpGet(url);
            if (headerMap != null) {
                Set<Entry<String, String>> entries = headerMap.entrySet();
                for (Entry<String, String> entry : entries) {
                    httpGet.setHeader(entry.getKey(), entry.getValue());
                }
            }
            HttpResponse httpResponse = httpClient.execute(httpGet);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (isPrintLogger) {
                LOGGER.info("请求方式：GET，状态码：statusCode={}，请求连接：URL={}", statusCode, url);
            }
            if (statusCode == 200) {
                entity = httpResponse.getEntity();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entity;
    }

    public static HttpEntity doPost(String url, Map<String, String> map, String charset) {
        CloseableHttpClient httpClient;
        HttpPost httpPost;
        try {
            httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> elem = (Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                return response.getEntity();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HttpEntity doPost(String url, Map<String, String> map, String charset, String authorization) {
        CloseableHttpClient httpClient;
        HttpPost httpPost;
        try {
            httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> elem = (Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
            }
            if (StringUtil.isValid(authorization)) {
                httpPost.setHeader("Authorization",authorization);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                return response.getEntity();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String doGet(String url, Map<String, String> params, String authorization) {
        HttpEntity entity = null;
        Iterator iterator = params.entrySet().iterator();
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
        while (iterator.hasNext()) {
            Entry<String, String> entry = (Entry) iterator.next();
            list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        try {
            if (params != null) {
                String paramStr = EntityUtils.toString(new UrlEncodedFormEntity(list), Consts.UTF_8);
                url += "?" + paramStr;
            }
            HttpGet httpGet = new HttpGet(url);
            if (StringUtil.isValid(authorization)) {
                httpGet.setHeader("Authorization",authorization);
            }
            HttpResponse httpResponse = httpClient.execute(httpGet);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                entity = httpResponse.getEntity();
                return EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String doPost(String url, Map<String, String> map) {
        CloseableHttpClient httpClient;
        HttpPost httpPost;
        String result = null;
        try {
            httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> elem = (Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = null;
            if (response != null) {
                entity = response.getEntity();
                result = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
    public static HttpClient getTLSProtocolHttpClient(String Protocol) throws Exception{
    	HttpClient httpclient  = null;
    	if(Protocol==null||Protocol.isEmpty()){
    		Protocol = "TLSv1";//JAVE 7 默认
    	}
    	// 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
    	X509TrustManager trustManager = new X509TrustManager() {
    		@Override
    		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    		}
    		
    		@Override
    		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    		}
    		
    		@Override
    		public X509Certificate[] getAcceptedIssuers() {
    			return null;
    		}
    		
    	};
    	
		try {
			SSLContext sc = SSLContext.getInstance(Protocol);
			sc.init(null, new TrustManager[] { trustManager }, null);
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sc);
			httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			return httpclient;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Get HttpClient failed!");
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Get HttpClient failed!");
		}
    }

    public static String doGetForMiddleWare(String url, Map<String, String> params) {
        HttpEntity entity;
        try {
            Iterator iterator = params.entrySet().iterator();
            String firstParamStr = "";
            String paramStr = "";

            while (iterator.hasNext()) {
                Entry<String, String> entry = (Entry) iterator.next();
                if ("apiKey".equals(entry.getKey())) {
                    firstParamStr = entry.getKey() + "=" + entry.getValue();
                } else {
                    paramStr = paramStr + "&" + "param" + "=" + entry.getKey() + "=" + entry.getValue();
                }

            }
            url += "?" + firstParamStr + paramStr;
            HttpGet httpGet;
            httpGet = new HttpGet(url);

            HttpResponse httpResponse = httpClient.execute(httpGet);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                entity = httpResponse.getEntity();
                return EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
