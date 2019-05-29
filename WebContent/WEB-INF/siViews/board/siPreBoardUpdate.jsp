<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
	<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
	<script>
		$(document).ready(function(){
			$("button[name=fileDelBtn]").click(function(){
				if(confirm("첨부파일을 삭제하시겠습니까?")){
					$(".delFile").hide();
					$("input[name=boardFilename]").show();
					$("input[name=status]").val("delete");
				}
			});
		});
	</script>
<body>
	<!-- 파일 업로드, 제대로 안되서 나중에 손봐야됨 -->
	<section name="siSection">
		<form action="/siPreBoardUpdate?boardNo=${board.boardNo }" method="post" enctype="multipart/form-data">
			<table style="border:1px solid black;width: 700px;">
				<tr>
					<th colspan="2">공지사항</th>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="boardTitle" value=${board.boardTitle }></td>
				</tr>
				<tr>
					<th>파일첨부</th>
					<td>
						<input type="hidden" name="status" value="stay">
						<!-- 삭제 유무 판단용 input태그 -->
						<c:choose>
							<c:when test="${!empty board.boardFilepath }">
								<img class="delFile" src="/siUpload/board/${board.boardFilename }" width="16px">
								<input type="file" name="boardFilename" style="none;">
								<span class="delFile">${board.boardFilename }</span>
								<button type="button" name="fileDelBtn">삭제하기</button>
								<input type="hidden" name="boardFilename" value=${board.boardFilename }>
								<input type="hidden" name="boardOldFilepath" value=${board.boardFilepath }>
								<br/>
							</c:when>
							<c:otherwise>
								<input type="file" name="boardFilename">
								<br/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea name="boardContent">${board.boardContent }</textarea></td>
				</tr>
			</table>
			<button type="submit">수정하기</button>
		</form>
	</section>
</body>
</html>