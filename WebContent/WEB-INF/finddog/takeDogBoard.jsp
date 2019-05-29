<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/common/header.jsp" />
<%-- Content --%>

<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">입양하기</h2>
		<ul class="main-adopt-review-list clearfix"><!-- 입양후기는 최소/최대 8개가 노출됩니다. -->
			<c:forEach items="${tdpd.list }" var="m" varStatus="i">
				<li>
					<a href="/takeDogBoard">
							<div class="img-thum">
							<span style="background:url('${m.filename }') no-repeat center center; background-size:cover;"></span>
							</div>
							<div class="txt-thum">
							
							<p>제목 : ${m.textTitle }</p><br>
							
						</div>
					</a>
				</li>
			</c:forEach>
		</ul>
		
		<!-- paging -->
		<div class="paging">${tdpd.pageNavi }</div>
		
		<div id="searchDog" class="common-tbl-box"><!-- id는 바꿔서 복붙 -->
			<!-- search -->
			<form action="" method="post">
		 		<!-- 검색박스 -->
		 		<div class="board-search-box">
					<select name="search_item">
						<option value="subject">제목</option>
						<option value="content">작성자</option>
					</select>
					<input placeholder="검색어를 입력해주세요." type="search" name="search_order" class="search-word" value="">
					<button type="submit" class="bbs-search-btn" title="검색"><img src="/img/search_icon.png" style="width:30px;"></button>
				</div>
			</form>
		</div>
	</div>
</section>

<jsp:include page="/WEB-INF/common/footer.jsp" />