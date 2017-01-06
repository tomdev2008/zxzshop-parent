package com.wangmeng.common.utils;

/**
 * <p> 图片工具类 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-11-05 16:30
 */
public class PicUtil {

    /**
     * 截取图片路径，去除图片服务器地址前缀
     *
     * @param picPath
     * @return
     */
    public static String cutPicServerPrefix(String picPath){

        String url = "http://";

        if (picPath==null || "".equals(picPath.trim())) {
            return picPath;
        }
        if (picPath.indexOf(url) > -1) {
            String _picPath = picPath.substring(picPath.indexOf(url) + url.length());
            if (_picPath != null && !"".equals(_picPath.trim())) {
                if (!_picPath.startsWith("/")) {
                    _picPath = "/" + _picPath;
                }
                return _picPath;
            }
        }
        return picPath;
    }

}
