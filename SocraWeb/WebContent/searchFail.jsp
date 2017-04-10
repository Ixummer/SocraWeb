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
		<div class="row clearfix"  style="margin-top: 50px;background-color: #000000;width: 100%;margin:50px 0px;">
			<div class="col-md-6 column" style="width: 300px;">
				<h1 class="text-center" style="color: #FFFFFF;">
					Socra
				</h1>
			</div>
			<div class="col-md-6 column" style="padding: 0px;margin-right: 200px;width: 300px;">
				<ul class="nav nav-pills" style="margin-top: 20px;" >
					<li class="active">
						 <a href="IndexGoodsShowServlet">首页</a>
					</li>
					<li>
					<%
						String nickname = (String)session.getAttribute("nickname");
						String url = "login.html";
						if(nickname!=null && !nickname.equals("")){
							url = "userPersonalInformation.jsp";
						}else{
							nickname = "登陆";
						}
					%>
						 <a href= <%=url %>> <%=nickname %> </a>
					</li>
					<li >
						<%
						if(session.getAttribute("nickname")!=null){
						%>
						 <a href="UserLogoutServlet">退出</a>
						 <%
						} 
						%>
					</li>
					<li >
						 <a href="ReplaceFilterServlet">管理仓库</a>
					</li>
				</ul>
			</div>
		</div>
	<div class="container">
	<div class="row clearfix">
		 <div >
		 	<center>
			 	<form action="SearchGoodsServlet" method="GET">
			 		<span><input type="text" name="searchText" style="margin:0px;color:#778899;
			 			padding:0px;position:relative;top:1px;" value="Search"></span>
			 		<span >
			 			<button type="submit" style="position:relative;left:-5px;top:0px;width:50px;height:25px;
			 				padding:0px;background-color:#5CACEE;color:#FFFFFF;font-family:SimHei;"
			 				 class="btn btn-default">搜索</button>
			 		</span>
			 	</form>
		 	</center>
		 </div>
	</div>
	<div>
		<center>
			<h1>没有查询到该商品</h1>
		</center>
	</div>
</div>
	</div>
      <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
      <script src="js/jquery-3.2.0.min.js"></script>
      <!-- 包括所有已编译的插件 -->
      <script src="js/bootstrap.min.js"></script>
      <script type="text/javascript">
      	function jump(id){
      		location.href='QueryGoods?gid='+id;
      	}
      </script>
   </body>
</html>