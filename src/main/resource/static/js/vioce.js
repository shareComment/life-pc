$(document).ready(function() {
	$('#commentBtn').click(function (){
		var brand_id = $('#brand_id').val();
		var comment_pid = 0;
		var score = $('#score').val()*20;
		var comment = $('#comment').val();
		var comment_img = '';
		$(".imgurl").each(function(){
			if($(this).val() != ''){
				comment_img += ',' + $(this).val();
			}
		});
		$.ajax({
			url: '/comment/addComment',
			data: {"brand_id":brand_id, "comment_pid":comment_pid, "score":score, "comment":comment, "comment_img":comment_img.substring(1)},
			type: 'post',
			dataType: 'json',
			success: function(data){
				if(data == '0'){
					alert("评论成功！");
					window.location.href="/brand/"+brand_id;
				}else{
					alert(data);
				}
			}
		});
	});
});