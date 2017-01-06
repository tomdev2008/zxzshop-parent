$(function(){
    $('.info span').click(function(){
        $('.info .case').toggle();
    });
    $('.cabtns button').click(function(){
        $(this).is('.active') || $(this).addClass('active').siblings('button').removeClass('active');
        $(this).index() == 1 ? $('.three_ca_content').eq(0).show().end().eq(1).hide() :  $('.three_ca_content').eq(1).show().end().eq(0).hide();
    });
    $('.agentbtns button').click(function(){
        $(this).is('.active') || $(this).addClass('active').siblings('button').removeClass('active');
        $(this).index() == 1 ? $('.more_info_content .three_ca_content').eq(0).show().end().eq(1).hide() :  $('.more_info_content .three_ca_content').eq(1).show().end().eq(0).hide();
    });
    $('.info_btn').click(function(){
        !$(this).is('.active') ? $(this).addClass('active') && $('.more_info_content').slideDown() : $(this).removeClass('active') && $('.more_info_content').slideUp();
    });


    $('.btn-change').click(function(){
        $.dialog({
            title:'更改手机号',
            ok:function(){},
            cancel:function(){},
            okVal:'确定',
            cancelVal:'取消',
            lock:true,
            content:document.getElementById('change-mobile')
        });
    });

    // 点击完善信息按钮
    $('.more_info .more').click(function() {
        var _this = $(this), _fields = _this.parents('.more_info').find('.more_info_content');
        if (_this.hasClass('up')) {
            _this.removeClass('up');
            _fields.show();
        } else {
            _this.addClass('up');
            _fields.hide();
        }
    });
    
    //账户信息查询
    getEnteriseInformation();
  
    
});



