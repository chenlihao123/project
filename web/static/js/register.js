

function register(){
	util.showMsg(false,"phoneMsg","",true);
	util.showMsg(false,"codeMsg","",true);
	util.showMsg(false,"username","",true);
	util.showMsg(false,"pwdMsg","",true);
	util.showMsg(false,"err-txt","");
	util.showMsg(false,"usernameMsg","");
	var phone = $("#phone").val().trim();
	var pwd = $("#pwd").val();
	var username = $("#username").val();
	var messageCode = $("#messageCode").val().trim();
	var countryCode = $("#countryCode").val();
	var fid = $("#fid").val();
	var refer = $("#refer").val();
	if(util.isEmpty(phone)){
		util.showMsg(true,"phoneMsg","请输入手机号",true);
		$("#phone").focus();
	}else{
		if(util.isInterPhone(phone,countryCode)){
			if(util.isEmpty(messageCode)){
				util.showMsg(true,"codeMsg","请输入验证码",true);
				$("#messageCode").focus();
				return;
			}
			if(util.isEmpty(username)){
				util.showMsg(true,"usernameMsg","",true);
				util.showMsg(true,"usernameMsg","请输入用户名",true);
				$("#username").focus();
				return;
			}
			if(util.isEmpty(pwd)){
				//避免密码提示语与下方综合提示语冲突
				util.showMsg(true,"pwdMsg","",true);
				util.showMsg(true,"err-txt","请输入密码");
				$("#pwd").focus();
				return;
			}else if(!util.checkPwd(pwd)){
				//避免密码提示语与下方综合提示语冲突
				util.showMsg(true,"pwdMsg","",true);
				util.showMsg(true,"err-txt","密码格式不正确");
				$("#pwd").focus();
				return;
			}
			//
			console.log(fid);

			$.ajax({
				url:"http://localhost:8080/onlinelearning/stu.do",
				dataType: "json",
				type: "POST",
				data:{
					phone:phone,
					password:pwd,
					username:username,
					code:messageCode,
					action: "register"

				},
				success:function (result) {
					var jsonObj = JSON.parse(result);
					alert(jsonObj)
				}
			})






			// $.ajax({
			// 	url: "/isPhoneExist?phone="+phone,
			// 	type:"post",
			// 	success: function(data){
			// 		 if("true"==data){//手机号在平台存在
			// 		$.ajax({
			// 			url: "/v11/registertologin?phone="+phone+"&code="+messageCode+"&password="+pwd,
			// 			type:"post",
			// 			dataType : 'json',
			// 			success: function(data){
			// 					if (true == data.status) {
			// 						if(fid == "123856"){
			// 							 var host=document.domain;
			// 							 var url = document.location.protocol + "//"+host+"/v11/verify?&fid="+fid
			// 							 +"&newversion=true&refer="+encodeURIComponent(refer)+"&logined=1";
			// 							 window.location = url;
			// 						}else{
			// 							window.location = decodeURIComponent(refer);
			// 						}
			// 					}else{
			// 					$("#err-txt").html(data.msg);
			// 					return;
			// 					}
			// 			}
			// 		});
			//
			// 		 }else{
			// 		 	//注册流程，根据fid是否大于0判断去选择单位还是去验证单位
			// 		 	if(fid > 0){
			// 		 		selectedUnit();
			// 		 	}else{
			//
			// 			 	$.ajax({
			//     				url: "/num/isPhoneCode?phone="+phone+"&phoneVercode="+messageCode,
			//     				type:"post",
			//     				success: function(data){
			//     					if("true"==data){
			//     						var host=document.domain;
			//     						var url = document.location.protocol+"//"+host+"/v11/selectunit?uname="+encodeURIComponent(phone)+"&code="+messageCode+"&password="+pwd+"&countrycode="+countryCode
			//     						+"&refer="+encodeURIComponent(refer)+"&newversion=true";
			//     						window.location = url;
			//     					}else{
			//     						$("#err-txt").html("验证码错误");
			//     						return;
			//     					}
			//     				}
			// 	    		 });
			//
			// 		 	}
			// 		 }
			// 	}
			// 	}
			// );


		}else{
			util.showMsg(true,"phoneMsg","手机号格式错误",true);
			$("#phone").focus();
		}

	}


}
//单位验证,多个页面使用，参数的id请别随便修改
function selectedUnit(){

	var phone = $("#phone").val();
	var pwd = $("#pwd").val();
	var messageCode = $("#messageCode").val();
	var countryCode = $("#countryCode").val();
	var fid = $("#").val();
	var refer = $("#refer").val();
	var enc = $("#enc").val();
	if(util.isEmpty(enc)){
		enc = "";
	}
	var fidName="";
	if(fid > 0){
		var host=document.domain;
		var url = document.location.protocol + "//"+host+"/v11/verify?uname="+encodeURIComponent(phone)+"&enc="+enc+"&password="+pwd+"&fid="+fid+"&unitname="+encodeURIComponent(fidName)+"&countrycode="+countryCode
			+"&newversion=true&refer="+encodeURIComponent(refer)+"&messageCode="+messageCode;
		window.location = url;
	}else{
		$("#err-txt").html('请选择单位');
		return;
	}
}


//不需要认证，等价于开放注册
function verify5(){
	var uname = $("#uname").val();
	var realName = $("#rName").val();
	var password = $("#regpwd").val();
	var code = $("#code").val();
	var fid = $("#fid").val();
	var countrycode = $("#countrycode").val();
	var refer = $("#refer").val();
	if (realName == "") {
		$("#err-txt").html("请输入姓名");
		return;
	}

	$.ajax({
		url: "/xxt/checkname?realname="+encodeURIComponent(realName)+"&uname="+uname,
		type:"get",
		dataType : 'json',
		success: function(data){
			if(data.result){
				var homeUrl = "/v11/doverify5";
				$.ajax({
					url: homeUrl,
					data:{ 'fid':fid,'realname':realName,'password':password,'countrycode':countrycode,'uname':uname,'code':code},
					dataType : 'json',
					success: function(data){
						if($("#micorserviceUrl").val() != ""){
							window.location = $("#micorserviceUrl").val();
						}else{
							window.location = decodeURIComponent(refer);
						}
					},
					error:function(e){
						$("#err-txt").html("注册异常，请联系客服");
						return;
					}
				});
			}else{
				$("#err-txt").html("操作不成功，请修改或稍后再试");
				return;
			}
		}
	});


}


function verify41(){
	var fid = $("#fid").val();
	var code = $("#verifycode").val();
	if(code == ""){
		$("#err-txt").html("请输入单位验证码");
		$("#verifycode").focus();
		return;
	}
	$.ajax({
		url: "/v11/verifycode",
		type:"post",
		dataType : 'json',
		data:{ 'fid':fid,'code':code},
		success: function(data){
			if(data.status){
				$("#verifycode").hide();
				$("#verify4_1").hide();
				$("#rName").show();
				$("#verify4_2").show();
				$("#err-txt").html("");
				$("#rName").focus();
			}else{
				$("#err-txt").html("单位验证码错误，请重新输入");
				return;
			}
		}
	});
}

function verify42(){
	var uname = $("#uname").val();
	var realName = $("#rName").val();
	var password = $("#regpwd").val();
	var code = $("#code").val();
	var fid = $("#fid").val();
	var verifycode = $("#verifycode").val();
	var countrycode = $("#countrycode").val();
	var refer = $("#refer").val();
	if (realName == "") {
		$("#err-txt").html("请输入姓名");
		return;
	}
	$.ajax({
		url: "/xxt/checkname?realname="+encodeURIComponent(realName)+"&uname="+uname,
		type:"get",
		dataType : 'json',
		success: function(data){
			if(data.result){
				var homeUrl = "/v11/doverify4";
				$.ajax({
					url: homeUrl,
					data:{ 'fid':fid,'realname':realName,'password':password,'countrycode':countrycode,'uname':uname,'code':code,'verifycode':verifycode},
					dataType : 'json',
					success: function(data){
						if(data.status){
							if($("#micorserviceUrl").val() != ""){
								window.location = $("#micorserviceUrl").val();
							}else{
								window.location = decodeURIComponent(refer);
							}
						}else{
							$("#err-txt").html(data.mes);
							return;
						}
					},
					error:function(e){
						$("#err-txt").html("注册异常，请联系客服");
						return;
					}
				});
			}else{
				$("#err-txt").html("操作不成功，请修改或稍后再试");
				return;
			}
		}
	});
}

function verify21(){
	var fid = $("#fid").val();
	var code = $("#code").val();
	var pwd = $("#pwd2").val();
	var openid4=$("#idNumber").val();
	if(openid4 == ""){
		$("#err-txt").html("请输入借阅证");
		return;
	}
	if(pwd == ""){
		$("#err-txt").html("请输入密码");
		return;
	}
	if(openid4 != ""  && pwd != ""){
		$.ajax({
			url: "/v11/verifyopenid4",
			type:"post",
			dataType : 'json',
			data:{ 'fid':fid,'openid4':openid4,'pwd':pwd},
			success: function(data){
				if(data.status){
					$("#verify2_1").hide();
					$("#idNumber").hide();
					$("#pwd2").hide();
					$("#showname").show();
					$("#verify2_2").show();
					$("#err-txt").html("");
					$("#rName").focus();
				}else{
					$("#err-txt").html("借阅证或密码错误");
					return;
				}
			}
		});
	}
}
function verify22(){
	var uname = $("#uname").val();
	var realName = $("#rName").val();
	var password = $("#regpwd").val();
	var pwd2 = $("#pwd2").val();//借阅证密码
	var code = $("#code").val();
	var fid = $("#fid").val();
	var openid4 = $("#idNumber").val();
	var countrycode = $("#countrycode").val();
	var refer = $("#refer").val();
	if (realName == "") {
		$("#err-txt").html("请输入姓名");
		return;
	}
	$.ajax({
		url: "/xxt/checkname?realname="+encodeURIComponent(realName)+"&uname="+uname,
		type:"get",
		dataType : 'json',
		success: function(data){
			if(data.result){
				var homeUrl = "/v11/doverify2";
				$.ajax({
					url: homeUrl,
					data:{ 'fid':fid,'realname':realName,'password':pwd2,'regpwd':password,'countrycode':countrycode,'uname':uname,'code':code,'openid4':openid4},
					dataType : 'json',
					success: function(data){
						if(data.status){

							if($("#micorserviceUrl").val() != ""){
								window.location = $("#micorserviceUrl").val();
							}else{
								window.location = decodeURIComponent(refer);
							}
						}else{
							$("#err-txt").html(data.mes);
							return;
						}
					},
					error:function(e){
						$("#err-txt").html("注册异常，请联系客服");
						return;
					}
				});
			}else{
				$("#err-txt").html("操作不成功，请修改或稍后再试");
				return;
			}
		}
	});
}

function doverify(type,fid,idNumber,pwd,realname,refer){
	var idNumber=$("#idNumber").val().trim();
	if(idNumber == ""){
		$("#idNumber").focus();
		$("#err-txt").html("请输入学号");
		return;
	}


	var param = {'fid':fid,'idNumber':idNumber,'pwd':pwd,'realname':realname};

	$.post("/mooc/doverify",param,function(data,status){
		if(data.status){
			window.location = decodeURIComponent(refer);
		}else{
			var msg = util.isEmpty(data.msg) ? "绑定失败" : data.msg;
			var returnType = util.isEmpty(data.type) ? "0" : data.type;
			if(returnType == "1"){//姓名不相符，需要输入姓名
				$("#showRname").show();

			}else if(returnType == "3"){//需要输入密码验证
				$("#showpwd").show();

			}else if(returnType == "5"){//信任库
				window.location = decodeURIComponent(refer);
			}
			if(returnType != "5"){
				$("#err-txt").html(msg);
			}
		}
	},"json");
}

function verify(){

	var fid=$("#fid").val();
	var refer = $("#refer").val();
	var uname=$("#uname").val();
	var realname=$("#rName").val();
	var password=$("#pwd").val();
	var regpwd = $("#regpwd").val();
	var idNumber=$("#idNumber").val().trim();
	var FidName=$("#unitname").val().trim();
	var countrycode = $("#countrycode").val();

	if(fid == ""){
		return;
	}

	if($("#logined").val() =="1"){
		doverify(1,fid,idNumber,password,realname,refer);
	}else{

		if(idNumber == ""){
			$("#idNumber").focus();
			$("#err-txt").html("请输入学号");
			return;
		}else if(realname == ""){
			$("#rName").focus();
			$("#err-txt").html("请输入姓名");
			return;
		}

		var code=$("#code").val();

		if(fid == "-1"){
			fid = 0;
		}

		jQuery.ajax({
			url : '/v11/doverify?fid='+fid+'&idNumber='+encodeURIComponent(idNumber)+"&r=1",
			type :'post',
			async : true,
			data:{'uname':uname,'realname':realname,'password':password,'regpwd':regpwd,'code':code,'unitname':FidName,'countrycode':countrycode,'newversion':'true'},
			dataType : 'json',
			success : function(data){
				if(data.status){
					var host=document.domain;
					if(data.type=="3"){
						if($("#micorserviceUrl").val() != ""){
							window.location = $("#micorserviceUrl").val();
						}else{
							window.location = decodeURIComponent(refer);
						}
					}else if(data.type=='4'){
						$("#err-txt").html("您输入的学号与姓名不相符，请重新输入");
						$("#rName").show();
					}else if(data.type=='5'){//让输入密码
						$("#err-txt").html("请输入该账号密码完成绑定");
						$("#showpwd").show();
					}else if(data.type=='6'){//密码输入错误
						$("#err-txt").html("您输入的密码与该账号密码不相符，请重新输入");
						$("#showpwd").show();
					}else if(data.type=='1'){
						$("#promptBox").css("display","block");
						$(".textTip").html(data.msg);
					}else{
						$("#err-txt").html("绑定失败");
						return;
					}
				}else{
					$("#err-txt").html(data.msg);
					return;
				}
			}
		});
	}
}

//验证码登录不存在时，输入完登录密码之后选择单位或验证单位
function toSelectUnitPwd(){
	util.showMsg(false,"passwordMsg","",true);
	util.showMsg(false,"passwordMsg","",true);
	var phone = $("#phone").val();
	var pwd = $("#pwd").val();
	var messageCode = $("#messageCode").val();
	var countrycode = $("#countryCode").val();
	var refer = $("#refer").val();
	var fid = $("#fid").val();
	if(util.isEmpty(pwd)){
		util.showMsg(true,"passwordMsg","请输入密码",true);
		return;
	}else if(!util.checkPwd(pwd)){
		util.showMsg(true,"passwordMsg","密码格式不正确",true);
		return;
	}
	//注册流程，根据fid是否大于0判断去选择单位还是去验证单位
	if(fid > 0){
		selectedUnit();
	}else{
		var host=document.domain;
		var url = document.location.protocol+"//"+host+"/v11/selectunit?uname="+encodeURIComponent(phone)+"&code="+messageCode+"&password="+pwd+"&countrycode="+countrycode
			+"&refer="+encodeURIComponent(refer)+"&newversion=true";
		window.location = url;
	}
}

//不选择单位，跳转至输入姓名
function skipToSetName(){
	var uname = $("#phone").val();
	var pwd = $("#pwd").val();
	var enc = $("#enc").val();
	var countrycode = $("#countryCode").val();
	var refer = $("#refer").val();
	var host=document.domain;
	var url=document.location.protocol+"//"+host+"/v11/setname?uname="+encodeURIComponent(uname)+"&password="+pwd+"&enc="+enc+"&countrycode="+countrycode+"&refer="+encodeURIComponent(refer)+"&newversion=true";
	window.location = url;
}

//跳过单位选择输入姓名页面的注册请求
function registerName(){
	$("#err-txt").html("");
	var uname = $("#uname").val();
	var realName = $("#realname").val();
	var password = $("#password").val();
	var code = $("#enc").val();
	var role = $("#role").val();
	var countrycode = $("#countrycode").val();
	var refer = $("#refer").val();
	if (realName == "") {
		$("#err-txt").html("请输入真实姓名");
		return;
	}

	/*
	if(!checkRealName(realName)){
		$("#err-txt").html("姓名不符合规范");
		return;
	}*/

	$.ajax({
		url: "/xxt/checkname?realname="+encodeURIComponent(realName)+"&uname="+uname,
		type:"get",
		success: function(data){
			var myjson = eval("("+data+")");
			if(myjson.result){
				var homeUrl = "/v9/realregisterNot302?uname="+uname+"&realname="+encodeURIComponent(realName)+"&password="+password+"&code="+code+"&countrycode="+countrycode;
				$.ajax({
					url: homeUrl,
					type:"post",
					success: function(data){
						refer = util.isEmpty(refer) ? "http://i.mooc.chaoxing.com" : refer;
						window.location = decodeURIComponent(refer);
					},
					error:function(e){
						$("#err-txt").html("注册异常，请联系客服");
						return;
					}
				});
			}else{
				$("#err-txt").html("操作不成功，请修改或稍后再试");
				return;
			}
		}
	});
}



