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

<title>预约更新</title>

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
        var val1 = $(":text[id=t1]").val(); 
        var val2 = $(":text[id=t2]").val(); 
        var val3 = $(":text[id=t3]").val(); 
        if(val1==""){  
            $("#tip1").html("预约学生学号不能为空!");
            $(":text[id=t1]").focus();  
            check.preventDefault();//此处阻止提交表单 
        }
        else{
        	$("#tip1").html(""); 
        }  
        if(val2==""){  
            $("#tip2").html("预约图书编号不能为空!");
            $(":text[id=t2]").focus();  
            check.preventDefault();//此处阻止提交表单 
        }
        else{
        	$("#tip2").html(""); 
        }  
        if(val3==""){  
            $("#tip3").html("预约截止时间不能为空!");
            $(":text[id=t3]").focus();  
            check.preventDefault();//此处阻止提交表单 
        }
        else{
        	$("#tip3").html(""); 
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
						<td height="30"><div class="titlebt">预约更新</div>
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
									<td class="left_txt">当前位置》预约更新</td>
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
					<h3><font size=2>预约更新</font></h3>
					<s:set  value="#request.appointment" name="appointment"></s:set>
					<font size="2" color="red" ><s:actionmessage/></font>
    				<form action="appointment_update" method="post">
    					<input type="hidden" name="appointment.id" value="<s:property value="#appointment.id"/>" readonly/>
						<table border="0">
							<tr>
								<td align="right">预约学生学号：</td>
								<td align="left">
									<input name="appointment.student.studentNumber" type="text" size="20"  id="t1"  value="<s:property value="#appointment.student.studentNumber"/>">
									<font color="red" size="2"><span id="tip1"></span></font>
								</td>
							</tr>
							<tr>
								<td align="right">预约图书编号：</td>
								<td align="left">
									<input name="appointment.book.bookNumber" type="text" size="20"  id="t2" value="<s:property value="#appointment.book.bookNumber"/>">
									<font color="red" size="2"><span id="tip2"></span></font>
								</td>
							</tr>
							<tr>
								<td align="right">预约截止时间：</td>
								<td align="left">
									<input name="appointment.endTime" type="text" size="20"  id="t3" value="<s:property value="#appointment.endTime"/>">
									<font color="red" size="2"><span id="tip3"></span></font>
								</td>
							</tr>
							<tr>
								<td align="right"><input type="submit" name="submit" value="保存"/></td>
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

