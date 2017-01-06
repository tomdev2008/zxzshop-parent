// JavaScript Document


//买家中心新弹出页面头部加载
if($("#buyer-header").length > 0){
    $.ajax({
        type: "get",
        url: "buyer-header.html",
        dataType: "html",
        async: false,
        success: function (data) {
            $("#buyer-header").append(data);
        }
    });
}

//卖家中心新弹出页面头部加载
if($("#seller-header").length > 0){
    $.ajax({
        type: "get",
        url: "seller-header.html",
        dataType: "html",
        async: false,
        success: function (data) {
            $("#seller-header").append(data);
        }
    });
}

//新弹出页面底部加载
$('#inner-footer').load('../template/inner-footer.html', function(){});

$(document).ready(function () {

    //自定义滚动条
    // if (+[1, ]) {
    //     if (window.location.pathname != "/SellerAdmin/Enterprise/ViewPage1" && window.location.pathname.indexOf('/ThirdEnterprise/') == -1) {
    //         $("html").niceScroll({
    //             //styler: 'fb',
    //             cursorcolor: "#c1c1c1",
    //             cursorwidth: "20px",
    //             autohidemode: false
    //         });
    //     }
    // }
    // radio获取选中项的value值
    $('input[name="testradio"]:checked').val();

    //操作提示
    $('.primary-btn').hover(function () {
        $('.primary').toggle();
    });

    //表单搜索下拉
    $(function () {
        if (!$('.search-box').hasClass('only-line')) {

            $('.custom-inline .submit').append('<span class="btn btn-default btn-ssm search-close"><i class="glyphicon glyphicon-remove"></i></span>');

            $('.search-box input,.search-box select').each(function () {
                $(this).focus(function () {
                    $('.custom-inline').addClass('deploy');
                });
                $(this).keydown(function (event) {
                    if (event.keyCode == 13) {
                        try {
                            if ($("#searchButton").length > 0) {
                                $('#searchButton').click();
                            }
                            else if ($("#searchBtn").length > 0) {
                                $('#searchBtn').click();
                            }
                            else if ($("#search").length > 0) {
                                $('#search').click();
                            }
                            searchClose(event);
                        }
                        catch (e) {
                        }
                    }
                });
                //var current = this;
                //if(window.addEventListener){
                //	this.addEventListener('focus', function () {
                //		$('.custom-inline').addClass('deploy');
                //	}, true);
                //}else if(window.attachEvent){
                //	this.attachEvent('onfocus', function () {
                //		$('.custom-inline').addClass('deploy');
                //	}, true);
                //}
            });

            //$('.search-box').not('.only-line').click(function(){
            //	$('.custom-inline').addClass('deploy');
            //});

            $('.search-close').click(function (e) {
                searchClose(e);
            });
        }
        else
        {
            $('.search-box input,.search-box select').each(function () {
                $(this).keydown(function (event) {
                    if (event.keyCode == 13) {
                        if ($("#searchButton").length > 0) {
                            $('#searchButton').click();
                        }
                        else if ($("#searchBtn").length > 0) {
                            $('#searchBtn').click();
                        }
                        else if ($("#search").length > 0) {
                            $('#search').click();
                        }
                    }
                });

            });
        }
    });

    //文本框聚焦自动选中文字
    //$("input[type=text]").click(function() {
    //	this.select();
    //});
    //添加权限组
    $('.checkbox-list li').each(function () {
        var _this = $(this);
        _this.find('.parent-check>input').click(function () {
            _this.children('p').find('input').attr('checked', this.checked);
        })
    });
    $('.power-check-all input').click(function () {
        $('.checkbox-list').find('input[type=checkbox]').attr('checked', this.checked);
    });

});


function searchClose(e) {
    e ? e.stopPropagation() : event.cancelBubble = true;
    $('.custom-inline').removeClass('deploy');
    //$('.start_datetime,.end_datetime').val('');
}


function solvedOpen(elem) {
    var tips = $(window.parent.document).find(elem).text().replace('(', '').replace(')', '');
    if (tips > 0) {
        $('.nav li.solved').click();
    }
}
function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

function htmlDel(str) {
    var s = "";
    if (str.length == 0) return "";
    s = str.replace(/<[^>]+>/g, "");
    return s;

}


function verifyIdNo(num){
   var bool = true;
   // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
   if(reg.test(num) === false){  
      bool = false;  
   }
   return bool;
} 

function verifyNum(num){
    var bool = true;
    var reg = /^\d+$/;
    if(reg.test(num) === false){
       bool = false;  
     }
     return bool;
}

//验证EMAIL格式
function verifyEmail(str){
    var bool = true;
    var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
    if(reg.test(str) === false){
       bool = false;  
     }
     return bool;
}

//验证必填
function verifyInputRequired(dom){
    var bool = true;
    if($.trim(dom.val()) == ""){
        bool = false;
        dom.siblings('.error-msg').show();
    }else{
        dom.siblings('.error-msg').hide();
    }
    return bool;
}


