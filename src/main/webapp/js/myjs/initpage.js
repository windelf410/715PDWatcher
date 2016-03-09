//init height
window.onresize = function() {
	var reHt = $(window).height() - $("#main_content").offset().top
			- $("#bottom2").outerHeight();
	if (reHt > $("#main_content").outerHeight() - 60) {
		$("#main_content").css({
			"height" : reHt - 60 + "px"
		});
	}
	$('#dg').datagrid('resize', {
		width:function(){return 0.7*document.getElementById("main_content").getAttribute("height");},
	});
};
$(function() {
	// init height

	var reHt = $(window).height() - $("#main_content").offset().top
			- $("#bottom2").outerHeight();
	if (reHt > $("#main_content").outerHeight() - 60) {
		$("#main_content").css({
			"height" : reHt - 60 + "px"
		});
	}
	$('#dg').datagrid('resize', {
		width:function(){return 0.7*document.getElementById("main_content").getAttribute("height");},
	});
	
	// init header nav
	$.ajax({
		url : '/shuhuibao/new_net/nav.html #nav',
		type : 'get',
		dataType : 'html',
		async : false,
		success : function(data) {
			$('#header2').html(data);
			$('#header2').html($('#nav').html());
		}
	});

	$("ul[id=nav4]").each(function() {
		$(this).show();
		$(this).attr("nowHeight", $(this).children("li").length * 31 - 1);
		$(this).parent().parent().show();
		$(this).css({
			"height" : "0",
			"left" : ($(this).parent().outerWidth() - $(this).outerWidth()) / 2
		});
		$(this).parent().parent().hide();
	});

	$("ul[id=nav3]").children("li").each(function() {
		$(this).hover(function() {
			$(this).find("#nav4").stop().animate({
				"height" : $(this).find("#nav4").attr("nowHeight") + "px"
			}, 500);
		}, function() {
			$(this).find("#nav4").stop().animate({
				"height" : "0px"
			}, 500);
		});
	});

	$("#nav2").children("li").each(function() {
		$(this).children("a").bind("click", function() {
			$("#nav2").children("li").removeClass("sel");
			$(this).parent().addClass("sel");
			$("ul[id=nav3]").hide();
			$(this).parent().children("#nav3").show();
		});
	});

	$("#nav2").children("li").eq(0).children("a").click();

	// data list init
	$(".data_list .tr2").hover(
			function() {
				$(this).css({
					"background" : "#f3f3f3"
				});
				$(this).find(".default_btn5").removeClass("default_btn5")
						.addClass("default_btn4");
			},
			function() {
				$(this).css({
					"background" : "#ffffff"
				});
				$(this).find(".default_btn4").removeClass("default_btn4")
						.addClass("default_btn5");
			});

	$('#footer').load('/shuhuibao/new_net/nav.html #bottom2');
});