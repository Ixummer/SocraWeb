<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*,bean.UserBean,dao.UserDAO" %>
<!DOCTYPE html PUBLIC "-///w3c//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose/dtd">
<html>
   <head>
      <title>Home</title>
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <!-- 引入 Bootstrap -->
      <link href="css/bootstrap.min.css" rel="stylesheet">
      <link href="css/myself.css" rel="stylesheet">
 
      <!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
      <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
      <!--[if lt IE 9]>
         <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
         <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
      <![endif]-->
   </head>
<body>
	<div class="container" style="margin:0px 0px; padding: 0px 0px; width: 100%">
		<div class="row clearfix"  style="background-color: #000000;width: 100%;margin: 50px 0px;">
			<div class="col-md-6 column" style="width: 300px;">
				<h1 class="text-center" style="color: #FFFFFF;">
					Socra
				</h1>
			</div>
			<div class="col-md-6 column" style="padding: 0px;margin-right: 200px;width: 500px;">
				<ul class="nav nav-pills" style="margin-top: 20px;" >
					<li class="active">
						 <a href="index.jsp">首页</a>
					</li>
					<li>
						 <a href="index.jsp">返回</a>
					</li>
					<li >
						 <a href="error.html">信息</a>
					</li>
				</ul>
			</div>
		</div>
			<%
				UserDAO dao = new UserDAO();
				UserBean bean = dao.getBean();
				String uid = "account";
				String upsd = "123456";
				String uname = "abc";
				if(bean!=null){
					uid = bean.getUid();
					upsd = bean.getUpsd();
					uname = bean.getUname();
				}
			%>
		<div class="row clearfix">
			<div class="col-md-12 column" style="padding: 0px;">
				<form class="form-horizontal" role="form" style="margin-top: 200px ;"
						action="UserModifyInformationServlet" method="post" onsubmit="return validate();">
					<div class="form-group" style="margin-left: 30%;">
						 <label for="inputEmail3" class="col-sm-2 control-label">用户账号</label>
						<div class="col-sm-10" style="width: 30%;">
							<input type="email" class="form-control" id="inputEmail3" 
								name="account" value="<%=uid %>" readOnly="true" />
						</div>
					</div>
					<div class="form-group" style="margin-left: 30%;">
						 <label for="inputPassword3" class="col-sm-2 control-label">用户密码</label>
						<div class="col-sm-10" style="width: 30%;">
							<input type="text" class="form-control" id="inputPassword3" 
								name="password" value="<%=upsd %>" />
						</div>
					</div>
					<div class="form-group" style="margin-left: 30%;">
						 <label for="inputPassword3" class="col-sm-2 control-label">用户名称</label>
						<div class="col-sm-10" style="width: 30%;">
							<input type="text" class="form-control" id="inputNickName3" 
								name="nickname" value="<%=uname %>" />
						</div>
					</div>
					<div class="form-group" style="margin-left: 42%;">
						<div class="col-sm-offset-2 col-sm-10" style="margin: 0px;">
							 <button type="submit" class="btn btn-default" style="margin-right: 100px;">修改</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
      <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
      <script src="js/jquery-3.2.0.min.js"></script>
      <!-- 包括所有已编译的插件 -->
      <script src="js/bootstrap.min.js"></script>
</body>
</html>