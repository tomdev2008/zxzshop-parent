var PATH = '/pages';
$(function () {

/////////////////////////////////////
});


//首页产品等页面header头部内容
if($("#header").length > 0){
    $.ajax({
        type: "get",
        url: "/pages/template/header.html",
        dataType: "html",
        async: false,
        success: function (data) {
            $("#header").append(data);

            //模拟下拉选择框
            $('.select > .selected').click(function(e){
                e.stopPropagation();
                var _this = $(this);
                _this.next().addClass('up');
                _this.siblings('.select-menu').slideDown(300);
            });
             
            $(document).click(function(){
                $('.select > b.arrow').removeClass('up');
                $('.select-menu').slideUp(300);
            });

            $('.select > .select-menu > li').click(function(e){
                var _this = $(this);
                _this.parents('.select').find('.selected').text(_this.text()).addClass(_this.attr('class'));
            });
            //搜索下拉框
            $('.search .select > .select-menu > li').click(function(e){
                var _this = $(this);
                var _val = _this.attr('data-id');
                $('#searchtype').val(_val);
            });

            //语言选择下拉框
            $('.select.language > .select-menu > li').click(function (e) {
                var _this = $(this);
                if (_this.hasClass('en')) {
                    location.href = '/pages/en/index.html';
                } else if (_this.hasClass('cn')) {
                    location.href = '/index.html';
                }
            });


            $("li[name=di]").click(function () {
                var val = $(this).attr("data-id");
                $("#searchtype").val(val);
                if(val==1)
                {
                    $("#searchBox").attr("placeholder", "输入产品名称");
                }
                if(val==2)
                {
                    $("#searchBox").attr("placeholder", "输入供应商名称");
                }
                if(val==3)
                {
                    $("#searchBox").attr("placeholder", "输入品牌名称");
                }
            });

            $('#searchBtn').click(function () {
            	if(location.href.indexOf("products.html") > 0) return false;
                var keyWords = $('#searchBox').val();
                var fileType = $("#searchtype").val();

                if (fileType == 1) {
                    if ($.trim(keyWords) != "") {
                        location.href = "/pages/products.html?queryKey=" + (keyWords ? encodeURI(keyWords) : $('#searchBox').attr('placeholder'));
                    } else {
                        location.href = "/pages/products.html?queryKey=";
                    }
                }
                if (fileType == 2) {
                    if ($.trim(keyWords) != "") {
                        location.href = "/pages/products.html?queryKey=" + (keyWords ? encodeURI(keyWords) : $('#searchBox').attr('placeholder'));
                    } else {
                        location.href = "/pages/products.html?queryKey=";
                    }
                }
                if (fileType == 3) {
                    if ($.trim(keyWords) != "") {
                        location.href = "/pages/products.html?queryKey=" + (keyWords ? encodeURI(keyWords) : $('#searchBox').attr('placeholder'));
                    } else {
                        location.href = "/pages/products.html?queryKey=";
                    }
                }
                return false;	
            });

        }
    });
}




function getParam(paramName) {  
    paramValue = "", isFound = !1;  
    if (this.location.search.indexOf("?") == 0 && this.location.search.indexOf("=") > 1) {  
        arrSource = unescape(this.location.search).substring(1, this.location.search.length).split("&"), i = 0;  
        while (i < arrSource.length && !isFound) arrSource[i].indexOf("=") > 0 && arrSource[i].split("=")[0].toLowerCase() == paramName.toLowerCase() && (paramValue = arrSource[i].split("=")[1], isFound = !0), i++  
    }  
    return paramValue == "" && (paramValue = null), paramValue  
}

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null;
}

