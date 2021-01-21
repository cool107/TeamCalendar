<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<script src="resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript"></script>
<head lang="en">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<title>MyPage</title>
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
	height: 280px;
	border-radius: 20px;
	text-align: center;
}

table {
	margin: auto;
	text-align: center;
}

td {
	text-align: left;
	font-size: 25px;
}
</style>
<script src="resources/js/jquery-3.3.1.js"></script>
<script>

	$(function(){
		$("#department").val("${member.department}");
		$('#department').css('pointer-events','none');
	});
	
		
</script>

<body>
	<div class="navbar navbar-fixed-top">
		<p style="text-align: right; font-size: 20px;">
			<a class="nav-link" href="/">HOME</a> &nbsp;&nbsp;&nbsp;
		</p>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<h4 class="font-thin"
		style="text-align: center; color: white; font-size: 32px;">
		<span style="font-size: 45px;">登録情報</span>
	</h4>
	<br>
	<form action="mypage" id="mypage" method="get" style="height: 400px;">
		<fieldset>
			<table cellspacing="30">
				<tr style="height: 35px;"></tr>
				<tr>

					<td>ID</td>
					<td>${member.id}</td>
				</tr>

				<tr>
					<td>名前</td>
					<td>${member.name}</td>
				</tr>

				<tr>
					<td>学科</td>
					<td>${member.department}</td>

				</tr>

				<tr>
					<td>職位</td>
					<td>${member.division}</td>

				</tr>
				<tr style="height:20px;">
				</tr>

				<tr style="height: 60px;">
					<td colspan="2" style="text-align: center;"><a
						href="updateMember" class="btn btn-primary" id="updateMember"
						type="button"
						style="width: 200px; background-color: white; border-color: #93b2ec; color: #93b2ec;">修正</a>&nbsp;&nbsp;&nbsp;&nbsp;  <a
						class="btn btn-primary" href="/" type="button"
						style="width: 200px; background-color: white; border-color: #93b2ec; color: #93b2ec;">キャンセル</a>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>