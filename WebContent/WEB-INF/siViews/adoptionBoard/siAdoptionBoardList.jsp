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
			<h2 class="comm-content-tit">입양후기</h2>
			<div class="common-tbl-box">
				<table class="comm-tbl type2">
					<colgroup>
						<col width="5%">
						<col width="">
						<col width="15%">
						<col width="10%">
						<col width="7%">
					</colgroup>
					<thead>
						<tr>
							<th>No.</th>
							<th>게시글 제목</th>
							<th>작성자</th>
							<th>날짜/시간</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
			 			<c:forEach items="${ap.list }" var="list">
							<c:if test="${list.adoptionBoardType == 2 }">
								<tr>
									<td>${list.adoptionBoardRnum}</td>
									<td><a href="/siAdoptionBoardView?adoptionBoardNo=${list.adoptionBoardNo }">${list.adoptionBoardTitle }</a></td>
									<!-- name 값을 넘겨주도록 설정필요 -->
									<td>${list.adoptionBoardName }(${list.adoptionBoardId })</td>
									<td>${list.adoptionBoardDate }</td>
									<td>${list.adoptionBoardCount }</td>
								</tr>
							</c:if>
						</c:forEach>
						<tr>
							<td colspan="5" style="text-align:center;">
								<div>${ap.pageNavi }</div>
							</td>
						</tr>
					</tbody>
				</table>
				<form action="/siAdoptionBoardSearch" method="post">
					<div class="board-search-box">
						<select name="searchWord">
							<option value="adoptionBoardName">작성자</option>
							<option value="adoptionBoardTitle">글 제목</option>
						</select>
						<input type="text" name="keyword">
						<button type="submit" class="bbs-search-btn">검색</button>
						<c:if test="${not empty sessionScope.member.id }">
						<!-- 로그인이 되있어야 글쓰기버튼 활성화 -->
							<button type="button" class="bbs-search-btn" style="float:right;" onclick="location.href='/siViews/adoptionBoard/siAdoptionBoardInsert.jsp'">글쓰기</button>
						</c:if>
					</div>
				</form>
			</div>
		</div>
	</section>
</body>
<script>
	Date.prototype.format = function(f) {
		if (!this.valueOf()) return " ";
		var weekKorName = [ "일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일" ];
		var weekKorShortName = [ "일", "월", "화", "수", "목", "금", "토" ];
		var weekEngName = [ "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday","Friday", "Saturday" ];
		var weekEngShortName = [ "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" ];
		var d = this;
		return f.replace(/(yyyy|yy|MM|dd|KS|KL|ES|EL|HH|hh|mm|ss|a\/p)/gi,
		function($1) {
			switch ($1) {
			case "yyyy":
			   return d.getFullYear(); // 년 (4자리)
			case "yy":
			   return (d.getFullYear() % 1000).zf(2); // 년 (2자리)
			case "MM":
			   return (d.getMonth() + 1).zf(2); // 월 (2자리)
			case "dd":
			   return d.getDate().zf(2); // 일 (2자리)
			case "KS":
			   return weekKorShortName[d.getDay()]; // 요일 (짧은 한글)
			case "KL":
			   return weekKorName[d.getDay()]; // 요일 (긴 한글)
			case "ES":
			   return weekEngShortName[d.getDay()]; // 요일 (짧은 영어)
			case "EL":
			   return weekEngName[d.getDay()]; // 요일 (긴 영어)
			case "HH":
			   return d.getHours().zf(2); // 시간 (24시간 기준, 2자리)
			case "hh":
			   return ((h = d.getHours() % 12) ? h : 12).zf(2); // 시간
			                                          // (12시간
			                                          // 기준,
			                                          // 2자리)
			case "mm":
			   return d.getMinutes().zf(2); // 분 (2자리)
			case "ss":
			   return d.getSeconds().zf(2); // 초 (2자리)
			case "a/p":
			   return d.getHours() < 12 ? "오전" : "오후"; // 오전/오후 구분
			default:
			   return $1;
			}
		});
	};
	String.prototype.string = function(len) {
	   var s = '', i = 0;
	   while (i++ < len) {
	      s += this;
	   }
	   return s;
	};
	String.prototype.zf = function(len) {
	   return "0".string(len - this.length) + this;
	};
	Number.prototype.zf = function(len) {
	   return this.toString().zf(len);
	};
</script>
<jsp:include page="/WEB-INF/common/footer.jsp" />
</html>