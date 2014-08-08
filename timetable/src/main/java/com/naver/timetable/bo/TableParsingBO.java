/*
 * @(#)TableParsingBO.java $version 2014. 8. 5.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.bo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import com.naver.timetable.dao.CategoryDAO;
import com.naver.timetable.dao.ClassInfoDAO;
import com.naver.timetable.model.Category;
import com.naver.timetable.model.ClassInfo;
import com.naver.timetable.model.ClassTime;

/**
 * 테이블 파싱과 관련된 작업을 하는 BO
 * @author younghan
 */
@Service
public class TableParsingBO {
	private static final Logger LOG = LoggerFactory.getLogger(TableParsingBO.class);
	private static final List<String> CAMPUS_CODE = Lists.newArrayList("H1","H2");
	private static final List<String> MAJOR_CODE = Lists.newArrayList("1","2");
	private static final String DEFAULT_URL = "http://webs.hufs.ac.kr:8989/jsp/HUFS/stu1/stu1_c0_a0_d2.jsp?org_sect=A&ledg_year=";
	private static final String CATEGORY_URL = "http://webs.hufs.ac.kr:8989/jsp/HUFS/stu1/stu1_c0_a0_d1.jsp?ledg_year=";
	private static final String SESSON_FILTER = "&ledg_sessn=";
	private static final String CAMPUS_FILTER = "&campus_sect=";
	private static final String MAJOR_FILTER = "&gubun=";
	
	//구분 값을 키로하여 선택하도록 함. 데이터 베이스를 사용해야 하는지 생각해야함.
	private static final Map<String, String> CATEGORY_FILTER = new ImmutableMap.Builder<String, String>()
		.put("1","crs_strct_cd")
		.put("2","compt_fld_cd").build();
	
	
	private static final Map<String, String> WEEKDAY = new ImmutableMap.Builder<String, String>()
	.put("월", "MON")
	.put("화", "TUE")
	.put("수", "WED")
	.put("목", "THU")
	.put("금", "FRI")
	.put("토", "SAT").build();
	
	@Autowired
	CategoryDAO categoryDAO;
	
	@Autowired
	ClassInfoDAO classInfoDAO;

	public void saveTimeTable(String year, String sesson) {
		
//		http://webs.hufs.ac.kr:8989/jsp/HUFS/stu1/stu1_c0_a0_d2.jsp?org_sect=A
		//				http://webs.hufs.ac.kr:8989/jsp/HUFS/stu1/stu1_c0_a0_d2.jsp?org_sect=A&ledg_year=2014&ledg_sessn=1&campus_sect=H2&gubun=1&crs_strct_cd=ARAC2_H2
		StringBuilder baseString = new StringBuilder(DEFAULT_URL);
		baseString.append(year).append(SESSON_FILTER).append(sesson).append(CAMPUS_FILTER);

		for(String campusCode : CAMPUS_CODE)	{
			for(String majorCode : MAJOR_CODE)	{
				List<String> categoryCodes = categoryDAO.getCatgCode(campusCode, majorCode);
				for(String categoryId : categoryCodes)	{
					StringBuilder sb = new StringBuilder(baseString);
					sb.append(campusCode).append(MAJOR_FILTER).append(majorCode).append("&").append(CATEGORY_FILTER.get(majorCode)).append("=").append(categoryId);
					
					try {
						Document doc;
						doc = Jsoup.connect(sb.toString()).get();
						
						Elements els = doc.getElementsByAttribute("onmouseover");
						List<ClassInfo> classList = convertTrElementToClass(els, majorCode, campusCode, categoryId);
						List<ClassTime> timeList = makeTimeList(classList);
	
						classInfoDAO.saveClassInfoList(classList);
						classInfoDAO.saveClassTimeList(timeList);
						
					} catch (IOException e) {
						LOG.error("DOC error", e);
					}
					
				}
			}
		}
		
	}
	
	/**
	 * @param tr 테이블행이 담겨있는 elements 객체
	 * @param majorCode 전공/ 교양 여부 코드로 1 or 2로 구분된다.
	 * @param campusCode 서울/ 글로벌 캠퍼스의 코드로 H1 이나 H2로 표기
	 * @param categoryId 카테고리id
	 * @return 해당 카테고리 페이지에 있는 수업에 대한 정보를 ClassInfo로 만들어서 List로 반환한다.
	 */
	public List<ClassInfo> convertTrElementToClass(Elements tr, String majorCode, String campusCode, String categoryId)	{
		List<ClassInfo> classList = Lists.newArrayList();
		for (int i = 0; i < tr.size(); ++i) {
			ClassInfo classInfo = new ClassInfo();
			Elements tdElements = tr.get(i).getElementsByTag("td");

			classInfo.setCatgId(categoryId);
			classInfo.setGrade(tdElements.get(2).text());
			classInfo.setClassNum(tdElements.get(3).text());
			classInfo.setClassName(tdElements.get(5).text());
			//만들어 보기
			Elements urlElements = tdElements.get(5).getElementsByAttribute("href");
			classInfo.setUrl(urlElements.size() == 0 ? "" : urlElements.get(0).absUrl("href"));
			classInfo.setPoint(Integer.parseInt(tdElements.get(6).text()));
			classInfo.setProf(tdElements.get(8).text());
			// 직접 만들어 보기 
			classInfo.setRoom(tdElements.get(9).text());
			classInfo.setCyber(Boolean.toString(tdElements.get(13).text().equals("사이버")));
			classInfo.setForNative(Boolean.toString(tdElements.get(14).text().equals("원어강의")));
			
			classList.add(classInfo);
		}
		return classList;
	}
	
	/**
	 * @param classInfos 
	 * @return 강의들에 해당하는 시간을 <학수번호, 강의시간>의 맵형태로 반환한다.
	 */
	public List<ClassTime> makeTimeList(List<ClassInfo> classInfos)	{
		List<ClassTime> classTimeList = Lists.newArrayList();
		for(ClassInfo classInfo : classInfos)	{
			String[] result = StringUtils.split(classInfo.getRoom()," ");
			String weekDay = "";
			for(String t : result)	{
				// 숫자로 확인되면 삽입.
				if(StringUtils.isNumeric(t))	{
					ClassTime ct = new ClassTime();
					ct.setClassNum(classInfo.getClassNum());
					ct.setWeekDay(weekDay + t);
					classTimeList.add(ct);
				} else if(t.length() < 4)	{
					weekDay = WEEKDAY.get(t);
				} 			
			}
		}
		return classTimeList;
	}
	
	
	
	/**
	 * 전공 / 교양 여부와 서울 / 글로벌 캠퍼스 여부에 따라서 총 4가지 조합으로 카테고리의 내용을 가져오는 메서드.
	 * 연도와 학기를 입력으로 받음.
	 */
	public void saveCategory(String year, String sesson) {
		for (String campusCode : CAMPUS_CODE) {
			StringBuilder sb = new StringBuilder(CATEGORY_URL);
			sb.append(year).append(SESSON_FILTER).append(sesson).append(CAMPUS_FILTER).append(campusCode);
			System.out.println(sb.toString());
			Document doc;
			try {
				doc = Jsoup.connect(sb.toString()).get();
				for (String majorCode : MAJOR_CODE) {
					Elements els = doc.getElementsByAttributeValue("name", CATEGORY_FILTER.get(majorCode));
					List<Category> categories = Lists.newArrayList();
					// 1 : 1학기 , 2: 여름계절학기 , 3: 2학기 , 4 : 겨울계절학기
					// crs_strct_cd : 전공 , compt_fld_cd : 교양
					for (int i = 0; i < els.size(); ++i) {
						Elements optionElements = els.get(i).getElementsByTag("option");
						for (int j = 0; j < optionElements.size(); j++) {
							categories.add(new Category(optionElements.get(j).attr("value"), optionElements.get(j).text(), majorCode, campusCode));
						}
					}

					categoryDAO.insertCategories(categories);

				}
			} catch (IOException e) {
				LOG.error("Making Parsing DOC Error : ", e);
			}

		}

	}
}
