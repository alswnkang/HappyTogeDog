<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/common/header.jsp" />
</head>
<body>
	<section name="siSection" id="content-wrapper">
		<div class="area">
			<div class="voluntary-box">
				<!-- 자유게시판 게시글 조회 -->
				<form action="/siPreBoardUpdateOriginal?boardNo=${board.boardNo }" method="post" enctype="multipart/form-data">
					<table class="comm-tbl view">
						<colgroup>
							<col width="20%">
							<col width="/">
							<col width="/">
							<col width="100%">
						</colgroup>
						<tr>
							<th>제목</th>
							<td>${board.boardTitle }</td>
						</tr>
						<tr>
							<th>내용</th>
							<td>
								<c:if test="${not empty board.boardFilename }">
									<img src='/siUpload/board/${board.boardFilename }'width="800px"/>
									<!-- 파일이 있으면 넘겨준 No를 기준으로 게시물의 이름을 불러와서 출력 -->
									<br/>
									${board.boardContent }
								</c:if>
								<c:if test="${empty board.boardFilename }">
									${board.boardContent }
								</c:if>
							</td>
						</tr>
						<!-- 댓글 기능은 아직 미구현(임시로 적어놓기만) -->
						<tr>
							<th>댓글</th>
							<td><input type="text" name="boardComment"/></td>
						</tr>
						<tr>
							<th>댓글 작성자</th><th>댓글 내용 , 댓글 작성일</th>
						</tr>
					</table>
					<div class="common-tbl-btn-group" style="text-align:right;">
						<c:if test='${sessionScope.member.id==board.boardId || sessionScope.member.id eq "admin" }'>
						<!-- 회원 아이디와 글 작성자의 아이디가 같거나 관리자라면 수정/삭제 버튼 생성 -->
							<button type="submit" class="btn-style3">수정</button>
							<button type="button" class="btn-style3" onclick='location.href="/siPreBoardDelete?boardNo=${board.boardNo }"'>삭제</button>
							<!-- 게시글 번호를 siPreBoardDelete?boardNo 서블릿에 전달-->
						</c:if>
						<button type="button" class="btn-style2" onclick="location.href='/siPreBoard'">목록으로 이동</button>
					</div>
				</form>
			</div>
		</div>
	</section>
</body>
<jsp:include page="/WEB-INF/common/footer.jsp" />
</html>