var seller_id;

$(document).ready(function(){
    initial();
});

/**
 * initialize page
 **/
function initial(){
    pageInitial();
    if(!getLoginUser()){
        logout();
    }
    //加载账号对应的默认企业信息
    getDefaultEnterprise();

    //初始化订单和报价数据
    if (seller_id!=null && seller_id!=undefined && seller_id!=''){

        //todo 卖家报价数统计目前不做
        countByBuyerSigned4Seller(seller_id);
        countByBuyerPaid4Seller(seller_id);
        countByFinished4Seller(seller_id);
        countByClosed4Seller(seller_id);
    }
}

/**
 * initial page elements
 */
function pageInitial(){
    $(".main-login").css("display","none");
    $(".main-logout").css("display","none");
}

/**
 * fetch default enterprise for user
 */
function getDefaultEnterprise(){
    $.ajax({
        type:"GET",
        url:"/userinfo/enterpriseinfo.do?categery=1&t="+Math.random(),
        dataType:"json",
        async:false,
        contentType : "application/json; charset=utf-8",
        success : function(data){
            if (data!=null && data!=undefined && data.code=='000000') {

                $('.company-name').html(data.obj.companyName);

                seller_id = data.obj.id;

                //console.log(data.obj);

                if (data.obj.certifStatus==1){
                    $('#cert-status').html('未认证');
                    $('#cert-status').attr('class','');
                    $('#cert-status').addClass('ca0');

                }else if(data.obj.certifStatus==2){
                    $('#cert-status').html('已认证');
                    $('#cert-status').attr('class','');
                    $('#cert-status').addClass('ca highlight');
                }
            }else {
                console.log("无默认企业信息！" + data);
            }
        },
        error : function () {
            console.log("error!");
        }
    });
}



/**
 * fetch login User
 */
function getLoginUser(){
    var result = false;
    $.ajax({
        type:"GET",
        url:"/user/currentLoginUser.do?t=" + Math.random(),
        dataType:"json",
        async:false,
        contentType : "application/json; charset=utf-8",
        success: function (data) {
            if (data!=null && data!=undefined && data.code=='000000') {

                //防止其他角色进入卖家中心页面
                if (data.obj.userType!=2){
                    return false;
                }

                $(".main-login").css("display","block");

                var nickObj = $(".uname");
                var telObj = $(".tel");
                nickObj.html(data.obj.realName==null?'':data.obj.realName);
                telObj.html(data.obj.cellPhone==null?'':data.obj.cellPhone);

                result = true;
            }else {
                $(".main-logout").css("display","block");
                result = false;
                console.log(data.value);
            }
        },
        error: function(){
            console.log("fetch login user error!code = " + data.code);
            $(".main-logout").css("display","block");
            result = false;
        }
    });
    return result;
}

/**
 * count buyersigned orders
 */
function countByBuyerSigned4Seller(seller_id){
    $.ajax({
        type:"GET",
        url:"/orderInfo/countByBuyerSigned4Seller.do?supplyId="+seller_id,
        dataType:"json",
        async:false,
        contentType : "application/json; charset=utf-8",
        success : function(data){
            if (data!=null && data!=undefined && data.code=='000000') {
                $('.count-buyersigned').html(data.obj);
            }
        },
        error : function () {
            console.log("error!")
        }
    });
}

/**
 * count buyerpaid orders
 */
function countByBuyerPaid4Seller(seller_id){
    $.ajax({
        type:"GET",
        url:"/orderInfo/countByBuyerPaid4Seller.do?supplyId="+seller_id,
        dataType:"json",
        async:false,
        contentType : "application/json; charset=utf-8",
        success : function(data){
            if (data!=null && data!=undefined && data.code=='000000') {
                $('.count-buyerpaid').html(data.obj);
            }
        },
        error : function () {
            console.log("error!")
        }
    });
}

/**
 * count finished orders
 */
function countByFinished4Seller(seller_id){
    $.ajax({
        type:"GET",
        url:"/orderInfo/countByFinished4Seller.do?supplyId="+seller_id,
        dataType:"json",
        async:false,
        contentType : "application/json; charset=utf-8",
        success : function(data){
            if (data!=null && data!=undefined && data.code=='000000') {
                $('.count-finished').html(data.obj);
            }
        },
        error : function () {
            console.log("error!");
        }
    });
}


/**
 * count closed orders
 */
function countByClosed4Seller(seller_id){
    $.ajax({
        type:"GET",
        url:"/orderInfo/countByClosed4Seller.do?supplyId="+seller_id,
        dataType:"json",
        async:false,
        contentType : "application/json; charset=utf-8",
        success : function(data){
            if (data!=null && data!=undefined && data.code=='000000') {
                $('.count-closed').html(data.obj);
            }else {
                $('.count-closed').html(0);
            }
        },
        error : function () {
            $('#count-closed').html(0);
            console.log("error!");
        }
    });
}



/**
 * logout
 */
function logout() {
    $.removeCookie('Himall-User', { path: '/' });
    $.removeCookie('Himall-SellerManager', { path: "/" });
    window.location.href = "/pages/redirect-login.html";
}