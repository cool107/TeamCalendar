<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<script src="resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript">

	$(function() {
		$("#department").val("${member.department}");
		
 
		$("#update_Member").on('click', function(){
			$('#updateMember').submit();
		});		
		
		$("#ps").on('focus', function() {
			$(this).css('display', 'none');
			$(this).next().css('display', 'inline-block');
			}); 			
	});
	
</script>


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

.passhidden { 
	display: none; 
	
}

</style>

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
		<span style="font-size: 45px;">登録情報の編集</span>
	</h4>
	<br>
	<form action="updateMember" id="updateMember" method="POST" style="
    height: 400px;">
		<fieldset>
			<table cellspacing="30">
					<tr style="height: 35px;" ></tr>
				<tr>
					
					<td>
						<div class="form-group">
						<p style="width: 30%; float: left; font-size: 20px;">ID</p><input type="text" class="form-control" name="id"
								id="id" value="${member.id}" readonly="readonly" style="width:70%; float: left">
						</div>
						<p></p>
					</td>
				</tr>
				
					<tr style="height: 35px;">
					<td>
						<div class="form-group">
							<p style="width:30%; float:left; font-size: 20px;">PASSWORD</p>
							<input class="form-control" type="text" id="ps" placeholder="入力して下さい" style="width:70%; float:left;"/>
								<input class="passhidden" type="password" id="pw" name="pw" style="width:70%; float:left; height: 35px;" />
						</div>
		
					</td>
				</tr>
					<tr style="height: 35px;">
					<td>	
						<div class="form-group">
							<p style="width:30%;float:left; font-size: 20px;">NAME</p>
							<input type="text" class="form-control" name="name"
								id="name" value="${member.name}"  style="width:70%; float:left;" readonly="readonly">
						</div>
						<p></p>
					</td>
				</tr>
				<tr style="height: 35px;">
					<td>
						<div class="form-group">
							<p style="width:30%; float:left; font-size: 20px; vertical-align: middle;">学科</p>
							<select class="selectpicker" name="department" id="department"
								style="width: 70%; height: 40px; float:left;">
 								<option value="medical" >医学系研究科</option>					
								<option value="education">教育学研究科</option>
								<option value="economy">経済学研究科</option>
								<option value="engineering">工学系研究科</option>
								<option value="information">情報理工学系研究科</option>
								<option value="frontier science">新領域創成科学研究科</option>
								<option value="humanities">人文社会系研究科</option>
								<option value="mathematics">数理科学研究科</option>
								<option value="culture">総合文化研究科</option>
								<option value="agriculture">農学生命科学研究科</option>
								<option value="law">法学政治学研究科</option>
								<option value="pharmacy">薬学系研究科</option>
								<option value="sciences">理学系研究科</option> 
							</select>
						</div>
					</td>
				</tr>

				<tr style="height: 100px">
					<td colspan="2" style="text-align: center;">
						<input class="btn btn-primary" id="update_Member" type="button" value="変更する"
							style="width: 200px; background-color: white; border-color: #93b2ec; color: #93b2ec;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
							class="btn btn-primary" href="/" type="button"
							style="width: 200px; background-color: white; border-color: #93b2ec; color: #93b2ec;">キャンセル</a>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>		
</body>
</html>