<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/common/header.jsp" />
<%-- Content --%>

<section name="siSection" id="content-wrapper">
		<div class="area">
			<h2 class="comm-content-tit">강아지를 찾습니다</h2>
			<div class="common-tbl-box">
				<table class="comm-tbl type2">
					<colgroup>
						<col width="5%">
						<col width="15%">
						<col width="">
						<col width="15%">
						<col width="10%">
						<col width="7%">
					</colgroup>
					<thead>
						<tr>
							<th>No.</th>
							<th>사진</th>
							<th>게시글 제목</th>
							<th>작성자</th>
							<th>날짜/시간</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
			 			<c:forEach items="${bp.list }" var="list">
							<tr>
								<td>${list.boardRnum}</td>
								<td><img src='${list.boardFilepath }'></td>
								<td><a href="/detailTakeBoard?boardNo=${list.boardNo }">${list.boardTitle }</a></td>
								<!-- name 값을 넘겨주도록 설정필요 -->
								<td>${list.boardName }(${list.boardId })</td>
								<td>${list.boardDate }</td>
								<td>${list.boardCount }</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="5" style="text-align:center;">
								<div>${bp.pageNavi }</div>
							</td>
						</tr>
					</tbody>
				</table>
				<form action="/takeBoardSearch" method="post">
					<div class="board-search-box">
						<select name="searchWord">
							<option value="boardName">작성자</option>
							<option value="boardTitle">글 제목</option>
						</select>
						<input type="text" name="keyword">
						<button type="submit" class="bbs-search-btn">검색</button>
						<c:if test="${not empty sessionScope.member.id }">
						<!-- 로그인이 되있어야 글쓰기버튼 활성화 -->
							<button type="button" class="bbs-search-btn" style="float:right;" onclick="location.href='/findBoardInsert'">글쓰기</button>
						</c:if>
					</div>
				</form>
			</div>
		</div>
</section>

<jsp:include page="/WEB-INF/common/footer.jsp" />