$(function(){
    $('.status button').click(function(){
        !$(this).is('.active') && $(this).addClass('active').siblings('button').removeClass('active')
    });

    $('.field_date').datetimepicker();

    $('.check').click(function(){
    	var _id = $(this).attr('tab');
    	$.dialog({
            title: '温馨提示',
            lock: true,
            width: '500px',
            padding: '20px',
            content: document.getElementById('check_content'),
            button: [
            {
                name: '审核通过',
                callback:function(){
                	$.ajax({
        				type:"POST",
        				url:'auditPass.do',
        				data:"id=" + _id,
        				success:function(data){
        					window.location.href=document.location.href; 
        			    }
        				
        		    });
                }
            },
            {
                name: '拒绝',
                callback:function(){
                	$.ajax({
      				type:"POST",
      				url:'refuse.do',
      				data:"id=" + _id+"&refuseReason="+refuseReason,
      				success:function(data){
      					window.location.href=document.location.href; 
      			    }
      				
      		      });
                }
            },
            ]
        });
//        $.dialog({
//            title:'商品审核',
//            ok:function(){
//            	$.ajax({
//    				type:"POST",
//    				url:'auditPass.do',
//    				data:"id=" + _id,
//    				success:function(data){
//    					window.location.href="../product/toProductList.do"; 
//    			    }
//    				
//    		    });
//            },
//            cancel:function(){
//              var refuseReason = $("#refuseReason").val();
//              $.ajax({
//  				type:"POST",
//  				url:'refuse.do',
//  				data:"id=" + _id+"&refuseReason="+refuseReason,
//  				success:function(data){
//  					window.location.href="../product/toProductList.do"; 
//  			    }
//  				
//  		      });
//            },
//            okVal:'审核通过',
//            cancelVal:'拒绝',
//            lock:true,
//            content:document.getElementById('check_content')
//        })
    });
    $('.delete').click(function(){
    	var _id = $(this).attr('tab');
    	var name = $(this).attr('rel');
        $.dialog({
            title:'温馨提示',
            ok:function(){
            	$.ajax({
    				type:"POST",
    				url:'delProduct.do',
    				data:"id=" + _id,
    				success:function(data){
    					window.location.href=document.location.href;
    			    }
    				
    		    });
            },
            cancel:function(){},
            lock:true,
            width:400,
            height:100,
            content:'<p style="text-align:center;font-size:14px;">您确定要删除'+name+'吗？</p>'
        })
    });
    $('.view').click(function(){
    	var _this = $(this);
        $.dialog({
            title:'查看原因',
            cancel:function(){},
            cancelVal:'关闭',
            lock:true,
            content:_this.parent().find('.view-detail')[0]
        })
    });
    
    $('.offshelf-view').click(function(){
    	var _this = $(this);
    	$.dialog({
    		title:'违规下架',
    		cancel:function(){},
    		cancelVal:'关闭',
    		lock:true,
    		content:_this.parent().find('.offshelf-detail')[0]
    	})
    });

    $('.off').click(function(){
    	var _id = $(this).attr('tab');
        $.dialog({
            title:'违规下架',
            ok:function(){
            	var offshelf = $("#offshelfReason").val();
            	$.ajax({
    				type:"POST",
    				url:'offshelf.do',
    				data:"id=" + _id + "&offshelf="+offshelf,
    				success:function(data){
    					window.location.href=document.location.href; 
    			    }
    		    });
            	
            },
            cancel:function(){},
            okVal:'违规下架',
            cancelVal:'取消',
            lock:true,
            content:document.getElementById('off_content')
        })
    });
})
