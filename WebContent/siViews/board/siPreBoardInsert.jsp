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
			<h2 class="comm-content-tit">게시글 작성</h2>
			<div class="common-tbl-box">
				<form action="/siPreBoardInsert" method="post" enctype="multipart/form-data"><!-- 파일 첨부를 하기위해 enctype 설정 -->
					<input type="hidden" name="boardType" value="1"/>
					<!-- 자유게시판 구분번호인 1을 submit -->
					<input type="hidden" name="memberId" value="${sessionScope.member.id }"/>
					<!-- 등록한 사람의 아이디 정보를 전달 -->
					<table class="comm-tbl">
						<colgroup>
							<col width="28%">
							<col width="/">
						</colgroup>
						<tr>
							<th>제목</th>
							<td><input type="text" name="boardTitle"/></td>
						</tr>
						<tr>
							<th>파일첨부</th>
							<td><input type="file" name="boardFilename"/></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea type="text" name="boardContent"></textarea></td>
						</tr>
					</table>
					<div class="common-tbl-btn-group">
						<button type="submit" class="btn-style1">등록하기</button>
						<button type="reset" class="btn-style2" onclick="location.href='/siPreBoard'">취소</button>
					</div>
				</form>
			</div>
		</div>
	</section>
</body>
<jsp:include page="/WEB-INF/common/footer.jsp" />
</html>