let num = 2;
let target = $(".hr")[0];
let type = "favorite";
let postResponse = JSON.parse($(".postResponse").val());

if ($(".nickname").val() === "") {
  alert("로그인 먼저 해주세요")
  location.href = "/";
}

// 좋아요 버튼
$(".favorite").click(() => {
  postResponse = JSON.parse($(".postResponse").val()); // 없으면 안됨 ..
  console.log(postResponse.length, " ")
  type = "favorite";
  $(".hr").css("display", "block");
  if (postResponse.length === 0) {
    $(".hr").css("display", "none");
    $.get("/resources/exception-page/favorite-exception.html", (response) => {
      $("section").html(response);
    });
  }
  if (num !== 1) {
    num = 1;
  }
  $("section").html(""); // 없으면 구독버튼 클릭후 사라지지않음,
})

const printFavorite = () => {
  let post = "";
  for (p of postResponse) {
    post += `<hr>`
    post += `<div class='section-post' data-postnum=${p.postnum}>`;
    post += `<div><h4>${p.mainTitle}</h4>`;
    post += `<h5>${p.subTitle || ""}</h5>`;
    post += `<h6>${p.member.nickname}</h6>`;
    post += `<p>${p.content}</p></div>`;
    if (p.mainTitleImg === undefined) {
      post += `<div><img src='/resources/images/background9.png' alt='메인이미지'/></div></div>`;
    } else {
      post += `<div><img src='${p.mainTitleImg}' alt='메인이미지'/></div></div>`;
    }
  }
  return post;
}

const firstRequest = (() => {
  console.log(postResponse.length, "postResponse.length")
  target = $(".hr")[0];
  if (postResponse.length === 0) {
    $(".hr").css("display", "none");
    $.get("/resources/exception-page/favorite-exception.html", (response) => {
      $("section").html(response);
    });
  } else {
    $("section").html(printFavorite());
  }
})()

// 뫈스크롤
const option = {
  root      : null,
  rootMargin: "0px",
  threshold : 0.5
}

const observer =
    new IntersectionObserver((entries, observer) => {
      entries.forEach(entry => {
        if (entry.isIntersecting) {
          request(type);
        }
      })
    }, option);
observer.observe(target);
const showFavorite = () => {
  $(".section-post").on("click", "div", (e) => {
    const postnum = $(e.currentTarget).parent().data("postnum");
    location.href = `/post/${postnum}`;
  })
}
showFavorite();

// 구독 버튼
$(".subscribe").click(() => {
  let memberResponse = "";
  $(".hr").css("display", "none");
  type = "subscribe";

  $.ajax({
    url    : "/library/subscribe",
    success: (response) => {
      $("section").html(response);
      printSubscribe($(".memberResponse").val());
    },
    error  : (xhr, status) => {
      console.log(xhr, "구독 예외?")
      $.get("/resources/exception-page/subscribe-exception.html", (response) => {
        $("section").html(response);
      });
    },
  })

  /*  $.get("/library/subscribe", (response) => {
      $("section").html(response);
      let memberResponse = $(".memberResponse").val();
      printSubscribe(memberResponse);
      if (memberResponse === 0) {
        $.get("/resources/exception-page/subscribe-exception.html", (response) => {
          $("section").html(response);
        });
      }
    })*/
})

const printSubscribe = (response) => {
  let memberResponse = JSON.parse(response);
  let subscribe = "";
  if (memberResponse.length === 0) {
    $.get("/resources/exception-page/subscribe-exception.html", (response) => {
      $("section").html(response);
    })
  } else {
    // loginid는 내가 로그인한 계정이 아니라 글쓴 작가의 아이디
    for (m of memberResponse) {
      subscribe += `<div class="section-subscribe-wrapper--writer" data-writer="${m.loginid}"><div><img src="${m.profile}" alt='프로필 이미지'></div>`;
      subscribe += `<div><p>${m.nickname}</p><hr><p>${m.intro}</p></div></div>`;
    }

    $(".section-subscribe-wrapper").append(subscribe);
    target = $(".section-subscribe-wrapper > div:last-child")[0];
    observer.observe(target);

    $(".section-subscribe-wrapper--writer").on("click", "div", (e) => {
      const writerId = $(e.currentTarget).parent().data("writer");
      location.href = `/library/writer/${writerId}`;
    })
  }
}


const request = () => {
  let url = "";
  type === "favorite" ? url = `/library/favorite/${num}` : url = `/library/subscribe/${num}`;
  $.ajax({
    url,
    success: (response) => {
      num += 1;
      if (type === "favorite") {
        postResponse = response;
        $("section").append(printFavorite());
        showFavorite();
      } else {
        $(".section-subscribe-wrapper").html(printSubscribe(JSON.stringify(response)));
      }
    },
    error  : (xhr, status) => {
      console.log(status);
    }
  })
}