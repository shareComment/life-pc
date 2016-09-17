$(document).ready(function() {
	// 初始化回复列表
	replyPaginator('1');
	$('#saveReply').click(function(){
		if($.trim($('#reply_content').val()) == ''){
			alert('请先填写回复内容');
			return false;
		}
		$.ajax({
			url: '/comment/addComment',
			data: {"brand_id":$('#brand_id').val(), "comment_pid":$('#comment_id').val(), "comment":$('#reply_content').val()},
			type: 'post',
			dataType: 'json',
			success: function(data){
				if(data == '0'){
					alert("回复成功！");
					$('#reply_content').val('');
					replyPaginator('1');
				}else{
					alert(data);
				}
			}
		});
	});

	// 查询回复列表
	function replyPaginator(page){
		$.ajax({
			url: '/comment/reply',
			data: {"brand_id":$('#brand_id').val(), "comment_id":$('#comment_id').val(), "pageNo":page},
			type: 'get',
			dataType: 'json',
			success: function(data){
				console.log(data);
				// 评论列表
				var str = '';
				$.each(data.replyList, function(i) {
					str +=  '<div class="media">'+
								'<a class="pull-left" href="#">'+
									'<img class="media-object" src="'+data.replyList[i].avator+'" onerror="this.src='+'\''+'/images/comment/user.png'+'\''+'" alt="用户头像" width="54px" height="54px" />'+
								'</a>'+
								'<div class="media-body">'+
										'<h6 class="media-heading">'+data.replyList[i].nickname+'&nbsp;&nbsp;<span style="color:'+data.replyList[i].grade_Color+'">'+data.replyList[i].grade_Name+'</span></h6>'+
										'<h6>'+data.replyList[i].comment+'</h6>';
					if(data.replyList[i].comment_img){
						str += '<div>';
						var imgs = data.replyList[i].comment_img.split(',');
						$.each(imgs, function(j) {
							str += '<a href="'+imgs[j]+'" data-toggle="lightbox"><img src="'+imgs[j]+'" class="img-rounded" alt="" width="60px" height="40px"></a>&nbsp;&nbsp;';
						});
						str += '</div>';
					}
					str += '<h6 class="pull-right">'+data.replyList[i].add_time+'&nbsp;&nbsp;&nbsp;&nbsp;</h6>'+
								'</div>'+
							'</div>';
				});
				$('#replyList').empty();
				$('#replyList').append(str);
				// 分页控件
				var paginator = '';
				if(data.totalPages > 1){
					paginator += '<div class="pull-right">'+
									'<ul id="reply-paginator">'+
									'</ul>'+
								 '</div>';
					$('#replyList').append(paginator);
					var options = {
				            currentPage: data.currentPage,
				            totalPages: data.totalPages,
				            bootstrapMajorVersion: 3,
				            onPageClicked: function (event, originalEvent, type, page) {
				            	replyPaginator(page);
				            }
				        }
					$('#reply-paginator').bootstrapPaginator(options);
				}
			}
		});
	}

	// 点赞
	$('#zan').click(function(){
		$.ajax({
			url: '/comment/zan',
			type: 'GET',
			data: {'comment_id': $('#comment_id').val()},
			dataType: 'text',
			success: function(data){
				if(data == '0'){
					$('#zanCount').text(parseInt($('#zanCount').text())+1);
				}else{
					alert(data);
				}
			}
		});
	});
});