<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member"%>
    <%
    	Member m = (Member)session.getAttribute("member");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HappyTogeDog</title>
<link rel="Shortcut Icon" href="/img/favicon.ico">
<!-- css -->
<link rel="stylesheet" type="text/css" href="/css/reset.css"> <!-- 태그 초기화 css(민주) -->
<link rel="stylesheet" type="text/css" href="/css/layout.css"> <!-- 레이아웃 css(민주) -->
<link rel="stylesheet" type="text/css" href="/css/content.css"> <!-- 컨텐츠 css(민주) -->
<!-- script -->
<script src="http://code.jquery.com/jquery-3.4.0.min.js"></script><!-- jQuery 선언 -->

<!-- Datapicker 달력 -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script type="text/javascript" src="/js/nav.js"></script><!-- nav.js -->
<script type="text/javascript" src="/js/common.js"></script><!-- common.js -->
<script type="text/javascript" src="/js/script.js"></script><!-- script.js -->

<!-- 슬라이드 CSS & Jquery -->
<link rel="stylesheet" type="text/css" href="/css/plugin/slick.css">
<script src="/js/plugin/slick.js"></script>
</head>
<body>
	<header id="header">
		<div id="headerInnerWrap">
			<!-- 헤더 상단 -->
			<div id="headerInner" class="clearfix">
				<!-- 로고 -->
				<h1 class="header-logo"><a href="/">로고</a></h1>
				<!-- 부가메뉴 -->
				<div class="header-util">
					<ul class="header-util-box clearfix">
						<%if(m == null){%>
						<li><a href="/member/login.jsp">Login</a></li>
						<li><a href="/member/selectJoin.jsp">JOIN</a></li>
						<%}else{ %>
						<!-- 로그인 후 -->
						<li><a href="/logout">Logout</a></li>
						<li><a href="/myPage?id=<%= m.getId() %>">MyPage</a></li>
						<%} %>
					</ul>
				</div>
				<!-- 주메뉴 -->
				<nav id="gnb" class="total-menu">
					<div id="gnbBg"></div>
					<ul class="clearfix">
						<li class="gnb01">
							<a href="">입양</a>
							<div class="gnb-2dep">
								<ul>
									<li><a href="">입양하기</a></li>
									<li><a href="">전국 보호소 찾기</a></li>
								</ul>
							</div>
						</li>
						<li class="gnb02">
							<a href="">봉사활동</a>
							<div class="gnb-2dep">
								<ul>
									<li><a href="">봉사활동 신청</a></li>
								</ul>
							</div>
						</li>
						<li class="gnb03">
							<a href="/sponsorship">후원하기</a>
							<div class="gnb-2dep">
								<ul>
									<li><a href="/sponsorship">후원하기</a></li>
									<li><a href="">Q&A</a></li>
								</ul>
							</div>
						</li>
						<li class="gnb04">
							<a href="">실종유기견찾기</a>
							<div class="gnb-2dep">
								<ul>
									<li><a href="">보호중인 유기견</a></li>
									<li><a href="">강아지를 찾습니다</a></li>
								</ul>
							</div>
						</li>
						<li class="gnb05">
							<a href="">커뮤니티</a>
							<div class="gnb-2dep">
								<ul>
									<li><a href="">공지사항</a></li>
									<li><a href="">입양후기</a></li>
									<li><a href="">자유게시판</a></li>
								</ul>
							</div>
						</li>
					</ul>
				</nav>
			</div>
		</div>
	</header>