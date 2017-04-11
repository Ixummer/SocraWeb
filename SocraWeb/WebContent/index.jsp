<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*,bean.GoodsBean,dao.GoodsDAO" %>
<!DOCTYPE html PUBLIC "-///w3c//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose/dtd">
<html>
   <head>
      <title>Home</title>
		<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", -10);
		%>
      <meta http-equiv="Pragma" contect="no-cache">
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
						 <!-- <a href="ReplaceFilterServlet">管理仓库</a> -->
						 <!-- <a href="manageGoods.jsp">管理仓库</a> -->
						 <a href="ManageGoodsServlet">管理仓库</a>
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
		 <div class="col-md-6 column" style="margin-left: 0px;wieth=1250px;margin-top:50px;">
			<center>
			<table class="table" border="5px" style="width:1200px;text-align:center;">
				<thead style="background-color:#FFE1FF">
					<tr >
						<th style="text-align:center;">
							编号
						</th>
						<th style="text-align:center;">
							产品
						</th>
						<th style="text-align:center;">
							价格
						</th>
						<th style="text-align:center;">
							简介
						</th>
						<%if(session.getAttribute("nickname")!=null){ %>
							<th style="text-align:center;">操作</th>	
						<% 	}%>	
					</tr>
				</thead>
				
					<%
						GoodsDAO dao = new GoodsDAO();
						List<GoodsBean> list = (ArrayList<GoodsBean>)dao.getBeanList();
						if(list!=null){
					%>
					<tbody>
					<% for(GoodsBean bean : list){
						int gid = bean.getGid();
					%>
					<tr style="background-color:#D1EEEE" id=<%=gid %>>
						<td >
							<%=bean.getGid() %>
						</td>
						<td>
							<%=bean.getGname() %>
						</td>
						<td>
							<%=bean.getGprice() %>
						</td>
						<td>
							<%=bean.getGintroduce() %>
						</td>
						<%if(session.getAttribute("nickname")!=null){ 
							// 把要查询的内容放到session中，只能用Ajax
						%> 
							<td><button onclick="jump(<%=gid %>)">查看详情</button></td>	
						<% 	}%>		
					</tr>
					<%}%>
					</tbody>
					<%}else{ %>
							<h1>无法获取商品信息，请先登录</h1>
					<%} %>
			</table>
			</center>
		</div>
		<div class="col-md-4 column">
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-12 column" style="text-align: center;">
			<ul class="pagination">
				<li>
					 <a href="#">Prev</a>
				</li>
				<li>
					 <a href="#">1</a>
				</li>
				<li>
					 <a href="#">2</a>
				</li>
				<li>
					 <a href="#">3</a>
				</li>
				<li>
					 <a href="#">4</a>
				</li>
				<li>
					 <a href="#">5</a>
				</li>
				<li>
					 <a href="#">Next</a>
				</li>
			</ul>
		</div>
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