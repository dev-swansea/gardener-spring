<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Title</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <link rel="stylesheet" href="/resources/css/writer.css">
  <script src="/resources/js/writer.js" defer></script>
</head>
<body>

<div style="margin: 0 auto;  text-align: center;">
  <a href="/">
    <img src="/resources/images/logo_small.png" alt="로고이미지">
  </a>
</div>

<main>
  <section>
    <header>
      <div class="section-writer-wrapper">
        <img src="/resources/images/profile.png" alt="">
        <div>
          <p>필명</p>
          <p>이메일</p>
          <p>작가소개</p>
        </div>
      </div>

      <div class="section-subscribe-wrapper"></div>
    </header>

    <article></article>
  </section>
  <hr class="hr">

</main>

<script>
  const writer = ${writer};
  const subscribe = '${subscribe}';
  const sessionLoginid = '${sessionScope.member.loginid}';
  const inputObj = $(`<input type='hidden' value='\${sessionLoginid}' class='loginyn'>`)
  $("body").append(inputObj);

  let sub = "";
  let writerPost = "";

  $(".section-writer-wrapper").attr("data-writerid", writer[0].member.writer.loginid)
  $(".section-writer-wrapper img").attr("src", "/resources/images/profile.png");
  $(".section-writer-wrapper p:eq(0)").text(writer[0].member.nickname)
  $(".section-writer-wrapper p:eq(1)").text(writer[0].member.email)
  $(".section-writer-wrapper p:eq(2)").text(writer[0].member.intro)

  {
    if (subscribe !== "") {
      for (s of JSON.parse(subscribe)) {
        if (s.nickname !== $(".section-writer-wrapper p:eq(0)").text()) {
          sub += `<div class="sb" data-writerid="\${s.loginid}" style="text-align: center; cursor: pointer"><img src="\${s.profile || '/resources/images/profile.png'}" alt='작가이미지'>`;
          sub += `<p>\${s.nickname}</p></div>`;
        }
      }
      $(".section-subscribe-wrapper").html(sub);
      $(".sb").click((e) => {
        const url = $(e.currentTarget).data("writerid");
        location.href = `/library/writer/\${url}`
      })
    }
  }

  {
    for (w of writer) {
      if (sessionLoginid !== $(".section-writer-wrapper").data("writerid") && w.publicYn === true) {
        writerPost += `<hr><div class='section-post' data-postnum='\${w.postnum}' style="cursor: pointer">`;
        writerPost += `<div><h4>\${w.mainTitle}</h4><h5>\${w.subTitle || ""}</h5><h6>\${w.member.nickname}</h6>`;
        writerPost += `<p>\${w.content}</p></div>`;
        writerPost += `<div><img src='\${w.mainTitleImg || "/resources/images/background9.png"}' alt='게시글이미지'></div>`;
        writerPost += `</div>`;
        $("article").html("");
        $("article").html(writerPost);
      }

      if (sessionLoginid === $(".section-writer-wrapper").data("writerid")) {
        writerPost += `<hr><div class='section-post' data-postnum='\${w.postnum}'>`;
        writerPost += `<div><h4>\${w.mainTitle}</h4><h5>\${w.subTitle || ""}</h5><h6>\${w.member.nickname}</h6>`;
        writerPost += `<p>\${w.content}</p></div>`;
        writerPost += `<div><img src='\${w.mainTitleImg || "/resources/images/background9.png"}' alt='게시글이미지'></div>`;
        writerPost += `</div>`;
        $("article").html(writerPost);
        $(".hr").css("display", "block")
      }
    }
    if ($("article").text() === "") {
      $(".hr").css("display", "none");
      $.get("/resources/exception-page/library-exception.html", (response) => {
        $("article").html(response);
      });
    }
  }
</script>
</body>
</html>
