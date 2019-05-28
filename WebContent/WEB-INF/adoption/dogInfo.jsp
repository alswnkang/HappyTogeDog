<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css" href="/css/adoption_bk.css">
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=5l95i6gfap&submodules=geocoder"></script>
<!-- css -->
<!-- Header -->
<jsp:include page="/WEB-INF/common/header.jsp" />

<!-- 보호소 코드 디비에 저장해야하기 때문에 전 페이지에서 받아와야함-->
<!-- Content -->
<section id="content-wrapper">
	<div class="area">
		<h2 class="comm-content-tit">강아지 입양페이지(?)</h2>
		<div id="dogInfo" class="common-box">
			<table class="comm-tbl">
				<colgroup>
					<col width="20%">
					<col width="30%">
					<col width="20%">
					<col width="30%">
				</colgroup>
				<tr>
					<th>보호소명</th>
					<td>받아오기</td>
					<th>보호소 주소</th>
					<td>받아오기</td>
				</tr>
				<tr>
					<th>보호소 연락처</th>
					<td>받아오기</td>
					<th>보호소 <br>방문가능 시간
					</th>
					<td>받아오기</td>
				</tr>
				<tr>
					<th>품종</th>
					<td>받아오기(kindCd)</td>
					<th>나이</th>
					<td>받아오기(age)</td>
				</tr>
				<tr>
					<th>성별</th>
					<td>받아오기(sexCd)</td>
					<th colspan="2"></th>
				</tr>
				<tr>
					<th>특징</th>
					<td colspan="3">받아오기(specialMark, noticeComment, neuterYn)</td>
				</tr>
			</table>
			<div class="common-tbl-btn-group">
				<button type="button" class="btn-style1"
					onclick="location.href='/reservation'">보호소 방문예약</button>
				<!-- onclick="location.href='/reservation?보호소명,주소,전화번호,코드넘겨주기 -->
			</div>
		</div>
		<div class="view-detail">
			<img src="/img/dog_test_bk.jpg" style="max-width:100%;"><br><br>
			 강아지에 대한 설명을 넣을까 말까?
		</div>
		<div class="care-location" id="map">
			
		</div>
	</div>
</section>

<script>
	window.onload = function(){
		//보호소 주소 받아와서 넣기
		searchAddressToCoordinate('전라남도 무안군 삼향읍 석매길 36');	/* 페이지 로드 되면 메소드 바로 불러오기 */
	
		//보호소 주소 위도,경도로 변환
		function searchAddressToCoordinate(address) {
		    naver.maps.Service.geocode({
		        query: address
		    }, function(status, response) {
		        if (status === naver.maps.Service.Status.ERROR) {
		            return alert('Something Wrong!');
		        }
		        if (response.v2.meta.totalCount === 0) {
		            return alert('totalCount' + response.v2.meta.totalCount);
		        }
		        var htmlAddresses = [],
		            item = response.v2.addresses[0],
		            point = new naver.maps.Point(item.x, item.y);
		        infoWindow.setContent([
		            '<div style="padding:10px;min-width:200px;line-height:50%;">',
		            '<h4 style="margin-top:5px;"> '+ address +'</h4><br />',
		            htmlAddresses.join('<br />'),
		            '</div>'
		        ].join('\n'));
		        map.setCenter(point);		//맵 중심 정하기
		        marker.setPosition(point);	//마커 중심 정하기
		        infoWindow.open(map, marker);	//마커위치위에 주소창 띄우기
		    });
		}
	var map = new naver.maps.Map("map", {
		//searchAddressToCoordinate()에서 marder 중심 정해주기 때문에 center가 따로 필요없음
	    /* center: new naver.maps.LatLng(37.3595316, 127.1052133), */
	    zoom: 10,
	    zoomControl : true,		//줌 컨트롤 가능하게
	    mapTypeControl: true
	});
	//마커 생성
	var marker = new naver.maps.Marker({
		//searchAddressToCoordinate()에서 marder 중심 정해주기 때문에 position이 따로 필요없음
		/* position : new naver.maps.LatLng(37.3595316, 127.1052133), */
		map : map	//map이라는 속성에 var map을 넣어줌
	});
	
	//정보창 생성
	var infoWindow = new naver.maps.InfoWindow({
	    anchorSkew: false	//anchorSkew : 기울임
	});
	map.setCursor('pointer');
	
	
	//위경도->주소로 변환
	/* function searchCoordinateToAddress(latlng) {
	    infoWindow.close();
	    naver.maps.Service.reverseGeocode({
	        coords: latlng,
	        orders: [
	            naver.maps.Service.OrderType.ADDR,
	            naver.maps.Service.OrderType.ROAD_ADDR
	        ].join(',')
	    }, function(status, response) {
	        if (status === naver.maps.Service.Status.ERROR) {
	            return alert('Something Wrong!');
	        }
	
	        var items = response.v2.results,
	            address = '',
	            htmlAddresses = [];
	
	        for (var i=0, ii=items.length, item, addrType; i<ii; i++) {
	            item = items[i];
	            address = makeAddress(item) || '';
	            addrType = item.name === 'roadaddr' ? '[도로명 주소]' : '[지번 주소]';
	
	            htmlAddresses.push((i+1) +'. '+ addrType +' '+ address);
	        }
	
	        infoWindow.setContent([
	            '<div style="padding:10px;min-width:200px;line-height:150%;">',
	            '<h4 style="margin-top:5px;">검색 좌표</h4><br />',
	            htmlAddresses.join('<br />'),
	            '</div>'
	        ].join('\n'));
	
	        infoWindow.open(map, latlng);
	    });
	} */


	//검색을 위한 function
	/* function initGeocoder() {
	    map.addListener('click', function(e) {
	        searchCoordinateToAddress(e.coord);
	    });
	
	    $('#address').on('keydown', function(e) {
	        var keyCode = e.which;
	
	        if (keyCode === 13) { // Enter Key
	            searchAddressToCoordinate($('#address').val());
	        }
	    });
	
	    $('#submit').on('click', function(e) {
	        e.preventDefault();
	
	        searchAddressToCoordinate($('#address').val());
	    });
	
	    searchAddressToCoordinate('정자동 178-1');
	} */
	
	/* function makeAddress(item) {
	    if (!item) {
	        return;
	    }
	    var name = item.name,
	        region = item.region,
	        land = item.land,
	        isRoadAddress = name === 'roadaddr';
	    var sido = '', sigugun = '', dongmyun = '', ri = '', rest = '';
	    if (hasArea(region.area1)) {
	        sido = region.area1.name;
	    }
	    if (hasArea(region.area2)) {
	        sigugun = region.area2.name;
	    }
	    if (hasArea(region.area3)) {
	        dongmyun = region.area3.name;
	    }
	    if (hasArea(region.area4)) {
	        ri = region.area4.name;
	    }
	    if (land) {
	        if (hasData(land.number1)) {
	            if (hasData(land.type) && land.type === '2') {
	                rest += '산';
	            }
	            rest += land.number1;
	            if (hasData(land.number2)) {
	                rest += ('-' + land.number2);
	            }
	        }
	
	        if (isRoadAddress === true) {
	            if (checkLastString(dongmyun, '면')) {
	                ri = land.name;
	            } else {
	                dongmyun = land.name;
	                ri = '';
	            }
	            if (hasAddition(land.addition0)) {
	                rest += ' ' + land.addition0.value;
	            }
	        }
	    }
	
	    return [sido, sigugun, dongmyun, ri, rest].join(' ');
	} */
	
	/* function hasArea(area) {
	    return !!(area && area.name && area.name !== '');
	}
	
	function hasData(data) {
	    return !!(data && data !== '');
	}
	
	function checkLastString (word, lastString) {
	    return new RegExp(lastString + '$').test(word);
	}
	
	function hasAddition (addition) {
	    return !!(addition && addition.value);
	} */

	/* naver.maps.onJSContentLoaded = initGeocoder; */
}
</script>

<!-- Footer -->
<jsp:include page="/WEB-INF/common/footer.jsp" />