$(function(){
    //图片上传
    $('.uploadpic').imgUploadPreview({
        url:'/upload/uploadCA.do?t='+Math.random()
    });
    
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

    
   
    /*********************query********************/
   var id=getUrlParam("id");
    if(id!=null&& id!=""){
        var bl =false;
        //非法操作认证
        $.ajax({
            url:'/userinfo/enterpriseinfolist.do?t='+Math.random(),
            type:'post', //数据发送方式
            dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
            data:{"userid":2},
            success:function(data){
                if (data.code == '000000') {
                    var obj = data.obj;
                    for(var i=0;i<data.obj.length;i++){
                        if(id==obj[i].id){
                            bl=true;
                        }
                    }
                }else if(data.code=="020021"){
                    location.href = "../../pages/redirect-login.html";
                }
                
                if(bl){
                    //查询信息
                    getEntepriseInfobyId(id);
                }else{
                    $.dialog.errorTips("非法操作！");
                }
                
                
            },error: function(){
                alert('error');
            }
        });
        
        
    }
    /**********************************************************/
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

    function verifyCompanyPhoto(){
        var bool = true;
        var _type = $('#company-type button.active').attr('data-id');
        if(_type == "1"){
            var _len = $('#three_ca_content .uploadpic > img').length;
            if(_len != 3){
                bool = false;
                $('#company-type .error-msg').show();
            }else{
                $('#company-type .error-msg').hide();
            }
        }else{
            var _len = $('#five_ca_content .uploadpic > img').length;
            if(_len != 1){
                bool = false;
                $('#company-type .error-msg').show();
            }else{
                $('#company-type .error-msg').hide();
            }
        }
        return bool;
    }

    function verifyBrandPhoto(){
        var bool = true;
        var _len = $('.brand-authorization .uploadpic > img').length;
        if(_len == 0){
            bool = false;
            $('#brand-authorization .error-msg').show();
        }else{
            $('#brand-authorization .error-msg').hide();
        }
        return bool;
    }

    
    /*************************CA申请***********************************/
    $(".submit").click(function() {
        if(!verifyInputRequired($('#companyName')) || !verifyCompanyPhoto() || !verifyBrandPhoto()){
            return false;
        }
        var _type = $('#company-type button.active').attr('data-id');//三证五证
        var _cardType= $('.agentbtns button.active').attr('data-id');//委托/法人
        var parm = new Object();
        var url='/userinfo/insertEnterprise.do?t='+Math.random();
        var ids=getUrlParam("id");
        if(ids!=null&&ids!=''){
            parm.id=ids;
            url='/userinfo/enterpriseupdatePic.do?t='+Math.random();
        }else{
            parm.categery=1;//企业
            parm.isDefault=0;//不设置默认
        }
        
        parm.companyName=$("#companyName").val();
        parm.enterpriseType=_type;
        //三证五证图片
        if(_type==1){
            var img3 = $('#three_ca_content .uploadpic img');
            parm.businessimage= img3.eq(0).attr('src');//营业执照
            parm.organizationalimage=img3.eq(1).attr('src');//组织机构图片
            parm.taximage=img3.eq(2).attr('src');   //税务
        }else if(_type==2){
            var img4=$('#five_ca_content .uploadpic img');
            parm.fitimage =img4.eq(0).attr('src'); //三证五证合一   
        }
        parm.authCertificate= $('.brand_content .uploadpic img').eq(0).attr('src');    //授权证书
        parm.cardType=_cardType;//委托2/法人1
        if(_cardType==1){
            var img5= $('#three_ca_content2 .uploadpic img');
            parm.positiveimage=img5.eq(0).attr('src');
            parm.flipimage = img5.eq(1).attr('src');
        }else if(_cardType==2){
            var img6= $('#three_ca_content1 .uploadpic img');
            parm.positiveimage=img6.eq(0).attr('src');
            parm.flipimage = img6.eq(1).attr('src');
            parm.proxyimage=img6.eq(2).attr('src');
        }
        
        $.ajax({
            url:url,
            type:'post', //数据发送方式
            dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
            data:parm,//设置查询用户id
            success: function(msg){ //成功
                if(msg.code=="000000"){
                    $.dialog.alert("成功！", function(){
                      location.href = "../../pages/seller/Account.html";
                    });
                }else if(msg.code=="020021"){
                    location.href = "../../pages/redirect-login.html";
                }else if(msg.code=="020001"){
                    $.dialog.confirm("企业资料未审核！", function(){
//                      location.href = "../../pages/seller/AccountCa.html";
                    });
                }
            },error: function(){ //失败
                //TODO:返回异常数据
                $.dialog.errorTips("请求失败！");
            }
        });
        
    });
    /*************************submit***********************************/
    
  
});

function getEntepriseInfobyId(id) {
    $.ajax({
        url:'/userinfo/queryenterpriseinfoCA.do?t='+Math.random(),
        type:'post', //数据发送方式
        dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
        data:{"id":id},//设置查询用户id
        success: function(msg){ //成功
            if(msg.code=="000000"){
                var data=msg.obj;
                if(data.certifStatus==2){
                    $(".submit").hide();
                }else if(data.status==2){
                    $(".submit").hide();
                }
                $("#companyName").val(msg.obj.companyName);
                
                if(data.enterpriseType ==1){
                    var _str1;
                    var img3 = $('#three_ca_content .uploadpic');
                    if(data.businessimage!=null&&data.businessimage!=''){
                        _str1 = '<img src="'+data.businessimage+'">';
                        if(data.certifStatus!=2){
                            _str1+='<a class="remove" title="删除"></a>';
                        }
                        
                        img3.eq(0).append(_str1);
                    }
                    var _str2;
                     if(data.organizationalimage!=null &&data.organizationalimage!=''){
                        _str2 = '<img src="'+data.organizationalimage+'">';
                        if(data.certifStatus!=2){
                            _str2+='<a class="remove" title="删除"></a>';
                        }
                        img3.eq(1).append(_str2); 
                     }
                    
                    var _str3;
                    if(data.taximage!=null&& data.taximage!=''){
                        _str3 = '<img src="'+data.taximage+'">';
                        if(data.certifStatus!=2){
                            _str3+='<a class="remove" title="删除"></a>';
                        }
                        img3.eq(2).append(_str3);
                    }
                }else if(data.enterpriseType ==2){
                    var img4=$('#five_ca_content .uploadpic');
                    
                    var _str4;
                    if(data.fitimage!=null && data.fitimage!=''){
                        _str4 = '<img src="'+data.fitimage+'">';
                        if(data.certifStatus!=2){
                            _str4+='<a class="remove" title="删除"></a>';
                        }
                        img4.eq(0).append(_str4);
                        
                    }
                    $('#company-type button.five_ca').addClass('active').siblings('button').removeClass('active');
                    $('#three_ca_content').hide();
                    $('#five_ca_content').show();
                }
                
                
                
                
                
                
                 //授权证书
                var _str5;
                if(data.authCertificate!=null&&data.authCertificate!=''){
                    _str5 = '<img src="'+data.authCertificate+'">';
                    if(data.certifStatus!=2){
                        _str5+='<a class="remove" title="删除"></a>';
                    }
                    $('.brand_content .uploadpic').eq(0).append(_str5);
                }
                if(data.cardType==1){
                    var _str6;
                    var img6=$('#three_ca_content2 .uploadpic');
                    if(data.positiveimage!=null&&data.positiveimage!=''){
                        _str6 = '<img src="'+data.positiveimage+'">';
                        if(data.certifStatus!=2){
                            _str6+='<a class="remove" title="删除"></a>';
                        }
                        img6.eq(0).append(_str6);
                    }
                    var _str7;
                    if(data.flipimage!=null &&data.flipimage!=''){
                        _str7 = '<img src="'+data.flipimage+'">';
                        if(data.certifStatus!=2){
                            _str7+='<a class="remove" title="删除"></a>';
                        }
                        img6.eq(1).append(_str7);
                    }
                    $('#person-type button.five_ca').addClass('active').siblings('button').removeClass('active');
                    $('#three_ca_content1').hide();
                    $('#three_ca_content2').show();
                }else if(data.cardType==2){
                    
                    var img7=$('#three_ca_content1 .uploadpic');
                    var _str8;
                    if(data.positiveimage!=null&&data.positiveimage!=''){
                        _str8 = '<img src="'+data.positiveimage+'">';
                        if(data.certifStatus!=2){
                            _str8+='<a class="remove" title="删除"></a>';
                        }
                        img7.eq(0).append(_str8);
                    }
                    var _str9;
                    if(data.flipimage!=null && data.flipimage!=''){
                        _str9 = '<img src="'+data.flipimage+'">';
                        if(data.certifStatus!=2){
                            _str9+='<a class="remove" title="删除"></a>';
                        }
                        img7.eq(1).append(_str9);
                    }
                    var _str10;
                    if(data.proxyimage!=null&&data.proxyimage!=''){
                        _str10 = '<img src="'+data.proxyimage+'">';
                        if(data.certifStatus!=2){
                            _str10+='<a class="remove" title="删除"></a>';
                        }
                        img7.eq(2).append(_str10);
                    }
                    
                    $('#person-type button.three_ca').addClass('active').siblings('button').removeClass('active');
                    $('#three_ca_content1').show();
                    $('#three_ca_content2').hide();
                }
                
            }else if(msg.code=="020021"){
                location.href = "../../pages/redirect-login.html";
            }
        },error: function(){ //失败
            //TODO:返回异常数据
            $.dialog.errorTips("请求失败！");
        }
    });
}


//获取url参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null;
}
