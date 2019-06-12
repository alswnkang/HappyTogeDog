<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">봉사활동 공고등록</h2>
		<div id="voluntaryRegisterBox" class="common-tbl-box"><!-- id는 바꿔서 복붙 -->
			<form action="/voluntaryRegister" method="post" enctype="multipart/form-data">
				<table class="comm-tbl"><!-- 공고등록은 보호소회원 전용메뉴(보호소회원 로그인시에만 사용가능) :: 로그인 후 등록하려는 보호소 회원의 보호소 코드, 보호소명 fix -->
					<colgroup>
						<col width="28%">
						<col width="/">
					</colgroup>
					<tr>
						<th>보호소 코드</th>
						<td><input type="text" name="code" value="${sessionScope.member.code}" readonly></td>
					</tr>
					<tr>
						<th>보호소 명</th>
						<td><input type="text" name="name" value="${sessionScope.member.name}" readonly></td>
					</tr>
					<tr>
						<th>봉사활동 공고 제목</th>
						<td><input type="text" name="title"></td>
					</tr>				
					<tr>
						<th>봉사 날짜</th>
						<td><input type="text" id="datepicker2" name="volunDate"></td>
					</tr>
					<tr>
						<th>봉사 시간</th>
						<td>
							<input type="text" name="volunTime1" class="short num"> 시&nbsp;&nbsp;~&nbsp;&nbsp;<input type="text" name="volunTime2" class="short num"> 시 &nbsp;&nbsp;
							(ex. 9시 ~ 15시)
						</td>
					</tr>
					<tr>
						<th>봉사 가능 인원 수</th>
						<td><input type="text" name="person" class="short num"> 명</td>
					</tr>
					<tr>
						<th>봉사 상세설명</th>
						<td><textarea name="detail" rows="10"></textarea></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td><input type="file" name="filename"><p class="comment">(※ 파일 크기는 최대 10MB까지만 가능합니다.)</p></td>
					</tr>
				</table>
				<div class="common-tbl-btn-group">
					<button type="submit" class="btn-style1">등록</button><button type="reset" class="btn-style2">취소</button>
				</div>
			</form>
		</div>
	</div>
</section>

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />