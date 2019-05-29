<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/common/header.jsp" />
<link rel="stylesheet" type="text/css" href="/css/style.css">

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">후원하기</h2>
		<div class="product-list clearfix">
			<div class="product" onclick="location.href='/viewProduct?code=1'">
				<div class="product-inner">
					<div class="prd-img-thum">
						<span style="background:url(/img/76896814691427225_1127979769.jpg) no-repeat center center; background-size:cover;"></span>
					</div>
					<div class="prd-txt-thum">
						<div class="info">
							<h3>상품명</h3>
							<p class="price">최소 후원금액 <b>20,000원</b></p>
							<p class="txt">상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.</p>
						</div>
					</div>
				</div>
			</div>
			<div class="product" onclick="location.href='/viewProduct?code=1'">
				<div class="product-inner">
					<div class="prd-img-thum">
						<span style="background:url(/img/39066105050978558_-1615663619.jpg) no-repeat center center; background-size:cover;"></span>
					</div>
					<div class="prd-txt-thum">
						<div class="info">
							<h3>상품명</h3>
							<p class="price">최소 후원금액 <b>20,000원</b></p>
							<p class="txt">상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.</p>
						</div>
					</div>
				</div>
			</div>
			<div class="product" onclick="location.href='/viewProduct?code=1'">
				<div class="product-inner">
					<div class="prd-img-thum">
						<span style="background:url(/img/prd_img01.jpg) no-repeat center center; background-size:cover;"></span>
					</div>
					<div class="prd-txt-thum">
						<div class="info">
							<h3>유기견 사진전시회 기념 달력</h3>
							<p class="price">최소 후원금액 <b>20,000원</b></p>
							<p class="txt">상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<jsp:include page="/WEB-INF/common/footer.jsp" />