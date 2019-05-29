<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section name="siSection">
		<form action="/siPreBoardInsert" method="post" enctype="multipart/form-data"><!-- 파일 첨부를 하기위해 enctype 설정 -->
			<table style="border:1px solid black;width: 700px;">
				<tr>
					<th>게시글작성</th>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="boardTitle"/></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>
						${sessionScope.memberId} <!-- 로그인 정보 합치면 변경, 임시 session memberId 값 -->
						<input type="hidden" name="boardName" value="${sessionScope.memberId }"/>
					</td>
				</tr>
				<tr>
					<th>파일첨부</th>
					<td><input type="file" name="boardFilename"/></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea type="text" name="boardContent"></textarea></td>
				</tr>
				<tr>
					<td>
						<button type="submit">등록하기</button>
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>