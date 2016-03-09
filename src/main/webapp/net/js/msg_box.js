$.extend({
	msgbox:function(options) {
		var default_options = {
			titleText:"慧·当家&nbsp;温馨提示",
			msgText:"确定执行该操作？",
			init: function() {},
			onsubmit: function() {},
			oncancel: function() {}
		};
		$.extend(default_options,options);
		//alert(default_options.msgText);
		
		if($.isFunction(default_options.init)){
			default_options.init.apply();
		}
		
		//init
		$("body").append("<div class='msg_box_bg'></div>");
		$(".msg_box_bg").css({"width":$(window).width(),"height":$(document).height()});
		
		
		var html_str = "";
		
       	html_str += '<div id="msg_box">';
       	html_str += '<div class="msg_box_title">';
		html_str += '<div style="width:300px;padding-left:10px;float:left;" class="ellipsis fnt_ffffff_13">'+default_options.titleText+'</div>';
		
		html_str += '<div style="width:20px;padding:4px;float:right;"><a herf="javascript:" id="msg_close_btn"><img src="images/close_btn.png" ></a></div>';
       	html_str += '</div>';
        html_str += '<div style="margin:0 auto;width:300px;line-height:20px;text-align:center;padding:20px 0;" class="fnt_333333_13">';
        html_str += default_options.msgText;
        html_str += '</div>';
        html_str += '<div style="padding:10px 0;">';
        html_str += '<div class="default_btn" style="width:78px;height:23px;line-height:23px;float:right;margin-right:10px">';
        html_str += '<a id="msg_box_submit_btn" href="javascript:" class="fnt_ac6249_13 no-line">确&nbsp;认</a>';
        html_str += '</div>';
        html_str += '<div class="default_btn2" style="width:78px;height:23px;line-height:23px;float:right;margin-right:10px">';
        html_str += '<a id="msg_box_cancel_btn" href="javascript:" class="fnt_666666_13 no-line">取&nbsp;消</a>';
        html_str += '</div>';
		html_str += '<div class="clearfloat" style="height:0px;"></div>';
        html_str += '</div>';
        html_str += '</div>';
		
		$("body").append(html_str);
		$("#msg_box").css({"left":($(window).width() - $("#msg_box").outerWidth())/2,"top":($(window).height() - $("#msg_box").outerHeight())/2});
		
		var doclose = function(){
			$(".msg_box_bg").remove();
			$("#msg_box").remove();
		};
		$("#msg_box #msg_close_btn").bind("click",doclose);
		
		var docancel = function(){
			if($.isFunction(default_options.oncancel)){
				default_options.oncancel.apply();
			}
			
			$(".msg_box_bg").remove();
			$("#msg_box").remove();
		};
		$("#msg_box #msg_box_cancel_btn").bind("click",docancel);
		
		var dosubmit = function(){
			if($.isFunction(default_options.onsubmit)){
				default_options.onsubmit.apply();
			}else{
				alert("未绑定提交方法,执行cancel操作！");
			}
			$(".msg_box_bg").remove();
			$("#msg_box").remove();
		}
		$("#msg_box #msg_box_submit_btn").bind("click",dosubmit);
	}
});