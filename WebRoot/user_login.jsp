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

<title>读者登录</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/skin.css">
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(":submit[name=submit]").click(function(check) {
			var val1 = $(":text[id=name]").val();
			var val2 = $(":password[id=pd]").val();
			var val3 = $(":text[id=code]").val();
			if (val1 == "") {
				$("#nametip").html("读者条码不能为空!");
				$(":text[id=name]").focus();
				check.preventDefault();//此处阻止提交表单 
			} else {
				$("#nametip").html("");
			}
			if (val2 == "") {
				$("#pdtip").html("登录密码不能为空!");
				$(":password[id=pd]").focus();
				check.preventDefault();//此处阻止提交表单  
			} else {
				$("#pdtip").html("");
			}
			if (val3 == "") {
				$("#codetip").html("验证不能为空!");
				$(":text[id=code]").focus();
				check.preventDefault();//此处阻止提交表单  
			} else {
				$("#codetip").html("");
			}
		});
	});
	function changeValidateCode(obj) {
		//获取当前的时间作为参数，无具体意义  
		var timenow = new Date().getTime();
		//每次请求需要一个不同的参数，否则可能会返回同样的验证码  
		//这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。  
		obj.src = "rand.action?d=" + timenow;
	}
</script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #1D3647;
}
-->
</style>
</head>

<body>
	<table width="100%" height="166" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td height="42" valign="top"><table width="100%" height="42"
					border="0" cellpadding="0" cellspacing="0" class="login_top_bg">
					<tr>
						<td width="1%" height="21">&nbsp;</td>
						<td height="42">&nbsp;</td>
						<td width="17%">&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td valign="top"><table width="100%" height="532" border="0"
					cellpadding="0" cellspacing="0" class="login_bg">
					<tr>
						<td width="49%" align="right"><table width="91%" height="532"
								border="0" cellpadding="0" cellspacing="0" class="login_bg2">
								<tr>
									<td height="138" valign="top"><table width="89%"
											height="427" border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td height="149">&nbsp;</td>
											</tr>
											<tr>
												<td height="80" align="right" valign="top">
													<img src="images/logo.png" width="279" height="68">
												</td>
											</tr>
											<tr>
												<td height="198" align="right" valign="top"><table
														width="100%" border="0" cellpadding="0" cellspacing="0">
														<tr>
															<td width="35%">&nbsp;</td>
															<td height="25" colspan="2" class="left_txt">
															</td>
														</tr>
														<tr>
															<td>&nbsp;</td>
															<td height="25" colspan="2" class="left_txt">
															</td>
														</tr>
														<tr>
															<td>&nbsp;</td>
															<td height="25" colspan="2" class="left_txt">
															</td>
														</tr>
														<tr>
															<td>&nbsp;</td>
															<td width="30%" height="40">
															</td>
															<td width="35%">
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>

							</table>
						</td>
						<td width="1%">&nbsp;</td>
						<td width="50%" valign="bottom"><table width="100%"
								height="59" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="4%">&nbsp;</td>
									<td width="96%" height="38">
										<h3>
											<span class="login_txt_bt">读者登录 </span>
										</h3> 
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td height="21"><table cellSpacing="0" cellPadding="0"
											width="100%" border="0" id="table211" height="328">
											<tr>
												<td height="164" colspan="2" align="middle">
													<form name="myform" action="studentLogin"
														method="post">
														<table cellSpacing="0" cellPadding="0" width="100%"
															border="0" height="143" id="table212">
															<tr>
																<td colspan="2"><font size="2" color="red"><s:actionmessage/>
																</font></td>
															</tr>
															<tr>
																<td width="13%" height="38" class="top_hui_text"><span
																	class="login_txt">读者条码：&nbsp;&nbsp; </span>
																</td>
																<td height="38" colspan="2" class="top_hui_text"><input
																	name="student.studentNumber" class="editbox4" id="name"
																	value="" size="20"> <font color="red" size="2"><span
																		id="nametip"></span> </font>
																</td>
															</tr>
															<tr>
																<td width="13%" height="35" class="top_hui_text"><span
																	class="login_txt"> 登录密码： &nbsp;&nbsp; </span>
																</td>
																<td height="35" colspan="2" class="top_hui_text"><input
																	class="editbox4" type="password" size="20" id="pd"
																	name="student.password"> <img
																	src="images/luck.gif" width="19" height="18"> <font
																	color="red" size="2"><span id="pdtip"></span> </font>
																</td>
															</tr>
															<tr>
																<td width="13%" height="35"><span class="login_txt">验证码：</span>
																</td>
																<td height="35" colspan="2" class="top_hui_text">
																	<input class=wenbenkuang name="verifycode" type=text id="code" maxLength=4 size=10> 
																	<img src="rand.action" onclick="changeValidateCode(this)" />
																	<font color="red" size="2"><span id="codetip"></span> </font>
																</td>
															</tr>
															<tr>
																<td height="35">&nbsp;</td>
																<td width="20%" height="35"><input name="submit"
																	type="submit" class="button" id="Submit" value="登 陆">
																</td>
																<td width="67%" class="top_hui_text"><input
																	name="cs" type="button" class="button" id="cs"
																	value="取 消">
																</td>
															</tr>
															<tr>
																<td height="40">&nbsp;</td>
																<td width="20%" height="40">
																	&nbsp;
																</td>
																<td width="67%" class="top_hui_text">
																	&nbsp;
																</td>
															</tr>
															<tr>
																<td height="18">&nbsp;</td>
																<td clospan="2">
																	  <font size=1><a href="user_login">读者登录</a>&nbsp;|&nbsp;<a href="manager_login">管理员登录</a></font>
																</td>
															</tr>
														</table>
														<br>
													</form>
												</td>
											</tr>
											<tr>
												<td width="433" height="164" align="right" valign="bottom"><img
													src="images/login-wel.gif" width="242" height="138">
												</td>
												<td width="57" align="right" valign="bottom">&nbsp;</td>
											</tr>
										</table>
									</td>
								</tr>
							</table></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="20"><table width="100%" border="0" cellspacing="0"
					cellpadding="0" class="login-buttom-bg">
					<tr>
						<td align="center"><span class="login-buttom-txt">Copyright
								&copy; 2012-2013 版权所有</span>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
