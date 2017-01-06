//上传主程序
//var UPLOADID=0;
(function ($) {
    function uploadfile(target, opts) {
        var imgupfile = $(target).find("input:file"); //上传控件
        if (imgupfile.val() == "") {
            $.dialog.errorTips("请选择要上传的图片！");
            return false;
        }
        else {
            var flag = true;
                if (imgupfile.size/1024 > opts.maxSize*1024) {
                    flag = false;
                    return;
                }

            if (!checkImgType(imgupfile.val())) {
                $.dialog.errorTips("上传格式为gif、jpeg、jpg、png、bmp", '', 3);
                return false;
            }
            if (!flag) {
                $.dialog.errorTips("上传的图片不能超过" + opts.maxSize + "M");
                return;
            }
        }

        //准备表当
        var myform = document.createElement("form");
        myform.action = opts.url;
        myform.method = "post";
        myform.enctype = "multipart/form-data";
        myform.style.display = "none";
        //将表单加当document上，
        document.body.appendChild(myform);  //重点
        var form = $(myform);
        var fu = imgupfile.clone(true).val(""); //先备份自身,用于提交成功后，再次附加到span中。
        var fu1 = imgupfile.appendTo(form); //然后将自身加到form中。此时form中已经有了file元素。
        $(fu).prependTo($(target));


        //开始模拟提交表当。
        form.ajaxSubmit({     
        	dataType:"text/html",
            success: function (data) {
					var data = JSON.parse(data);
                    var files = data.obj;
                if(data.code=="000000"){
                    var _html ='<img src="'+files+'" >\
                                <a class="remove" title="删除"></a>';
                    $(target).append(_html);
                    $(target).find('.path').val(files);
                }else{
                	 $.dialog.errorTips(data.value);
                }
                form.remove();
            }
        });
    }


    //检查上传的图片格式
    function checkImgType(filename) {
        var pos = filename.lastIndexOf(".");
        var str = filename.substring(pos, filename.length)
        var str1 = str.toLowerCase();
        if (!/\.(gif|jpg|jpeg|png|bmp)$/.test(str1)) {
            return false;
        }
        return true;
    }

    function bindEvent(target, opts) {
       $('input.uploadFilebtn', $(target)).change(function () {
            uploadfile(target, opts);
        });

       $(target).on('click', '.remove', function(){
            $(this).siblings('img').remove();
            $(this).siblings('.id').attr('value','0');
            $(this).siblings('.path').attr('value','');
            $(this).remove();
       });
    }

    $.fn.imgUploadPreview = function (options, param) {
        // if (typeof options == "string") {
        //     return $.fn.imgUploadPreview.methods[options](this, param);
        // }
        options = options || {};
        return this.each(function () {
            var opts = $.extend({}, $.fn.imgUploadPreview.defaults, options);
            //$(this).find('div[imageBox]').each(function () {
                bindEvent(this, opts);
            //});
        });
    };

    // $.fn.imgUploadPreview.methods = {
    //     getImgSrc: function (jQ) {
    //         var images = $(jQ).find('input.hiddenImgSrc');
    //         if (images.length == 1)
    //             return images.val();
    //         else {
    //             var srcArr = [];
    //             images.each(function () {
    //                 var src = $(this).val();
    //                 if (src)
    //                     srcArr.push(src);
    //             });
    //             return srcArr;
    //         }
    //     }
    // };

    $.fn.imgUploadPreview.defaults = {
    		url:"../upload/uploadFile.do",
        maxSize:2  //M为单位
    };
})(jQuery);

$(function(){
	//图片上传
	$('.uploadpic').imgUploadPreview({
		url:"../upload/uploadFile.do?path=/products"
	});
    $('.type ul li').click(function(){
        var _this = $(this);
        if(_this.is('.current')) return;
        _this.addClass('current').siblings('li').removeClass('current');
        var idx = _this.index();
        $('.type').find('.type_items .item').eq(idx).addClass('active').siblings('.item').removeClass('active');
    });
    $('.type .item input[type=checkbox]').click(function(){
        var bool = $(this).prop('checked');
        var val = $(this).val();
        if(bool){
            $('.type_selected').append('<span data-val='+val+'>'+val+'</span>');
        }else{
            $('.type_selected').find('span[data-val='+val+']').remove();
        }
    })
})
