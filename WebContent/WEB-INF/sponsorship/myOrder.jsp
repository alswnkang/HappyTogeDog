<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="/WEB-INF/common/header.jsp" />
<link rel="stylesheet" type="text/css" href="/css/style.css">

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<div class="order-view">
			<p class="main-comm-tit">주문 상세</p>
			<table class="comm-tbl">
				<tr>
					<td>주문번호</td><td>${orderInfo.no }</td>
				</tr>
				<tr class="tr-title"><td colspan="2">주문자 정보</td></tr>
				<tr>
					<td>주문자 명</td><td>${orderInfo.name }</td>
				</tr>
				<tr>
					<td>주문자 연락처</td><td>${orderInfo.phone }</td>
				</tr>
				<tr>
					<td>주문자 이메일</td><td>${orderInfo.email }</td>
				</tr>
				<tr class="tr-title"><td colspan="2">배송지 정보</td></tr>
				<tr>
					<td>받으시는 분</td><td>${orderInfo.receiveName }</td>
				</tr>
				<tr>
					<td>배송지 연락처</td><td>${orderInfo.receivePhone }</td>
				</tr>
				<tr>
					<td>배송지 주소</td><td>( ${orderInfo.post } )<br>${fn:split(orderInfo.address,'//')[0] } ${fn:split(orderInfo.address,'//')[1] }</td>
				</tr>
				<tr>
					<td>배송 메모</td><td>${orderInfo.memo }</td>
				</tr>
				<c:if test="${not empty orderInfo.deilveryNum }">
					<tr>
						<td>운송장 번호</td><td>${orderInfo.deilveryNum }</td>
					</tr>
				</c:if>
			</table>
			<c:if test="${not empty sessionScope.member }">
				<button class="order-btn">목록으로</button>
			</c:if>
		</div>
		
	</div>
</section>
<jsp:include page="/WEB-INF/common/footer.jsp" />