/*
 * @(#)ParsingTest.java $version 2014. 8. 5.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Document;

import com.google.common.collect.Maps;

/**
 * @author younghan
 */
public class ParsingTest {
	public static void main(String[] args) {
		//http://webs.hufs.ac.kr:8989/jsp/HUFS/stu1/stu1_c0_a0_d1.jsp?ledg_year=2014&ledg_sessn=1&campus_sect=H2
		//http://webs.hufs.ac.kr:8989/jsp/HUFS/stu1/stu1_c0_a0_d2.jsp?org_sect=A
		//http://webs.hufs.ac.kr:8989/jsp/HUFS/stu1/stu1_c0_a0_d2.jsp?org_sect=A&ledg_year=2014&ledg_sessn=1&campus_sect=H2&gubun=1&crs_strct_cd=ARAC2_H2
		Document doc;
//		try {
//			doc = Jsoup.connect("http://webs.hufs.ac.kr:8989/jsp/HUFS/stu1/stu1_c0_a0_d2.jsp?org_sect=A&ledg_year=2014&ledg_sessn=1&campus_sect=H2&gubun=1&crs_strct_cd=ARAC2_H2").get();
//			Elements els = doc.getElementsByAttribute("onmouseover");
//			for(int i = 0; i < els.size(); ++i)	{
//				Elements tdElements = els.get(i).getElementsByTag("td");
//				for(int j = 0; j < tdElements.size(); j++)	{
//					System.out.println(tdElements.get(j).text());
//				}
//			}
//		} catch (IOException e) {
//			System.out.println(e);
//		}
//		try {
//			doc = Jsoup.connect("http://webs.hufs.ac.kr:8989/jsp/HUFS/stu1/stu1_c0_a0_d1.jsp?ledg_year=2014&ledg_sessn=1&campus_sect=H1").get();
//			Elements els = doc.getElementsByAttributeValue("name", "crs_strct_cd");
//			for(int i = 0; i < els.size(); ++i)	{
//				Elements optionElements = els.get(i).getElementsByTag("option");
//				for(int j = 0; j < optionElements.size(); j++)	{
//					System.out.println(optionElements.get(j).attr("value"));
//					System.out.println(optionElements.get(j).text());
//				}
//			}
//		} catch (IOException e) {
//			System.out.println(e);
//		}

//		
//		CubridDataManager dm = new CubridDataManager();
//		dm.setStrDriver("cubrid.jdbc.driver.CUBRIDDriver");
//		dm.setStrDBConn("jdbc:cubrid:10.99.204.57:30102:nstore:::?althosts=10.99.204.57:30102");
//		dm.setStrUserID("nstore");
//		dm.setStrUserPW("nstore");
//		dm.setInitialSize(5);
//		dm.setMaxActive(30);
//		dm.setMaxIdle(20);
//		dm.setMinIdle(5);
//		dm.setTimeBetweenEvictionRunsMillis(-1);
//		
//		dm.initDriver();
//		
//		Connection conn = dm.getConnection();
//		
//		String strSQL = "SELECT prod_nm FROM nst_prod";
//	    PreparedStatement objStmt;
//		try {
//			objStmt = conn.prepareStatement(strSQL);
//			ResultSet objRS = objStmt.executeQuery();
//			List<String> temp = Lists.newArrayList(); 
//			while (objRS.next()) {
//				temp.add(objRS.getString(1));
//			}
//			
//			System.out.println(temp);
//		} catch (SQLException e) {
//			e.printStackTrace();;
//		}

		Map<String, String> engDay = Maps.newHashMap();
		engDay.put("월", "MON");
		engDay.put("화", "TUE");
		engDay.put("수", "WED");
		engDay.put("목", "THU");
		engDay.put("금", "FRI");
		engDay.put("토", "SAT");
	    String test = "목 5 6 7 8 토 3 4 (0309)";
		String[] result = StringUtils.split(test," ");
		String weekDay = "";
		for(String t : result)	{
			if(StringUtils.isNumeric(t))	{
				System.out.println(weekDay + t);
			} else if(t.length() < 4)	{
				weekDay = engDay.get(t);
			} 			
		}
		
	}
}
