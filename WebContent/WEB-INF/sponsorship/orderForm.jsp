<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/common/header.jsp" />
<link rel="stylesheet" type="text/css" href="/css/style.css">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="/js/script.js"></script>

<div class="area">
	<div class="order-form">
		<div class="order-product">
			<ul class="clear-float">
				<li><img src="/img/76896814691427225_1127979769.jpg" width="150" onclick="location.href='/viewProduct'"></li>
				<li>상품명</li>
				<li>${amount} 개</li>
				<li>${price} 원</li>
			</ul>
		</div>
		
		<form action="/orderIng" method="post">
			<div class="order">
				<p class="main-comm-tit">주문자 정보</p>
				<table>
					<tr>
						<td>이름</td><td><input type="text" name="name"></td>
					</tr>
					<tr>
						<td>연락처</td>
						<td>
							<select name="phone1" class="phone">
								<option value="010">010</option>
								<option value="011">011</option>
							</select>
							 - <input type="text" name="phone2" class="phone num" maxlength="4"> 
							 - <input type="text" name="phone3" class="phone num" maxlength="4"></td>
					</tr>
					<tr>
						<td>이메일</td><td><input type="text" name="email"></td>
					</tr>
				</table>
			</div>
			
			<div class="delivery">
				<p class="main-comm-tit">배송지 정보</p>
				<table>
					<tr>
						<td>이름</td><td><input type="text" name="name"></td>
					</tr>
					<tr>
						<td>연락처</td><td><input type="text" name="phone"></td>
					</tr>
					<tr>
						<td>배송지 주소</td>
						<td style="height: 200px;">
							<input type="text" name="post" class="post" onclick="getAddr(this.form);" readonly><br><br>
							<input type="text" name="address" class="address" onclick="getAddr(this.form);" readonly><br><br>
							<input type="text" name="address2" class="address" placeholder="상세 주소를 입력하세요">
						</td>
					</tr>
					<tr>
						<td>배송 메모</td>
						<td>
							<input type="text" name="memo" class="memo">
						</td>
					</tr>
				</table>
			</div>
			
			<div class="pay">
				<p class="main-comm-tit">결제 정보</p>
				<table>
					<tr>
						<td>결제 수단</td>
						<td>
							<label><input type="radio" name="payMethod" value="card"> 신용카드</label>
							<label><input type="radio" name="payMethod" value="account"> 무통장입금</label>
							<label><input type="radio" name="payMethod" value="phone"> 휴대폰</label>
						</td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: right;"><button type="submit" class="order-btn">결제하기</button></td>
					</tr>
				</table>
			</div>
			
		</form>

	</div>
	
</div>
<jsp:include page="/WEB-INF/common/footer.jsp" />