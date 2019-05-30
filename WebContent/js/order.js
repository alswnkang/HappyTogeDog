$(function(){
	
	$('.status').each(function(){
		var st = $(this).data('status');
		$(this).children('option').each(function(){
			if(st == $(this).val()){
				$(this).prop("selected",true);
			}
		});
	});
	
	
	$('#orderListBox .status').change(function(){
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
					//alert('변경 완료');
				}
				
			},
			error : function(){
				console.log("error");
			}
		});
		
	});
	
	
});

function list(reqPage){
	
	$('input[name=reqPage]').val(reqPage);
	search.submit();
}
