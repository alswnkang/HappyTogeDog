<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<link rel="stylesheet" type="text/css" href="/css/findMydog_DJ.css">

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />
<script src="/js/test.js"></script>

<style>
.board-search-box input{height:40px; border:1px solid #ccc; background:#fff;}
</style>
	
<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">유기동물 검색 서비스</h2>
		<div id="searchDog" class=""><!-- id는 바꿔서 복붙 -->
		
		
		 	<!-- 검색박스 -->
		 	<form action="/printSearchDog">
		 	<div class="board-search-box">
				<select name="kind"><!-- option 세부항목은 각자 알아서 넣으시면 됩니다. -->
					<option value="content">품종</option>
					<c:forEach items="${kind }" var="k">
						<option value="${k.code }">${k.kind }</option>
					</c:forEach>
				</select>
				<select name="happenCity" style="margin-right:7px;">
					<option>도시</option>
					<c:forEach items="${city }" var="c">
						<option value="${c.cityCode }">${c.cityName }</option>
					</c:forEach>
				</select>
				<input type="date" name="startDay" class="datepicker search-day"> ~ <input type="date" name="endDay" class="datepicker search-day">
				<button type="submit" class="bbs-search-btn" title="검색" style="margin-left:5px;"><img src="/img/search_icon.png" style="width:30px;"></button>
			</div>
			</form>
		</div>
	</div>
</section>


<%--footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />