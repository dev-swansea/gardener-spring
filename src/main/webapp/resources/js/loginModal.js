$(() => {
  $('li.nav-item.ms-5 a.nav-link[href="/login"]').click(function (e) {
    e.preventDefault();
    $('#myModal').modal('show');
  });

  $("#closebtn").on("click", () => {
    location.href = "/"; //페이지 이동
  })

  $(".login").submit((e) => {
    e.preventDefault();
    const data = $(e.target).serialize();
    $.ajax({
      url    : "/login",
      method : "post",
      data   : data,
      success: (response) => {
        //로그인 성공인 경우
        swal("환영합니다");
        location.href = "/"; //페이지 이동
      },
      error  : (xhr) => {
        swal("잘못된 정보입니다. 다시 입력해주세요.");
        $("form.login>input[name=id]").focus();
      }
    });

    e.submit();
  })
});