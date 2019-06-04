<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />
<link rel="stylesheet" type="text/css" href="/css/qna.css">
<script type="text/javascript" src="/js/qna.js"></script>

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">Q & A</h2>
		<div class="qna-view">
			<table class="comm-tbl">
				<tr>
					<td>제목</td><td>${qna.boardTitle }</td>
				</tr>
				<tr>
					<td>작성자</td><td>${qna.boardName }</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<c:if test="${not empty qna.boardFilename }">
							<img width="100%" src="/upload/qna/${qna.boardFilepath }">
						</c:if>
						${qna.boardContent }
					</td>
				</tr>
				<!-- 첨부파일 이미지를 내용에 넣을까 다운로드로 할까 고민중
				<c:if test="${not empty qna.boardFilename }">
					<tr>
						<td>파일첨부</td><td>${qna.boardFilename }</td>
					</tr>
				</c:if>
				-->
				<c:if test="${not empty comment.boardCommentContent }">
					<tr>
						<td>답변</td><td>${comment.boardCommentContent }</td>
					</tr>
				</c:if>
				<c:if test="${empty comment.boardCommentContent && not empty sessionScope.member}">
					<c:if test="${sessionScope.member.memberLevel eq 2}">
						<tr><td colspan="2">
							<form action="insertComment" method="post">
								<input type="hidden" name="boardRef" value="${qna.boardNo }">
								<textarea name="boardCommentContent" rows="3"></textarea>
								<button type="submit" class="commentRegi">등록</button>
							</form>
						</td></tr>
					</c:if>
				</c:if>
			</table>
			
			<div class="common-tbl-btn-group">
					<button class="btn-style1 sm">수정</button>
					<button class="btn-style2 sm">삭제</button>
				<button class="btn-style3" onclick="list(${search.reqPage });">목록으로</button>
			</div>
			<form action="${pageName}" method="post" name="search">
		 		<input type="hidden" name="reqPage">
		 		<input type="hidden" name="searchType" value="${search.searchType }">
		 		<input type="hidden" name="searchVal" value="${search.searchVal }">
			</form>
		</div>
		
		
	</div>
</section>

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />