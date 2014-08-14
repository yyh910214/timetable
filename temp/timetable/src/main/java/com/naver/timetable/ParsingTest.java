/*
 * @(#)ParsingTest.java $version 2014. 8. 5.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * @author younghan
 */
public class ParsingTest {
	public static void main(String[] args) {
		HttpClient httpClient = null;
		HttpResponse httpResponse = null;
		try	{
			httpClient = HttpClientBuilder.create().build();
			httpResponse = httpClient.execute(new HttpGet("http://webs.hufs.ac.kr:8989/jsp/HUFS/stu1/stu1_c0_a0_d2.jsp?org_sect=A&ledg_year=2014&ledg_sessn=1&campus_sect=H2&gubun=1&crs_strct_cd=ARAC2_H2"));

			String html = EntityUtils.toString(httpResponse.getEntity());
			
			html = html.replaceAll("<!--(.*?)-->", ""); //중간 공백제거
			String regexTrHtml = "(<tr[^>]*?>)([\\s\\S]*?)(?=<\\/tr>)";
			String regexTdHtml = "(<td([^>]*?)>)([\\s\\S]*?)(?=<\\/td>)";
		
			Pattern trPattern = Pattern.compile(regexTrHtml);
			Pattern tdPattern = Pattern.compile(regexTdHtml);
			Matcher matcher = trPattern.matcher(html);
			
			while (matcher.find()) {
				Matcher tdMatcher = tdPattern.matcher(matcher.group(2));
				while(tdMatcher.find())	{
					System.out.println(tdMatcher.group(3));
				}
			}
		}	catch (ClientProtocolException e)	{
			
		}	catch (IOException e) { 
			
		}	finally	{
			//예외 먹음
			HttpClientUtils.closeQuietly(httpResponse);
			HttpClientUtils.closeQuietly(httpClient);
		}
		
//		String text    =
//	        "<tr asd>asdfas  df<td></td>\n</tr>\n\n " +
//	        "<tr qqqq>wsws<td>xcv</td>qq</tr>'.";
//
//	String patternString = "(<tr[^>]*?>)([\\s\\S]*?)(?=<\\/tr>)";
//	System.out.println(patternString);
//
//	Pattern pattern = Pattern.compile(patternString);
//	Matcher matcher = pattern.matcher(text);
//	int count = 0;
//	while(matcher.find()) {
//	    count++;
//	    System.out.println("found: " + count + " : "
//	            + matcher.start() + " - " + matcher.end());
//	}
//		
		
		
		
		
	}
}
