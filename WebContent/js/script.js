
/* 후원물품 수량,금액 체크 */
function check(){
	var chkPrice = $('input[name=chkPrice]').val();
	var amount = $('input[name=amount]').val();
	var price = $('input[name=price]').val();
	if(amount==''){
		alert('수량을 입력해주세요');
		$('input[name=amount]').focus();
		return false;
	}
	if(price==''){
		alert('후원 금액을 입력해주세요');
		$('input[name=price]').focus();
		return false;
	}
	if(chkPrice*amount>price){
		alert('양심있냐 ㅠㅠ');
		return false;
	}
	return true;
}

/* daum 주소 api */
/*
 * http://postcode.map.daum.net/guide
 */
function getAddr(f){
    new daum.Postcode({
        oncomplete: function(data) {
        	f.post.value = data.zonecode;
            f.address.value = data.address;
        }
    }).open();
}

/* 숫자 형식 */
function addComma(num) {
	var regexp = /\B(?=(\d{3})+(?!\d))/g;
	return num.toString().replace(regexp, ',');
}

/* 숫자만 입력 */
$(function(){
	var chkPrice = $('input[name=chkPrice]').val();
	//$('input[name=amount].num').val(1);
	//$('input[name=price].num').val(chkPrice);
	$('input[type=text].num').keyup(function(){
		var num = $(this).val();
		var check = /^[0-9]*$/;
		if(!check.test(num)){
			alert('숫자만 입력해 주세요');
			$(this).val('');
		}
	});
	
	$('input[name=amount].num').keyup(function(){
		
		$('#limitPrice').text(addComma($(this).val()*chkPrice));
		$('#realAmount').text(addComma($(this).val()));
	});
	
	$('input[name=price].num').keyup(function(){
		$('#realPrice').text(addComma($(this).val()));
	});
	
	
});