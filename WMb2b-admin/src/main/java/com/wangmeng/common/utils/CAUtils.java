package com.wangmeng.common.utils;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.wangmeng.common.constants.Constant;
import com.wangmeng.expand.ssq.api.SsqExpService;
import com.wangmeng.expand.ssq.bean.APICertResult;
import com.wangmeng.service.api.AreaRegionService;
import com.wangmeng.service.bean.Enterprisephoto;
import com.wangmeng.service.bean.Region;
import com.wangmeng.service.bean.vo.EnterpriseInfoVo;

/**
 * <p> CA工具类 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-11-04 18:31
 */
public class CAUtils {

    private static final Logger logger = Logger.getLogger(CAUtils.class);


    /**
     * 个人认证
     *
     * 错误 {"msg":"身份证号只能为15或18位","isResult":false}
     * 正确 {"msg":"ok","cerNo":"CFCA-33-20161030120330782-85396","isResult":true}
     *
     * @param enterpriseInfoVo
     * @param ssqService
     * @param areaRegionService
     * @return
     */
    public static APICertResult applyCA4Person(EnterpriseInfoVo enterpriseInfoVo, SsqExpService ssqService, AreaRegionService areaRegionService){

    	APICertResult result = null;
        String password="123456789abc"; //默认值
        Region region = areaRegionService.getRegion(enterpriseInfoVo.getRegionId());
        String province = (region==null) ? null : region.getProvinceName();
        String city = (region==null) ? null : region.getCityName();
        String name = enterpriseInfoVo.getPersonName();
        String cellPhone = enterpriseInfoVo.getPersonPhone();
        String email = enterpriseInfoVo.getPersonEmail();
        String address = enterpriseInfoVo.getCompanyAddress();
        String userIDCard = enterpriseInfoVo.getIdCardNo();

        try {
            result = ssqService.certificateApply(name,password,cellPhone,address,province,city,email,userIDCard);
        } catch (Exception e) {
            logger.warn("Failure to CA for person!  " + e.getMessage());
        }
        return result;
    }


    /**
     * 企业认证
     *
     * 错误 {"msg":"身份证号只能为15或18位","isResult":false}
     * 正确 {"msg":"ok","cerNo":"CFCA-33-20161030120330782-85396","isResult":true}
     *
     * 	5	200001	营业执照证件	营业执照证件
     *	6	200002	组织机构代码证件	组织机构代码证件
     *	7	200003	税务登记证	税务登记证
     *	8	200004	三/五证合一	三/五证合一
     *
     * @param enterpriseInfoVo
     * @param ssqService
     * @param areaRegionService
     * @return
     */
    public static APICertResult applyCA4Enterprise(EnterpriseInfoVo enterpriseInfoVo, SsqExpService ssqService,AreaRegionService areaRegionService){

    	APICertResult result = null;

        Region region = areaRegionService.getRegion(enterpriseInfoVo.getRegionId());
        String province = (region==null) ? null : region.getProvinceName();
        String city = (region==null) ? null : region.getCityName();
        String name = enterpriseInfoVo.getCompanyName();
        String linkMan = enterpriseInfoVo.getContactsName();
        String password = "123456789abc"; //默认值
        String cellPhone = enterpriseInfoVo.getContactsPhone();
        String email = enterpriseInfoVo.getContactsEmail();
        String address = enterpriseInfoVo.getCompanyAddress();
        String userIDCard = enterpriseInfoVo.getIdCardNo();

        String icCode = "";
        String orgCode = "";
        String taxCode = "";
        List<Enterprisephoto> enterprisephotoList =  enterpriseInfoVo.getEnterprisephotoList();

        //三证或合一证件编号读取
        if (enterprisephotoList!=null) {
            for (Enterprisephoto lic : enterprisephotoList ) {
                if (lic!=null && lic.getDictCode()!=null && StringUtil.isNotEmpty(lic.getDescription())) {
                    String licCodeType = lic.getDictCode();
                    switch (licCodeType) {
                        case "200002":
                            orgCode = StringUtils.trim(lic.getDescription());
                            break;
                        case "200003":
                            taxCode = StringUtils.trim(lic.getDescription());
                            break;
                        case "200004":
                            icCode = StringUtils.trim(lic.getDescription());
                            break;
                        default:
                            break;
                    }
                }
            }
        }

        try{
            //三证
            if (enterpriseInfoVo.getEnterpriseType()==Constant.EnterpriseLicType.LIC_TORF.getStatusCode()
                    && (StringUtil.isNotEmpty(orgCode) && StringUtil.isNotEmpty(taxCode) )) {
                result = ssqService.certificateApplyOrg(name,linkMan,password,cellPhone,address,province,city,email,userIDCard,icCode,orgCode,taxCode);

            //三证/五证合一
            } else if (enterpriseInfoVo.getEnterpriseType()==Constant.EnterpriseLicType.LIC_ONE.getStatusCode() && StringUtil.isNotEmpty(icCode) ) {
                result = ssqService.certificateApplyOrg(name,linkMan,password,cellPhone,address,province,city,email,userIDCard, icCode, orgCode, taxCode);
            }
        }catch (Exception e){
            logger.warn("Failure to CA for enterprise! " + e.getMessage());
        }
        return result;
    }

}
