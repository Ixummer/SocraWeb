<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*,bean.GoodsBean,dao.GoodsDAO" %>
<!DOCTYPE html>
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
		<div class="col-md-6 column" style="margin-left:0px;width:1200px;">
			<table class="table" id="table" border="5px" style="width:1200px;text-align:center;">
				<thead>
					<tr>
						<th style="text-align:center;">编号</th>
						<th style="text-align:center;">商品</th>
						<th style="text-align:center;">价格</th>
						<th style="text-align:center;">介绍</th>
						<th style="text-align:center;">操作</th>
					</tr>
				</thead>
				<%
						GoodsDAO dao = new GoodsDAO();
						List<GoodsBean> list = (ArrayList<GoodsBean>)dao.getBeanList();
						int count = list.size();
						int i=1;
						if(list!=null){
					%>
					<tbody>
					<% for(GoodsBean bean : list){
						if(i>10)
							break;
						int gid = bean.getGid();
					%>
					<tr id=<%=gid %>>
						<td><%=bean.getGid() %></td>
						<td><%=bean.getGname() %></td>
						<td><%=bean.getGprice() %></td>
						<td><%=bean.getGintroduce() %></td>
						<td style="width:300px;">
							<button onclick="changeEdit(<%=gid %>)" id="edit">编辑<%=gid %></button>
							<button onclick="modifyGoods('table',<%=gid %>)">保存</button>
							<button onclick="modifyInformation(<%=gid %>)">修改详细信息</button>
						</td>
					</tr>
					<%	i++; }%>
					</tbody>
					<%} %>
			</table>
		</div>
		<div class="col-md-4 column">
		</div>
		 <!--  
		<div style="margin-left:10px;">
			<button onclick="getTableRowLength('table')">获取数据</button>
			<p id="sumresult"></p>
			<button onclick="showTableContent('table')">查看数据</button>
			<table id="tableresult"></table>
		</div>
		-->
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
		function getTableRowLength(id){
			var mytable = document.getElementById(id);
			var temp = mytable.rows.length;
			document.getElementById('sumresult').innerHTML = temp;
			
			return temp;
		}
		
		function getTableContent(id){
			var mytable = document.getElementById(id);
			var data = [];
			var row = mytable.rows.length;
			var cell = mytable.rows[0].cells.length;
			for (var i = 0; i<row; i++) {
				for(var j=0; j<cell; j++){
					if(!data[i])
						data[i] = new Array();
					data[i][j] = mytable.rows[i].cells[j].innerHTML;
				}
			}
			return data;
		}

	    function showTableContent(id){
	        var data = getTableContent(id);
	        var tmp = '';
	        for(i=0,rows=data.length; i<rows; i++){
	            for(j=0,cells=data[i].length; j<cells; j++){
	                tmp += data[i][j] + ',';
	            }
	            tmp += '<br>';
	        }
	        document.getElementById('tableresult').innerHTML = tmp;
	    }
		
		// 全局XMLHttpServlet对象
		var xhr = false;
		function createXHR(){
			try{
				xhr = new XMLHttpRequest();
			}catch(e){
				try{
					xhr = ActiveXObject("Microsoft.XMLHttpReuqest");
				}catch(e1){
					xhr = false;
				}
			}
			if(!xhr)
				alert('创建xhr对象失败');
		}
		
		function getJSON(id){
			var data = getTableContent(id);
			var goodsJson;
			if(data.length>0){
				goodsJson = "{'goods':[";
				for(var i=1; i<data.length-1; i++){
					goodsJson += "{'gid':'" + data[i][0] + "',";
					goodsJson += "'gname':'" + data[i][1] + "',";
					goodsJson += "'gprice':'" + data[i][2] + "',";
					goodsJson += "'gintroduce':'" + data[i][3]+"'},";
				}
				goodsJson += "{'gid':'" + data[i][0] + "',";
				goodsJson += "'gname':'" + data[i][1] + "',";
				goodsJson += "'gprice':'" + data[i][2] + "',";
				goodsJson += "'gintroduce':'" + data[i][3]+"'}]}";
			}
			if(goodsJson)
				return goodsJson;
			else
				return false;
		}

		function getURL(id,no){
			var data = getTableContent(id);
			var url = "";
			if(data.length>0){
				url += "gid=" + data[no][0] + "&";
				url += "gname=" + data[no][1] + "&";
				url += "gprice=" + data[no][2] + "&";
				url += "gintroduce=" + data[no][3];
			}
			return url;
		}
		
		function modifyGoods(id,no){
			createXHR();
			var obj = document.getElementById(id);
			//var goodsJson = getJSON(id);
			var goodJson = getURL(id,no);
			var url = "ModifyGoodsServlet?" + goodJson;
			// 需要进行url转码,防止浏览器自行转码
			url = encodeURI(url);	
			//alert(url);
			xhr.open("GET",url,true);
			xhr.onreadystatechange = function(){
				if(xhr.status==200 && xhr.readyState==4){
					var result = xhr.responseText;
					if(result){
						alert('数据修改成功');
					}else{
						alert('数据修改失败');
					}
				}
			};
			xhr.send(null);
		}
		
		function changeEdit(id){
			 var obj = document.getElementById(id);
			 var flag = obj.getAttribute("contenteditable");
			 if(!flag){
			 	obj.setAttribute("contenteditable","true");
			 }
		}
		
		function modifyInformation(id){
			location.href='QueryGoodsInformation?gid='+id;
		}
      </script>
   </body>
</html>