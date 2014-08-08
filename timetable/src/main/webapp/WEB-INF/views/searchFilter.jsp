<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" href="http://knit.naver.com/dist/css/knit.css">
<title>Insert title here</title>
</head>
<body>

<header>
<div class="brand">
  <h1>HUFS 강의 시간표 검색 <small>hufs</small></h1>
</div>
<div class="clearfix"></div>
<nav class="navbar navbar-default navbar-collapse mg-reset" role="navigation">
  <ul class="nav nav-pills">
  <li class="active"><a href="#">시간표 검색</a></li>
  <li><a href="#">강의 평가 검색</a></li>
  <li><a href="#">중고 게시판</a></li>
  </ul>
</nav>
<nav class="navbar navbar-inverse navbar-collapse" role="navigation">
  <div class="row">
    <div class="col-xs-7 text-right">
      <div class="form-inline navbar-btn">
        <strong>20202020</strong>
        <a href="#" class="btn btn-default"><span class="glyphicon glyphicon-lock"></span> 개인정보</a>
        <a href="#" class="btn btn-default"><span class="glyphicon glyphicon-log-out"></span>로그아웃</a>
      </div>
    </div>
  </div>
</nav>
<header>

<table class="table table-bordered mg-bottom-lg">
    <colgroup>
      <col style="width:100px">
      <col>
      <col style="width:150px">
      <col>
      <col style="width:150px">
    </colgroup>
    <tr>
    <th class="text-center">
      <label class="checkbox-inline">	
        <strong>학점</strong>
      </label>
    </th>
    <td>
      <label class="checkbox-inline">
        <input type="checkbox" id="inlineCheckbox1" value="option1"> 1학점
      </label>
      <label class="checkbox-inline">
        <input type="checkbox" id="inlineCheckbox2" value="option2"> 2학점
      </label>
      <label class="checkbox-inline">
        <input type="checkbox" id="inlineCheckbox3" value="option3"> 3학점
      </label>
    </td>
    <th class="text-center">
      <label class="checkbox-inline">	
        <strong>기타</strong>
      </label>
    </th>
    <td>
      <label class="checkbox-inline">
        <input type="checkbox" id="inlineCheckbox1" value="option1"> 원어
      </label>
      <label class="checkbox-inline">
        <input type="checkbox" id="inlineCheckbox2" value="option2"> 사이버
      </label>
    </td>
    <td rowspan="2">
    	검색
    </td>
    </tr>
    <tr>
    <th class="text-center">강의시간</th>
    <td>
      <select class="form-control">
      <option>월요일 1교시</option>
      </select>
      
      <div style="height:70px; border:1px solid black; position:relative; overflow:hidden;">
      	<ul>
      		<li>월요일1교시</li>
      		<li>화요일1교시</li>
      		<li>월요일2교시</li>

      	</ul>
      </div>
      
    </td>
    <th class="text-center">카테고리</th>
    <td>
      <select class="form-control">
      <option>컴퓨터공학과</option>
      </select>
      <div style="height:70px; border:1px solid black; position:relative; overflow:hidden;">
      	<ul>
      		<li>asdasd</li>
      		<li>aaaa</li>
      		<li>asdasd</li>
      		<li>asdasd</li>
      		<li>asdasd</li>
      	</ul>
      </div>
    </td>
    </tr>
    </table>
    
    
    <div class="table-responsive">
				<table class="table table-ellipsis table-hover">
				<colgroup>
				<col style="width:170px">
				<col class="hidden-sm" style="width:90px">
				<col class="hidden-sm" style="width:90px">
				<col>
				<col class="hidden-sm" style="width:90px">
				</colgroup>
				<thead>
				  <tr>
				  	<th class="text-center">카테고리</th>
				  	<th class="text-center">학년</th>
					<th class="text-center">학수번호</th>
					<th class="text-center">강의명</th>
					<th class="text-center">학점</th>
					<th class="text-center hidden-sm">담당교수</th>
					<th class="text-center hidden-sm">강의실</th>
					<th class="text-center hidden-sm">사이버</th>
					<th class="text-center hidden-sm">원어</th>
				  </tr>
				</thead>
				<tbody>
				  <tr>
				  	<td>컴퓨터공학과</td>
				  	<td>3</td>
					<td>A22015401</td>
					<td><a href="검수입력상세.html">마이크로프로세서및실습</a></td>
					<td class="text-center hidden-sm">3</td>
					<td class="hidden-sm">이소정</td>
					<td class="text-center hidden-sm">화 7 8 (5313) 목 1 2 (5209)</td>
					<td>사이버</td>
					<td>원어</td>
				  </tr>
				  <tr>
				  	<td>컴퓨터공학과</td>
				  	<td>3</td>
					<td>A22015401</td>
					<td><a href="검수입력상세.html">마이크로프로세서및실습</a></td>
					<td class="text-center hidden-sm">3</td>
					<td class="hidden-sm">이소정</td>
					<td class="text-center hidden-sm">화 7 8 (5313) 목 1 2 (5209)</td>
					<td>사이버</td>
					<td>원어</td>
				  </tr>
				  <tr>
				  	<td>컴퓨터공학과</td>
				  	<td>3</td>
					<td>A22015401</td>
					<td><a href="검수입력상세.html">마이크로프로세서및실습</a></td>
					<td class="text-center hidden-sm">3</td>
					<td class="hidden-sm">이소정</td>
					<td class="text-center hidden-sm">화 7 8 (5313) 목 1 2 (5209)</td>
					<td>사이버</td>
					<td>원어</td>
				  </tr>
				  <tr>
				  	<td>컴퓨터공학과</td>
				  	<td>3</td>
					<td>A22015401</td>
					<td><a href="검수입력상세.html">마이크로프로세서및실습</a></td>
					<td class="text-center hidden-sm">3</td>
					<td class="hidden-sm">이소정</td>
					<td class="text-center hidden-sm">화 7 8 (5313) 목 1 2 (5209)</td>
					<td>사이버</td>
					<td>원어</td>
				  </tr>
				  <tr>
				  	<td>컴퓨터공학과</td>
				  	<td>3</td>
					<td>A22015401</td>
					<td><a href="검수입력상세.html">마이크로프로세서및실습</a></td>
					<td class="text-center hidden-sm">3</td>
					<td class="hidden-sm">이소정</td>
					<td class="text-center hidden-sm">화 7 8 (5313) 목 1 2 (5209)</td>
					<td>사이버</td>
					<td>원어</td>
				  </tr>
				</tbody>
				</table>
			</div>



</body>
</html>





<!-- <div id="search_filter"> -->
<!--            <div id="search_filter_major" class="search_filter_div"> -->
<!-- <div class="search_filter_body"> -->
<!-- 분류 : -->
<!-- <select id="filter_department_id" name="filter[department_id]"><option value="1">컴퓨터공학부</option> -->
<!-- <option value="174">환경보건학과</option> -->
<!-- <option value="175">환경조경학과</option> -->
<!-- <option value="230">흉부외과학교실</option></select><br /> -->
<!-- </div> -->
<!-- </div> -->
<!-- <div id="search_filter_rest" class="search_filter_div"> -->
<!-- <div class="search_filter_head"><h2 style="border-right:4px solid #D2DE26" >기타</h2></div> -->
<!-- <div class="search_filter_body" style="border-right: 4px solid #DDE7BC;"> -->
<!-- <input id="filter_31" name="filter_31" type="checkbox" value="{}" /> 원어 <br /> -->
<!-- <input id="filter_32" name="filter_32" type="checkbox" value="{}" /> 사이버 <br /> -->
<!-- </div> -->
<!-- </div> -->

<!-- <div id="search_filter_time" class="search_filter_div" style="border-top:1px solid #DDE7BC;"> -->
<!-- <div class="search_filter_body"> -->
<!-- <span id="search_filter_semester">2014년 2학기</span>에 열린 강의<br /> -->
<!-- <select id="filter_week" name="filter[week]"><option value="0">월요일</option> -->
<!-- <option value="1">화요일 1</option> -->
<!-- <option value="2">화요일 2</option> -->
<!-- <option value="3">화요일 3</option> -->
<!-- <option value="4">화요일 4</option> -->
<!-- <option value="5">화요일 5</option></select>  -->
<!-- </div> -->
<!-- </div> -->

<!-- <div id="search_filter_point" class="search_filter_div" style="border-top:1px solid #DDE7BC;"> -->
<!-- <div class="search_filter_head"><h2>학점 선택</h2></div> -->
<!-- <div class="search_filter_body"> -->
<!-- <input id="filter_61" name="filter_61" type="checkbox" value="{}" /> 1 학점 -->
<!-- <input id="filter_62" name="filter_62" type="checkbox" value="{}" /> 2 학점 -->
<!-- <input id="filter_63" name="filter_63" type="checkbox" value="{}" /> 3 학점 -->
<!-- </div> -->
<!-- </div> -->
