$(function(){
	query();
	///////////////////////////删除收货地址
	$(document).on('click', '.close', function(){
		var _this = $(this), _id = _this.attr('data-id');
        $.dialog.confirm('确认要删除这个收货地址吗？',function(){
            $.ajax({
                url:'/userinfo/deleteAddress.do?t='+Math.random(),
                type:'post', //数据发送方式
                dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
                data:{"id":_id},
                success:function(data){
                    if(data.code=="000000"){
                        $.dialog.succeedTips("删除成功！");
                        _this.parents('.item').remove();
                    }else{
                        $.dialog.errorTips("删除失败！");
                    }
                },error: function(){
                    $.dialog.errorTips("error！");
                }
            }); 
        });	
	});
		
   $(document).on('click', '.add_address', function(){
        $.dialog({
            width:750,
            title:'新增收货地址',
            ok:function(){ 
                var val = saveAddress();
                 return val;
            },
            cancel:function(){},
            okVal:'提交',
            content:document.getElementById('address-fields'),
            lock:true
        });
        $('#address-fields .error-msg').hide();
        $('#address-fields input.text').val('');
        $('#provinceDiv').val('0');
        $('#cityDiv').html('<option value="0">请选择</option>');
        $('#countyDiv').html('<option value="0">请选择</option>');
    });

   var regMobile = /([\d]{11})|(^0[\d]{2,3}-?[\d]{7,8}$)/;
   var regTel = /^((0\d{2,3})-)(\d{7,8})$/;
   var regEmail = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;

   function saveAddress(id){
        var consigneeName = $('#consigneeName').val(),
            provinceid = getOption($('#provinceDiv'), 0),
            province = getOption($('#provinceDiv'), 1),
            cityid = getOption($('#cityDiv'), 0),
            city = getOption($('#cityDiv'), 1),
            countyid = getOption($('#countyDiv'), 0),
            county = getOption($('#countyDiv'), 1),
            consigneeAddress = $('#consigneeAddress').val(),
            consigneeMobile = $('#consigneeMobile').val(),
            consigneePhone = $('#consigneeTel').val(),
            consigneeEmail = $('#consigneeEmail').val(),
            consigneeAlias = $('#consigneeAlias').val(),
            valArr = [consigneeName, province +' '+ city +' '+ county, consigneeAddress, consigneeMobile, '', ''];
        if (consigneeName) {
            $('#consigneeNameNote').hide();
        } else {
            $('#consigneeNameNote').show();
            return false;
        }
        if (consigneeAddress) {
            $('#consigneeAddressNote').hide();
        } else {
            $('#consigneeAddressNote').show();
            return false;
        }
       
        if (!isSelectAddr($('#provinceDiv'), $('#cityDiv'), $('#countyDiv')))
        {
            $('#areaNote').show();
            return false;
        }
        else {
            $('#areaNote').hide();
        }

        if (!regMobile.test(consigneeMobile)) {
            $('#consigneeMobileNote').show();
            return false;
        } else {
			$('#consigneeMobileNote').hide();
        }

        if (!regTel.test(consigneePhone) && consigneePhone != '') {
            $('#consigneeTelNote').show();
            return false;
        } else {
			$('#consigneeTelNote').hide();
        }

        if (!regEmail.test(consigneeEmail) && consigneeEmail != '') {
            $('#consigneeEmailNote').show();
            return false;
        } else {
            $('#consigneeEmailNote').hide();
        }

        // ----------------------  添加收货地址
        var _url = '/userinfo/insertAddress.do?t='+Math.random();
        var _data = { "regionId":countyid,"areaId":countyid,"cityId":cityid,"provinceId":provinceid, "address": consigneeAddress,"telPhone":consigneePhone ? consigneePhone : "", "email":consigneeEmail ? consigneeEmail : "" ,phone: consigneeMobile, shipTo: consigneeName,"userId":1 };
        if(id != undefined){
            _url = "/userinfo/updateAddress.do?t="+Math.random();
            _data.id = id;
        }
        $.ajax({
            type: 'post',
            url: _url,
            dataType: 'json',
            data: _data,
            success: function (d) {
                if (d.code=="000000") {
                	query();
                }else if(d.code=="020021")
                {
                	 location.href = "../../pages/redirect-login.html";
                }else 
                {
                    $.dialog.errorTips('保存失败，' + d.msg);
                };
            },error: function(){
            	$.dialog.errorTips("error！");
			}
        });

        var data = {};
        var arr = [];
        $('#consigneeName').val('');
        $('#consigneeAddress').val('');
        $('#consigneeMobile').val('');
        //$('#areaName').html('<span></span>&nbsp;<span></span>&nbsp;<span></span>');
        $('#id_add_order').attr('data', '');
        $('#order_edit').hide();
        $('.thickdiv').hide();
        provinceDiv.children().each(function (i, e) {
            if (i == 0) { e.selected = true; }
        });
        cityDiv.children().each(function (i, e) {
            if (i == 0) { e.selected = true; }
        });
        countyDiv.children().each(function (i, e) {
            if (i == 0) { e.selected = true; }
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
        $('.add-btn').each(function (i, e) {

            $(e).bind('click', function () {
                if (checkNumber(number)) {
                    $.dialog.errorTips('最多只能创建20个收货地址！');
                    return;
                }
                var left, top, width, height;
                left = $(window).width() / 2;
                top = $(window).height() / 2;
                width = $('.thickbox').width()/2;
                height = $('.thickbox').height()/2;
                $('.thickdiv').show();
                $('#order_edit .thicktitle').text('新增收货地址');
                $('#order_edit').css({ top: '50%', left: '50%', marginLeft: '-' + width + 'px', marginTop: '-' + height + 'px', position: 'fixed' }).show();

                provinceDiv.children().each(function (i, e) {
                    if (i == 0) { e.selected = true; }
                });
                cityDiv.children().each(function (i, e) {
                    if (i == 0) { e.selected = true; }
                });
                countyDiv.children().each(function (i, e) {
                    if (i == 0) { e.selected = true; }
                });

        $("#consigneeName").focus();
            });
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
        $('#consigneeName').blur(function () {
            var str = $(this).val();
            if (str) {
                $('#consigneeNameNote').hide();
            } else {
                $('#consigneeNameNote').show();
            }
        });
        $('#consigneeAddress').blur(function () {
            var str = $(this).val();
            if (str) {
                $('#consigneeAddressNote').hide();
            } else {
                $('#consigneeAddressNote').show();
            }
        });
        $('#consigneeMobile').blur(function () {
            var str = $(this).val(),
                bool = regMobile.test(str);
            if (bool) {
                $('#consigneeMobileNote').hide();
            } else {
            	$('#consigneeMobileNote').show();
            }
        });
        $('#consigneeTel').blur(function () {
            var str = $(this).val(),
                bool = regTel.test(str);
            if (bool || str == "") {
                $('#consigneeTelNote').hide();
            } else {
            	$('#consigneeTelNote').show();
            }
        });

        $('#consigneeEmail').blur(function () {
            var str = $(this).val(),
                bool = regEmail.test(str);
            if (bool || str == "") {
                $('#consigneeEmailNote').hide();
            } else {
            	$('#consigneeEmailNote').show();
            }
        });

        //编辑地址
        $(document).on('click', '.edit', function(){
            $('#address-fields .error-msg').hide();
            var _this = $(this), _ul = _this.parents('.item').find('ul.address'), _detail = _ul.find('.detail');
            $('#consigneeName').val(_ul.find('li:eq(0) .dd').text());
            $('#consigneeAddress').val(_ul.find('li:eq(1) .dd').text());
            $('#consigneeMobile').val(_ul.find('li:eq(2) .dd').text());
            $('#consigneeTel').val(_ul.find('li:eq(3) .dd').text());
            $('#consigneeEmail').val(_ul.find('li:eq(4) .dd').text());
            var pid = _detail.attr('data-province'),
                cid = _detail.attr('data-city'),
                cuid = _detail.attr('data-area');

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

            var _id = _this.parents('.item').attr('data-id');
            $.dialog({
                width:750,
                title:'编辑收货地址',
                ok:function(){ 
                    var val = saveAddress(_id);
                     return val;
                },
                cancel:function(){},
                okVal:'提交',
                content:document.getElementById('address-fields'),
                lock:true
            });

        });

/////////////////////////////////////////////////////////////
});
function query() {
	/*******************************************************************************/
	//查询收货地址
		var parm =new Object();
		parm.userid=1;
		parm.pagesize=20;
		parm.currentpage=1;
		var html='';
		 html='<div class="mt20"><button class="add_address">新增收货地址</button>您已创建<span id="add_num">#addnum#</span>个收货地址，最多创建20个收货地址</div>';
		$.ajax({
	    	url:'/userinfo/addressList.do?t='+Math.random(),
			type:'post', //数据发送方式
			dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
			data:parm,
			success:function(data){
				if (data.code == '000000') {
					var obj = data.data;
					for(var i=0;i< obj.length;i++){
						var _email = obj[i].email!=null?obj[i].email:"";
						var _telphone= obj[i].telPhone!=null?obj[i].telPhone:"";
						html += '<div class="item mt20 clearfix" data-id="'+obj[i].id+'">';
						html += ' <div class="item_title">';
						html += ''+obj[i].shipTo+'&nbsp;&nbsp;'+obj[i].regionStr+' ';
						html += '</div>';
						html += '<ul class="address">';
						html += ' <li class="prop clearfix">';
						html += '  <div class="dt">收货人：</div>';
						html += '  <div class="dd">'+obj[i].shipTo+'</div>';
						html += ' </li>';
						html += '    <li class="prop clearfix">';
						html += '<div class="dt">收货地址：</div>';
						html += '<div class="dd detail" data-province="'+obj[i].provinceId+'"  data-city="'+obj[i].cityId+'"  data-area="'+obj[i].areaId+'">'+obj[i].address+'</div>';
						html += '</li>';
						html += '<li class="prop clearfix">';
						html += '<div class="dt">手机号码：</div>';
						html += '<div class="dd">'+obj[i].phone+'</div>';
						html += '</li>';
						html += '    <li class="prop clearfix">';
						html += '<div class="dt">固定电话：</div>';
						html += '<div class="dd">'+_telphone+'</div>';
						html += '</li>';
						html += '<li class="prop clearfix">';
						html += ' <div class="dt">电子邮箱：</div>';
						html += '   <div class="dd">'+_email+'</div>';
						html += '</li>';
						html += '</ul>';
						html += '<a href="javascript:;" data-id="'+obj[i].id+'" class="close"></a>';
						html += '<div class="item_tools">';
						if(obj[i].isDefault==1){
							html +='<a>默认</a>';
						}
						if(obj[i].isDefault!=1){
							html +='<a href="javascript:;"  data-id="'+obj[i].id+'" class="default">设为默认地址</a>';
						}
						html += '<a href="javascript:;"  data-id="'+obj[i].id+'" class="edit">编辑</a>';
						html += '</div>';
						html += '</div>';
					}
				} else if(data.code=="020021")
                {
                     location.href = "../../pages/redirect-login.html";
                }
				var htl = html.replace("#addnum#", ""+data.totalNum);
				$('.content .repeater').empty().append(htl);
			},error: function(){
				$.dialog.errorTips("error！");
				$('.content .repeater').append(html);
			}
	    });
		
		$(document).on('click', 'a.default', function(){
			var _this = $(this), _id = _this.attr('data-id');
			$.ajax({
		    	url:'/userinfo/addressIsDefault.do?t='+Math.random(),
				type:'post', //数据发送方式
				dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
				data:{"id":_id},
				success:function(data){
					if(data.code=="000000"){
						$.dialog.succeedTips("设置成功！");
						query();
					}else{
						$.dialog.errorTips("设置失败！");
					}
				},error: function(){
					$.dialog.errorTips("error！");
				}
			});
		});
	/*******************************************************************************/
}
