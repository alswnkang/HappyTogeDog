<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/common/header.jsp" />
	<section id="content-wrapper">
	<div class="area">
	<input type="hidden" id="a" value="${user }">
	<h1 class="comm-content-tit">관리자 페이지</h1>
	<div class="common-tbl-box">
	 	<form action="/seeUser" method="post" id="form"> 
	 		<select name="user" id="user">
			<option value="2">전체회원</option>
			<option value="0">일반회원</option>
			<option value="1">보호소회원</option>
			</select>
	 </form> 	
		<table class="comm-tbl type2">
			<tr>
				<th>ID</th>
				<th>CODE</th>
				<th>이름</th>
				<th>전화번호</th>
				<th>우편번호</th>
				<th>주소</th>
				<th>EMAIL</th>
				<th>회원등급</th>
			</tr>
			<c:forEach items="${pd.list }" var = "m">
				<tr>
				<td>${m.id }</td>
				<td>${m.code }</td>
				<td>${m.name }</td>
				<td>${m.phone }</td>
				<td>${m.post }</td>
				<td>${m.address }</td>
				<td>${m.email }</td>
				<td>${m.user }</td>
				</tr>
			</c:forEach>
		</table>
		<div id="pageNavi">${pd.pageNavi }</div>
		<form action="/searchUser" method="post">
		<select name="select">
			<option value="1">아이디</option>
			<option value="2">이름,보호소</option>
			<option value="3">코드</option>
		</select>
		<input type="text" name="search">
		<input type="submit" value="검색">
		</form>
		</div>
		
		</div>
	</section>	
	
	<script>
		$(document).ready(function(){
			var a = $('#a').val();
			console.log(a);
			if(a == 0){
				$('#user').children().eq(1).attr('selected','selected');
			}else if(a == 1){
				$('#user').children().eq(2).attr('selected','selected');
			}else if(a == 2){
				$('#user').children().eq(0).attr('selected','selected');
			}
		});
			$('#user').change(function(){
				$('#form').submit();
				
				
	/*			var user = $('#user').val();
				
				$.ajax({
					url:"/seeUser",
					data : {user:user},
					type : "get",
					success : function(data){
						
					},
					error : function(){
						console.log("전송 실패");
					}
				});	*/
			});
		
	</script>
</body>
</html>