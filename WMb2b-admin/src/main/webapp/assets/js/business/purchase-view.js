var business = {};
business.purchase = {
	init : function() {
		this.uploadFile();
		this.calendarInit();
		this.bindEvent();
	},
	bindEvent : function() {
		var _this = this;
		$("#btnAuditing").click(function (){
			_this.auditPassed();
	        return false;
	    })
		//保存
		$('#btnSave').click(function(){
			_this.saveInquiry();
			return false;
		});

		//添加商品
	    $("#add-product").click(function(){
	        var _html = '<tr>\
	        			  <td><button type="button" class="remove">删除</button></td>\
	                      <td><input name="productName" type="text"></td>\
	                      <td><input name="brandNames" type="text"></td>\
	                      <td><input name="sku" type="text"></td>\
	                      <td><input name="model" type="text"></td>\
	                      <td><input name="quantity" type="text"></td>\
	                      <td><input name="unit" type="text"></td>\
	                    </tr>';
	          $(this).parent().prev().find('tbody').append(_html);
	          var _tr = $("#product-list > tbody > tr");
	          _tr.each(function(index, element){
	          	var _this = $(this);
	          	_this.find('td:eq(1) input').attr('name', 'products['+index+'].productName');
	          	_this.find('td:eq(2) input').attr('name', 'products['+index+'].brandNames');
	          	_this.find('td:eq(3) input').attr('name', 'products['+index+'].sku');
	          	_this.find('td:eq(4) input').attr('name', 'products['+index+'].model');
	          	_this.find('td:eq(5) input').attr('name', 'products['+index+'].quantity');
	          	_this.find('td:eq(6) input').attr('name', 'products['+index+'].unit');
	          });

	          return false;
	    });

	    //删除商品
	    $(document).on('click', 'button.remove', function(){
	          var _id = $(this).parents('tr').attr('data-id');
	          if(_id){
	          	$(this).parents('tr').hide();
	          	$(this).parent().append('<input name="label" class="label" value="-1" type="hidden">');
	          }else{
	          	$(this).parents('tr').remove();
	          }
	          var _tr = $("#product-list > tbody > tr");
	          _tr.each(function(index, element){
	          	var _this = $(this);
	          	_this.find('td:eq(0) input.pid').attr('name', 'products['+index+'].id');
	          	_this.find('td:eq(0) input.label').attr('name', 'products['+index+'].label');
	          	_this.find('td:eq(1) input').attr('name', 'products['+index+'].productName');
	          	_this.find('td:eq(2) input').attr('name', 'products['+index+'].brandNames');
	          	_this.find('td:eq(3) input').attr('name', 'products['+index+'].sku');
	          	_this.find('td:eq(4) input').attr('name', 'products['+index+'].model');
	          	_this.find('td:eq(5) input').attr('name', 'products['+index+'].quantity');
	          	_this.find('td:eq(6) input').attr('name', 'products['+index+'].unit');
	          });
	          return false;
	    });
	},
	uploadFile : function(e) {
		var _this = this;
	      BUI.use('bui/uploader',function (Uploader) {
	      /**
	       * 返回数据的格式
	       *
	       *  默认是 {url : 'url'},否则认为上传失败
	       *  可以通过isSuccess 更改判定成功失败的结构
	       */
	      var uploader = new Uploader.Uploader({
	        //指定使用主题
	        theme: 'imageView',
	        render: '#J_Uploader',
	        url: '$!{request.getContextPath()}/upload/uploadFile.do',
	        queue: {
	          resultTpl:{
	            'success': '<div class="success"><img src="../../../test/upload/{url}" title="{name}"/></div>',
	            'error': '<div class="error"><span class="uploader-error">{msg}</span></div>'
	          }
	        },
	        rules: {
	          //文的类型
	          ext: ['.png,.jpg','文件类型只能为{0}'],
	          //上传的最大个数
	          max: [5, '文件的最大个数不能超过{0}个'],
	          //文件大小的最小值,这个单位是kb
	          minSize: [10, '文件的大小不能小于{0}KB'],
	          //文件大小的最大值,单位也是kb
	          maxSize: [2048, '文件大小不能大于2M']
	        }
	      }).render();
	 
	      //获取上传文件的对列
	      var queue = uploader.get('queue');
	 
	      //设置文件的回显
	      queue.setItems([
	        {success: true, name: "Winter.jpg",ext:'.jpg', url:"2014_05_22/Winter.jpg"}
	      ]);
	    });
	},
	calendarInit : function() {
		BUI.use('bui/calendar',function(Calendar){
          var datepicker = new Calendar.DatePicker({
            trigger:'.calendar',
            autoRender : true
          });
        });
	},
	auditPassed : function(){
	  var sheetCode = $("#purchaseNo").val();
		var _tr = $("#product-list > tbody > tr");
		var _bool = true;
		if(_tr.length==0){
			BUI.Message.Alert("请至少添加一样商品","error");
			_bool = false;
		}else{
			_tr.each(function(index, element){
				var materialname = $(this).find('td:eq(1) input').val();
				var brandname = $(this).find('td:eq(2) input').val();
				var sku = $(this).find('td:eq(3) input').val();
				var model = $(this).find('td:eq(4) input').val();
				var quantity = $(this).find('td:eq(5) input').val();
				var reg = /^\d+(\.\d+)?$/;
				if(materialname=="" || materialname==undefined){
					BUI.Message.Alert("请输入材料名称！","warning");
					_bool = false;
					return;
				}
				if(brandname=="" || brandname==undefined){
					BUI.Message.Alert("请输入材料品牌！","warning");
					_bool = false;
					return;
				}
				if(model=="" || model==null ||model==undefined){
					BUI.Message.Alert("请输入型号！","warning");
					_bool = false;
					return;
				}
				if(sku=="" || sku==null ||sku==undefined){
					BUI.Message.Alert("请输入规格！","warning");
					_bool = false;
					return;
				}
				if(quantity=="" || parseFloat(quantity)<= 0 ){
					BUI.Message.Alert("商品数量不可小于0","warning");
					_bool = false;
					return;
				}
				if(!reg.test(quantity)){
					BUI.Message.Alert("商品数量不可输入非数字","warning");
					_bool = false;
					return;
				}
			});
		}
		if(!_bool) return;
		$('#detailForm').submit();
		
      $.ajax({
          type: 'get',
          url: "../purchaseinfo/auditingPurchase.do",
          data:{purchaseCode:sheetCode,status:3},
           dataType: "json",
          success: function(data){
              if(data.code == '000000'){//返回成功
                 location.href="../purchaseinfo/queryPurchaseQuery.do";
              }
          },
          error : function() {    
              BUI.Message.Alert('审核失败！','error'); 
          }   
        });
	},
	saveInquiry : function() {
		var _tr = $("#product-list > tbody > tr");
		var _bool = true;
		if(_tr.length==0){
			BUI.Message.Alert("请至少添加一样商品","error");
			_bool = false;
		}else{
			_tr.each(function(index, element){
				var materialname = $(this).find('td:eq(1) input').val();
				var brandname = $(this).find('td:eq(2) input').val();
				var sku = $(this).find('td:eq(3) input').val();
				var model = $(this).find('td:eq(4) input').val();
				var quantity = $(this).find('td:eq(5) input').val();
				var reg = /^\d+(\.\d+)?$/;
				if(materialname=="" || materialname==undefined){
					BUI.Message.Alert("请输入材料名称！","warning");
					_bool = false;
					return;
				}
				if(brandname=="" || brandname==undefined){
					BUI.Message.Alert("请输入材料品牌！","warning");
					_bool = false;
					return;
				}
				if(model=="" || model==null ||model==undefined){
					BUI.Message.Alert("请输入型号！","warning");
					_bool = false;
					return;
				}
				if(sku=="" || sku==null ||sku==undefined){
					BUI.Message.Alert("请输入规格！","warning");
					_bool = false;
					return;
				}
				if(quantity=="" || parseFloat(quantity)<= 0 ){
					BUI.Message.Alert("商品数量不可小于0","warning");
					_bool = false;
					return;
				}
				if(!reg.test(quantity)){
					BUI.Message.Alert("商品数量不可输入非数字","warning");
					_bool = false;
					return;
				}
			});
		}
		if(!_bool) return;
		$('#detailForm').submit();
	}
};