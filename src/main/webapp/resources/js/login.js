//$(() => {
//  const url = "http://localhost:8888";
//  const formObj = $("form.login");
//
//  //아이디저장 체크박스
//  const cbObj = formObj.find("input[type=checkbox]");
//  const savedId = localStorage.getItem("savedId");
//  if (savedId != null) {
//    formObj.find("input[name-loginId]").val(savedId);
//  }
//
//  formObj.submit((e) => {
//    if (cbObj.prop("checked")) {
//      //체크된 경우
//      const idValue = formObj.find("input[name=id]").val();
//      localStorage.setItem("savedId", idValue);
//    } else {
//      //체크 해제된 경우
//      localStorage.removeItem("savedId");
//    }
//
//
//    /*로그인*/
//    const data = $(e.target).serialize();
//    alert(data);
//    $.ajax({
//      url    : "/login",
//      method : "post",
//      data   : data,
//      success: (response) => {
//        alert(response, "response")
//        if (response.trim() == 1) {
//          //로그인 성공인 경우
//          swal("환영합니다");
//          location.href = "/"; //페이지 이동
//        } else if (response.trim() == 0) {
//          //로그인 실패인 경우
//          $("form.login>input[name=id]").focus();
//        }
//      },
//      error  : (xhr) => {
//        swal("잘못된 정보입니다. 다시 입력해주세요.");
//
//      },
//    });
//    return false;
//  });
//});
