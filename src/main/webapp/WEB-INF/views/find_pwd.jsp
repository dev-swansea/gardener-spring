<%@ page language="java" contentType="text/html; charset=UTF-8" %>
  <!DOCTYPE html>
  <html lang="en">

  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="/resources/js/find_pwd.js"></script>
    <link rel="stylesheet" href="/resources/css/find_pwd.css">
    <title>비밀번호 찾기</title>
  </head>

  <!--로고-->

  <body>
    <div class="logo">
     	<a href="./">
	      <img src="/resources/images/logo.png">
	    </a>
    </div>

    <!--내용 입력란-->
    <form class="find-pwd">
      <input type="text" name="loginId" placeholder="아이디를 입력해주세요"><br>
      <input type="email" name="email" placeholder="이메일을 입력해주세요"><br>
      <!--<input type="button" name="button" value="인증번호 전송">-->
      <!--<input type="number" name="number"placeholder="인증번호 입력">-->
      <!--<input type="button" name="check" value="인증확인">-->
      <!--<input type="password" name="pwd"placeholder="변경할 비밀번호입력">-->
      <!--<input type="password" name="pwd"placeholder="변경할 비밀번호확인">-->
      <button class="bt-find-pwd">비밀번호 찾기</button><br>    
    </form>



  </body>

  </html>