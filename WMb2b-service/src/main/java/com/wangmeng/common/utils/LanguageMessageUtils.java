package com.wangmeng.common.utils;

import java.util.ArrayList;
import java.util.Locale;

public class LanguageMessageUtils {

    /**
     * 获取多语言信息
     *
     * @param code
     * 			返回值
     * @param locale
     * 			语言
     * @return
     * @throws Exception
     */
    	public static String getmessage(String code, Locale locale) {
		try {
			if (null != code) {
				String tempcode = "";
				ArrayList<String> strArr = new ArrayList<String>();
				String tempstrcode = code;
				while (tempstrcode.indexOf("\":\"") > 0) {
					tempcode = tempstrcode.substring((tempstrcode
							.indexOf("\":\"") + 3));
					tempcode = tempcode.substring(0, tempcode.indexOf("\""));
					if (StringUtil.isNotEmpty(tempcode)) {
						if (tempcode.contains("\\")) {
							tempcode = tempcode.replace("\\\\", "\\");
						}
						if (StringUtil.isNotEmpty(tempcode)&& !strArr.contains(tempcode)) {
							strArr.add(tempcode);
						}
					}
					tempstrcode=tempstrcode.substring(tempstrcode.indexOf(":\"")+tempcode.length()+2);

				}
				for (String str : strArr) {
					if (StringUtil.isNotEmpty(str)) {
						MessageResource message = new MessageResource();
						String strcode = "";
						if (str.contains("\\")) {
							strcode = str.replace("\\", "\\\\");
						} else {
							strcode = str;
						}
						code = code.replace("\"" + strcode + "\"", "\""
								+ message.getMessage(str, null, locale) + "\"");
						// System.out.print("\n getmessage tempstrcode3:"+code);
					}
				}
			}
		} catch (Exception e) {
			System.out.print("\n getmessage:" + e.getMessage());
		}

		return code;

	}


    public static void main(String args[]){


//		String ts = "{\"InquirySheetSellerId\":1680,\"InquirySheetProductId\":1532,\"Price\":1.0,\"InquirySheetId\":1380,\"QuotationTime\":\"2016-09-01\",\"ProductId\":0,\"ProductName\":\"仿古砖\",\"SkuId\":\"\",\"SKU\":\"22+33\",\"SalePrice\":0.0,\"Quantity\":100,\"Unit\":\"平方米\",\"curStatus\":\"6\",\"HasPushed\":\"1\",\"inquiryServiceCost\":0.05,\"Pic\":\"\",\"Category\":\"\",\"Brand\":\"\",\"Spec\":\"\",\"ShopId\":52573,\"ShopName\":\"林玲厂家入驻\",\"sheetProduct\":{\"Id\":1532,\"InquirySheetId\":1380,\"ProductName\":\"仿古砖\",\"Industry\":\"\",\"Quantity\":100,\"Unit\":\"平方米\",\"Annex\":\"\",\"AnnexName\":\"\",\"Specification\":\"22+33\",\"CategoryId\":17,\"CategoryPath\":\"1|3|17\",\"BrandId\":0,\"BrandIds\":\"\",\"BrandName\":\"\",\"CategoryName\":\"仿古砖\"}},{\"Id\":771,\"InquirySheetSellerId\":1681,\"InquirySheetProductId\":1531,\"Price\":1.0,\"InquirySheetId\":1380,\"QuotationTime\":\"2016-09-01\",\"ProductId\":0,\"ProductName\":\"大理石\",\"SkuId\":\"\",\"SKU\":\"11+22\",\"SalePrice\":0.0,\"Quantity\":100,\"Unit\":\"平方米\",\"curStatus\":\"6\",\"HasPushed\":\"1\",\"inquiryServiceCost\":0.05,\"Pic\":\"\",\"Category\":\"\",\"Brand\":\"\",\"Spec\":\"\",\"ShopId\":52573,\"ShopName\":\"林玲厂家入驻\",\"sheetProduct\":{\"Id\":1531,\"InquirySheetId\":1380,\"ProductName\":\"大理石\",\"Industry\":\"\",\"Quantity\":100,\"Unit\":\"平方米\",\"Annex\":\"\",\"AnnexName\":\"\",\"Specification\":\"11+22\",\"CategoryId\":6,\"CategoryPath\":\"1|2|6\",\"BrandId\":0,\"BrandIds\":\"\",\"BrandName\":\"\",\"CategoryName\":\"大理石\"}";
//		Locale loc = new Locale("zh");
//		try {
//			System.out.println(getmessage(ts, loc));
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
    }
}
