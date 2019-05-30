$(function(){
	
	/* 검색박스의 주문상태 값 세팅 */
	var status = $('select[name=status]').data('val');
	$('select[name=status]').children('option').each(function(){
		if(status == $(this).val()){
			$(this).prop("selected",true);
		}
	});
	
	/* 검색박스의 검색타입 값 세팅 */
	var searchType = $('select[name=searchType]').data('val');
	$('select[name=searchType]').children('option').each(function(){
		if(searchType == $(this).val()){
			$(this).prop("selected",true);
		}
	});
	
	/* 검색박스의 결제수단 값 세팅 */
	var method = $('input[name=method]').val();
	$('input[type=radio]').each(function(){
		if(method == $(this).val()){
			$(this).prop("checked",true);
		}
	});
	
	
	/* 각 주문의 주문상태 selectbox 세팅 */
	$('.status').each(function(){
		var st = $(this).data('status');
		$(this).children('option').each(function(){
			if(st == $(this).val()){
				$(this).prop("selected",true);
			}
		});
	});
	
	/* 각 주문의 주문상태 selectbox변경하면 update */
	$('#orderListBox .status').change(function(){
		var req
		var no = $(this).data('no');
		var status = $(this).val();
		$.ajax({
			url : 'updateStatus',
			type : 'post',
			data : {no:no,status:status},
			success : function(data){
				if(data == 'fail'){
					alert(data);
				}else{
					list('');
					//alert('변경 완료');
				}
				
			},
			error : function(){
				console.log("error");
			}
		});
		
	});
	
	
});

/* 페이지 이동 */
function list(reqPage){
	
	$('input[name=reqPage]').val(reqPage);
	search.submit();
}
