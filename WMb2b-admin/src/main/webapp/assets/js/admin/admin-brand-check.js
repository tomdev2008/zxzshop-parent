$(function(){
    
    $('#refuse').click(function(){
    	var _id = $(this).attr('tab');
        $.dialog({
            title:'品牌审核',
            ok:function(){
            	var refuseReason = $('#refuseReason').val();
            	$.ajax({
    				type:"POST",
    				url:'refuse.do',
    				data:"id=" + _id + "&refuseReason=" + refuseReason,
    				success:function(data){
    					window.location.href="../Brands/toBrandsapplyList.do"; 
    			    }
    				
    		    });
            },
            cancel:function(){},
            lock:true,
            content:document.getElementById('refuse_content')
        })
    });
})