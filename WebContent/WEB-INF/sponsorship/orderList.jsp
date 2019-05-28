<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />
<link rel="stylesheet" type="text/css" href="/css/style.css">

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">주문 내역(관리자)</h2>
		<div id="orderListBox" class="common-tbl-box"><!-- id는 바꿔서 복붙 -->
			<!-- 검색박스 -->
		 		<div class="board-search-box order-search">
		 			<input class="datepicker search-word"> ~ <input class="datepicker search-word">
		 			<br><br>
		 			<select name="status">
		 				<option>---주문상태---</option>
						<option value="0">주문 완료</option>
						<option value="1">결제 완료</option>
						<option value="2">배송중</option>
						<option value="3">배송 완료</option>
					</select>
		 			<br><br>
		 			<label><input type="radio" name="payMethod" value="card" checked> 신용카드</label>
					<label><input type="radio" name="payMethod" value="trans"> 실시간 계좌이체</label>
					<label><input type="radio" name="payMethod" value="vbank"> 가상계좌</label>
					<label><input type="radio" name="payMethod" value="account"> 무통장입금</label>
					<label><input type="radio" name="payMethod" value="phone"> 휴대폰</label>
					<br><br>
					<select name="search_item">
						<option value="no">주문번호</option>
						<option value="name">주문자명</option>
					</select>
					<input placeholder="검색어를 입력해주세요." type="search" name="search_order" class="search-word" value="">
					<button type="submit" class="bbs-search-btn" title="검색"><img src="/img/search_icon.png" style="width:30px;"></button>
				</div>
				<p class="total">총 주문 수 : ${total.count } / 총 후원 금액(결제 완료된 주문합계) : <fmt:formatNumber value="${total.price }" pattern="#,###" /> 원</p>
				<table class="comm-tbl type2"><!-- 신청목록게시판은 한페이지에 게시물 최대 10개 노출 -->
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
							<th>결제수단</th>
							<th>후원금액</th>
							<th>주문날짜</th>
							<th>주문상태</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${orderList}" var="order">
							<tr>
								
								<td><a href="/myOrder?no=${order.no}">${order.no }</a></td>
								<td>
									${order.name }<br>
									<c:if test="${not empty order.id}">(${order.id })</c:if>
									<c:if test="${empty order.id}">(비회원)</c:if>
								</td>
								<td>${order.productName }</td>
								<td>
									<c:if test="${order.payMethod eq 'card'}">신용카드</c:if>
									<c:if test="${order.payMethod eq 'trans' }">실시간 계좌이체</c:if>
									<c:if test="${order.payMethod eq 'vbank' }">가상계좌</c:if>
									<c:if test="${order.payMethod eq 'account' }">무통장입금</c:if>
									<c:if test="${order.payMethod eq 'phone' }">휴대폰</c:if>
								</td>	
								<td><fmt:formatNumber value="${order.pay }" pattern="#,###" /> 원</td>	
								<td>${order.sponDate }</td>
								<td>
									<select class="status" data-status="${order.status}">
										<option>---주문상태---</option>
										<option value="0">주문 완료</option>
										<option value="1">결제 완료</option>
										<option value="2">배송중</option>
										<option value="3">배송 완료</option>
									</select>
								</td>						
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

		</div>
	</div>
</section>

<%-- Footer --%>
<script>
	$(function(){
		
		$('.status').each(function(){
			var st = $(this).data('status');
			$(this).children('option').each(function(){
				if(st == $(this).val()){
					$(this).prop("selected",true);
				}
			});
		});
		
	});
</script>
<jsp:include page="/WEB-INF/common/footer.jsp" />