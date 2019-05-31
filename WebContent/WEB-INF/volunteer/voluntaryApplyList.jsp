<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
    
<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">봉사활동 신청 내역</h2>
		<div id="voluntaryListBox">
			<table class="comm-tbl type2"><!-- 신청목록게시판은 한페이지에 게시물 최대 10개 노출 -->
				<colgroup>
					<col width="5%">
					<col width="">
					<col width="15%">
					<col width="10%">
					<col width="23%">
					<col width="10%">
				</colgroup>
				<thead>
					<tr>
						<th>No.</th>
						<th>봉사활동 제목</th>
						<th>봉사활동 <br>날짜 / 시간</th>
						<th>신청한 <br>인원 수</th>
						<th>보호소명</th>
						<th>신청일</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="">
						<c:forEach items="" var="" varStatus="i">
							<tr>
								<td>${i.count }</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<!-- paging -->
			<div class="paging">
	 			${vld.pageNavi }
	 		</div>
	 	</div>
	 </div>
</section>


<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />