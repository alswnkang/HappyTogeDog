<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<link rel="stylesheet" type="text/css" href="/css/content.css">

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />
<script src="http://code.jquery.com/jquery-3.4.0.min.js"></script><!-- jQuery 선언 -->
	

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">입양하기</h2>
		<ul class="main-adopt-review-list clearfix"><!-- 입양후기는 최소/최대 8개가 노출됩니다. -->
			<c:forEach items="${sdpd.list }" var="m" varStatus="i">
				<li>
					<form action="/dogInfo" method="post" name="form_${i.count}">
						<input type="hidden" name="careNm" value="${m.careNm }">
						<c:if test="${fn:contains(m.careAddr,'(')}">
							<input type="hidden" name="careAddr" value="${fn:split(m.careAddr,'(')[0] }">
						</c:if>
						<c:if test="${fn:contains(m.careAddr,'(')==false}">
							<input type="hidden" name="careAddr" value="${m.careAddr}">
						</c:if>
						<input type="hidden" name="careTel" value="${m.careTel }">
						<input type="hidden" name="kindCd" value="${m.kindCd }">
						<input type="hidden" name="age" value="${m.age }">
						<input type="hidden" name="sexCd" value="${m.sexCd }">
						<input type="hidden" name="specialMark" value="${m.specialMark }">
						<input type="hidden" name="neuterYn" value="${m.neuterYn }">
						<input type="hidden" name="filename" value="${m.filename }">
						<button type="submit" style="display:none;"></button>
						<a onclick="javascript:form_${i.count}.submit();">		<!-- 보호소명 보내기 -->
							<div class="img-thum">
							<span style="background:url('${m.filename }') no-repeat center center; background-size:cover;"></span>
							</div>
							<div class="txt-thum">
							
							<p>보호소명  : ${m.careNm }, 품종 : ${m.kindCd }</p><br>
							<p>성별 :${m.sexCd }
							</div>
						</a>
					</form>
				</li>
			</c:forEach>
		</ul>
		
		
		<!-- paging -->
		<div class="paging">${sdpd.pageNavi}</div>
		
		<div id="searchDog" class="common-tbl-box"><!-- id는 바꿔서 복붙 -->
			<!-- search -->
			<form action="" method="post">
		 		<!-- 검색박스 -->
		 		<div class="board-search-box">
					<select name="search_item"><!-- option 세부항목은 각자 알아서 넣으시면 됩니다. -->
						<option value="subject">지역</option>
						<option value="content">품종</option>
					</select>
					<input placeholder="검색어를 입력해주세요." type="search" name="search_order" class="search-word" value="">
					<button type="submit" class="bbs-search-btn" title="검색"><img src="/img/search_icon.png" style="width:30px;"></button>
				</div>
			</form>
		</div>
	</div>
</section>
	
<%--footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />