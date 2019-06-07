<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
				<form action="/siNoticeInsert" method="post" enctype="multipart/form-data"><!-- 파일 첨부를 하기위해 enctype 설정 -->
					<input type="hidden" name="noticeType" value="0"/>
					<!-- 자유게시판 구분번호인 1을 submit -->
					<input type="hidden" name="memberId" value="${sessionScope.member.id }"/>
					<!-- 등록한 사람의 아이디 정보를 전달 -->
					<input type="hidden" name="memberName" value="${sessionScope.member.name }"/>
					<fmt:formatDate value="${list.noticeDate }" pattern="yyyy-MM-dd hh:mm"/>
					<table class="comm-tbl">
						<colgroup>
							<col width="28%">
							<col width="/">
						</colgroup>
						<tr>
							<th style="text-align:center;">제목</th>
							<td><input type="text" name="noticeTitle" placeholder="글 제목" maxlength="50"/></td>
						</tr>
						<tr>
							<th style="text-align:center;">파일첨부</th>
							<td><input type="file" name="noticeFilename"/></td>
						</tr>
						<tr>
							<th height="100" style="text-align:center;">내용</th>
							<td><textarea rows="10" type="text" name="noticeContent" placeholder="글 내용" maxlength="2048"></textarea></td>
						</tr>
					</table>
					<div class="common-tbl-btn-group">
						<button type="submit" class="btn-style1">등록하기</button>
						<button type="reset" class="btn-style2" onclick="location.href='/siNotice'">취소</button>
					</div>
				</form>
			</div>
		</div>
	</section>
</body>
<jsp:include page="/WEB-INF/common/footer.jsp" />
</html>