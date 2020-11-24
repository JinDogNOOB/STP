<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<title>슬기로운 스템프 STP!</title>
<link rel="stylesheet" href="/static/css/board/bootstrap.css" href="/static/css/board/writing.css">
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
        <li><a href="/board/lists?id=notice&page=1">공지사항</a></li>
        <li><a href="/board/lists?id=csgo&page=1">CS:GO 토론방</a></li>
        <li><a href="/board/lists?id=free&page=1">자유 토론방</a></li>
        <li><a href="/board/lists?id=pubg&page=1">PUBG 토론방</a></li>
        <li><a href="/board/lists?id=hots&page=1">시공의 폭풍</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="/user/info/mypage">마이페이지</a></li>
        <li><a href="/user/logout">로그아웃</a></li>
      </ul>
    </div>
  </div>
</nav>
<!--상단 메뉴 바-->
<br>

<div class="container" >
  <div class="jumbotron" style="background-color:rgba(0,0,0,0.8);">

<div class="container">
<form  action="/board/writeProc?id=${boardName}" method="post" enctype="multipart/form-data">
	<h2 style="font-family: Helvetica Nene; margin-bottom:40px; font-weight: bolder; color: white;" > 새 글쓰기</h2>
  <div class="form-group">
    <label for="exampleInputEmail1" style="color: white;">글 제목</label>
    <input type="text" class="form-control" id="exampleInputEmail1" name="subject" placeholder="title">
  </div>

  <div class="form-group" style="color: white;">
    <label>글 내용</label>
    <textarea class="form-control" rows="10" id="comment" name="content" placeholder="contents"></textarea>
  </div>
<hr/>


<div class="form-group" >
 <label class="col-sm-2 control-label" style="color: white;">첨부파일</label>
 <div class="col-sm-10">
  <div class="input-group">
   <label class="input-group-btn">
    <span class="btn btn-default btn-file">
      <input type="file" name="files" data-display-target="attachFile">
    </span>
   </label>
   <input type="text" style="margin-left: 10px" class="form-control" readonly="" id="attachFile" placeholder="지원되는 파일 양식: jpg, png, gif, pdf, doc, docx, xls, xlsx, ppt, pptx">
  </div>
 </div>
</div>

<div style="text-align: right">
	<button type="button" class="btn btn-default" onClick="location.href='/board/lists?id=${boardName}&page=1'">목록으로</button>&nbsp;&nbsp;&nbsp;
	<button type="reset" class="btn btn-default" >초기화</button>&nbsp;
	<button type="submit" class="btn btn-default">글작성</button>
</div>


</form>
</div>
  </div>
</div>

</body>
</html>
