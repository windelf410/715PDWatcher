$.extend({
	loadingbox:function(options) {
		var default_options = {
			titleText:"慧·当家&nbsp;温馨提示",
			msgText:"操作进行中，请稍等...",
			init: function() {},
			onsubmit: function() {},
			doclose:true
		};
		$.extend(default_options,options);
		//alert(default_options.msgText);
		
		if($.isFunction(default_options.init)){
			default_options.init.apply();
		}
		
		//init
		$("body").append("<div class='loading_box_bg'></div>");
		$(".loading_box_bg").css({"width":$(window).width(),"height":$(document).height()});
		
		
		var html_str = "";
		
       	html_str += '<div id="loading_box">';
		html_str += '<div class="loading_box_title">';
		html_str += '<div style="width:300px;padding-left:10px;float:left;" class="ellipsis fnt_ffffff_13">'+default_options.titleText+'</div>';
       	html_str += '</div>';
        html_str += '<div style="margin:0 auto;width:300px;line-height:20px;text-align:center;padding:20px 0;" class="fnt_333333_13">';
        html_str += '<img src="images/loading.gif" style="vertical-align:middle;"/>&nbsp;&nbsp;';
        html_str += default_options.msgText;
        html_str += '</div>';
        html_str += '</div>';
		
		$("body").append(html_str);
		$("#loading_box").css({"left":($(window).width() - $("#loading_box").outerWidth())/2,"top":($(window).height() - $("#loading_box").outerHeight())/2});
		
		if($.isFunction(default_options.onsubmit)){
			default_options.onsubmit.apply();
		}else{
			alert("未绑定提交方法,执行cancel操作！");
		}
		
		if(default_options.doclose){
			$(".loading_box_bg").remove();
			$("#loading_box").remove();
		}
		
	}
});