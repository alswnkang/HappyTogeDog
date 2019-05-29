<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>

<body>
	<jsp:include page="/WEB-INF/common/header.jsp" />
	<section>
		<h1>임시 회원 가입 폼 일반회원,보호소회원</h1>
		<form action="/memberJoin" method="post" onsubmit="return check()">
			<div>
				
				<h1 id="h_title1">일반 회원가입</h1>
				<h1 id="h_title2" style="display:none">보호소 회원가입</h1>
				아이디 : <input type="text" name="id" id="id" placeholder = "4~12자리 영/숫자">
				<button type="button" id="checkId">중복체크</button><br>
				<p id="p_checkId" style="display:none">아이디 입력양식 확인</p>
				
				비밀번호 : <input type="password" name="pw" id="pw" placeholder = "영/숫자를 포함한 8~13자리"><br>
				<p id="p_checkPw" style="display:none">비밀번호 입력양식 확인</p>
				비밀번호 확인 : <input type="password" name="pw_re" id="pw_re"><br>
				<p id="p_checkPw_re" style="display:none">비밀번호가 일치하지 않습니다</p>
				<p id="p_code" style="display:none">보소호코드 : <input type="text" name="code"><br></p>
				이름 : <input type="text" name="name" id="name"><br>
				전화번호 : <input type="text" name="phone" id="phone"><br>
				우편번호 : <input type="text" id="post" placeholder="우편번호" name="post">
				<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
				주소 : <input type="text" id="address" placeholder="도로명주소" name="address">
					<span id="guide" style="color:#999;display:none"></span>
					<input type="text" id="detailAddress" placeholder="상세주소" name="detailAddress" value=""><br>
				EMAIL : <input type="text" name="email" id="email" value="${email }" readonly>
				
				<br>
				<input type="hidden" name="level" id="level" value="${level}">
				<div id="selectTime">
				<select name="time" id="time">
					<option value="08">08시</option>
					<option value="09">09시</option>
					<option value="10">10시</option>
					<option value="11">11시</option>
					<option value="12">12시</option>
					<option value="13">13시</option>
					<option value="14">14시</option>
					<option value="15">15시</option>
					<option value="16">16시</option>
					<option value="17">17시</option>
					
				</select> 
				~
				<select name="endTime" id="endTime">
					<option>시간선택</option>
					<option value="09">09시</option>
					<option value="10">10시</option>
					<option value="11">11시</option>
					<option value="12">12시</option>
					<option value="13">13시</option>
					<option value="14">14시</option>
					<option value="15">15시</option>
					<option value="16">16시</option>
					<option value="17">17시</option>
					<option value="18">18시</option>
					
				</select><br>
				</div>
				<input type="submit" value="회원가입" id="sub">
				<input type="reset" value="취소" id="reset">
				<h1>레벨 : ${level }</h1>
			</div>
		</form>
		
			시 선택 : 
			<select name="city" id="city">
				<option>도시선택</option>
				<c:forEach items="${list }" var="m" varStatus="i">
						<option value="${m.cityCode }">${m.cityName }</option>
				</c:forEach>
			</select>
			<button id="citysel">확인</button><br>
			구 선택:
			<select name="area" id="area">
				<option>지역구선택</option>
			</select>
			<button id="areasel">확인</button><br>
			<select name="care" id="care">
				<option>보호소선택</option>
			</select>
		
	</section>
	
	
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('post').value = data.zonecode;
                document.getElementById("address").value = roadAddr;
                
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
              

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
</script>
	
	<script>
	$("#citysel").click(function(){
		var value = $("#city").val();
		$.ajax({
			url:"/areaCode",		
			data : {value:value},
			type : "get",
			
			success : function(data){
				var $select = $("#area");
				$select.find("option").remove();
				$select.append("<option>지역구선택</option>");
				
				for(var i=0;i<data.length;i++){
					var areaName=data[i].districtName;
					var areaValue=data[i].district;
					var selected="";
					
					$select.append("<option value='"+areaValue+"''"+selected+">"+areaName+"</option>");
					
				}
				
			},
			error : function(){
			console.log("실패");	
			}
		});
		
	});
	
	$("#areasel").click(function(){
		var citValue = $("#city").val();
		var areValue =	$("#area").val();
		$.ajax({
			url:"/careCode",		
			data : {citValue:citValue,areValue:areValue},
			type : "get",
			
			success : function(data){
				var $select = $("#care");
				$select.find("option").remove();
				$select.append("<option>보호소선택</option>");
				
				for(var i=0;i<data.length;i++){
					var careName=data[i].careNm;
					var careRegNo=data[i].careRegNo;
					var selected="";
					
					$select.append("<option value='"+careRegNo+"''"+selected+">"+careName+"</option>");
					
				}
				
			},
			error : function(){
			console.log("실패");	
			}
		});
		
	});
	
	
		$(document).ready(function(){
			$('#time').change(function(){
				var time = $('#time').val();
				var no = $('#time').children().eq();
				
				for(var i=0;i<11;i++){
					if(time>=$('#endTime').children().eq(i).val()){					
						$('#endTime').children().eq(i).css('display','none');		//select #time 을 선택했을때 #time의 value값보다 #endTime의 value값이 작을때 작은 value의 display를 none
					}else if(time<=$('#endTime').children().eq(i).val()){			
						$('#endTime').children().eq(i).css('display','block');		//select #time 을 선택했을때 #time의 value값보다 #endTime의 value값이 클때 큰 value의 display를 block
					}
				}
			});
			$('#endTime').change(function(){
				var endTime = $('#endTime').val();
				for(var j=0;j<11;j++){
				if(endTime<=$('#time').children().eq(j).val()){				//select #endTime을 선택했을때 #time의 value 값보다 작을때 #time의 display를 none
					$('#time').children().eq(j).css('display','none');
				}else if(endTime>=$('#time').children().eq(j).val()){		//select #endTime을 선택했을때 #time의 value 값보다 클때 #time의 display를 block
					$('#time').children().eq(j).css('display','block');
				}
				}
			});
			var level = $('#level').val();
			if(level > 0){
				$('#p_code').css('display','block');		//level이 0보다 클때 block
				$('#h_title2').css('display','block');		//level이 0보다 클때 block
				$('#h_title1').css('display','none');		//level이 0보다 클때 none
			}else if(level == 0){
				$('#selectTime').css('display','none');		//level이 0일때 none
			}
			
			$('#id').keyup(function(){
				var id = $('#id').val();
				var id_re = /^[a-zA-Z0-9]{4,12}$/;
				if(id_re.test(id)==true){
					$('#p_checkId').css('display','none');
					
				}else if(id_re.test(id)==false){
					$('#p_checkId').css('display','block');
					$('#p_checkId').css('color','red');
				}
			});
			
			$('#pw').keyup(function(){
				var pw = $('#pw').val();
				var checkPw = /^[a-zA-Z0-9]{8,13}$/;
				if(checkPw.test(pw)==true){
					$('#p_checkPw').css('display','none');
					
				}else if(checkPw.test(pw)==false){
					$('#p_checkPw').css('display','block');
					$('#p_checkPw').css('color','red');
				}
			});
			
			$('#pw_re').keyup(function(){
				var pw = $('#pw').val();
				var pw_re = $('#pw_re').val();
				if(pw != pw_re){
					$('#p_checkPw_re').css('display','block');
					$('#p_checkPw_re').css('color','red');
				}else{
					$('#p_checkPw_re').css('display','none');
				}
			});
			
			$('#reset').click(function(){
				$('#p_checkId').css('display','none');
				$('#p_checkPw').css('display','none');
				$('#p_checkPw_re').css('display','none');
			});
			
		});
		
		$('#checkId').click(function(){
			var memberId = $('#id').val();
			$.ajax({
				url : "/checkId",
				type : "get",
				data : {memberId : memberId},
				success : function(data){
					if(data ==1){
						alert("사용가능")
					}else{
						alert("사용불가")
					}
				},
				error : function(){
					console.log("실패");
				}
			});
		});
		function check(){
			var id_re = /^[a-zA-Z0-9]{4,12}$/;
			var checkPw = /^[a-zA-Z0-9]{8,13}$/;
			if($('#id').val() == ""){
				alert("아이디를 입력해주세요");
				$('#id').focus();
				return false;
			}
			if(id_re.test($('#id').val())==false){
				alert("아이디 양식이 틀렸습니다");
				$('#id').focus();
				return false;
			}
			if($('#pw').val() == ""){
				alert("비밀번호를 입력해주세요");
				$('#pw').focus();
				return false;
			}
			if(checkPw.test($('#pw').val())==false){
				alert("비밀번호 양식이 틀렸습니다");
				$('#pw').focus();
				return false;
			}
			if($('#name').val() == ""){
				alert("이름을 입력해주세요");
				$('#name').focus();
				return false;
			}
			if($('#phone').val() == ""){
				alert("전화번호를 입력해주세요");
				$('#phone').focus();
				return false;
			}
			if($('#post').val() == ""){
				alert("우편번호를 입력해주세요");
				$('#post').focus();
				return false;
			}
			if($('#address').val() == ""){
				alert("주소를 입력해주세요");
				$('#address').focus();
				return false;
			}
			if($('#email').val() == ""){
				alert("이메일을 입력해주세요");
				$('#email').focus();
				return false;
			}
		}
		
	</script>
</body>
	<jsp:include page="/WEB-INF/common/footer.jsp" />
</html>