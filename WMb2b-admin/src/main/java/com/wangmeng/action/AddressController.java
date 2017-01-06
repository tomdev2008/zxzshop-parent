package com.wangmeng.action;

import com.wangmeng.service.api.AreaRegionService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.api.UserInfoService;
import com.wangmeng.service.bean.Address;
import com.wangmeng.service.bean.Region;
import com.wangmeng.service.bean.ResultCode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> 地址管理控制器 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-25 12:04
 */
@Controller
@RequestMapping("/address")
public class AddressController {

    private static final Logger logger = Logger.getLogger(AddressController.class);

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ResultCodeService resultCodeService;

    @Autowired
    private AreaRegionService areaRegionService;

    /**
     * 列出账号相关的地址列表
     *
     * @param request
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(HttpServletRequest request, int userId, ModelMap model){

        ResultCode result = new ResultCode();
        Map<String, Object> params = new HashMap<String, Object>();
        try {
            params.put("userid",userId);
            List<Address> addressList = userInfoService.queryAddlist(params);
            if (addressList!=null && addressList.size()>0){
                result.setObj(addressList);
            }else {
                result.setCode("020001");
                result.setValue(resultCodeService.queryResultValueByCode("020001"));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.setCode("030001");
        }
        model.put("userId",userId);
        model.put("result", result);
        return "/business/address/list";
    }


    @ResponseBody
    @RequestMapping("/add")
    public boolean add(HttpServletRequest request, Address address, ModelMap model){

        boolean result = false;
        if (address==null || address.getUserId()<=0){
            return false;
        }
        try {
            if (address.getRegionId()<=0){
                Region region = areaRegionService.getRegionByPCA(address.getProvinceId(),address.getCityId(), address.getAreaId());
                if (region!=null && region.getId()>0){
                    address.setRegionId(region.getId());
                }
            }
            result = userInfoService.insertAddress(address);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public boolean delete(HttpServletRequest request, Address address, ModelMap model){

        boolean result = false;
        if (address==null || address.getId()<=0) return result;

        try {
            result = userInfoService.deleteAddress(address.getId());
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return result;
    }


    @ResponseBody
    @RequestMapping("/update")
    public boolean update(HttpServletRequest request, Address address, ModelMap model){

        boolean result = false;
        if (address==null || address.getId()<=0) return result;
        try {
            if (address.getRegionId()<=0){
                Region region = areaRegionService.getRegionByPCA(address.getProvinceId(),address.getCityId(), address.getAreaId());
                if (region!=null && region.getId()>0){
                    address.setRegionId(region.getId());
                }
            }
            result = userInfoService.updateAddress(address);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return result;
    }


    @ResponseBody
    @RequestMapping("/default")
    public boolean setDefault(HttpServletRequest request, Address address, ModelMap model){

        boolean result = false;
        if (address==null || address.getId()<=0) return result;

        try {
            result = userInfoService.setDefault(address);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return result;
    }


}
