$(()=>{
	
	//돌아온 result 값
   	var searchResult = JSON.parse($('#searchResult').val());
  	console.log(searchResult.length);
  	
  	//검색한 결과 보여주는 code
  	var resultTotal = searchResult.length; 
  	
  	for(var i=0; i<resultTotal; i++){

		//날짜 값 변환(timestamp)
		var date = new Date(searchResult[i].createdate);
		var year = date.getFullYear();
		var month = ('0' + (date.getMonth() + 1)).slice(-2);
		var day = ('0' + date.getDate()).slice(-2);

		var createdate = year + '년 ' + month + '월 ' + day + "일";

		var nickname = searchResult[i].nickname;

		//card front view
		$('.card-img').attr("src",searchResult[i].maintitleimg);
		$('.card-title').html(searchResult[i].maintitle);
		$('.card-text').html(searchResult[i].subtitle);
		$('.nickname').html(nickname);

		//card back view
		$('.back-text').html(searchResult[i].content);
		$('.create-date').html(createdate);
		$('.favorite').html(searchResult[i].favorite);
		$('h5.nickname').html(nickname+" 작가 소개");
		$('.writer-intro').html(searchResult[i].intro);

	}


})