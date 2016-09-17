$(document).ready(function() {
	// 取轮播图
	getCarouselList();
	function getCarouselList(){
		$.ajax({
			url: '/carousel',
			type: 'GET',
			dataType: 'json',
			success: function(data){
				console.log(data);
				var str = '';
				$.each(data.carouselList, function(i) {
					if(i == '0')
						str += '<div class="item active">'+
								'<img alt="slide" src="'+data.carouselList[i].param_value+'" width="100%" />'+
							   '</div>';
					else
						str += '<div class="item">'+
								'<img alt="slide" src="'+data.carouselList[i].param_value+'" width="100%" />'+
							   '</div>';
				});
				$('#carouselList').empty();
				$('#carouselList').append(str);
			}
		});
	}
});