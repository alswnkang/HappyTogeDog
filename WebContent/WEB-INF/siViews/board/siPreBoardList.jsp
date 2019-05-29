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
	a{
		text-decoration: none;
		color: black;
	}
	[name=siSection]{
		width: 700px;
		height: 1200px;
	}
</style>
<body>
	<section name="siSection">
		<form action="/siPreBoardSearch" method="get">
			<table style="border:1px solid black;width: 700px;">
				<tr>
					<th colspan="5">자유게시판</th>
				</tr>
				<tr>
					<th style="width: 10%;">번호</th>
					<th style="width: 40%;">제목</th>
					<th style="width: 25%;">글쓴이</th>
					<th style="width: 20%;">날짜</th>
					<th style="width: 20%;">조회</th>
				</tr>
	 			<c:forEach items="${bp.list }" var="list" varStatus="i">
					<tr style="text-align:center;">
						<td>${list.boardNo}</td>
						<td><a href="/siPreBoardView?boardNo=${list.boardNo }">${list.boardTitle }</a></td>
						<!-- name 값을 넘겨주도록 설정필요 -->
						<td>${list.boardName }</td>
						<td>${list.boardDate }</td>
						<td>${list.boardCount }</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="5" style="text-align:center;">
						<div>${bp.pageNavi }</div>
					</td>
				</tr>
			</table>
			<br/>
			<select name="searchWord" style="margin-left:30%;padding-bottom:4px;padding-top:0.8px;">
				<option value="boardName">작성자</option>
				<option value="boardTitle">글 제목</option>
			</select>
			<input type="text" name="keyword" style="padding-bottom:1px;padding-top:4px;">
			<button type="submit">검색</button>
			<a href="/siViews/board/siPreBoardInsert.jsp" style="margin-left:19%;">
				<button type="button">글쓰기</button>
			</a>
		</form>
	</section>
</body>
</html>