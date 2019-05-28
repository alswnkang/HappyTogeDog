<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">Q & A</h2>
		<div id="qnaListBox" class="common-tbl-box"><!-- id는 바꿔서 복붙 -->
			<form action="" method="post">
				<table class="comm-tbl type2"><!-- 신청목록게시판은 한페이지에 게시물 최대 10개 노출 -->
					<colgroup>
						<col width="5%">
						<col width="10%">
						<col width="">
						<col width="15%">
						<col width="15%">
					</colgroup>
					<thead>
						<tr>
							<th>No.</th>
							<th>답변상태</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1</td>
							<td><span class="volun-status ing">답변대기</span></td>
							<td><p class="volun-tit"><a href="/qnaView">비밀글입니다.</a></p></td>
							<td>작성자</td>	
							<td>2019-05-24</td>						
						</tr>
						<tr>
							<td>2</td>
							<td><span class="volun-status end">답변완료</span></td>
							<td><p class="volun-tit"><a href="/qnaView">배송 언제 되나요</a></p></td>
							<td>작성자</td>
							<td>2019-05-24</td>
						</tr>
					</tbody>
				</table>
				<!-- paging -->
				<div class="paging">
		 			<a href="" class="paging-arrow prev-arrow"><img src="/img/left_arrow.png" style="width:30px;height:30px;"></a>
		 			<a href="" class="cur">1</a>
		 			<a href="">2</a>  
		 			<a href="">3</a>
		 			<a href="">4</a>
		 			<a href="">5</a>
		 			<a href="" class="paging-arrow next-arrrow"><img src="/img/right_arrow.png" style="width:30px;height:30px;"></a>
		 		</div>
		 		<!-- 검색박스 -->
		 		<div class="board-search-box">
					<select name="search_item"><!-- option 세부항목은 각자 알아서 넣으시면 됩니다. -->
						<option value="subject">제목</option>
						<option value="content">내용</option>
					</select>
					<input placeholder="검색어를 입력해주세요." type="search" name="search_order" class="search-word" value="">
					<button type="submit" class="bbs-search-btn" title="검색"><img src="/img/search_icon.png" style="width:30px;"></button>
				</div>
			</form>
		</div>
	</div>
</section>

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />