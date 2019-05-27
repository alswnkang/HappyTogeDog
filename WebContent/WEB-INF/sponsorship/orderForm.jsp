<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/common/header.jsp" />
<link rel="stylesheet" type="text/css" href="/css/style.css">
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="/js/script.js"></script>


<div class="area">
	<div class="order-form">
		<div class="order-product">
			<ul class="clear-float">
				<li><img src="/img/76896814691427225_1127979769.jpg" width="150" onclick="location.href='/viewProduct'"></li>
				<li id="prdName">상품명</li>
				<li>${amount} 개</li>
				<li>${price} 원</li>
			</ul>
		</div>
		
		<form id="orderForm">
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
						<td>이름</td><td><input type="text" name="receiveName"></td>
					</tr>
					<tr>
						<td>연락처</td><td><input type="text" name="receivephone"></td>
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
							<label><input type="radio" name="payMethod" value="card" checked> 신용카드</label>
							<label><input type="radio" name="payMethod" value="trans"> 실시간 계좌이체</label>
							<label><input type="radio" name="payMethod" value="vbank"> 가상계좌</label>
							<label><input type="radio" name="payMethod" value="account"> 무통장입금</label>
							<label><input type="radio" name="payMethod" value="phone"> 휴대폰</label>
						</td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: right;"><input type="hidden" name="pay" value="${price}"><span id="total">${price}</span>원<button class="order-btn">결제하기</button></td>
					</tr>
				</table>
			</div>
			
		</form>

	</div>
	
</div>
<script>
	$(function() {

		$('.pay button').click(function() {
			
			event.preventDefault();
			
			var prdName = $('#prdName').html();
			var price = $('.pay span#total').html();
			var name = $('input[name=name]').val();
			var email = $('input[name=email]').val();
			var phone = $('input[name=phone1]').val()+'-'+$('input[name=phone2]').val()+'-'+$('input[name=phone3]').val();
			var post = $('input[name=post]').val();
			var addr = $('input[name=address]').val()+' '+$('input[name=address2]').val();
			var method = $('input[name=payMethod]:checked').val();
			/*
			console.log(prdName);
			console.log(price);
			console.log(name);
			console.log(email);
			console.log(phone);
			console.log(post);
			console.log(addr);
			console.log(method);
			*/
			if(method=='account'){

				var form = $('#orderForm')[0];
				var data = new FormData(form);
					
				$.ajax({
					url : "/orderIng",
					type : "post",
					data : data,
					success : function(data){
						
					},
					error : function(){
						console.log("실패");
					}
				});
				
			}else{
				
				var d = new Date();
				var date = d.getFullYear() + '' + (d.getMonth() + 1) + '' + d.getDate() + '' + d.getHours() + '' + d.getMinutes() + '' + d.getSeconds();
				
				IMP.init('imp20013985');
				IMP.request_pay({
					merchant_uid : prdName+"_"+ date,
					name : prdName,
					amount : price, 
					buyer_name : name,
					buyer_tel : phone,
					buyer_email : email,
					buyer_addr : addr,
					buyer_postcode : post,
					pay_method : method
				}, function(response) {
					if (response.success) {
						alert('완료');
						/*
						var msg = '결제가 완료되었습니다.';
						var info1 = '고유 ID : ' + response.imp_uid;
						var info2 = '결제금액 : ' + response.paid_amount;
						var info3 = '카드 승인 번호 : ' + response.apply_num;
						$('#paymentResult').html(
								msg + "<br>" + info1 + "<br>" + info2
										+ "<br>" + info3 + "<br>");
						*/
					} else {
						alert('결제를 취소하셨습니다.');
						//$('#paymentResult').html('에러내용 : ' + response.error_msg + date);
					}
				});	
			}
		});
		
	});
</script>
<jsp:include page="/WEB-INF/common/footer.jsp" />