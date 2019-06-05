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
<body>
	<section name="siSection" id="content-wrapper">
		<div class="area">
			<div class="voluntary-box">
				<!-- 자유게시판 게시글 조회 -->
				<table class="comm-tbl view">
					<colgroup>
						<col width="20%">
						<col width="3%">
						<col width="/">
						<col width="100%">
					</colgroup>
					<tr>
						<th colspan="2" style="text-align:center;">${vd.b.boardTitle }</th>
					</tr>
					<tr>
						<td>작성자 : ${vd.b.boardName }(${vd.b.boardId })</td>
						<td>작성일 : ${vd.b.boardDate2 }</td>
					</tr>
					<c:if test="${not empty vd.b.boardFilename }">
						<tr>
							<td colspan="2" style="border-bottom: 0px;">
								<a style="float:right;" href="javascript:fileDownload('${vd.b.boardFilename }','${vd.b.boardFilepath }')">${vd.b.boardFilename }</a>
								<br/>
								<img src='/siUpload/board/${vd.b.boardFilename }'width="500px"/>
								<!-- 파일이 있으면 넘겨준 No를 기준으로 게시물의 이름을 불러와서 출력 -->
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty sessionScope.member.id }">
					<!-- 로그인을 안하면 댓글리스트만 조회가능, 등록칸이 보이지 않도록 설정 -->
						<tr>
							<td colspan="2" style="border-bottom: 0px;border-top: 0px;">${vd.b.boardContent }</td>
						</tr>
						<tr>
							<td colspan="2" style="border-top: 0px;">
								<button type="button" class="cmtBtn" style="float:right;">댓글</button>
							</td>
						</tr>
					</c:if>
					<c:if test="${empty sessionScope.member.id }">
					<!-- 로그인을 안하면 댓글리스트만 조회가능, 등록칸이 보이지 않도록 설정 -->
						<tr>
							<td colspan="2" style="border-top: 0px;">${vd.b.boardContent }</td>
						</tr>
					</c:if>
				</table>
				<form action="/siPreBoardCommentInsert" method="post">
					<input type="hidden" name="memberId" value="${sessionScope.member.id }"/>
					<input type="hidden" name="memberName" value="${sessionScope.member.name }"/>
					<input type="hidden" name="boardNo" value="${vd.b.boardNo }"/>
					<input type="hidden" name="boardType" value="1"/>
					<table class="comm-tbl view" id="commentTb" style="display:none;">
						<tr>
							<td colspan="4" style="text-align:center">
								댓글입력 <input type="text" name="boardCommentContent" value=""/>
								<button type="submit">등록</button>
							</td>
						</tr>
					</table>
				</form>
				<form id="cmtUpdateForm" action="/siPreBoardCommentUpdate" method="post">
					<input type="hidden" name="memberId" value="${sessionScope.member.id }"/>
					<input type="hidden" name="boardNo" value="${vd.b.boardNo }"/>
					<table class="comm-tbl view">
						<c:forEach items="${vd.list }" var="list">
							<c:if test="${list.boardRef == vd.b.boardNo}">
							<!-- 해당 게시글에 입력된 댓글만 출력되도록 -->
								<input type="hidden" name="boardCommentNo" value="${list.boardCommentNo }"/>
								<tr>
									<td width="20%">${list.boardCommentName }(${list.boardCommentId })</td>
									<td width="65%">
										<span>${list.boardCommentContent }</span>
										<input type="text" value="${list.boardCommentContent }" name="boardCommentContent" style="display:none;"/>
									</td>
									<td width="11%">
										${list.boardCommentDate2 }<br/>
										<c:if test="${sessionScope.member.id==list.boardCommentId }">
										<!-- 댓글 작성자일 때 수정/삭제 가능하도록 -->
											<button type="button">수정</button>
											<button type="text" style="display:none;">/</button>
											<button type="reset" style="display:none;">취소</button>
											/
											<a href="/siPreBoardCommentDelete?boardCommentNo=${list.boardCommentNo }&boardNo=${vd.b.boardNo }">삭제</a>
										</c:if>
										<c:if test="${sessionScope.member.id!=list.boardCommentId && sessionScope.member.id eq 'admin' }">
										<!-- 작성자가 아니면서 id가 admin인 경우 댓글을 삭제 가능하도록 -->
											<a href="/siPreBoardCommentDelete?boardCommentNo=${list.boardCommentNo }&boardNo=${vd.b.boardNo }">삭제</a>
										</c:if>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</table>
				</form>
				<form action="/siPreBoardUpdateOriginal?boardNo=${vd.b.boardNo }" method="post" enctype="multipart/form-data">
					<div class="common-tbl-btn-group" style="text-align:right;">
						<c:if test='${sessionScope.member.id==vd.b.boardId }'>
						<!-- 회원 아이디와 글 작성자의 아이디가 같거나 관리자라면 수정/삭제 버튼 생성 -->
							<button type="submit" class="btn-style3">수정</button>
						</c:if>
						<c:if test='${sessionScope.member.id==vd.b.boardId || sessionScope.member.id eq "admin" }'>
							<button type="button" id="boardDelBtn" class="btn-style3">삭제</button>
							<!-- 게시글 번호를 siPreBoardDelete?boardNo 서블릿에 전달-->
						</c:if>
						<button type="button" class="btn-style2" onclick="location.href='/siPreBoard'">목록으로 이동</button>
					</div>
				</form>
			</div>
		</div>
	</section>
</body>
<script>
	$(document).ready(function(){
		$('.cmtBtn').click(function(){
			$('#commentTb').show();
		});
	});
	$(document).ready(function(){
		$('button').eq(1).click(function(){
			$(this).parent().prev().children().eq(0).hide();
			$(this).parent().prev().children().eq(1).show();
			$(this).html('등록').attr("id","cmtUpdate");
			$(this).nextAll().show();
			$('button').next(1).click(function(){
				location.href='/siPreBoardView?boardNo='+${vd.b.boardNo };
			});
			$("#cmtUpdate").click(function(){
				$(this).parent().prev().children().eq(0).show();
				$(this).parent().prev().children().eq(1).hide();
				$(this).nextAll().hide();
				$(this).html('수정').removeAttr("id");
				$('#cmtUpdateForm').submit();
			});
		});
	});
	$(document).ready(function(){	//게시글 삭제 확인
		$('#boardDelBtn').click(function(){
			if(confirm("게시글을 삭제하시겠습니까?")){
				location.href = '/siPreBoardDelete?boardNo='+${vd.b.boardNo };
			}
		});
	});
	function fileDownload(boardFilename,boardFilepath){
		var url = "/siPreBoardFileDownload";
		var encFilename = encodeURIComponent(boardFilename);
		var encFilepath = encodeURIComponent(boardFilepath);
		location.href=url+'?filename='+encFilename+'&filepath='+encFilename;
	}
</script>
<jsp:include page="/WEB-INF/common/footer.jsp" />
</html>