<%@ page language="java" contentType="text/html; charset=UTF-8" %>
  <!DOCTYPE html>
  <html lang="en">

  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="/resources/js/find_id.js"></script>
    <link rel="stylesheet" href="/resources/css/find_id.css">
    <title>아이디 찾기</title>
  </head>


  <!--로고-->

  <body>
    <div class="logo">
	    <a href="./">
	      <img src="/resources/images/logo.png">
	    </a>
    </div>

    <!--내용 입력-->
    <form class="find-loginId">
      <input type="text" name="name" placeholder="필명을 입력해주세요"><br>
      <input type="email" name="email" placeholder="이메일을 입력해주세요"><br>
      <!-- <input type="button" name="button" value="인증번호 전송"> -->
      <!-- <input type="number" name="number"placeholder="인증번호 숫자입력"> -->
      <button class="bt-find-id" name=button>아이디 찾기</a></button><br>
    </form>
  </body>

  </html>