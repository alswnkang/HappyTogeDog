$(function(){

	/* 검색박스의 검색타입 값 세팅 */
	var searchType = $('select[name=searchType]').data('val');
	$('select[name=searchType]').children('option').each(function(){
		if(searchType == $(this).val()){
			$(this).prop("selected",true);
		}
	});
	
});

/* 페이지 이동 */
function list(reqPage){
	
	$('input[name=reqPage]').val(reqPage);
	search.submit();
}

/* 비밀번호 입력 체크 */
function check(f){
	if(f.boardPw.value == ''){
		alert('비밀번호를 입력해주세요');
		f.boardPw.focus();
		return false;
	}
	return true;
	
}


function chkSubmit(f){
	if(f.boardTitle.value == ''){
		alert('제목을 입력해주세요');
		f.boardTitle.focus();
		return false;
	}
	if(f.boardName.value == ''){
		alert('작성자명을 입력해주세요');
		f.boardName.focus();
		return false;
	}
	return true;
	
}
