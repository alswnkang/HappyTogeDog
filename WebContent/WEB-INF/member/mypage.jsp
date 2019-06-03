<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
<body>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<h1>임시 마이페이지 일반회원,보호소회원</h1>
	<section>
		<form action="/memberModify" method="post">
			<div><input type="hidden" name="level" value="${m.memberLevel }">
				<table>
					<tr>
						<th>아이디</th>
						<td><input type="text" name="id" value="${m.id }" readonly></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="pw" value="${m.pw }"></td>
					</tr>
					<tr>
						<th>코드</th>
						<td><input type="text" name="code" value="${m.code }" readonly></td>
					</tr>
					<tr>
						<th>이름</th>
						<td><input type="text" name="name" value="${m.name }" class="modify"></td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td><input type="text" name="phone" value="${m.phone }" class="modify"></td>
					</tr>
					<tr>
						<th>우편번호</th>
						<td><input type="text" id="post" name="post" value="${m.post }" class="modify">
						<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
						</td>
					</tr>
					<tr>
						<th>주소</th>
						<td><input type="text" id="address" name="address" value="${m.address }" class="modify">
							<span id="guide" style="color:#999;display:none"></span>
					<input type="text" id="detailAddress" placeholder="상세주소" name="detailAddress" value="">
						</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><input type="text" name="email" value="${m.email }" readonly></td>
						
					</tr>
					
					<c:if test="${m.memberLevel == 1}">
					<tr>
						<th>시간</th>
						<td>
							<div id="selectTime">
								<select name="time" id="time">
									<option id="startTime">${m.startTime }</option>
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
									<option id="endTime2">${m.endTime }</option>
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
							</td>
						</tr>
					</c:if>
					
				</table><br>
				<c:if test="${m.memberLevel == 2}">
				<button type="button" onclick="location.href='/adminPage'">관리자페이지</button>
				</c:if>
				<c:if test="${m.memberLevel < 2}">
				<button type="button" onclick="location.href='/delete?id=${m.id}'">탈퇴하기</button>
				</c:if>
				<input type="submit" value="수정하기">
				<input type="reset" value="취소">
			</div>
		</form>
	</section>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
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
            },
 		theme : {
 			bgColor: "#FFFFFF", //바탕 배경색
 		   searchBgColor: "#FE431E", //검색창 배경색
 		   //contentBgColor: "", //본문 배경색(검색결과,결과없음,첫화면,검색서제스트)
 		   pageBgColor: "#FE431E", //페이지 배경색
 		   textColor: "#000000", //기본 글자색
 		   queryTextColor: "#FFFFFF", //검색창 글자색
 		   postcodeTextColor: "#FE431E", //우편번호 글자색
 		   emphTextColor: "#059DEB", //강조 글자색
 		   outlineColor: "#9F9F9F" //테두리
 		}
 		
        }).open();
    }
</script>
	
	<script>
		$(document).ready(function(){
			$('.modify').click(function(){
				$(this).val("");
				
				
			});
			$('#time').change(function(){
				var time = $('#time').val();
				var no = $('#time').children().eq();
				
				for(var i=0;i<11;i++){
				
					if(time>=$('#endTime').children().eq(i).val()){
						$('#endTime').children().eq(i).css('display','none');
					}else if(time<=$('#endTime').children().eq(i).val()){
						$('#endTime').children().eq(i).css('display','block');
					}
				}
			});
			$('#endTime').change(function(){
				var endTime = $('#endTime').val();
				for(var j=0;j<11;j++){
				if(endTime<=$('#time').children().eq(j).val()){
					$('#time').children().eq(j).css('display','none');
				}else if(endTime>=$('#time').children().eq(j).val()){
					$('#time').children().eq(j).css('display','block');
				}
				}
			});

			$("#time").mouseover(function(){
				$('#startTime').css('display','none');
			});
			$("#endTime").mouseover(function(){
				$('#endTime2').css('display','none');
			});
		});
	</script>
	
</body>
</html>