<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<title>슬기로운 스템프 STP!</title>
<link rel="stylesheet" href="/static/css/dk/Login_find.css">
</head>
<body>
    <div id="id_pwd_frame">
        <div id="title">
            <h1>무엇을 도와드릴까요?</h1>
        </div>
        <div id="id_pw_button">
            <button id="id_but" onclick="location.href='/user/findid'">아이디찾기</button>
            <button id="pw_but" onclick="location.href='/user/findpw'">비밀번호찾기</button>
        </div>
        <button id="main_page" onclick="location.href='/main'"></button>
    </div>
</body>
</html>
