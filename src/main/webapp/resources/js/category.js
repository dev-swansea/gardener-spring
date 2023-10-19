$(()=>{
	
	//돌아온 result 값
   	var searchResult = JSON.parse($('#searchResult').val());
  	console.log(searchResult[0]);
  	
  	//날짜 값 변환(timestamp)
  	var date = new Date(searchResult[0].createdate);
  	var year = date.getFullYear();
	var month = ('0' + (date.getMonth() + 1)).slice(-2);
	var day = ('0' + date.getDate()).slice(-2);
  	
	var createdate = year + '년 ' + month + '월 ' + day + "일";
	
	var nicknme = searchResult[0].nickname;
	
	//card front view
	$('.card-img').attr("src",searchResult[0].maintitleimg);
	$('.card-title').html(searchResult[0].maintitle);
	$('.card-text').html(searchResult[0].subtitle);
	$('.nickname').html(nicknme);
	
	//card back view
	$('.back-text').html(searchResult[0].content);
	$('.create-date').html(createdate);
	$('.favorite').html(searchResult[0].favorite);
	$('h5.nickname').html(nicknme+" 작가 소개");
	$('.writer-intro').html(searchResult[0].intro);
	
})