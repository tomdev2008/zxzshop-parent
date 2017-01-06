
$(function () {

	 $.ajax({
			url:'/news/queryCategoryInfo.do?t='+Math.random(),
			type:'post', //数据发送方式
			dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
			data:'',
			success: function(msg){ //成功
				if(msg.code == 000000){
					var html ='';
					var obj = msg.obj;
					for(var i = 0; i< obj.length; i++ ){
						html += '<div class="current">'
						html += '<h4><a href="help.html?cid='+obj[i].id+'">'+obj[i].name+'</a></h4>'
						html += ' <ul>'
						var subobj = obj[i].subNesCategory;
						if(subobj != null){
							for(var j in subobj){
								html += '<li data-id="'+subobj[j].id+'"><b></b><a href="help.html?cid='+subobj[j].id+'">'+subobj[j].name+'</a></li>'
							}
						}
						html += '</ul>'
						html += '</div>'
					}
                	$('.help_side').html(html);	

                	var _cid = getUrlParam('cid');

					 _cid = _cid ? _cid : 1;	
					 newsInfo(_cid);
                	$('.help_side ul li[data-id='+_cid+']').addClass('active');
				}else{
					//TODO:返回数据为空
				}
			},error: function(){ //失败
				//TODO:返回异常数据
			}
		});
	 
});

//新闻列表
var isNewsRequest = false;
function  newsInfo(id, currentPage){
	if(currentPage == undefined) currentPage = 0;
	var _id = id;
	if(!isNewsRequest){
		isNewsRequest = true;
		$.ajax({
			url:'/news/newsList.do?t='+Math.random(),
			type:'post', //数据发送方式
			dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
			data:{categoryId:id, pagesize:20, currentPage: currentPage},
			success: function(msg){ //成功
				var html ='';
				if(msg.code == 000000){
					html+='<h5><span>发表时间</span>标题</h5><ul>'
					var obj = msg.data;
					for(var i = 0; i < obj.length; i++ ){
						html += ' <li><div><a href="help.html?cid='+_id+'&aid='+obj[i].id+'">'+obj[i].title+'</a></div> <span>'+obj[i].publishTime+'</span></li>';
					}
					html+='</ul>'
					$('.news-show').html(html);

					//处理分页
					var _html = '<li class="active">1</li>';
					for(var i = 1; i < msg.totalPage; i++){
						_html += '<li>'+ (i+1) +'</li>';
					}
					$('.paging ul.page-number').html(_html);
					$('.paging ul.page-number > li').eq(msg.currentPage).addClass('active').siblings().removeClass('active');

					if($('.paging ul.page-number > li:first').hasClass('active')){
						$('.paging .prev').addClass('disabled')
					}else{
						$('.paging .prev').removeClass('disabled')
					}
					if($('.paging ul.page-number > li:last').hasClass('active')){
						$('.paging .next').addClass('disabled')
					}else{
						$('.paging .next').removeClass('disabled')
					}

					$('.paging ul.page-number > li').off().on('click', function(){
						var _this = $(this);
						if(_this.hasClass('active')) return;
						 newsInfo(_id, parseInt(_this.text())-1);
					});

					$('.paging .prev').click(function(){
						var _lis = $('.paging ul.page-number > li');
						var _index = _lis.filter('.active').index();
						if(_index > 0){
							 _lis.eq(_index - 1).click();
						}
					});
					$('.paging .next').click(function(){
						var _lis = $('.paging ul.page-number > li');
						var _index = _lis.filter('.active').index();
						if(_index < _lis.length -1){
							 _lis.eq(_index + 1).click();
						}
					});

					//显示文章详情
					var _aid = getUrlParam('aid');
					if(_aid){
					 	newsInfoById(_aid)
					}

				}else{
					html+='<h5><span>发表时间</span>标题</h5><p class="message">'+ msg.value +'</p>';
					$('.news-show').html(html);	
				}
				$('.paging').show();
			},error: function(){ //失败
				//TODO:返回异常数据
			},
			complete:function(){
				isNewsRequest = false;
				$('.help_side li').removeClass('current');
				$('.help_side li[data-id='+id+']').addClass('current');
			}
		});
	}
 	
}


//新闻内容
function  newsInfoById(id){
	 $.ajax({
			url:'/news/queryNewsById.do?t='+Math.random(),
			type:'post', //数据发送方式
			dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
			data:{Id:id},
			success: function(msg){ //成功
				if(msg.code == 000000){
					var html ='';
					var obj = msg.obj;
					html+='<h3 class="help_tit"><strong>'+obj.title+'</strong></h3><div class="article">'+obj.content+'</div>'
					$('.news-show').html(html);
				}else{
					$('.news-show').html('<p class="message">'+ msg.value +'</p>');
				}
				$('.paging').hide();
			},error: function(){ //失败
				//TODO:返回异常数据
			}
		});
}
