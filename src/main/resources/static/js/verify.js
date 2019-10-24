
	//校验手机号码格式
	function verifyPhoneFunc(val){
		var reg=new RegExp('^1\\d{10}$');
		if(!reg.test(val)){
			layer.msg('手机号码格式不正确');
			return  false;
		}
	}
		
	//校验邮箱码格式
	function verifyEmailFunc(val){
		var reg=new RegExp('^[\\.a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$');
		if(!reg.test(val)){
			layer.msg('邮箱格式不正确');
			return false;
		}
	}
	
		
	//校验字符串不能为空
	function verifyBlankFunc(val,verifyStr){
		if(!val){
			layer.msg(verifyStr+'不能为空');
			return false;
		}
	}
	
	//校验字符串的长度
	function verifyBlankAndLengthFunc(val,verifyStr,length){
		verifyBlankFunc(val,verifyStr);
		if(val.length>length){
			layer.msg(verifyStr+'长度太长');
			return false;
		}
	}
	
	//校验数字格式
	function verifyNumberFunc(val,verifyStr){
		verifyBlankFunc(val,verifyStr);
		if(isNaN(val)){
			layer.msg(verifyStr+'格式不正确');
			return false;
		}
	}
		
	//校验数字格式和长度
	function verifyNumberAndLengthFunc(val,verifyStr,length){
		verifyBlankFunc(val,verifyStr);
		if(isNaN(val)){
			layer.msg(verifyStr+'格式不正确');
			return false;
		}
		if(val.length>length){
			layer.msg(verifyStr+'长度不能超过'+length);
			return false;
		}
	}
	
	//校验身份证信息
	function verifyIdCardFunc(val,verifyStr,length){
		if(!val){
			layer.msg(verifyStr+'不能为空');
			return false;
		}
		if(val.length<length || val.length>length){
			layer.msg(verifyStr+'格式不正确');
			return false;
		}
	}
	
	
	//校验登陆密码
	function verfiyPassWordFunc(val){
		if(!val){
			layer.msg('密码不能为空');
			return false;
		}
		var reg=new RegExp('/^[A-Za-z0-9]{6,12}$/');
		if(val.length<6){
			layer.msg('密码至少为6位')
			return false;
		}
	}
	
	