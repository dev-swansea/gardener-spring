$(() => {
  
  //모달 창 띄우기
  $('li.nav-item.ms-5 a.nav-link[href="/signup"]').click(function(e) {
	e.preventDefault(); 	
	$('#signupModal').modal('show');
  });	
	
  //모달 close버튼
  $("#signupClosebtn").on("click", () =>{
  location.href = "/"; //페이지 이동
  })	 

  //DOM트리에서 form객체찾기
  const formObj = $("form.signup");  
  
  //프로필 객체
  const profile =  $(".profile");  
  
  //ID 중복확인 버튼 객체
  const btIdDupChk = $("form.signup input.iddupchk"); 
  
  //비밀번호
  const pwd = $("form.signup input.pwd");
  
  //비밀번호 확인
  const pwdCfm =$("form.signup input.confirm");
  
  //이메일
  const email = $("form.signup input[name=signupid]");
  
  //필명
  const nickname = $("form.signup input[name=nickname]");
  
  //필명 중복확인 버튼 객체 찾기
  const btNameDupchk = $("form.signup button.namedupchk");

  //가입버튼 객체 찾기
  const btSignup = $("form.signup.bt-Signup"); 	

 


  //프로필 업로드
   profile.click(() => {    	
 	 $("#img").click();
   });

  $('#img').change((e) => {  
   var selectedFile = e.target.files[0];	

    // 이미지 요소의 src 속성을 선택한 파일의 URL로 설정
    if (selectedFile) {
        var fileReader = new FileReader();
        fileReader.onload = function (e) {
            $(".profile").attr("src", e.target.result);
        };
        fileReader.readAsDataURL(selectedFile);
        }   	
  });
  
  //ID 중복확인    
  btIdDupChk.click(() => {   
    const signupid = $("form.signup input[name='signupid']").val();

  	//아이디 입력란이 빈칸인 경우
  	if (signupid == '') {
  	  swal('ID를 입력하세요');
  	  return;
  	}
  	
  
    $.ajax({
      url: "/id_chk",
      method: "get",
      data: "loginid=" + signupid , 
      success: (response) => {
        console.log(response);
        if (!response) {
          swal("사용 가능한 아이디 입니다");        
        } else {
          swal("중복된 아이디 입니다, 다른 아이디를 입력해주세요");
        }
      },
    });
  });


  //Nickname중복확인
  btNameDupchk.click(() => {  
  
  //아이디 중복확인 버튼 객체 찾기
	 const btIdDupChk = $("form.signup input.iddupchk");  	
	 const nickname = $("input[name='nickname']").val();
    //필명 입력란이 빈칸인 경우
  	if (nickname == '') {
  	  swal('필명을 입력하세요');
  	  return;
  	}  
     
	  $.ajax({
	    url: "/name_chk",
	  	method: "get",
	    data: "nickname=" + nickname,  
	    success: (response) => {	      
	      if (!response) {
	        swal("사용 가능한 필명 입니다");
	      } else {
         	swal("중복된 필명 입니다. 다른 필명을 입력해주세요");
       }
     },
   });
 });

  //회원가입
  formObj.submit((e) => {    
  	e.preventDefault();
    
  	//1.비밀번호 확인
	if (pwd.val() != pwdCfm.val()){	
		alert("비밀번호를 다시 입력해주세요");
		return;
	}
	
  	//2. 프로필 이미지 업로드 
	var formData = new FormData();	
  	var inputFile = $("input[name = 'uploadFile']");    
    var files = inputFile[0].files; 
    

  	formData.append("uploadFile", files[0]);
  
  	$.ajax({
	  	url: "/profile",
	  	processData: false,
	  	contentType: false,
	  	data: formData,
	  	type: "post",
	  	success: function(result){	 	  	
	  	}  	
   	});

  		
   	   	
  	//3. 회원가입  
 	let data = $(e.target).serialize();
	data += `&loginid=${$('.id').val()}`; // id.val() 값을 data에 추가
	
	$.ajax({
	  url: "/signup",
	  method: "post",
	  data: data,
	  success: (response) => {	    
	      alert("회원가입이 되었습니다");
	      window.location.href = "/"; // 페이지 이동
	  },
	  error: (xhr) => {
	    alert("회원가입 실패했습니다");
	  },
	});
	
	return false;

  });
});
