$(document).ready(function() {
	$('#getCode').click(function (){
		var tel = $('#telephone').val();
		if(validatemobile(tel)){
			return false;
		}
		$.ajax({
		     type: 'GET',
		     url: '/signup/getCode',
		     data: {'telephone':tel},
		     dataType: 'json',
		     success: function (data){
		    	 if(data == '0'){
		    		 $('#getCode').attr("disabled", true);
		    		 setTimeout(function(e){
		    			 $('#getCode').attr("disabled", false);
	                    },60000);
		    	 }
		     }
		});
	});

	/**
	 * 检查手机号码有效性
	 */
	function validatemobile(param){
		var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
		if(!myreg.test(param)) {
			alert('请输入有效的手机号码！');
			return false;
		}
	}
});