/*
 * @(#)HttpClientBO.java $version 2014. 8. 13.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.bo;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author younghan
 */
@Service
public class HttpClientBO {
	private static final Logger LOG = LoggerFactory.getLogger(HttpClientBO.class);
	
	public String getHttpBody(String url, String method, List<NameValuePair> param)	{
		HttpClient httpClient = null;
		HttpResponse httpResponse = null;
		HttpRequestBase httpRequest;
	    
		try	{
			if(StringUtils.upperCase(method).equals("POST"))	{
				httpRequest = new HttpPost(url);
				((HttpPost)httpRequest).setEntity(new UrlEncodedFormEntity(param));
			} else	{				
				httpRequest = new HttpGet(url);
			}
			
			TrustManager[] trustManagers =  new TrustManager[1];
			trustManagers[0] = new DefaultTrustManager();

			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(new KeyManager[0], trustManagers, new SecureRandom());
			SSLContext.setDefault(sslContext);

			sslContext.init(null, trustManagers, null);
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,
			        SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			httpClient = HttpClients.custom()
			        .setSSLSocketFactory(sslsf)
			        .build();
	    
//			httpClient = HttpClientBuilder.create().build();
			httpResponse = httpClient.execute(httpRequest);
			return EntityUtils.toString(httpResponse.getEntity());
		}	catch (ClientProtocolException e)	{
			LOG.error("Client protocol error : ", e);
		}	catch (IOException e) { 
			LOG.error("IO error : ", e);
		} catch (KeyManagementException e) {
			LOG.error("IO error : ", e);
		} catch (NoSuchAlgorithmException e) {
			LOG.error("IO error : ", e);
		}	finally	{
			//예외 먹음
			HttpClientUtils.closeQuietly(httpResponse);
			HttpClientUtils.closeQuietly(httpClient);
		}
		
		return null;
	}
	
	public String getHttpBody(String url)	{
		return getHttpBody(url, "GET", null);
	}
	
	class DefaultTrustManager implements X509TrustManager{
	    void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

	    }

	    void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

	    }

		/**
		 * @param chain
		 * @param authType
		 * @throws CertificateException
		 * @see javax.net.ssl.X509TrustManager#checkClientTrusted(java.security.cert.X509Certificate[], java.lang.String)
		 */
		@Override
		public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {
		}

		/**
		 * @param chain
		 * @param authType
		 * @throws CertificateException
		 * @see javax.net.ssl.X509TrustManager#checkServerTrusted(java.security.cert.X509Certificate[], java.lang.String)
		 */
		@Override
		public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {
		}

		/**
		 * @return
		 * @see javax.net.ssl.X509TrustManager#getAcceptedIssuers()
		 */
		@Override
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	}
}
