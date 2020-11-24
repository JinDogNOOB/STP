<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
  <title>슬기로운 스템프 STP!</title>
  <meta charset="utf-8">
  <meta name="viewport">
  <link rel="stylesheet" href="/static/css/dk/main_frame.css">
  <script type="text/javascript">
    self.moveTo(500,500);
    self.resizeTo();
  </script>
</head>
<body>
<div class="frame" id="grid_main">
  <div id="frame1">
    <div id="frame1_main_menu_frame">
      <div id="frame1_main_name" onclick="location.href='' +
       'webhacking.html'"></div>
      <div class="frame1_main_list">
        <div class="frame1_list_name main_list_hover1">
          <h1 class="h1">&nbsp;&nbsp;&nbsp;&nbsp;Game main title</h1>
        </div>
        <div class="frame1_list1 list_hover1">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;name
        </div>
        <div class="frame1_list2 list_hover2">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;name
        </div>
        <div class="frame1_list3 list_hover3">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;name
        </div>
        <div class="frame1_list4 list_hover4">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;name
        </div>
        <div class="frame1_list_name2 main_list_hover2">
          <h1 class="h1">&nbsp;&nbsp;&nbsp;&nbsp;Game main title</h1>
        </div>
        <div class="frame1_list5 list_hover5">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;name
        </div>
        <div class="frame1_list6 list_hover6">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;name
        </div>
      </div>
    </div>
    <div id="frame1_main_login_frame">
      <button type="button" class="login_button hover1" onclick="location.href='/user/login'">로그인</button>
      <button type="button" class="signup_button hover2" onclick="location.href='/join_new'">가입하기</button>
    </div>
  </div>
  <div class="frame2"></div>
</div>
</body>
</html>
