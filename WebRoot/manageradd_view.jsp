
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>添加管理员</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>  
<link rel="stylesheet" type="text/css" href="css/list.css">
<link href="css/skin.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #EEF2FB;
}
</style>
<script type="text/javascript">  
$(document).ready(function(){  
    $(":submit[name=submit]").click(function(check){  
        var val1 = $(":text[id=name]").val(); 
        var val2 = $(":password[id=pd]").val(); 
        if(val1==""||val1==null){  
            $("#nametip").html("用户名不能为空!");
            $(":text[id=name]").focus();  
            check.preventDefault();//此处阻止提交表单 
        }
        else{
        	$("#nametip").html(""); 
        }  
        if(val2==""||val2==null){  
            $("#pdtip").html("密码不能为空!"); 
            $(":password[id=pd]").focus();  
            check.preventDefault();//此处阻止提交表单  
        }else{
        	$("#pdtip").html(""); 
        }   
    });  
}); 
</script> 
</head>
<body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="17" valign="top" background="images/mail_leftbg.gif"><img
				src="images/left-top-right.gif" width="17" height="29" />
			</td>
			<td valign="top" background="images/content-bg.gif">
				<table width="100%" height="30" border="0" cellpadding="0"
					cellspacing="0" class="left_topbg" id="table2">
					<tr>
						<td height="30"><div class="titlebt">添加管理员
						</td>
					</tr>
				</table></td>
			<td width="16" valign="top" background="images/mail_rightbg.gif"><img
				src="images/nav-right-bg.gif" width="16" height="29" />
			</td>
		</tr>

		<tr>
			<td height="48px" valign="middle" background="images/mail_leftbg.gif"></td>
			<td valign="top" bgcolor="#F7F8F9">
				<table width="99%" height="0" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top">
							<table width="99%"  height="0"border="0" align="center"cellpadding="0" cellspacing="0">
								<tr>
									<td class="left_txt">当前位置》添加管理员</td>
								</tr>
								<tr>
									<td height="20">
										<table width="100%" height="1" border="0"cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
											<tr>
												<td></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
			<td background="images/mail_rightbg.gif">&nbsp;</td>
		</tr>
		
		<tr>
			<td valign="middle" background="images/mail_leftbg.gif">&nbsp;</td>
			<td valign="top" bgcolor="#F7F8F9">
				<div class="list">
					<h3><font size=2>添加管理员</font></h3>
					<form action="manager_add" method="post">
						<font size="2" color="red" ><s:actionmessage/></font>
						<table border="0">
							<tr>
								<td align="right">管理员ID：</td>
								<td align="left">
									<input name="manager.managerID" type="text" size="20"  id="name">
									<font color="red" size="2"><span id="nametip"></span></font>
								</td>
							</tr>
							<tr>
								<td align="right">登录密码：</td>
								<td align="left">
									<input name="manager.password" type="password" size="20" id="pd">
									<font color="red" size="2">&nbsp;&nbsp;<span id="pdtip"></span></font>
								</td>
							</tr>
							<tr>
								<td align="right">权&nbsp;&nbsp;&nbsp;&nbsp;限：</td>
								<td align="left">
									<input type="radio" name="manager.level" value="0" checked="checked"/>普通管理员
	      							<input type="radio" name="manager.level" value="1"/>超级管理员
								</td>
							</tr>
							<tr>
								<td align="right"><input type="submit" name="submit" value="添加"/></td>
								<td align="left">
									<input type="reset" value="重置"/>
								</td>
							</tr>
						</table>
					</form>
				</div>
				</td>
			<td background="images/mail_rightbg.gif">&nbsp;</td>
		</tr>

		<tr>
			<td valign="bottom" background="images/mail_leftbg.gif"><img
				src="images/buttom_left2.gif" width="17" height="17" />
			</td>
			<td background="images/buttom_bgs.gif"><img
				src="images/buttom_bgs.gif" width="17" height="17">
			</td>
			<td valign="bottom" background="images/mail_rightbg.gif"><img
				src="images/buttom_right2.gif" width="16" height="17" />
			</td>
		</tr>
	</table>
</body>
</html>

