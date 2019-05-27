<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css" href="/css/content.css">
<link rel="stylesheet" type="text/css" href="/css/findMydog_DJ.css">

	<%-- Header --%>
	<jsp:include page="/WEB-INF/common/header.jsp" />
	<script src="http://code.jquery.com/jquery-3.4.0.min.js"></script><!-- jQuery 선언 -->
	
	
	
	<%-- Content --%>
	
		<script src="/js/test.js"></script>
	
	
	<script type="text/javascript">
      $(function() {
         test.init();
      });
   </script>
	
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">유기동물 조회 서비스</h2>
		<div id="searchDog" class="common-tbl-box"><!-- id는 바꿔서 복붙 -->
			<form action="" method="post">
				<table class="comm-tbl type2"><!-- 신청목록게시판은 한페이지에 게시물 최대 10개 노출 -->
					<colgroup>
						<col width="5%">
						<col width="">
						<col width="10%">
						<col width="18%">
						<col width="10%">
						<col width="25%">
						<col width="15%">
					</colgroup>
					<thead>
						<tr>
							<th>No.</th>
							<th>공고번호</th>
							<th>품종</th>
							<th>발견장소</th>
							<th>보호센터</th>
							<th>보호주소</th>
							<th>특징</th>
						</tr>
					</thead>
					<tbody >
					<c:forEach items="${list }" var="m" varStatus="i">
					<tr>
						<td>${i.count }</td>
						<td><img src=${m.filename }></td>
						<td>${m.careNm }</td>
						<td>${m.careTel }</td>
						<td>${m.happenPlace }</td>
						<td>${m.happenDt }</td>
						<td>${m.noticeNo }</td>
					</tr>
					
					</c:forEach>
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
						<option value="subject">지역</option>
						<option value="content">품종</option>
					</select>
					<input placeholder="검색어를 입력해주세요." type="search" name="search_order" class="search-word" value="">
					<button type="submit" class="bbs-search-btn" title="검색"><img src="/img/search_icon.png" style="width:30px;"></button>
				</div>
			</form>
		</div>
	</div>
</section>


	
	
	
	
	
	
	
	
	
	
	
	<%--footer --%>
	<jsp:include page="/WEB-INF/common/footer.jsp" />