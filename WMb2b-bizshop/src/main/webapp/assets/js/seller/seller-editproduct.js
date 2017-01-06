
$(function(){
    // $('#dvFast').on('show.bs.collapse', function () {
    //     $('.cate .col').addClass('slide');
    // }).on('hide.bs.collapse', function () {
    //     $('.cate .col').removeClass('slide');
    // });

    var _ueditor = UE.getEditor('product-detail');

    var id=GetQueryString("id");
	$('.select-category').hide();
    $('.add-product').show(); 

    function verifyProductField(){
        var _bool = true;
         $('.add-product input.required').each(function(){
            if($.trim($(this).val()) == ""){
                $(this).siblings('.error-msg').show();
                _bool = false;
            }else{
                $(this).siblings('.error-msg').hide();
            }
         });

         $('.add-product select.required').each(function(){
            if($.trim($(this).val()) == "" || $.trim($(this).val()) == "0"){
                $(this).siblings('.error-msg').show();
                _bool = false;
            }else{
                $(this).siblings('.error-msg').hide();
            }
         });

         var _productImg = $('#product-photo .uploadpic:first img');
         if(_productImg.length == 0){
            $('#product-photo .error-msg').show();
            _bool = false;
         }else{
            $('#product-photo .error-msg').hide();
         }
         return _bool;
    }

    
    //提交编辑产品
     $('#btnsubmit').click(function(){
       
        //未认证时弹出此框     
        /*$.dialog({
            title:'温馨提示',
            content:'您好！通过认证 即可发布您的商品！',
            ok:function(){
            },
            cancel:function(){
               
            },
            okVal:'去认证',
            lock:true
        });*/

        if(!verifyProductField()){
            return false;
        }

        var imgArr=[];
        $('.uploadpic img').each(function(){
            imgArr.push($(this).attr('src'));   
        });
        var  imagstr = imgArr.join('|');
        //for( var is=0;is<imgArr.length;is++){
        //  imagstr=imagstr+imgArr[is]+"|";
        //};
        var parm =new Object();
        parm.productId=id;
//        parm.categoryId= 6;  //类别id $("#categeryid").val()
        parm.categoryId= $("#categeryid").val();
        parm.brandId = $("#brandlist").val();   //品牌
        parm.name  =  $("#name").val();     //产品名称
        parm.code = $("#code").val();      //商品编码
        parm.picts=  imagstr;   //商品图片
        parm.advertise  =$("#advertises").val();  //商品广告词 
        parm.birthArea= $('#countyDiv').val(); //商品产地
        parm.prod_province=$('#provinceDiv').val();
        parm.prod_city=$('#cityDiv').val();
        parm.prod_area=$('#countyDiv').val();
        parm.detail=  _ueditor.getContent();  //商品详情
        parm.marketPrice=$("#marketPrice").val();       //市场价
        // parm.maxPrice=
        parm.unit= $("#unit").val();   //计量单位
        parm.startMass= $("#startMass").val(); //起批量
        parm.model=$("#model").val(); //型号
        parm.sku=$("#sku").val(); //型号
        parm.keyword=$("#keyword").val(); //关键词
        parm.status=1;
        $.ajax({
            url: '/product/updateProduct.do?t='+Math.random(),
            type:'post', //数据发送方式
            dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
            data:parm,
            success: function(msg){ //成功
                if(msg.code=="000000"){
                     $.dialog({
                            title:'商品发布',
                            ok:function(){location.reload();},
                            cancel:function(){location.href = "/pages/seller/Home.html";},
                            okVal:'继续发布',
                            cancelVal:'返回首页',
                            content:'恭喜你，您的商品修改成功'
                        });
                }else if(msg.pageCode=="020021"){
                    location.href = "../../pages/redirect-login.html";
                }
            },error: function(){ //失败
                //TODO:返回异常数据
                $.dialog.errorTips("请求失败！");
            }
        });
        
    });

     //编辑产品分类
     $('.edit-category').on('click', function(){
        $('.select-category').show();
        $('.add-product').hide(); 
     });
    


    //查询品牌
    querymyBrands();
    //图片上传
    $('.uploadpic').imgUploadPreview({
        url:'/upload/uploadproducts.do?t='+Math.random()
    });
    //加载数据
    
    $.ajax({
        url: '/product/queryProductbyId.do?t='+Math.random(),
        type:'post', //数据发送方式
        dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
        data:{"Id":id},
        success: function(msg){ //成功
            if(msg.code=="000000"){
            	 $("#categeryid").val(msg.obj.categoryId);  //类别id
            	 $("#categeryname").val(msg.obj.categoryname);
                 $("#brandlist").val(msg.obj.brandId);   //品牌
                 $("#name").val(msg.obj.name);     //产品名称
                //parm.code = $("#code").val();      //商品编码
                var imags =  msg.obj.picts.split("|");
                var imgs = $('.field_value .uploadpic');
                for(var i =0;i<imags.length;i++){
                	imgs.eq(i).append(
                			 '<img src="'+imags[i]+'" >\
                             <a class="remove" title="删除"></a>'
                	);
                }
               $("#advertises").val(msg.obj.advertise);  //商品广告词 
               $('#countyDiv').val(msg.obj.birthArea); //商品产地
               _ueditor.addListener("ready", function () { //商品详情
                    _ueditor.setContent(msg.obj.detail);
               });
               $("#marketPrice").val(msg.obj.marketPrice);       //市场价
               $("#unit").val(msg.obj.unit);   //计量单位
               $("#startMass").val(msg.obj.startMass); //起批量
               $("#model").val(msg.obj.model); //型号
               $("#sku").val(msg.obj.sku); //规格
               $("#keyword").val(msg.obj.keyword); //关键词
               pid= msg.obj.prod_province;
               cid=msg.obj.prod_city;
               cuid =msg.obj.prod_area;  
               $('#provinceDiv').children().each(function () {
                   if ($(this).val() == pid) {
                       $(this).parent().val('').val(pid);
                       city = fnSelect(province, pid, 'city');
                       cityDiv.html('<option value="0">请选择</option>');
                       countyDiv.html('<option value="0">请选择</option>');
                       createElem(city, cityDiv, pid);
                       $('#cityDiv').children().each(function () {
                           if ($(this).val() == cid) {
                               $(this).parent().val(cid);
                               city = fnSelect(province, pid, 'city');
                               county = fnSelect(city, cid, 'county');
                               countyDiv.html('<option value="0">请选择</option>');
                               createElem(county, countyDiv);
                               $('#countyDiv').children().each(function () {
                                   if ($(this).val() == cuid) {
                                       $(this).parent().val(cuid);
                                   }
                               });
                               return;
                           }
                       });
                       return;
                   }
               });
               
               
            }else if(msg.pageCode=="020021"){
                location.href = "../../pages/redirect-login.html";
            }
        },error: function(){ //失败
            //TODO:返回异常数据
            $.dialog.errorTips("请求失败！");
        }
    });
});


    var categoryData = null;
    var productId = 0;
    var categoryIds = "";
    var saveCategoryCount = 10; //保留最新的10个常用分类
    var saveCategoryDays = 30; //常用分类保存天数
    var isShowFast = 0; //常用分类是否打开

    var _option = "<a class=\"select\"><span data-id=\"#ID#\" data-pid=\"#PID#\" data-level=\"#LEVEL#\">#NAME#</span><i class=\"tag\">&gt;</i></a>";
    $(document).ready(function () {

        productId = GetQueryString("productId") || 0;
        categoryIds = GetQueryString("categoryIds") || "";
        //加载分类数据
        initCategory();
        //加载常用分类
//        initFast();

        //下一步点击事件
        $("#btnNext").click(function () {
            if (!$("#hidCategoryIds").val()) {
                $.dialog.errorTips('请选择完整商品分类！');
                return;
            }
        	var firstname = $("#hidCategoryNames").val().split("_")[0];
        	var seccondname = $("#hidCategoryNames").val().split("_")[1];
        	var thirdname = $("#hidCategoryNames").val().split("_")[1];
        	var thirdId = $("#hidCategoryIds").val().split("_")[1];
        	//商家经营类目判断
            $.ajax({
                url: '/categories/querycategerybyUserid.do?t='+Math.random(),
                async: false,
                dataType: 'json',
                data: { categoryId: thirdId },
                success: function (data) {
                    if (!(data.code=="000000")) {
                        $.dialog.errorTips("店铺还没有添加此经营类目！");
                        return;
                    }
                    else {
                        $('.select-category').hide();
                        $('.add-product').show();  
                        $("#categeryid").val(thirdId);
                        $("#categeryname").val(unescape(firstname)+">"+unescape(seccondname));
                    }
                },
                error: function (e) {
                    console.log(e);
                }
            });


        });

        //常用分类点击事件
        $('#dvFast').on('click', function (e) {
            if (isShowFast == 0) {
                $('#dvHistory').css({
                    position: 'absolute',
                    display: 'block',
                    left: '20px',
                    top: '57px',
                    background: '#fff',
                    zIndex: '999'
                }).slideDown(100);
                isShowFast = 1;
                $('#spFastDown .col').addClass('slide');
            } else {
                $('#dvHistory').css({
                    display: 'none'
                });
                isShowFast = 0;
                $('#spFastDown .col').removeClass('slide');
            }
        });

        //常用分类项选择事件
        $("#dvHistory .history").bind("click", function () {
            var ids = $(this).find("a:first").attr("data-ids");
            var categoryNames = $(this).find("a:first").text();
            $('#spFast').html(categoryNames);
            $("#dvHistory").slideUp(100);
            isShowFast = 0;
            $('#spFastDown').html('<i class="glyphicon glyphicon-chevron-down"></i>');
            initByIds(ids);
        });
    });



    //加载分类数据
    function initCategory() {
        $.ajaxSetup({ cache: false });
        $.get("/categories/queryCateGoriesList.do?t="+Math.random(), function (data) {
            if (!data) {
                $.dialog.errorTips('商品分类加载不正确，请刷新后重试！');
                return;
            }

            categoryData = data.obj;
            $(data.obj).each(function () {
                var firstId = this.id;
                var firstName = this.name;
                //加载一级分类
                $("#dvFirst").append(_option.replace("#ID#", firstId).replace("#PID#", "0").replace("#LEVEL", "1").replace("#NAME#", firstName));
            });

            $("#dvFirst").find("a").bind("click", function () { categorySelected(this, 1); });

            //参数初始化更新选中状态
            initByIds(categoryIds);
        });
    };

    //参数初始化更新选中状态
    function initByIds(ids) {
        if (!ids) return;
        if (ids.indexOf("_") == -1) {

        }
        var firstId = ids.split("_")[0];
        var secondId = ids.split("_")[1];
        var thirdId = ids.split("_")[2];
        categorySelected(null, 1, firstId);
        categorySelected(null, 2, secondId);
        categorySelected(null, 3, thirdId);
    }

    //分类选择事件
    function categorySelected(obj, level, selectId) {
        if (!categoryData) {
            $.dialog.errorTips('商品分类加载不正确，请刷新后重试！');
            return;
        }

        if (obj)
            obj = $(obj);
        else if (selectId) {
            //通过ID找到对象
            var dvId = "";
            if (level == 1)
                dvId = "#dvFirst";
            else if (level == 2)
                dvId = "#dvSecond";
            else if (level == 3)
                dvId = "#dvThird";
            obj = $(dvId).find("span[data-id='" + selectId + "']").parent();
        }

        var selectId = obj.find("span:first").attr("data-id");
        if (level == 1) {
            //一级分类点击时,加载二级分类
            $("#dvFirst").find(".hover").removeClass("hover");
            obj.addClass("hover");
            $("#dvSecond,#dvThird").empty();

            $(categoryData).each(function () {
                if (this.id == selectId && this.keyValue) {
                    //加载二级分类
                    $(this.keyValue).each(function () {
                        var secondId = this.id;
                        var secondName = this.name;
                        $("#dvSecond").append(_option.replace("#ID#", secondId).replace("#PID#", selectId).replace("#LEVEL", "2").replace("#NAME#", secondName));
                    });

                    $("#dvSecond").find("a").bind("click", function () { categorySelected(this, 2); });
                }
            });

            //点击一级分类清空值
            if ($("#hidCategoryIds").val()) {
                try {
                    var firstId = $("#hidCategoryIds").val().split("_")[0];
                    if (firstId != selectId)
                        $("#hidCategoryIds,#hidCategoryNames").val("");
                } catch (e) {
                    $("#hidCategoryIds,#hidCategoryNames").val("");
                }
            }
        } else if (level == 2) {
            //二级分类点击时,加载三级分类
            $("#dvSecond").find(".hover").removeClass("hover");
            obj.addClass("hover");
            $("#dvThird").empty();

            $(categoryData).each(function () {
                if (this.keyValue) {
                    $(this.keyValue).each(function () {
                        if (this.id == selectId && this.keyValue) {
                            var secondId = this.id;
                            var secondName = this.name;
                            //加载三级分类
                            $(this.keyValue).each(function () {
                                var thirdId = this.id;
                                var thirdName = this.name;
                                $("#dvThird").append(_option.replace("#ID#", thirdId).replace("#PID#", secondId).replace("#LEVEL", "2").replace("#NAME#", thirdName));
                            });
                        }
                    });

                    $("#dvThird").find("a").bind("click", function () { categorySelected(this, 3); });
                }
            });
            //点击二级分类清空值
            if ($("#hidCategoryIds").val()) {
                try {
                    var firstId = $("#hidCategoryIds").val().split("_")[1];
                    if (firstId != selectId)
                        $("#hidCategoryIds,#hidCategoryNames").val("");
                } catch (e) {
                    $("#hidCategoryIds,#hidCategoryNames").val("");
                }
            }
            
          //三级分类点击时，设置样式
            $("#dvThird").find(".hover").removeClass("hover");
            obj.addClass("hover");
            var firstId = $("#dvFirst .hover").find("span:first").attr("data-id");;
            var firstName = $("#dvFirst .hover").find("span:first").text();
            var secondId = $("#dvSecond .hover").find("span:first").attr("data-id");;
            var secondName = $("#dvSecond .hover").find("span:first").text();
            var thirdName = obj.find("span:first").text();

            $("#hidCategoryIds").val(firstId + "_" + secondId );
            $("#hidCategoryNames").val(escape(firstName) + "_" + escape(secondName));
       
        } else if (level == 3) {
        	leveid=level;
        	
            //三级分类点击时，设置样式
            $("#dvThird").find(".hover").removeClass("hover");
            obj.addClass("hover");
            var firstId = $("#dvFirst .hover").find("span:first").attr("data-id");;
            var firstName = $("#dvFirst .hover").find("span:first").text();
            var secondId = $("#dvSecond .hover").find("span:first").attr("data-id");;
            var secondName = $("#dvSecond .hover").find("span:first").text();
            var thirdName = obj.find("span:first").text();

            $("#hidCategoryIds").val(firstId + "_" + secondId + "_" + selectId);
            $("#hidCategoryNames").val(escape(firstName) + "_" + escape(secondName) + "_" + escape(thirdName));
        }
    };

    //加载常用分类
    function initFast() {
        var fast = $.cookie('fastCategory');
        if (fast) {
            var arr = fast.split(",");
            var html = "";
            $(fast.split(",")).each(function () {
                var ids = this.split("|")[0];
                var names = this.split("|")[1];
                $("#dvHistory").append("<div class=\"history\"><a class=\"select\" data-ids=\"" + ids + "\">" + unescape(names).replace(new RegExp(/(_)/g), ' > ') + "</a></div>");
            });
        }
    };

    //添加常用分类Cookie
    function addCookie(cookieVal) {
        if (!cookieVal)
            return;
        var oldCookie = $.cookie("fastCategory");
        if (!oldCookie)
            $.cookie('fastCategory', cookieVal, saveCategoryDays);
        else {
            var isExists = false;
            var tmpCookieVal = $.cookie("fastCategory");
            var saveCookieVal = "";
            $(oldCookie.split(",")).each(function (i) {
                if (cookieVal == this) {
                    isExists = true;
                    return false;
                }
            });

            if (!isExists)
                tmpCookieVal = cookieVal + "," + tmpCookieVal;
            var j = 1;
            $(tmpCookieVal.split(",")).each(function (i) {
                if (this && j <= saveCategoryCount) {
                    saveCookieVal += this + ",";
                    j++;
                }
            });
            if (saveCookieVal)
                saveCookieVal = saveCookieVal.substr(0, saveCookieVal.length - 1);
            $.cookie("fastCategory", saveCookieVal, saveCategoryDays);
        }
    };



 //获得URL参数
function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
};


function querymyBrands() {
    $.ajax({
        url: '/brands/queryBrandsapplylist.do?t='+Math.random(),
        type:'post', //数据发送方式
        async: false,
        dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
        data:{"auditStatus":1},
        success: function(msg){ //成功
            if(msg.pageCode=="000000"){
                var html='<option value="0">请选择品牌...</option>';
                for(var i=0;i<msg.data.length;i++){
                    html+='<option value="'+msg.data[i].brandId+'">'+msg.data[i].brandName+'</option>';
                }
                $("#brandlist").html(html);
            }else if(msg.pageCode=="020021"){
                location.href = "../../pages/redirect-login.html";
            }
        },error: function(){ //失败
            //TODO:返回异常数据
            $.dialog.errorTips("请求失败！");
        }
    });
}
var city = [];
        var provinceDiv = $('#provinceDiv');
        var cityDiv = $('#cityDiv');
        var countyDiv = $('#countyDiv');
        var areaName = $('#areaName');
        var createElem = function (data, elem, id) {
            if (!data) { return; }
            for (var i = 0, e; e = data[i++];) {
                if (id) {
                    elem.append('<option value="' + e.id + '" data="' + id + '">' + e.name + '</option>');
                } else {
                    elem.append('<option value="' + e.id + '">' + e.name + '</option>');
                }
            }
        };
        createElem(province, provinceDiv);

        var fnSelect = function (data, val, tag) {
            if (!data) { return; }
            for (var i = 0, e; e = data[i++];) {
                if (e.id == val) {
                    return e[tag];
                }
            }
        };
        provinceDiv.change(function (e) {
            var t = e.target,
                id = $(this).val(),
                city;
            if (id != 0) {
                city = fnSelect(province, id, 'city');
                cityDiv.html('<option value="0">请选择</option>');
                countyDiv.html('<option value="0">请选择</option>');
                createElem(city, cityDiv, id);
                //areaName.find('span').eq(0).html($(this).find("option:selected").html() + '&nbsp;');
                //areaName.find('span').eq(1).html('');
                //areaName.find('span').eq(2).html('');
            }
            return false;
        });
        cityDiv.change(function (e) {
            var t = e.target,
                id = $(this).val(),
                tag,
                city,
                county;
            if (id != 0) {
                tag = $(this).find("option:selected").attr('data');
                city = fnSelect(province, tag, 'city');
                county = fnSelect(city, id, 'county');
                countyDiv.html('<option value="0">请选择</option>');
                createElem(county, countyDiv);
                //areaName.find('span').eq(1).html($(this).find("option:selected").html() + '&nbsp;');
                //areaName.find('span').eq(2).html('');
            }
        });
        countyDiv.change(function (e) {
            var id = $(this).val();
            if (id != 0) {
                //areaName.find('span').eq(2).html($(this).find("option:selected").html());
            }
        });

var getOption = function (elem, bool) {
        var s, t;
        if (bool) {
            elem.children().each(function (i, e) {
                s = e.selected;
                if (s == true) {
                    t = $(e).html();
                    if (t == '请选择') {
                        t = '';
                    }
                    return;
                }
            });
        } else {
            elem.children().each(function (i, e) {
                s = e.selected;
                if (s == true) {
                    t = $(e).val();
                    return;
                }
            });
        }
        return t;
    };
    //判断是否选择地区
    var isSelectAddr = function (p, c, t) {
        if (!p || !c || !t)
            return false;
        var haveProvince = false;
        var haveCity = false;
        var haveTown = false;
        p.children().each(function (i, e) {
            s = e.selected;
            if (s == true && i > 0) {
                haveProvince = true;
                return;
            }
        });
        if (haveProvince) {
            c.children().each(function (i, e) {
                s = e.selected;
                if (s == true && i > 0) {
                    haveCity = true;
                    return;
                }
            });
            if (haveCity)
            {
                var idx = 0;
                t.children().each(function (i, e) {
                    s = e.selected;
                    idx = i;
                    if (s == true && i > 0) {
                        haveTown = true;
                        return;
                    }
                });
                haveTown = idx > 0 ? haveTown : true;
            }
        }
        return haveProvince && haveCity && haveTown;
    };