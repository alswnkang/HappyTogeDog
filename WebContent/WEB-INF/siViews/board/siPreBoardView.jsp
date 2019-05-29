<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
	<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
	<style>
		[name=siSection]{
			width: 700px;
			height: 1200px;
		}
	</style>
<body>
	<section name="siSection">
		<form action="/siPreBoardUpdateOriginal?boardNo=${board.boardNo }" method="post" enctype="multipart/form-data">
			<table style="border:1px solid black;width: 700px;">
				<tr>
					<th>제목</th>
					<td>${board.boardTitle }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<c:if test="${not empty board.boardFilename }">
							<img src='/siUpload/board/${board.boardFilename }' width="300px" />
							<!-- 파일이 있으면 넘겨준 No를 기준으로 게시물의 이름을 불러와서 출력 -->
						</c:if>
						<br/>
						${board.boardContent }
					</td>
				</tr>
				<tr>
					<th>댓글</th>
					<td><input type="text" name="boardComment"/></td>
				</tr>
			</table>
			<c:if test='${sessionScope.memberId==board.boardId || sessionScope.memberId eq "admin" }'>
			<!-- 회원 아이디와 글 작성자의 아이디가 같거나 관리자라면 수정/삭제 버튼 생성 -->
				<button type="submit">수정</button>
				<a href="/siPreBoardDelete?boardNo=${board.boardNo }" style="text-decoration:none;">
					<button type="button">삭제</button>
				</a>
				<!-- 게시글 번호를 siPreBoardDelete?boardNo 서블릿에 전달-->
			</c:if>
			<a href="/siPreBoard"><button type="button">목록으로 이동</button></a>
		</form>
	</section>
</body>
</html>