<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/common/header.jsp" />
<%-- Content --%>

<section id="content-wrapper">
		<div class="area">
		<h2 class="comm-content-tit">글글글</h2>
		<div id="DetailTake" class="common-box">
		
		
		
			<div class="common-tbl-btn-group type2">
			</div>
			<div class="dog-info-top-inner clearfix">
				<div class="dog-info-left">
					<img src="/img/no_detail_img.gif" style="max-width:100%;">
				</div>
				<div class="dog-info-right">
					<table class="comm-tbl">
						<colgroup>
							<col width="25%">
							<col width="/">
						</colgroup>
						<tr>
							<th>제목</th>
							<td>${b.boardTitle }</td>
						</tr>
						<tr>
							<th>내용</th>
							<td>${b.boardContent }</td>
						</tr>
					</table>
				</div>
			</div>
			
		</div>
		<!-- <div class="care-location" id="map"></div> -->
	</div>
</section>

<jsp:include page="/WEB-INF/common/footer.jsp" />