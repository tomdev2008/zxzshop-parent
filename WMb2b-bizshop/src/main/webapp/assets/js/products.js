$(function() {
//  $("img.lazy").lazyload({
//	  	 threshold : 0,
//		 failure_limit : 10,
//  		 placeholder : "../images/loading.gif",
//         effect : "fadeIn",
//		 skip_invisible : false
//   });

var _queryKey = getUrlParam('queryKey');
if(_queryKey)$("#searchBox").val(_queryKey);

//默认查询产品
queryProducts();

//清除已选择产地
var _address = getUrlParam('addrname');
if(null != _address && _address !=''){
  var _str = '<a href="javascript:;" data-query="BirthArea"><span>产地：'+_address+'</span><i>×</i></a>';
  $('#select-attr .selected').show().find('.values').append(_str);
  $('#select-attr .place-attr').hide();
}

//清除已选择品牌
var _brand = getUrlParam('bname');
if(null != _brand && _brand !=''){
  var _str = '<a href="javascript:;" data-query="brandId"><span>品牌：'+_brand+'</span><i>×</i></a>';
  $('#select-attr .selected').show().find('.values').append(_str);
  $('#select-attr .brand-attr').hide();
}
$(document).on('click', '#select-attr .selected .values a', function(){
    $(this).remove();
    queryProducts($(this).attr('data-query'));
    return false;
});

//顶部上一页/下一页按钮
$(document).on('click', '.pagin .icon_lr .prev:not(.disabled)', function(){
  var _index = $('#pagination .m-pagination-page li.active').index();
  var _prve = parseInt(_index) - 1;
  $('#pagination .m-pagination-page li:eq('+_prve+') a').click();
});

$(document).on('click', '.pagin .icon_lr .next:not(.disabled)', function(){
  var _index = $('#pagination .m-pagination-page li.active').index();
  var _next = parseInt(_index) + 1;
  $('#pagination .m-pagination-page li:eq('+_next+') a').click();
});
	 
  
  /***************************************************************************************/
  //查询推荐产品 by jiangsg
  var hotQuery = {};
  hotQuery.IsRecommend=1;//热门推荐
  hotQuery.pagesize=4;//默认四条记录
  $.ajax({
	  type: "post",
      url: "/product/queryProductList_index.do?t="+Math.random(),
      data: hotQuery,
      dataType: "json",
      async: false,
      success: function (data){
    	  if (data.code == '000000') {
    		  var obj = data.data;
        	  var html='';
        	  for(var i in obj){
        		  var pic = '';
        		  if(obj[i].picts != null){
        			  if(obj[i].picts.indexOf('|') != -1){
        				  pic = obj[i].picts.split('|')[0];//多张图片
        			  }else{
        				  pic = obj[i].picts;
        			  }
        			  if(pic.charAt(pic.length - 1)=="/"){
    					  pic=pic+"1.png";
    				  }
        		  }
//        		  var url ='http://localhost:8080/WMb2b-bizshop/'
      
              var _scid = getUrlParam('scid');
              var _scname = getUrlParam('scname');  
        		  html += '<li>';
        		  html += '<div class="list-img"><a href="'+PATH + "/productDetail.html?cid="+obj[i].productId+'&scid='+_scid+'&scname='+_scname+'" target="_blank"><img src="'+ (null != pic ? pic: '')+'"></a></div>';
        		  html += ' <div class="list-info">';
      			  html += '<span class="price"><i>市场指导价：</i>¥ '+obj[i].marketPrice+'<i>/'+obj[i].unit+'</i></span>';
    				  html += '<a class="name" href="'+PATH + "/productDetail.html?cid="+obj[i].id+'&scid='+_scid+'&scname='+_scname+'" target="_blank" title="'+obj[i].name+'">'+obj[i].name+'</a>';
    				  html += '</div>';
    				  html +='</li>';
        	  }
        	  		  $('.hot ul').html(html);
    	  }
      }
  });
  /***************************************************************************************/
 
  	


  //breadcrumb
  var _pid = getUrlParam('pid');
  var _cid = getUrlParam('cid');
  var _cname = getUrlParam('cname');
  var _scid = getUrlParam('scid');
  var _scname = getUrlParam('scname');
  var _breadHtml = '<li><a href="/">首页</a></li>';
  //if(_cid && _cname){
  //  _breadHtml += '<li><a href="products.html?pid='+_pid+'&cid='+_cid+'&cname='+_cname+'">'+_cname+'</a></li>';
  //}
  if(_scid && _scname){
    _breadHtml += '<li><a href="products.html?pid='+_pid+'&cid='+_cid+'&cname='+_cname+'&scid='+_scid+'&scname='+_scname+'">'+_scname+'</a></li>';
  }
  $('.breadcrumb > ul').empty().append(_breadHtml);

///////////////////////////////////
});


function queryCondiction(id){
	// 查询品牌
		$.ajax({
				url:'/categories/queryBrandList.do?t='+Math.random(),
				type:'post', // 数据发送方式
				dataType:'json', // 接受数据格式 (这里有很多,常用的有html,xml,js,json)
				data:{CategoryId:id},
				success: function(msg){ // 成功
					if(msg.code<20000){
						$('#prop-chandi').html('');
						var obj = msg.obj;
						var html ='';
						for(var i in obj){
							html += '<li><a href="'+PATH + "/products.html?brid="+obj[i].id+'&pid='+id+'" title="'+ obj[i].name +'">'+ obj[i].name  +'</a></li>';
						}
						$('#attrs_brandlist').html(html);

            $('.brand-attr .values').each(function () {
                var h = $(this).find('.fold').height();
                if (h > 36) {
                    $(this).find('.option').show();
                    $(this).find('.fold').addClass('unfold');
                } else {
                    $(this).find('.option').hide();
                }
            });

					}else{
						$('#attrs_brandlist').html('');
//						alert(msg.value);
					}
				},error: function(){ // 失败
					// TODO:返回异常数据
				}
			});
		
		// 查询产地
		$.ajax({
				url:'/product/queryBirthArea.do?t='+Math.random(),
				type:'post', // 数据发送方式
				dataType:'json', // 接受数据格式 (这里有很多,常用的有html,xml,js,json)
				data:{categoryId:id},
				success: function(msg){ // 成功
					if(msg.code<20000){
						$('#prop-chandi').html('');
						var obj = msg.obj;
						var html ='';
						for(var i in obj){
							html += '<li><a href="'+PATH + "/products.html?addrname="+(obj[i] !='' ? obj[i] :'其他')+'&pid='+id+'" title="'+ obj[i] +'" title="'+ (obj[i] !='' ? obj[i] :'其他') +'">'+ (obj[i] !='' ? obj[i] :'其他') +'</a></li>';
						}
						$('#prop-chandi').html(html);

            $('.place-attr .values').each(function () {
                var h = $(this).find('.fold').height();
                if (h > 36) {
                    $(this).find('.option').show();
                    $(this).find('.fold').addClass('unfold');
                } else {
                    $(this).find('.option').hide();
                }
            });

					}else{
						$('#prop-chandi').html('');
//						alert(msg.value);
					}
				},error: function(){ // 失败
					// TODO:返回异常数据
				}
			});


    $('.option .more').click(function () {
          if ($(this).hasClass('fold')) {
              $(this).removeClass('fold').html('<b></b>更多');
              $(this).parent().siblings().addClass('unfold');
          } else {
              $(this).addClass('fold').html('<b></b>收起');
              $(this).parent().siblings().removeClass('unfold');
          }
      });

   ////////////////////////
  }

  //价格区间清除
  $('#pricerange .clear').click(function(){
      $('#txtMinPirce').val('');
      $('#txtMaxPirce').val('');
  });

  //价格区间确定
  $('#pricerange .submit').click(function(){
      queryProducts();
  });
  
  //搜索按钮
  $('#searchBtn').click(function(){
    queryProducts();
  });


/*
* 查询产品列表，参数delname:需要删除的查询条件，用于清除已选中的条件
*/
function queryProducts(delname){

   if($("#pagination").pagination()){
      $("#pagination").pagination('destroy');
   }

   var query={};
    query.queryKey= $("#searchBox").val();
    query.type=$('.search span.selected').attr('data-selected');
    var brid = getParam("brid");
    if(null != brid && brid != ''){
      query.brandId=brid;
    }else{
  // query.brandId=$("#searchBox").val();
    }
    var addrname = getUrlParam("addrname");
    if(null != addrname){
      query.BirthArea=addrname;
    }else{
  // query.BirthArea=$("#searchBox").val();
    }
    // query.StartMass=$("#searchBox").val();
    var pid = getParam("pid");
  if(null != pid && pid != ''){
    query.CategoryId=pid;
    queryCondiction(pid);
  }else{
  //    query.CategoryId=$("#searchBox").val();
  }
  
  //  query.SaleCount=$("#searchBox").val();
  query.priceMax=$("#txtMaxPirce").val();
  query.priceMin=$("#txtMinPirce").val();
  //  query.price=$("#searchBox").val();
  //  query.salecount=$("#searchBox").val();
  //  query.currentPage=$("#searchBox").val();
  //  query.pagesize=$("#searchBox").val();

  if(!!delname){
    delete query[delname];
    if(delname == 'BirthArea'){
      $('#select-attr .place-attr').show();
    }
    if(delname == 'brandId'){
      $('#select-attr .brand-attr').show();
    }

    var _selected = $('#select-attr .selected .value a');
    if(_selected.length == 0){
      $('#select-attr .selected').hide();
    }
  }

  $("#pagination").pagination({
    pageSize: 20,
      showJump: true,
      async: false,
      remote: {
          url: "/product/queryProductList_index.do?t="+Math.random(),
          params: query,    //自定义请求参数
          beforeSend: function(XMLHttpRequest){
             $('.products .list').addClass('loading');
          },
          success: function (data, pageIndex) {
              var obj = data.data;
              var html='';
              for(var i in obj){
                var pic = '';
                if(obj[i].picts != null){
                  if(obj[i].picts.indexOf('|') != -1){
                    pic = obj[i].picts.split('|')[0];//多张图片
                  }else{
                    pic = obj[i].picts;
                  }
                  if(pic.charAt(pic.length - 1)=="/"){
					  pic=pic+"1.png";
				  }
                }
//                var url ='http://192.165.3.186:8080/WMb2b-bizshop/';

                var _scid = getUrlParam('scid');
                var _scname = getUrlParam('scname');  
                html += '<li>';
                html += '<div class="list-img"><a href="'+PATH + "/productDetail.html?cid="+obj[i].productId+'&scid='+_scid+'&scname='+_scname+'" target="_blank"><img src="' + (null != pic ? pic: '')+'"></a>';
                //html += ' <div class="favour">';
                //html += '<span id="lblOrderCount_326615">已售<b>'+obj[i].saleCount+'</b>件</span>';
                //html += '<a onclick="" id="cancel326615" href="javascript:void();" style="display: none;">已收藏</a>';
                //html += '<a onclick="addFavoriteFun(326615)" id="coll326615" href="javascript:void();" class="heart_f">收藏商品</a>';
                //html += '</div>';
                html += ' <div class="list-info">';
                html += '<span class="price"><i>市场指导价：</i>¥ '+obj[i].marketPrice+'<i>/'+obj[i].unit+'</i></span>';
                html += '<a class="name" href="'+PATH + "/productDetail.html?cid="+obj[i].productId+'&scid='+_scid+'&scname='+_scname+'" target="_blank" title="'+obj[i].name+'">'+obj[i].name+'</a>';
                html += '<a class="shop" href="'+PATH + "/productDetail.html?cid="+obj[i].productId+'&scid='+_scid+'&scname='+_scname+'" target="_blank">'+obj[i].enterprise+'</a>';
                html += '<span class="location">'+obj[i].birthArea+'</span>';
                html += '</div>';
                html +='</li>';
              }
              $('.pagin .page-number i:eq(0)').text(data.currentPage+1);
              $('.pagin .page-number i:eq(1)').text(data.totalPage);
              
              if(data.currentPage == 0){
                $('.pagin .icon_lr .prev').addClass('disabled');
              }else{
                $('.pagin .icon_lr .prev').removeClass('disabled');
              }

              if(data.currentPage+1 == data.totalPage){
                 $('.pagin .icon_lr .next').addClass('disabled');
              }else{
                 $('.pagin .icon_lr .next').removeClass('disabled');
              }

              $('.products .list > ul').html(html);
              $('.breadcrumb .count em').text(data.totalNum);
              $('.products .list').removeClass('loading');
          },
          complete:function(){
             $('.products .list').removeClass('loading');
          },
          pageIndexName: 'currentPage', 
          pageSizeName: 'pagesize', 
          totalName:'totalNum'
      }
  });
}