<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>管理页面</title>

<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="this is my page">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="css/left.css" />

<script src="js/prototype.lite.js" type="text/javascript"></script>
<script src="js/moo.fx.js" type="text/javascript"></script>
<script src="js/moo.fx.pack.js" type="text/javascript"></script>
</head>
<body>
	<s:set name="manager"  value="#session.manager"></s:set>
	<table style="width:100; height:280; border:0; cellpadding:0;cellspacing:0; bgcolor:EEF2FB" >
		<tr>
			<td width="182" valign="top"><div id="container">
					<h1 class="type">
						<a href="javascript:void(0)">图书管理</a>
					</h1>
					<div class="content">
						<ul class="MM">
							<li><a href="book_list" target="main">图书列表</a>
							</li>
							<li><a href="admin/bookadd_view" target="main">添加图书</a>
							</li>
							<li><a href="admin/bookfind_view" target="main">精确查询</a>
							</li>
							<li><a href="admin/booksearch_view" target="main">模糊查询</a>
							</li>
						</ul>
					</div>
					<h1 class="type">
						<a href="javascript:void(0)">学生管理</a>
					</h1>
					<div class="content">
						<ul class="MM">
							<li><a href="student_list" target="main">学生列表</a>
							</li>
							<li><a href="admin/studentadd_view" target="main">添加学生</a>
							</li>
							<li><a href="admin/studentfind_view" target="main">学生查询</a>
							</li>
						</ul>
					</div>
					<h1 class="type">
						<a href="javascript:void(0)">借阅管理</a>
					</h1>
					<div class="content">
						<ul class="MM">
							<li><a href="borrow_list" target="main">借阅列表</a>
							</li>
							<li><a href="admin/borrowadd_view" target="main">新增借阅</a>
							</li>
							<li><a href="admin/borrowback_view" target="main">归还图书</a>
							</li>
							<li><a href="admin/borrowlose_view" target="main">借阅挂失</a>
							</li>
							<li><a href="admin/borrowfind_view" target="main">借阅查询</a>
							</li>
						</ul>
					</div>
					<h1 class="type">
						<a href="javascript:void(0)">预约管理</a>
					</h1>
					<div class="content">
						<ul class="MM">
							<li><a href="appointment_list" target="main">预约列表</a>
							</li>
							<li><a href="admin/appointmentadd_view" target="main">新增预约</a>
							</li>
							<li><a href="admin/appointmentfind_view" target="main">预约查询</a>
							</li>
						</ul>
					</div>
				</div>
					<h1 class="type">
						<a href="javascript:void(0)">罚单管理</a>
					</h1>
					<div class="content">
						<ul class="MM">
							<li><a href="amercement_list" target="main">罚单列表</a>
							</li>
							<li><a href="admin/amercementadd_view" target="main">新增罚单</a>
							</li>
							<li><a href="admin/amercementpay_view" target="main">罚款缴纳</a>
							</li>
							<li><a href="admin/amercementfind_view" target="main">罚单查询</a>
							</li>
						</ul>
					</div>

				<h1 class="type">
					<a href="javascript:void(0)">系统管理</a>
				</h1>
				<div class="content">
				<s:if test="#manager.level">
					<ul class="MM">
						<li><a href="manager_list" target="main">管理员列表</a>
						</li>
						<li><a href="admin/manageradd_view" target="main">添加管理员</a>
						</li>
						<li><a href="admin/managerfind_view" target="main">管理员查询</a>
						</li>
					</ul>
				</s:if>
				<s:else>
				<ul class="MM">
						<li>
							<a href="manager_load?manager.id=<s:property value="#manager.id"/>" target="main">登录信息</a>
						</li>
						<li>
							<a href="manager_modifyView?manager.id=<s:property value="#manager.id"/>" target="main">修改密码</a>
						</li>
					</ul>
					
				</s:else>
				</div>
				
		</tr>
	</table>
</body>
<script type="text/javascript">
	var contents = document.getElementsByClassName('content');
	var toggles = document.getElementsByClassName('type');

	var myAccordion = new fx.Accordion(toggles, contents, {
		opacity : true,
		duration : 400
	});
	myAccordion.showThisHideOpen(contents[0]);
</script>
</html>
