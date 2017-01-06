$(function() {
	var delayTime = 120;
	var verify = $(".divinfo button.getverify");
	publicAjaxRequest(
			"/userinfo/getEnterpriseInfo.do?categery=1&userid="
					+ $.cookie("wm_user_id"), null, function(data) {
				if (data.code == '000000') {
					$("#companyName").val(data.obj.companyName);
				}
			});
	var countDown = function() {
		delayTime--;
		verify.attr("disabled", "disabled");
		verify.text(delayTime + '秒后重新获取');
		if (delayTime === 1) {
			delayTime = 120;
			verify.text("");
			verify.text("获取验证码");
			verify.css("color", "#111112");
			verify.removeAttr("disabled");
		} else {
			setTimeout(countDown, 1000);
			verify.css("color", "#aaa");
		}

	};

	function sendCode() {
		if ($(".divinfo button.getverify").attr("disabled")) {
			return;
		}
		var destination = $("#contactsPhone").val();
		var _url = "SendValideteCode";
		jQuery.support.cors = true;
		jQuery
				.ajax({
					type : "post",
					data : {
						phone : destination,
						module : "标准下单"
					},
					url : _url,
					success : function(result) {
						if (result.success === true) {
							$(".divinfo button.getverify").attr("disabled",
									"disabled");
							$(".divinfo button.getverify").html("120秒后重新获取");
							setTimeout(countDown, 1000);
						} else {
							alert(result.msg);
						}
					}
				});
	}

	$(".divinfo button.getverify").click(function() {
		debugger;
		var mobile = $("#contactsPhone").val();
		if (!checkMobile($("#contactsPhone").val())) {
			$.dialog.tips("请正确填写手机号码");
			return;
		}
		sendCode();
	});

	$("#yanzhengma").blur(function() {
		var checkCode = parseInt($.trim($(this).val()));
		if (!checkCode) {
			var pluginId = "Himall.Plugin.Message.SMS";
			var destination = $("#contactsPhone").val();
			$.ajax({
				type : "post",
				url : "../../../Home/StandardSheetCheckCode",
				data : {
					pluginId : pluginId,
					code : checkCode,
					destination : destination
				},
				dataType : "json",
				async : false,
				success : function(data) {
					if (!data)
						return;
					if (data.success === true) {

					} else {
						$.dialog.tips(data.msg);
					}
				}
			});
		}
	});
});

$(function() {
	$("#up_file").on("change", function() {
		changeUpfile($(this));
	});
});

function DeleteUpfile(e) {
	$("input[name='upfile']").val("");
	$("#up_file").val("").parents("p").show();
	$("span.file_name").text("");
	$("a[name='deletefile']").hide();
	$("img[name='imgfile']").attr("style", "display:none;");

}
function changeUpfile(e) {
	var $this = e;
	var $form = $this.parents("form");
	var file = e.val();
	var fileType = file.substring(file.lastIndexOf(".") + 1);
	var str1 = fileType.toLowerCase();
	var fileName = file.substring(file.lastIndexOf("\\") + 1);
	var re = new RegExp(/\.(gif|jpg|jpeg|png|bmp|doc|docx|xls|xlsx|pdf)$/);
	if ("gif|jpg|jpeg|png|bmp|doc|docx|xls|xlsx|pdf".indexOf(str1) == -1) {
		$.dialog.tips("不支持此文件类型!");
		return false;
	}
	$form.attr({
		action : "/purchase/uploadFile.do?type=2",
		method : "post",
		enctype : "multipart/form-data",

	}).ajaxSubmit(
			{
				success : function(data) {
					var data = JSON.parse(data);
					if (data.code == "000000") {
						var dataPath = data.obj[0];
						var dataType = dataPath.substring(dataPath
								.lastIndexOf(".") + 1);
						var dataTypename = dataType.toLowerCase();
						$form.find("input[name='upfile']").val(dataPath);
						$form.find("span.file_name").text(fileName);
						$form.find("a[name='deletefile']").show();

						if ("gif|jpg|jpeg|png|bmp".indexOf(dataType
								.toLowerCase()) != -1) {
							$form.find("img[name='imgfile']").attr("src",
									"../../assets/img/jpg_.jpg");
						}
						if ("doc|docx".indexOf(dataType.toLowerCase()) != -1) {
							$form.find("img[name='imgfile']").attr("src",
									"../../assets/img/word_.jpg");
						}
						if ("xls|xlsx".indexOf(dataType.toLowerCase()) != -1) {
							$form.find("img[name='imgfile']").attr("src",
									"../../assets/img/xls_.jpg");
						}
						if ("pdf".indexOf(dataType.toLowerCase()) != -1) {
							$form.find("img[name='imgfile']").attr("src",
									"../../assets/img/pdf_.jpg");
						}
						$form.find("img[name='imgfile']").attr("style",
								"display:block;");
						$this.parents("p").hide();
					} else {
						$.dialog.tips(data.value);
					}

				}

			});
}

var n = $(".divinfo p[name='add']").length;
if (n < 10) {
	var html = "";
	html += '<p class="up_file" name="add"> <span style="padding-right:100px">';
	html += '<img style="display:none" name="imgfile" src=""><span class="file_name" name="file_name">11111</span></span>';
	html += '<a class="del" name="deletefile" style="display:none;">删除</a><input type="hidden" name="upfile" value="" />';
	$(this).parents(".divinfo").append(html);

}

function displayAddress() {
	var expressway = $('input[name=receiving]:checked').val();
	if (expressway == 1) {
		$("#ReceivingAddress").hide();
	} else {
		$("#ReceivingAddress").show();
	}
}

// /----获取物流配送地址 ----------------
function getPostAddress() {
	var _this = $("#shipping-address");
	var userId = 1;
	$.ajax({
		type : "POST",
		url : "/userinfo/addressList.do?userid=" + userId,
		dataType : "json",
		success : function(data) {
			if (data.code == '000000') {
				var addressListStr = "";
				var addressList = data.data;
				for ( var idx = 0; idx < addressList.length; idx++) {
					addressListStr += "<li data-id=\"" + addressList[idx].id
							+ "\" data-address=\"" + addressList[idx].regionStr
							+ addressList[idx].address + "\">";
					addressListStr += addressList[idx].regionStr
							+ addressList[idx].address + "</li>";
				}
				$("#postAddressList").html(addressListStr);
			}
			_this.siblings('.address-list').show();
		},
		error : function(data) {
			$.dialog.errorTips(data.Data);
			return;
		}

	});
};

// 选择收货地址
$('#shipping-address').on('click', function(e) {
	e.stopPropagation();
	getPostAddress();
	var _this = $(this);
	_this.siblings('.address-list').show();
});
$(document).on('click', '.address-list li', function(e) {
	e.stopPropagation();
	var _this = $(this);
	$('#shipping-address').val(_this.text());
	$('#shipping-address').attr('data-id', _this.attr('data-id'));
	$('#shipping-address').attr('data-address', _this.attr('data-address'));
	_this.parents('.address-list').hide();
});
$('body').on('click', function() {
	$('.address-list').hide();
});

function getInquiryShootInfo() {
	var _deadlineChecked = $('input[name=inquiry-deadline]:checked');
	var endDateStr = null;
	if (_deadlineChecked.length != 0) {
		var nowDate = new Date();
		nowDate.setDate(new Date().getDate() + (_deadlineChecked.val() / 24));
		
		endDateStr = nowDate.getFullYear() + "-" + (nowDate.getMonth() + 1)
				+ "-" + nowDate.getDate() + " 23:59:59";
	} else {
		endDateStr = $("#endDate").val() + " 23:59:59";
	}

	var inquiry = {
		name : $("#inquirySheetTitle").val(),
		deadTimeStr : endDateStr,
		invoiceType : $("input[name='invoice']:checked").val(),
		expressWay : $('input[name=receiving]:checked').val(),
		contactName : $("#contactsName").val(),
		contactMobile : $("#contactsPhone").val(),
		userId : $.cookie("wm_user_id"),
		companyId : $.cookie("EnterpriseId"),
		companyName : $("#companyName").val(),
		regionId : $('#shipping-address').attr('data-id'),
		address : $("#shipping-address").attr('data-address'),
		remark : $('#inquiry-remark').val(),
		filePath : [],
		products : []
	};

	var products = [];
	$.each($("tr[name='inquirySheet']"), function(index, item) {
		var pn = $('td', item).eq(1).find('input').val();
		if (pn != null && pn != '') {
			products.push({
				productName : $('td', item).eq(1).find('input').val(),
				brandNames : $('td', item).eq(2).find('input').val(),
				sku : $('td', item).eq(3).find('input').val(),
				model : $('td', item).eq(4).find('input').val(),
				quantity : $('td', item).eq(5).find('input').val(),
				unit : $('td', item).eq(6).find('input').val()
			});
		}
	});
	inquiry.filePath.push($("input[name='upfile']").val());
	inquiry.products = products;
	return inquiry;
}

function isPhoneNo(phone) {
	var pattern = /^1[34578]\d{9}$/;
	return pattern.test(phone);
}
function saveInquirySheet() {
	if (Verification()) {
		var InquirySheet = getInquiryShootInfo();
		if (InquirySheet == null)
			return;
		var InquirySheet = JSON.stringify(InquirySheet);
		$.ajax({
			type : "post",
			url : '/purchase/publishPurchase.do',
			dataType : "json",
			contentType : "application/json",
			data : InquirySheet,
			async : false,
			success : function(data) {
				if (data.code == '000000') {
					$.dialog.succeedTips('发布成功',function() {
						window.location.href = '/pages/buyer/Purchase.html?nav=Purchase';
					});
				} else {
					$.dialog.errorTips('发布失败' + data.value);
				}
			}
		});
	}
}

function Verification() {
	if ($("#inquirySheetTitle").val() == '') {
		$.dialog.tips("请填写工程名称");
		return false;
	}

	var Products = $("tbody").find("tr[name='inquirySheet']");
	var excount = 0;
	var c = true;
	// debugger;
	$.each(Products, function(index, item) {
		var yes = true;
		var xcount = 0, ycount = 0, tdcount = 4;
		$(item).find("td:eq(1),td:eq(2),td:eq(5)").each(function() {
			if (!c)
				return;
			var span = $(this).find("span.textlist").html();
			var input = $(this).find("input:text").val();
			var isval = false;
			if (span == null || span == undefined) {// 读input
				if (input != "") {
					isval = true;
				}
			} else {// 读span
				if (span != "") {
					isval = true;
				}
			}
			if (isval) {// 全部都不能为空
				yes = false;
				xcount++;
				excount++;
			} else {
				if (!yes) {
					c = false;// 此条信息已有填写，每条必填
					excount = excount > 0 ? excount-- : excount;
					return;
				} else {
					ycount++;
				}
			}
		});
		if (ycount == tdcount) {// 全部为空--跳过检测
			c = true;
		}
		if (excount == 0) {
			c = false;
			return;
		}
	});

	if ($("#contactsPhone").val() == '') {
		$.dialog.tips("请填写联系人手机号码");
		return false;
	}

	if (!checkMobile($("#contactsPhone").val())) {
		$.dialog.tips("请正确填写联系人手机号码");
		return false;
	}

	if ($("#contactsName").val() == '') {
		$.dialog.tips("请填写联系人姓名");
		return false;
	}

	if ($("#endDate").val() == ''
			&& $('input[name=inquiry-deadline]:checked').length == 0) {
		$.dialog.tips('请选择询价截止时间');
		return;
	} else
		$('#inquirySheetEndDateNote').hide();

	if ($("input[name='receiptMode']:checked").val() == 0) {
		if (!isSelectAddr($('#consignee_province'), $('#consignee_city'),
				$('#consignee_county'))) {
			$("#inquirySheetAddrNote").show();
			return false;
		} else
			$("#inquirySheetAddrNote").hide();
	}

	if($("#receive-2").hasClass("checked")){
		if($("#shipping-address").attr("data-address")==undefined ||$("#shipping-address").attr("data-address")=="") {
			$.dialog.tips("请填写收货地址");
			return false;
		}
	}

	if ($("#password").val() == '') {
		$.dialog.tips("请填写密码");
		return false;
	}
	return true;
}

function checkMobile(str) {
	var re = /^1\d{10}$/;
	return re.test(str);
}
function addli(obj) {
	var text = $(obj).text();
	$(obj).parents("#nextmenu").siblings(".textlist").text(text);
	$(obj).parents("#nextmenu").siblings("img").hide();
	$("#nextmenu").remove();
}
$(".list-type>span").click(function() {
	$(this).next("img").show();
});

// 删除
function Delete(obj) {
	// $(".contmain .w1190c .tb td .del").click(function () {
	debugger;
	var n = $(".tb tbody tr").length;
	if (n >= 2) {
		$(obj).parents("tr").remove();
	}
	if (n == 2) {
		$(obj).parents("tr").remove();
		$(".tb tbody tr").find("span.del").hide();
	}
	$(".tb tbody tr").each(function() {
		$(this).find("td:first").text($(this).index(".tb tbody tr") + 1);

	});

	// })

}

function importData() {
	var len = $(".tb tbody tr[name='inquirySheet']").length;
	$(".tb tbody tr[name='inquirySheet']").each(function(a, b) {
		var $input = $(b).find("td:eq(1) input");
		if (a == (len - 1)) {
			var _val = $(b).find("td:eq(1) input").val();
			if (_val != "" && _val != undefined) {
				// appendInquirys($(".UploadInquirystb"), { GoodName: "",
				// CategoryName: "", BrandName: "", SizeName: "", Num: "", Unit:
				// "" }, len + 1);
			}
		}
	});
}

$('.add-inquiry').on('click', function() {
	appendInquirys($(".UploadInquirystb"), {
		GoodName : "",
		CategoryName : "",
		BrandName : "",
		SkuName : "",
		ModelName : "",
		Num : "",
		Unit : ""
	}, 1);
});

function deleteinquiry(d) {
	var len = $(".tb tbody tr[name='inquirySheet']").length;
	if (len > 1) {
		$(d).parent().parent().remove();
	} else {
		$.dialog.errorTips('最后一条不能删除');
	}
}

function appendInquirys(container, _item, index) {
	var _temp = $(".inquirystempor").children().clone();
	$(_temp).find(".inquiryName").val(_item.GoodName);
	$(_temp).find(".inquirySku").val(_item.SkuName);
	$(_temp).find(".inquiryModel").val(_item.ModelName);
	$(_temp).find(".inquiryNum").val(_item.Num);
	$(_temp).find(".inquiryUnit").val(_item.Unit);
	$(container).append(_temp);
}

$(function() {
	var imgpath = $(".fillup form");
	imgpath.find("input[type='file']").on("change", function() {
		change($(this));
	});
});
function change(e) {
	debugger;
	var $this = e;

	var $imgpath = $this.parents("form");

	var file = e.val();
	var fileType = file.substring(file.lastIndexOf(".") + 1);
	var str1 = fileType.toLowerCase();
	var re = new RegExp(
			/\.(gif|jpg|jpeg|png|bmp|doc|docx|xls|xlsx|pdf|dwg|rar|zip)$/);

	if ("gif|jpg|jpeg|png|bmp|doc|docx|xls|xlsx|pdf|dwg|rar|zip|txt"
			.indexOf(str1) == -1) {
		$.dialog.tips("文件类型不正确!");
		return false;
	}
	$imgpath.attr({
		action : "/common/PublicOperation/AddInquirySheetUploadPic",
		method : "post",
		enctype : "multipart/form-data",
	}).ajaxSubmit({

		success : function(data) {
			console.info(this);
			console.info(data);
			var dataPath = data.split('|')[0];
			console.info(dataName);
			var dataN = data.split('|')[1];
			var dataName = dataN.substring(dataN.lastIndexOf("\\") + 1);
			var dataType = dataName.substring(dataName.lastIndexOf(".") + 1);
			if (data.status == "Error") {
				$.dialog.tips(data.Error);
				return false;
			}

			$imgpath.find("input[name='imghidden']").val(dataPath);
			if ($imgpath.find("input[name='imghidden']").val() != null) {
				$imgpath.find("span").html("已选择");
			}
		}

	});
}

$("#receipt").click(function() {
	if ($("#express").attr("checked"))
		$("#addr").show();
	else
		$("#addr").hide();
});

$("#sellers").click(function() {
	if ($("#shop").attr("checked")) {
		$("#selectSeller").show();

	} else {
		$("#selectSeller").hide();
		$("#shopName").hide();
	}
});

$("#uploadImg").himallUpload({
	displayImgSrc : "",
	imgFieldName : "BrandLogo"
});
var TodayTime = new Date();
TodayYYYY = TodayTime.getFullYear();
TodayMM = TodayTime.getMonth() + 1;
TodayDD = TodayTime.getDate();
TodayTime = TodayYYYY + "-" + TodayMM + "-" + TodayDD;
var TwoWeek = new Date((new Date()).getTime() + 24 * 60 * 60 * 1000 * 14);
TwoYYYY = TwoWeek.getFullYear();
TwoMM = TwoWeek.getMonth() + 1;
TwoDD = TwoWeek.getDate();
TwoWeek = TwoYYYY + "-" + TwoMM + "-" + TwoDD;
// $(".end_datetime")
// .datetimepicker({
// language: 'zh-CN',
// format: 'yyyy-mm-dd',
// autoclose: true,
// weekStart: 1,
// minView: 2,
// todayHighlight: true,
// }).datetimepicker("setStartDate", TodayTime)
// .on("changeDate", function (ev) {
// var starttime = $("#endDate").val();
// $("#receiptDate").datetimepicker("setStartDate", starttime);
// $("#endDate").datetimepicker("hide")
// })

$(".end_datetime").datetimepicker({
	language : 'zh-CN',
	format : 'yyyy-mm-dd',
	autoclose : true,
	weekStart : 1,
	minView : 2,
	todayHighlight : true,
}).datetimepicker("setStartDate", TodayTime).datetimepicker("setEndDate",
		TwoWeek).on(
		"changeDate",
		function(ev) {
			$('input[name=inquiry-deadline]').removeAttr('checked').parent()
					.removeClass('checked');
		});

$(".receipt_datetime").datetimepicker({
	language : 'zh-CN',
	format : 'yyyy-mm-dd',
	autoclose : true,
	weekStart : 1,
	minView : 2,
	todayHighlight : true
}).datetimepicker("setStartDate", TodayTime).on("changeDate", function(ev) {
	var starttime = $("#receiptDate").val();
	$("#endDate").datetimepicker("setEndDate", starttime);
	$("#receiptDate").datetimepicker("hide");
});

function delremove(d) {
	$(d).parent().parent().remove();
}

function viewFrameDialog(title, url) {
	$
			.dialog({
				title : title,
				lock : true,
				id : 'addAddress',
				width : '1080px',
				height : '690px',
				content : [
						'<div class="dialog-form">',
						'<div class="form-group">',
						'<iframe src='
								+ url
								+ ' frameborder="0" width="100%" height="700"></iframe></div>',
						'</div>' ].join(''),
				padding : '0px'
			});
}

$('.address-list .add').on('click', function() {
	viewFrameDialog('新增收货地址', 'Address.html');
})

function verifyFigure(obj) {
	var val = obj.val();
	var reg = /^\d+(\.\d+)?$/;
	if (!reg.test(val)) {
		return false;
	} else {
		return true;
	}
}

$(document).on('blur', '.UploadInquirystb input.inquiryNum', function() {
	var _this = $(this);
	if (!verifyFigure(_this)) {
		$.dialog.tips('请输入有效的数量');
		var _val = parseFloat(_this.val());
		_val = isNaN(_val) ? '' : _val;
		_this.val(_val);
	}
});

// 模拟单选按钮
$('.divinfo label.sub:has(input[type=radio])').click(function() {
	var _this = $(this);
	_this.addClass('checked').siblings().removeClass('checked');
});

// 询价截止时间点击上面单选按钮后清空日期选择框中时间
$('label.sub input[name=inquiry-deadline]').click(function() {
	$('#endDate').val('');
});

// 点击完善信息按钮
$('.more-info .more').click(function() {
	var _this = $(this), _fields = _this.parents('.more-info').find('.fields');
	if (_this.hasClass('up')) {
		_this.removeClass('up');
		_fields.show();
	} else {
		_this.addClass('up');
		_fields.hide();
	}
});

//隐藏显示左边菜单
$(parent.document.body).addClass('no-aside');
$(window).on('unload', function(){
	$(parent.document.body).removeClass('no-aside');
})