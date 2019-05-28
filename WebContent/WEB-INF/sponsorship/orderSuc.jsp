<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/common/header.jsp" />
<link rel="stylesheet" type="text/css" href="/css/style.css">

<div class="area">
	<div class="order-success">
		<p class="main-comm-tit">주문이 완료되었습니다.</p>
		<c:if test="${empty orderInfo.id }">
			<p>비회원 주문시 주문번호를 메모해주세요.</p>
		</c:if>
		<table class="comm-tbl type2">
			<tr>
				<td>주문번호</td><td>${orderInfo.no }</td>
			</tr>
			<tr>
				<td>주문자 명</td><td>${orderInfo.name }</td>
			</tr>
			<tr>
				<td>주문자 연락처</td><td>${orderInfo.phone }</td>
			</tr>
			<tr>
				<td>주문자 이메일</td><td>${orderInfo.email }</td>
			</tr>
			<tr>
				<td>받으시는 분</td><td>${orderInfo.receiveName }</td>
			</tr>
			<tr>
				<td>배송지 연락처</td><td>${orderInfo.receivePhone }</td>
			</tr>
			<tr>
				<td>배송지 주소</td><td>( ${orderInfo.post } ) ${orderInfo.address }</td>
			</tr>
			<tr>
				<td>배송 메모</td><td>${orderInfo.memo }</td>
			</tr>
		</table>
		<button class="order-btn">주문 내역 확인</button>
	</div>
	
</div>
<jsp:include page="/WEB-INF/common/footer.jsp" />