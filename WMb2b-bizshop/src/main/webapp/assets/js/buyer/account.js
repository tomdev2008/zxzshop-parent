
$(function(){
	query();
	queryperson();
    $(document).on('click', 'a.defaultddd', function(){
		var _this = $(this), _id = _this.attr('data-id');
		$.ajax({
	    	url:'/userinfo/enterpriseIsDefault.do?t='+Math.random(),
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
    
    
});

function queryperson(){
	//个人认证信息查询
	  $.ajax({
	    	url:'/userinfo/enterpriseinfo.do?t='+Math.random(),
			type:'post', //数据发送方式
			dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
			data:{"categery":0},
			success:function(data){
				if (data.code == '000000') {
					if(data.obj.certifStatus==2){
						$("#isrecod").text("已认证");
						$("#caText").text("查看");
					}else if(data.obj.certifStatus==1){
						if(data.obj.status==2){
							$("#isrecod").text("已审核");
							$("#caText").text("查看");
						}else{
							$("#isrecod").text("待审核");
							$("#caText").text("修改");
						}
						
					}else{
						$("#isrecod").text("未认证");
						$("#caText").text("申请");
					}
				}else if(data.code=="020021"){
	                location.href = "../../pages/redirect-login.html";
	            }
				
			},error: function(){
				$.dialog.errorTips('error');
			}
	    });
}


function query() {
	//企业信息查询
    $.ajax({
    	url:'/userinfo/enterpriseinfolist.do?t='+Math.random(),
		type:'post', //数据发送方式
		dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
		data:{"userid":2},
		success:function(data){
			var html='';
			if (data.code == '000000') {
				var obj = data.obj;
				if(obj&&obj.length!=0){
					for(var i=0;i<data.obj.length;i++){
						html=html+'<li class="comp_box comp_type2">'; 
						html=html+'<span class="default">';
						if(obj[i].isDefault==1){
							html=html+' 默认账户';
						}else{
							html=html+'<a href="javascript:;"  data-id="'+obj[i].id+'" class="defaultddd">设置为默认账户</a>';
						}
						html=html+'</span>';
						html=html+'<h3>'+obj[i].companyName+'</h3>';
						html=html+'<div class="com-info">';
						html=html+'<span>企业资料：</span>';
						
						if(obj[i].certifStatus==2){
							html=html+ '<div class="comp"><i></i><span class="highlight">CA已认证</span></div>';
							html=html+' <a href="AccountCa.html?id='+obj[i].id+'">查看资料</a>';
						}else if(obj[i].certifStatus==1){
							if(obj[i].status==2){
								html=html+ '<span class="highlight">已审核</span>';
								html=html+' <a href="AccountCa.html?id='+obj[i].id+'">查看资料</a>';
							}else if(obj[i].status==1){
								html=html+ '<span class="highlight">待审核</span>';
								html=html+' <a href="AccountCa.html?id='+obj[i].id+'">查看资料</a>';
							}else{
								html=html+ '<span class="highlight">未认证</span>';
							}
						}else{
							html=html+ '<span class="highlight">未认证</span>';
						}
						html=html+'<li class="comp_box">';
						html=html+' 即将开通';
						html=html+' </li>';
						html=html+'<li class="comp_box">';
						html=html+' 即将开通';
						html=html+' </li>';
						html=html+' </div>';
						html=html+' </li>';
					}
					
				}else{
	            	html=html+'<li class="comp_box comp_type1">';
	    			html=html+'<a href="AccountCa.html">';
	    			html=html+'     <i></i>';
	    			html=html+'     添加企业';
	    			html=html+'  </a>';
	    			html=html+' </li>';
	    			html=html+'<li class="comp_box">';
					html=html+' 即将开通';
					html=html+' </li>';
					html=html+'<li class="comp_box">';
					html=html+' 即将开通';
					html=html+' </li>';
	            }
				 
				
			}else if(data.code=="020021"){
                location.href = "../../pages/redirect-login.html";
            }else{
            	html=html+'<li class="comp_box comp_type1">';
    			html=html+'<a href="AccountCa.html">';
    			html=html+'     <i></i>';
    			html=html+'     添加企业';
    			html=html+'  </a>';
    			html=html+' </li>';
    			html=html+'<li class="comp_box">';
				html=html+' 即将开通';
				html=html+' </li>';
				html=html+'<li class="comp_box">';
				html=html+' 即将开通';
				html=html+' </li>';
            }
			$('#panel_title_id').html(html);
		},error: function(){
			$.dialog.errorTips('error');
		}
    });
}