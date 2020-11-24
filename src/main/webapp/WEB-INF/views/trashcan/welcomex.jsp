<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
  <title>X웨르껌마이뿌랜드</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="/static/css/main2.css">
  <script src="/static/js/profile.js"></script>
  <style type="text/css">
	a:link {color:#ff0000;text-decoration:none}
	a:visited {color:#ff0000;text-decoration:none}
	a:active {color:#ff0000;text-decoration:none}
	a:hover {color:#ff0000;text-decoration:none}
  </style>

</head>

<body>
<div class="frame" id="grid_main">
  <div id="frame1">
    <div id="frame1_main_menu_frame">
      <div id="frame1_main_name" onclick="location.href='/welcome'"></div>
      <div class="frame1_main_list">
        <div class="frame1_list_name main_list_hover1">
          <h1 class="h1">&nbsp;&nbsp;&nbsp;&nbsp;게임 게시판</h1>
        </div>
        <div class="frame1_list1 list_hover1">
          <a href="/board/lists?id=notice&page=1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;공지사항</a>
        </div>
        <div class="frame1_list2 list_hover2">
          <a href="/board/lists?id=free&page=1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;자유게시판</a>
        </div>
        <div class="frame1_list3 list_hover3">
          <a href="/board/lists?id=csgo&page=1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CS : GO</a>
        </div>
        <div class="frame1_list4 list_hover4">
          <a href="/board/lists?id=pubg&page=1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;배틀그라운드</a>
        </div>
         <div class="frame1_list5 list_hover5">
          <a href="/board/lists?id=hots&page=1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;시공의 폭풍</a>
        </div>
        <div class="frame1_list_name2 main_list_hover2">
          <h1 class="h1">&nbsp;&nbsp;&nbsp;&nbsp;자유 상점</h1>
        </div>
        <div class="frame1_list6 list_hover6">
         <a href="/board/shop"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 상점 </a>
        </div>
      </div>
    </div>
    <div id="profile">
      <div id="profile_img_frame">
        <img id="profile_img" src="/static/userthumnail/${thumbnailname}">
        <div>
        <h3>${session.uname}(${session.uid}) ${session.auth}상태</h3>
        </div>
          <div class="flip-container" ontouchstart="this.classList.toggle('hover');">
            <div class="flipper">
              <div class="front">포인트</div>
              <div class="back">${money }p</div>
            </div>
          </div>
          <div class="flip-container2" ontouchstart="this.classList.toggle('hover');">
            <div class="flipper2">
              <div class="front2">내정보</div> 
              <div class="back2" onclick="location.href='/user/modify'">수정</div>
            </div> 
          </div>
      </div>
    </div>
  </div>
</div>

<div class="frame2">
</div> 
</body>
</html>
