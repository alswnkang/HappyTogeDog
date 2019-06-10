<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />

	<script>
		$(document).ready(function(){
			$("#delFileBtn").click(function(){
				if(confirm("첨부파일을 삭제하시겠습니까?")){
					$(".delFile").hide();
					$("#boardFilename").show();
					$("#status").val("delete");
				}
			});
		});
	</script>

	<section id="content-wrapper">
		<div class="area">
			<h2 class="comm-content-tit">게시글 수정</h2>
			<div class="common-tbl-box">
				<form action="/siPreBoardUpdate?boardNo=${board.boardNo }" method="post" enctype="multipart/form-data">
					<table class="comm-tbl">
						<colgroup>
							<col width="28%">
							<col width="/">
						</colgroup>
						<tr>
							<th>제목</th>
							<td><input type="text" name="boardTitle" value="${board.boardTitle }"></td>
						</tr>
						<tr>
							<th>파일첨부</th>
							<td>
								<input type="hidden" name="status" id="status" value="stay">
								<!-- 삭제 유무 판단용 input태그 -->
								<c:choose>
									<c:when test="${not empty board.boardFilepath }">
										<img class="delFile" src="/siUpload/board/${board.boardFilename }" width="16px">
										<input type="file" id="boardFilename" name="boardFilename" style="display:none;">
										<span class="delFile">${board.boardFilename }</span>
										<button type="button" id="delFileBtn" class="file-del-btn delFile">삭제하기</button>
										<input type="hidden" name="boardOldFilename" value=${board.boardFilename }>
										<input type="hidden" name="boardOldFilepath" value=${board.boardFilepath }>
										<br/>
									</c:when>
									<c:otherwise>
										<input type="file" name="boardFilename">
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea name="boardContent">${board.boardContent }</textarea></td>
						</tr>
					</table>
					<div class="common-tbl-btn-group">
						<button type="submit" class="btn-style1">수정하기</button>
						<button type="reset" class="btn-style2" onclick="location.href='/siPreBoardView?boardNo=${board.boardNo }'">취소</button>
					</div>
				</form>
			</div>
		</div>
	</section>

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />