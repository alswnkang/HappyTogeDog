<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css" href="/css/content.css">
<link rel="stylesheet" type="text/css" href="/css/findMydog_DJ.css">

	<%-- Header --%>
	<jsp:include page="/WEB-INF/common/header.jsp" />
	<script src="http://code.jquery.com/jquery-3.4.0.min.js"></script><!-- jQuery 선언 -->
	
	
	
	<%-- Content --%>
	
<script src="/js/test.js"></script>
	
	

	
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">유기동물 검색 서비스</h2>
		<div id="searchDog" class="common-tbl-box"><!-- id는 바꿔서 복붙 -->
		
		
		 	<!-- 검색박스 -->
		 	<form action="/printSearchDog">
		 	<div class="board-search-box">
				<select name="kind"><!-- option 세부항목은 각자 알아서 넣으시면 됩니다. -->
					<option value="content">품종</option>
					<c:forEach items="${kind }" var="k">
						<option>${k.kind }</option>
					</c:forEach>
				</select>
				<select name="happenCity">
					<option>도시</option>
					<c:forEach items="${city }" var="c">
						<option>${c.cityName }</option>
					</c:forEach>
				</select>
				<br>
				<select name="shappenDateY" style="width:100px;">
					<option>년</option>
					<c:forEach items="${yList }" var="y"  varStatus="i">
						<option>${y }</option>
					</c:forEach>
				</select>
				<select name="shappenDateM" style="width:50px;">
					<option>월</option>
					<c:forEach items="${mList }" var="y"  varStatus="i">
						<option>${y }</option>
					</c:forEach>
				</select>
				<select name="shappenDateM" style="width:50px;">
					<option>일</option>
					<c:forEach items="${dList }" var="y"  varStatus="i">
						<option>${y }</option>
					</c:forEach>
				</select>
				~
				<select name="ehappenDateY" style="width:100px;">
					<option>년</option>
					<c:forEach items="${yList }" var="y"  varStatus="i">
						<option>${y }</option>
					</c:forEach>
				</select>
				<select name="ehappenDateM" style="width:50px;">
					<option>월</option>
					<c:forEach items="${mList }" var="y"  varStatus="i">
						<option>${y }</option>
					</c:forEach>
				</select>
				<select name="ehappenDateM" style="width:50px;">
					<option>일</option>
					<c:forEach items="${dList }" var="y"  varStatus="i">
						<option>${y }</option>
					</c:forEach>
				</select>
				<br>
				
		
				<button type="submit" class="bbs-search-btn" title="검색"><img src="/img/search_icon.png" style="width:30px;"></button>
			</div>
			</form>
		</div>
	</div>
</section>


	<%--footer --%>
	<jsp:include page="/WEB-INF/common/footer.jsp" />