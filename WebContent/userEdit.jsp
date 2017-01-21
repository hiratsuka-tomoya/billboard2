<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー編集</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
<!--

function passwordCheck() {
	var form = document.forms.userEditForm;
	var result = form.password.value == form.checkPassword.value ? true : false;
	if (result == false) {
		var message = "確認用パスワードが一致しません"
		window.alert(message);
	}

	return result;
}

function stopCheck(target) {
	var result = confirm("[" + target + "]を停止しますか？" );
	return result;
}

function init(branchId, departmentId) {
console.log(branchId);
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

//-->
</script>
</head>
<body onLoad="init(${ branchId },${ departmentId })">
<div class="heading">ユーザー編集</div>
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
<form action="userEdit" method="post" name="userEditForm" onSubmit = "return passwordCheck()"><br />
	<label for="loginId">ログインID</label>
	<input name="loginId" value="${ loginId }" id="loginId" maxlength="20" required/><br />
	<label for="password">パスワード</label>
	<input name="password" type="password" id="password" maxlength="255" /> <br />
	<label for="checkPassword">パスワード（確認用）</label>
	<input name="checkPassword" type="password" id="checkPassword" maxlength="255" /> <br />
	<label for="name">ユーザー名</label>
	<input name="name" value="${name}" id="name" maxlength="10" required/><br />
	<div class="item">支店・部署</div>
	<select name="branchId" id="branchId" onChange="changeDepartmentList()">
	<option value="1">本社</option>
	<option value="2">支店A</option>
	<option value="3">支店B</option>
	<option value="4">支店C</option>
	<option value="5">支店D</option>
	<option value="6">支店E</option>
	<option value="7">支店F</option>
	<option value="8">支店G</option>
	</select>
	<select name="departmentId" id="departmentId">
	</select>
	<input type="submit" value="登録" /> <br />
	<a href="./top">戻る</a>
</form>
<div class="copyright">Copyright(c)Tomoya Hiratsuka</div>
</div>
</body>



</html>
