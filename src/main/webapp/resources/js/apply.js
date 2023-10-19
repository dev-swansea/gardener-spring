$(document).ready(() => {
  const data = {loginid: $("#loginid").val()};
  console.log(data, "data")
  console.log(typeof data, "data")

  // 작가 신청 제출하기
  $("#applybtn").on("click", function () {
    $.ajax({
      url    : "/apply",
      type   : "POST",
      data   : data,
      success: (response) => {
        if (response === "true") {
          alert("제출되었습니다");
          window.location.href = "/";
        }
      },
      error  : function (error) {
        alert(error);
        console.log("Failed to send data to the server:" + error);
        alert("제출에 실패하였습니다");
      },
    });
  });
});
