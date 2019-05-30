<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">Q & A</h2>
		<div class="qna-view">
			<form action="/checkPw" method="post" onsubmit="return check(this.form);">
				<input type="hidden" name="boardNo" value="${boardNo }">
				<table class="comm-tbl">
					<tr>
						<td>비밀번호</td>
						<td>
							<input type="text" name="boardPw">
						</td>
					</tr>
				</table>
				<div class="common-tbl-btn-group">
					<button type="submit" class="btn-style1">확인</button>
				</div>
			</form>
		</div>
		
	</div>
</section>

<script>
	function check(){
		console.log(f);
		return false;
	}
</script>
<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />