<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿</title>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/blitzer/jquery-ui.css" >
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/i18n/jquery.ui.datepicker-ja.min.js"></script>
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
<body>

既存カテゴリー表示したい
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
      <li class="active"><a href="/Billboard/">ホーム</a></li>
      <li> <a href="/Billboard/newpost">新規投稿</a></li>
      <li> <a href="/Billboard/logout">ログアウト</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
      <li > <a href="/Billboard/management/top">ユーザー管理</a></li>
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
		新規投稿
	</div>
	<div class="panel-body">
	<form action="newpost" method="post">
	<div class="form-group">
	<label for="title">件名</label>
	<input type="text" class="form-control" id="InputText" placeholder="（50文字まで）" name="title" value="${title}" size="50" maxlength="50" />
	</div>
	<div class="form-group">
	<label for="text">本文</label>
	<textarea class="form-control" id="InputTextarea" placeholder="（1000文字まで）"  name="text" class="textarea" rows="10" cols="100" maxlength="1000" >${text}</textarea>
	</div>
	<div class="form-group">
	<label for="category">カテゴリー</label>
	<input type="text" class="form-control" id="InputText" name="category" placeholder="プルダウンから選択もしくは自由入力 （10文字まで）" value="${category}" autocomplete="on" list="categoryList" maxlength="10" />
	<datalist id="categoryList">
    <option value="個">
    <option value="枚">
	</datalist>
	</div>
	<input type="submit" value="投稿" /> <br />
	</form>
	</div>
	</div>
</div>
</div>
<div class="push"></div>
<footer class="footer"><div class="copyright"><p class="text-center">Copyright(c)Tomoya Hiratsuka</p></div></footer>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</html>
