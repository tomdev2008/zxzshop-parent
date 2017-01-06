$(function(){
    $('.number button').click(function(){
        !$(this).is('.active') && $(this).addClass('active').siblings('button').removeClass('active');
    });
    $('.price_range .price_range_btn').click(function(){
         !$(this).is('.active') && $(this).addClass('active').siblings('.price_range_btn').removeClass('active');
    });
    query();
});
function query() {
	var _companyId = $.cookie("EnterpriseId");
	var _endTime = "";
	var _beginQt = 0;
	var _endQt = 0;
    var _status = $('.price_range_btn.active').val();
    var _quantityLast = $("button.quantity_rage.active[name=QuantityLast]").val();
    var _lastTime = $("button.price_range_btn.active[name=LastTime]").val();
    if(_quantityLast == 1){
    	_beginQt = 1;
    	_endQt = 50;
    }else if(_quantityLast == 2){
    	_beginQt = 51;
    	_endQt = 100;
    }else if(_quantityLast == 3){
    	_beginQt = 101;
    	_endQt = 500;
    }else{
    	_beginQt = 501;
    }
    if(_lastTime != 0){
    	var nowDate = new Date(new Date() + (_lastTime * 24));
    	_endTime = nowDate.getFullYear() + "-" + (nowDate.getMonth() + 1)
			+ "-" + nowDate.getDay() + " 23:59:59";
    }
    $("#list").hiMallDatagrid({
        url: '/dealQuote/getQuoteHistory.do',
        nowrap: false,
        rownumbers: true,
        NoDataMsg: '没有找到符合条件的数据',
        border: false,
        fit: true,
        fitColumns: true,
        pagination: true,
        idField: "Id",
        pageSize: 4,
        pageNumberName:"page",
        pageSizeName: "size",
        backDataName:"data",
        totalName:"totalNum",
        pagePosition: 'bottom',
        pageNumber: 1,
        onLoadSuccess:function(data){
        	if(data.pageCode == '020021'){
        		window.location.href="/pages/redirect-login.html";
        	}
        },
        queryParams: {companyId:_companyId,productName: $("#ProductName").val(), 
        	symbol: _status,endTime:_endTime,beginQt:_beginQt,endQt:_endQt},
        columns:[[
            { field: "productName", title: "材料名称", width: 80, align: "center" },
            { field: "brandNames", title: "材料品牌", width: 80, align: "center" },
            { field: "sku", title: "规格", width: 80, align: "center" },
            { field: "model", title: "型号", width: 80, align: "center" },
            { field: "quantity", title: "数量", width: 80, align: "center" },
            { field: "unit", title: "单位", width: 80, align: "center"},
            { field: "price", title: "单价（元）", width: 80, align: "center"},
            { field: "companyName", title: "供应商", width: 80, align: "center"},
            { field: "quoteTime", title: "报价日期", width: 80, align: "center"}           
        ]]
    });
}