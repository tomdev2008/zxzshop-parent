/**
 * <p> 卖家中心通用函数 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-15 15:08
 */

/**
 * 从url中获取参数值，根据指定的参数名称
 */
(function ($) {
    $.getUrlParamVal = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return decodeURI(r[2]);
        return null;
    }
})(jQuery);


/**
 * 判断是否是数字
 *
 * @param s
 * @returns {boolean}
 * @constructor
 */
function isNum(s) {
    if (s!=null && s!="") {
        return !isNaN(s);
    }
    return false;
}
