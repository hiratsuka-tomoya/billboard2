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
<title>ユーザー管理</title>
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

  /* フッタの高さと等しいネガティブマージン */
  /* また最後の子要素の潜在的なマージンとしても機能 */
  margin-bottom: -50px;
}
.footer,
.push {
  height: 50px;
}
-->
</style>
<script type="text/javascript">
function stopCheck(target) {
	var result = confirm("[" + target + "]を停止しますか？" );
	return result;
}
function recoverCheck(target) {
	var result = confirm("[" + target + "]を復帰しますか？" );
	return result;
}
</script>
</head>
<body>
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
      <li class="active"> <a href="/Billboard/management/top">ユーザー管理</a></li>
      <li> <a href="/Billboard/management/newuser">ユーザー新規登録</a></li>
      <li> <a href="/Billboard/logout">ログアウト</a></li>
      </ul>
    </div><!--/.nav-collapse -->
    </div>
  </div>
</div>
<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>
<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading">
			ユーザー一覧
		</div>
		<table class="table">
		   <thead>
		      <tr>
		        <th>ID</th>
		        <th>ユーザー名</th>
		        <th>支店</th>
		        <th>部署</th>
		        <th>状態</th>
		        <th>停止/復帰</th>
		      </tr>
		    </thead>
		    <tbody>
			<c:forEach items="${ users }" var="user">
			  <tr>
			    <td class="col-md-1"><c:out value="${user.id}" /></td>
			    <td class="col-md-3"><a href="userEdit?editUserId=${user.id}"><c:out value="${user.name}" /></a></td>
			    <td class="col-md-2">
			    	<c:choose>
						<c:when test="${user.branchId == 1}">本社</c:when>
						<c:when test="${user.branchId == 2}">支店A</c:when>
						<c:when test="${user.branchId == 3}">支店B</c:when>
						<c:when test="${user.branchId == 4}">支店C</c:when>
						<c:when test="${user.branchId == 4}">支店D</c:when>
						<c:when test="${user.branchId == 4}">支店E</c:when>
						<c:when test="${user.branchId == 4}">支店F</c:when>
						<c:when test="${user.branchId == 4}">支店G</c:when>
					</c:choose>
			    </td>
			    <td class="col-md-2">
			    	<c:choose>
						<c:when test="${user.departmentId == 1}">総務人事担当者</c:when>
						<c:when test="${user.departmentId == 2}">情報管理担当者</c:when>
						<c:when test="${user.departmentId == 3}">支店長</c:when>
						<c:when test="${user.departmentId == 4}">社員</c:when>
					</c:choose>
			    </td>
			    <td class="col-md-2">
			    	<c:if test="${user.isStopped() == true}">
						<c:out value="停止中" />
				    </c:if>
				    <c:if test="${user.isStopped() == false}">
			    		<c:out value="稼働中" />
				    </c:if>
			    </td>
		      	<td class="col-md-2">
				    <form action="./top" method="post">
					    <c:if test="${user.isStopped() == false}">
					    	<input type="hidden" name="stopUserId" value="${user.id}">
					    	<input type="submit" value="停止" onClick="return stopCheck('${user.name}')"></input>
					    </c:if>
					    <c:if test="${user.isStopped() == true}">
							<input type="hidden" name="recoverUserId" value="${user.id}">
					    	<input type="submit" value="復帰" onClick="return recoverCheck('${user.name}')"></input>
					    </c:if>
				    </form>
			    </td>
			  </tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<div class="push"></div>
</div>
<footer class="footer"><div class="copyright"><p class="text-center">Copyright(c)Tomoya Hiratsuka</p></div></footer>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</body>
</html>
