<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/common/header.jsp" />
<link rel="stylesheet" type="text/css" href="/css/style.css">

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<div class="order-success">
			<p class="main-comm-tit">주문이 완료되었습니다.</p>
			<c:if test="${empty orderInfo.id }">
				<p class="order-no">주문번호 : <span> ${orderInfo.no }</span><p>
				<p class="order-no">비회원은 주문번호로 주문조회가 가능합니다.</p>
			</c:if>
			<button class="order-btn" onclick="location.href='/findOrder.jsp'">주문 내역 확인</button>
		</div>
		
	</div>
</section>
<jsp:include page="/WEB-INF/common/footer.jsp" />