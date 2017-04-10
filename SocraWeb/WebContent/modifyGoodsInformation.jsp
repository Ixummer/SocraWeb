<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*,bean.GoodsBean,dao.GoodsDAO" %>
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
		<div class="row clearfix"  style="background-color: #000000;width: 100%;margin:50px 0px;">
			<div class="col-md-6 column" style="width: 300px;">
				<h1 class="text-center" style="color: #FFFFFF;">
					Socra
				</h1>
			</div>
			<div class="col-md-6 column" style="padding: 0px;margin-right: 200px;width: 300px;">
				<ul class="nav nav-pills" style="margin-top: 20px;" >
					<li class="active">
						 <a href="index.jsp">主页</a>
					</li>
					<%
						String adminnickname = (String)session.getAttribute("adminnickname");
						if(adminnickname!=null && !adminnickname.equals("")){
						
					%>
					<li>
						 <a href="control.jsp">管理</a>
					</li>
					<li>
						 <a href="adminPersonal.jsp"><%=adminnickname %></a>
					</li>
					<li >
						 <a href="ManageLogoutServlet">退出</a>
					</li>
					<%
						}else {
					%>
						<li>
							 <a href="admin.html">登陆</a>
						</li>
					<% } %>
				</ul>
			</div>
		</div>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column" style="padding: 0px;">
				<%
					GoodsBean bean = (GoodsBean)session.getAttribute("GoodsBean");
					String information = (String)session.getAttribute("information");
				%>
				<form class="form-horizontal" role="form" style="margin-top: 50px ;"
						action="ModifyGoodsInformationServlet" method="post" onsubmit="return validate();">
					<div class="form-group" style="margin-left: 20%;">
						 <label for="inputEmail3" class="col-sm-2 control-label">产品编号</label>
						<div class="col-sm-10" style="width: 50%;">
							<input type="text" class="form-control" value=<%=bean.getGid() %>
								name="gid" readonly="true" >
						</div>
					</div>
					<div class="form-group" style="margin-left: 20%;">
						 <label for="inputPassword3" class="col-sm-2 control-label">产品名称</label>
						<div class="col-sm-10" style="width: 50%;">
							<input type="text" class="form-control" value=<%=bean.getGname() %>
								name="gname" >
						</div>
					</div>
					<div class="form-group" style="margin-left: 20%;">
						 <label for="inputPassword3" class="col-sm-2 control-label">产品价格</label>
						<div class="col-sm-10" style="width: 50%;">
							<input type="text" class="form-control" value=<%=bean.getGprice() %>
								name="gprice" >
						</div>
					</div>
					<div class="form-group" style="margin-left: 20%;">
						 <label for="inputPassword3" class="col-sm-2 control-label">产品简介</label>
						<div class="col-sm-10" style="width: 50%;">
							<input type="text" class="form-control" value=<%=bean.getGintroduce() %>
								name="gintroduce" >
						</div>
					</div>
					<div class="form-group" style="margin-left: 20%;">
						 <label for="inputPassword3" class="col-sm-2 control-label">详细介绍</label>
						<div class="col-sm-10" style="width: 50%;">
							<input type="hidden" id="area_content" value=<%=information %>
								 >
							<textarea class="form-control" id="area" name="area">
							</textarea>
						</div>
					</div>
					<div class="form-group" style="margin-left: 42%;">
						<div class="col-sm-offset-2 col-sm-10" style="margin: 0px;">
							 <button type="submit" class="btn btn-default" style="margin-right: 100px;"
							 	onclick="editHidden('area')">
							 确认修改</button>
						</div>
					</div>
				</form>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10" style="margin-left: 43%;">
							 <button onclick="location.href='manageGoods.jsp'" class="btn btn-default" style="margin-right: 100px;">返回
							  </button>
						</div>
					</div>
			</div>
		</div>
	</div>
</div>
      <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
      <script src="js/jquery-3.2.0.min.js"></script>
      <!-- 包括所有已编译的插件 -->
      <script src="js/bootstrap.min.js"></script>
      <script type="text/javascript">
      	// 显示时不允许有空格，应该改为&nbsp
      	document.getElementById("area").value = document.getElementById("area_content").value;	
      </script>
   </body>
</html>