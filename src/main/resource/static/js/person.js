$(document).ready(function() {
	$('.right-content').load('/person/profit');

	// 修改密码
	$('#chgPass').click(function (){
		$('#o_password').val('');
		$('#n_password').val('');
		$('#n_password_').val('');
		$('#myModal').modal('show');
	});
	
	$('#updPass').click(function(){
		if($.trim($('#o_password').val()) == ''){
			alert('请输入原密码！');
			return false;
		}
		if($.trim($('#n_password').val()) == ''){
			alert('请输入新密码！');
			return false;
		}
		if($.trim($('#n_password_').val()) == ''){
			alert('请再次输入新密码！');
			return false;
		}
		if($.trim($('#n_password').val()) != $.trim($('#n_password_').val())){
			alert('两次密码输入不一致！');
			return false;
		}
		$.ajax({
			url: 'person/chgPass',
			type: 'post',
			dataType: 'text',
			data: {"oldPwd":$('#o_password').val(), "newPwd":$('#n_password').val()},
			success: function(data){
				console.log(data);
				if(data == '0'){
					alert('密码修改成功！');
					$('#myModal').modal('hide');
				}else{
					alert(data);
				}
			}
		});
	});

	// 编辑资料
	$('#eidtProfile').click(function (){
		$('.right-content').load('/person/profile');
	});

	// 个人中心menu
	$('.left-menu ul li a').click(function (){
		$('.left-menu ul .active').removeClass('active');
		$(this).parent().addClass('active');
		$('.right-content').load($(this).attr('data-url'));
	});
});