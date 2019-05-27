<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<div id="voluntaryViewBox" class="voluntary-box">
			<!-- 봉사활동 상세정보 -->
			<div class="voluntary-top-box">
				<table class="comm-tbl view"><!-- 공고등록은 보호소회원 전용메뉴(보호소회원 로그인시에만 사용가능) :: 로그인 후 등록하려는 보호소 회원의 보호소 코드, 보호소명 fix -->
					<colgroup>
						<col width="20%">
						<col width="/">
						<col width="20%">
						<col width="/">
					</colgroup>
					<tr>
						<th colspan="4">
							<p class="volun-view-tit">봉사활동 공고 제목이 노출됩니다.</p>
							<p class="volun-view-sub-tit">
								<span><b>작성일</b>2019-05-26</span><!-- 공고 등록일이 노출됩니다. -->
							</p>
						</th>
					</tr>
					<tr>
						<th>보호소 명</th>
						<td colspan="3">보호소 명이 들어갑니다.</td>
					</tr>				
					<tr>
						<th>봉사 날짜</th>
						<td>봉사 날짜가 들어갑니다.(ex. 2019-05-26)</td>
						<th>봉사 시간</th>
						<td>봉사 시간이 들어갑니다.(ex. 9시 ~ 16시)</td>
					</tr>
					<tr>
						<th>봉사 가능 인원 수</th>
						<td colspan="3">봉사 가능 인원 수가 들어갑니다.(ex. 10명)</td>
					</tr>
					<tr>
						<th>봉사 상세설명</th>
						<td colspan="3">
							봉사활동 상세 설명 내용이 들어갑니다. 봉사활동 상세 설명 내용이 들어갑니다. 봉사활동 상세 설명 내용이 들어갑니다.
							봉사활동 상세 설명 내용이 들어갑니다. 봉사활동 상세 설명 내용이 들어갑니다. 봉사활동 상세 설명 내용이 들어갑니다.
						</td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td colspan="3"><p><a href="">첨부파일 다운로드를 할 수 있습니다.</a></p></td>
					</tr>
				</table>
				<div class="common-tbl-btn-group" style="text-align:right;">
					<button type="button" class="btn-style1" onclick="javascript:layerLoad('/voluntaryApply');">신청하기</button><!-- 신청 마감시 버튼 변경->신청 마감 누르면 alert('신청이 마감되었습니다.') -->
					<button type="button" class="btn-style2" onclick="location.href='/volunteerList'">목록으로</button>
				</div>
			</div>
			<!-- 봉사활동 신청 폼 --><!-- 봉사활동 신청은 회원 전용 -->
			<!-- <div class="voluntary-bottom-box">
				<h2 class="comm-content-tit">봉사활동 신청하기</h2>
				<div class="voluntary-bottom-inner">
					<form action="" method="post">
						<table class="comm-tbl">
							<colgroup>
								<col width="20%">
								<col width="/">
								<col width="20%">
								<col width="/">
							</colgroup>
							<tr>
								<th>보호소 명</th>
								<td colspan="3"><input type="text" name="" readonly></td>
							</tr>
							<tr class="hidden">
								<th>보호소 코드</th>
								<td colspan="3"><input type="text" name="" readonly></td>
							</tr>
							<tr>
								<th>신청자 아이디</th>
								<td>신청자 아이디가 들어갑니다.</td>
								<th>신청자 전화번호</th>
								<td>신청자 전화번호가 들어갑니다.</td>
							</tr>				
							<tr>
								<th>봉사 날짜</th>
								<td>봉사 날짜가 들어갑니다.(ex. 2019-05-26)</td>
								<th>봉사 시간</th>
								<td>봉사 시간이 들어갑니다.(ex. 9시 ~ 16시)</td>
							</tr>
							<tr>
								<th>봉사 신청 인원 수</th>
								<td colspan="3"><input type="text" name="person" class="short num"> 명</td>
							</tr>
						</table>
						<div class="common-tbl-btn-group">
							<button type="submit" class="btn-style1">신청</button>
						</div>
					</form>
				</div>
			</div> -->
		</div>
	</div>
</section>

<%-- 모달 레이어 팝업 --%>
<article class="modal-fixed-pop-wrapper">
	<div class="modal-fixed-pop-inner">
		<div class="modal-loading"><span class="loading"></span></div>
		<div class="modal-inner-box">
			<div class="modal-inner-content"></div>
		</div>
	</div>
</article>

<%-- 모달 레이어 팝업 script --%>
<script>
function layerLoad(strUrl){
	var $modalWrap = $(".modal-fixed-pop-wrapper");

	$("html").css({
		"margin-right":"17px",
		"overflow-y":"hidden"
	});
	$modalWrap.fadeIn();
	
	$.ajax({
		type: "POST",
		url: strUrl,
		data: "",
		success: function(resultText){
			$modalWrap.find(".modal-inner-content").html(resultText);
		},
		error: function() {
			alert("호출에 실패했습니다.");
			$(".modal-fixed-pop-wrapper").hide();
			$("html").css({
				"margin-right":"0",
				"overflow-y":"scroll"
			});
		},
		beforeSend:function(){ 
			$('.modal-loading').fadeIn(); 
		},
		complete:function(){ 
			$('.modal-loading').hide();
		}
	});
}

// 모달 창 닫기
$(document).ready(function  () {
	var $modalWrap = $(".modal-fixed-pop-wrapper");
	$(".modal-close-btn").click(function  () {
		$(".modal-inner-content").empty();
		$modalWrap.css("display","none");
		$("html").css({
			"margin-right":"0",
			"overflow-y":"scroll"
		});
		$modalWrap.fadeOut();
		return false;
	});
});
</script>

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />