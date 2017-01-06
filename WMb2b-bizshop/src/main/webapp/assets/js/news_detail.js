$(function() {
	
	///
	$.ajax({
	url:'/news/newsList.do?t='+Math.random(),
	type:'post', //数据发送方式
	dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
	data:'',
	success: function(msg){ //成功
		if(msg.code<20000){
			var subhtml = '<h3 class="title">最新推荐</h3>';
			var obj = msg.data;
			for(var i in obj ){
				if(i < 5){
					subhtml += '<a href="'+PATH + "/newsDetail.html?id="+obj[i].id+'"> <div class="news-box"><img src="'+obj[i].iconUrl+'"><p>'+obj[i].title+'</p></div> </a>';
				}
			}
			$('.right-content').html(subhtml);
		}else{
			//TODO:返回数据为空
		}
	},error: function(){ //失败
		//TODO:返回异常数据
	}
});
	
	
	var value = getParam("id");
	if(value){
		$.ajax({
			url:'/news/queryNewsById.do?t='+Math.random(),
			type:'post', //数据发送方式
			dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
			data:{Id:value},
			success: function(msg){ //成功
				if(msg.code<20000){
					var obj = msg.obj;
					$('.newDetail_title').html('<a href="newsDetail.html?id='+obj.id+'">'+obj.title+'</a>');
					$('.left-content h3').html(obj.title);
					$('.left-content .source').html(obj.source +' | '+obj.publishTime);
					$('.left-content .article').html(obj.content);
					
				}else{
					alert(msg.value);
				}
			},error: function(){ //失败
				//TODO:返回异常数据
			}
		});
	}
})