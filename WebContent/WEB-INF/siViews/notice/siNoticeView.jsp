<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />

<style>
.cmt-txt{display:inline-block; vertical-align:middle; font-size:14px; font-weight:500;}
.cmtBtn{display:inline-block; vertical-align:middle; font-size:14px; background:rgba(254,67,30); padding:8px 20px; color:#fff; border-radius:5px;}
.noticeCommentContent{max-width:85%;}
</style>

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
							<td colspan="2" style="border-bottom: 0px;">
								<a style="float:right;" href="javascript:fileDownload('${vd.n.noticeFilename }','${vd.n.noticeFilepath }');">${vd.n.noticeFilename }</a>
								<br/>
								<img src='/siUpload/notice/${vd.n.noticeFilename }' width="500px"/>
								<!-- 파일이 있으면 넘겨준 No를 기준으로 게시물의 이름을 불러와서 출력 -->
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty sessionScope.member.id }">
						<tr >
							<td colspan="2" style="border-bottom: 0px;border-top: 0px;">${vd.n.noticeContent }</td>
						</tr>
						<tr>
							<td colspan="2" style="border-top: 0px;text-align:right;">
								<button type="button" class="cmtBtn">댓글</button>
							</td>
						</tr>
					</c:if>
					<c:if test="${ empty sessionScope.member.id }">
						<tr>
							<td colspan="2" style="border-top: 0px;">${vd.n.noticeContent }</td>
						</tr>
					</c:if>
				</table>
				<form action="/siNoticeCommentInsert" method="post">
					<input type="hidden" name="memberId" value="${sessionScope.member.id }"/>
					<input type="hidden" name="memberName" value="${sessionScope.member.name }"/>
					<input type="hidden" name="noticeNo" value="${vd.n.noticeNo }"/>
					<input type="hidden" name="noticeType" value="0"/>
					<table class="comm-tbl view" id="commentTb" style="display:none;">
						<tr>
							<td colspan="4" style="text-align:center;">
								<span class="cmt-txt">댓글 입력</span>&nbsp;&nbsp;<input type="text" name="noticeCommentContent" value="" maxlength="50" class="noticeCommentContent"/>
								<button type="submit" class="cmtBtn">등록</button>
							</td>
						</tr>
					</table>
				</form>
				<c:forEach items="${vd.list }" var="list" varStatus="i">
					<form action="/siNoticeCommentUpdate" method="post">
						<input type="hidden" name="memberId" value="${sessionScope.member.id }"/>
						<input type="hidden" name="noticeNo" value="${vd.n.noticeNo }"/>
						<table class="comm-tbl view">
							<c:if test="${list.noticeRef == vd.n.noticeNo && list.noticeCommentRef == 0 }">
							<!-- 해당 게시글에 입력된 댓글만 출력되도록 -->
								<input type="hidden" name="noticeCommentNo" value="${list.noticeCommentNo }"/>
								<tr>
									<td width="20%">${list.noticeCommentName }(${list.noticeCommentId })</td>
									<td width="65%">
										<span>${list.noticeCommentContent }</span>
										<input type="text" value="${list.noticeCommentContent }" name="noticeCommentContent" style="display:none;"/>
									</td>
									<td width="11%">
										${list.noticeCommentDate2 }<br/>
										<c:if test="${sessionScope.member.id==list.noticeCommentId }">
										<!-- 댓글 작성자일 때 수정/삭제 가능하도록 -->
											<button class="mdfBtn" type="button">수정</button>
											<button type="text" style="display:none;">/</button>
											<button class="cancelBtn" type="reset" style="display:none;">취소</button>
											/
											<a href="#" class="cmtDelBtn" onclick="cmtDelBtn('${list.noticeCommentNo }');">삭제</a>
											/
										</c:if>
										<c:if test="${sessionScope.member.id!=list.noticeCommentId && sessionScope.member.id eq 'admin' }">
										<!-- 작성자가 아니면서 id가 admin인 경우 댓글을 삭제 가능하도록 -->
											<a href="#" class="cmtDelBtn" onclick="cmtDelBtn('${list.noticeCommentNo }');">삭제2</a>
											/
										</c:if>
										<c:if test="${not empty sessionScope.member.id }"><!-- 로그인시 노출 -->
											<button type="button" class="reCmtBtn">대댓글</button>
										</c:if>
									</td>
								</tr>
							</c:if>
							<c:forEach items="${vd.list }" var="clist"><!-- 대댓글 조회를 위해 forEach 내부에 forEach 사용 -->
								<c:if test="${clist.noticeCommentRef == list.noticeCommentNo && not empty clist.noticeCommentRef && clist.noticeRef == vd.n.noticeNo }">
									<tr>
										<td width="20%"> └─ ${clist.noticeCommentName }(${clist.noticeCommentId })</td>
										<td width="65%">
											<span>${clist.noticeCommentContent }</span>
											<input type="text" value="${clist.noticeCommentContent }" class="noticeReCommentModify${clist.noticeCommentNo }" style="display:none;"/>
										</td>
										<td width="11%">
											${clist.noticeCommentDate2 }<br/>
											<c:if test="${clist.noticeCommentId == sessionScope.member.id }">
												<button class="cmtrUpdate" type="button" onclick="cmtrMfy('${clist.noticeCommentRef }','${clist.noticeCommentNo }')" style="display:none;">등록</button>
												<button class="mdfBtnr" type="button">수정</button>
												<button type="text" style="display:none;">/</button>
												<button class="cancelBtnr" type="reset" style="display:none;">취소</button>
												/
												<a href="#" class="rcmtDelBtn" onclick="rcmtDelBtn('${clist.noticeCommentNo }','${clist.noticeCommentRef }');">삭제</a>
											</c:if>
											<c:if test="${sessionScope.member.id!=clist.noticeCommentId && sessionScope.member.id eq 'admin' }">
											<!-- 작성자가 아니면서 id가 admin인 경우 댓글을 삭제 가능하도록 -->
												<a href="#" class="rcmtDelBtn" onclick="rcmtDelBtn('${clist.noticeCommentNo }','${clist.noticeCommentRef }');">삭제2</a>
											</c:if>
										</td>
									</tr>
								</c:if>
							</c:forEach>
							<tr style="display:none;"><!-- 대댓글 버튼 클릭시 입력창 노출 -->
								<td> -> re : ${sessionScope.member.name }(${sessionScope.member.id })</td>
								<td>
									<input type="text" name="noticeReCommentContent" class="noticeReCommentContent${list.noticeCommentNo }"  placeholder="대댓글을 입력하세요" maxlenth="50">
								</td>
								<td>
									<button onclick="sendReCmt('${list.noticeCommentNo }')" type="button">대댓글 등록하기</button>
								</td>
							</tr>
						</table>							
					</form>
				</c:forEach>
				<form action="/siNoticeUpdateOriginal?noticeNo=${vd.n.noticeNo }" method="post" enctype="multipart/form-data">
					<div class="common-tbl-btn-group" style="text-align:right;">
						<c:if test='${sessionScope.member.id==vd.n.noticeId }'>
							<button type="submit" class="btn-style3">수정</button>
						</c:if>
						<c:if test='${sessionScope.member.id==vd.n.noticeId || sessionScope.member.id eq "admin" }'>
							<button type="button" id="noticeDelBtn" class="btn-style3">삭제</button>
						</c:if>
						<button type="button" class="btn-style2" onclick="location.href='/siNotice'">목록으로 이동</button>
					</div>
				</form>
			</div>
		</div>
	</section>

<script>
	function sendReCmt(noticeCommentNo){	//대댓글 전송
		var memberId = '${sessionScope.member.id }';		
		var memberName = '${sessionScope.member.name }';
		var noticeCommentContent = $(".noticeReCommentContent"+noticeCommentNo).val();
		location.href="/siNoticeReCommentInsert?noticeType=0"+"&noticeRef="+${vd.n.noticeNo }
			+"&memberId="+memberId+"&memberName="+memberName+"&noticeCommentContent="+noticeCommentContent
			+"&noticeNo="+${vd.n.noticeNo }+"&noticeCommentRef="+noticeCommentNo;
	}
	function rcmtDelBtn(noticeCommentNo,noticeCommentRef){//대댓글 삭제확인
		if(confirm("댓글을 삭제하시겠습니까?")){
			location.href="/siNoticeReCommentDelete?noticeCommentNo="+noticeCommentNo+"&noticeNo="+${vd.n.noticeNo }+"&noticeCommentRef="+noticeCommentRef;
		}
	};
	function cmtDelBtn(noticeCommentNo){ //댓글 삭제확인
		if(confirm("댓글을 삭제하시겠습니까?")){
			location.href="/siNoticeCommentDelete?noticeCommentNo="+noticeCommentNo+"&noticeNo="+${vd.n.noticeNo };
		}
	};
	$(document).ready(function(){	//대댓글 입력 tr 노출
		$('.reCmtBtn').click(function(){
			$(this).hide();
			$(this).parent().parent().parent().children().last().show();
		});
	});
	$(document).ready(function(){// 댓글 입력창 노출
		$('.cmtBtn').click(function(){
			$('#commentTb').show();
		});
	});
	$(document).ready(function(){//댓글 수정,취소 버튼  
		$('.mdfBtn').click(function(){
			$(this).parent().prev().children().eq(0).hide();
			$(this).parent().prev().children().eq(1).show();
			$(this).html('등록').attr("class","cmtUpdate");
			$(this).nextAll().show();
			$('.cancelBtn').click(function(){
				location.href='/siNoticeView?noticeNo='+${vd.n.noticeNo };
			});
			$(".cmtUpdate").click(function(){
				$(this).parents('form').submit();
			});
		});
	});
	$(document).ready(function(){	//대댓글 수정,취소 버튼 
		$('.mdfBtnr').click(function(){
			$(this).parent().prev().children().eq(0).hide();
			$(this).parent().prev().children().eq(1).show();
			$(this).hide();
			$('.cmtrUpdate').show();
			$(this).nextAll().show();
			$('.cancelBtnr').click(function(){
				location.href='/siNoticeView?noticeNo='+${vd.n.noticeNo };
			});
		});
	});
	function cmtrMfy(noticeCommentRef,noticeCommentNo){//대댓글 수정
		var noticeCommentContent2 = $('.noticeReCommentModify'+noticeCommentNo).val();
		location.href="/siNoticeReCommentUpdate?noticeCommentContent="+noticeCommentContent2
			+"&noticeNo="+${vd.n.noticeNo }+"&noticeCommentRef="+noticeCommentRef+"&noticeCommentNo="+noticeCommentNo;
	}
	$(document).ready(function(){	//게시글 삭제확인
		$('#noticeDelBtn').click(function(){
			if(confirm("게시글을 삭제하시겠습니까?")){
				location.href = '/siNoticeDelete?noticeNo='+${vd.n.noticeNo };
			}
		});
	});
	function fileDownload(noticeFilename,noticeFilepath){	//파일 다운로드
		var url = "/siNoticeFileDownload";
		var encFilename = encodeURIComponent(noticeFilename);
		var encFilepath = encodeURIComponent(noticeFilepath);
		location.href=url+'?filename='+encFilename+"&filepath="+encFilename;
	}
</script>

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />