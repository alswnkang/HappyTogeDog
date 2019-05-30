<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />

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
					<td>내용</td><td>${qna.boardContent }</td>
				</tr>
				<c:if test="${not empty qna.boardFilename }">
					<tr>
						<td>파일첨부</td><td>${qna.boardFilename }</td>
					</tr>
				</c:if>
			</table>
			<c:if test="${sessionScope.member.id ne 'admin' }">
				<textarea rows="" cols=""></textarea>
				<button class="order-btn">댓글작성..</button>
			</c:if>
		</div>
		
	</div>
</section>

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />