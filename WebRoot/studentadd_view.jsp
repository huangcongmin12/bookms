
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

<title>添加学生</title>

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
        var val4 = $(":text[id=t4]").val(); 
        var val5 = $(":text[id=t5]").val(); 
        var val6 = $(":text[id=t6]").val(); 
        var val7 = $(":text[id=t7]").val(); 
        if(val1==""){  
            $("#tip1").html("学号不能为空!");
            $(":text[id=t1]").focus();  
            check.preventDefault();//此处阻止提交表单 
        }
        else{
        	$("#tip1").html(""); 
        }  
        if(val2==""){  
            $("#tip2").html("登录密码不能为空!");
            $(":text[id=t2]").focus();  
            check.preventDefault();//此处阻止提交表单 
        }
        else{
        	$("#tip2").html(""); 
        }  
        if(val3==""){  
            $("#tip3").html("姓名不能为空!");
            $(":text[id=t3]").focus();  
            check.preventDefault();//此处阻止提交表单 
        }
        else{
        	$("#tip3").html(""); 
        }  
        if(val4==""){  
            $("#tip4").html("性别不能为空!");
            $(":text[id=t4]").focus();  
            check.preventDefault();//此处阻止提交表单 
        }
        else{
        	$("#tip4").html(""); 
        }  
        if(val5==""){  
            $("#tip5").html("年龄不能为空!");
            $(":text[id=t5]").focus();  
            check.preventDefault();//此处阻止提交表单 
        }
        else{
        	$("#tip5").html(""); 
        }  
        if(val6==""){  
            $("#tip6").html("所在系别不能为空!");
            $(":text[id=t6]").focus();  
            check.preventDefault();//此处阻止提交表单 
        }
        else{
        	$("#tip6").html(""); 
        }  
        if(val7==""){  
            $("#tip7").html("所在班级不能为空!");
            $(":text[id=t7]").focus();  
            check.preventDefault();//此处阻止提交表单 
        }
        else{
        	$("#tip7").html(""); 
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
						<td height="30"><div class="titlebt">添加学生
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
									<td class="left_txt">当前位置》添加学生</td>
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
					<h3><font size=2>添加学生</font></h3>
					<form action="student_add" method="post">
						<font size="2" color="red" ><s:actionmessage/></font>
						<table border="0">
							<tr>
								<td align="right">学号：</td>
								<td align="left">
									<input name="student.studentNumber" type="text" size="20"  id="t1">
									<font color="red" size="2"><span id="tip1"></span></font>
								</td>
							</tr>
							<tr>
								<td align="right">登录密码：</td>
								<td align="left">
									<input name="student.password" type="text" size="20"  id="t2">
									<font color="red" size="2"><span id="tip2"></span></font>
								</td>
							</tr>
							<tr>
								<td align="right">姓名：</td>
								<td align="left">
									<input name="student.studentName" type="text" size="20"  id="t3">
									<font color="red" size="2"><span id="tip3"></span></font>
								</td>
							</tr>
							<tr>
								<td align="right">性别：</td>
								<td align="left">
									<input name="student.studentSex" type="text" size="20"  id="t4">
									<font color="red" size="2"><span id="tip4"></span></font>
								</td>
							</tr>
							<tr>
								<td align="right">年龄：</td>
								<td align="left">
									<input name="student.studentAge" type="text" size="20"  id="t5">
									<font color="red" size="2"><span id="tip5"></span></font>
								</td>
							</tr>
							<tr>
								<td align="right">所在班级：</td>
								<td align="left">
									<input name="student.studentClass" type="text" size="20"  id="t6">
									<font color="red" size="2"><span id="tip6"></span></font>
								</td>
							</tr>
							<tr>
								<td align="right">所在系别：</td>
								<td align="left">
									<input name="student.department" type="text" size="20"  id="t7">
									<font color="red" size="2"><span id="tip7"></span></font>
								</td>
							</tr>
							<tr>
								<td align="right">借书权限：</td>
								<td align="left">
									<input name="student.permitted" type="radio" size="20" value="1" checked="checked">有 
									<input name="student.permitted" type="radio" size="20" value="0" >无 
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

