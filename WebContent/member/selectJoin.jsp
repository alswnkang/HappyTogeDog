<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>임시 회원가입 선택</h1>
	<form action="/join" method="post">
		<div>
			<input type="hidden" name="level" value="0">
			<button type="submit">일반회원 가입</button>
		</div>
	</form>
	<form action="/join" method="post">
		<div>
			<input type="hidden" name="level" value="1">
			<button type="submit">보호소회원 가입</button>
		</div>
	</form>
</body>
</html>