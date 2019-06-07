<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
	<style type="text/css">
		#checked-container{
			text-align : center;
			padding-top : 50px;
		}
		#checked{
			color: red;
			font-weight: bold;
		}
	</style>
</head>
<body>
	<input type="hidden" id="check" value="${result }">
	<c:if test="${result == 0 }">
	<div id="checked-container">
	
		인증번호 입력 <input type="text" name="email_num" id="email_num"><br>			<!-- 인증번호 입력받는 곳 -->
		<input type="text" name="serverNum" id="serverNum" value="${num }">		<!-- 서버 인증번호 -->
		<input type="button" id="btn" value="확인">
		<div id="countdown"></div>
	
	</div>
	</c:if>
	
	<script>
		$(document).ready(function(){
			var check = $('#check').val();
			if(check>0){
				alert("중복된 이메일 입니다.");
				self.close();
			}else{
			function countdown( elementId, seconds ){
				  var element, endTime, hours, mins, msLeft, time;

				  function updateTimer(){
				    msLeft = endTime - (+new Date);
				    if ( msLeft < 0 ) {
				      console.log('done');
				      alert("다시 인증받으세요");
				      $('#serverNum').val("356854651654");
				      self.close();
				    } else {
				      time = new Date( msLeft );
				      hours = time.getUTCHours();
				      mins = time.getUTCMinutes();
				      element.innerHTML = (hours ? hours + ':' + ('0' + mins).slice(-2) : mins) + ':' + ('0' + time.getUTCSeconds()).slice(-2);
				      setTimeout( updateTimer, time.getUTCMilliseconds());
				      // if you want this to work when you unfocus the tab and refocus or after you sleep your computer and come back, you need to bind updateTimer to a $(window).focus event^^
				    }
				  }

				  element = document.getElementById( elementId );
				  endTime = (+new Date) + 1000 * seconds;
				  updateTimer();
				}

				countdown('countdown', 10);	 // second base
			
			
			}
		});
		
		$('#btn').click(function(){
			var serverNum = $('#serverNum').val();
			var email_num = $('#email_num').val();
			if(serverNum == email_num){
				alert("인증완료");
				var a = opener.document.getElementById("a");		//email.jsp 의 input a 저장
				a.value=1;											// value값 1로 변경
				self.close();										//팝업창 닫기
			}else{
				alert("인증실패");
			}
		});
		
		
		
	</script>
	
</body>
</html>