$(document).on('change', '#basic', function(){
	$("#brandId").val($(this).val());
});
//更新
function updateProduct(){
	var _imgs = [];
	$('#authCerti .uploadpic').each(function(){
		_imgs.push($(this).find('input[name=picture]').val());
	});
	$("#picList").val(_imgs);
	$("#product-detail").val(_ueditor.getContent());
	var reg = new RegExp("^[0-9]*\.{0,1}[0-9]{1,2}$");
	var regNum = new RegExp("^[1-9][0-9]*$");
	$("#product-detail").val(_ueditor.getContent());
	//校验商品名称
	var name = $.trim($("#name").val());
	if(name==""){
		$("#name_span").text("商品名称不能为空!");
		return;
	}else{
		$("#name_span").text("");
	}
	//校验计量单位
	var unit = $.trim($("#unit").val());
	if(unit==""){
		$("#unit_span").text("计量单位不能为空!");
		return;
	}else{
		$("#unit_span").text("");
	}
	//校验市场价格
	var marketPrice = $.trim($("#marketPrice").val());
	if(marketPrice==""){
		$("#marketPrice_span").text("市场参考价不能为空!");
		return;
	}else{
		$("#marketPrice_span").text("");
		if(marketPrice<0){
			$("#marketPrice_span").text("市场参考价不能为负数!");
		    return;
		}else{
			$("#marketPrice_span").text("");
			if(marketPrice.indexOf("-") >= 0 ){
	            $("#marketPrice_span").text("市场参考价不能输入负号");
				return;
	        }else{
				if(!reg.test(marketPrice)){
	        		$("#marketPrice_span").text("市场参考价不符合规则!小数点后最多2位例：11.11");
					return;
				}
			}
			
		}
	}
	//校验起批数量
	var startMass = $.trim($("#startMass").val());
	if(startMass==""){
		$("#startMass_span").text("起批数量不能为空!");
		return;
	}else{
		$("#startMass_span").text("");
		if(!regNum.test(startMass)){
			$("#startMass_span").text("起批数量必须为正整数!");
			return;
		}
	}
	//校验关键字
	var keyword = $.trim($("#keyword").val());
	if(keyword==""){
		$("#keyword_span").text("关键字不能为空!");
		return;
	}else{
		$("#keyword_span").text("");
	}
	//校验是否变更分类
	var secondCateId = $("#secondCateId").val();
	if(secondCateId!=""){
		$("#categoryId").val(secondCateId);
	}
	Product_Update_Form.submit();
}

function queryEnterpriseCategory(){
	var enterpriseId = $("#enterpriseId").val();
	var thirdCateId = $("#thirdCateId").val();
	if(thirdCateId!=""){
		 $.ajax({
			 type:"POST",
				url:'../ProductCategories/api/queryEnterpriseCategory.do',
				data:"enterpriseId=" + enterpriseId+"&categoryId="+thirdCateId,
				success:function(data){
					if( data!="true"){
						$("#cate_span").text("该企业没有此经营类目!!");
					}else{
						$("#cate_span").text("");
					}
			    }
		 });
	}else{
		$("#cate_span").text("");
	}

}