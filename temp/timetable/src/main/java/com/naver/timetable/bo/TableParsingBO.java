/*
 * @(#)TableParsingBO.java $version 2014. 8. 5.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.bo;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.naver.timetable.dao.CategoryDAO;
import com.naver.timetable.dao.ConfigDAO;
import com.naver.timetable.dao.LectureDAO;
import com.naver.timetable.model.CampusMajorEnum;
import com.naver.timetable.model.Category;
import com.naver.timetable.model.Lecture;
import com.naver.timetable.model.LectureAttending;
import com.naver.timetable.model.LectureSearchParam;
import com.naver.timetable.model.LectureTime;

/**
 * 테이블 파싱과 관련된 작업을 하는 BO
 * @author younghan
 */
@Service
public class TableParsingBO {
	private static final Logger LOG = LoggerFactory.getLogger(TableParsingBO.class);
	private static final String DEFAULT_URL = "http://webs.hufs.ac.kr:8989/jsp/HUFS/stu1/stu1_c0_a0_d2.jsp?org_sect=A&ledg_year=%s&ledg_sessn=%s&campus_sect=%s&gubun=%s&%s=%s";
	private static final String CATEGORY_URL = "http://webs.hufs.ac.kr:8989/jsp/HUFS/stu1/stu1_c0_a0_d1.jsp?org_sect=A&ledg_year=%s&ledg_sessn=%s&campus_sect=%s";
	private static final String TR_TAG_REGEX = "(<tr[^>]*?>)([\\s\\S]*?)(?=<\\/tr>)";
	private static final String TD_TAG_REGEX = "(<td([^>]*?)>)([\\s\\S]*?)(?=<\\/td>)";
	private static final String CHECK_ANCHOR_REGEX = "([\\S\\s]*?)href([\\S\\s]*)";
	private static final String SPLIT_ANCHOR_REGEX = "\"([\\S]*?)\">([\\S\\s]*)<\\/a>";
	private static final String SELECT_TAG_REGEX = "<select name=\"%s\"([\\s\\S]*?)<\\/select>";
	private static final String OPTION_TAG_REGEX = "value[\\s]?=\"(\\S*?)\">([\\S\\s]*?)<\\/option>";

	private static final int THREAD_COUNT = 10;
	
	public static final Map<String, String> WEEKDAY = new ImmutableMap.Builder<String, String>()
	.put("월", "MON")
	.put("화", "TUE")
	.put("수", "WED")
	.put("목", "THU")
	.put("금", "FRI")
	.put("토", "SAT").build();
	
	@Autowired
	CategoryDAO categoryDAO;
	
	@Autowired
	LectureDAO lectureDAO;
	
	@Autowired
	ConfigDAO configDAO;
	
	@Autowired
	HttpClientBO httpClientBO;
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void doParsing(String year, String season)	{
		// 한 학기에 한번만 가능하도록 함.
		if(configDAO.isExistSeason(year, season) == 0)	{
			configDAO.insertSeason(year, season);
			saveCategory(year,season);
			saveTimeTable(year,season);
		}
	}
	
	/**
	 * 년도와 학기를 인자로 받아서 해당하는 url조합들을 만들어 Queue형식으로 돌려줌.
	 * @param year
	 * @param season
	 * @return
	 */
	protected Queue<UrlEntity> makeUrlQueue(String year, String season)	{
		Queue<UrlEntity> urlQueue = Lists.newLinkedList();
		
		for(CampusMajorEnum campusMajor : CampusMajorEnum.values())	{
			List<String> categoryCodes = categoryDAO.getCatgCode(campusMajor.getCampus(), campusMajor.getMajorCode());
			for(String categoryCode : categoryCodes)	{
				UrlEntity urlEntity = new UrlEntity(year, season, categoryCode, campusMajor);
				urlQueue.add(urlEntity);
				//처음에는 wait 후 notify방식으로 사용
//				urlQueue.notify();
			}
		}
		return urlQueue;
	}

	public void saveTimeTable(String year, String season) {

		Queue<UrlEntity> urlQueue = makeUrlQueue(year, season);
		
		//사용할 스레드 초기화
		LectureMakeThread makeLectureThread[] = new LectureMakeThread[THREAD_COUNT];
		for(int i = 0; i < THREAD_COUNT; i++)	{
			makeLectureThread[i] = new LectureMakeThread(urlQueue);
			makeLectureThread[i].start();
		}
		// 큐가 끝남을 알리기 위해서 넣음
	
		
		//모든 Thread가 종료됨.
		for(int i = 0; i < THREAD_COUNT; i++)	{
			try {
				makeLectureThread[i].join();
			} catch (InterruptedException e) {
				LOG.debug("main interrupted", e);
			}
		}
		
		
		List<LectureTime> timeList = makeTimeList(lectureDAO.getLectureList(new LectureSearchParam(year, season)));
		
		if(!timeList.isEmpty())	{
			lectureDAO.saveClassTimeList(timeList);
		}
	}
	
	
	/**
	 * @param lectures 
	 * @return 강의들에 해당하는 시간을 <학수번호, 강의시간>의 맵형태로 반환한다.
	 */
	public List<LectureTime> makeTimeList(List<Lecture> lectures)	{
		List<LectureTime> lectureTimeList = Lists.newArrayList();
		for(Lecture lecture : lectures)	{
			String[] result = StringUtils.split(lecture.getRoom()," ");
			String weekDay = "";
			for(String t : result)	{
				// 숫자로 확인되면 삽입.
				if(StringUtils.isNumeric(t))	{
					LectureTime ct = new LectureTime();
					ct.setLectureID(lecture.getLectureID());
					ct.setWeekDay(weekDay + t);
					lectureTimeList.add(ct);
				} else if(t.length() < 4)	{
					weekDay = WEEKDAY.get(t);
				} 			
			}
		}
		return lectureTimeList;
	}
	
	
	
	/**
	 * 전공 / 교양 여부와 서울 / 글로벌 캠퍼스 여부에 따라서 총 4가지 조합으로 카테고리의 내용을 가져오는 메서드.
	 * 연도와 학기를 입력으로 받음.
	 */
	public void saveCategory(String year, String season) {
		//기존에 존재하는 키일경우 넘기는 방법이 필요함.
		List<Category> existCategory = categoryDAO.getAllCategory();
		for (CampusMajorEnum campusMajor : CampusMajorEnum.values()) {
			String url = String.format(CATEGORY_URL, year, season, campusMajor.getCampus());
			
			List<Category> categoryToInsert = deleteExistCategory(existCategory, makeCategoryList(url, campusMajor));
			if(categoryToInsert.size() > 0)
				categoryDAO.insertCategories(categoryToInsert);
		}
	}
	
	/**
	 * 기존에 중복되는 categoryId가 있는지 확인하고 중복되지 않는 category만 넘겨줌
	 * @param existCategory
	 * @param newCategory
	 * @return
	 */
	public List<Category> deleteExistCategory(List<Category> existCategory, List<Category> newCategory)	{
		Map<String, Category> compareMap = Maps.newHashMap();
		for(Category category : existCategory)	{
			compareMap.put(category.getCategoryId(), category);
		}
		
		List<Category> result = Lists.newArrayList();
		for(Category category : newCategory)	{
			if(!compareMap.containsKey(category.getCategoryId()))	{
				result.add(category);
			}
		}
		
		return result;
	}
	
	public List<Category> makeCategoryList(String url, CampusMajorEnum campusMajor)	{
		String htmlBody = httpClientBO.getHttpBody(url);
		Matcher selectMatcher = makeMatcher(String.format(SELECT_TAG_REGEX, campusMajor.getMajorUrl()), htmlBody);
		selectMatcher.find();
		Matcher categoryMatcher = makeMatcher(OPTION_TAG_REGEX, selectMatcher.group(1));
		List<Category> categories = Lists.newArrayList();
		while(categoryMatcher.find())	{
			categories.add(new Category(categoryMatcher.group(1), categoryMatcher.group(2), campusMajor.getMajorCode(), campusMajor.getCampus()));
		}
		return categories;
	}
	
	/**
	 * html내용 중 강의내용을 골라 Lecture의 List로 변경 해줌.
	 * @param htmlBody html 전체 내용
	 * @param categoryId 분류 카테고리의 ID
	 * @return
	 */
	public List<Lecture> parsingToLecture(String htmlBody,String categoryId, String year, String season)	{
		htmlBody = htmlBody.replaceAll("<!--(.*?)-->", ""); //주석 제거
	
		Matcher trMatcher = makeMatcher(TR_TAG_REGEX,htmlBody);
		
		List<Lecture> lectureList = Lists.newArrayList();
		trMatcher.find(); // 제목 표시하는 줄 넘어가기 위해서
		
		while (trMatcher.find()) {
			Matcher tdMatcher = makeMatcher(TD_TAG_REGEX, trMatcher.group(2));
			Lecture lecture = convertTdToLecture(tdMatcher,categoryId, year, season);
			lectureList.add(lecture);
		}
		return lectureList;
	}
	
	/**
	 * td에 해당하는 Matcher를 인자로 받아서 Lecture객체에 맞게 변환해줌.
	 * @param tdMatcher
	 * @return
	 */
	public Lecture convertTdToLecture(Matcher tdMatcher, String categoryId, String year, String season)	{
		Lecture lecture = new Lecture();
		lecture.setCatgId(categoryId);
		int count = 0;
		while(tdMatcher.find())	{
			String text = tdMatcher.group(3).trim();
			switch(count)	{
				case 2 : lecture.setGrade(text); break;
				case 3 : lecture.setLectureNum(text); break;
				case 5 :
					List<String> splitName = splitLectureName(text);
					lecture.setUrl(splitName.get(0));
					lecture.setLectureName(splitName.get(1)); break;
				case 6 : lecture.setPoint(Integer.parseInt(text)); break;
				case 8 : lecture.setProf(text); break;
				case 9 : lecture.setRoom(text); break;
				case 13 : lecture.setCyber(Boolean.toString(text.equals("사이버"))); break;
				case 14 : lecture.setForNative(Boolean.toString(text.equals("원어강의"))); break;
			}
			count++;
		}
		
		lecture.setLectureYear(year);
		lecture.setLectureSeason(season);
		return lecture;
	}
	
	/**
	 * 과목명 td의 내용을 가져와서 링크 주소와 실제 내용 부분으로 나눔
	 * @param lectureNameText 과목명에 들어있는 td
	 * @return
	 */
	public List<String> splitLectureName(String lectureNameText)	{
		if(lectureNameText.matches(CHECK_ANCHOR_REGEX))	{
			Matcher matcher = makeMatcher(SPLIT_ANCHOR_REGEX, lectureNameText);
			matcher.find();
			return Lists.newArrayList(matcher.group(1), matcher.group(2));
		} else	{
			// a tag 가 없는 경우
			return Lists.newArrayList("",lectureNameText);
		}
	}
	
	/**
	 * 정규식과 text를 바탕으로 Matcher를 만들어줌.
	 * @param regex
	 * @param text
	 * @return
	 */
	protected Matcher makeMatcher(String regex, String text)	{
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(text);
	}
	
	
	/**
	 * htmlBody의 내용을 받아서 AttendingList로 생성
	 * @param htmlBody
	 * @return
	 */
	public List<LectureAttending> convertTdToAttending(String htmlBody)	{
		htmlBody = htmlBody.replaceAll("<!--(.*?)-->", ""); //주석 제거	
		Matcher trMatcher = makeMatcher(TR_TAG_REGEX,htmlBody);
		
		List<LectureAttending> attendingList = Lists.newArrayList();
		trMatcher.find(); // 제목 표시하는 줄 넘어가기 위해서
		while (trMatcher.find()) {
			Matcher tdMatcher = makeMatcher(TD_TAG_REGEX, trMatcher.group(2));

			LectureAttending attending = new LectureAttending();
			//3번째가 학수번호
			for(int i = 0; i <= 3; i++)	{
				tdMatcher.find();
			}
			
			attending.setLectureNum(tdMatcher.group(3).trim());
			// 10번째가 수강 인원
			for(int i = 0; i <= 6; i++)	{
				tdMatcher.find();
			}
			String tdText = tdMatcher.group(3).trim();
			attending.setAttending(Integer.parseInt(tdText.split("/")[0]));

			attendingList.add(attending);
			
		}
		return attendingList;
	}
	
	/**
	 * 수강인원 저장
	 */
	public void saveAttending()	{
//		for(CampusMajorEnum campusMajor : CampusMajorEnum.values())	{
//			List<String> categoryCodes = categoryDAO.getCatgCode(campusMajor.getCampus(), campusMajor.getMajorCode());
//			for(String categoryId : categoryCodes)	{
//				String url = String.format(DEFAULT_URL,
//					configDAO.getValue(YEAR), configDAO.getValue(SEASON), campusMajor.getCampus(), campusMajor.getMajorCode(), campusMajor.getMajorUrl(), categoryId);
//				String htmlBody = httpClientBO.getHttpBody(url);
//				
//				lectureDAO.saveAttending(convertTdToAttending(htmlBody));
//			}
//		}
	}
	
	class UrlEntity	{
		String year;
		String season;
		String categoryId;
		CampusMajorEnum campusMajor;
		
		public UrlEntity()	{
			
		}
		/**
		 * @param year
		 * @param season
		 * @param categoryId
		 * @param campusMajor
		 */
		public UrlEntity(String year, String season, String categoryId, CampusMajorEnum campusMajor) {
			super();
			this.year = year;
			this.season = season;
			this.categoryId = categoryId;
			this.campusMajor = campusMajor;
		}
		
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			this.year = year;
		}
		public String getSeason() {
			return season;
		}
		public void setSeason(String season) {
			this.season = season;
		}
		public String getCategoryId() {
			return categoryId;
		}
		public void setCategoryId(String categoryId) {
			this.categoryId = categoryId;
		}
		public CampusMajorEnum getCampusMajor() {
			return campusMajor;
		}
		public void setCampusMajor(CampusMajorEnum campusMajor) {
			this.campusMajor = campusMajor;
		}
	}
	
	class LectureMakeThread extends Thread	{
		Queue<UrlEntity> urlQueue;
		List<Lecture> lectureList;
		
		public LectureMakeThread(Queue<UrlEntity> urlQueue)	{
			this.urlQueue = urlQueue;
			lectureList = Lists.newArrayList();
		}
		
		public List<Lecture> getLectureList()	{
			return lectureList;
		}
		
		public void run() {		
			while(true)	{
				UrlEntity urlEntity;
				synchronized(urlQueue)	{
					// 큐가 비어있으면 thread 끝내도록.
					if(urlQueue.isEmpty())	{
						break;
					}
					urlEntity = urlQueue.poll();
				}

				// 아래로는 urlEntity로 작업해서 lectureList에 넣는 부분
				
				String url = String.format(DEFAULT_URL,
					urlEntity.getYear(), urlEntity.getSeason(), urlEntity.getCampusMajor().getCampus(),
					urlEntity.getCampusMajor().getMajorCode(), urlEntity.getCampusMajor().getMajorUrl(), urlEntity.getCategoryId());
				//httpBody 가져옴
				String htmlBody = httpClientBO.getHttpBody(url);
				htmlBody = htmlBody.replaceAll("<!--(.*?)-->", ""); //주석 제거
				
				Matcher trMatcher = makeMatcher(TR_TAG_REGEX,htmlBody);
				
				trMatcher.find(); // 제목 표시하는 줄 넘어가기 위해서
				
				while (trMatcher.find()) {
					Matcher tdMatcher = makeMatcher(TD_TAG_REGEX, trMatcher.group(2));
					Lecture lecture = convertTdToLecture(tdMatcher, urlEntity.getCategoryId(), urlEntity.getYear(), urlEntity.getSeason());
					lectureList.add(lecture);
				}
			}
			lectureDAO.saveClassInfoList(this.lectureList);	
		}
	}
	
	class LectureMakeRunnable implements Runnable	{
		Queue<UrlEntity> urlQueue;
		List<Lecture> lectureList;
		
		public LectureMakeRunnable(Queue<UrlEntity> urlQueue)	{
			this.urlQueue = urlQueue;
			lectureList = Lists.newArrayList();
		}

		public LectureMakeRunnable() {
		}
		/**
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			
			while(true)	{
				UrlEntity urlEntity;
				synchronized(urlQueue)	{
					if(urlQueue.isEmpty())	{
						try {
							if(urlQueue.peek() == null) 
							urlQueue.wait();
						} catch (InterruptedException e) {
							// 예외 처리
							LOG.debug("thread run : ", e);
						}
					}
					urlEntity = urlQueue.peek();
					if(urlEntity.getSeason() == "ENDQUEUE")	{
						break;
					}
					urlQueue.poll();
					// 비어있나 위에서 검사하기 때문에 poll사용
				}
				String url = String.format(DEFAULT_URL,
					urlEntity.getYear(), urlEntity.getSeason(), urlEntity.getCampusMajor().getCampus(),
					urlEntity.getCampusMajor().getMajorCode(), urlEntity.getCampusMajor().getMajorUrl(), urlEntity.getCategoryId());
				//httpBody 가져옴
				String htmlBody = httpClientBO.getHttpBody(url);
				htmlBody = htmlBody.replaceAll("<!--(.*?)-->", ""); //주석 제거
				
				Matcher trMatcher = makeMatcher(TR_TAG_REGEX,htmlBody);
				
				trMatcher.find(); // 제목 표시하는 줄 넘어가기 위해서
				
				while (trMatcher.find()) {
					Matcher tdMatcher = makeMatcher(TD_TAG_REGEX, trMatcher.group(2));
					Lecture lecture = convertTdToLecture(tdMatcher, urlEntity.getCategoryId(), urlEntity.getYear(), urlEntity.getSeason());
					lectureList.add(lecture);
				}
			}
			
		}
		
	}
}
