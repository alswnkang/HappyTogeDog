<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<jsp:include page="/WEB-INF/common/header.jsp" />
<body>
	<section name="siSection" id="content-wrapper">
		<div class="area">
			<h2 class="comm-content-tit">자유게시판</h2>
			<div class="common-tbl-box">
				<table class="comm-tbl type2">
					<colgroup>
						<col width="5%">
						<col width="">
						<col width="15%">
						<col width="10%">
						<col width="7%">
					</colgroup>
					<thead>
						<tr>
							<th>No.</th>
							<th>게시글 제목</th>
							<th>작성자</th>
							<th>날짜/시간</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
			 			<c:forEach items="${list }" var="b" varStatus="i">
							<tr>
								<td>${i.count}</td>
								<td><a href="/siPreBoardView?boardNo=${b.boardNo }">${b.boardTitle }</a></td>
								<!-- name 값을 넘겨주도록 설정필요 -->
								<td>${b.boardId }</td>
								<td>${b.boardDate }</td>
								<td>${b.boardCount }</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="5" style="text-align:center;">
								<div>${bp.pageNavi }</div>
							</td>
						</tr>
					</tbody>
				</table>
				<form action="/siPreBoardSearch" method="get">
					<div class="board-search-box">
						<select name="searchWord">
							<option value="boardName">작성자</option>
							<option value="boardTitle">글 제목</option>
						</select>
						<input type="text" name="keyword">
						<button type="submit" class="bbs-search-btn">검색</button>
						<button type="button" class="bbs-search-btn" style="float:right;" onclick="location.href='/siViews/board/siPreBoardInsert.jsp'">글쓰기</button>
					</div>
				</form>
			</div>
		</div>
	</section>
</body>
<jsp:include page="/WEB-INF/common/footer.jsp" />
</html>