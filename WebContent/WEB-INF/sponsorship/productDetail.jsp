<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="/WEB-INF/common/header.jsp" />
<link rel="stylesheet" type="text/css" href="/css/style.css">
<script type="text/javascript" src="/js/script.js"></script>

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<div class="product clear-float">
			<div class="product-top clearfix">
				<div class="product-img">
					<img src="/img/${prd.prdImg }">
				</div>
				<div class="product-info">
					<form action="/order" method="post" onsubmit="return check();">
						<input type="hidden" name="chkPrice" value="${prd.prdPrice}">
						<input type="hidden" name="prdCode" value="${prd.prdCode}">
						<div class="info-inner">
							<h2 class="title">${prd.prdName }</h2>
							<p class="detail">상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.
								상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.
								상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.상품설명입니다.
							</p>
							<p class="price">최소 후원금액 : <b><span id="limitPrice"><fmt:formatNumber value="${prd.prdPrice}" pattern="#,###" /></span> 원</b></p>
							<p class="option">수량<input type="text" name="amount" class="num" value="1"> 개</p>
							<p class="option">금액<input type="text" name="price" class="num"> 원</p>
							<p class="price type2"><span>총 수량  <b><span id="realAmount">0</span> 개</b></span> / &nbsp;&nbsp;<span>총 금액 <b><span id="realPrice">0</span> 원</b></span></p>
							<p class="order-btn-box"><button type="submit" class="order-btn">후원하기</button></p>
						</div>
					</form>
				</div>	
			</div>
			
			<div class="tab clearfix">
				<a href="#detail">상세설명</a>
				<a href="#qna">Q&A</a>
			</div>
			<div id="detail" class="product-bottom">
				<p class="main-comm-tit">상세설명</p>
				<p class="se_textarea"><!-- SE3-TEXT { --><span>&lt;포인핸드&nbsp;라이프백&gt; 에는&nbsp;<br></span><span>사람과&nbsp;동물이&nbsp;손을&nbsp;맞잡은&nbsp;<br></span><span><b>포인핸드의&nbsp;의미</b></span><span>가&nbsp;담겨있습니다.<br></span><span></span><br><span>포인핸드는&nbsp;굿즈를&nbsp;통해&nbsp;</span><span>일상&nbsp;속에서&nbsp;<br></span><span><b>사지않고&nbsp;입양하는&nbsp;문화</b></span><span>를&nbsp;알리고,<br></span><span>수익금으로&nbsp;유기동물&nbsp;입양을&nbsp;<br></span><span>위한&nbsp;환경을&nbsp;만들어가고&nbsp;있습니다.</span><br><br><span>따듯한&nbsp;봄, 포인핸드&nbsp;라이프백과&nbsp;함께하세요.<br></span><span><br></span><span></span><!-- } SE3-TEXT --></p>
				<img id="SEDOC-1553589953132-1560122654_image_0_img" class="se_mediaImage __se_img_el" src="https://shop-phinf.pstatic.net/20190326_275/101057211_1553589820181rJceV_JPEG/1.jpg" width="60%" data-attachment-id="IEEltJT3O__mVZQFSTvL8gtVBkWw" alt="">
				<p class="se_textarea"><!-- SE3-TEXT { --><strong style=" color: rgb(0, 0, 0)">포인핸드 시그니처 라이프 백</strong><br><strong style=" color: rgb(0, 0, 0)">[품질경영 및 공산품안전관리법에 의한 표시]</strong><br><span style="color: rgb(0, 0, 0);">1. 소재 :&nbsp;면(생지) 100% / 10수2합&nbsp;</span><br><span style="color: rgb(0, 0, 0);">2. 수입자명 : 포인핸드</span><br><span style="color: rgb(0, 0, 0);">3. 제조국명 : 대한민국</span><br><span style="color: rgb(0, 0, 0);">4. 제조연월 : 2018년 4월</span><br><span style="color: rgb(0, 0, 0);">5. 치수 : ONE SIZE (실측 사이즈 참고)</span><br><span style="color: rgb(0, 0, 0);">6. 취급상 주의사항&nbsp;</span><br><span style="color: rgb(0, 0, 0);">- 표준 물세탁 권장</span><!-- } SE3-TEXT --></p>
			</div>
			<div id="qna" class="product-bottom">
				<p class="main-comm-tit">Q&A</p>
				<table class="comm-tbl type2">
					<colgroup>
						<col width="5%">
						<col width="10%">
						<col width="">
						<col width="15%">
						<col width="15%">
					</colgroup>
					<thead>
						<tr>
							<th>No.</th>
							<th>답변상태</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty qnaList }">
							<tr>
								<td colspan="5">
									<p class="none">해당 상품에 대해 등록된 게시물이 없습니다.</p>
								</td>
							</tr>
						</c:if>
						<c:forEach items="${qnaList}" var="qna">
							<tr>
							<td>${qna.boardRnum }</td>
							<td>
								<c:if test="${qna.boardCount eq 0 }">
									<span class="volun-status ing">답변대기</span>
								</c:if>
								<c:if test="${qna.boardCount eq 1 }">
									<span class="volun-status end">답변완료</span>
								</c:if>
							</td>
							<td>
								<p class="volun-tit">
									<a href="/qnaView?boardNo=${qna.boardNo }">
										${qna.boardTitle }
										<c:if test="${qna.boardSecret eq 1 }"><img src="/img/lock.png"></c:if>
										<c:set var="today"><fmt:formatDate value="<%=new java.util.Date()%>" pattern="yyyy-MM-dd" /></c:set>
										<c:if test="${qna.boardDate eq today }"><img src="/img/new.png"></c:if>
									</a>
								</p>
							</td>
							<td>${fn:substring(qna.boardName,0,2) }*</td>	
							<td>${qna.boardDate }</td>						
						</tr>
						</c:forEach>
					</tbody>
				</table>
				<p class="common-tbl-btn-group right">
					<button class="btn-style2" onclick="location.href='/qnaList'">더보기</button><button class="btn-style2" onclick="location.href='/regiQna?prdCode=${prd.prdCode}'">작성하기</button>
				</p>
			</div>
		</div>
	</div>
</section>
<jsp:include page="/WEB-INF/common/footer.jsp" />