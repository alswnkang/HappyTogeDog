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
	<section id="content-wrapper">
	<form name="send_emailFrm">								<!-- email.js 로 보낼 폼 -->
		<input type="hidden" name="send_email">
		<input type="hidden" name="send_level" value="${level }">
	</form>
		<form action="/emailJoin" method="post" onsubmit="return check()">
		<div class="area">
		<h1 class="comm-content-tit">회원가입</h1>
			<table class="comm-tbl type2">
			<tr>
			<th>이메일 입력</th>
			<td><input type="text" name="email" id="email"></td>
			<td><button type="button" onclick="send_email()" id="btn">인증하기</button></td>
			</tr>
			<input type="text" name="level" id="level" value="${level }" style="display:none">	<br>					<!-- 가입자 레벨 -->
			<input type="text" name="a" id="a" value="0" change="emailChk();" style="display:none">	<br>		<!-- 이메일 인증 완료시 1 미완료시 0 -->
			</table>						<!-- email.js로  -->
			<input type="submit" value="계속하기" id="btn2">
		</div>
		</form>
		</section>
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