<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>管理页面</title>
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="this is my page">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="css/u_left.css" />

</head>
<body>
	<s:set  value="#session.student" name="student"></s:set>
	<table style="width:100; height:280; border:0; cellpadding:0;cellspacing:0; bgcolor:#EEF2FB " >
		<tr><td>&nbsp;&nbsp;&nbsp;</td><td>&nbsp;&nbsp;&nbsp;</td></tr>
		<tr>
			
			<td width="182" valign="top">
					<h1>
						<a href="booksearch_view"   target="main">图书搜索</a>
					</h1>
					<h1>
						<a href="student_borrowlist"   target="main">借阅信息</a>
					</h1>
					<h1>
						<a href="student_callBack" target="main">催还通知</a>
					</h1>
					<h1 >
						<a href="student_appoint" target="main">预约通知</a>
					</h1>
					<h1 >
						<a href="student_borrowHistory" target="main">借阅历史</a>
					</h1>
					<h1 >
						<a href="student_amerce" target="main">罚款记录</a>
					</h1>
					<h1>
						<a href="student_modifypwd_view" target="main">修改密码</a>
					</h1>
					<td>&nbsp;&nbsp;</td>
			</td>	
		</tr>
	</table>
</body>
</html>
