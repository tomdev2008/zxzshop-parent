$(function(){
    var returnv =  $("#path").val()+"/enterprise/visit.do",
        audit =  $("#path").val()+"/enterprise/audit.do";
    var compid = $("#compid").val();//企业id
    $('#check_pass').click(function(){
        var compname = $("#compname").val(),
            registerCapital = $("#registerCapital").val(),
            companyAddress = $("#companyAddress").val(),
            contactName = $("#contactName").val(),
            contactPhone = $("#contactPhone").val();
        var bussinesscode = $("#bussinesscode").val(),
            organizationcode = $("#organizationcode").val(),
            taxcode = $("#taxcode").val(),
            creditcode = $("#creditcode").val();
        //var bussinesspic = $("#businessimg img").attr("src"),
        //    organizationpic = $("#organizationimg img").attr("src"),
        //    taxpic = $("#taximg img").attr("src"),
        //    creditpic = $("#creditimg img").attr("src");

        if(compname=="" ||compname ==null ||compname ==undefined){
            BUI.Message.Alert("请输入企业名称","warning");
            return false;
        }
        if(registerCapital=="" || registerCapital==null ||registerCapital ==="0"||registerCapital==="0.0"){
            BUI.Message.Alert("请输入注册资金","warning");
            return false;
        }
        if(companyAddress=="" || companyAddress==null){
            BUI.Message.Alert("请输入详细地址","warning");
            return false;
        }
        if(contactName=="" || contactName==null){
            BUI.Message.Alert("请输入联系人姓名","warning");
            return false;
        }
        if(contactPhone=="" || contactPhone==null /*|| !(/^0{0,1}(13[0-9]|15[0-9]|153|156|18[7-9])[0-9]{8}$/.test(contactPhone))*/){
            BUI.Message.Alert("请输入正确的联系人电话","warning");
            return false;
        }
        if(creditcode == null||creditcode == ""){//三证为空
            if(bussinesscode==""||organizationcode==""||taxcode==""){
                BUI.Message.Alert("三证必须填写完整或填写社会机构信用代码证！","warning");
                return;
            }else{
                document.forms[0].submit();
            }
        }else{//三证不为空
            document.forms[0].submit();
        }

        //$.dialog({
        //    title:'审核通过',
        //    width:500,
        //    height:150,
        //    lock:true,
        //    content:'<p style="text-align:center;line-height:30px">恭喜您，当前企业通过审核！<br><span class="highlight">3秒后自动返回企业管理</span></p>'
        //});
    });
    $('#check_unpass').click(function(){
        $.dialog({
            title:'客户回访',
            ok:function(){
                var cont = $("#cont").val();
                $.ajax({
                    url:returnv,
                    type:"post",
                    dataType:"json",
                    data:{
                        "enterpriseId":compid,
                        "content":cont
                    },
                    success:function(data){
                        if(data.code=="000000"){
                            BUI.Message.Alert('提交成功！','success');
                            setTimeout(function(){
                                location.reload();
                            },1000);
                        }
                    },
                    error:function(){
                        alert("失败");
                    }
                });
            },
            cancel:function(){},
            lock:true,
            content:document.getElementById('check_unpass_content')
        })
    })

    var path =  $("#path").val()+"/upload/uploadFile.do?path=enterprise";
    //图片上传
    $('.uploadpic').imgUploadPreview({
        url:path
    });
})



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