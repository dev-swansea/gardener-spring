$(document).ready(function () {
  const deleteBtn = $(".deletebtn");

  deleteBtn.on("click", function () {
    const confirmation = confirm("회원탈퇴 하시겠습니까?");

    if (confirmation) {
      const url = "/mypage/delete";
      const loginid = $("#loginid").val();

      $.ajax({
        type   : "POST",
        url    : url,
        data   : {loginid: loginid},
        success: function (data, textStatus, xhr) {
          if (xhr.status === 200) {

            sessionStorage.removeItem("name");
            alert("회원탈퇴가 완료되었습니다.");
            window.location.href = "/";

          }
        },
        error  : function (xhr, textStatus, errorThrown) {

          console.error("Error:", errorThrown);
          alert("회원탈퇴를 실패했습니다.");
        }
      });
    }
  });
});
