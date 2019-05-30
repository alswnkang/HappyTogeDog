<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />
<script type="text/javascript" src="/js/qna.js"></script>

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">Q & A</h2>
		<div id="qnaListBox" class="common-tbl-box">
				<table class="comm-tbl type2">
					<colgroup>
						<col width="5%">
						<col width="10%">
						<col width="">
						<col width="15%">
						<col width="15%">
					</colgroup>
					<thead>
						<tr>
							<th>No.</th>
							<th>답변상태</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${qnaList.qnainfoList}" var="qna">
							<tr>
							<td>${qna.boardRnum }</td>
							<td>
								<c:if test="${qna.boardCount eq 0 }">
									<span class="volun-status ing">답변대기</span>
								</c:if>
								<c:if test="${qna.boardCount eq 1 }">
									<span class="volun-status end">답변완료</span>
								</c:if>
							</td>
							<td>
								<p class="volun-tit">
									<c:if test="${qna.boardSecret eq 0 }">
										<a href="/qnaView?boardNo=${qna.boardNo }">${qna.boardTitle }</a>
									</c:if>
									<c:if test="${qna.boardSecret eq 1 }">
										<a href="/checkPw?boardNo=${qna.boardNo }">비밀글 입니다.</a>
									</c:if>
								</p>
							</td>
							<td>${qna.boardName }</td>	
							<td>${qna.boardDate }</td>						
						</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- paging -->
				<div class="paging">
		 			${qnaList.pageNavi }
		 		</div>
		 		<!-- 검색박스 -->
		 		<div class="board-search-box">
		 			<form action="/qnaList" method="post" name="search">
		 				<input type="hidden" name="reqPage">
						<select name="searchType" data-val="${search.searchType }">
							<option value="board_title">제목</option>
							<option value="board_name">작성자</option>
						</select>
						<input placeholder="검색어를 입력해주세요." type="search" name="searchVal" class="search-word" value="${search.searchVal }">
						<button type="submit" class="bbs-search-btn" title="검색"><img src="/img/search_icon.png" style="width:30px;"></button>
					</form>
				</div>
		</div>
	</div>
</section>

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />