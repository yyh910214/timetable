/*
 * @(#)HttpClientBO.java $version 2014. 8. 13.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.bo;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.HttpClientBuilder;
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
	
	public String getHttpBody(String url)	{
		HttpClient httpClient = null;
		HttpResponse httpResponse = null;
		try	{
			httpClient = HttpClientBuilder.create().build();
			httpResponse = httpClient.execute(new HttpGet(url));
			return EntityUtils.toString(httpResponse.getEntity());
		}	catch (ClientProtocolException e)	{
			LOG.error("Client protocol error : ", e);
		}	catch (IOException e) { 
			LOG.error("IO error : ", e);
		}	finally	{
			//예외 먹음
			HttpClientUtils.closeQuietly(httpResponse);
			HttpClientUtils.closeQuietly(httpClient);
		}
		
		return null;
	}
}
