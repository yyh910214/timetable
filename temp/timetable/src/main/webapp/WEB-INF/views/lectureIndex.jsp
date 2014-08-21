<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet"
	href="/css/jAutoCheckList/bootstrap/bootstrap.min.css" />
<link rel="stylesheet"
	href="/css/jAutoCheckList/bootstrap/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="/css/jAutoCheckList/jAutochecklist.min.css" />
<link rel="stylesheet" href="/css/jAutoCheckList/style.css" />
<link rel="stylesheet" href="/css/table.css" />
<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
<script src="/js/jAutoCheckList/jAutochecklist.min.js"></script>
<script src="/js/jAutoCheckList/bootstrap/bootstrap.min.js"></script>
<script src="/js/jAutoCheckList/script.js"></script>
<script type="text/javascript">
	function searchLecture() {

		var showTable = function(res) {
			var lectureTable = $('#lecture');
			lectureTable.empty();
			if (res.length == 0) {
				lectureTable.append("<tr>"
						+ "<td colspan='9'> 검색 결과가 앖습니다. </td>" + "</tr>");
			} else {
				for (var idx = 0; idx < res.length; idx++) {
					lectureTable
							.append("<tr>"
									+ "<td><a target='_blank' href='/comment/commentList?lectureID="
									+ res[idx].lectureID
									+ "'>"
									+ res[idx].lectureNum
									+ "</a></td>"	
									+ "<td><a target='_blank' href='http://webs.hufs.ac.kr:8989" + res[idx].url + "'>"
									+ res[idx].lectureName
									+ "</a></td>"
									+ "<td>"
									+ res[idx].grade
									+ "</td>"
									+ "<td>"
									+ res[idx].prof
									+ "</td>"
									+ "<td>"
									+ res[idx].point
									+ "</td>"
									+ "<td>"
									+ res[idx].room
									+ "</td>"
									+ "<td>"
									+ res[idx].cyber
									+ "</td>"
									+ "<td>"
									+ res[idx].forNative
									+ "</td>"
									+ "<td><a target='_blank' href='/timetable/addTimetable?lectureID="
									+ res[idx].lectureID + "&year=" +res[idx].lectureYear + "&season="+ res[idx].lectureSeason + "'>선택</a></td>" + "</tr>");
				}
			}
		};
		var points = new Array();
		$("input[name='point[]']:checked").each(function() {
			points.push($(this).val());
		});
		var searchParam = {
			"category" : $('#category').jAutochecklist('get'),
			"schedule" : $('#schedule').jAutochecklist('get'),
			"point" : points,
			"cyber" : $("input:checkbox[name='cyber']").is(":checked"),
			"forNative" : $("input:checkbox[name='forNative']").is(":checked")
		};

		$.ajax({
			type : "post",
			url : "/lecture/searchLecture.json",
			contentType : "application/json",
			data : JSON.stringify(searchParam),
			dataType : "json",
			success : showTable
		});
	};
	$(document).ready(function() {
		$('#category').jAutochecklist({
			listWidth : 400
		});

		$('#schedule').jAutochecklist({
			listWidth : 400
		});
	});
</script>

</head>
<body>
	<table class="table table-bordered mg-bottom-lg">
		<colgroup>
			<col style="width: 100px">
			<col>
			<col style="width: 150px">
			<col>
			<col style="width: 150px">
		</colgroup>
		<tr>
			<th class="text-center"><label class="checkbox-inline">
					<strong>학점</strong>
			</label></th>
			<td><label class="checkbox-inline"> <input
					type="checkbox" id="inlineCheckbox1" name="point[]" value="1">
					1학점
			</label> <label class="checkbox-inline"> <input type="checkbox"
					id="inlineCheckbox2" name="point[]" value="2"> 2학점
			</label> <label class="checkbox-inline"> <input type="checkbox"
					id="inlineCheckbox3" name="point[]" value="3"> 3학점
			</label></td>
			<th class="text-center"><label class="checkbox-inline">
					<strong>기타</strong>
			</label></th>
			<td><label class="checkbox-inline"> <input
					type="checkbox" name="forNative"> 원어
			</label> <label class="checkbox-inline"> <input type="checkbox"
					name="cyber"> 사이버
			</label></td>
			<td rowspan="2"><input type=button
				onclick="javascript:searchLecture();return false;" value="확인">
			</td>
		</tr>
		<tr>
			<th class="text-center">강의시간</th>
			<td>
				<ul id="schedule">
					<c:forEach var="weekday" items="${weekdays}">
						<li class="group">${weekday.key }요일전체</li>
						<c:forEach begin="1" end="10" step="1" varStatus="idx">
							<li class="child" data-value="${weekday.value }${idx.index}">
								${weekday.key }요일 ${idx.index}교시</li>
						</c:forEach>
					</c:forEach>
				</ul>
			</td>
			<th class="text-center">카테고리</th>
			<td>
				<ul id="category">
					<c:forEach var="categories" varStatus="idx"
						items="${categoryGroup}">
						<li class="group">--그룹선택--</li>
						<c:forEach var="category" varStatus="idx" items="${categories}">
							<li class="child" data-value="${category.categoryId }">
								${category.categoryName }</li>
						</c:forEach>
					</c:forEach>
				</ul>
			</td>
		</tr>
	</table>
	<table class="bordered">
		<thead>
			<tr>
				<th>학수번호</th>
				<th>과목명</th>
				<th>학년</th>
				<th>교수님</th>
				<th>학점</th>
				<th>강의시간</th>
				<th>사이버</th>
				<th>원어</th>
				<th>선택</th>
			</tr>
		</thead>
		<tbody id="lecture">
			<tr>
				<td colspan="9">강의를 검색해주세요</td>
			</tr>
		</tbody>
	</table>
</body>
</html>