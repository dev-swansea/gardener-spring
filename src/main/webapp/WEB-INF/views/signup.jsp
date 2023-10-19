<%@ page language="java" contentType="text/html; charset=UTF-8" %>
  <!DOCTYPE html>
  <html lang="en">

  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  
    <link rel="stylesheet" href="/resources/css/signup.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
      
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="/resources/js/signup.js"></script>    
   
    
    <title>회원가입</title>
  </head>

  <body>
    <form class="signup">
    <!-- 로고 -->
     <div class="signupLogo">
		<img src="/resources/images/logo_small.png" />
	 </div>    
      <!--프로필 이미지 첨부-->
     <div>
       <img class="profile" src="/resources/images/profile.png" />   
      	<input type="file" id=img name="uploadFile" style="display:none" >        
	     <div class="profileText">profile</div>
     </div>
      
     
      
     <span class="placehold-text">
    	<input type="text" class="id form-control" name="loginid" placeholder="아이디" required/>
  	  </span>
      <div>
        <input type="button" class="iddupchk" value="아이디 중복 확인" />    
      </div>     
      <div>
        <input class="pwd form-control" type="password" name="pwd" placeholder="비밀번호" required />
      </div>    
        <input class="pwd confirm form-control" type="password" placeholder="비밀번호확인" required />
      <span class="placehold-text">
      	<input type="email" name="email" class="form-control" placeholder="이메일" required/>
      </span>         
      <span class="placehold-text">
      	<input type="text" name="nickname" class="form-control" placeholder="필명" required/>
      </span>
      <div>
        <button type="button" class="namedupchk" />필명 중복 확인</button>
      	<textarea type="text" name="intro" class="form-control" maxlength=100 rows="3" cols="30" placeholder="자기소개를 입력해주세요" required></textarea>
       	  <div>
          <button class="bt-Signup">회원 가입</button>
          
          </div>
      </div>
    </form>
    <div class="footer"></div>
  </body>

  </html>