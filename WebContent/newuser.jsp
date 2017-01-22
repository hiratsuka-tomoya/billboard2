<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ユーザー登録</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/blitzer/jquery-ui.css" >
<link href="../css/style.css" rel="stylesheet" type="text/css">
<!--[if lt IE 9]>
<script src="//cdn.jsdelivr.net/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<style type="text/css">
<!--
html, body{
padding-top: 40px;
height: 100%;
margin: 0;
}
.wrapper {
  min-height: 100%;

  margin-bottom: -50px;
}
.footer,
.push {
  height: 50px;
}
-->
</style>
<script type="text/javascript">
function init(branchId, departmentId) {

	if ( typeof branchId !== "undefined") {
		var select1 = document.getElementById("branchId");
		for(var i=0;i<select1.length;i++){
			if(select1[i].value==branchId){
				select1[i].selected=true;
			break;
			}
		}
	}
	changeDepartmentList();
	if ( typeof departmentId !== "undefined") {
		var select2 = document.getElementById("departmentId");
		for(var i=0;i<select2.length;i++){
			if(select2[i].value==departmentId){
				select2[i].selected=true;
			break;
			}
		}
	}
}
function changeDepartmentList() {

	var select1 = document.getElementById("branchId");
	var select2 = document.getElementById("departmentId");
	if (select1.options[select1.selectedIndex].value == 1) {
		select2.options[0] = new Option("総務人事担当者", 1);
		select2.options[1] = new Option("情報管理担当者", 2);
	}
	else {
		select2.options[0] = new Option("支店長", 3);
		select2.options[1] = new Option("社員", 4);
	}
}
</script>
</head>
<body onLoad="init(${ branchId },${ departmentId })">
<div class="wrapper">
<div id="navbar-main">
  <div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
		</div>
    <div class="navbar-collapse collapse">
      <ul class="nav navbar-nav">
      <li><a href="/Billboard/">ホーム</a></li>
      <li> <a href="/Billboard/management/top">ユーザー管理</a></li>
      <li class="active"> <a href="/Billboard/management/newuser">ユーザー新規登録</a></li>
      <li> <a href="/Billboard/logout">ログアウト</a></li>
      </ul>
    </div><!--/.nav-collapse -->
    </div>
  </div>
</div>


<div class="container">
	<c:if test="${ not empty errorMessages }">
			<div class="alert alert-warning" role="alert">
				<div class="errorMessages">
					<ul>
						<c:forEach items="${errorMessages}" var="message">
							<li><c:out value="${message}" />
						</c:forEach>
					</ul>
				</div>
				<c:remove var="errorMessages" scope="session"/>
			</div>
	</c:if>
	<div class="panel panel-default">
		<div class="panel-heading">
			ユーザー新規登録
		</div>
		<div class="panel-body">
			<form action="newuser" method="post"><br />
				<div class="form-group">
				<label for="loginId">ログインID</label>
				<input name="loginId" class="form-control" value="${loginId}" id="loginId"/><br />
				</div>
				<div class="form-group">
				<label for="password">パスワード</label>
				<input name="password" class="form-control" type="password" id="password"/> <br />
				</div>
				<div class="form-group">
				<label for="name">ユーザー名</label>
				<input name="name" class="form-control" value="${name}" id="name"/><br />
				</div>
				<div class="row">
					<div class="col-sm-6">
						<div class="form-group">
							<label for="name">支店</label>
							<select name="branchId" class="form-control" id="branchId" onChange="changeDepartmentList()">
							<option value="1">本社</option>
							<option value="2">支店A</option>
							<option value="3">支店B</option>
							<option value="4">支店C</option>
							<option value="5">支店D</option>
							<option value="6">支店E</option>
							<option value="7">支店F</option>
							<option value="8">支店G</option>
							</select>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label for="name">部署</label>
							<select name="departmentId" class="form-control" id="departmentId">
							</select>
						</div>
					</div>
				</div>
				<input type="submit" value="登録" />
			</form>
		</div>
	</div>
</div>
<div class="push"></div>
</div>

<footer class="footer"><div class="copyright"><p class="text-center">Copyright(c)Tomoya Hiratsuka</p></div></footer>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</body>
</html>
