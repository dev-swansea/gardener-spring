$(document).ready(function () {
  const cancelBtn = $(".cancel.btn");

  cancelBtn.on("click", function () {
    const confirmation = confirm("작가를 취소하시겠습니까?");

    if (confirmation) {
      const url = "/mypage/applydelete";
      const loginid = $("#loginid").val();

      $.ajax({
        type   : "POST",
        url    : url,
        data   : {loginid: loginid},
        success: function (data, textStatus, xhr) {
          if (xhr.status === 200) {
            alert("작가가 취소되었습니다.");
            $(".applybtn").text("작가신청")
            window.location.href = "/mypage";

          }
        },
        error  : function (xhr, textStatus, errorThrown) {
          console.error("Error:", errorThrown);
          alert("작가 취소를 실패했습니다.");
        }
      });
    }
  });
});