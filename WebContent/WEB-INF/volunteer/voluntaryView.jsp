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
								<span><b>작성일</b>2019-05-26</span>
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
							(공고 등록 시 첨부파일 이미지가 여기에 같이 노출됨.)
						</td>
					</tr>
				</table>
				<div class="common-tbl-btn-group" style="text-align:right;">
					<button type="button" class="btn-style2" onclick="location.href='/volunteerList'">목록으로</button>
				</div>
			</div>
			<!-- 봉사활동 신청 폼 -->
			<div class="voluntary-bottom-box">
				<h2 class="comm-content-tit">봉사활동 신청하기</h2>
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
							<button type="submit" class="btn-style1">신청</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</section>

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />