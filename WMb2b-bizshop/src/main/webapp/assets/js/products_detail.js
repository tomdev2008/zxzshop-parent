$(function() {
	var valueid = getParam("cid");
    if (valueid) {
        NTKF_PARAM.itemid = valueid;
    }

  	if(null != valueid && valueid !=''){
 		 $.ajax({
 			url:'/product/queryProductbyId.do?t='+Math.random(),
 			type:'post', //数据发送方式
 			dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
 			data:{Id:valueid},
 			success: function(msg){ //成功
 				if(msg.code<20000){
 					var obj = msg.obj;
 					$('#name h1').html(obj.name);
 					$('#summary-price .value').html('≥'+ obj.startMass);
 					$('#summary-price .price-length-6').html(obj.marketPrice+'元');
 					$('#summary-price .unit').html('/'+obj.unit);
 					
 					$('.dd .pp').html(obj.brandName);
 					$('.dd .gg').html(obj.unit);
 					$('.dd .cd').html(obj.birthArea);
 					$('#addressId').html(obj.birthArea);
 					var pic = obj.picts;
 					var ht='';
 					if(pic != null){
 						var imgArr=[];
 						imgArr=pic.split('|');
		                  for(var i=0;i<imgArr.length;i++){
		                	  if(i==0){
		                		  $("#pimg").attr('src', imgArr[i]);
		                		  $("#zoom1").attr('href', imgArr[i]);
		                	  }
		                	 ht+='<li>\
		                	     <a href='+imgArr[i]+' class="cloud-zoom-gallery" rel="useZoom: \'zoom1\', smallImage: \''+imgArr[i]+'\'">\
		                		 <img src='+imgArr[i]+' width="50" height="50"></a></li>';
		                  }
		                 $("#spec-list ul").html(ht);
		                 $('.cloud-zoom, .cloud-zoom-gallery').CloudZoom();
 		            }
                    var advertise = obj.Advertise;
                    if (advertise != null) {
                        $('.product-introduce .list').html('<li>' + obj.Advertise + '</li>');
                    }
                    var detail = obj.detail;
                    if (detail != null) {
                        $('.article').html('<li>' + detail + '</li>');
                    }

                    var _scid = getUrlParam('scid');
                    var _scname = getUrlParam('scname');
 					var _breadCrumb = '<li><a href="/">首页</a></li>';
 					_breadCrumb += '<li><a href="/pages/products.html?cid='+obj.categoryId+'&scname='+_scname+'">'+_scname+'</a></li>\
 									<li>'+obj.name+'</li>';
 					$('.breadcrumb ul').html(_breadCrumb);

 				}
 			},error: function(){ //失败
 				//TODO:返回异常数据
 			}
 		});
 	}


 	var isFavorite="true";
        $(function(){
            $('#addressChoose').click(function() {
                $(this).himallDistrict({items:province,closeFn:function(){calcFreight(); }});
            });

            if($('#spFreight').text() != "")
            {
                $('#fg').show();
            }
        });

        function calcFreight()
        {
            var isFree=$("#hdFreightType").val();//是否包邮
            var select=$("#addressChoose").data("select");
            if(select != "")
            {
                var cityid = select.split(',')[1];//收货城市Id
                if(parseInt(cityid) > 0 && isFree == "0")
                {
                    //重新计算运费
                    var totalnum = 0;//商品总数量
                    $('.wrap-input .text').each(function (i, e) {
                        if (parseInt($(e).val()) > 0) {
                            totalnum += parseInt($(e).val());
                        }
                    });
                    if(totalnum == 0)
                    {
                        totalnum = 1;
                    }
                    $.ajax({
                        type: 'post',
                        url: '../CalceFreight',
                        data: { cityId: cityid,pId: "326398",count:totalnum },
                        dataType: "json",
                        async: false,
                        success: function (data) {
                            if (data.success == true) {
                                $("#spFreight").text(data.msg);
                                $('#fg').show();
                            }
                        }
                    });

                }
            }
        }


});