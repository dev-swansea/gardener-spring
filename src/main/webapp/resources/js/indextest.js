$(()=>{

//전체적인 검색기능 영역 start --------------------------------------------------------------------
	
	    
	//검색할 optionValue 값
    const selectValue = $(".form-select").val();
    
    //돌아온 result 값
   	const resultNone = $(".resultNone").val();
    	
    function search(){
    
		//검색할 텍스트 값
	    const textValue = $("#search-text").val();
    	
    
		//text창에 아무것도 입력을 안한 경우
        if (textValue == "") {
            alert("검색할 단어를 입력해주세요");
        }else{
     		$('.search-form').submit();
     		
        }
           
    }
	
	//DB에 결과가 없을때만 alert창 생성
	if (resultNone !== "" && resultNone !== undefined) {
    	alert(resultNone);
	}


    
    //search 버튼객체 찾기
  	const searchbtn = $("#searchbtn");
  	
    //검색버튼을 클릭하면 할 일 Start
        searchbtn.click((e) => {
        	search()
        });
    //검색버튼을 클릭하면 할 일 End

    //입력창에 엔터를 누르면 할 일 start
        const txtobj = $("input[type=text]");

        txtobj.keydown((e) => {
            if (e.key === "Enter") {
                //Enter에 입력하면 할 일
                e.preventDefault();
                search()
            }
        });
    //입력창에 엔터를 누르면 할 일 end
  
//전체적인 검색기능 영역 end --------------------------------------------------------------------

//recommed 영역 start-----------------------------------------------------------------------
	var tableTr = $('.search-postnum');
	
	
	//var trClassName = tableTr.attr("class");
	//console.log(trClassName);
	
	tableTr.click((e)=>{
		var postnumS = $(e.currentTarget).data("postnum"); //String타입이다.
		console.log(postnumS);
		var postnumInt = parseInt(postnumS);
		console.log(postnumInt);
		
		
		window.location.href = "/post/"+postnumS;
	})

//recommed 영역 end--------------------------------------------------------------------------






















})