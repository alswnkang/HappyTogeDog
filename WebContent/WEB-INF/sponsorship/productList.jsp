<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/common/header.jsp" />
<link rel="stylesheet" type="text/css" href="/css/style.css">

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<div class="product-list clear-float">
			<div class="product" onclick="location.href='/viewProduct?code=1'">
				<img src="/img/76896814691427225_1127979769.jpg">
				<div class="info">
					<p>상품명</p>
					<p>최소 후원금액 20,000원</p>
					<p>상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.</p>
				</div>
			</div>
			<div class="product" onclick="location.href='/viewProduct?code=2'">
				<img src="/img/39066105050978558_-1615663619.jpg">
				<div class="info">
					<p>상품명</p>
					<p>최소 후원금액 10,000원</p>
					<p>상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.</p>
				</div>
			</div>
		</div>
	</div>
</section>
<jsp:include page="/WEB-INF/common/footer.jsp" />