<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/common/header.jsp" />
</head>
	<script>
		$(document).ready(function(){
			$("#delFileBtn").click(function(){
				if(confirm("첨부파일을 삭제하시겠습니까?")){
					$(".delFile").hide();
					$("#noticeFilename").show();
					$("#status").val("delete");
				}
			});
		});
	</script>
<body>
	<section id="content-wrapper">
		<div class="area">
			<h2 class="comm-content-tit">게시글 수정</h2>
			<div class="common-tbl-box">
				<form action="/siNoticeUpdate?noticeNo=${notice.noticeNo }" method="post" enctype="multipart/form-data">
					<table class="comm-tbl">
						<colgroup>
							<col width="28%">
							<col width="/">
						</colgroup>
						<tr>
							<th>제목</th>
							<td><input type="text" name="noticeTitle" value="${notice.noticeTitle }"></td>
						</tr>
						<tr>
							<th>파일첨부</th>
							<td>
								<input type="hidden" name="status" id="status" value="stay">
								<!-- 삭제 유무 판단용 input태그 -->
								<c:choose>
									<c:when test="${not empty notice.noticeFilepath }">
										<img class="delFile" src="/siUpload/notice/${notice.noticeFilename }" width="16px">
										<input type="file" id="noticeFilename" name="noticeFilename" style="display:none;">
										<span class="delFile">${notice.noticeFilename }</span>
										<button type="button" id="delFileBtn" class="file-del-btn delFile">삭제하기</button>
										<input type="hidden" name="noticeOldFilename" value=${notice.noticeFilename }>
										<input type="hidden" name="noticeOldFilepath" value=${notice.noticeFilepath }>
										<br/>
									</c:when>
									<c:otherwise>
										<input type="file" name="noticeFilename">
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea name="noticeContent">${notice.noticeContent }</textarea></td>
						</tr>
					</table>
					<div class="common-tbl-btn-group">
						<button type="submit" class="btn-style1">수정하기</button>
						<button type="reset" class="btn-style2" onclick="location.href='siNoticeView?noticeNo=${notice.noticeNo }'">취소</button>
					</div>
				</form>
			</div>
		</div>
	</section>
</body>
<jsp:include page="/WEB-INF/common/footer.jsp" />
</html>