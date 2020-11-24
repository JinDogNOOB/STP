<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>슬기로운 스템프 STP!</title>
  <link rel="stylesheet" href="/static/css/dk/signup.css">
  <link rel="stylesheet" href="/static/css/dk/alertify.core.css">
  <link rel="stylesheet" href="/static/css/dk/alertify.default.css">
  <script src="/static/js/dk/signup.js"></script>
  <script src="/static/js/dk/alertify.min.js"></script>
</head>

<body>
<div id="sign_up_frame">
  <video id="videobcg" preload="auto" autoplay="true" loop="loop" muted="muted" volume="0">
    <source src="/static/backgroundvideo/mainvideo.mp4" type="video/mp4"></video>
  <div id="sub_sign_up_frame">
  <form id="sign_up" name="sign_up" method="post" onsubmit="return sign();" action="/join_check">
    <div id="login-box">
      <h1>Sign up</h1>
      <input type="text" name="uid" id="uid" maxlength="20" placeholder="ID" />
      <input type="text" name="uname" id="uname" maxlength="12" placeholder="nickname"/>
      <input type="email" name="email" id="email" placeholder="E-mail" />
      <input type="password" name="upassword" id="upassword" maxlength="15" min="8" placeholder="Password" />
      <input type="password" name="password2" id="upassword2" maxlength="15" min="8" placeholder="Retype password"/>
      <span>
            <button id="signup_submit" type="submit" name="signup_submit" value="Sign me up">Sign up</button>
      </span>
    </div>
  </form>
  </div>
</div>
</body>
</html>

