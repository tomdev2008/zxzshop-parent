
$(function () {

    var left=parseInt($("#news").css("left"));
    var bannerSlider = new Slider($('#banner_tabs'), {
        time: 5000,
        delay: 400,
        event: 'hover',
        auto: true,
        mode: 'fade',
        controller: $('#bannerCtrl'),
        activeControllerCls: 'active'
    });
    var timer;
    $('#banner_tabs .flex-prev').click(function () {
        bannerSlider.prev();
    });
    $('#banner_tabs .flex-next').click(function () {
        bannerSlider.next();
    });
    
    $(document).on("mouseover", ".media-news-box .big-img, .media-news-box .img", function(){
        $(".img-text", this).stop(true).slideDown(500);
        $(".shadow", this).stop(true).slideDown(500);
    });
    $(document).on("mouseout", ".media-news-box .big-img, .media-news-box .img", function(){
        $(".img-text", this).stop(true).slideUp(500);
        $(".shadow", this).stop(true).slideUp(500);
    });

    $(".prev").click(function(){
        if(left<0){
            $("#news").animate({left:left+360},function ()
            {
                left=parseInt($("#news").css("left"));
            });
        }
    })
    ;$(".next").click(function(){
        if(left>-720){
            $("#news").animate({left:left-360},function () {
                left=parseInt($("#news").css("left"));
            });
        }
    });
    $.ajax({
		url:'/brands/queryBrandsList.do?t='+Math.random(),
		type:'post', //数据发送方式
		dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
		data:{'isIndexShow':1},
		success: function(msg){ //成功
			if(msg.code<20000){
				 brandJson = msg.obj.sort(function () {
				        return Math.random() > 0.5 ? -1 : 1;
				    });
				    var brandLi = '';
//				    var url ='../../' ;
				    //var url ='http://192.165.3.186:8080/WMb2b-bizshop/' ;/*+ brandJson[i].logo */
				    for (var i = 0; i < 8; i++) {
				        brandLi += '<li><a href="'+PATH + "/products.html?brid="+ brandJson[i].id + '&bname='+brandJson[i].name+'" title="' + brandJson[i].name + '"><img src="'+brandJson[i].logo+'"  alt="' + brandJson[i].name + '"></a></li>';
				    }
				    var brandUl = $('.categorys .brand > ul');
				    brandUl.empty().append(brandLi);
			}else{
				//TODO:返回数据为空
			}
		},error: function(){ //失败
			//TODO:返回异常数据
		}
	});
    
    //查询产品分类（不包含配套服务）
    $.ajax({
		url:'/categories/queryCateGoriesList.do?t='+Math.random(),
		type:'post', //数据发送方式
		dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
		data:'',
		success: function(msg){ //成功
			if(msg.code<20000){
				var htll='';
				var data=msg.obj;
				for(var i=0;i<data.length;i++){
					var subtitle;
					var colvalue;
					switch (data[i].id)
					{
					case 1://装饰
						colvalue=1;
						break;
					case 31://安装
						colvalue=2;
						break;
					case 124://建筑
						colvalue=3;
						break;
					default:
						colvalue=1;
						break;
					}
					
					htll +='<div class="item item-'+colvalue+'">';
					
					//htll +='<dl><dt><i></i>'+data[i].name+'</dt><dd><a href="'+PATH + "/products.html?pid="+data[i].keyValue[0].id+"&cid="+data[i].keyValue[0].id+"&cname="+data[i].keyValue[0].name+'" title="'+data[i].keyValue[0].name+'">'+data[i].keyValue[0].name+'</a><a href="'+PATH + "/products.html?pid="+data[i].keyValue[1].id+"&cid="+data[i].keyValue[1].id+"&cname="+data[i].keyValue[1].name+'" title="'+data[i].keyValue[1].name+'">'+data[i].keyValue[1].name+'</a></dd></dl>';
					htll +='<dl><dt><i></i>'+data[i].name+'</dt><dd><a>'+data[i].keyValue[0].name+'</a><a>'+data[i].keyValue[1].name+'</a></dd></dl>';
					htll +='<div class="sub-menu">';
					var keyvalues = data[i].keyValue;
					for(var j=0;j<keyvalues.length;j++){
						//<a href="'+PATH + "/products.html?pid="+keyvalues[j].id+"&cid="+keyvalues[j].id+"&cname="+keyvalues[j].name+'" title="'+keyvalues[j].name+'">'+keyvalues[j].name+'</a>
						htll+='<dl><dt>'+keyvalues[j].name+'</dt><dd>';
						var subkeyvalues = keyvalues[j].keyValue;
						for(var n=0;n<subkeyvalues.length;n++){
							htll+='<a href="'+PATH + "/products.html?pid="+subkeyvalues[n].id+"&cid="+keyvalues[j].id+"&cname="+keyvalues[j].name+"&scid="+subkeyvalues[n].id+"&scname="+subkeyvalues[n].name+'" title="'+subkeyvalues[n].name+'">'+subkeyvalues[n].name+'</a>';
						}
						htll += '</dd></dl>';
					}
					
					htll+='</div></div>';
					
				}
				$("#categorysmenu").append(htll);
			}else{
				//TODO:返回数据为空
			}
		},error: function(){ //失败
			//TODO:返回异常数据
		}
	});
    
    //配套服务
    $.ajax({
		url:'/categories/querySupportingServList.do?t='+Math.random(),
		type:'post', //数据发送方式
		dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
		data:'',
		success: function(msg){ //成功
			if(msg.code<20000){
				var htll='';
				var data=msg.obj;
				for(var i in data){
					var subtitle;
					var colvalue;
					switch (data[i].id)
					{
					case 468://设计
						colvalue=1;
						subtitle='Design';
						break;
					case 470://安装
						colvalue=2;
						subtitle='Installation';
						break;
					case 469://施工
						colvalue=3;
						subtitle='Construction';
						break;
					case 471://物流公司
						colvalue=4;
						subtitle='Logistics';
						break;
					default:
						colvalue=1;
						subtitle='Design';
						break;
					}
					
					htll +='<dl class="col-'+colvalue+'"><dt><a href="/pages/thirdService.html" title="'+data[i].name+'"><span class="title">'+data[i].name+'</span><span class="sub-title">'+subtitle+'</span></a></dt><dd>';
					var keyvalues = data[i].keyValue;
					for(var j in keyvalues){
						htll +='<a href="/pages/thirdService.html" title="'+keyvalues[j].name+'">'+keyvalues[j].name+'</a>';
					}
					htll +='</dd></dl>';
				}
				$("#supportingid").append(htll);
			}else{
				//TODO:返回数据为空
			}
		},error: function(){ //失败
			//TODO:返回异常数据
		}
	});
    
    /*************************************************************************/
    //东方新闻
    var parm=new Object();
    parm.currentPage =0;
    $.ajax({
    	url:'/news/newsList.do?t='+Math.random(),
		type:'post', //数据发送方式
		dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
		data:parm,
		success:function(data){
			if (data.code == '000000') {
				var obj = data.data;
				var html='';
				for(var i in obj){
					if(i%2 ==0){//
						html +='<div class="news-box">';
					}
		        	html += '<a href="/pages/newsDetail.html?Id='+obj[i].id+'" target="_blank" class="text-news">';
		        	var _date = obj[i].publishTime, _index = _date.indexOf(" ");
		        	if(_index > -1){
		        		_date = _date.substr(0, _index);
		        	}

		        	var time=new Date(_date);
		        	var Y = time.getFullYear(); 
		        	var D = (time.getDate() < 10 ? '0'+(time.getDate()) : time.getDate());
		        	var M = time.getMonth()+1;
		        	html += '<p class="time"><span>'+M+'.'+D+' </span><span>/</span>'+Y+'</p>';
					html += '<p class="news-title">'+obj[i].title+'</p>';
					/*html += '<p class="news-text">'+obj[i].simpleDetail+'</p>';*/
					html += '</a>';
					if( !(i%2 ==0)){
						html +='</div>';
					}
				}
				$('#news').html(html);
			}
		},error: function(){
			//alert('error');
		}
    });
    
    //新闻图片点击列表(推荐新闻)
    var parm=new Object();
    parm.currentPage =0;
    parm.isRecommend =1;
    parm.pagesize =3;
    $.ajax({
    	url:'/news/newsList.do?t='+Math.random(),
		type:'post', //数据发送方式
		dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
		data:parm,
		success:function(data){
			if (data.code == '000000') {
				var obj = data.data;
				var html='';
//				/pages/newsDetail.html?Id=671
				html+='<a href="http://www.cifesh.com"  target="_blank" class="big-img"><img src="/assets/images/index/265203.jpg">';
				html+='<div class="shadow" style="display:none;">';
				html+='</div>';
				html+='<div class="img-text" style="display:none;">';
				html+='CIFE-2017中国国际门窗幕墙博览会';
				html+='</div>';
				html+='</a>';
				html+='<a href="/pages/topic/2016.11.18/index.html"  target="_blank" class="video">';
				html+='<video poster="/assets/images/index/1.jpg" src="/assets/images/index/video.mp4"></video>';
				html+='</a>';
					
				html+='<a href="/pages/newsDetail.html?Id=665" class="img" target="_blank"><img src="/assets/images/index/36_1.png">';
				html+='<div class="shadow" style="display:none;">';
				html+='</div>';
				html+='<div class="img-text" style="display:none;">';
				html+= '东方建材网CEO接受中央电视台、凤凰网等...';
				html+='</div>';
				html+='</a>';
				
				html+='<a href="/pages/newsDetail.html?Id=679" class="img" target="_blank"><img src="/assets/images/index/30_1.png">';
				html+='<div class="shadow" style="display:none;">';
				html+='</div>';
				html+='<div class="img-text" style="display:none;">';
				html+= '贾华琴：未来全装时代，建筑装饰业更有激...';
				html+='</div>';
				html+='</a>';
				
				html+='<a href="/pages/newsDetail.html?Id=668" class="img" target="_blank"><img src="/assets/images/index/40_1.png">';
				html+='<div class="shadow" style="display:none;">';
				html+='</div>';
				html+='<div class="img-text" style="display:none;">';
				html+= '恽稚荣：建材模式创新，线下走向线上';
				html+='</div>';
				html+='</a>';
				
				$('.media-news-box').html(html);
			}
		},error: function(){
			//alert('error');
		}
    });
    /*************************************************************************/

    
    /*********************第三方入驻***********************************************/
    
    $('#thirdint').click(function() {
    	$.ajax({
    		type:"GET",
    		url:"/user/currentLoginUser.do?t="+Math.random(),
    		dataType:"json",
    		async:false,
    		contentType : "application/json; charset=utf-8",
    		success: function (data) {
    			if (data!=null && data!=undefined && data.code=='000000') {
    				
    				if (data.obj.userType==1){
    					$.dialog.alert('您已经是采购商，不能入驻！');
    				}else if(data.obj.userType==2){
    					$.dialog.alert('您已经是供应商，不能入驻！');
    				}else if(data.obj.userType==3){
    					location.href = "/pages/third/index.html?to=Account";
    				}
    			}else {
                    location.href = "../../pages/redirect-login.html";
                }
    		},
    		error: function(){
    			console.log("fetch login user error!code = " + data.code);
    			$(".logout").css("display","block");
    			result = false;
    		}
    	});
	});
    
});
