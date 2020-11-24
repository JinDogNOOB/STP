<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>X로그인</title>
  <link rel="stylesheet" href="/static/css/login.css">
  <script src="/static/js/login.js"></script>
</head>

<body>
<div class="vid-container">
  <video id="videobcg" preload="auto" autoplay="true" loop="loop" muted="muted" volume="0">
    <source src="/static/backgroundvideo/mainvideo.mp4" type="video/mp4">
  </video>
  
  <form name="join" class="join" method="post" onsubmit="return Login();" action="/user/loginProc">
    <h1>로그인</h1>
    <input type="text" placeholder="ID" id="uid" name="uid"/>
    <input type="password" placeholder="PASSWORD" id="upassword" name="upassword"/>
    <button name="login_but" id="login_but" type="submit">확인</button>
    <p><span id="sign_up" onclick="location.href='/join_new'">회원가입</span><span id="Login_find" onclick="location.href='/temp'">잊어버렸음</span></p>
  </form>
</div>
</body>
</html>
