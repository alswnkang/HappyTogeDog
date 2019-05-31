<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" type="text/css" href="/css/adoption_bk.css">  <!-- css -->
<!-- Header -->
<jsp:include page="/WEB-INF/common/header.jsp" />
<style>
	.view{cursor:pointer;}
</style>
<!-- Content -->
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">방문신청 예약 내역</h2>
		<div class="common-box">
		<div class="search-selectDate">
			<form action="/reservationCareMypage" method="post">
				<input type="search" name="startDay" class="datepicker search-word" value="${start}"> ~ <input type="search" name="endDay" class="datepicker search-word" value="${end}">
				<button type="submit" class="bbs-search-btn2" title="검색"><img src="/img/search_icon.png" style="width:30px;"></button>
			</form>
		</div>
			<table class="comm-tbl type2">
				<tr>
					<th>No.</th><th>신청자 아이디</th><th>신청자 이름</th><th>신청자 전화번호</th><th>방문 날짜</th><th>방문 시간</th><th>신청 날짜</th><th>신청 상태</th>
				</tr>
				<c:forEach items="${bp.list }" var="ba">
					<tr onclick="view(${ba.no})" class="view">
						<td>${ba.no}</td>
						<td>${ba.id }</td>
						<td>${ba.name }</td>
						<td>${ba.phone }</td>
						<td>${ba.visitDate }</td>
						<td>${ba.visitTime }</td>
						<td>${ba.applyDate }</td>
						<td>${ba.result }</td>
					</tr>
				</c:forEach>
			</table>
			<div class="paging">${bp.pageNavi}</div>
		</div>
	</div>
</section>	


<script>
		function view(no){
			location.href="/reservationView?no="+no+"&startDay=${start}&endDay=${end}";
		}
</script>

<!-- Footer -->
<jsp:include page="/WEB-INF/common/footer.jsp" />