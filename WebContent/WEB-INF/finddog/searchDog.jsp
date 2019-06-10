<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css" href="/css/findMydog_DJ.css">

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />
	
<script src="/js/test.js"></script>	
	
<%-- Content --%>
<script type="text/javascript">
    $(function() {
       test.init();
    });
 </script>
	
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">유기동물 조회 서비스</h2>
		<div id="searchDog" class=""><!-- id는 바꿔서 복붙 -->
			<table class="comm-tbl type2"><!-- 신청목록게시판은 한페이지에 게시물 최대 10개 노출 -->
				<colgroup>
					<col width="5%">
					<col width="">
					<col width="15%">
					<col width="18%">
					<col width="10%">
					<col width="25%">
					<col width="15%">
				</colgroup>
				<thead>
					<tr>
						<th>No.</th>
						<th>사진</th>
						<th>보호센터</th>
						<th>발견장소</th>
						<th>발견시간</th>
						<th>특징</th>
					</tr>
				</thead>
				<tbody >
					<c:forEach items="${sdpd.list }" var="m" varStatus="i">
					<tr>
						<td>${i.count }</td>
						<td><img src=${m.filename } style="height: 200px; width: 200px;" ></td>
						<td>${m.careNm }</td>
						<td>${m.happenPlace }</td>
						<td>${m.happenDt }</td>
						<td>${m.noticeNo }</td>
					</tr>
					</c:forEach>
					<c:if test="${empty sdpd.list }">
						<tr>
							<td colspan="6">
								<p class="none">조회 결과가 없습니다.</p>
							</td>
						</tr>
					</c:if>
				</tbody>
			</table>
			<!-- paging -->
			<div class="paging">
				${sdpd.pageNavi }
	 		</div>
	 		
	 		<table class="comm-tbl type2"><!-- 신청목록게시판은 한페이지에 게시물 최대 10개 노출 -->
				<colgroup>
					<col width="5%">
					<col width="">
					<col width="15%">
					<col width="18%">
					<col width="10%">
					<col width="25%">
					<col width="15%">
				</colgroup>
				<thead>
					<tr>
						<th>No.</th>
						<th>사진</th>
						<th>보호자</th>
						<th>제목</th>
						<th>발견장소</th>
						<th>발견시간</th>
						
					</tr>
				</thead>
				<tbody >
					<c:forEach items="${sdpd2.list }" var="m" varStatus="i">
					<tr>
						<td>${i.count }</td>
						<td><a href="/detailTakeBoard?boardNo=${m.boardNo }"><img src="/siUpload/board/${m.boardFilepath }" style="height: 200px; width: 200px;" ></a></td>
						<td><a href="/detailTakeBoard?boardNo=${m.boardNo }">${m.boardName }</a></td>
						<td><a href="/detailTakeBoard?boardNo=${m.boardNo }">${m.boardTitle }</a></td>
						<td>${m.happenCity }</td>
						<td>${m.happenDate }</td>
					

					</tr>
					</c:forEach>
					<c:if test="${empty sdpd2.list }">
						<tr>
							<td colspan="6">
								<p class="none">조회 결과가 없습니다.</p>
							</td>
						</tr>
					</c:if>
				</tbody>
			</table>
				<!-- paging -->
			<div class="paging">
				${sdpd2.pageNavi }
	 		</div>
	 		
	 		<!-- 검색박스 -->
	 		 	<!-- 검색박스 -->
		 	<form action="/printSearchDog">
		 	<div class="board-search-box">
				<select name="kind"><!-- option 세부항목은 각자 알아서 넣으시면 됩니다. -->
					<option value="content">품종</option>
					<c:forEach items="${kind }" var="k">
						<option value="${k.code }">${k.kind }</option>
					</c:forEach>
				</select>
				<select name="happenCity" style="margin-right:7px;">
					<option>도시</option>
					<c:forEach items="${city }" var="c">
						<option value="${c.cityCode }">${c.cityName }</option>
					</c:forEach>
				</select>
				<input type="date" name="startDay" class="datepicker search-day"> ~ <input type="date" name="endDay" class="datepicker search-day">
				<button type="submit" class="bbs-search-btn" title="검색" style="margin-left:5px;"><img src="/img/search_icon.png" style="width:30px;"></button>
			</div>
			</form>
			
		</div>
	</div>
</section>


	
	
	
	
	
	
	
	
	
	
	
	<%--footer --%>
	<jsp:include page="/WEB-INF/common/footer.jsp" />