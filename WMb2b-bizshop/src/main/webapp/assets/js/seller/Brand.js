var ApplyMode=2;
$(function(){
   //查询品牌列表
	query();
	
	$(document).on('click', '#button1', function(){
		$(this).addClass('active').siblings().removeClass('active');
		$(this).index() == 1 ? $('.new_switch').show() && $('.have_switch').hide() :$('.new_switch').hide() && $('.have_switch').show();
		ApplyMode=1;
	});
	$(document).on('click', '#button2', function(){
		$(this).addClass('active').siblings().removeClass('active');
		$(this).index() == 1 ? $('.new_switch').show() && $('.have_switch').hide() :$('.new_switch').hide() && $('.have_switch').show();
		ApplyMode=2;
	});
	$('#brandsubmit').click(function() {
		insertapply(ApplyMode);
	});
});

////新增品牌申请
//function insertapply(ApplyMode) {
//	var parm =new Object();
//	parm.applyMode=ApplyMode;//品牌申请类型 (平台已有1 新增2)
//	parm.brandName=$("#brandName").val();
//	parm.logo=$("#logo").val();
//	parm.categoryIds=$("#categoryIds").val();
//	parm.authCertificate=$("#authCertificate").val();
//	parm.remark=$("#remark").val();
//	parm.userId=1;
//	$.ajax({
//		url:'/brands/insertBrandsapply.do?t='+Math.random(),
//		type:'post', //数据发送方式
//		dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
//		data:parm,
//		success: function(msg){ //成功
//			if(msg.code=="000000"){
//				 $.dialog({
//			            title:'新增品牌',
//			            ok:function(){},
//			            cancel:function(){},
//			            okVal:'继续新增',
//			            cancelVal:'返回列表',
//			            content:'恭喜你，品牌提交成功 等待审核'
//			        });
//			}
//		},error: function(){ //失败
//			//TODO:返回异常数据
//			$.dialog.errorTips("请求失败！");
//		}
//	});
//}

function query() {
    $("#list").hiMallDatagrid({
        url: '/brands/queryBrandsapplylist.do?t='+Math.random(),
        nowrap: false,
        rownumbers: true,
        NoDataMsg: '没有找到符合条件的数据',
        border: false,
        fit: true,
        fitColumns: true,
        pagination: true,
        idField: "Id",
        pageSize: 10,
        pagePosition: 'bottom',
        pageNumber: 1,
        pageNumberName:"currentPage",//当前页
        pageSizeName: "pageSize",//每页条数
        backDataName:"data",//数据
        totalName:"totalNum",//总数
        queryParams: { userId:1,pageSize:10,currentPage: 1},
        columns:
        [[
        {
            field: "brandName", operation: false, title: "品牌名称", align: "center",width: 50,
            
        },
        { field: "logo", title: "LOGO", width: 80, align: "center",
        	formatter: function (value, row, index) {
                var _src = row.logo.toString();
                return '<img src='+_src+' height=40 />';
            }
        },
        { field: "auditStatus", title: "状态", width: 100, align: "center"},
        {
            field: "operation", operation: true, title: "操作", width: 150,
            formatter: function (value, row, index) {
                var id = row.id.toString();
                var html = ["<span class=\"btn-a\" > "];
                html.push("<a href='./BrandView.html?id=" + id + "'>查看</a>");
                html.push("</span>");
                return html.join("");
            }
        }
        ]]
    });
}