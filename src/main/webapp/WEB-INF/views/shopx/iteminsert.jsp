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

<div class="container">
  <br><br>
</div>
<div class="container">
  <div class="jumbotron">
          <div class="well bs-component">
            <form action="/board/shop/insertProc?shopId=${shopId}" method="post" enctype="multipart/form-data" class="form-horizontal">
              <legend>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 판매아이템 등록</legend>

                <div class="form-group">
                  <label for="inputFile" class="col-lg-2 control-label">상품 이미지</label>
                  <div class="col-lg-10">
                    <div class="form-control-wrapper fileinput">
                      <input type="text" readonly="" class="form-control empty">
                      <input type="file" id="files" name="files">
                  </div>
                    <br>
                </div>

                  <div class="form-group">
                    <label class="col-lg-2 control-label">상품 이름</label>
                    <div class="col-lg-10">
                      <input id="itemname" name="itemname" type="text" rows="2" class="form-control">
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="col-lg-2 control-label">상품 가격</label>
                    <div class="col-lg-10">
                      <input id="price" name="price" type="text" rows="2" class="form-control">
                  </div>
                  </div>

                  <div class="form-group">
                  <div class="col-lg-10 col-lg-offset-2">
                    <button type="submit" class="btn btn-primary">등록</button>

                  </div>
                  </div>

                </div>
            </form>
          </div>
        </div>
      </div>
<script type="text/javascript" src="/static/js/shop/Cart.js"></script>
</body>
</html>
