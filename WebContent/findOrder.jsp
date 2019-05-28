<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/common/header.jsp" />
<link rel="stylesheet" type="text/css" href="/css/style.css">
<script type="text/javascript" src="/js/script.js"></script>

<div class="area">
	<div class="order-success">
		<p class="main-comm-tit">GUEST LOGIN</p>
		<p>비회원은 주문번호로 주문조회가 가능합니다.</p>
		<table>
			<tr>
				<td>주문번호</td><td><input type="text" name="no"></td>
			</tr>
			<tr>
				<td>주문자 연락처</td><td><input type="text" name="phone1" class="phone num"> - <input type="text" name="phone2" class="phone num"> - <input type="text" name="phone3" class="phone num"></td>
			</tr>
		</table>
		<button class="order-btn">비회원 주문조회</button>
	</div>
	
</div>
<jsp:include page="/WEB-INF/common/footer.jsp" />