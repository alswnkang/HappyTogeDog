<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/common/header.jsp" />
	<h1>로그인임</h1>
	<form action="/login" method="post">
	<div>
	아이디 : <input type="text" name="id"><br>
	비밀번호 : <input type="password" name="pw"><br>
	<input type="submit" value="확인">
	</div>
	</form>
	
</body>
</html>