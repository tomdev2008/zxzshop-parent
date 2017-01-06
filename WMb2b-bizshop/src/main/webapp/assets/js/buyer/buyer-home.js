function buyerHomeLoad(){
	var userId=$.cookie("wm_user_id");
	publicAjaxRequest("/userinfo/getUserInfo.do?Id=" + userId,null,userFix);
	publicAjaxRequest("/userinfo/getEnterpriseInfo.do?categery=1&userid=" + userId,null,companyFix);
	publicAjaxRequest("/inquiry/getStatistic.do?userId="+userId,null,function(data){
		if(data.code== '000000'){
			var countWait = 0;
			var countHas = 0;
			var countOver = 0;
			if(data.obj != null){
				for(var idx = 0;idx < data.obj.length;idx++){
					if(data.obj[idx].key<4){
						countWait += parseInt(data.obj[idx].value);
					}else if(data.obj[idx].key >=4 && data.obj[idx].key <7){
						countHas += parseInt(data.obj[idx].value);
					}else if(data.obj[idx].key == 7){
						countOver += parseInt(data.obj[idx].value);
					}
				}			
			}
			var content = "<li><a href='InquiryManagement.html?nav=InquiryManagement&status=3'>待报价（"+countWait+"）</a></li>\
			<li><a href='InquiryManagement.html?nav=InquiryManagement&status=6'>待已报价（"+countHas+"）</a></li>\
			<li><a href='InquiryManagement.html?nav=InquiryManagement&status=6'>询价结束（"+countOver+"）</a></li>";
			$("#InquiryStatistic").html(content);
		}else if(data.code == '020021'){
			window.location.href = "/pages/redirect-login.html";
		}
	});
	publicAjaxRequest("/orderInfo/getStatistic.do?userId="+userId,null,function(data){
		if(data.code== '000000'){
			var countSellerSign = 0;
			var countSellerSend = 0;
			var countOver = 0;
			var countClose = 0;
			if(data.obj != null){
				for(var idx = 0;idx < data.obj.length;idx++){
					if(data.obj[idx].key >= 40 && data.obj[idx].key <= 50){
						countSellerSign += parseInt(data.obj[idx].value);
					}else if(data.obj[idx].key >=60 && data.obj[idx].key <= 80){
						countSellerSend += parseInt(data.obj[idx].value);
					}else if(data.obj[idx].key == 90){
						countOver += parseInt(data.obj[idx].value);
					}else if(data.obj[idx].key == 99){
						countClose += parseInt(data.obj[idx].value);
					}
				}			
			}
			var content = "<li><a href='Order.html?nav=Order&status=30'>卖家已签约（"+countSellerSign+"）</a></li>\
                    <li><a href='Order.html?nav=Order&status=60'>卖家已发货（"+countSellerSend+"）</a></li>\
                    <li><a href='Order.html?nav=Order&status=90'>交易完成（"+countOver+"）</a></li>\
                    <li><a href='Order.html?nav=Order&status=99'>已关闭（"+countClose+"）</a></li>";
			$("#OrderStatisitc").html(content);
		}else if(data.code == '020021'){
			window.location.href = "/pages/redirect-login.html";
		}
	});
}

function userFix(data){
	if(data.code == '000000'){
		$.cookie("UserId", data.obj.id, {
			expires : 365,
			path : '/'
		});
		var userName = "";
		$('.mobile').text(data.obj.cellPhone);
		if(data.obj.realName != null && data.obj.realName != ''){
			userName = data.obj.realName;
		}else if(data.obj.userName != null && data.obj.userName != ''){
			userName = data.obj.userName;
		}else{
			userName = data.obj.cellPhone;
		}
		$(".user").text(userName);
		$.cookie("UserName",userName,{
			expires : 365,
			path : '/'
		});
		$(".user img").attr("src",data.obj.photo);
	}
}
function companyFix(data){
	if(data.code == '000000'){
		$.cookie("EnterpriseId", data.obj.id, {
			expires : 365,
			path : '/'
		});
		$("#EnterpriseName").text(data.obj.companyName);
		if(data.obj.certifStatus == 2){
			$("#VerifiedStatus").show();
		}else{
			$("#VerifiedStatus").hide();
		}
	}
}


$(document).ready(function () {

    //头部导航下拉
    $('.navbar-nav .dropdown').hover(function () {
		$(this).addClass('open');
    }, function () {
		$(this).removeClass('open');
	});

    $('.navbar-nav .dropdown-menu').each(function () {
        $(this).css('marginLeft', -$(this).width() / 2);
	});

	//左侧菜单切换
    $('.navbar-nav .dropdown').each(function () { $(this).find('li').eq(0).addClass('current'); });

    $('.navbar-nav.navbar-left').find('.dropdown').click(function () {
        var str = $(this).find('.dropdown-menu').html();
		$(this).addClass('active').siblings().removeClass('active').end().find('li').first().addClass('current').siblings().removeClass();;
		$('.aside-list').html(str);

		//默认跳转至第一个二级菜单的链接
		$("iframe").attr("src", $(this).find('li').first().children().attr("href"));

		//修改对应图标
        var ico = 'ico' + ($(this).index() + 1);
		$('.column-icon i').removeClass().addClass(ico);
    });


    $('.aside-list,.navbar-nav .dropdown').on('click', 'li', function () {
        $(this).addClass('current').siblings().removeClass('current');
    });

    $('.nav.navbar-right .dropdown li').click(function () {
        var href = $(this).find('a').attr('href');
        if (typeof (href) == "undefined") {
			return false;
        } else {
            $('.nav.navbar-left li').find('a').each(function () {
                if (href == $(this).attr('href')) {
					$(this).parent().click();
                }
			});
		}
    });

    var headerHeight = $('body').hasClass('no-topbar') ? 80 : 112;
	$('.content,.aside').height($(window).height()-headerHeight);
    var wWidth = $(window).width();
    if(wWidth < 1200){
        $('.container > .content').css("position", "initial");
        $('.container > .content').css("width", "auto");
    }else{
         $('.container > .content').css('width', (wWidth - 1200)/2 + 1020);
         $('.container > .content').css("position", "fixed");
    }
	$(window).resize(function() {
        $('.content,.aside').height($(window).height()-headerHeight);
        var wWidth = $(window).width();
        if(wWidth < 1200){
            $('.container > .content').css("position", "initial");
            $('.container > .content').css("width", "auto");
        }else{
             $('.container > .content').css('width', (wWidth - 1200)/2 + 1020);
             $('.container > .content').css("position", "fixed");
        }

    });
    var flag = 0;
    var classFull = 'glyphicon-resize-full';
    var classSmall = 'glyphicon-resize-small';
    $('#resize-web').click(function () {
        if ($(this).hasClass('glyphicon-resize-full')) {
            flag = 1;
			$('body').addClass('wide');
			$(this).removeClass(classFull).addClass(classSmall);
        } else {
            flag = 0;
			$('body').removeClass('wide');
			$(this).removeClass(classSmall).addClass(classFull);
		}
        addCookie('resizeWeb', flag, 24);
    });

    var flag = getCookie('resizeWeb');
    if (flag == 1) {
		$('body').addClass('wide');
		$('#resize-web').removeClass(classFull).addClass(classSmall);
	}
    $('.top-bar .dropdown').hover(function () {
        $(this).toggleClass('hover');
    });

});


function addCookie(name, value, expiresHours) {
    var cookieString = name + "=" + escape(value);
    if (expiresHours > 0) {
        var date = new Date();
        date.setTime(date.getTime() + expiresHours * 3600 * 1000);
        cookieString = cookieString + "; expires=" + date.toGMTString();
    }
    document.cookie = cookieString;
}


function getCookie(name) {
    var strCookie = document.cookie;
    var arrCookie = strCookie.split("; ");
    for (var i = 0; i < arrCookie.length; i++) {
        var arr = arrCookie[i].split("=");
        if (arr[0] == name) return arr[1];
    }
    return "";
}

function deleteCookie(name) {
    var date = new Date();
    date.setTime(date.getTime() - 10000);
    document.cookie = name + "=v; expires=" + date.toGMTString();
}

function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

$(function () {
    var myurl = GetQueryString("url");
    if (myurl != null && myurl.toString().length > 1) {
        var tar = GetQueryString("tar").toLowerCase();
        $(".nav.navbar-left ul li a").each(
            function (index, item) {
                if ($(item).attr("href").toLowerCase().indexOf(tar) >= 0) {
                    $(item).parent().click();
                }
            }
        );
        window.iframe.location.href = myurl;
    } else {
        $(".nav li").first().click();
    }
});
