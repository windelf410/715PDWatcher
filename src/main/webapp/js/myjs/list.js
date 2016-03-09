/* 主体信息页面js脚本 */
var dataArray = new Array();
var deleteUrl;
var editUrl;
function initUrl(delUrl, edUrl){
	deleteUrl = delUrl;
	editUrl = edUrl;
}

/**
2  * 设置未来(全局)的AJAX请求默认选项
3  * 主要设置了AJAX请求遇到Session过期的情况
4  */
$.ajaxSetup({
    type: 'POST',
   complete: function(xhr,status) {
      var sessionStatus = xhr.getResponseHeader('sessionstatus');
       if(sessionStatus == 'timeout') {
         var top = getTopWinow();
           //var yes = confirm('对不起，请登录');
           
         // if (yes) {
               top.location.href = '/shuhuibao/net/index.html';            
       //  }
       }
   }
});

/**
20  * 在页面中任何嵌套层次的窗口中获取顶层窗口
21  * @return 当前页面的顶层窗口对象
22  */
function getTopWinow(){
   var p = window;
   while(p != p.parent){
      p = p.parent;
      }
    return p;
}

function acquireType(queryUrls) {
	//console.log("askURL:"+queryUrls);
	
	var urlArray = queryUrls.split('&');
	if(dataArray.length == 0)
		for(index in urlArray){
			$.ajax({
				url : urlArray[index],
				type : 'post',
				dataType : 'json',
				success : function(data) {
					dataArray = dataArray.concat(data);
				},
				error : function(msg) {
				}
			});
		}
}

function query() {
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var type = $("#select").combobox("getValue");
	
	/*王继茹  注释掉  if (startTime.length == 0 && endTime.length == 0 && type.length == 0)
		return;*/
	if (startTime.length != 0 && endTime.length != 0 && startTime > endTime) {
		$("#reminder").css("display", "inline");
		return;
	}
	$("#reminder").css("display", "none");
	
	$("#dg").datagrid('load', {
		type : type,
		startTime : startTime,
		endTime : endTime
	});
}
function queryWithField(field){
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var value;
	if(field != null)
		value = $("#" + field).textbox("getValue");
	
	/*王继茹 注释掉if (startTime.length == 0 && endTime.length == 0 && value.length == 0)
		return;*/
	if (startTime.length != 0 && endTime.length != 0 && startTime > endTime) {
		$("#reminder").css("display", "inline");
		return;
	}
	$("#reminder").css("display", "none");
	
	if(field == null){
		$('#dg').datagrid('load', {
			startTime: startTime,
			endTime: endTime
		});
	}else if(field == 'member'){
		$("#dg").datagrid('load', {
			member : value,
			startTime : startTime,
			endTime : endTime
		});
	}else if(field == 'title'){
		$("#dg").datagrid('load', {
			title : value,
			startTime : startTime,
			endTime : endTime
		});
	}
}
function lines_bottom(){
	 $('#dg').datagrid('getPanel').removeClass('lines-both lines-no lines-right lines-bottom').addClass('lines-bottom');
}

function getTypeName(value, row, index) {
	for (var i = 0; i < dataArray.length; ++i){
		if (value == dataArray[i].type_ID)
			return dataArray[i].typeName;
	}
}
function generateOrder(value, row, index) {
	return index + 1;
}

function operate(vlaue, row, index){
	var editButton = '<input id="edit' + index 
					+ '" type="button" class="default_btn5 btn_height" value="修改"'
					+ ' onmouseover="over(this)" onmouseout="out(this)" onclick="editRow(this)">';
	var deleteButton = '<input id="delete' + index 
					+ '" type="button" class="default_btn5 btn_height" value="删除"'
					+ ' onmouseover="over(this)" onmouseout="out(this)" onclick="deleteRow(this)">';
	
	return editButton + ' ' + deleteButton;
}

function over(element){
	$(element).removeClass('default_btn5').addClass('default_btn6');
}
function out(element){
	$(element).removeClass('default_btn6').addClass('default_btn5');
}
function deleteRow(element) {
	var index = element.id.match(/\d+/g).toString();
	var value;
	eval("value = $('#dg').datagrid('getRows')[index]." + $('#idName').val());
	if (index.length != 0) {
		index = parseInt(index);
		if (index != NaN)
			$.ajax({
				url : deleteUrl,
				type : 'post',
				dataType : 'json',
				data : {
					ids : value
				},
				success : function(data) {
					if (data.code == 1) {
						alert('删除成功');
						$('#dg').datagrid('reload');
					} else
						alert('删除失败');
				},
				error : function(errMsg) {
				}
			});
	}
}
function editRow(element){
	var index = element.id.match(/\d+/g).toString();
	var row = $('#dg').datagrid('getRows')[index];		
	var content = JSON.stringify(row);
	window.location = encodeURI(editUrl + "?content=" + content);
}
function reload(){
	$('#dg').datagrid('load');
}

function breviary(value, row, index){
	if( value != undefined && value.length != 0 && value.length > 15){
		return value.slice(0, 15) + '...';
	}
	return value;
}
