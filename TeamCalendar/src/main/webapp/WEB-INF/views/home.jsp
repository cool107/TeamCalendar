<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head lang="en">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/login.css">
<!------ Include the above in your HEAD tag ---------->

</head>

<body>
<h2>チームスケジュール</h2>
<div class="container" id="container">
	<div class="form-container sign-up-container">
		<form action="joinUser" id="joinUser" method="post">
			<h1>会員登録</h1>
			<div id="checkMsg2" style="font-size: 20px;"></div>
			<input type="text" id="email2" name="email2" placeholder="Email">
			<input type="password" name="password2" id="password2" placeholder="PASSWORD">
			<input type="text" name="name2" id="name2" placeholder="名前">
			<input type="text" name="division"	id="division" placeholder="チーム">
			<button id="join">会員登録</button>
		</form>
	</div>
	<div class="form-container sign-in-container">
		<form action="loginUser" id="loginUser" method="post">
			<h1>ログイン</h1>
			<div id="checkMsg" style="font-size: 20px;" ></div>
			<input type="text" 	name="email" id="email" placeholder="Email">
			<input type="password" name="password" id="password" placeholder="PASSWORD">
			<input type="submit" type ="button" value ="ログイン">
<!-- 			<button id="login">ログイン</button> -->
		</form>
	</div>
	<div class="overlay-container">
		<div class="overlay">
			<div class="overlay-panel overlay-left">
				<h1>ログイン</h1>
				<p>ログインをしてください。</p>
				<button class="ghost" id="signIn">ログイン</button>
			</div>
			<div class="overlay-panel overlay-right">
				<h1>登録情報</h1>
				<p>簡単に登録できます。</p>
				<button class="ghost" id="signUp">登録</button>
			</div>
		</div>
	</div>
</div>
<script src="resources/js/jquery-3.3.1.js"></script>
<script>
const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
	container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
	container.classList.remove("right-panel-active");
});

$(function() {
	$("#login").on('click', login);
	$("#join").on('click', joinMember);
	$("#email").on('keyup', join);
});

function joinMember() {
	var email2 =$("#email2").val();
	var password2 = $('#password2').val();
	var name2 = $('#name2').val();
	var division2 =$('#division').val();
	
	if(email2 == ""){
		alert("IDを入力してください。");
		return;
	} else if (password2 == "") {
		alert("パスワードを入力してください。");
		return;
	} else  if (name2 == "") {
		alert("お名前を入力してください。");
		return;
	} else  if (division2 == "") {
		alert("所属を入力してください。");
		return;
	} else {
		$("#joinUser").submit();
	};
}

function join() {
	var email2 = $("#email2").val();
	
	if (email.length < 3 || email.length > 30) {
		$("#checkMsg2").html("IDの長さは3~30字です。").css('color', 'red');
	} else {
		$.ajax({
			method : 'GET'
			, url : 'checkId'
			, data : {'email' : email2}
			, success : function(resp){
				if (resp != "") {
					$("#checkMsg2").html("このIDは使用中です。</br>他のIDを選んでください。").css('color', 'red');
				} else {
					$("#checkMsg2").html("このIDは使えます。").css('color', 'green');
				};
			}
		});
	};
};

function login(){
	var email = $('#email').val();
	var password = $('#password').val();
	
	if (email == "") {
		$("#checkMsg").html("IDを入力してください。").css('color', 'red');
		return;
	} else if (password == "") {
		$("#checkMsg").html("パスワードを入力してください。").css('color', 'red');
		return;
	}
	
	$.ajax({
		method : 'GET'
		, url : 'checkId'
		, data : {'email' : email}
		, success : function(resp){
			if (resp == "") {
				$("#checkMsg").html("会員ではありません。").css('color', 'red');
				return;
			} else {
				if (resp.password == password) {
					$("#loginUser").submit();
				} else {						
					$("#checkMsg").html("パスワードを確認してください。").css('color', 'red');
					return;
				}
			}
		}
	});
};
  </script> 

</body>
</html>