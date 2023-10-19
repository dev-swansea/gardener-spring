<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script src="https://code.jquery.com/jquery-3.7.0.min.js"
          integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
  <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
  <script src="/resources/js/post.js"></script>
  <link rel="stylesheet" href="/resources/css/post.css">
  <title>Document</title>
</head>

<body>

<div style="margin: 0 auto;  text-align: center;">
  <a href="/">
    <img src="/resources/images/logo_small.png" alt="로고이미지">
  </a>
</div>

<!-- 섹션 시작 -->
<section>
  <!-- 섹션 헤더 시작 -->
  <div class="section-header-image">
    <div class=" main-image"></div>
  </div>
  <!-- 섹션 헤더 제목 시작 -->
  <div class="section-header-title">
    <div class="section-header-main-title">대제목</div>
    <div class="section-header-main-subtitle">물리학 하는 사람들은 로맨틱하다.</div>
  </div>
  <!-- 섹션 헤더 제목 끝 -->
  <!-- 섹션 헤더 글 정보 시작 -->
  <div class="section-header-info">
    <div class="section-header-info-writer">이완</div>
    <div class="section-header-info-date">2023-07-26</div>
  </div>
  <!-- 섹션 헤더 글 정보 끝 -->
  <!-- 섹션 헤더 끝 -->

  <!-- 아티클 시작 -->
  <article>
  </article>
  <!-- 아티클 끝 -->

  <!-- 수정, 삭제, 저장 버튼 시작 -->
  <!-- 내 글을 확인할 때는 수정, 삭제만 보이게, 다른 사람 글은 반대로 -->
  <div class="btn-wrap">
    <!-- 섹션 헤더 엄지척,신고 시작 -->
    <div class="section-header-icon">
      <div>
        <img src="/resources/images/thumbs-up.png" alt="좋아요 표시" class="favorite">
        <span>5</span>
        <div><img src="/resources/images/report.png" alt="신고 이미지" class="report"></div>
      </div>
    </div>
    <!-- 섹션 헤더 엄지척,신고 끝 -->
    <div class="modify-btn">
      <div>
        <button>수정</button>
        <button>삭제</button>
      </div>
    </div>
  </div>
  <!-- 수정, 삭제, 저장 버튼 끝 -->

  <!-- 작가 프로필 시작 -->
  <hr>
  <div class="writer-profile">
    <div class="writer-profile-info">
      <div class="writer-profile-name">이수완</div>
      <div class="writer-profile-intro">충남 보령시에서 왔수다</div>
    </div>
    <div class="writer-profile-pic">
      <img src="/resources/images/profile.png" alt="작가 프로필 이미지">
      <button>구독하기</button>
    </div>
  </div>
  <!-- 작가 프로필 끝 -->

  <!-- 댓글 쓰기 -->
  <div class="section-reply">
    <div>
      <div>
        <p>첫 댓글을 남겨주세요</p>
        <p>(x/200)</p>
      </div>
      <textarea></textarea>
      <button>댓글작성</button>
    </div>
    <hr>
  </div>

  <%--댓글 표시--%>
  <div class="section-reply-list"></div>

  <!-- 페이지네이션 시작 -->
  <div class="pagination-wrap"></div>

</section>
<!-- 섹션 끝 -->

<!-- 푸터 -->
<div class="footer"></div>
<input type="hidden" value="${sessionScope.member.nickname}" class="nickname">
<script>
  let postResponse = "";
  try {
    postResponse = ${post};
  } catch (error) {
    postResponse = "";
  }
  const subscribeResponse = '${subscribe}';
  const writerBtn = $(".writer-profile-pic > button")
  let pageNum = 1;

  $(".main-image").css("background-image", `url(\${postResponse?.mainTitleImg})`)
  $(".section-header-main-title").html(postResponse.mainTitle);
  $(".section-header-main-subtitle").html(postResponse?.subTitle || "");
  $(".section-header-info-writer").html(postResponse.member.nickname);
  $(".section-header-info-date").html(postResponse.createDate);
  $("article").html(postResponse.content);
  $(".section-header-icon span").html(postResponse.favorite);
  $(".writer-profile-name").html(postResponse.member.nickname);
  $(".writer-profile-intro").html(postResponse.member.intro);
  $(".writer-profile-pic img").attr("src", postResponse.member.profile)
  writerBtn.attr("data-writer", postResponse.member.loginid)

  // 글자수 체크
  $(".section-reply textarea").keyup((e) => {
    let reply = $(e.target).val().trim().length;
    $(".section-reply p:last-child").text(`(\${reply}/200)`)

    if (reply > 200) {
      $(e.target).val($(e.target).val().substring(0, 200));
      $(".section-reply p:last-child").text(`(200/200)`)
      swal("200글자 이하만 가능합니다.")
    }
  })


  if ("${sessionScope.member.loginid}" === $(".writer-profile-pic button").data("writer")) {
    $(".writer-profile-pic button").css("display", "none");
  }

  // 좋아요 확인 요청
  const checkFavorite = () => {
    postService.checkFavorite(postResponse.postnum, (response) => {
      let count = response?.no?.length || 0;
      $(".section-header-icon img[alt='좋아요 표시']").removeClass("click");
      if (response?.yes?.length > 0) {
        count += 1;
        $(".section-header-icon img[alt='좋아요 표시']").addClass("clicked");
      }
      $(".section-header-icon span").html(count);
    });
  };
  if (subscribeResponse !== "") {
    // 아.. 개선 필요
    checkFavorite();
  }


  // 댓글 리스트 불러오기
  const showList = (page) => {
    replyService.getAllReply({postnum: postResponse.postnum, page: page || 1}, (response) => {
      $(".section-reply p:first-child").text(`\${response.replyCnt}개`);
      let reply = "";


      // 새로운 댓글 달았을때 뒤로 페이지 이동
      if (page === -1) {
        pageNum = Math.ceil(response.replyCnt / 5.0); // 이건 10개씩 보여주기로 한 기준이라 10으로 나눈건가?? 난 5로 보여줘야하는데 5로 바꿀까?
        showList(pageNum);
        return;
      }

      if (response.list.length === 0 || response.list === null) {
        $(".section-reply-list").html("");
        $(".pagination-wrap").html("");
        return;
      }

      for (let i = 0, len = response.list.length || 0; i < len; i++) {
        let dateTime = replyService.displyTime(response.list[i].createDate);
        reply += `<div class='section-reply-list--top'>`
        reply += `<div><img src="/resources/images/profile.png" alt="유저 이미지">`
        reply += `<div><p class='reply-list--name'>\${response.list[i].member.nickname}</p>`
        reply += `<p class='reply-list--date'><small>\${dateTime}</small></p></div></div>`
        if (response.list[i].member.nickname === $(".nickname").val()) {
          reply += `<div class='reply-list--btn'><button class="reply-list--btn--remove" data-id=\${response.list[i].id}>삭제</button> <button class="reply-list--btn--modify" data-id=\${response.list[i].id}>수정</button></div>`;
        } else {
          reply += `<div>
              <img src="/resources/images/thumbs-up.png" alt="좋아요 표시">
              <div><img src="/resources/images/report.png" alt="신고 이미지"></div>
          </div>`
        }
        reply += `</div>`
        reply += `<div contenteditable = "false" data-id=\${response.list[i].id}>\${response.list[i].content} </div>`
        reply += `</div> <hr>`
      }
      $(".section-reply-list").html(reply);
      pagination(response.replyCnt);
    })
  }
  showList(pageNum);

  // pagination
  const pagination = (replyCnt) => {
    let pn = `<ul class='pagination'>`;
    let endPage = Math.ceil(pageNum / 5.0) * 5; // 기본 페이지는 10개씩, 댓글은 5개씩 보여줄 것
    let startPage = endPage - 4; // 기본 1
    let prev = startPage != 1; // 첫 번째 페이지(1) 이상이면 prev가 존재하게 (2 페이지부터 존재)
    let next = false;

    if (endPage * 5 >= replyCnt) {
      endPage = Math.ceil(replyCnt / 5.0);
    }

    if (endPage * 5 < replyCnt) {
      next = true;
    }

    if (prev) {
      pn += `<li class='page-item'><a class='page-link' href=\${startPage -1}>Prev</a></li>`
    }

    for (let i = startPage; i <= endPage; i++) {
      let active = pageNum === i ? "active" : "";
      pn += `<li class='page-item \${active}'><a class='page-link' href=\${i}>\${i}</a></li>`;
    }

    if (next) {
      pn += `<li class='page-item'><a class='page-link' href=\${endPage +1}>Next</a></li>`
    }

    pn += `</ul>`;
    $(".pagination-wrap").html(pn);
  }

  // 댓글 페이지 이동
  $(".pagination-wrap").on("click", "li", (e) => {
    e.preventDefault();
    const aTag = $(e.currentTarget).find("a");
    const targetNum = aTag.attr("href");

    pageNum = Number(targetNum);
    showList(pageNum);
  })

  // 댓글 추가
  $(".section-reply button").click(() => {
    const data = {
      content: $(".section-reply textarea").val().trim(),
      postnum: postResponse.postnum
    }

    if (data.content === "") {
      swal("댓글을 입력해주세요")
      return;
    }

    replyService.addReply(data, (response) => {
      $(".section-reply textarea").val("");
      showList(-1);
    });
  })

  // 댓글 삭제
  $(".section-reply-list").on("click", ".reply-list--btn--remove", (e) => {
    replyService.removeReply($(e.currentTarget).data("id"), (response) => {
      // 여기 수정
      if (Number($(".section-reply p:first-child").text().split("개")[0]) % 5 - 1 === 0) {
        showList(pageNum - 1);
      } else {
        showList(pageNum);
      }
    })
  });

  // 댓글 수정
  $(".section-reply-list").on("click", ".reply-list--btn--modify", (e) => {
    const replyId = $(e.currentTarget).data("id");
    const replyDiv = $(`div[data-id="\${replyId}"]`);

    if ($(e.currentTarget).text() === "수정") {
      replyDiv.addClass("edit-mode");
      replyDiv.prop("contenteditable", "true");
      replyDiv.focus();
      $(e.currentTarget).text("변경");
      return;
    }


    if (($(e.currentTarget).text() === "변경")) {
      const reply = {
        content: replyDiv.html().replace(/&nbsp;/gi, '').replace(/<div><br><\/div>/gi, '').replace(/<p><br><\/p>/gi, '').trim(),
        id     : replyId
      };

      replyService.modifyReply(reply, (response) => {
        replyDiv.removeClass("edit-mode");
        replyDiv.prop("contenteditable", "false");
        replyDiv.html();
        $(e.currentTarget).text("수정");

        showList(pageNum)
      })
    }
  })

  // 포스트 수정
  $(".modify-btn button:first-child").click((e) => {
    postService.modifyPost(`/posting?postnum=\${postResponse.postnum}`);
  })

  // 포스트 삭제
  $(".modify-btn button:last-child").click((e) => {
    postService.deletePost(postResponse.postnum, (response) => {
      location.href = "/magazine";
    });
  })

  // 좋아요
  $(".section-header-icon img[alt='좋아요 표시']").click((e) => {
    let result = "";

    if ($(".nickname").val() === "") {
      swal("로그인을 해주세요");
      return;
    }

    if ($(e.target).attr("class") === "favorite") {
      $(e.target).addClass("clicked");
      result = "clicked"
    } else {
      result = "click"
    }

    postService.updateFavorite(postResponse.postnum, (response) => {
      let favoriteCnt = Number($(".section-header-icon span").html());
      if (response === "false") {
        $(e.target).removeClass("clicked");
        $(".section-header-icon span").html(favoriteCnt - 1);
        return;
      }
      $(".section-header-icon span").html(favoriteCnt + 1);
    }, result)
  })


  // 유저 구별
  if ($(".nickname").val() === postResponse.member.nickname) {
    $(".section-header-icon img[alt='신고 이미지']").css("display", "none");
  } else {
    $(".modify-btn").css("display", "none");
  }

  // 구독
  $(".writer-profile-pic button").click(() => {

    if ($(".nickname").val() === "") {
      swal("로그인을 해주세요");
      return;
    }

    subcribeService.insertSubscribe(writerBtn.data("writer"), (response) => {
          console.log(response, " <= 구독버튼 클릭");
          writerBtn.addClass("sub");
        },
        (error) => {
          console.log("예외 터짐")
          writerBtn.removeClass("sub");
        })
  })

  if (subscribeResponse === postResponse.member.loginid) {
    writerBtn.addClass("sub");
  }

  // 작가 프로필 사진 클릭 시 작가페이지 이동
  $(".writer-profile-pic > img").click((e) => {
    const writerId = $(e.target).siblings().data("writer");
    location.href = `/library/writer/\${writerId}`
  })
</script>
</body>
</html>