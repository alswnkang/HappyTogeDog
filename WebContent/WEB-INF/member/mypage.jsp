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
	<table>
		<tr>
			<th>이름</th>
			<td><input type="text" name="id" value="${m.id }" readonly></td>
		</tr>
		<tr>
			<th>코드</th>
			<td><input type="text" name="code" value="${m.code }" readonly></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="name" value="${m.name }"></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td><input type="text" name="phone" value="${m.phone }"></td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><input type="text" name="post" value="${m.post }"></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><input type="text" name="address" value="${m.address }"></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type="text" name="email" value="${m.email }"></td>
		</tr>
		<c:if test="${m.memberLevel == 1}">
		<tr>
			<th>시간</th>
			<td>${m.possibleTime }</td>
		</tr>
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
		<tr>
		<th>시간</th>
		<td>${m.startTime },${m.endTime }</td>
		</tr>
		</c:if>
	</table>
	
	<script>
		$(document).ready(function(){
			$('input').click(function(){
				$(this).val("");
			});
			$('#time').change(function(){
				var time = $('#time').val();
				var no = $('#time').children().eq();
				
				for(var i=0;i<10;i++){
					if(time>=$('#endTime').children().eq(i).val()){
						$('#endTime').children().eq(i).css('display','none');
					}else if(time<=$('#endTime').children().eq(i).val()){
						$('#endTime').children().eq(i).css('display','block');
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