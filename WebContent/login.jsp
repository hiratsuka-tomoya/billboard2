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
<title>BBS - ログイン</title>
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
padding-top: 10px;
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
</head>
<body>
<div class="wrapper">
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
		ログイン
	</div>
	<div class="panel-body">
		<form action="login" method="post"><br />
			<div class="form-group">
			<label for="loginID">ログインID</label>
			<input name="loginID" class="form-control" value="${loginID}" id="loginID"/>
			</div>
			<div class="form-group">
			<label for="password">パスワード</label>
			<input name="password" class="form-control" type="password" id="password"/>
			</div>
			<input type="submit" value="ログイン" /> <br />
		</form>
	</div>
</div>
</div>
</div>
<footer class="footer"><div class="copyright"><p class="text-center">Copyright(c)Tomoya Hiratsuka</p></div></footer>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</body>
</html>
