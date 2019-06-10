<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />
<%pageContext.setAttribute("newLineChar", "\n"); %>
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
						<th colspan="2" style="text-align:center;">${vd.a.adoptionBoardTitle }</th>
					</tr>
					<tr>
						<td>작성자 : ${vd.a.adoptionBoardName }(${vd.a.adoptionBoardId })</td>
						<td>작성일 : ${vd.a.adoptionBoardDate2 }</td>
					</tr>
					<c:if test="${not empty vd.a.adoptionBoardFilename }">
					<!-- 파일이 있을 때 -->
						<c:if test="${not empty sessionScope.member.id }">
						<!-- 로그인 된 경우 사진과 내용, 댓글 버튼 노출 -->
						<tr>
							<td colspan="2" style="text-align:center;border-bottom: 0px;">
								<a style="float:right;" href="javascript:fileDownload('${vd.a.adoptionBoardFilename }','${vd.a.adoptionBoardFilepath }');">${vd.a.adoptionBoardFilename }</a>
								<br/>
								<img src='/siUpload/adoptionBoard/${vd.a.adoptionBoardFilename }'width="500px"/>
								<!-- 파일이 있으면 넘겨준 No를 기준으로 게시물의 이름을 불러와서 출력 -->
								<br/><br/><br/>
								${fn:replace(vd.a.adoptionBoardContent,newLineChar,"<br/>")}
							</td>
						</tr>
							<tr>
								<td colspan="2" class="cmtBtn" style="border-top: 0px;">
									<button type="button" style="float:right;">댓글</button>
								</td>
							</tr>
						</c:if>
						<c:if test="${empty sessionScope.member.id }">
						<!-- 로그인 안된 경우 사진과 내용만 -->
							<tr>
								<td colspan="2" style="text-align:center;">
									<a style="float:right;" href="javascript:fileDownload('${vd.a.adoptionBoardFilename }','${vd.a.adoptionBoardFilepath }');">${vd.a.adoptionBoardFilename }</a>
									<br/>
									<img src='/siUpload/adoptionBoard/${vd.a.adoptionBoardFilename }'width="500px"/>
									<!-- 파일이 있으면 넘겨준 No를 기준으로 게시물의 이름을 불러와서 출력 -->
									<br/><br/><br/>
									${fn:replace(vd.a.adoptionBoardContent,newLineChar,"<br/>")}
								</td>
							</tr>
						</c:if>
					</c:if>
					<c:if test="${empty vd.a.adoptionBoardFilename }">
					<!-- 파일이 없을 때 -->
						<c:if test="${not empty sessionScope.member.id }">
						<!-- 로그인 된 경우 내용과 댓글 버튼 노출 -->
							<tr>
								<td colspan="2" style="text-align:center;border-bottom: 0px;">
									${vd.a.adoptionBoardContent }
								</td>
							</tr>
							<tr>
								<td colspan="2" class="cmtBtn" style="border-top: 0px;">
									<button type="button" style="float:right;">댓글</button>
								</td>
							</tr>
						</c:if>
						<c:if test="${empty sessionScope.member.id }">
						<!-- 로그인 안된 경우 내용만 -->
							<tr>
								<td colspan="2" style="text-align:center;">
									${vd.a.adoptionBoardContent }
								</td>
							</tr>
						</c:if>
					</c:if>
				</table>
				<form action="/siAdoptionBoardCommentInsert" method="post">
					<input type="hidden" name="memberId" value="${sessionScope.member.id }"/>
					<input type="hidden" name="memberName" value="${sessionScope.member.name }"/>
					<input type="hidden" name="adoptionBoardNo" value="${vd.a.adoptionBoardNo }"/>
					<input type="hidden" name="adoptionBoardType" value="2"/>
					<table class="comm-tbl view" id="commentTb" style="display:none;">
						<tr>
							<td colspan="4" style="text-align:center">
								댓글입력 <input type="text" name="adoptionBoardCommentContent" value=""/>
								<button type="submit">등록</button>
							</td>
						</tr>
					</table>
				</form>
				<c:forEach items="${vd.list }" var="list">
					<form action="/siAdoptionBoardCommentUpdate" method="post">
						<input type="hidden" name="memberId" value="${sessionScope.member.id }"/>
						<input type="hidden" name="adoptionBoardNo" value="${vd.a.adoptionBoardNo }"/>
						<table class="comm-tbl view">
							<c:if test="${list.adoptionBoardRef == vd.a.adoptionBoardNo && list.adoptionBoardCommentRef == 0 }">
							<!-- 해당 게시글에 입력된 댓글만 출력되도록 -->
								<input type="hidden" name="adoptionBoardCommentNo" value="${list.adoptionBoardCommentNo }"/>
								<tr>
									<td width="20%">${list.adoptionBoardCommentName }(${list.adoptionBoardCommentId })</td>
									<td width="65%">
										<span>${list.adoptionBoardCommentContent }</span>
										<input type="text" value="${list.adoptionBoardCommentContent }" name="adoptionBoardCommentContent" style="display:none;"/>
									</td>
									<td width="11%">
										${list.adoptionBoardCommentDate2 }<br/>
										<c:if test="${sessionScope.member.id==list.adoptionBoardCommentId }">
										<!-- 댓글 작성자일 때 수정/삭제 가능하도록 -->
											<button class="mdfBtn" type="button">수정</button>
											<button type="text" style="display:none;">/</button>
											<button class="cancelBtn" type="reset" style="display:none;">취소</button>
											/
											<a href="#" class="cmtDelBtn" onclick="cmtDelBtn('${list.adoptionBoardCommentNo }');">삭제</a>
											/
										</c:if>
										<c:if test="${sessionScope.member.id!=list.adoptionBoardCommentId && sessionScope.member.id eq 'admin' }">
										<!-- 작성자가 아니면서 id가 admin인 경우 댓글을 삭제 가능하도록 -->
											<a href="#" class="cmtDelBtn" onclick="cmtDelBtn('${list.adoptionBoardCommentNo }');">삭제2</a>
											/
										</c:if>
										<c:if test="${not empty sessionScope.member.id }"><!-- 로그인시 노출 -->
											<button type="button" class="reCmtBtn">대댓글</button>
										</c:if>
									</td>
								</tr>
							</c:if>
							<c:forEach items="${vd.list }" var="clist"><!-- 대댓글 조회를 위해 forEach 내부에 forEach 사용 -->
								<c:if test="${clist.adoptionBoardCommentRef == list.adoptionBoardCommentNo && not empty clist.adoptionBoardCommentRef && clist.adoptionBoardRef == vd.a.adoptionBoardNo }">
									<tr>
										<td width="20%"> └─ ${clist.adoptionBoardCommentName }(${clist.adoptionBoardCommentId })</td>
										<td width="65%">
											<span>${clist.adoptionBoardCommentContent }</span>
											<input type="text" value="${clist.adoptionBoardCommentContent }" class="adoptionBoardReCommentModify${clist.adoptionBoardCommentNo }" style="display:none;"/>
										</td>
										<td width="11%">
											${clist.adoptionBoardCommentDate2 }<br/>
											<c:if test="${clist.adoptionBoardCommentId == sessionScope.member.id }">
												<button class="cmtrUpdate" type="button" onclick="cmtrMfy('${clist.adoptionBoardCommentRef }','${clist.adoptionBoardCommentNo }')" style="display:none;">등록</button>
												<button class="mdfBtnr" type="button">수정</button>
												<button type="text" style="display:none;">/</button>
												<button class="cancelBtnr" type="reset" style="display:none;">취소</button>
												/
												<a href="#" class="rcmtDelBtn" onclick="rcmtDelBtn('${clist.adoptionBoardCommentNo }','${clist.adoptionBoardCommentRef }');">삭제</a>
											</c:if>
											<c:if test="${sessionScope.member.id!=clist.adoptionBoardCommentId && sessionScope.member.id eq 'admin' }">
											<!-- 작성자가 아니면서 id가 admin인 경우 댓글을 삭제 가능하도록 -->
												<a href="#" class="rcmtDelBtn" onclick="rcmtDelBtn('${clist.adoptionBoardCommentNo }','${clist.adoptionBoardCommentRef }');">삭제2</a>
											</c:if>
										</td>
									</tr>
								</c:if>
							</c:forEach>
							<tr style="display:none;"><!-- 대댓글 버튼 클릭시 입력창 노출 -->
								<td> -> re : ${sessionScope.member.name }(${sessionScope.member.id })</td>
								<td>
									<input type="text" name="adoptionBoardReCommentContent" class="adoptionBoardReCommentContent${list.adoptionBoardCommentNo }"  placeholder="대댓글을 입력하세요" maxlenth="50">
								</td>
								<td>
									<button onclick="sendReCmt('${list.adoptionBoardCommentNo }')" type="button">대댓글 등록하기</button>
								</td>
							</tr>
						</table>
					</form>
				</c:forEach>
				<form action="/siAdoptionBoardUpdateOriginal?adoptionBoardNo=${vd.a.adoptionBoardNo }" method="post" enctype="multipart/form-data">
					<div class="common-tbl-btn-group" style="text-align:right;">
						<c:if test='${sessionScope.member.id==vd.a.adoptionBoardId }'>
						<!-- 회원 아이디와 글 작성자의 아이디가 같을때만 수정버튼 생성-->
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

<script>
	function sendReCmt(adoptionBoardCommentNo){	//대댓글 전송
		var memberId = '${sessionScope.member.id }';		
		var memberName = '${sessionScope.member.name }';
		var adoptionBoardCommentContent = $(".adoptionBoardReCommentContent"+adoptionBoardCommentNo).val();
		location.href="/siAdoptionBoardReCommentInsert?adoptionBoardType=2"+"&adoptionBoardRef="+${vd.a.adoptionBoardNo }
			+"&memberId="+memberId+"&memberName="+memberName+"&adoptionBoardCommentContent="+adoptionBoardCommentContent
			+"&adoptionBoardNo="+${vd.a.adoptionBoardNo }+"&adoptionBoardCommentRef="+adoptionBoardCommentNo;
	}
	function rcmtDelBtn(adoptionBoardCommentNo,adoptionBoardCommentRef){//대댓글 삭제확인
		if(confirm("댓글을 삭제하시겠습니까?")){
			location.href="/siAdoptionBoardReCommentDelete?adoptionBoardCommentNo="+adoptionBoardCommentNo+"&adoptionBoardNo="+${vd.a.adoptionBoardNo }+"&adoptionBoardCommentRef="+adoptionBoardCommentRef;
		}
	};
	function cmtDelBtn(adoptionBoardCommentNo){ //댓글 삭제확인
		if(confirm("댓글을 삭제하시겠습니까?")){
			location.href="/siAdoptionBoardCommentDelete?adoptionBoardCommentNo="+adoptionBoardCommentNo+"&adoptionBoardNo="+${vd.a.adoptionBoardNo };
		}
	};
	$(document).ready(function(){	//대댓글 입력 tr 노출
		$('.reCmtBtn').click(function(){
			$(this).hide();
			$(this).parent().parent().parent().children().last().show();
		});
	});
	$(document).ready(function(){	// 댓글 입력창 노출
		$('.cmtBtn').click(function(){
			$('#commentTb').show();
		});
	});
	$(document).ready(function(){	//댓글 수정,취소 버튼  
		$('.mdfBtn').click(function(){
			$(this).parent().prev().children().eq(0).hide();
			$(this).parent().prev().children().eq(1).show();
			$(this).html('등록').attr("class","cmtUpdate");
			$(this).nextAll().show();
			$('.cancelBtn').click(function(){
				location.href='/siAdoptionBoardView?adoptionBoardNo='+${vd.a.adoptionBoardNo };
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
				location.href='/siAdoptionBoardView?adoptionBoardNo='+${vd.a.adoptionBoardNo };
			});
		});
	});
	function cmtrMfy(adoptionBoardCommentRef,adoptionBoardCommentNo){	//대댓글 수정
		var adoptionBoardCommentContent2 = $('.adoptionBoardReCommentModify'+adoptionBoardCommentNo).val();
		location.href="/siAdoptionBoardReCommentUpdate?adoptionBoardCommentContent="+adoptionBoardCommentContent2
			+"&adoptionBoardNo="+${vd.a.adoptionBoardNo }+"&adoptionBoardCommentRef="+adoptionBoardCommentRef+"&adoptionBoardCommentNo="+adoptionBoardCommentNo;
	}
	$(document).ready(function(){	//삭제 확인
		$('#adoptionBoardDelBtn').click(function(){
			if(confirm("게시글을 삭제하시겠습니까?")){
				location.href = '/siAdoptionBoardDelete?adoptionBoardNo='+${vd.a.adoptionBoardNo };
			}
		});
	});
	function fileDownload(adoptionBoardFilename,adoptionBoardFilepath){
		var url = "/siAdoptionFileDownload";
		var encFilename = encodeURIComponent(adoptionBoardFilename);
		var encFilepath = encodeURIComponent(adoptionBoardFilepath);
		location.href=url+'?filename='+encFilename+"&filepath="+encFilename;
	}
</script>

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />