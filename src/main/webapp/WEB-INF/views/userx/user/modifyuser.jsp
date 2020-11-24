<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Full Page Background Image | Progressive</title>
  <link rel="stylesheet" href="my_info.css">
  <script src="my_info.js">
  </script>
</head>
<body>
<form id="page-wrap" method="post" name="my_info_frame" onsubmit="return personal_info_modify();" action="/user/modifyProc" enctype="multipart/form-data">
  <div>
    <input id="nickname_frame" type="text" name="uname" placeholder="Nick Name">
  </div>
  <div>
    <input id="password_frame" type="password" name="upassword" placeholder="Password">
  </div>
  <div>
    <input id="password_retry_frame"  type="password" name="password_retry" placeholder="Re-enter Password">
  </div>
  <div>
    <input type="file" name="files">
  </div>
  <div>
    <input id="info_modify" type="submit" name="info_modify_but" value="입력">
  </div>
  <div>
    <input id="reset" type="reset" value="초기화">
  </div>
  <div>
    <input id="withdrawal" type="button" value="므야이건">
  </div>
</form>

<style type="text/css" style="display: none !important;">
  * {
  margin: 0;
  padding: 0;
  }
  body {
  overflow-x: hidden;
  }
  #demo-top-bar {
  text-align: left;
  background: #222;
  position: relative;
  zoom: 1;
  width: 100% !important;
  z-index: 6000;
  padding: 20px 0 20px;
  }
  #demo-bar-inside {
  width: 960px;
  margin: 0 auto;
  position: relative;
  overflow: hidden;
  }
  #demo-bar-buttons {
  padding-top: 10px;
  float: right;
  }
  #demo-bar-buttons a {
  font-size: 12px;
  margin-left: 20px;
  color: white;
  margin: 2px 0;
  text-decoration: none;
  font: 14px "Lucida Grande", Sans-Serif !important;
  }
  #demo-bar-buttons a:hover,
  #demo-bar-buttons a:focus {
  text-decoration: underline;
  }
  #demo-bar-badge {
  display: inline-block;
  width: 302px;
  padding: 0 !important;
  margin: 0 !important;
  background-color: transparent !important;
  }
  #demo-bar-badge a {
  display: block;
  width: 100%;
  height: 38px;
  border-radius: 0;
  bottom: auto;
  margin: 0;
  background: url(/images/examples-logo.png) no-repeat;
  background-size: 100%;
  overflow: hidden;
  text-indent: -9999px;
  }
  #demo-bar-badge:before, #demo-bar-badge:after {
  display: none !important;
  }
</style>
