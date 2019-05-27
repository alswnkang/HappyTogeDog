<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<section class="modal-content">
	<h1>봉사활동 신청하기</h1>
	<div class="modal-inner-con">
		<div class="modal-inner">
			<div class="voluntary-bottom-inner">
				<form action="" method="post">
					<table class="comm-tbl"><!-- 봉사활동 신청은 회원 전용 -->
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
						<button type="submit" class="btn-style1">신청하기</button>
						<button type="button" onclick="javascript:;" class="modal-close-btn btn-style2">닫기</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>

<script>
// 봉사활동 신청 모달 창 닫기
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