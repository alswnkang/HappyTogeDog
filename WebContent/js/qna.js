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
