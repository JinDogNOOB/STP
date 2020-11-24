<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>회원가입</title>
  <link rel="stylesheet" href="/static/css/signup.css">
  <script src="/static/js/signup.js"></script>
</head>

<body>
<div id="sign_up_frame">
  <form id="sign_up" name="sign_up" method="post" onsubmit='return sign();' action="/join_check">
    <div id="login-box">
      <h1>Sign up</h1>
      <input type="text" id="uid" name="uid" maxlength="20" placeholder="ID" /> 
      <input type="text" id="uname" name="uname" maxlength="12" placeholder="꼭 실명이 아니어도됨"/> 
      <input type="text" id="email" name="email" placeholder="E-mail" /> 
      <input type="password" id="upassword" name="upassword" maxlength="15" min="8" placeholder="Password" />
      <input type="password" name="password2" maxlength="15" min="8" placeholder="Retype password"/>
      <span>
        <button id="ID_check" type="button" name="overlap" value="ID Check">ID 체크 </button>
        <button id="signup_submit" type="submit" name="signup_submit">제출 </button>
        
      </span>
    </div>
  </form> 
</div>
</body>
</html>

