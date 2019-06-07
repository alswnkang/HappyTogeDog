<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%for(int i=600;i<700;i++){%>
	 	insert into member values('shelter<%=i %>','1234','code','careNm','phone',null,'addr','09시~18시','shelter<%=i%>@happy.com','1');<br>
	<%} %>
</body>
</html>