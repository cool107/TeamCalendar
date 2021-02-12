<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<script src="resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript"></script>
<head lang="en">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/login.css">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/cal.css">
<meta charset="utf-8">

<title>MyPage</title>


</head>
<style>
    /**
 * ALL the UI design credit goes to:
 * https://www.sketchappsources.com/free-source/2676-calendar-template-sketch-freebie-resource.html
 */


/* WRAPPER */

.wrapper {
  display: grid;
  grid-template-rows: 70px 1fr 70px;
  grid-template-columns: 1fr;
  grid-template-areas: "sidebar"
                       "content";
  width: 100vw; /* unnecessary, but let's keep things consistent */
  height: 100vh;
}

@media screen and (min-width: 850px) {
  .wrapper {
    grid-template-columns: 200px 5fr;
    grid-template-rows: 1fr;
    grid-template-areas: "sidebar content";
  }
}



/* SIDEBAR */

main {
  grid-area: content;
  padding: 48px;
}

sidebar {
  grid-area: sidebar;
  display: grid;
  grid-template-columns: 1fr 3fr 1fr;
  grid-template-rows: 3fr 1fr;
  grid-template-areas: "logo menu avatar"
                       "copyright menu avatar";
}
.logo {
  display: flex;
  align-items: center;
  justify-content: center;
}
.copyright {
  text-align: center;
}
.avatar {
  grid-area: avatar;
  display: flex;
  align-items: center;
  flex-direction: row-reverse;
}
.avatar__name {
  flex: 1;
  text-align: right;
  margin-right: 1em;
}
.avatar__img > img {
  display: block;
}

.copyright {
  grid-area: copyright;
}
.menu {
  grid-area: menu;
  display: flex;
  align-items: center;
  justify-content: space-evenly;
}
.logo {
  grid-area: logo;
}
.menu__text {
  display: none;
}

@media screen and (min-width: 850px) {
  sidebar {
    grid-template-areas: "logo"
                         "avatar"
                         "menu"
                         "copyright";
    grid-template-columns: 1fr;
    grid-template-rows: 50px auto 1fr 50px;
  }
  
  .menu {
    flex-direction: column;
    align-items: normal;
    justify-content: flex-start;
  }
  .menu__text {
    display: inline-block;
  }
  .avatar {
    flex-direction: column;
  }
  .avatar__name {
    margin: 1em 0;
  }
  .avatar__img > img {
    border-radius: 50%;
  }
}




/* MAIN */

.toolbar{
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 24px;
}
.calendar{}

.calendar__week,
.calendar__header {
  display: grid;
  grid-template-columns: repeat(7, 1fr);  
}
.calendar__week {
  grid-auto-rows: 100px;
  text-align: right;
}

.calendar__header {
  grid-auto-rows: 50px;
  align-items: center;
  text-align: center;
}

.calendar__day {
  padding: 16px;
}




/* COSMETIC STYLING */

:root {
  --red: #ED5454;
}

body {
  font-family: Montserrat;
  font-weight: 100;
  color: #A8B2B9;
}

sidebar {
  background-color: white;
  box-shadow: 5px 0px 20px rgba(0, 0, 0, 0.2);
}

main {
  background-color: #FCFBFC;
}

.avatar__name {
  font-size: 0.8rem;
}

.menu__item {
  text-transform: uppercase;
  font-size: 0.7rem;
  font-weight: 500;
  padding: 16px 16px 16px 14px;
  border-left: 4px solid transparent;
  color: inherit;
  text-decoration: none;
  transition: color ease 0.3s;
}

.menu__item--active .menu__icon {
  color: var(--red);
}
.menu__item--active .menu__text {
  color: black;
}

.menu__item:hover {
  color: black;
}


.menu__icon {
  font-size: 1.3rem;
}

@media screen and (min-width: 850px) {
  .menu__icon {
    font-size: 0.9rem;
    padding-right: 16px;
  }
  .menu__item--active {
    border-left: 4px solid var(--red);
    box-shadow: inset 10px 0px 17px -13px var(--red);
  }
}

.copyright {
  font-size: 0.7rem;
  font-weight: 400;
}

.calendar {
  background-color: white;
  border: 1px solid #e1e1e1;
}

.calendar__header > div {
  text-transform: uppercase;
  font-size: 0.8em;
  font-weight: bold;
}

.calendar__day {
  border-right: 1px solid #e1e1e1;
  border-top: 1px solid #e1e1e1;
}

.calendar__day:last-child {
  border-right: 0;
}

.toggle{
  display: grid;
  grid-template-columns: 1fr 1fr;

  text-align: center;
  font-size: 0.9em;
}
.toggle__option{
  padding: 16px;
  border: 1px solid #e1e1e1;
  border-radius: 8px;
  text-transform: capitalize;
  cursor: pointer;
}
.toggle__option:first-child {
    border-top-right-radius: 0;
    border-bottom-right-radius: 0;
}
.toggle__option:last-child {
    border-left: 0;
    border-top-left-radius: 0;
    border-bottom-left-radius: 0;
}
.toggle__option--selected{
  border-color: white;
  background-color: white;
  color: var(--red);
  font-weight: 500;
  box-shadow: 1px 2px 30px -5px var(--red);
}
  </style>
</style>
<script src="resources/js/jquery-3.3.1.js"></script>
<script>

		
</script>

<body>
	


<div class="wrapper">
 	
  <main>
  <div class="navbar navbar-fixed-top">
		<p style="text-align: right; font-size: 20px;">
		</p>
	</div>
	<h4 class="font-thin"
		style="text-align: center; color: balck; font-size: 32px;">
		<span style="font-size: 45px;">登録情報</span>
	</h4>
	<br>
<div class="container right-panel-active" id="container">
	<div class="form-container sign-up-container">
		<form action="updateUser" id="updateUser" method="post">
			<h1>会員登録</h1>
			<div id="checkMsg" style="font-size: 20px;"></div>
			ID<input type="text" id="email" name="email"　 value="${user.email}" readonly="readonly" placeholder="Email">
			名前<input type="text" name="name" id="name" value="${user.name}"placeholder="名前">
			PASSWORD<input type="password" name="password" id="password" value="${user.password}" placeholder="PASSWORD">
			所属<input type="text" name="division" id="division" value="${user.division}" placeholder="所属">
			加入日<input type="text" id="indate" name="indate"　 value="${user.indate}" readonly="readonly" placeholder="加入日">
			<button id="join" type="submit">情報修正</button>
		</form>
	</div>
	<div class="overlay-container">
		<div class="overlay">
			<div class="overlay-panel overlay-left">
				<p>修正情報を入力してください。
				
			</div>
		</div>
	</div>
</div>
					
  </main>
  <sidebar>
    <div class="logo"><a href="goMain">TeamSchedule</a></div>
    <div class="avatar">
      <div class="avatar__name">${sessionScope.loginName }様 </div>
    </div>
    <nav class="menu">
      <a class="menu__item" href="mypage">
        <i class="menu__icon fa fa-home"></i>
        <span class="menu__text">MｙInfo</span>
      </a>
      <a class="menu__item" href="userList">
        <i class="menu__icon fa fa-envelope"></i>
        <span class="menu__text">招待</span>
      </a>
      <a class="menu__item" href="teamCheck">
        <i class="menu__icon fa fa-list"></i>
        <span class="menu__text">チーム</span>
      </a>
      <a class="menu__item menu__item--active" href="#">
        <i class="menu__icon fa fa-calendar"></i>
        <span class="menu__text">カレンダー</span>
      </a>
      <a class="menu__item" href="#">
        <i class="menu__icon fa fa-bar-chart"></i>
        <span class="menu__text">チーム掲示板</span>
      </a>
      <a class="menu__item" href="#">
        <i class="menu__icon fa fa-trophy"></i>
        <span class="menu__text">achivements</span>
      </a>
      <a class="menu__item" href="logoutUser">
        <i class="menu__icon fa fa-sliders"></i>
        <span class="menu__text">ログアウト</span>
      </a>
    </nav>
  </sidebar>
</div>

</body>
</html>