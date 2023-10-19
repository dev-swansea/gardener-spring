$(()=>{

//전체적인 검색기능 영역 start --------------------------------------------------------------------
	
	    
	    //검색할 optionValue 값
    	const selectValue = $(".form-select").val();
    
    	//돌아온 result 값
    	const result = $(".result").val();
    	
    function search(){
    
		//검색할 텍스트 값
	    const textValue = $(".form-control").val();
    	
    
		//text창에 아무것도 입력을 안한 경우
        if (textValue == "") {
            alert("검색할 단어를 입력해주세요");
        }else{
     		$('form').submit();
     		
        }
           
    }
	
	//DB에 결과가 없을때만 alert창 생성
	if(result != ""){
        alert(result);
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
})