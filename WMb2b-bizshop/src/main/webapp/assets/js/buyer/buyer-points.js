$(function(){
   $("#UserName").text($.cookie("UserName"));
   //选择start
   $('.points .stars i').hover(function(){
        var _this = $(this), _index = _this.index();
        var _starts = _this.parent().find('i');
        _starts.each(function(index, element){
            if(index <= _index){
                $(this).toggleClass('active');
            }
        });
   });
   $('.points .stars i').click(function(){
        var _this = $(this), _index = _this.index();
        _this.parents('li').attr('data-point', _index+1);
        var _starts = _this.parent().find('i');
        _starts.removeClass('clicked');
        _starts.each(function(index, element){
            if(index <= _index){
                $(this).addClass('clicked');
            }
        });
   });


   //提交评价
   $('#submit-point').click(function(){
        var _checked = $('input[name=level]:checked');
        var _level = _checked ? _checked.val() : '';
        var _speeds = $('.points li.speeds').attr('data-point');
        var _service  = $('.points li.service').attr('data-point');
        var _remarks = $('#remark-point').val();
        var _inquiryNo = $.cookie("InquiryMangementInquiryNo");
        var _userId = $.cookie("wm_user_id");
        $.cookie("CommentInquiryInquiryNo",_inquiryNo);
        var _data = {customerId: _userId, quotationSpeed: _speeds,inquirySheetCode:_inquiryNo,
        		serviceAttitude:_service, suggestion: _remarks,level:_level};
        $.ajax({
            type : "POST",
            url : "/inquiry/commentInquiry.do",
            data: _data,
            dataType : "json",
            success : function(data) {
                if (data.code == '000000') {
                	$.cookie("CommentInquiryDealNo",data.obj);
                	$.dialog.succeedTips('评价成功',function(){
                		if(_level == 1){
                			window.location.href = '/pages/buyer/InquiryManagement.html';
                		}else{
                			window.location.href = '/pages/buyer/payway.html';
                		}
    				});
                }else{
                	$.dialog.errorTips('评论失败' + data.value);
                }
            },
            error : function(data) {
                $.dialog.errorTips(data.Data);
                return;
            }
        });

   });

/////////////////////////////////////////////////////////////
});

