
$(function () {
//	$.ajax({
//		url:'/news/newsList.do?t='+Math.random(),
//		type:'post', //数据发送方式
//		dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
//		data:'',
//		success: function(msg){ //成功
//			if(msg.code<20000){
//				var html ='';
//				var subhtml = '<h3 class="title">最新推荐</h3>';
//				var obj = msg.data;
//				for(var i in obj ){
//					html += '<li><a href="'+PATH + "/newsDetail.html?id="+obj[i].id+'" class="new-item">'
//					html += '<h3>'+obj[i].title+'</h3>'
//					html += '<img src="'+obj[i].iconUrl+'">'
//					html += '<p>'+obj[i].simpleDetail +'</p>'
//					var time=new Date(obj[i].publishTime);
//					var Y = time.getFullYear(); 
//		        	var D = (time.getDate()+1 < 10 ? '0'+(time.getDate()+1) : time.getDate()+1);
//		        	var M = (time.getMonth()+1 < 10 ? '0'+(time.getMonth()+1) : time.getMonth()+1);
//					var datetime=Y+"-"+M+"-"+D;
//					html += '<p>'+obj[i].source +'|'+datetime+'</p>'
//					html += '</a></li>'
//					if(i < 5){
//						subhtml += '<a href="'+PATH + "/newsDetail.html?id="+obj[i].id+'"> <div class="news-box"><img src="'+obj[i].iconUrl+'"><p>'+obj[i].title+'</p></div> </a>';
//					}
//				}
//				$('.right-content').html(subhtml);
//            	$('.new-index ul').html(html);		
//			}else{
//				//TODO:返回数据为空
//			}
//		},error: function(){ //失败
//			//TODO:返回异常数据
//		}
//	});


 $("#pagination").pagination({
    pageSize:10,
      showJump: true,
      remote: {
          url: '/news/newsList.do?t='+Math.random(),
          params: {},    //自定义请求参数
          beforeSend: function(XMLHttpRequest){
             $('.products .list').addClass('loading');
          },
          success: function (data, pageIndex) {
              var html ='';
				var subhtml = '<h3 class="title">最新推荐</h3>';
				var obj = data.data;
				for(var i in obj ){
					html += '<li><a href="'+PATH + "/newsDetail.html?id="+obj[i].id+'" class="new-item">'
					html += '<h3>'+obj[i].title+'</h3>'
					html += '<img src="'+obj[i].iconUrl+'">'
					html += '<p>'+obj[i].simpleDetail +'</p>'
					html += '<p>'+obj[i].source +'|'+obj[i].publishTime+'</p>'
					html += '</a></li>'
						if(i < 5){
							subhtml += '<a href="'+PATH + "/newsDetail.html?id="+obj[i].id+'"> <div class="news-box"><img src="'+obj[i].iconUrl+'"><p>'+obj[i].title+'</p></div> </a>';
						}
				}
				 $('.pagin .page-number i:eq(0)').text(obj.currentPage+1);
	              $('.pagin .page-number i:eq(1)').text(obj.totalPage);
	              
	              if(obj.currentPage == 0){
	                $('.pagin .icon_lr .prev').addClass('disabled');
	              }else{
	                $('.pagin .icon_lr .prev').removeClass('disabled');
	              }

	              if(obj.currentPage+1 == obj.totalPage){
	                 $('.pagin .icon_lr .next').addClass('disabled');
	              }else{
	                 $('.pagin .icon_lr .next').removeClass('disabled');
	              }
				$('.right-content').html(subhtml);
            	$('.new-index ul').html(html);	
          },
          complete:function(){
             $('.products .list').removeClass('loading');
          },
          pageIndexName: 'currentPage', 
          pageSizeName: 'pagesize', 
          totalName:'totalNum'
      }

  });
	
	
//	alert(window.location.search);
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
	 
    
});

