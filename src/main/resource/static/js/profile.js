$(document).ready(function() {
	// 初始化城市选区
	$('#city').citySelect({prov:$('#pro-prov').val(), city:$('#pro-city').val(), dist:$('#pro-dist').val()});
	// 保存个人资料
	$('#saveProfile').click(function (){
		var email = $('#email').val();
		var nickname = $('#nickname').val();
		var age = $('#age').val();
		var gender = $('input:radio:checked').val();
		var education = $('#education').val();
		var collage = $('#collage').val();
		var address = $('#select-prov').val()+','+$('#select-city').val()+','+$('#select-dist').val();
		var avatar = $('#avatar-src').val();
		$.ajax({
			url: '/person/profile',
			type: 'post',
			data: {"email":email, "nickname":nickname, "age":age, "gender":gender, "education":education, "collage":collage, "address":address, "avatar":avatar},
			dataType: 'text',
			success: function(data){
				if(data == "0"){
					alert('修改成功！');
					$('.right-content').load('/person/profile');
				}else{
					alert(data);
				}
			}
		});
	});

	// 头像上传
	$('#avatarFileSelector').on('change', function(){
		$.ajaxFileUpload({
	        url: '/sys/upload',
	        secureuri: false,
	        fileElementId: 'avatarFileSelector',//file标签的id
	        type: 'post',
	        dataType: 'text',//返回数据的类型
	        success: function (data, status) {
	        	console.log(data);
	        	$('#filePicker').attr('src','http://i.ziyoush.com/life_interface'+data);
	        	$('#avatar-src').val(data);
	        },
	        error: function (data, status, e) {
	            alert("上传失败，请重新上传" + e);
	        }
	    });
	});
});