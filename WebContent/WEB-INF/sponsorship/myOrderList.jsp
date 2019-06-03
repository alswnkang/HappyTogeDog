<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />
<link rel="stylesheet" type="text/css" href="/css/style.css">
<script type="text/javascript" src="/js/order.js"></script>

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">나의 후원 내역</h2>
		<div id="orderListBox" class="common-tbl-box">
			<!-- 검색박스 -->
		 		<div class="board-search-box order-search">
		 			<form action="/myOrderList" method="post" name="search">
		 				<input type="hidden" name="reqPage" value="">
			 			<input type="search" name="startDay" class="datepicker search-day" value="${search.startDay }"> ~ <input type="search" name="endDay" class="datepicker search-day" value="${search.endDay }">
			 			&nbsp;<button type="button" onclick="setDay(0);" class="bbs-search-btn" title="검색">1주일</button>
			 			&nbsp;<button type="button" onclick="setDay(1);" class="bbs-search-btn" title="검색">1개월</button>
						&nbsp;<button type="button" onclick="setDay(3);" class="bbs-search-btn" title="검색">3개월</button>
						&nbsp;<button type="button" onclick="setDay(6);" class="bbs-search-btn" title="검색">6개월</button>
						&nbsp;<button type="submit" class="bbs-search-btn" title="검색"><img src="/img/search_icon.png" style="width:30px;"></button>
					</form>
				</div>
				
				<table class="comm-tbl type2">
					<colgroup>
						<col width="15%">
						<col width="10%">
						<col width="15%">
						<col width="15%">
						<col width="15%">
						<col width="15%">
						<col width="15%">
					</colgroup>
					<thead>
						<tr>
							<th>주문번호</th>
							<th>주문자명</th>
							<th>주문상품</th>
							<!--  <th>결제수단</th>-->
							<th>후원금액</th>
							<th>주문날짜</th>
							<th>주문상태</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty orderList.orderinfoList }">
							<tr>
								<td colspan="6">
									<p class="none">검색 기간내의 후원내역이 없습니다.</p>
								</td>
							</tr>
						</c:if>
						<c:forEach items="${orderList.orderinfoList}" var="order">
							<tr>
								<td><a href="/myOrder?no=${order.no}">${order.no }</a></td>
								<td>
									${order.name }<br>
									<c:if test="${not empty order.id}">(${order.id })</c:if>
									<c:if test="${empty order.id}">(비회원)</c:if>
								</td>
								<td>${order.productName }</td>
								<!-- <td>
									<c:if test="${order.payMethod eq 'card'}">신용카드</c:if>
									<c:if test="${order.payMethod eq 'trans' }">실시간 계좌이체</c:if>
									<c:if test="${order.payMethod eq 'vbank' }">가상계좌</c:if>
									<c:if test="${order.payMethod eq 'account' }">무통장입금</c:if>
									<c:if test="${order.payMethod eq 'phone' }">휴대폰</c:if>
								</td>	-->
								<td><fmt:formatNumber value="${order.pay }" pattern="#,###" /> 원</td>	
								<td>${order.sponDate }</td>
								<td>
									<c:if test="${order.status eq 0 }">주문 완료</c:if>
									<c:if test="${order.status eq 1 }">결제 완료</c:if>
									<c:if test="${order.status eq 2 }">배송중</c:if>
									<c:if test="${order.status eq 3 }">배송완료</c:if>
								</td>						
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- paging -->
		 		<div class="paging">
		 			${orderList.pageNavi }	
		 		</div>

		</div>
	</div>
</section>

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />