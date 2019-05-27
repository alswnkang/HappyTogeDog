<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css" href="/css/content.css">
<link rel="stylesheet" type="text/css" href="/css/findMydog_DJ.css">

	<%-- Header --%>
	<jsp:include page="/WEB-INF/common/header.jsp" />
	
	<%--Content --%>
	<section id="content-wrapper">
		<div class="area">
			<h2 class="comm-content-tit">실종유기견 찾기</h2>
			<div id="findMydog" class="common-tbl-box">	
				<div class="">
					<ul>
						<li><a href="/finddog">보호중인 유기견</a></li>
						<li><a href="/lostdog">강아지를 찾습니다</a></li>
						<li><a href="/searchDog">유기견검색</a></li>
					</ul>
				</div>
			</div>
		</div>
	</section>



	<%--footer --%>
	<jsp:include page="/WEB-INF/common/footer.jsp" />