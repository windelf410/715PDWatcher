<%@ page language="java" contentType="text/html; charset=GBK"%>
<HTML>
	<HEAD>
		<META NAME="GENERATOR" Content="Microsoft Visual Studio 6.0">
		<TITLE> </TITLE>
		<style type="text/css">
        <!--
        body {
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
		overflow:hidden;
	}
		.STYLE1 {
			font-size: 12px;
			color: #a1c8c6;
		}
		.STYLE4 {color: #FFFFFF; font-size: 12px; }
		-->
		</style>
	  

	
		<script language="JavaScript" type="text/JavaScript">
			<!--
			function MM_preloadImages() { //v3.0
			  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
			    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
			    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
			}
			
			function MM_swapImgRestore() { //v3.0
			  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
			}
			
			function MM_findObj(n, d) { //v4.01
			  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
			    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
			  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
			  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
			  if(!x && d.getElementById) x=d.getElementById(n); return x;
			}
			
			function MM_swapImage() { //v3.0
			  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
			   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
			}
			MM_preloadImages('resource/images/login/login_over.jpg')
	//-->
		
			
	</script>
	<script language="JavaScript" type="text/JavaScript">
	 function doread(){
		 var oInput = document.getElementById("loginID");
		 window.event.returnValue = false;
	     oInput.focus();
		 var cont = window.location.href;
		 var a = cont.indexOf("=");
		 if(a!=-1){
			 var num = cont.substring(a+1,cont.length);
			 if(num==1){  
				 alert("用户不存在")
			 }else if (num==2){
				 alert("密码错误!")
			 }else if (num==3){
				 alert("已登录!")
			 }
		 }
		
	 }

	</script>
	</
	</HEAD>
	<BODY topmargin=0 rightmargin=0 leftmargin=0 bottommargin=0
		 onload="doread();">
	<form method=post action="rest/app/user/login">
			<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td bgcolor="035551">&nbsp;</td>
  </tr>
  <tr>
    <td height="311" background="img/login_03.gif"><table width="758" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="210" background="img/login11.jpg">&nbsp;</td>
      </tr>
      <tr>
        <td height="101"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="446" height="101" background="img/login_06.gif">&nbsp;</td>
            <td width="156"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="29%" height="22"><span class="STYLE4">用&nbsp;&nbsp;&nbsp;&nbsp;户:</span></td>
                <td width="71" height="22"><input type="text" id=loginID name=loginID class=input style="width:100px; height:16px; border:solid 1px #000000; color:#666666"></td>
              </tr>
              <tr>
                <td height="22"><span class="STYLE4">密&nbsp;&nbsp;&nbsp;&nbsp;码：</span></td>
                <td height="22"><input type="password" class=input id=password name=password style="width:100px; height:16px; border:solid 1px #000000; color:#666666"></td>
              </tr>
              <tr>
                <td height="25">&nbsp;</td>
                <td height="25"><input id=imglogin type="image" src="img/dl.gif" width="37" height="19" border="0" onMouseOver="MM_swapImage('imglogin','','img/dl.gif',1)"
																						onMouseOut="MM_swapImgRestore()" border="0"></a> <img src="img/qx.gif" width="37" height="19"></td>
                </tr>
            </table></td>
            <td width="156" background="img/login_09.gif">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td bgcolor="1f7d78">&nbsp;</td>
  </tr>
  <tr>
    <td bgcolor="1f7d78"><div align="center"><span class="STYLE1">@ 成都宏明电子科大新材料有限公司 	 ALL Right	s reserved </span></div></td>
  </tr>
</table>
		</form>
		<script language="JavaScript" type="text/JavaScript">
			<!--
			function focusid()
			{
				document.forms[0].id.focus();
			}
			function closeSelf(){         
				if(window.opener==null) window.opener=null;
				window.close();
			}
		//-->
		</script>
		<c:if test="${user!=null}">
			<c:redirect url="userDefinedAction.do?method=main" />
		</c:if>
	</BODY>
     <script language="JavaScript" type="text/JavaScript">
		<!--	

		function openepage(url,width,height)
		{
		    var url=url;
			var iWidth=width;
			var iHeight=height;
			var iTop = (window.screen.availHeight-30-iHeight)/2;
			var iLeft = (window.screen.availWidth-10-iWidth)/2;
			window.open(url,'','height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
		}
		
		function oahelp()
		{
          alert("暂未提供");
		}
		
		//-->
	</script>	
</HTML>
