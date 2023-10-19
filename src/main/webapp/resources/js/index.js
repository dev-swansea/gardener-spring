$(() => {

  //search  결과를 보여줄 위치
  const body = $("div.body");


 //전체적인 검색기능 영역 start --------------------------------------------------
  //search 버튼객체 찾기
  const searchbtn = $("#searchbtn");

  //검색 버튼을 클릭 or Enter를 클릭하면 할 함수 Start
  function Search() {

    //검색할 텍스트 값
    const textValue = $(".form-control").val();

    //검색할 optionValue 값
    const selectValue = $(".form-select").val();

    //alert(textValue);
    //alert(selectValue);

    //text창에 아무것도 입력을 안한 경우
    if(textValue == ""){
    	alert("검색할려는 키워드를 입력해주세요");
    }
    
    var resultValue = getParameterByName('result');
    alert(resultValue);
    



    // $.ajax({
    //   url:"/search",
    //   method:"get",
    //   data:{select:selectValue, text:textValue},
    //   success: (resultData) => {
    //     if(resultData.equals("[]")){
    //       alert('검색 결과가 없습니다.');
    //     }else{
    //       alert('검색한 결과');
    //       console.log(resultData);
    //       document.documentElement.innerHtml = resultData;
    //     }
    //   },
    //   error: function () {
    //            alert("검색하는 과정에서 오류가 났습니다.");
    //          },
    // });

  }//search() end

	//검색버튼을 클릭하면 할 일 Start
		searchbtn.click(() => {
          $('form').submit();
	      Search();
	  });
	//검색버튼을 클릭하면 할 일 End

	//입력창에 엔터를 누르면 할 일 start
	  const txtobj = $("input[type=text]");
	
	  txtobj.keyup((e) => {
	    if (e.key == "Enter") {
	      //Enter에 입력하면 할 일
	      Search();
	    }
	  });
  	//입력창에 엔터를 누르면 할 일 end
//전체적인 검색기능 영역 end --------------------------------------------------------------------

  //
  //   $.ajax({
  //     url: "/back/search",
  //     method: "get",
  //     data: { select: selectValue, text: textValue },
  //     success: (resultData) => {
  //       if (resultData.length != 0) {
  //         const resultlength = resultData.length;
  //
  //         body.load("./search.html", () => {
  //           const viewtable = $("table.writing-list");
  //
  //           for (var i = 0; i < resultlength; i++) {
  //             const tabletr = document.createElement("tr");
  //             tabletr.setAttribute("class", `num ${resultData[i].postnum}`);
  //             tabletr.innerHTML = `
  //                 <td>
  //                   <a>
  //                     <img src="${resultData[i].mainTitleImg}" alt="제목이미지"
  //                               style="display: block; width: 200px; height: 150px;">
  //                   </a>
  //                 </td>
  //                 <td class="test">
  //                   <h2>${resultData[i].mainTitle} -</h2>
  //                   <h3>${resultData[i].name}</h3>
  //                   <p>${resultData[i].content}</p>
  //                 </td>`;
  //
  //             $(viewtable).append(tabletr);
  //           }
  //         });
  //       } else {
  //         alert("검색하는 결과가 없습니다.");
  //       }
  //     },
  //     error: function () {
  //       alert("검색 실패");
  //     },
  //   });
  // });
  //검색버튼을 클릭하면 할 일 end



  //카테고리 하나를 누르면 할 일 start
  const category = $(".category");

  category.click((e) => {
    //클릭한 카테고리 text를 가져온다.
    const categorieValue = $(e.target).text();
    alert(categorieValue);

    $.ajax({
      url: "/back/category",
      method: "get",
      data: { category: categorieValue },
      success: (categoryData) => {
        if (categoryData.length != 0) {
          const length = categoryData.length;

          body.load("./category.html", () => {
            const viewtable = $("table.writing-list");

            //어떤 카테고리를 선택했는지 해더 출력
            const categoryHeading = $("<h2>").html(
              "카테고리 -> " + categorieValue
            );
            categoryHeading.css({
              "margin-bottom": "30px",
              "font-weight": "bold",
              display: "block",
            });
            viewtable.prepend(categoryHeading);

            for (var i = 0; i < length; i++) {
              const tableTr = document.createElement("tr");
              tableTr.innerHTML = `
              <td>
                  <a>
                    <img src="${categoryData[i].mainTitleImg}" alt="제목이미지" 
                              style="display: block; width: 200px; height: 150px;">
                  </a>
              </td>
              <td class="test">
                  <h2>${categoryData[i].mainTitle} -</h2>
                  <h3>${categoryData[i].name}</h3>
                  <p>${categoryData[i].content}</p>
              </td>`;

              //innerHTML로 작성한 tr -> table에 추가
              $(viewtable).append(tableTr);
            } //for end
          }); //load end
        } else {
          alert("해당카테고리의 글이 없습니다.");
        }
      }, //success end
      error: function () {
        alert("잠시만 기다려주세요");
      },
    }); //ajax end
  }); //click event end
  //카테고리 하나를 누르면 할 일 end
  
  
   
   
}); //ready end
