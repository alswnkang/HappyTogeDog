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
				<!-- 공지사항 게시글 조회 -->
				<table class="comm-tbl view">
					<colgroup>
						<col width="20%">
						<col width="3%">
						<col width="/">
						<col width="100%">
					</colgroup>
					<tr>
						<th colspan="2" style="text-align:center;">${vd.n.noticeTitle }</th>
					</tr>
					<tr>
						<td>작성자 : ${vd.n.noticeName }(${vd.n.noticeId })</td>
						<td>작성일 : ${vd.n.noticeDate2 }</td>
					</tr>
					<c:if test="${not empty vd.n.noticeFilename }">
						<tr>
							<td colspan="2">
								<a style="float:right;" href="javascript:fileDownload('${vd.n.noticeFilename }','${vd.n.noticeFilepath }');">${vd.n.noticeFilename }</a>
								<br/>
								<img src='/siUpload/notice/${vd.n.noticeFilename }'width="500px"/>
								<!-- 파일이 있으면 넘겨준 No를 기준으로 게시물의 이름을 불러와서 출력 -->
							</td>
						</tr>
					</c:if>
					<tr>
						<td colspan="2">${vd.n.noticeContent }</td>
					</tr>
				</table>
				<form action="/siNoticeCommentInsert" method="post">
					<input type="hidden" name="memberId" value="${sessionScope.member.id }"/>
					<input type="hidden" name="memberName" value="${sessionScope.member.name }"/>
					<input type="hidden" name="noticeNo" value="${vd.n.noticeNo }"/>
					<input type="hidden" name="noticeType" value="0"/>
					<table class="comm-tbl view" id="commentTb">
						<c:if test="${not empty sessionScope.member.id }">
							<tr>
								<td colspan="4" style="text-align:center">
									댓글입력 <input type="text" name="noticeCommentContent" value=""/>
									<button type="submit">등록</button>
								</td>
							</tr>
						</c:if>
					</table>
				</form>
				<form id="cmtUpdateForm" action="/siNoticeCommentUpdate" method="post">
					<input type="hidden" name="memberId" value="${sessionScope.member.id }"/>
					<input type="hidden" name="noticeNo" value="${vd.n.noticeNo }"/>
					<table class="comm-tbl view">
						<c:forEach items="${vd.list }" var="list">
							<c:if test="${list.noticeRef == vd.n.noticeNo}">
								<input type="hidden" name="noticeCommentNo" value="${list.noticeCommentNo }"/>
							<!-- 해당 게시글에 입력된 댓글만 출력되도록 -->
								<tr>
									<td width="20%">${list.noticeCommentName }(${list.noticeCommentId })</td>
									<td width="65%">
										<span>${list.noticeCommentContent }</span>
										<input type="text" value="${list.noticeCommentContent }" name="noticeCommentContent" style="display:none;"/>
									</td>
									<td width="10%">
										${list.noticeCommentDate }<br/>
										<c:if test="${sessionScope.member.id==list.noticeCommentId }">
										<!-- 댓글 작성자일 때 수정/삭제 가능하도록 -->
											<button type="button">수정</button>
											<button type="text" style="display:none;">/</button>
											<button type="reset" style="display:none;">취소</button>
											/
											<a href="/siNoticeCommentDelete?noticeCommentNo=${list.noticeCommentNo }&noticeNo=${vd.n.noticeNo }">삭제</a>
										</c:if>
										<c:if test="${sessionScope.member.id!=list.noticeCommentId && sessionScope.member.id eq 'admin' }">
										<!-- 작성자가 아니면서 id가 admin인 경우 댓글을 삭제 가능하도록 -->
											<a href="/siNoticeCommentDelete?noticeCommentNo=${list.noticeCommentNo }&noticeNo=${vd.n.noticeNo }">삭제</a>
										</c:if>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</table>
				</form>
				<form action="/siNoticeUpdateOriginal?noticeNo=${vd.n.noticeNo }" method="post" enctype="multipart/form-data">
					<div class="common-tbl-btn-group" style="text-align:right;">
						<c:if test='${sessionScope.member.id==vd.n.noticeId }'>
						<!-- 회원 아이디와 글 작성자의 아이디가 같거나 관리자라면 수정/삭제 버튼 생성 -->
							<button type="submit" class="btn-style3">수정</button>
							<button type="button" id="noticeDelBtn" class="btn-style3">삭제</button>
						</c:if>
						<button type="button" class="btn-style2" onclick="location.href='/siNotice'">목록으로 이동</button>
					</div>
				</form>
			</div>
		</div>
	</section>
</body>
<script>
	$(document).ready(function(){
		$('button').eq(1).click(function(){
			$(this).parent().prev().children().eq(0).hide();
			$(this).parent().prev().children().eq(1).show();
			$(this).html('등록').attr("id","cmtUpdate");
			$(this).nextAll().show();
			$('button').next(1).click(function(){
				location.href='/siNoticeView?noticeNo='+${vd.n.noticeNo };
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
	$(document).ready(function(){
		$('#noticeDelBtn').click(function(){
			if(confirm("게시글을 삭제하시겠습니까?")){
				location.href = '/siNoticeDelete?noticeNo='+${vd.n.noticeNo };
			}
		});
	});
	function fileDownload(noticeFilename,noticeFilepath){
		var url = "/siNoticeFileDownload";
		var encFilename = encodeURIComponent(noticeFilename);
		var encFilepath = encodeURIComponent(noticeFilepath);
		location.href=url+'?filename='+encFilename+"&filepath="+encFilename;
	}
</script>
<jsp:include page="/WEB-INF/common/footer.jsp" />
</html>