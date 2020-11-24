<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<link rel="stylesheet" href="/static/css/shop/bootstrap.css">
<head>
    <meta charset="UTF-8">
    <title>슬기로운 스템프 STP!</title>
</head>
<body>



<!--상단 메뉴 바-->
<nav role="navigation" class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <a href="/welcome"><img src="/static/backgroundimages/board/logo.png" ></a>
    </div>
    <div id="navbarCollapse" class="collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <li><a href="/board/shop/lists?shopId=sale">할인</a></li>
        <li><a href="/board/shop/lists?shopId=skin">스킨</a></li>
        <li><a href="/board/shop/lists?shopId=cloth">의류</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="/user/info/inventory">인벤토리</a></li>
        <li><a href="/user/logout">로그아웃</a></li>
      </ul>
    </div>
  </div>
</nav>
<!--상단 메뉴 바-->
<br>


<div class="video-hero jquery-background-video-wrapper demo-video-wrapper">
  <video class="jquery-background-video" preload="auto" autoplay="true" loop="loop" muted="muted" volume="0">
    <source src="/static/backgroundvideo/shopmain.mp4" type="video/mp4">
  </video>
  <div class="video-overlay"></div>
  <div class="page-width">
    <div class="video-hero--content">
      <h2>Welcome to</h2>
      <p>CS:GO Market</p>
    </div>
  </div>
</div>

<script type="text/javascript" src="/static/js/shop/Shopjs.js"></script>
</body>
</html>
