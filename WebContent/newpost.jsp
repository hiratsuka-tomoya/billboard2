<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー登録</title>
<link href="./css/style.css" rel="stylesheet" type="text/css">
<!--[if lt IE 9]>
<script src="//cdn.jsdelivr.net/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body>
既存カテゴリー表示したい
<div class="heading">新規投稿画面</div>
<div class="main-contents">
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
<form action="newpost" method="post"><br />
	<label for="title">件名</label>
	<input name="title" value="${title}" id="title" size="50" maxlength="50" /><br />
	<label for="text">本文</label>
	<textarea name="text" class="textarea" rows="10" cols="100" maxlength="1000" ></textarea>(1000字まで)<br>
	<label for="category">カテゴリー</label>
	<input name="category" value="${category}" id="category" maxlength="10" /><br />
	<input type="submit" value="投稿" /> <br />
	<a href="./">戻る</a>
</form>
<div class="copyright">Copyright(c)Tomoya Hiratsuka</div>
</div>
</body>



</html>
