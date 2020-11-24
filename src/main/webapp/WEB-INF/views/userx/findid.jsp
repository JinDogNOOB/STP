<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<title>슬기로운 스템프 STP!</title>
    <link rel="stylesheet" href="/static/css/dk/id_find.css">
    <link rel="stylesheet" href="/static/css/dk/alertify.core.css">
    <link rel="stylesheet" href="/static/css/dk/alertify.default.css">
    <script src="static/js/dk/alertify.min.js"></script>
    <script src="static/js/dk/id_find.js"></script>
</head>
<body>
    <div id="id_pwd_frame">
        <div id="title">
            <h1>아이디 찾기</h1>
        </div>
        <div id="id_pw_button">
            <form id="idfind" name="idfind" method="post" onsubmit="return id_find();" action="/user/findidProc">
                <input type="email" id="email" name="email" placeholder="이메일을 입력하세요"/>
                <input type="hidden" id="hidden"/>
                <input type="submit" id="id_find_but" name="id_find_but" value="아이디 찾기"/>
            </form>
        </div>
        <button id="main_page" onclick="location.href='/main'"></button>
    </div>
</body>
</html>
