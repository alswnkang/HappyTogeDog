<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section>
		<form action="/memberJoin" method="post">
			<div>
				<jsp:include page="/WEB-INF/common/header.jsp" />
				<h1>일반 회원가입</h1>
				아이디 : <input type="text" name="id"><br>
				비밀번호 : <input type="password" name="pw"><br>
				비밀번호 확인 : <input type="password" name="pw_re"><br>
				이름 : <input type="text" name="name"><br>
				전화번호 : <input type="text" name="phone"><br>
				우편번호 : <input type="text" name="post"><br>
				주소 : <input type="text" name="address"><br>
				EMAIL : <input type="text" name="email"><br>
				<input type="submit" value="회원가입">
			</div>
		</form>
	</section>
	
</body>
	<jsp:include page="/WEB-INF/common/footer.jsp" />
</html>