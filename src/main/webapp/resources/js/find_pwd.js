$(document).ready(() => {
  const url = "http://localhost:8888";

  //비밀번호 찾기 버튼 객체 찾기
  const btFindpwd = $('form.find-pwd button.bt-find-pwd')

  btFindpwd.click(() => {
    const id = $("input[name='loginId']").val();
    const email = $("input[name='email']").val();
    $.ajax({
      url    : "/find_pwd",
      method : "post",
      data   : {loginid: id, email: email},
      success: (response) => {
        if (response != '0') {
          alert('고객님의 비밀번호는 ' + response + ' 입니다')
          location.href = "/login";
        } else {
          alert('잘못된 정보입니다. 다시 입력해주세요')
        }
      },
      error  : () => {
        alert('잘못된 정보입니다. 다시 입력해주세요')
      }
    })
    return false;
  })
})



