package com.wangmeng.sys.authority.impl;

import com.wangmeng.sys.authority.api.IAuthorityService;
import com.wangmeng.sys.authority.mapping.AuthorityMapper;
import com.wangmeng.sys.legacy.constants.SysConstant;
import com.wangmeng.sys.legacy.domain.SysPower;
import com.wangmeng.sys.legacy.domain.SysRolePower;
import com.wangmeng.sys.legacy.model.SysPowerListModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * <p> 权限管理实现类 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-12-19 17:07
 */
public class AuthorityServiceImpl implements IAuthorityService {

    @Autowired
    private AuthorityMapper authorityMapper;


    @Override
    public List<SysPower> queryByUserIdAndType(int userId, int sourceType){

        List<SysPower> result = new ArrayList<>();
        if (userId<=0) return result;

        //todo 加入缓存逻辑

        return authorityMapper.queryByUserId(userId,sourceType);
    }


    @Override
    public Map<String, SysPowerListModel> queryFuncTreeByUserId(int userId) {

        if (userId<=0) return null;
        Map<String,SysPowerListModel> result = new TreeMap<String,SysPowerListModel>();

        try {
            List<SysPower> sysPowerList = queryByUserIdAndType(userId, SysConstant.AUTHORITY_SOURCE_TYPE_FUNCTREE);
            if(sysPowerList!=null && !sysPowerList.isEmpty()){
                for(SysPower power:sysPowerList){
                    if(StringUtils.equals(power.getSuperid(), SysConstant.POWER_SUPERID_NOT_DEFINED)){
                        SysPowerListModel powerListModel = new SysPowerListModel();
                        List<SysPower> listSysPower = new ArrayList<SysPower>();
                        for(SysPower pw:sysPowerList){
                            if(StringUtils.equals(pw.getSuperid(), String.valueOf(power.getId()))){
                                listSysPower.add(pw);
                            }
                        }
                        powerListModel.setListPower(listSysPower);
                        powerListModel.setRootPower(power);
                        String pString = StringUtils.rightPad(power.getDisplay()==null?"":power.getDisplay().toString(), 8, "0")+"_"+StringUtils.rightPad(power.getId().toString(), 8, "0");
                        result.put(pString, powerListModel);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public Map<String, SysPowerListModel> queryBTNByUserId(int userId) {
        return null;
    }

    @Override
    public boolean hasPrivilege(int userId, String resourceName) {

        if (userId<=0 || resourceName==null || "".equals(resourceName)) return false;
        List<SysPower> sysPowerList = queryByUserIdAndType(userId,-1);

        if (sysPowerList!=null && sysPowerList.size()>0){
            for (SysPower sysPower : sysPowerList) {
                if (resourceName.equals(sysPower.getPowerName())){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean add(SysRolePower sysRolePower) {
        return false;
    }

    @Override
    public boolean addByBatch(List<SysRolePower> sysRolePowerList) {
        return false;
    }
}
