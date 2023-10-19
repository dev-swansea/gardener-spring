<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
  <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
  <script src="/resources/js/posting.js" defer></script>
  <script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
  <link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.css"/>
  <link rel="stylesheet" href="/resources/css/posting.css">
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
    <div class="main-image"></div>
  </div>
  <!-- 섹션 헤더 타이틀 -->
  <div class="section-header-title">
    <input type="text" placeholder="제목을 입력해주세요" name="title" onfocus="this.placeholder=''"
           onblur="this.placeholder='제목을 입력해주세요'">
    <input type="text" placeholder="소제목" name="subtitle" onfocus="this.placeholder=''"
           onblur="this.placeholder='소제목을 입력해주세요'">
    <!-- 이미지 btn 시작 -->
    <div class="img-pic">
      <label for="imgBtn">
        <img src="/resources/images/image.png" alt="">
      </label>
      <input id="imgBtn" type="file" name="image" accept="image/*" hidden>
    </div>
    <!-- 이미지 btn 끝 -->
  </div>

  <!-- 아티클 시작 -->
  <article>
    <div id="editor"></div>
  </article>
  <!-- 아티클 끝 -->


  <!-- 공개 | 비공개 체크 시작 -->
  <div class="secret">
    <input type="checkbox" name="secret" value="1" id="secret" hidden>
    <span>비공개 | 공개</span>
    <label for="secret" class="secret-toggle">
      <span class="secret-toggle-btn"></span>
    </label>
  </div>
  <!-- 공개 | 비공개 체크 끝 -->

  <!-- 카테고리 시작 -->
  <div class="category">
    <ul>
      <li>
        <input type="radio" name="cate" value="시" hidden>
        <span>시</span>
      </li>
      <li><input type="radio" name="cate" value="여행" hidden>
        <span>여행</span>
      </li>
      <li><input type="radio" name="cate" value="연애" hidden>
        <span>연애</span>
      </li>
      <li><input type="radio" name="cate" value="IT" hidden>
        <span>IT</span>
      </li>
      <li><input type="radio" name="cate" value="사진" hidden>
        <span>사진</span>
      </li>
      <li><input type="radio" name="cate" value="영화" hidden>
        <span>영화</span>
      </li>
    </ul>

    <ul>
      <li><input type="radio" name="cate" value="음악" hidden>
        <span>음악</span>
      </li>
      <li><input type="radio" name="cate" value="애완동물" hidden>
        <span>애완동물</span>
      </li>
      <li><input type="radio" name="cate" value="요리" hidden>
        <span>요리</span>
      </li>
      <li><input type="radio" name="cate" value="육아" hidden>
        <span>육아</span>
      </li>
      <li><input type="radio" name="cate" value="운동" hidden>
        <span>운동</span>
      </li>
      <li><input type="radio" name="cate" value="도서" hidden>
        <span>독서</span>
      </li>
    </ul>

    <div>

    </div>

  </div>
  <!-- 카테고리 끝 -->

  <!-- 글쓰기 등록 btn 시작 -->
  <div class="post-btn">
    <button>취소</button>
    <button>글쓰기</button>
  </div>
  <!-- 글쓰기 등록 btn 끝 -->
</section>
<input class="postResponse" type="hidden" value='${post}'>
<script>
  const writerResponse = '${writer}';

  /* if (!writerResponse) {
     swal("로그인 해주세요")
     location.href = "/";
   }
 */

  $(".secret-toggle").click(() => {
    if (writerResponse !== "true") {
      $("input:checkbox[id='secret']").prop("checked", false);
      $(".secret-toggle").css("background-color", "#fff")
      $(".secret-toggle-btn").css("background-color", "rgb(121, 193, 150)");
      $(".secret-toggle-btn").css("left", "4px");
      swal("작가 신청을 해주세요");
    }
  });

</script>

</body>

</html>