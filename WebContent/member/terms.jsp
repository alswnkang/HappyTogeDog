<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<style>
		.main{
		/*	border : 1px solid red;		*/ 
			height : 1100px;
			background-color: #FFFFFF;
			padding-left: 100px;
			padding-right: 100px;
		}
		.main2{
			border : 1px solid #fe431e;			 	
			height : 250px;
			overflow : auto;
			overflow-x:hidden;
			text-align: left;
			padding: 20px;
			line-height: 25px;
		}
		#mid{
		/*	border : 1px solid blue;	*/
			height : 50px;
			text-align: center;
			line-height: 50px;
		}
		.t_mid:hover{
			cursor: pointer;
		}
		
		.t_mid{
		/*	border : 1px solid yellow;	*/
			height : 100%;
			width : 30%;
			float: left;
			box-sizing: border-box;
			
		}
		.c_mid{
		/*	border : 1px solid green;	*/
			height : 100%;
			width : 100%;
			overflow : auto;
			overflow-x:hidden;
			text-align: left;
			line-height: 25px;
			position: absolute;
			box-sizing: border-box;
			display: block;
			padding: 20px;
		}
		#mid2{
			border : 1px solid #fe431e;		
			height : 250px;
			clear: left;
			position: relative;
		}
		ul{
			padding-bottom: 10px;
		}
		h1{
			font-size: 30px;
			padding-top: 20px;
			padding-bottom: 10px;
		}
	</style>
</head>
<body>
<jsp:include page="/WEB-INF/common/header.jsp" />
	<section id="content-wrapper">
		<div class="area">
			<h1 class="comm-content-tit">이용약관</h1>
			<form action="/member/selectJoin.jsp" method="post" onsubmit="return check()">
			<div class="common-tbl-box">
				<div class="main">
					<h1>사이트 이용약관</h1>
					<div class="main2" style="font-size:120%">
						<h3>[제 1장 총칙]</h3>
						<h4>제 1조 목적</h4>
						<p>이 약관은 HappytogeDog, Inc. (이하 회사 또는 HappytogeDog)이 제공하는 모든 서비스의 이용 조건 및 절차에 관한 사항과 기타 필요한 사항을 규정함을 목적으로 합니다.</p>
						<h4>제 2조 효력과 변경</h4>
						<ul class="blNum">
		                    <li>(1) 이 약관은 이용자에게 서비스 화면을 통해 공지하거나 기타의 방법으로 회원에게 공지함으로써 효력을 발생합니다.</li>
		                    <li>(2) 회사는 영업상 필요 또는 기타 사정 변경이 있을 때 약관을 변경할 수 있으며, 변경 일주일 이전에 그 내용을 서비스 제공자의 웹사이트의 "서비스"관련 페이지에 공지합니다. 이용자는 변경된 약관에 동의하지 않을 권리가 있으며, 변경된 약관에 동의하지 않을 경우 서비스 이용이 중단될 수 있습니다. 다만, 이용자가 위 기간 동안 서비스 제공자에 대하여 거부의 의사표시를 하지 않는 경우 이용자는 변경된 약관에 동의한 것으로 간주됩니다.</li>
		                    <li>(3) 새로운 서비스가 개설될 경우 별도의 명시된 설명이 없는 한, 이 약관에 따라 제공합니다.</li>
		                </ul>
		                <h4>제 3조 약관 이외의 준칙</h4>
		                <p>이 약관에 명시되지 않은 사항이 관계 법령에 규정되어 있을 경우에는 그 규정에 따릅니다.</p>
		                <h3>[제 2장 회원 가입과 서비스 이용]</h3>
		                <h4>제 4조 정의</h4>
		                <p>이 약관에서 사용하는 용어의 정의는 다음과 같습니다.</p>
		                <ul class="blNum">
                    <li>(1) 회원 : 회사와 서비스 이용계약을 체결한 개인이나 법인 또는 법인에 준하는 단체
                        <ul class="blNum">
                            <li>가.  유료회원 : 회사가 인정하는 제품 및 서비스를 구입한 고객이 온라인으로 고객등록을 신청하고 이에 대하여 회사가 등록 작업을 함으로써 자격 취득</li>
                            <li>나.  무료회원 : 이용자의 이용 신청 및 약관 내용에 대한 동의와 이에 대한 회사의 이용 승낙으로 자격 취득</li>
                        </ul>
                    </li>
                    <li>(2) 서비스 이용 : 안랩닷컴에 로그인하여 이 약관에 따라 서비스를 제공 받거나 제품 및 서비스를 구매하여 사용함</li>
                    <li>(3) 운영자 : 서비스의 전반적인 관리와 원활한 운영을 위하여 회사가 선정한 사람</li>
                    <li>(4) 아이디(ID) : 회원식별을 위하여 회원이 선정하고 회사가 승인하는 문자, 숫자의 조합</li>
                    <li>(5) 비밀번호 : 회원의 비밀 보호를 위해 회원 자신이 설정한 문자와 숫자, 특수문자의 조합</li>
                    <li>(6) 서비스 일시 정지 : 정상이용 중 회사가 정한 일정한 요건에 따라 일정 기간 동안 서비스의 제공을 중지하는 것</li>
                    <li>(7) 해지 : 회사 또는 회원이 서비스 개통 후 이용 계약을 해약하는 것</li>
                </ul>
                <h4>제 5조 서비스 회원 가입의 성립</h4>
                <ul class="blNum">
                    <li>(1) 회원은 유료회원과 무료회원으로 나누어 등록, 관리합니다.</li>
                    <li>(2) 고객 및 이용자의 회원 가입 신청에 대하여 회사가 이를 승낙한 경우, 회사는 회원 ID와 기타 회사가 필요하다고 인정하는 내용을 이용자에게 통지합니다.</li>
                    <li>(3) 회사는 다음 각 호에 해당하는 회원 가입 신청에 대하여는 이를 승낙하지 아니 합니다.
                        <ul>
                            <li>가.  다른 사람의 명의를 사용하여 신청하였을 때</li>
                            <li>나.  본인의 실명으로 신청하지 않았을 때</li>
                            <li>다.  회원 가입 신청 시 이용자 정보를 허위로 기재하였을 때</li>
                            <li>라.  사회의 안녕과 질서 혹은 미풍양속을 저해할 목적으로 신청하였을 때</li>
                            <li>마.  기타 회사가 정한 이용신청요건이 미비할 때</li>
                        </ul>
                    </li>
                </ul>
                <h4>제 6조 서비스의 구분</h4>
                <ul class="blNum">
                    <li>(1) 회사가 회원에게 제공하는 서비스는 무료서비스와 유료서비스 등으로 구분합니다.</li>
                    <li>(2) 서비스의 종류와 내용 등은 회사가 공지나 서비스 이용 안내에서 별도로 정하는 바에 의합니다.</li>
                </ul>
                <h4>제 7조 서비스 이용 및 제한</h4>
                <ul class="blNum">
                    <li>(1) 회사가 제공하는 서비스는 회원의 자격에 따라 차등 제공되며, 무료회원도 유료회원의 자격을 취득하면 유료회원으로서 모든 서비스를 제공받을 수 있습니다.</li>
                    <li>(2) 서비스 이용 시간은 회사의 업무상 또는 기술상 특별한 지장이 없는 한 연중무휴, 1일 24시간을 원칙으로 합니다.</li>
                    <li>(3) 전항의 서비스 이용시간은 시스템 정기점검 등 회사가 필요에 의하여 정한 날 또는 시간의 경우 회원에게 사전 통지한 후, 제한할 수 있습니다.</li>
                </ul>
                <h4>제 8조 이용자 정보의 변경</h4>
                <p>회원은 회원 가입 신청시 기재한 정보가 변경되었을 경우에는, 즉시 온라인으로 수정을 하여야 하며 이용 신청시 기재한 사항 중 변경 사항을 수정하지 않아 발생되는 문제의 책임은 회원에게 있습니다.</p>
                <h3>[제 3장 의무]</h3>
                <h4>제 9조 회사의 의무</h4>
                <ul class="blNum">
                    <li>(1) 회사는 특별한 사정이 없는 한 회원이 신청한 서비스 제공 개시일에 서비스를 이용할 수 있도록 합니다.</li>
                    <li>(2) 회사는 이 약관에서 정한 바에 따라 계속적, 안정적으로 서비스를 제공할 의무가 있습니다.</li>
                    <li>(3) 회사는 회원으로부터 제기되는 의견에 대해서는 적절한 절차를 거처 처리하며, 일정 기간 내에 처리가 불가한 경우 회원에게 그 사유와 처리 일정을 알려주어야 합니다.</li>
                    <li>(4) 회사는 회원의 정보에 대하여 철저한 보안을 유지하며, 양질의 서비스를 운영하거나 개선하기 위한 목적으로만 사용하고, 이외의 다른 목적으로 타 기관 및 개인에게 양도하지 않습니다.</li>
                </ul>
                <h4>제 10조 회원의 의무</h4>
                <ul class="blNum">
                    <li>(1) 가입신청 양식에는 실명을 사용해야 합니다.</li>
                    <li>(2) 다른 사람의 명의를 사용하여 기재하지 않아야 합니다.</li>
                    <li>(3) 가입신청 양식의 내용은 현재의 사실과 일치해야 합니다.</li>
                    <li>(4) ID와 비밀번호에 관한 모든 관리의 책임은 회원에게 있습니다.</li>
                    <li>(5) 자신의 ID가 부정하게 사용된 경우, 회원은 반드시 회사에 그 사실을 통보해야 합니다.</li>
                    <li>(6) 다른 회원의 ID를 부정하게 사용하지 않아야 합니다.</li>
                    <li>(7) 서비스에서 얻은 정보를 회사의 사전승낙 없이 복제 및 변경하여 출판 및 방송 등에 사용하거나 타인에게 제공하는 행위를 하지 않아야 합니다.</li>
                    <li>(8) 회사의 저작권, 제 3자의 저작권 등 기타 권리를 침해하는 행위를 하지 않아야 합니다.</li>
                    <li>(9) 회원은 회사의 사전 승낙 없이 서비스를 이용한 영업행위를 할 수 없으며, 이에 따른 결과로 인하여 발생한 결과에 대해서는 회사가 책임지지 않습니다.</li>
                    <li class="num2">(10) 만 14세 미만의 어린이는 부모 등 법정 대리인의 동의를 얻은 후에 서비스 이용을 신청하여야 합니다. 또한 만 20세 미만의 이용자가 유료 서비스를 이용하고자 하는 경우에도 이와 같습니다.</li>
                    <li>(11) 안랩닷컴이 정한 정보 이외의 정보 등의 송신 또는 게시 행위를 하지 않아야 합니다.</li>
                    <li>(12) 회원은 이 약관 및 관계 법령에서 규정한 사항을 준수하여야 합니다.</li>
                </ul>
                <h4>제 11조 양도 금지</h4>
                <p>회원은 서비스의 이용권한, 기타 이용계약상 지위를 타인에게 양도, 증여할 수 없으며, 이를 담보로 제공할 수 없습니다.</p>
                <h3>[제 4장 계약 해지 및 서비스 이용 제한]</h3>
                <h4>제 12조 계약 해지 및 이용 제한</h4>
                <ul class="blNum">
                    <li>(1) 이용계약의 해지는 무료회원의 경우에는 언제나 가능하며, 제품고객으로 등록된 유료회원의 경우에는 자동 해지 처리 시 등록된 제품정보까지 삭제되어 제품 이용 관련 고객지원 서비스 제공에 문제가 발생할 수 있습니다. 따라서 자동 해지 처리가 불가하며 HappytogeDog의 고객지원센터를 통해 관련 해지 절차를 안내 받아 개별적으로 처리할 수 있습니다. 단, 이럴 경우 유료서비스 고객에게만 제공되는 인터넷상의 다양한 유료 서비스를 이용하실 수 없게 됩니다.</li>
                    <li>(2) 무료회원이 서비스 이용계약을 해지하고자 하는 때에는 회원 본인이 직접 인터넷의 해지서비스를 이용하여 서비스 해지 신청을 해야 하며 비밀번호를 입력하여 본인임을 확인한 후, 해지 확인을 선택하면 자동으로 가입 해지됩니다. 단, 유료회원의 경우에는 고객만족센터를 통한 해지 신청시에만 처리 가능합니다.</li>
                    <li>(3) 유료서비스 신청 후, 서비스 해지 신청에 따른 서비스 환불 정책은 각 유료서비스 별 개별 약관에 따릅니다. 각 서비스 별 개별 약관은 이용하시는 서비스의 신청 시 확인이 가능합니다.</li>
                    <li>(4) 가입 해지 여부는 기존의 ID, 비밀번호로 로그인이 되지 않으면 해지된 것입니다.</li>
                    <li>(5) 회사는 회원의 개인정보 보호를 위하여 서비스 미사용자(최종 로그인한 후 법률에서 정한 기간 동안 이용 기록이 없는 회원)에 대해 정기적으로 회원자격 상실에 대한 안내문(전자우편)을 통지하고, 안내문에서 정한 기한(30일) 내에 이용 기록이 없을 경우 회원자격을 상실시킬 수 있습니다. 이 경우, 회원의 개인정보 및 서비스 사용정보는 이용중인 회원의 개인정보와 분리하여 안전하게 보관하고, 분리보관일로부터 2년 뒤 파기 합니다.</li>
                    <li>(6) 회사는 회원이 다음 사항에 해당하는 행위를 하였을 경우, 사전 통지 없이 이용 계약을 해지하거나 또는 기간을 정하여 서비스 이용을 중지할 수 있습니다.
                        <ul>
                            <li>가.  공공 질서 및 미풍 양속에 반하는 경우</li>
                            <li>나.  범죄적 행위에 관련되는 경우</li>
                            <li>다.  국익 또는 사회적 공익을 저해할 목적으로 서비스 이용을 계획 또는 실행할 경우</li>
                            <li>라.  타인의 ID 및 비밀번호를 도용한 경우</li>
                            <li>마.  타인의 명예를 손상시키거나 불이익을 주는 경우</li>
                            <li>바.  같은 사용자가 다른 ID로 이중 등록을 한 경우</li>
                            <li>사.  사. 서비스에 위해를 가하는 등 건전한 이용을 저해하는 경우</li>
                            <li>아.  회사, 다른 회원 또는 제 3자의 지적재산권을 침해하는 경우</li>
                            <li>자.  회사의 서비스 정보를 이용하여 얻은 정보를 회사의 사전 승낙 없이 복제 또는 유통시키거나 상업적으로 이용하는 경우</li>
                            <li>차.  기타 관련 법령이나 회사가 정한 이용조건에 위배되는 경우</li>
                        </ul>
                    </li>
                </ul>
                <h4>제 13조 서비스 제한 및 해제 절차</h4>
                <ul class="blNum">
                    <li>(1) 회사가 서비스 이용을 제한하고자 하는 경우에는 그 사유, 일시 및 기간을 정하여 서면 또는 그 외의 방법을 이용하여 회원 또는 대리인에게 통지합니다.</li>
                    <li>(2) 회사는 전시, 사변, 천재지변 또는 이에 준하는 국가비상사태가 발생하거나 발생할 우려가 있는 경우 및 기타 불가항력적 사유가 있는 경우에는 전항의 과정 없이 서비스의 전부 또는 일부를 제한하거나 정지할 수 있습니다.</li>
                    <li>(3) (1)항의 규정에 의하여 서비스 이용중지를 통지 받은 회원 또는 그 대리인은 이용 중지에 대하여 이의가 있을 경우 이의신청을 할 수 있습니다.</li>
                    <li>(4) 회사는 이용 중지 기간 중에 그 이용 중지 사유가 해소된 것이 확인된 경우에 한하여 이용 중지 조치를 즉시 해제합니다.</li>
                </ul>
                <h4>제 14조 손해배상</h4>
                <ul class="blNum">
                    <li>(1) 회사가 제공하는 서비스와 관련하여 서비스 요금이 무료인 동안의 서비스 이용과 관련하여 회원에게 발생한 어떠한 손해에 관하여도 책임을 지지 않으며, 그 외의 경우에도 회사의 중대한 과실에 의한 경우를 제외하고 이에 대하여 책임을 부담하지 않습니다.</li>
                    <li>(2) 회사는 제 2장, 제 4조 3항에 따라 유료회원 대상의 서비스에 대하여 사전 공지를 한 경우의 서비스 중지에 관하여도 책임을 지지 않습니다.</li>
                    <li>(3) 회사는 회사의 귀책 사유로 유료 서비스 이용자가 해당 유료 서비스를 이용하지 못하는 경우에는 이용자가 그 사실을 회사에 통보한 시점 또는 회사가 그 사실을 알았거나 알 수 있었을 시점으로부터 4시간 이상 연속적으로 그 상태가 계속될 때에는 회원의 청구에 대하여 손해배상책임이 있습니다.</li>
                    <li>(4) 손해배상은 아래 기준으로 유료서비스 만료일을 연장해 드립니다.
                        <ul>
                            <li>4시간~8시간 연속 서비스 사용 불가 → 1일 연장</li>
                            <li>9시간~16시간 연속 서비스 사용 불가 → 2일 연장</li>
                            <li>17시간~24시간 연속 서비스 사용 불가 → 3일 연장</li>
                        </ul>
                    </li>
                    <li>(5) 회원이 이 약관의 규정을 위반함으로 인하여 회사나 회원 또는 제 3자에 대하여 책임을 부담하게 되고, 이로써 회사에게 손해가 발생하는 경우, 이 약관을 위반한 회원은 회사에게 발생하는 모든 손해를 배상하여야 합니다.</li>
                </ul>
                <h4>제 15조 손해배상의 청구</h4>
                <ul class="blNum">
                    <li>(1) 손해배상의 청구는 회사에 청구사유, 청구금액 및 산출근거를 기재하여 서면으로 하여야 합니다.</li>
                    <li>(2) 제 (1)항의 손해배상 청구는 그 청구사유를 안 날로부터 3개월 내에 행사하지 아니하면 그 청구권은 소멸합니다. 청구사유가 발생한 날로부터 1년이 경과한 때에도 이와 같습니다.</li>
                </ul>
                <h4>제 16조 면책조항</h4>
                <ul class="blNum">
                    <li>(1) 회사는 천재지변 또는 이에 준하는 불가항력으로 인하여 서비스를 제공할 수 없는 경우에는 서비스 제공에 관한 책임이 면제됩니다.</li>
                    <li>(2) 회사는 회원의 귀책사유로 인한 서비스 이용의 장애에 대하여 책임을 지지 않습니다.</li>
                    <li>(3) 회사는 회원이 서비스를 이용하여 기대하는 수익을 상실한 것에 대하여 책임을 지지 않습니다. 또한 회사는 서비스를 통하여 제공하는 자료의 정확성을 보장하지 아니하며 이로 인한 손해에 관하여 책임을 지지 않습니다.</li>
                    <li>(4) 회사는 고의에 의한 경우를 제외하고 회사가 제공하는 자료, 사실 기타 모든 정보의 신뢰도, 정확성에 대하여 책임을 지지 않습니다.</li>
                </ul>
                <h4>제 17조 관할법원</h4>
                <p>서비스 이용에 관하여 발생한 분쟁에 대해 소송이 제기될 경우 회사의 본사 소재지를 관할하는 법원을 관할법원으로 합니다.</p>
                <p class="mb0">(부칙) 이 약관은 2012년 8월 9일부터 시행합니다.</p>
                <p>종전의 약관은 본 약관으로 대체합니다.</p>
                
					</div>
					<p style="padding-top:10px"><input type="checkbox" id="chk1"><label for="chk1">이용약관에 동의합니다.</label></p>
					<br>
					<br>
					<h1>개인정보의 수집 및 이용 안내</h1>
					<div id="mid">
						<div id="t_mid1" class="t_mid" style="background-color:#fe431e; color:#ffffff;">
							<h2>수집하는 개인정보의 항목</h2>
						</div>
						<div id="t_mid2" class="t_mid">
							<h2>개인정보의 수집 및 이용목적</h2>
						</div>
						<div id="t_mid3" class="t_mid">
							<h2>개인정보의 보유 및 이용기간</h2>
						</div>
					</div>
					<div id="mid2">
						<div class="c_mid" id="c_mid1" style="font-size:120%">
							<p>홈페이지의 회원가입 통해 아래와 같은 최소한의 개인정보를 수집하고 있습니다. 상담 및 문의(전화, 팩스, 홈페이지), 설문 및 이벤트 참여, 제휴사의 제공을 통해 아래 정보가 추가 수집될 수 있습니다.</p>
							<ul class="blDot mb15">
                            <li><strong>공통 [필수항목]</strong>&nbsp;이름, 아이디, 비밀번호, 비밀번호 힌트, 비밀번호 답, 이메일</li>
                            <li><strong>개인, 외국인 회원 [필수항목]</strong> 생년월일, 성별, 휴대전화번호</li>
                            <li><strong>기업 회원 [필수항목]</strong> 부서명, 휴대폰 번호</li>
                            <li><strong>해외교포 [필수항목]</strong> 생년월일, 성별, 거주나라</li>
                            </ul>
                            <p>회원이 환불을 할 시 아래와 같은 최소한의 개인정보를 수집하고 있습니다.</p>
                            <ul class="blDot mb15">
                            <li><strong>환불 [필수항목]</strong> 계좌번호, 계좌 은행 명, 계좌 소유자명</li>
                            </ul>
                            <p>서비스 이용 및 사업 처리 과정에서 아래와 같은 정보들이 자동 생성되어 수집될 수 있습니다.</p>
                            <ul class="blDot">
                            <li>IP 주소, 쿠키, 방문 일시, 서비스 이용 기록, 불량 이용 기록</li>
                            </ul>
						</div>
						<div class="c_mid" id="c_mid2" style="display:none; font-size:120%">
							<p>홈페이지의 회원가입과 상담 및 문의를 통해 수집한 회원정보는 아래와 같이 회원관리에 이용됩니다.</p>
							<ul class="blDot mb15">
                            <li>회원제 서비스 이용에 따른 본인확인, 개인 식별, 불량회원의 부정 이용 방지와 비인가 사용 방지, 가입 의사 확인, 연령확인, 만14세 미만 아동 개인정보 수집 시 법정 대리인 동의여부 확인, 불만처리 등 민원 처리, 바이러스 신고, 고지사항 전달</li>
                            </ul>
                            <p>회원의 제품 구매 정보와 환불을 할 시 수집된 정보는 아래와 같이 서비스 제공에 관한 계약 이행 및 서비스 제공에 따른 요금정산에 이용 됩니다.</p>
                            <ul class="blDot mb15">
                            <li>공지사항 전달, 콘텐츠 제공, 불만 처리 등 의사소통 경로 확보, 구매 및 요금 결제, 청구서 등 발송, 금융거래 본인 인증 및 금융 서비스</li>
                            </ul>
                            
						</div>
						<div class="c_mid" id=c_mid3 style="display:none; font-size:120%">
							<p>회사는 개인정보의 수집 및 이용목적이 달성된 후에는 해당 정보를 지체 없이 파기합니다. 단, 전자상거래 등에서의 소비자보호에 관한 법률 등 관계 법령의 규정에 의하여 보존할 필요가 있는 경우 회사는 관계법령에서 정한 일정한 기간 동안 개인정보를 보관합니다.</p>
							<ul class="blDot">
                            <li>전자상거래 등에서의 소비자보호에 관한 법률
                                <ul class="blList">
                                <li>계약 또는 청약철회 등에 관한 기록: 5년</li>
                                <li>대금결제 및 재화 등의 공급에 관한 기록: 5년</li>
                                <li>소비자의 불만 또는 분쟁처리에 관한 기록: 3년</li>
                                </ul>
                            </li>
                            <li>전자금융거래법
                                <ul class="blList">
                                <li>전자금융 거래에 관한 기록: 5년</li>
                                </ul>
                            </li>
                            </ul>
                            
						</div>
					</div>
					<p style="padding-top:10px"><input type="checkbox" id="chk2"><label for="chk2">개인정보 수집 및 이용에 동의합니다.<br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;개인정보 수집 및 이용 동의에 거부할 수 있습니다. 단 동의 거부 시 회원가입이 제한됩니다.</label></p>
					<br><br>
					<h1>개인정보의 수집 및 이용 안내</h1>
					<table class="comm-tbl type2">
						<thead>
							<tr>
								<th colspan="2">구분</th>
								<th>항목</th>
								<th>목적</th>
								<th>보유기간</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td rowspan="2">가입</td>
								<td>개인,외국인</td>
								<td>일반전화번호</td>
								<td rowspan="2">회원 식별 및 연락</td>
								<td rowspan="2">회원 탈퇴 시</td>
							</tr>
							<tr>
								<td>기업</td>
								<td>회사 전화번호</td>
							</tr>
						</tbody>
					</table>
					<p style="padding-top:10px"><input type="checkbox" id="chk3"><label for="chk3">개인정보수집 및 이용에 동의 합니다.</label></p><br>
					<br>
					<input type="submit" value="확인">
					<input type="button" value="취소">
					
				</div>
			</div>
			</form>
		</div>
	</section>
	
	<script>
		$(document).ready(function(){
			$('#t_mid1').click(function(){
				$('#c_mid1').css('display','block');
				$('#c_mid2').css('display','none');
				$('#c_mid3').css('display','none');
				$(this).css('background-color','#fe431e');
				$(this).css('color','#ffffff');
				$('#t_mid2').css('background-color','#ffffff');
				$('#t_mid2').css('color','black');
				$('#t_mid3').css('background-color','#ffffff');
				$('#t_mid3').css('color','black');
			});
			$('#t_mid2').click(function(){
				$('#c_mid1').css('display','none');
				$('#c_mid2').css('display','block');
				$('#c_mid3').css('display','none');
				$(this).css('background-color','#fe431e');
				$(this).css('color','#ffffff');
				$('#t_mid1').css('background-color','#ffffff');
				$('#t_mid1').css('color','black');
				$('#t_mid3').css('background-color','#ffffff');
				$('#t_mid3').css('color','black');
			});
			$('#t_mid3').click(function(){
				$('#c_mid1').css('display','none');
				$('#c_mid2').css('display','none');
				$('#c_mid3').css('display','block');
				$(this).css('background-color','#fe431e');
				$(this).css('color','#ffffff');
				$('#t_mid2').css('background-color','#ffffff');
				$('#t_mid2').css('color','black');
				$('#t_mid1').css('background-color','#ffffff');
				$('#t_mid1').css('color','black');
			});
		});
		function check(){
			if($('#chk1').is(":checked")== false){
				alert("사이트 이용약관에 동의해 주세요");
				return false;
			}
			if($('#chk2').is(":checked")== false){
				alert("개인정보 수집약관에 동의해 주세요");
				return false;
			}
		}
		
	</script>
</body>
</html>