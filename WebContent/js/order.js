$(function(){
	
	$('.status').each(function(){
		var st = $(this).data('status');
		$(this).children('option').each(function(){
			if(st == $(this).val()){
				$(this).prop("selected",true);
			}
		});
	});
	
});
