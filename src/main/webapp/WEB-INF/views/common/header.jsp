<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1 style="text-align: center; margin-top:10px;">
  <a href="./"><img src="https://i.imgur.com/2enY1dP.png"/></a>
</h1>

<nav class="navbar navbar-expand-lg navbar-light">
  <div class="container">
    <a class="navbar-brand" href="./" style="font-size: 30px;">작가의 정원</a>
    <button
            class="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNav"
            aria-controls="navbarNav"
            aria-expanded="false"
            aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <c:choose>
          <c:when test="${!empty sessionScope.member.loginid}">
            <li class="nav-item ms-5">
              <a class="nav-link" href="/magazine" style="font-size: 20px">매거진</a>
            </li>
            <li class="nav-item ms-5">
              <a class="nav-link" href="/posting" style="font-size: 20px">글쓰기</a>
            </li>
            <li class="nav-item ms-5">
              <a class="nav-link" href="/library" style="font-size: 20px">서재</a>
            </li>
            <li class="nav-item ms-5">
              <a class="nav-link" href="/mygarden" style="font-size: 20px">나의 정원</a>
            </li>
            <li class="nav-item ms-5">
              <a class="nav-link apply" href="/apply" style="font-size: 20px">작가신청</a>
            </li>
            <li class="nav-item ms-5">
              <a class="nav-link" href="/mypage" style="font-size: 20px">마이페이지</a>
            </li>
            <li class="nav-item ms-5">
              <a class="nav-link" href="/logout" style="font-size: 20px">로그아웃</a>
            </li>
          </c:when>
          <c:otherwise>
            <li class="nav-item ms-5">
              <a class="nav-link" href="/magazine" style="font-size: 20px">매거진</a>
            </li>

            <li class="nav-item ms-5">
              <a class="nav-link" href="/signup" style="font-size: 20px">회원가입</a>
            </li>

            <li class="nav-item ms-5">
              <a class="nav-link" href="/login" style="font-size: 20px">로그인</a>
            </li>
          </c:otherwise>
        </c:choose>

        <!-- 회원가입 모달 태그 -->
        <div class="modal fade" id="signupModal" tabindex="-1" role="dialog"
             aria-hidden="true" aria-labelledby="signupModalLabel">
          <div class="modal-dialog">
            <div class="modal-content" id="signupContent">
              <div class="modal-body">
                <!-- 회원가입 모달 내용 입력란 -->
                <form class="signup" action="/signup" method="post" name="f2">
                  <!-- 회원가입 입력 필드들을 추가 -->

                  <div class="signupLogo">
                    <img src="/resources/images/logo_small.png"/>
                  </div>
                  <!--프로필 이미지 첨부-->
                  <div>
                    <img class="profile" src="/resources/images/profile.png"/> <input
                          type="file" id=img name="uploadFile" style="display: none">
                    <div class="profileText">profile</div>
                  </div>
                  <span class="placehold-text"> <input type="text"
                                                       class="id form-control" name="signupid" placeholder="아이디"
                                                       required/>
									</span>
                  <div>
                    <input type="button" class="iddupchk" value="아이디 중복 확인"/>
                  </div>
                  <div>
                    <input class="pwd form-control" type="password" name="pwd"
                           placeholder="비밀번호" required/>
                  </div>
                  <div>
                    <input class="pwd confirm form-control" type="password"
                           placeholder="비밀번호확인" required/>
                  </div>
                  <span class="placehold-text"> <input type="email"
                                                       name="email" class="form-control" placeholder="이메일" required/>
									</span> <span class="placehold-text"> <input type="text"
                                                               name="nickname" class="form-control" placeholder="필명"
                                                               required/>
									</span>
                  <div>
                    <button type="button" class="namedupchk"/>
                    필명 중복 확인
                    </button>
                    <textarea type="text" name="intro" class="form-control"
                              maxlength=100 rows="3" cols="30" placeholder="자기소개를 입력해주세요"
                              required></textarea>
                    <div>
                      <button class="bt-Signup">회원 가입</button>
                      <a href="/"></a>
                    </div>
                  </div>
                </form>
              </div>
              <button type="button" class="btn btn-default" id="signupClosebtn"
                      data-dismiss="modal">close
              </button>
            </div>
          </div>
        </div>
        <!-- 회원가입 모달 태그 끝 -->


        <!-- 로그인 모달 태그 -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
             aria-hidden="true" aria-labelledby="myModalLabel">
          <div class="modal-dialog">
            <div class="modal-content" id="loginContent">
              <div class="modal-body">
                <!--로그인 로고-->
                <div class="logo">
                  <img src="/resources/images/logo.png"/>
                </div>

                <!--로그인 내용 입력란-->
                <form class="login" action="/login" method="post" name="f1">
                  <input type="text" name="loginid" placeholder="아이디"/><br/>
                  <input type="password" name="password" placeholder="비밀번호"/><br/>

                  <div>
                    <input class="checkbox" type="checkbox"/>아이디 저장
                  </div>
                  <br/> <input class="loginbtn" type="submit" value="로그인"><br/>

                  <!--로그인 링크-->
                  <div class="sign-wrap">
										<span class="signup"> <a
                            href="/signup">회원 가입</a>
										</span> <span class="find-loginId"> <a
                          href="/find_id">아이디 찾기</a>
										</span> <span class="find-pwd"> <a
                          href="/find_pwd">비밀번호 찾기</a>
										</span>
                  </div>

                </form>

              </div>
              <button type="button" class="btn btn-default" id="closebtn"
                      data-dismiss="modal">close
              </button>
            </div>
          </div>
        </div>
        <!--  모달태그 끝 -->

      </ul>
    </div>
  </div>
</nav>

<script>
  const status = '${member}';
  if (status) {
    const writerStatus = status.slice(status.lastIndexOf("type") + 5, status.lastIndexOf("type") + 9)
    if (writerStatus === "true") {
      $(".apply").click((e) => {
        e.preventDefault();
        alert("이미 신청되었습니다.")
        return;
      })
    }
  }
</script>