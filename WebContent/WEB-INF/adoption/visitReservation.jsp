<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css" href="/css/reservationContent_bk.css">	<!-- css -->
<!-- Header -->
<jsp:include page="/WEB-INF/common/header.jsp" />

<!-- 보호소 코드 디비에 저장해야하기 때문에 전 페이지에서 받아와야함, 아이디는 세션에서 받아오기 -->

<!-- Content -->
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">보호소 방문 예약</h2>
		<div id="reservationBox" class="common-tbl-box">
			<form action="/visitReservationComplete" method="post">
				<table class="comm-tbl">
					<colgroup>
						<col width="35%">
						<col width="/">
					</colgroup>
					<tr>
						<th>보호소명</th>
						<td><input type="text" name="careNm" value="받아오기" readonly> </td>
					</tr>
					<tr>
						<th>보호소 주소</th>
						<td><input type="text" name="careAddr" value="받아오기" readonly></td>	
					</tr>
					<tr>
						<th>보호소 전화번호</th>
						<td><input type="text" name="careTel" value="받아오기" readonly></td>	
					</tr>
					<tr>
						<th>신청자 이름</th>
						<td><input type="text" name="name" value="받아오기" readonly></td>
					</tr>
					<!-- <tr>
						<th>신청자 아이디</th>
						<td>받아오기</td>
					</tr> -->
					<tr>
						<th>신청자 전화번호</th>
						<td><input type="text" name="phone" value="받아오기"></td>	<!-- 받아오는건 value로하고 새로 입력도 가능하게 -->
					</tr>
					<tr>
						<th>마당이 있습니까?</th>
						<td>
						<label><input type="radio" id="yard1" name="yard" value="있음">있음 </label>
						<label><input type="radio" id="yard2" name="yard" value="없음"> 없음</label>
						</td>
					</tr>
					<tr>
						<th>키우고 있는 애완동물이 있습니까?</th>
						<td>
							<input type="text" name="animal" placeholder="키우고 있다면 애완동물의 종류, 수 등 대해 상세히 적어주세요.">
						</td>
					</tr>
					<tr>
						<th>가족 구성원</th>
						<td>
							<input type="text" name="family" placeholder="가족구성원, 수 등 대해 상세히 적어주세요.">
						</td>
					</tr>
					<tr>
						<th>강아지를 키워본 경험이 있습니까?</th>
						<td>
							<input type="text" name="experience">
						</td>
					</tr>
					<tr>
						<th>강아지와 함께 있어줄 수 있는 <br>평균시간은 얼마입니까?</th>
						<td>
							<input type="text" name="avgTime">
						</td>
					</tr>
					<tr>
						<th>방문 날짜</th>
						<td>
							<input type="text" id="datepicker" name="visitDate">
						</td>
					</tr>
					<tr>
						<th>방문 시간</th>
						<td>
							<select name="visitTime">
								
								<!-- 보호소  방문가능시간 데이터 받아와서 option값 정하기 -->
							</select>
						</td>
					</tr>
				</table>
				<div class="common-tbl-btn-group">
					<button type="submit" class="btn-style1">등록</button><button type="reset" class="btn-style2">취소</button>
				</div>
			</form>
		</div>
	</div>
</section>	
	
	
<!-- Footer -->
<jsp:include page="/WEB-INF/common/footer.jsp" />