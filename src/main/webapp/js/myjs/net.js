/**
 * 
 */
/*动态导入css样式表文件*/
function addSheetFile(path){ 
    var fileref=document.createElement("link"); 
    fileref.rel="stylesheet"; 
    fileref.type="text/css"; 
    fileref.href=path; 
    var headobj=document.getElementsByTagName('head')[0]; 
    headobj.appendChild(fileref); 
} 
/* 新增和修改页面js脚本 */
var delAttUrl;
var dwnAttUrl;
$(function() {
	addSheetFile("../../../../../css/net/loading.css");
	$("#attachment").append("<div style='margin-left:45%;' id='circular' class='marginLeft'><div id='circular_1' class='circular'></div><div id='circular_2' class='circular'></div><div id='circular_3' class='circular'></div><div id='circular_4' class='circular'></div><div id='circular_5' class='circular'></div><div id='circular_6' class='circular'></div><div id='circular_7' class='circular'></div><div id='circular_8' class='circular'></div><div class='clearfix'></div></div>");
	$("#circular").css('display','none');
	noAttachment();
});

function initParam(delUrl, dwnUrl){
	delAttUrl = delUrl;
	dwnAttUrl = dwnUrl;
}
function generateOrder(value, row, index) {
	return index + 1;
}

function reset() {
	$('#data').form('reset');
	$('#attachment').form('clear');
	$('#file input').remove();
	var rows = $('#dg').datagrid('getRows');
	// rows是对表格所用行的引用，但表格动态变化时，rows也随之变化，顾在删除前应保存行数
	var rowCounts = rows.length;
	for (var i = 0; i < rowCounts; ++i) {
		$('#dg').datagrid('deleteRow', 0);
	}

	// 清除所有的错误提示
	$('td [class="errorMsg"]').each(function(index, element) {
		$(element).css('display', 'none');
	});
	cancelCircle();
	noAttachment();
}
		

function noAttachment() {
	$('#dg').datagrid('appendRow', {
		attachedFileName : '暂无附件'
	});
	$("#dg").datagrid('mergeCells', {
		index : 0,
		field : 'attachedFileName',
		colspan : 3,
		type : 'body'
	});
}
function checkInject(){
	var result = true;
	var pattern = new RegExp("[%`~!#$^&*()=|{}':;',\\[\\]<>/?~]");       //格式 RegExp("[在中间定义特殊过滤字符]")
	$("#data :input").each(function(index,element){
		if(pattern.test($(element).val())){
			result = false;
			return false;
		}
	});
	return result;
}
function submit(baseUrl, attUrl, id, value){
	//清除所有的错误提示
	$('td [class="errorMsg"]').each(function(index,element){
		$(element).css('display', 'none');
	});
	
	if(!validate())
		return ;
	 if(!checkInject())
		 return;
	$('#data').form('submit',{
		url: baseUrl,
		onSubmit:function(param){
			$("#circular").css('display','block');
			var myButton=document.getElementsByTagName("input");
			var btnLength=myButton.length;
			myButton[btnLength-3].disabled=true;
			myButton[btnLength-2].disabled=true;
			myButton[btnLength-1].disabled=true;
			myButton[btnLength-3].className="disStyle";
			myButton[btnLength-2].className="disStyle";
			myButton[btnLength-1].className="disStyle";
			try {
				var str = "param." + id + ' = ' + value;
				eval(str);
			} catch (err) {
				return false;
			}
		},
		success: function(data){
			var code = JSON.parse(data).code;
			var files = 0;
			try {
				files = $('#file').children('input').length;
			} catch (err) {
				files = 0; //没有附件时默认为0
			}
			if(code == "1" && files != 0){
				try{
					var id = (value == null ? parseInt(JSON.parse(data).desc) : value);//修改成功后，不返回修改项的id
					$('#id').val(id);
					$('#attachment').form('submit',{
						url: attUrl,
						//测试上传文件用代码
						success: function(data){
							if(JSON.parse(data).code == 1){
								//alert("完成");
								/*if(confirm("保存数据和附件成功，继续添加？")){
									addAgain();
								}else{
									history.back(-1);
								}*/
								alert('保存数据和附件成功');
								history.back(-1);
							}else if(JSON.parse(data).code == 2){
								alert(JSON.parse(data).desc);
							}
							cancelCircle();	
						}
					});
				}catch(err){
					alert('错误01-' + err);
					cancelCircle();	
				}
			}else if(code == "1" && files == 0){
				/*if(confirm("保存数据成功，继续添加？")){
					addAgain();
				}else{
					history.back(-1);
				}*/
				alert('保存数据成功');
				history.back(-1);
			}else if(code == "3"){
				//alert(3);
			}
		}		
	});
}
function addAgain(){
	var urlArray = window.location.toString().split('/');
	var href = urlArray[urlArray.length - 1];
	window.location = href.replace('edit', 'add');
}
function cancelCircle(){
	$("#circular").css('display','none');
	var myButton=document.getElementsByTagName("input");
	var btnLength=myButton.length;
	myButton[btnLength-3].disabled=false;
	myButton[btnLength-2].disabled=false;
	myButton[btnLength-1].disabled=false;
	myButton[btnLength-3].className="default_btn6 btn_height";
	myButton[btnLength-2].className="default_btn5 btn_height";
	myButton[btnLength-1].className="default_btn5 btn_height";
}

function upload() {
	var fileOrder = generateFileOrder();

	//当前id存在时删除重新添加（存在的原因是上次点击了上传控件，但没有选择文件） 
	var e = document.getElementById('uploadFile' + fileOrder);
	if (e != null) {
		$('#uploadFile' + fileOrder).remove();
		//alert('元素存在需要被删除!');
	}
	var input = '<input type="file" id="uploadFile' + fileOrder
			+ '" name="uploadFile' + fileOrder + '" onchange="change(this, ' + fileOrder + ');">';
	$(input).appendTo('#file');
	$('#uploadFile' + fileOrder).click();
}

function change(element, index) {
	if(/safebox.html/i.test(window.location) && !checkFileSize(element)){
		alert('文件大小不能超过10M');
		return;
	}
	var path = $(element).val().split('\\');
	var name = path[path.length - 1];
	var time = new Date();
	/*var cTime = time.getFullYear().toString();
	cTime += ("/" + (time.getMonth() + 1).toString());
	cTime += ("/" + time.getDate().toString());
	cTime += (" " + time.getHours());
	cTime += (":" + time.getMinutes());
	cTime += (":" + time.getSeconds());*/
	var rows = $("#dg").datagrid('getRows');
	if (rows.length == 1 && rows[0].attachedFileName == '暂无附件') {
		$('#dg').datagrid('deleteRow', 0);
	}
	$("#dg").datagrid('appendRow', {
		attachedFileName : name,
		uploadDate : time.format('yyyy-MM-dd hh:mm:ss')
	});
	//禁用下载按钮
	$('#view' + index).attr('disabled', 'disabled').css('wait', 'auto');
	/* $('#file input').each(function(index, element) {
		alert("index" + index + ": " + $(element).val());
	}); */
}

function checkFileSize(target, size){
	var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
	var fileSize = 0;
	if( isIE && !target.files){
		var filePath = target.value;
		var fileSystem = new ActiveXObject('Scripting.FileSystemObject');
		var file = fileSystem.GetFile(filePath);
		fileSize = file.Size;
	}else{
		fileSize = target.files[0].size;
	}
	if(fileSize > size * 1024 * 1024)
		return false;
	return true;
}
function generateFileOrder() {
	var rows = $('#dg').datagrid('getRows');
	if (rows.length == 1 && rows[0].attachedFileName == '暂无附件')
		return 0;
	return rows.length;
}

function operate(value, row, index) {
	var attID = row.attachment_ID;
	var fileName = row.attachedFileName;
	/*
	 * var viewBtn = "<input type=\"button\" id=\"view" + index + "\"
	 * value=\"下载\" class=\"default_btn5\" onclick=\"downloadFile(" + attID +
	 * "," + fileName + ")\">";
	 */
	var viewBtn = "<a href=\""
			+ dwnAttUrl
			+ "?id="
			+ attID
			+ "\" class=\"default_btn5 no-line\" style=\"width:26px;display:inline-block\" id=\"view"
			+ index + "\" download=\""
			+ fileName + "\">下载</a>";
	var delBtn = "<input type=\"button\" id=\"del"
			+ index
			+ "\" value=\"删除\" class=\"default_btn5\" style=\"display:inline-block\" onclick=\"deleteFile(this.id, "
			+ attID + " )\">";
	return viewBtn + ' ' + delBtn;
}

function deleteFile(id, attID) {
	var rows = $('#dg').datagrid('getRows').length;
	var index = parseInt(id.match(/\d+/g));
	if(confirm('删除后不可恢复，确认删除吗？')){
		//如果id不存在，表示是从服务器中读取的，删除时应删除服务器中文件
		var ex = document.getElementById('uploadFile' + index) == null ;
		if(ex){
			$.ajax({
				url: delAttUrl,
				type: 'get',
				data: {
					ids: attID
				},
				dataType: 'json'/*,
				success: function(data){
					if(data.code == 1)
						alert(data.desc);
				}*/
			});
		}
		//删除一行
		$('#dg').datagrid('deleteRow', index);
		//删除对应的input标签
		$('#uploadFile' + index).remove();
		if (rows == 1) {
			noAttachment();
		}
	}
}
function downloadFile(id){
	var aLink = document.createElement('a');
    var evt = document.createEvent("HTMLEvents");
    evt.initEvent("click", false, false);//initEvent 不加后两个参数在FF下会报错, 感谢 Barret Lee 的反馈
    //aLink.download = fileName;
    aLink.href = dwnAttUrl + "?id=" + id;
    aLink.dispatchEvent(evt);
    
    //window.location = dwnAttUrl + "?id=" + id;
}
function loadAttachment(url){
	$('#dg').datagrid({
		url: url,
		method: 'post',
		onBeforeLoad: function(){
			console.log("附件的外键是：" + id);
		},
		queryParams: {
			id: id
		} ,
		onLoadSuccess: function(data){
			try{
				if(data.total == 0)
					noAttachment();
			}catch(err){
				alert('错误04-加载附件出错\n' + err);
			}
		}
	});
}

function checkFile(target, size){
	if(!checkFileSize(target, size))
		return 1; //文件大小不正确 
	if(!checkFileExtName(target))
		return 2; //文件类型不正确
	return 0; //文件大小和文件类型都正确
}
function checkFileExtName(target){
	//console.log(target.files);
	var type = target.files[0].type;
	if(type == 'image/jpeg' 
		|| type == 'image/gif' 
		|| type == 'image/png')
		return true;
	else
		return false;
}