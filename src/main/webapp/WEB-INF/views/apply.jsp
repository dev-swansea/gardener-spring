<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>

  <!-- Jquery CDN -->
  <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

  <!-- Bootstrap CDN -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>

  <!-- Carousel CDN -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>

  <!-- Font Awesome CDN -->
  <script src="https://kit.fontawesome.com/a00fe672c7.js" crossorigin="anonymous"></script>


  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <link rel="stylesheet" href="/resources/css/apply.css"/>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="/resources/js/apply.js"></script>

  <title>apply</title>
</head>
<input type="hidden" id="loginid" value="${writer.loginid}" readonly/>
<body>
<%@ include file="./common/header.jsp" %>

<div style="text-align: center">
  <div class="apply">작가 신청</div>
</div>

<div class="Introduce">
  <h3>01. 작가소개</h3>
  <fieldset class="fieldset">
    <legend>작가님이 궁금해요</legend>
    <span
    >작가님이 누구인지 이해하고 <br/>앞으로 작가의 정원에서 어떤 활동을
          보여주실지 알려주세요.</span
    ><br/><br/>
    <textarea
            name="introduce"
            rows="6"
            cols="300"
            style="resize: none"
            placeholder="작가 소개를 입력해 주세요.(300자내외)"
    ></textarea>
  </fieldset>
</div>

<div class="Activity">
  <h3>02. 작가의정원 활동 계획</h3>
  <fieldset class="fieldset">
    <legend>어떤 글을 작성하실 건가요?</legend>
    <span>발행하고자 하는 글의 주제나 소재, 대략의 목차를 알려주세요.</span>
    <span><br/>작가님의 활동을 기대할 수 있도록 미리 알려주세요.</span
    ><br/><br/>
    <textarea
            name="plan"
            rows="6"
            cols="300"
            style="resize: none"
            placeholder="활동 계획을 입력해주세요.(300자내외)"
    ></textarea>
  </fieldset>
</div>

<div class="Writing">
  <h3>03. 자료첨부</h3>
  <fieldset class="fieldset">
    <legend class="screen_out">작가님의 글을 공유해주세요</legend>
    <span>작가님이 쓰셨던 글들을 공유해주세요.</span><br/>
    <span class="txt_lab"
    >이 외에 온라인매체 기고글이나 출간 책 주소를 입력해주세요.</span
    ><br/><br/>
    <span class="bundle_tf" style="margin-bottom: 10px">
          <input name="refUrl" type="url" class="url" placeholder="http://"/>
        </span>
  </fieldset>

  <br/>
  <button class="applybtn" id="applybtn">제출하기</button>
</div>
</body>
</html>