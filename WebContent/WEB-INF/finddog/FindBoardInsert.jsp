<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/common/header.jsp" />


	<section name="siSection" id="content-wrapper">
		<div class="area">
			<h2 class="comm-content-tit">게시글 작성</h2>
			<div class="common-tbl-box">
				<form action="/findBoardEnroll" method="post" enctype="multipart/form-data"><!-- 파일 첨부를 하기위해 enctype 설정 -->
					<input type="hidden" name="boardType" value="4"/>
					<!-- 보호게시판 구분번호인 1을 submit -->
					<input type="hidden" name="memberId" value="${sessionScope.member.id }"/>
					<!-- 등록한 사람의 아이디 정보를 전달 -->
					<input type="hidden" name="memberName" value="${sessionScope.member.name }"/>
					<table class="comm-tbl">
						<colgroup>
							<col width="28%">
							<col width="/">
						</colgroup>
						<tr>
							<th>품종</th>
							<th>
								<select name="kind"><!-- option 세부항목은 각자 알아서 넣으시면 됩니다. -->
									<option value="content">품종</option>
									<c:forEach items="${kind }" var="k">
										<option value="${k.code }">${k.kind }</option>
									</c:forEach>
								</select>
							</th>
						</tr>
						<tr>
							<th>발견지역</th>
							<th>
								<select name="happenCity">
									<option>도시</option>
									<c:forEach items="${city }" var="c">
										<option value="${c.cityCode }">${c.cityName }</option>
									</c:forEach>
								</select>
							</th>
						</tr>
						<tr>
							<th>발견시간</th>
							<th>
								<select name="shappenDateY" style="width:100px;">
									<option>년</option>
									<c:forEach items="${yList }" var="y"  varStatus="i">
										<option value="${y }">${y }</option>
									</c:forEach>
								</select>년
								<select name="shappenDateM" style="width:50px;">
									<option>월</option>
									<c:forEach items="${mList }" var="y"  varStatus="i">
										<option value="${y }">${y }</option>
									</c:forEach>
								</select>월
								<select name="shappenDateD" style="width:50px;">
									<option>일</option>
									<c:forEach items="${dList }" var="y"  varStatus="i">
										<option value="${y }">${y }</option>
									</c:forEach>
								</select>일
							</th>
							
						</tr>
						
						<tr>
							<th>제목</th>
							<td><input type="text" name="boardTitle"/></td>
						</tr>
						<tr>
							<th>사진</th>
							<td><input type="file" name="boardFilename"/></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea type="text" name="boardContent"></textarea></td>
						</tr>
					</table>
					<div class="common-tbl-btn-group">
						<button type="submit" class="btn-style1">등록하기</button>
						<button type="reset" class="btn-style2" onclick="location.href='/takeBoard'">취소</button>
					</div>
				</form>
			</div>
		</div>
	</section>

<jsp:include page="/WEB-INF/common/footer.jsp" />