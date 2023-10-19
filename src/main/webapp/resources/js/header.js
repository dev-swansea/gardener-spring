$(document).ready(function () {
  // 로그인 버튼 클릭 시 모달 열기
  $("#loginButton").click(function () {
    $("#loginModal").show();
  });

  // 모달 외부 영역 또는 닫기 버튼을 클릭하면 모달 닫기
  $("#loginModal, .modal-close").click(function (event) {
    if (event.target == this) {
      $("#loginModal").hide();
    }
  });
});