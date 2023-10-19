$(document).ready((e) => {
  let clickStatus = 0;

  const profile = $(".profile");

  //확장자크기 설정 및 검사
  function checkExtention(fileName, fileSize) {
    var regex = new RegExp("(.*?)\.(exe|sh|zip|alz|txt)$");
    var maxSize = 1024 * 1024 * 5;

    if (fileSize > maxSize) {
      alert("파일 사이즈 초과");
      return false;
    }

    if (regex.test(fileName)) {
      alert("해당 종류의 파일은 업로드할 수 없습니다.");
      return false;
    }
    return true;
  }

  /* mypage 사용자 정보 가져오기 */
  function getUserInfo() {
    $.ajax({
      url     : "/mypage",
      method  : "get",
      dataType: "JSON",
      success : (response) => {
        console.log(response);
        const userInfo = response;

        $("#loginid").val(userInfo.loginid);
        $("#pwd").val(userInfo.pwd);
        $("#nickname").val(userInfo.nickname);
        $("#email").val(userInfo.email);
        $("#createDate").val(userInfo.createDate);
        $("#intro").val(userInfo.intro);
        $("#profile").val(userInfo.profile);
      },
      error   : (xhr, status, error) => {
        console.log(
            "Failed to get user info. Status:",
            status,
            "Error:",
            error
        );
      },
    });
  }


  /*정보수정 시작하기*/
  $(".modifybtn.btn").on("click", function () {
    const inputs = $("input");
    const textareas = $("textarea");

    if (clickStatus === 0) {
      inputs.each(function () {
        if (this.id !== "createDate" && this.id !== "loginid") {
          $(this).removeAttr("readonly");
        }
      });
      textareas.each(function () {
        $(this).removeAttr("readonly");
      });
      $(".modifybtn.btn").text("수정 완료");

      clickStatus = 1;
    } else if (clickStatus === 1) {
      inputs.each(function () {
        $(this).prop("readonly", true);
      });
      textareas.each(function () {
        $(this).prop("readonly", true);
      });

      $(".modifybtn.btn").text("정보 수정");
      clickStatus = 0;


      const data = {};
      $("input").each(function () {
        data[this.id] = $(this).val();
      });
      $("textarea").each(function () {
        data[this.id] = $(this).val();
      });

      $.ajax({
        url    : "/mypage/update",
        method : "POST",
        data   : data,
        success: function (response) {
          console.log("Data successfully sent to the server!");

        },
        error  : function (error) {
          console.log("Failed to send data to the server:", error);
        },
      });
    }
  });

  $("#joinDate").prop("readonly", true);


  //프로필 업로드
  profile.click((e) => {
    $("#img").click();
  });

  $('#img').change((e) => {
    var selectedFile = e.target.files[0];

    if (!selectedFile) {
      return;
    }


    if (!checkExtention(selectedFile.name, selectedFile.size)) {
      return false;
    }

    var fileReader = new FileReader();
    fileReader.onload = function (e) {
      $(".profile").attr("src", e.target.result);
    };
    fileReader.readAsDataURL(selectedFile);


    //프로필 이미지 저장
    var formData = new FormData();
    var inputFile = $("input[name = 'uploadFile']");
    var files = inputFile[0].files;
    formData.append("uploadFile", files[0]);

    $.ajax({
      url        : "/mypage/profile",
      processData: false,
      contentType: false,
      data       : formData,
      type       : "post",
      success    : function (result) {
        console.log("Profile image successfully uploaded to the server!");
      },
      error      : function (error) {
        console.log("Failed to upload profile image:", error);
      }
    });
  });


});
