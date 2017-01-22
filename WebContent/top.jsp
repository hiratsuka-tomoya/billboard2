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
<title>掲示板</title>
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
<script type= text/javascript>
function clearRefine(){
	document.refine.refineCategory.value = "";
	document.refine.refineStartDate.value = "";
	document.refine.refineEndDate.value = "";
}

function myConfirm(target) {
	var result = confirm(target + "を削除しますか？" );
	return result;
}

</script>
<script>
  $(function() {
    $( "#datepicker1" ).datepicker();
  });

  $(function() {
    $( "#datepicker2" ).datepicker();
  });
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
			絞込み条件設定
		</div>
		<div class="panel-body">
			<form action="./" method="get" name="refine">
				<div class="form-group">
					<label for="category">カテゴリー</label>
					<div class="category">
						<select name="refineCategory" class="form-control">
							<c:forEach items="${categories}" var="category">
								<c:if test="${ category == refineCategory }">
									<option value="${category}" selected><c:out value="${category}" /></option>
								</c:if>
								<c:if test="${ category != refineCategory }">
									<option value="${category}"><c:out value="${category}" /></option>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-sm-6">
						<label for="category">from</label>
						<input type="text" id="datepicker1" name="refineStartDate" class="form-control" value="${ refineStartDate }" size="8">
						</div>
						<div class="col-sm-6">
						<label for="category">to</label>
						<input type="text" id="datepicker2" name="refineEndDate" class="form-control" value="${ refineEndDate }"  size="8">
						</div>
					</div>
				</div>
				<input type="submit" value="絞込み">
				<input type="button" value="クリア" onClick="clearRefine( )">
			</form>
		</div>
	</div>

		<hr>
	</div>

	<div class="postings">
		<c:forEach items="${ userPostings }" var="posting">
			<div class="posting">
				<h1><c:out value="${posting.title}" /></h1>
				<div class="information">
					<c:out value="${ posting.userName }" />
					<c:out value="${ posting.createdDate }" />
					<c:out value="${ posting.category }" />
				</div>
				<div class="mainText"><c:out value="${posting.text}" /></div>
				<form action="deleteposting" method="post" name="deleteposting" onSubmit="return myConfirm('投稿')">
					<div class="mainText"><c:out value="${ comment.text }" /></div>
					<span class="information">
						<c:out value="${ posting.userName }" />
						<c:out value="${ posting.createdDate }" />
					</span>
					<input type="hidden" name="postingUserId" value="${posting.userId}">
					<input type="hidden" name="postingId" value="${posting.id}">
					<input type="submit" value="投稿削除"> (投稿者のみ可　管理者はパスで消せるようにしたい)
				</form>
				<hr>
				<c:forEach items="${ userComments }" var="comment">
					<c:if test="${ posting.id == comment.postingId }">
						<div class="comment">
							<form action="deletecomment" method="post" name="deletecomment" onSubmit="return myConfirm('コメント')">
								<div class="mainText"><c:out value="${ comment.text }" /></div>
								<span class="information">
									<c:out value="${ comment.userName }" />
									<c:out value="${ comment.createdDate }" />
								</span>
								<input type="hidden" name="commentUserId" value="${comment.userId}">
								<input type="hidden" name="commentId" value="${comment.id}">
								<input type="submit" value="コメント削除"> (投稿者のみ可　管理者はパスで消せるようにしたい)
							</form>
							<hr>
						</div>
					</c:if>
				</c:forEach>
				<form action="newcomment" method="post" name="newcomment">
					<div class="item">コメント入力欄</div>
					<textarea name="text" class="textarea" rows="5" cols="100" maxlength="500"></textarea><br>
					<input type="hidden" name="postingId" value="${posting.id}">
					<input type="submit" value="投稿">(500字まで)
				</form>
				<hr>
			</div>
			<hr>
		</c:forEach>
	</div>
</div>
<div class="push"></div>
<footer class="footer"><div class="copyright"><p class="text-center">Copyright(c)Tomoya Hiratsuka</p></div></footer>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</body>
</html>
