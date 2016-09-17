$(document).ready(function() {
	// 品牌星级初始化
	$(".score").rating({displayOnly: true, step: 0.1});
	allCommentPaginator('1');
	// 查询品牌下全部评论
	$('#allComment').click(function(){
		allCommentPaginator('1');
	});
	// 所有评论翻页
	function allCommentPaginator(page){
		$.ajax({
			url: '/brand/allComment',
			post: 'GET',
			data: {"brand_id":$('#brand_id').val(),"pageNo":page},
			dataType: 'json',
			success: function(data){
				console.log(data);
				// 评论列表
				var str = '';
				$.each(data.commentList, function(i) {
					str += '<p></p>'+
						  '<div class="media">'+
				          	'<a class="pull-left" href="#">'+
				          		'<img class="media-object" src="'+data.commentList[i].avator+'" onerror="this.src='+'\''+'/images/brand/user.png'+'\''+'" alt="用户头像" width="54px" height="54px" />'+
				          	'</a>'+
				          	'<div class="media-body">'+
				          		'<a href="/comment/'+data.commentList[i].comment_id+'">'+
				          			'<h6 class="media-heading">'+data.commentList[i].nickname+'&nbsp;&nbsp;<span style="color:'+data.commentList[i].grade_Color+'">'+data.commentList[i].grade_Name+'</span></h6>'+
				          			'<h6>'+data.commentList[i].comment+'</h6>';
					if(data.commentList[i].comment_img){
						str += '<div>';
						var imgs = data.commentList[i].comment_img.split(',');
						$.each(imgs, function(j) {
							str += '<a href="'+imgs[j]+'" data-toggle="lightbox"><img src="'+imgs[j]+'" class="img-rounded" alt="" width="60px" height="40px"></a>&nbsp;&nbsp;';
						});
						str += '</div>';
					}
		          	str += '<h6 class="pull-right">'+data.commentList[i].add_time+'&nbsp;&nbsp;&nbsp;&nbsp;</h6>'+
		          		'</a>'+
		          	'</div>'+
		          '</div>';
				});
				$('#all').empty();
				$('#all').append(str);
				// 分页控件
				var paginator = '';
				if(data.all_totalPages > 1){
					paginator += '<div class="pull-right">'+
									'<ul id="allComment-paginator">'+
									'</ul>'+
								 '</div>';
					$('#all').append(paginator);
					var options = {
				            currentPage: data.all_currentPage,
				            totalPages: data.all_totalPages,
				            bootstrapMajorVersion: 3,
				            onPageClicked: function (event, originalEvent, type, page) {
				            	allCommentPaginator(page);
				            }
				        }
					$('#allComment-paginator').bootstrapPaginator(options);
				}
			}
		});
	}
	// 我的评论翻页
	function myCommentPaginator(page){
		$.ajax({
			url: '/brand/myComment',
			post: 'GET',
			data: {"brand_id":$('#brand_id').val(),"pageNo":page},
			dataType: 'json',
			success: function(data){
				console.log(data);
				// 评论列表
				var str = '';
				$.each(data.myCommentList, function(i) {
					str += '<p></p>'+
						  '<div class="media">'+
				          	'<a class="pull-left" href="#">'+
				          		'<img class="media-object" src="'+data.myCommentList[i].avator+'" onerror="this.src='+'\''+'/images/brand/user.png'+'\''+'" alt="用户头像" width="54px" height="54px" />'+
				          	'</a>'+
				          	'<div class="media-body">'+
				          		'<a href="/comment/'+data.myCommentList[i].comment_id+'">'+
				          			'<h6 class="media-heading">'+data.myCommentList[i].nickname+'&nbsp;&nbsp;<span style="color:'+data.myCommentList[i].grade_Color+'">'+data.myCommentList[i].grade_Name+'</span></h6>'+
				          			'<h6>'+data.myCommentList[i].comment+'</h6>';
					if(data.myCommentList[i].comment_img){
						str += '<div>';
						var imgs = data.myCommentList[i].comment_img.split(',');
						$.each(imgs, function(j) {
							str += '<a href="'+imgs[j]+'" data-toggle="lightbox"><img src="'+imgs[j]+'" class="img-rounded" alt="" width="60px" height="40px"></a>&nbsp;&nbsp;';
						});
						str += '</div>';
					}
					str += '<h6 class="pull-right">'+data.myCommentList[i].add_time+'&nbsp;&nbsp;&nbsp;&nbsp;</h6>'+
				          		'</a>'+
				          	'</div>'+
				          '</div>';
				});
				$('#my').empty();
				$('#my').append(str);
				// 分页控件
				var paginator = '';
				if(data.my_totalPages > 1){
					paginator += '<div class="pull-right">'+
									'<ul id="myComment-paginator">'+
									'</ul>'+
								 '</div>';
					$('#my').append(paginator);
					var options = {
				            currentPage: data.my_currentPage,
				            totalPages: data.my_totalPages,
				            bootstrapMajorVersion: 3,
				            onPageClicked: function (event, originalEvent, type, page) {
				            	myCommentPaginator(page);
				            }
				        }
					$('#myComment-paginator').bootstrapPaginator(options);
				}
			}
		});
	}
	// 查询品牌下我的评论
	$('#myComment').click(function(){
		myCommentPaginator('1');
	});

	
});