<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<title>슬기로운 스템프 STP!</title>
  <link rel="stylesheet" href="/static/css/dk/pw_find.css">
  <link rel="stylesheet" href="/static/css/dk/alertify.core.css">
  <link rel="stylesheet" href="/static/css/dk/alertify.default.css">
  <script src="/static/js/dk/alertify.min.js"></script>
  <script src="/static/js/dk/pw_find.js"></script>
</head>
<body>
    <div id="id_pwd_frame">
        <div id="title">
            <h1>비밀번호 찾기</h1>
        </div>
        <div id="id_pw_button">
            <form name="pwfind" method="post" onsubmit="return pw_find();" action="/user/info/getpwurl">
                <input type="text" id="uid" name="uid" placeholder="아이디를 입력하세요">
                <input type="email" id="email" name="email" placeholder="이메일을 입력하세요">
                <input type="submit" id="id_find_but" name="id_find_but" value="비밀번호 찾기">
            </form>
        </div>
        <button id="main_page" onclick="location.href='/main'"></button>
    </div>
</body>
</html>
