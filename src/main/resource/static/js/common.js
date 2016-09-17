$(document).ready(function() {
	$('.dropdown-toggle').dropdown();

	// 分享
	$('#shareBtn')
	// 反馈
	$('#feedBtn').click(function(){
		var content = $('#content').val();
		var contact = $('#contact').val();
		if($.trim(content) && $.trim(contact)){
			$.ajax({
				url: '/sys/feed',
				data: {"feed_con":content},
				type: 'post',
				dataType: 'text',
				success: function(data){
					if(data == '0'){
						alert("反馈成功~");
						$('#content').val('');
						$('#contact').val('');
						$('#feedModal').modal('hide');
					}else{
						alert(data);
						$('#content').val('');
						$('#contact').val('');
						$('#feedModal').modal('hide');
					}
				}
			});
		}else{
			alert("请将信息填写完整~");
			$('#content').val('');
			$('#contact').val('');
			$('#feedModal').modal('hide');
		}
	});

	// 快捷导航
	$('.rootmenu .top').on('mouseover',function(){
		$.ajax({
			url: '/subcate/'+$(this).attr('id'),
			type: 'get',
			dataType: 'json',
			success: function(data){
				var secmenu = '';
				$.each(data,function(n,value) {
					secmenu += "<li><a tabindex='-1' href='javascript:void(0);' class='sec' id='"+value.cate_id+"'>"+value.cate_name+"</a><ul class='dropdown-menu thrmenu'></ul></li>";
				});
				$('.secmenu').empty();
				$('.secmenu').append(secmenu);
			}
		});
	});

	$('.secmenu .sec').on('mouseover',function(){
		$.ajax({
			url: '/taglist/'+$(this).attr('id'),
			type: 'get',
			dataType: 'json',
			success: function(data){
				var thrmenu = '';
				$.each(data,function(n,value) {
					thrmenu += "<li><a tabindex='-1' href='javascript:void(0);'>"+value.tag_name+"</a></li>";
				});
				$('.thrmenu').empty();
				$('.thrmenu').append(thrmenu);
			}
		});
	});
});