package com.wangmeng.common.utils;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： OrgCodeGenerator          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Nov 2, 2016               <br/>
 * 作者　　　　　　　： 无名                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  组织机构代码生成
 *    用于测试
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class OrgCodeGenerator {

	protected static final Logger logger = LoggerFactory.getLogger(OrgCodeGenerator.class);

	public OrgCodeGenerator() {
	}

    private static String genOrgCodeRaw(char[] bt) {  
    	char[] a = (new String(bt)).toCharArray();
        int[] ww = { 3, 7, 9, 10, 5, 8, 4, 2 };  
  
        int[] cc = new int[8];  
        int DD = 0;  
        int C9 = 0;  
  
        for (int i = 0; i < 8; i++) {  
            cc[i] = a[i];  
            if (47 < cc[i] && cc[i] < 58)  
                cc[i] = cc[i] - 48;  
            else  
                cc[i] = cc[i] - 65;  
        }  
        for (int i = 0; i < 8; i++) {  
            DD += cc[i] * ww[i];  
        }  
        C9 = 11 - DD % 11;  
        if (C9 == 10) {  
            return new String(a) + "-X";  
        } else if (C9 == 11) {  
            return new String(a) + "-" + (char) (48);  
        } else {  
            return new String(a) + "-" + (char) (C9 + 48);  
        }  
  
    } 
	
	/**
	 * 验证企业代码是否正确
	 * 
	 * @param code
	 *            企业组织机构代码
	 * @return
	 */
	public static final boolean isValidOrgCode(String code) {
		int[] ws = { 3, 7, 9, 10, 5, 8, 4, 2 };
		String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String regex = "^([0-9A-Z]){8}-[0-9|X]$";

		if (!code.matches(regex)) {
			return false;
		}
		int sum = 0;
		for (int i = 0; i < 8; i++) {
			sum += str.indexOf(String.valueOf(code.charAt(i))) * ws[i];
		}
		int c9 = 11 - (sum % 11);

		String sc9 = String.valueOf(c9);
		if (11 == c9) {
			sc9 = "0";
		} else if (10 == c9) {
			sc9 = "X";
		}
		return sc9.equals(String.valueOf(code.charAt(9)));
	}
	
	/**
	 * 生成组织机构代码
	 *   校验可以用的
	 * @author 衣奎德
	 * @creationDate. Nov 2, 2016 11:12:10 AM
	 * @return
	 */
	public static String genOrgCode() {
		String nString = null;
		while(nString == null){
			String btCode = RandomStringUtils.randomNumeric(8);
			String code = OrgCodeGenerator.genOrgCodeRaw(btCode.toCharArray());
			if (isValidOrgCode(code)) {
				nString = code;
			}
		}
		return nString;
	}
	
	/**
	 * 生成组织机构代码和税号
	 * @author 衣奎德
	 * @creationDate. Nov 2, 2016 11:12:07 AM
	 * @return
	 */
	public static String[] genOrgCodeAndTaxCode() {
		//组织机构代码
		String orgCode = OrgCodeGenerator.genOrgCode();
		//税务登记号
		//税务登记证号由六位行政区划代码加九位组织机构代码组成。
		Integer[] areaCodeList = IdCardGenerator.areaCode.values().toArray(new Integer[0]);
		String areaCode = ""+areaCodeList[RandomUtils.nextInt(areaCodeList.length)];
		String taxCode = areaCode+orgCode;
		return new String[]{orgCode, taxCode};
	}
	
//	public static void main(String[] args) {
//		for (int i = 0; i <50; i++) {
//			String[] codes = OrgCodeGenerator.genOrgCodeAndTaxCode();
//			System.out.println("组织机构代码:"+codes[0]+", 税号:"+codes[1]);
//		}
//	
//		//String icCode = "9161013156602"+RandomStringUtils.randomNumeric(4)+"L";
//		//统一社会信用代码（18位）包含了组织机构代码（第9-17位）和税务登记证号码(第3-17位)。
//		//统一社会信用代码设计为18位，使用阿拉伯数字或英文字母表示，
//		//由五个部分组成。
//		//第一部分（第1位）：为登记管理部门代码；
//		//第二部分（第2位）：为企业等纳税人类别代码；
//		//第三部分（第3-8位）：为登记管理机关行政区划码；
//		//第四部分（第9-17位）：为主体标识码；
//		//第五部分（第18位）：为校验码，由系统自动生成。
//		
//		IdCardGenerator idc = new IdCardGenerator();
//		for (int i = 0; i <50; i++) {
//			//9-1-610131-56602
//			String icCode = "91"+idc.randomAreaCode()+RandomStringUtils.randomNumeric(9)+RandomStringUtils.randomAlphabetic(1).toUpperCase();
//			System.out.println("三/五证合一:"+icCode);
//		}
//	}

}
