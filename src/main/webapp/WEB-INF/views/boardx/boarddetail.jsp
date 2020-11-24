<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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
<table align="center" class="table table-hover " class="col-md-10">
    <tbody>
    <tr>
        <td class="tb_top" style="background-color:rgba(255,255,255,0.8);">
          <div >${detail.subject }</div>
        </td>
    </tr>

    <tr style="background-color: #fcfcfc; font-size: 13px">
        <td class="tb_lr minitext">
          <div class="tb_left">
            <span> 작성자 </span>
            <span class="tb_spr">&nbsp;l&nbsp;</span>
            <span> ${detail.writer } </span>
            <span class="tb_spr">&nbsp;l&nbsp;</span>
          <span>${detail.reg_date }</span>
          <span class="tb_spr">&nbsp;l&nbsp;</span>
          <span>조회 ${detail.viewcount }</span>
        </div>
      </td>
    </tr>

    <tr>
    <td valign="top" style="padding-top: 50px; padding-bottom: 50px; background-color:rgb(255,255,255);">
      <div class="memo_content" >
        <span style="font-size: 19px; font-family: dotum;" >${detail.content }</span>
      </div>
    </td>
    </tr>
    <tr>
	<td>
	  <div class="form-group">
    		<p><a href="/board/fileDown/${boardName }/${files.bno }"> ${files.fileOriName }</a>></p>
    	</div>
	</td>
    </tr>
</tbody>
</table> 

<div style="text-align: right;">
  <button type="button" class="btn btn-default" onClick="location.href='/board/lists?id=${boardName}&page=1'">목록으로</button>&nbsp;&nbsp;&nbsp;
  <button type="button" class="btn btn-default" onClick="location.href='/board/modify?id=${boardName}&bno=${detail.bno }'">글수정</button>&nbsp;
  <button type="button" class="btn btn-default" onClick="location.href='/board/delete?id=${boardName}&bno=${detail.bno }'">글삭제</button>
</div>

<hr/>

<div class="container">
	<div class="commentList"></div>
</div>


<div class="container">
	<label for="content">comment</label>
	<form name="commentInsertForm">
		<div class="input-group">
			<input type="hidden" name="bno" value="${detail.bno }"/>
			<input type="hidden" name="boardName" value="${boardName }"/>
			<input type="text" class="form-control" id="content" name="content" placeholder="내용을 입력하세요">
			<input type="text" style="display: none;" />
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" name="commentInsertBtn">등록</button>
			</span>
		</div>
	</form>
</div>


</div>
  </div>
</div>
<%@ include file="commentS.jsp" %>

</body>
</html>
