/*
 * @(#)ParsingTest.java $version 2014. 8. 5.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author younghan
 */
public class ParsingTest {
	public static void main(String[] args) {
		String serverName = "webs.hufs.ac.kr";
	      int port = 8989;
	      try
	      {
	         System.out.println("Connecting to " + serverName
	                             + " on port " + port);
	         Socket client = new Socket(serverName, port);
	         System.out.println("Just connected to "
	                      + client.getRemoteSocketAddress());
	         OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out =
	                       new DataOutputStream(outToServer);

	         out.writeUTF("GET /jsp/HUFS/stu1/stu1_c0_a0_d0.jsp HTTP/1.1\n"
	                      + "Host: webs.hufs.ac.kr:8989\n\n");
	         InputStream inFromServer = client.getInputStream();
	         BufferedReader br = new BufferedReader(new InputStreamReader(inFromServer));
	         String newLine;
	         while((newLine = br.readLine())!=null){
	        	 System.out.println(newLine);
	         }
	         client.close();
	      }catch(IOException e)
	      {
	         e.printStackTrace();
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
