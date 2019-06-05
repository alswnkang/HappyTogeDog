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
			<h2 class="comm-content-tit">공지사항</h2>
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
			 			<c:forEach items="${np.list }" var="list">
			 			
							<c:if test="${list.noticeType == 0 }">
								<tr>
									<td>${list.noticeRnum}</td>
									<td><a href="/siNoticeView?noticeNo=${list.noticeNo }">${list.noticeTitle }</a></td>
									<!-- name 값을 넘겨주도록 설정필요 -->
									<td>${list.noticeName }(${list.noticeId })</td>
									<td>
										${list.noticeDate2 }
									</td>
									<td>${list.noticeCount }</td>
								</tr>
							</c:if>
						</c:forEach>
						<tr>
							<td colspan="5" style="text-align:center;">
								<div>${np.pageNavi }</div>
							</td>
						</tr>
					</tbody>
				</table>
				<form action="/siNoticeSearch" method="post">
					<div class="board-search-box">
						<select name="searchWord">
							<option value="noticeName">작성자</option>
							<option value="noticeTitle">글 제목</option>
						</select>
						<input type="text" name="keyword">
						<button type="submit" class="bbs-search-btn">검색</button>
						<c:if test="${not empty sessionScope.member.id && sessionScope.member.id eq 'admin' }">
						<!-- 로그인이 되어있고 관리자일 경우 글쓰기버튼 활성화 -->
							<button type="button" class="bbs-search-btn" style="float:right;" onclick="location.href='/siViews/notice/siNoticeInsert.jsp'">글쓰기</button>
						</c:if>
					</div>
				</form>
			</div>
		</div>
	</section>
</body>
<jsp:include page="/WEB-INF/common/footer.jsp" />
</html>