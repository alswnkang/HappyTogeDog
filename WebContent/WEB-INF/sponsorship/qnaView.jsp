<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<div class="qna-view">
			<table class="comm-tbl">
				<tr>
					<td>제목</td><td>비밀글 입니다</td>
				</tr>
				<tr>
					<td>작성자</td><td>${orderInfo.name }</td>
				</tr>
				<tr>
					<td>내용</td><td>불량품이에요 바꿔주세요불량품이에요 바꿔주세요불량품이에요 바꿔주세요불량품이에요 바꿔주세요불량품이에요 바꿔주세요불량품이에요 바꿔주세요불량품이에요 바꿔주세요</td>
				</tr>
				<tr>
					<td>파일첨부</td><td>${orderInfo.name }</td>
				</tr>
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