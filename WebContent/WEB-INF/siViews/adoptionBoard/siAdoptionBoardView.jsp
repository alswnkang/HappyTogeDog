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
						<col width="/">
						<col width="/">
						<col width="100%">
					</colgroup>
					<tr>
						<th>제목</th>
						<td>${vd.a.adoptionBoardTitle }</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<c:if test="${not empty vd.a.adoptionBoardFilename }">
								<img src='/siUpload/adoptionBoard/${vd.a.adoptionBoardFilename }'width="800px"/>
								<!-- 파일이 있으면 넘겨준 No를 기준으로 게시물의 이름을 불러와서 출력 -->
								<br/>
								${vd.a.adoptionBoardContent }
							</c:if>
							<c:if test="${empty vd.a.adoptionBoardFilename }">
								${vd.a.adoptionBoardContent }
							</c:if>
						</td>
					</tr>
				</table>
				<form action="/siAdoptionBoardCommentInsert" method="post">
					<input type="hidden" name="memberId" value="${sessionScope.member.id }"/>
					<input type="hidden" name="memberName" value="${sessionScope.member.name }"/>
					<input type="hidden" name="adoptionBoardNo" value="${vd.a.adoptionBoardNo }"/>
					<input type="hidden" name="adoptionBoardType" value="2"/>
					<table class="comm-tbl view" id="commentTb">
						<c:if test="${not empty sessionScope.member.id }">
							<tr>
								<td colspan="4" style="text-align:center">
									댓글입력 <input type="text" name="adoptionBoardCommentContent" value=""/>
									<button type="submit">등록</button>
								</td>
							</tr>
						</c:if>
					</table>
				</form>
				<form id="cmtUpdateForm" action="/siAdoptionBoardCommentUpdate" method="post">
					<input type="hidden" name="memberId" value="${sessionScope.member.id }"/>
					<input type="hidden" name="adoptionBoardNo" value="${vd.a.adoptionBoardNo }"/>
					<table class="comm-tbl view">
						<c:forEach items="${vd.list }" var="list">
							<c:if test="${list.adoptionBoardRef == vd.a.adoptionBoardNo}">
								<input type="hidden" name="adoptionBoardCommentNo" value="${list.adoptionBoardCommentNo }"/>
							<!-- 해당 게시글에 입력된 댓글만 출력되도록 -->
								<tr>
									<td width="20%">${list.adoptionBoardCommentName }(${list.adoptionBoardCommentId })</td>
									<td width="65%">
										<span>${list.adoptionBoardCommentContent }</span>
										<input type="text" value="${list.adoptionBoardCommentContent }" name="adoptionBoardCommentContent" style="display:none;"/>
									</td>
									<td width="10%">
										${list.adoptionBoardCommentDate }<br/>
										<c:if test="${sessionScope.member.id==list.adoptionBoardCommentId }">
										<!-- 댓글 작성자일 때 수정/삭제 가능하도록 -->
											<button type="button">수정</button>
											<button type="text" style="display:none;">/</button>
											<button type="reset" style="display:none;">취소</button>
											/
											<a href="/siAdoptionBoardCommentDelete?adoptionBoardCommentNo=${list.adoptionBoardCommentNo }&adoptionBoardNo=${vd.a.adoptionBoardNo }">삭제</a>
										</c:if>
										<c:if test="${sessionScope.member.id!=list.adoptionBoardCommentId && sessionScope.member.id eq 'admin' }">
										<!-- 작성자가 아니면서 id가 admin인 경우 댓글을 삭제 가능하도록 -->
											<a href="/siAdoptionBoardCommentDelete?adoptionBoardCommentNo=${list.adoptionBoardCommentNo }&adoptionBoardNo=${vd.a.adoptionBoardNo }">삭제</a>
										</c:if>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</table>
				</form>
				<form action="/siAdoptionBoardUpdateOriginal?adoptionBoardNo=${vd.a.adoptionBoardNo }" method="post" enctype="multipart/form-data">
					<div class="common-tbl-btn-group" style="text-align:right;">
						<c:if test='${sessionScope.member.id==vd.a.adoptionBoardId }'>
						<!-- 회원 아이디와 글 작성자의 아이디가 같거나 관리자라면 수정/삭제 버튼 생성 -->
							<button type="submit" class="btn-style3">수정</button>
						</c:if>
						<c:if test='${sessionScope.member.id==vd.a.adoptionBoardId || sessionScope.member.id eq "admin" }'>
							<button type="button" id="adoptionBoardDelBtn" class="btn-style3">삭제</button>
						</c:if>
						<button type="button" class="btn-style2" onclick="location.href='/siAdoptionBoard'">목록으로 이동</button>
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
				location.href='/siAdoptionBoardView?adoptionBoardNo='+${vd.a.adoptionBoardNo };
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
		$('#adoptionBoardDelBtn').click(function(){
			if(confirm("게시글을 삭제하시겠습니까?")){
				location.href = '/siAdoptionBoardDelete?adoptionBoardNo='+${vd.a.adoptionBoardNo };
			}
		});
	});
</script>
<jsp:include page="/WEB-INF/common/footer.jsp" />
</html>