/* *******************************************************
 * filename : main.js
 * description : main 관련 JS
 * date : 2019-05-23
******************************************************** */

$(document).ready(function(){

	/* *********************** 메인 입양 유기견 리스트  ************************ */
	$('.main-adopt-list').slick({
		slidesToShow:4,
		slideToScroll:1,
		arrows:true,
		fade:false,
		dots:false,
		autoplay:true,
		spped:2000,
		infinite:true,
		autoplaySpeed:3000,
		pauseOnHover:true
	});
	
	/* *********************** 메인 후원 상품 리스트  ************************ */
	$('.main-sponse-list').slick({
		slideToShow:1,
		slideToScroll:1,
		arrows:false,
		dots:true,
		fade:false,
		autoplay:true,
		autoplay:true,
		spped:2000,
		infinite:true,
		autoplaySpeed:3000,
		pauseOnHover:true
	});
	
	/* *********************** 메인 공지사항&봉사활동 탭  ************************ */
	$(".tab-container").each(function  () {
		var $cmTabList = $(this).children(".tab-list");
		var $cmTabListli = $cmTabList.find("li");
		var $cmConWrapper = $(this).children(".tab-content-wrapper");
		var $cmContent = $cmConWrapper.children(".tab-con");
		
		
		// 탭 영역 숨기고 selected 클래스가 있는 영역만 보이게
		var $selectCon = $cmTabList.find("li.selected").find("a").attr("href");
		$cmContent.hide();
		$($selectCon).show();

		$cmTabListli.children("a").click(function  () {
			if ( !$(this).parent().hasClass("selected")) {
				var visibleCon = $(this).attr("href");
				$cmTabListli.removeClass("selected");
				$(this).parent("li").addClass("selected");
				$cmContent.hide();
				$(visibleCon).fadeIn();
			}
			return false;
		});
	});

});