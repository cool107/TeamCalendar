<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/login.css">
<meta charset="UTF-8">
<script src="resources/js/jquery-3.3.1.js"></script>
<script>
$(function(){
	$("#login").on('click', login);
	$("#goJoin").on('click', goJoin);
});

function login(){
	var email = $('#email').val();
	var password = $('#password').val();
	
	if (email == "") {
		$("#checkMsg").html("IDを入力してください。").css('color', 'red');
		return false;
	} else if (password == "") {
		$("#checkMsg").html("パスワードを入力してください。").css('color', 'red');
		return false;
	}
};

function goJoin(){
	location.href = "goJoin";
};

</script>
<title>Insert title here</title>
</head>
<body>
<h2>チームスケジュール</h2>
<div class="container" id="container">
	<div class="form-container sign-in-container">
		<form action="loginUser" id="loginUser" method="post">
			<h1>ログイン</h1>
			<div id="checkMsg" style="font-size: 17px;"></div>
			<input type="text" name="email" id="email" placeholder="Email">
			<input type="password" name="password" id="password" placeholder="PASSWORD">
			<button id="login">ログイン</button>
		</form>
	</div>
	<div class="overlay-container">
		<div class="overlay">
			<div class="overlay-panel overlay-right">
				<h1>登録情報</h1>
				<p>簡単に登録できます。</p>
				<button class="ghost" id="goJoin">登録</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>