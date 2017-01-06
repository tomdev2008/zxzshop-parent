var purchaseList = [];
var publQuoteList = [];
var quoteStatisticList = [];
var companyCa = false;
var personCa = false;
$(function(){
    //点击切换不同次数报价
    $(document).on('click', '.bj_btns button', function(){
        var _this = $(this);
        !_this.is('.active') && _this.addClass('active').siblings('button').removeClass('active');
    });

    $(document).on('click', '.send-order', function(){
    	var _this = $(this);
    	var _quoteNo = _this.attr('data-quote');
    	var _purchaseNo = _this.attr('data-purchase');
    	
        $.dialog({
            width:400,
            title:'确认下单',
            content:document.getElementById('send-order'),
            lock:true,
            ok:function(){
            	var role = $("input[name=sendorder]:checked").val();
            	if(role == null || role == ''){
            		$.dialog.errorTips('必须要选择一个角色！！');
            		return false;
            	}
            	if(role == 1){//个人签约
            		if(!personCa){
            			$.dialog.errorTips('请先进行个人实名认证！！');
                		return false;
            		}
            	}else if(role == 2){//企业签约
            		if(!personCa){
            			$.dialog.errorTips('请先进行个人实名认证！！');
                		return false;
            		}
            		if(!companyCa){
            			$.dialog.errorTips('请先进行企业CA认证！！');
                		return false;
            		}
            		
            	}
            	$.cookie("PurchaseSendOrderRole",role);
            	sendOrder(_quoteNo, _purchaseNo);
            },
            cancel:function(){}
        });
    });
    query();
    publicAjaxRequest("/userinfo/getCAStatus.do?userId="+$.cookie("wm_user_id"), null, showCAInfo);
});

function showCAInfo(data){
	if(data.code == '000000'){
		var list = data.obj;
		if(list != null){
			for(var idx=0;idx < list.length;idx++){
				if(list[idx].categery == 0){//个人CA数据
					if(list[idx].certifStatus == 2){//已认证
						$("#PersonalStatus").hide();
						personCa = true;
					}else{
						$("#PersonalStatus").show();
					}
				}else if(list[idx].categery == 1){//企业CA数据
					$.cookie("BuyerPurchaseCompanyId",list[idx].id);
					$("#CAEnterpriseName").text(list[idx].companyName);
					if(list[idx].certifStatus == 2){//已认证
						$("#EnterpriseStatus").hide();		
						companyCa = true;
					}else{
						$("#EnterpriseCa").attr("href","AccountCa.html?id="+list[idx].id);
						$("#EnterpriseStatus").show();
					}
				}
			}
		}
		if(personCa && companyCa){
			$("#CACertify").hide();
		}else{
			$("#CACertify").show();
		}
		publicAjaxRequest("/userinfo/getUserInfo.do?Id="+$.cookie("wm_user_id"), null, function(data){
			if(data.code=='000000'){
				$("#CAPersonalName").text(data.obj.realName);
			}
		});
	}
}
function sendOrder(quoteNo,purchaseNo){
	$.cookie("GetQuoteDetailQuoteNo",quoteNo);
	$.cookie("GetQuoteDetailPurchaseNo",purchaseNo);
//	window.location.href="Confirm-Order.html";
	//在新窗口中打开
	var vra=document.createElement('a'); 
    vra.target='_blank'; 
    vra.href="Confirm-Order.html"; 
    document.body.appendChild(vra); 
    vra.click();
}
function getDetail(data,dealNo){
	var titles = '采购编号：'+purchaseList[data].purchaseNo+' 询价截止日期：'
	+purchaseList[data].deadTime+'（'+purchaseList[data].statusStr+'）';
	if(purchaseList[data].quotes != null 
			&& purchaseList[data].quotes.length > 0){
		var invoice = "发票类型：";
		if(purchaseList[data].invoiceType == 1){
			invoice += "增值税普通发票";
		}else{
			invoice += "增值税专用发票";
		}
		$("#Invoice").text(invoice);
		var receiving = "收货方式：";
		if(purchaseList[data].expressWay == 1){
			receiving += "自取";
		}else{
			receiving += "供应方配送";
		}
		$("#Receiving").text(receiving);
		if(purchaseList[data].address == null){
			purchaseList[data].address = '无';
		}
		$("#Address").text("收货地址："+purchaseList[data].address);
		$("#Remarks").text("备注："+purchaseList[data].remark);
		var content = "<span>报价编号："+purchaseList[data].quotes[0].quoteNo
			+"</span><span>报价时间："+purchaseList[data].publishTime;
		content+= "</span><span>供应商：";
		if(purchaseList[data].quotes[0].companyName == null){
			content += "无</span>";
		}else{
			content += purchaseList[data].quotes[0].companyName + "</span>";
		}
		content += "<span>运费价格：<b>"+purchaseList[data].quotes[0].expressFee+"</b>元</span>";
		
		$("#TotalContent").html(content);
		var productsUrl = "/dealQuote/getDealQuoteStatisitc.do?dealNo="+dealNo;
		publicAjaxRequest(productsUrl,null,showQuoteList);
		$.dialog({
			width:1000,
			title:titles,
			content:document.getElementById('view_content'),
			lock:true
		});
	}else{
		$.dialog({
			width:1000,
			title:titles,
			content:"暂时没有报价数据",
			lock:true,
		});
	}
}

function showQuoteDetail(idx){
	if(idx < 0 || idx >= quoteStatisticList.length){
		return false;
	}
	if(quoteStatisticList[idx] == null){
		return false;
	}
	var quotesList = quoteStatisticList[idx].quoteList;
	var content = "<span>报价编号："+quoteStatisticList[idx].quoteNo
	+"</span><span>报价时间："+quoteStatisticList[idx].quoteTime;
	content+= "</span><span>供应商："+quoteStatisticList[idx].companyName+"</span>";
	content += "<span>运费价格：<b>"+quoteStatisticList[idx].expressFee+"</b>元</span>";
	$("#TotalContent").html(content);
	var listCtx = "";
	if(quotesList != null && quotesList.length > 0){
		for(var id = 0;id < quotesList.length;id++){
			if(quotesList[id] != null){
				listCtx += "<tr>";
				listCtx += "<td>"+quotesList[id].productName+"</td>";
				listCtx += "<td>"+quoteStatisticList[idx].brandNames+"</td>";
				listCtx += "<td>"+quotesList[id].sku+"</td>";
				listCtx += "<td>"+quotesList[id].model+"</td>";
				listCtx += "<td>"+quotesList[id].quantity+"</td>";
				listCtx += "<td>"+quotesList[id].unit+"</td>";
				listCtx += "<td>"+quotesList[id].price+"</td>";
				listCtx += "</tr>";
			}
	}
	}else{
		listCtx = "<tr><td>暂无报价数据</td></tr>";
	}
	$("#TableContext").html(listCtx);
}
function showQuoteList(data){
	if(data.code != '000000' ||data.obj == null || data.obj.length == 0){
		var content = "<span>暂无报价数据</span>";
		$("#TotalContent").html(content);
		quoteStatisticList = [];
		$(".bj_btns").html("");
		$("#TableContext").html("");
		return false;
	}
	quoteStatisticList = data.obj;
	var btnCtx = "";
	var label = 1;
	for(var bts=0;bts<data.obj.length;bts++){
		if(data.obj[bts] != null && data.obj[bts].status < 5){
			continue;
		}
		var _thisbtn = "<button id=\"btn-"+label+"\" ";
		_thisbtn += "onclick=\"showQuoteDetail("+bts+")\">第"+label+"次报价<i></i></button>";
		label++;
		_thisbtn += btnCtx;
		btnCtx = _thisbtn;
	}
	$(".bj_btns").html(btnCtx);
	var quoteStatistic = data.obj[data.obj.length-1];
	
	var content = "<span>报价编号："+quoteStatistic.quoteNo
	+"</span><span>报价时间："+quoteStatistic.quoteTime;
	content+= "</span><span>供应商："+quoteStatistic.companyName+"</span>";
	content += "<span>运费价格：<b>"+quoteStatistic.expressFee+"</b>元</span>";
	$("#TotalContent").html(content);
	var activeBtnId = label - 1;
	$("#btn-"+activeBtnId).addClass("active");
	
	var quoteList = quoteStatistic.quoteList;
	var listCtx = "";				
	if(quoteList != null && quoteList.length > 0){
		for(var idx = 0;idx < quoteList.length;idx++){
			if(quoteList[idx] != null){
				listCtx += "<tr>";
				listCtx += "<td>"+quoteList[idx].productName+"</td>";
				listCtx += "<td>"+quoteStatistic.brandNames+"</td>";
				listCtx += "<td>"+quoteList[idx].sku+"</td>";
				listCtx += "<td>"+quoteList[idx].model+"</td>";
				listCtx += "<td>"+quoteList[idx].quantity+"</td>";
				listCtx += "<td>"+quoteList[idx].unit+"</td>";
				listCtx += "<td>"+quoteList[idx].price+"</td>";
				listCtx += "</tr>";
			}
		}
	}else{
		listCtx = "<tr><td>暂无报价数据</td></tr>";
	}		
	$("#TableContext").html(listCtx);
}
function purchaseDetail(purchaseNo){
	$.cookie("GetPurchaseDetailPurchaseNo",purchaseNo);
	window.location.href="PurchaseDetail.html";
}
function query() {
    $("#list").hiMallDatagrid({
        url: '/purchase/getPurchaseList.do',
        nowrap: false,
        rownumbers: true,
        NoDataMsg: '没有找到符合条件的数据',
        border: false,
        fit: true,
        fitColumns: true,
        pagination: true,
        idField: "Id",
        pageSize: 2,
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
//        queryParams: {userId:0,companyId:0,buyerSeller:1},
        queryParams: {userId:$.cookie("wm_user_id"),companyId:0,buyerSeller:1},
        columns:[[
            { field: "operation", operation: true, title: "采购数据列表", align:"left",
                formatter: function (value, row, index) {
                	purchaseList[index] = row;
                    var _html = '<div class="heading">\
                                <ul>\
                                    <li>采购编号：'+row.purchaseNo+'</li>\
                                    <li>工程名称：'+row.name+'</li>\
                                    <li>提交日期：'+row.publishTime+'</li>\
                                    <li>询价截止日期：'+row.deadTime+'</li>\
                                </ul>\
                                <a href="javascript:purchaseDetail(\''+row.purchaseNo+'\')" class="btn">查看采购单</a>\
                            </div>';
                    var quotes = row.quotes;
                    var size = 0;
                    var _bodyContent = '';
                    if(quotes != null && quotes.length > 0){
                    	_bodyContent += '<table class="table inner">\
                                <thead>\
                                    <th>报价编号</th>\
                                    <th>供应商</th>\
                                    <th>品牌</th>\
                                    <th>材料种类</th>\
                                    <th>材料总价(元)</th>\
                                    <th>运费金额(元)</th>\
                                    <th>报价次数</th>\
                                    <th>最后报价时间</th>\
                                    <th>状态</th>\
                                    <th>操作</th>\
                                </thead>\
                                <tbody>';
                    	for(var idx=0;idx < quotes.length;idx++){
                    		if(quotes[idx].status < 5 ){
                    			continue;
                    		}
                    		if(quotes[idx].status == 5 ){
                    			quotes[idx].statusStr = '已报价';
                    		}
                    		_bodyContent += '<tr>\
                    			<td>'+quotes[idx].quoteNo+'</td>\
                    			<td>'+quotes[idx].companyName+'</td>\
                    			<td>'+quotes[idx].brandNames+'</td>\
                    			<td>'+quotes[idx].kinds+'</td>\
                    			<td>'+quotes[idx].totalCost+'</td>\
                    			<td>'+quotes[idx].expressFee+'</td>\
                    			<td>'+quotes[idx].times+'</td>\
                    			<td>'+quotes[idx].quoteTime+'</td>\
                    			<td>'+quotes[idx].statusStr+'</td>\
                    			<td>';
                    		_bodyContent += '<a href="javascript:getDetail('+index+',\''+row.purchaseNo+'\')" class="view">查看报价</a>';
                    		if(quotes[idx].status == 5|| (quotes[idx].status == 10 && quotes[idx].protocolStatus == 0)){
                    			_bodyContent +='<a href="javascript:" data-quote="'+quotes[idx].quoteNo+'" data-purchase="'+row.purchaseNo+'" class="send-order">&nbsp;确认下单</a>';
                    		}
                    		_bodyContent +='</td></tr>';
                    		size += 1;
                    	}
                    	_bodyContent += '</tbody></table>';
                    }
                    if(size == 0){
                    	_bodyContent ='';
                    }
                    _html += _bodyContent;
                    return _html;
                }
            }
        ]]
    });
}

