<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />
<link rel="stylesheet" type="text/css" href="/css/qna.css">

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
					<td>내용</td><td>${qna.boardContent }${qna.boardId }</td>
				</tr>
				<c:if test="${not empty qna.boardFilename }">
					<tr>
						<td>파일첨부</td><td>${qna.boardFilename }</td>
					</tr>
				</c:if>
				<c:if test="${sessionScope.member.id ne 'admin' }">
					<tr><td colspan="2">
						<form>
							<input type="hidden" name="" value="">
							<textarea name="commentContent" rows="3"></textarea>
							<button type="submit" class="commentRegi">등록</button>
						</form>
					</td></tr>
				</c:if>
			</table>
			
			<div class="common-tbl-btn-group">
				<c:if test="${not empty sessionScope.member}">
					<c:if test="${sessionScope.member.id eq qna.boardId }">
						<button class="btn-style1 sm">수정</button>
						<button class="btn-style2 sm">삭제</button>
					</c:if>
				</c:if>
				<button class="btn-style3" onclick="location.href='/qnaList'">목록으로</button>
			</div>
		</div>
		
		
	</div>
</section>

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />