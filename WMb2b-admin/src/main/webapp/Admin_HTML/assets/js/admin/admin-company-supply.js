$(function(){
    $('#contact').click(function(){
        $.dialog({
            title:'企业常用联系人',
            ok:function(){},
            cancel:function(){},
            lock:true,
            content:document.getElementById('contact_content')
        })
    });
    $('#bank').click(function(){
        $.dialog({
            title:'开户行信息',
            ok:function(){},
            cancel:function(){},
            lock:true,
            content:document.getElementById('bank_content')
        })
    });
})
