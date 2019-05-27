<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>	<!-- 문자열 처리를 위해 -->
<link rel="stylesheet" type="text/css" href="/css/reservationContent_bk.css">
<!--  Header -->
<jsp:include page="/WEB-INF/common/header.jsp" />

<!-- content -->
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">방문예약 신청 완료</h2>
			<div id="reservationBox" class="common-tbl-box">
				<table class="comm-tbl">
					<colgroup>
						<col width="35%">
						<col width="/">
					</colgroup>
					<tr>
						<th>신청자 이름</th>
						<td>${ba.name }</td>
					</tr>
					<tr>
						<th>보호소명</th>
						<td>${care[0]}</td>
					</tr>
					<tr>
						<th>보호소주소</th>
						<td>${care[1]}</td>
					</tr>
					<tr>
						<th>보호소전화번호</th>
						<td>${care[2]}</td>
					</tr>
					<tr>
						<th>방문 날짜</th>
						<td>${fn:substring(ba.visitDate,0,4)}년  
							${fn:substring(ba.visitDate,5,7)}월  
							${fn:substring(ba.visitDate,8,10)}일  
							${fn:substring(ba.visitTime,0,2)}시 ~ 
							${fn:substring(ba.visitTime,2,4)}시 
						</td>	<!-- 날짜 시간 결과 확인하기 -->
					</tr>
					<tr>
						<th>신청날짜</th>
						<td id="apply-date"></td>
					</tr>
				</table>
				<div class="common-tbl-btn-group">
					<button type="button" class="btn-style1" onclick="location.href='/'">메인으로</button><button type="button" class="btn-style2" onclick="location.href='##'">어디로 갈까</button>
				</div>
			</div>
	</div>
</section>

<script>
	//신청날짜에 오늘날짜 넣어주기
	$(document).ready(function(){
		var d = new Date();
		var date = d.getFullYear() + '년 ' + (d.getMonth() + 1) + '월 ' + d.getDate()+'일';
		$("#apply-date").text(date);
	});
</script>



<!-- Footer -->
<jsp:include page="/WEB-INF/common/footer.jsp" />