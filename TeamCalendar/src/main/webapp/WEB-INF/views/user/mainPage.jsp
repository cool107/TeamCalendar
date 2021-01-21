<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>After Login</title>
</head>
<body>
	<div id="home-row-1" class="row clearfix">
		<div class="col-12">
			<h1 class="font-semibold">
				TEAM カレンダー <span class="font-thin">メイン画面</span>
			</h1>
				<h4 class="font-thin">
					<span style="font-size: 45px;">${sessionScope.loginName } 様</span>、こんにちは！
				</h4>
			<a href="mypage">情報修正</a>
			<a href="userList">招待</a>
			<a href="logoutUser">ログアウト</a>
		</div>
</body>
</html>