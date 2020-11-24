<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>슬기로운 스템프 STP!</title>
  <link rel="stylesheet" href="/static/css/dk/Login.css">
  <link rel="stylesheet" href="/static/css/dk/alertify.core.css">
  <link rel="stylesheet" href="/static/css/dk/alertify.default.css">
  <script src="/static/js/dk/alertify.min.js"></script>
  <script src="/static/js/dk/Login.js"></script>
</head>

<body>
<div class="vid-container">
  <video id="videobcg" preload="auto" autoplay="true" loop="loop" muted="muted" volume="0">
    <source src="/static/backgroundvideo/mainvideo.mp4" type="video/mp4">
  </video>
  
  <form name="join" class="join" method="post" onsubmit="return Login();" action="/user/loginProc">
    <h1>Login</h1>
    <input type="text" placeholder="ID" id="uid" name="uid"/>
    <input type="password" placeholder="PASSWORD" id="upassword" name="upassword"/>
    <button name="login_but" id="login_but" type="submit">login</button>
    <p><span id="sign_up" onclick="location.href='/join_new'">Sign up</span><span id="Login_find" onclick="location.href='/user/choiceidpw'">can't find id or password?</span></p>
  </form>
</div>
</body>
</html>
