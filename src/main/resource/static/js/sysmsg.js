$(document).ready(function() {
	sysmsgPaginator('1');
	// 系统消息页
	function sysmsgPaginator(page){
		$.ajax({
			url: '/person/sysmsg/'+page,
			post: 'GET',
			dataType: 'json',
			success: function(data){
				console.log(data);
				// 系统消息列表
				var str = '';
				$.each(data.readMsgList, function(i) {
					str += '<a href="#" class="list-group-item">'+
							'<p><h5 class="list-group-item-heading">'+data.readMsgList[i].title+'</h5></p>'+
							'<p class="list-group-item-text">'+data.readMsgList[i].content+'</p>'+
						   '</a>';
				});
				$('#sysmsgList').empty();
				$('#sysmsgList').append(str);
				// 分页控件
				var paginator = '';
				if(data.sysmsg_totalPages > 1){
					paginator += '<div class="pull-right">'+
									'<ul id="sysmsg-paginator">'+
									'</ul>'+
								 '</div>';
					$('#sysmsgList').append(paginator);
					var options = {
				            currentPage: data.sysmsg_currentPage,
				            totalPages: data.sysmsg_totalPages,
				            bootstrapMajorVersion: 3,
				            onPageClicked: function (event, originalEvent, type, page) {
				            	sysmsgPaginator(page);
				            }
				        }
					$('#sysmsg-paginator').bootstrapPaginator(options);
				}
			}
		});
	}
});