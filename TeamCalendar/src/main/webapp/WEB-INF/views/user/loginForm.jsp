<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head lang="en">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<title>Login Page</title>
<meta name="description"
	content="BlackTie.co - Free Handsome Bootstrap Themes" />
<meta name="keywords"
	content="themes, bootstrap, free, templates, bootstrap 3, freebie,">
<meta property="og:title" content="">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link
	href="https://fonts.googleapis.com/css?family=M+PLUS+Rounded+1c:400,500,700&amp;subset=japanese"
	rel="stylesheet" type='text/css'>

</head>
<style>
body {
	background-image: url('resources/images/bg1.jpg');
	background-size: 100%;
}

fieldset {
	margin: auto;
	background-color: rgba(227, 227, 227, 0.5);
	width: 500px;
	height: 230px;
	border-radius: 20px;
	text-align: center;
}

table {
	margin: auto;
	text-align: center;
}
</style>
<script src="resources/js/jquery-3.3.1.js"></script>
<script>
	$(function(){
		$("#login").on('click', login);
	});
	
	function login(){
		var id = $('#id').val();
		var pw = $('#pw').val();
		
		if (id == "") {
			$("#checkMsg").html("IDを入力してください。").css('color', 'red');
			return;
		} else if (pw == "") {
			$("#checkMsg").html("パスワードを入力してください。").css('color', 'red');
			return;
		}
		
		$.ajax({
			method : 'GET'
			, url : 'checkId'
			, data : {'id' : id}
			, success : function(resp){
				if (resp == "") {
					$("#checkMsg").html("会員ではありません。").css('color', 'red');
					return;
				} else {
					if (resp.pw == pw) {
						$("#loginMember").submit();
					} else {						
						$("#checkMsg").html("パスワードを確認してください。").css('color', 'red');
						return;
					}
				}
			}
		});
	};
	
	
</script>
<body>
	<div class="navbar navbar-fixed-top">
		<p style="text-align: right; font-size: 20px;">
			<a class="nav-link" href="/">HOME</a> &nbsp;&nbsp;&nbsp;
		</p>
	</div>
	<h4 class="font-thin"
		style="text-align: center; color: white; font-size: 32px;">
		ようこそ! <span style="font-size: 45px;">SC UNIVERSITY</span>へ
	</h4>
	<br>
	<form action="loginMember" id="loginMember" method="post">
		<fieldset>
			<table cellspacing="30">
				<tr style="height: 35px;"></tr>
				<tr>
					<td>
						<div class="form-group">
							<input type="text" class="form-control"
								name="email" id="email" placeholder="Email入力">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="form-group">
							<input type="password" class="form-control" name="password"
								id="password" placeholder="PASSWORD">
						</div>
					</td>
				</tr>
				<tr style="height: 30px; vertical-align: middle;">
					<td>
						<div id="checkMsg" style="font-size: 20px;"></div>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><input
						class="btn btn-primary" id="login" type="button" value="ログイン"
						style="width: 200px; background-color: white; border-color: #93b2ec; color: #93b2ec;">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
						class="btn btn-primary" id="join" type="button" value="会員登録"
						style="width: 200px; background-color: white; border-color: #93b2ec; color: #93b2ec;">
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>

</html>
