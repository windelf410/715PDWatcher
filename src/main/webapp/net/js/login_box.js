$.extend({
	openLoginBox:function(){
		
		$("body").append("<div class='login_box_bg'></div>");
		$(".login_box_bg").css({"width":$(window).width(),"height":$(document).height()});
		$(".login_box_bg").bind("click",function(){
			$(".login_box_bg").remove();
			$("#login_box").remove();
		});
		
		//
		var login_str = "";
		login_str += '<div id="login_box" style="position:fixed;z-index:2000;left:0;top:0;">';
		login_str += '<div id="login_div">';
		login_str += '<div class="title">';
		login_str += '<div style="margin-left:44px;float:left;" class="fnt_ac6249_16">登录</div>';
		login_str += '<div id="err_msg" style="margin-right:30px;float:right;display:none;" class="fnt_fd0000_13">用户名或者密码错误！</div>';
		login_str += '</div>';
		login_str += '<div style="padding:20px 0 0 44px;">';
		login_str += '<div class="login_input_div">';
		login_str += '<div style="float:left"><img src="images/login_icon_1.png" /></div>';
		login_str += '<div style="float:left" class="line"></div>';
		login_str += '<div style="float:left"><input type="text" placeholder="请输入您的账号" class="login_input fnt_666666_13" style="width:240px"></div>';
		login_str += '</div>';
		login_str += '<div class="login_input_div" style="margin-top:10px;">';
		login_str += '<div style="float:left"><img src="images/login_icon_2.png" /></div>';
		login_str += '<div style="float:left" class="line"></div>';
		login_str += '<div style="float:left"><input type="text" placeholder="请输入您的密码" class="login_input fnt_666666_13" style="width:240px"></div>';
		login_str += '</div>';
		login_str += '<div class="login_input_div" style="margin-top:10px;">';
		login_str += '<div style="float:left"><img src="images/login_icon_3.png" /></div>';
		login_str += '<div style="float:left" class="line"></div>';
		login_str += '<div style="float:left"><input type="text" placeholder="请输入验证码" class="login_input fnt_666666_13" style="width:130px"></div>';
		login_str += '<div style="float:right;margin-top:-4px;width:107px;height:39px;">';
		login_str += '<a href="#"><img src="images/rcode.png" width="107" height="39" title="看不清楚？换一张！" /></a>';
		login_str += '</div>';
		login_str += '<div class="line2" style="float:right;margin-top:-4px;"></div>';
		login_str += '</div>';
		login_str += '<div style="margin-top:25px;line-height:41px;">';
		login_str += '<div style="float:left;width:140px;">';
		login_str += '<a href="#" class="fnt_999999_13 non-line">忘记密码？</a>';
		login_str += '<a href="#" class="fnt_ac6249_13 non-line">免费注册</a>';
		login_str += '</div>';
		login_str += '<div style="float:left;margin-left:10px">';
		login_str += '<div class="default_btn" style="width:164px;height:41px;line-height:48px;"><a href="#" class="fnt_ac6249_16 no-line">登&nbsp;&nbsp;录</a></div>';
		login_str += '</div>';
		login_str += '</div>';
		login_str += '</div>';
		login_str += '</div>';
		login_str += '</div>';
		
		$("body").append(login_str);
		$("#login_box").css({"left":($(window).width() - $("#login_box").outerWidth())/2,"top":($(window).height() - $("#login_box").outerHeight())/2});
	}
});