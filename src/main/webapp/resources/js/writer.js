const url = window.location.href;
const urlLastIndex = url.lastIndexOf("/");
const writerId = url.slice(urlLastIndex + 1)

let target = $(".hr")[0]
let num = 2;

$(".section-post").on("click", "div", (e) => {
  const postnum = $(e.currentTarget).parent().data("postnum");
  location.href = `/post/${postnum}`;
})

const option = {
  root      : null,
  rootMargin: "0px",
  threshold : 0.5
}

const observer = new IntersectionObserver((entries, observer) => {
  entries.forEach(entry => {
    if (entry.isIntersecting) {
      $.ajax({
        url    : `/library/writer/${writerId}/${num}`,
        success: (response) => {
          let writerPost = "";
          num++;
          for (w of JSON.parse(response[0])) {
            if ($(".loginyn").val() === "" && w.publicYn === true) {
              writerPost += `<hr><div class='section-post' data-postnum='${w.postnum}' style="cursor: pointer" onclick="movePage(${w.postnum})">`;
              writerPost += `<div><h4>${w.mainTitle}</h4><h5>${w.subTitle || ""}</h5><h6>${w.member.nickname}</h6>`;
              writerPost += `<p>${w.content}</p></div>`;
              writerPost += `<div><img src='${w.mainTitleImg || '/resources/images/background9.png'}' alt='게시글이미지'></div>`;
              writerPost += `</div>`;
            } else if ($(".loginyn").val() !== "" && $(".loginyn").val() === $(".section-writer-wrapper").data("writerid")) { // 로그인인데 내 나일경우
              writerPost += `<hr><div class='section-post' data-postnum='${w.postnum}' style="cursor: pointer" onclick="movePage(${w.postnum})">`;
              writerPost += `<div><h4>${w.mainTitle}</h4><h5>${w.subTitle || ""}</h5><h6>${w.member.nickname}</h6>`;
              writerPost += `<p>${w.content}</p></div>`;
              writerPost += `<div><img src='${w.mainTitleImg || '/resources/images/background9.png'}' alt='게시글이미지'></div>`;
              writerPost += `</div>`;
            } else if ($(".loginyn").val() !== "" && $(".loginyn").val() !== $(".section-writer-wrapper").data("writerid") && w.publicYn === true) {
              writerPost += `<hr><div class='section-post' data-postnum='${w.postnum}' style="cursor: pointer" onclick="movePage(${w.postnum})">`;
              writerPost += `<div><h4>${w.mainTitle}</h4><h5>${w.subTitle || ""}</h5><h6>${w.member.nickname}</h6>`;
              writerPost += `<p>${w.content}</p></div>`;
              writerPost += `<div><img src='${w.mainTitleImg || '/resources/images/background9.png'}' alt='게시글이미지'></div>`;
              writerPost += `</div>`;
            }
          }
          $("article").append(writerPost);
        },
        error  : (xhr, status) => {
          console.log(xhr, "xhr")
          console.log(status, "status")
        },
      })
    }
  })
}, option);
observer.observe(target);

const movePage = (postnum) => {
  location.href = `/post/${postnum}`
}