$(document).ready(function() {
	// 初始化点评列表
	remarkPaginator('1');
	// 查询点评列表
	function remarkPaginator(page){
		$.ajax({
			url: '/person/remark/'+page,
			data: {"pageNo":page},
			type: 'get',
			dataType: 'json',
			success: function(data){
				console.log(data);
				// 点评列表
				var str = '';
				$.each(data.remarkList, function(i) {
					str += '<a href="#" class="list-group-item">'+
						   	'<p><h6 class="list-group-item-heading">于 '+data.remarkList[i].add_time+'&nbsp;&nbsp;评论了 '+data.remarkList[i].brand_name+'</h6></p>'+
						   	'<p><span>评分：</span><span class="list-group-item-text"><input name="score" class="score rating-loading" value="'+data.remarkList[i].score/20+'" data-size="xs" /></span></p>'+
						   	'<p><span>评论内容：</span><span class="list-group-item-text">'+data.remarkList[i].comment+'</span></p>'+
						   '</a>';
				});
				$('#remarkList').empty();
				$('#remarkList').append(str);
				// 分页控件
				var paginator = '';
				if(data.totalPages > 1){
					paginator += '<div class="pull-right">'+
									'<ul id="remark-paginator">'+
									'</ul>'+
								 '</div>';
					$('#remarkList').append(paginator);
					var options = {
				            currentPage: data.currentPage,
				            totalPages: data.totalPages,
				            bootstrapMajorVersion: 3,
				            onPageClicked: function (event, originalEvent, type, page) {
				            	remarkPaginator(page);
				            }
				        }
					$('#remark-paginator').bootstrapPaginator(options);
				}
				$(".score").rating({displayOnly: true, step: 0.1});
			}
		});
	}
});