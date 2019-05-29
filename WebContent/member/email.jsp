<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="/js/email.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
<body>
	<jsp:include page="/WEB-INF/common/header.jsp" />
	<form name="send_emailFrm">								<!-- email.js 로 보낼 폼 -->
		<input type="hidden" name="send_email">
		<input type="hidden" name="send_level" value="${level }">
	</form>
		<form action="/emailJoin" method="post" onsubmit="return check()">
		<div>
			이메일 입력<input type="text" name="email" id="email"><br>
					레벨<input type="text" name="level" id="level" value="${level }">	<br>					<!-- 가입자 레벨 -->
					인증완료되면1<input type="text" name="a" id="a" value="0" change="emailChk();">	<br>		<!-- 이메일 인증 완료시 1 미완료시 0 -->
			<button type="button" onclick="send_email()" id="btn">인증하기</button><br>						<!-- email.js로  -->
			<input type="submit" value="계속하기" id="btn2">
		</div>
		</form>
	<script>
	
		function check(){
			if($('#a').val() != 1){
				alert("이메일 인증 필요");
				return false;
			}
		}
		
	</script>
</body>
</html>