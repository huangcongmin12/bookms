<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>首页</title>
<meta http-equiv=Content-Type content=text/html;charset=UTF-8>
<meta http-equiv="refresh" content="60">
<base target="main">
<link rel="stylesheet" type="text/css" href="../css/skin.css">
</head>
<body leftmargin="0" topmargin="0" onload="showTime()">
	<table width="100%" height="64" border="0" cellpadding="0"
		cellspacing="0" class="admin_topbg">
		<tr>
			<td width="60%" height="64">
				<!--<img src="images/logo.gif" width="262" height="64">-->
			</td>
			<td width="40%" valign="top"><table width="100%" border="0"
					cellspacing="0" cellpadding="0">
					<tr>
						<td width="74%" height="38" class="admin_txt">
							<s:property value="#session.student.studentName"/> 
							&nbsp; &nbsp;您好!&nbsp;&nbsp;<span id="nowTime"></span>
						</td>
						<td width="22%"><a href="#" target="_self"
							onClick="logout();"><img src="../images/out.gif" alt="安全退出系统"
								width="46" height="20" border="0">
						</a>
						</td>
						<td width="4%">&nbsp;</td>
					</tr>
					<tr>
						<td height="19" colspan="3">&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
<script language=JavaScript>
	function logout() {
		if (confirm("您确定要退出吗？"))
			top.location = "loginOut.action";
		return false;
	}
	function showsubmenu(sid) {
		var whichEl = eval("submenu" + sid);
		var menuTitle = eval("menuTitle" + sid);
		if (whichEl.style.display == "none") {
			eval("submenu" + sid + ".style.display=\"\";");
		} else {
			eval("submenu" + sid + ".style.display=\"none\";");
		}
	}
	
	function showTime()
	{
		myTime=new Date();
		var monthArray=new Array("1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月");
		var dayArray =new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六","星期日");
		year=myTime.getFullYear();
		date=myTime.getDate();
		hours=myTime.getHours();
		minutes=myTime.getMinutes();
		seconds=myTime.getSeconds();
		if(minutes<=9)
			minutes="0"+minutes;
		if(seconds<=9)
			seconds="0"+seconds;
		theTime="今天是:&nbsp;"+year+"年"+monthArray[myTime.getMonth()]+date+"日"+"&nbsp;&nbsp;&nbsp;"+dayArray[myTime.getDay()]+
			"&nbsp;&nbsp;&nbsp;当前时间："+hours+":"+minutes+":"+seconds;
		nowTime.innerHTML=theTime;
		setTimeout("showTime()",1000);
	}
</script>
</html>