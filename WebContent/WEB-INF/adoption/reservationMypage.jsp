<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" type="text/css" href="/css/adoption_bk.css">  <!-- css -->
<!-- Header -->
<jsp:include page="/WEB-INF/common/header.jsp" />

<!-- 보호소 코드 디비에 저장해야하기 때문에 전 페이지에서 받아와야함-->
<!-- Content -->
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">방문신청 예약 내역</h2>
		<div id="dogInfo" class="common-box">
			<table class="comm-tbl type2">
				<tr>
					<th>No.</th><th>보호소명</th><th>방문 날짜</th><th>방문 시간</th><th>신청 날짜</th><th>신청 상태</th>
				</tr>
				<c:forEach items="${bp.list }" var="ba">
					<tr>
						<td>${ba.no}</td>
						<td>${ba.code }</td>
						<td>${ba.visitDate }</td>
						<td>
							${fn:substring(ba.visitTime,0,2)}시 ~ 
							${fn:substring(ba.visitTime,2,4)}시 
						</td>
						<td>${ba.applyDate }</td>
						<td>${ba.result }</td>
					</tr>
				</c:forEach>
			</table>
			<div class="paging">${bp.pageNavi}</div>
		</div>
	</div>
</section>


<!-- Footer -->
<jsp:include page="/WEB-INF/common/footer.jsp" />