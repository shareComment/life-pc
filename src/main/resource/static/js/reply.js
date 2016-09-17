$(document).ready(function() {
	myReplyPaginator('1');
	// 查询我的回复
	$('#mr').click(function(){
		myReplyPaginator('1');
	});
	// 查询回复我的
	$('#rm').click(function(){
		replyMePaginator('1');
	});
	// 我的回复翻页
	function myReplyPaginator(page){
		$.ajax({
			url: '/person/myReply',
			post: 'GET',
			data: {"pageNo":page},
			dataType: 'json',
			success: function(data){
				console.log(data);
				// 回复列表
				var str = '';
				$.each(data.myReplyList, function(i) {
					str += '<a href="#" class="list-group-item">'+
							'<p><h6 class="list-group-item-heading">于 '+data.myReplyList[i].add_time+'&nbsp;&nbsp;&nbsp;&nbsp;回复了 '+data.myReplyList[i].nickname+'</h6></p>'+
							'<p class="list-group-item-text"> '+data.myReplyList[i].comment+'</p>'+
						   '</a>';
				});
				$('#myReplyList').empty();
				$('#myReplyList').append(str);
				// 分页控件
				var paginator = '';
				if(data.mr_totalPages > 1){
					paginator += '<div class="pull-right">'+
									'<ul id="myReply-paginator">'+
									'</ul>'+
								 '</div>';
					$('#myReplyList').append(paginator);
					var options = {
				            currentPage: data.mr_currentPage,
				            totalPages: data.mr_totalPages,
				            bootstrapMajorVersion: 3,
				            onPageClicked: function (event, originalEvent, type, page) {
				            	myReplyPaginator(page);
				            }
				        }
					$('#myReply-paginator').bootstrapPaginator(options);
				}
			}
		});
	}

		// 我的回复翻页
		function replyMePaginator(page){
			$.ajax({
				url: '/person/replyMe',
				post: 'GET',
				data: {"pageNo":page},
				dataType: 'json',
				success: function(data){
					console.log(data);
					// 回复列表
					var str = '';
					$.each(data.replyMeList, function(i) {
						str += '<a href="#" class="list-group-item">'+
								'<p><h6 class="list-group-item-heading">'+data.replyMeList[i].nickname+'&nbsp;&nbsp;&nbsp;&nbsp;于  '+data.replyMeList[i].add_time+'&nbsp;&nbsp;&nbsp;&nbsp;回复了我</h6></p>'+
								'<p class="list-group-item-text">'+data.replyMeList[i].comment+'</p>'+
							   '</a>';
					});
					$('#replyMeList').empty();
					$('#replyMeList').append(str);
					// 分页控件
					var paginator = '';
					if(data.rm_totalPages > 1){
						paginator += '<div class="pull-right">'+
										'<ul id="replyMe-paginator">'+
										'</ul>'+
									 '</div>';
						$('#replyMeList').append(paginator);
						var options = {
					            currentPage: data.rm_currentPage,
					            totalPages: data.rm_totalPages,
					            bootstrapMajorVersion: 3,
					            onPageClicked: function (event, originalEvent, type, page) {
					            	replyMePaginator(page);
					            }
					        }
						$('#replyMe-paginator').bootstrapPaginator(options);
					}
				}
			});
	}
});