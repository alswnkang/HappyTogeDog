<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />

<%-- Content --%>
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">전국 보호소 찾기</h2>
		<div id="findShelterBox" class="clearfix">
			<div class="find-shelter-left-box">
				<table class="comm-tbl type2"><!-- 보호소 리스트는 최대 7개씩 노출됩니다. -->
					<thead>
						<tr>
							<th>보호소명</th>
							<th>주소</th>
							<th>연락처</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>보호소명이 들어갑니다.</td>
							<td>보호소주소가 들어갑니다.</td>
							<td>보호소연락처가 들어갑니다.</td>
						</tr>
						<tr>
							<td>보호소명이 들어갑니다.</td>
							<td>보호소주소가 들어갑니다.</td>
							<td>보호소연락처가 들어갑니다.</td>
						</tr>
						<tr>
							<td>보호소명이 들어갑니다.</td>
							<td>보호소주소가 들어갑니다.</td>
							<td>보호소연락처가 들어갑니다.</td>
						</tr>
						<tr>
							<td>보호소명이 들어갑니다.</td>
							<td>보호소주소가 들어갑니다.</td>
							<td>보호소연락처가 들어갑니다.</td>
						</tr>
						<tr>
							<td>보호소명이 들어갑니다.</td>
							<td>보호소주소가 들어갑니다.</td>
							<td>보호소연락처가 들어갑니다.</td>
						</tr>
						<tr>
							<td>보호소명이 들어갑니다.</td>
							<td>보호소주소가 들어갑니다.</td>
							<td>보호소연락처가 들어갑니다.</td>
						</tr>
						<tr>
							<td>보호소명이 들어갑니다.</td>
							<td>보호소주소가 들어갑니다.</td>
							<td>보호소연락처가 들어갑니다.</td>
						</tr>
					</tbody>
				</table>
				<!-- paging -->
				<div class="paging">
		 			<a href="" class="paging-arrow prev-arrow"><img src="/img/left_arrow.png" style="width:30px;height:30px;"></a>
		 			<a href="" class="cur">1</a>
		 			<a href="">2</a>  
		 			<a href="">3</a>
		 			<a href="">4</a>
		 			<a href="">5</a>
		 			<a href="" class="paging-arrow next-arrrow"><img src="/img/right_arrow.png" style="width:30px;height:30px;"></a>
		 		</div>
		 		<!-- 검색박스 -->
		 		<div class="board-search-box">
		 			<form action="" method="get">
						<select name="type"><!-- option 세부항목은 각자 알아서 넣으시면 됩니다. -->
							<option value="name">보호소명</option>
						</select>
						<input placeholder="검색어를 입력해주세요." type="search" name="keyword" class="search-word">
						<button type="submit" class="bbs-search-btn" title="검색"><img src="/img/search_icon.png" style="width:30px;"></button>
					</form>
				</div>
			</div>
			<div class="find-shelter-right-box">
				<div class="map">
					<a href="javascript:;" class="loc01" id="loc01"><p class="loc-name">인천<span id="locCnt01">(1)</span></p></a>
					<a href="javascript:;" class="loc02" id="loc02"><p class="loc-name">서울<span id="locCnt02">(8)</span></p></a>
					<a href="javascript:;" class="loc03" id="loc03"><p class="loc-name">경기도<span id="locCnt03">(14)</span></p></a>
					<a href="javascript:;" class="loc04" id="loc04"><p class="loc-name">강원도<span id="locCnt04">(10)</span></p></a>
					<a href="javascript:;" class="loc05" id="loc05"><p class="loc-name">충청남도<span id="locCnt05">(8)</span></p></a>
					<a href="javascript:;" class="loc06" id="loc06"><p class="loc-name">대전<span id="locCnt06">(2)</span></p></a>
					<a href="javascript:;" class="loc07" id="loc07"><p class="loc-name">충청북도<span id="locCnt07">(3)</span></p></a>
					<a href="javascript:;" class="loc08" id="loc08"><p class="loc-name">경상북도<span id="locCnt08">(5)</span></p></a>
					<a href="javascript:;" class="loc09" id="loc09"><p class="loc-name">전라북도<span id="locCnt09">(5)</span></p></a>
					<a href="javascript:;" class="loc10" id="loc10"><p class="loc-name">광주<span id="locCnt10">(2)</span></p></a>
					<a href="javascript:;" class="loc11" id="loc11"><p class="loc-name">전라남도<span id="locCnt11">(7)</span></p></a>
					<a href="javascript:;" class="loc12" id="loc12"><p class="loc-name">경상남도<span id="locCnt12">(6)</span></p></a>
					<a href="javascript:;" class="loc13" id="loc13"><p class="loc-name">제주도<span id="locCnt13">(3)</span></p></a>
					<a href="javascript:;" class="loc14" id="loc14"><p class="loc-name">부산<span id="locCnt14">(4)</span></p></a>
					<a href="javascript:;" class="loc15" id="loc15"><p class="loc-name">대구<span id="locCnt15">(2)</span></p></a>
					<a href="javascript:;" class="loc16" id="loc16"><p class="loc-name">세종시<span id="locCnt16">(2)</span></p></a>															
				</div>
			</div>
		</div>
	</div>
</section>

<script type="text/javascript">
$(function(){
	$(".map > a").click(function(){
		if($(this).hasClass("on") === true) {
			$(".map > a").removeClass("on");
		}else{
			$(".map > a").removeClass("on");
			$(this).addClass("on");
		}
	});

});
</script>	

<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />