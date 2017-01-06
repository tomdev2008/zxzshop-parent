package com.wangmeng.common.utils;

/**
 * messageSource自定义代码
 */

import java.text.MessageFormat;
import java.util.*;

import com.wangmeng.service.bean.Resource;
import com.wangmeng.spring.ApplicationContextHolderSingleton;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import com.wangmeng.service.api.LanguageService;
import com.wangmeng.service.bean.Language;

public class MessageResource extends AbstractMessageSource implements
        ResourceLoaderAware {
    @SuppressWarnings("unused")
    private ResourceLoader resourceLoader;

    /**
     * Map切分字符
     */
    protected final String MAP_SPLIT_CODE = "|";
    private static final Map<String, String> properties = new HashMap<String, String>();

    public MessageResource() throws Exception {
        if (null != properties && properties.size() == 0) {
            reload();
        }
    }

    public void reload() throws Exception {
        properties.clear();
        properties.putAll(loadTexts());
    }

    /**
     *
     * 描述：TODO 查询数据 虚拟数据，可以从数据库读取信息，此处省略
     *
     * @return
     */
    private List<Resource> getResource() throws Exception {
        List<Resource> resources = new ArrayList<Resource>();
        //--bean 名称为配置文件中的Id
        LanguageService languageService = ApplicationContextHolderSingleton.getInstance().getBean("languageServiceImpl");
        List<Language> lst = languageService.queryAll();

        if (null != lst && lst.size() > 0) {
            for (int i = 0; i < lst.size(); i++) {
                Resource re = new Resource();
                Resource re1 = new Resource();
                Language lg = lst.get(i);
                re.setResourceId(i);
                re.setName(lg.getZh_CN());
                re.setText(lg.getEn_US());
                re.setLanguage("en");
                resources.add(re);

                re1.setResourceId(i + 1);
                re1.setName(lg.getEn_US());
                re1.setText(lg.getZh_CN());
                re1.setLanguage("zh");
                resources.add(re1);
            }
        }
        // inquiryService.query(param);
        // re.setResourceId(1);
        // re.setName("大理石");
        // re.setText("name001");
        // re.setLanguage("en");
        // resources.add(0, re);
        //
        //
        // re1.setResourceId(2);
        // re1.setName("大理石");
        // re1.setText("\u59D3\u540D");
        // re1.setLanguage("zh");
        // resources.add(1, re1);
        return resources;
    }

    /**
     *
     * 描述：TODO 加载数据
     *
     * @return
     */
    protected Map<String, String> loadTexts() throws Exception {
        Map<String, String> mapResource = new HashMap<String, String>();
        List<Resource> resources = this.getResource();
        for (Resource item : resources) {
            String code = item.getName() + MAP_SPLIT_CODE + item.getLanguage();
            mapResource.put(code, item.getText());
        }
        return mapResource;
    }

    /**
     *
     * 描述：TODO
     *
     * @param code
     * @param locale
     *            本地化语言
     * @return
     */
    private String getText(String code, Locale locale) {
        String localeCode = locale.getLanguage();
        String key = code + MAP_SPLIT_CODE + localeCode;
        String localeText = properties.get(key);
        String resourceText = code;

        if (localeText != null) {
            resourceText = localeText;
        }
		/*
		 * else { localeCode = Locale.ENGLISH.getLanguage(); key = code +
		 * MAP_SPLIT_CODE + localeCode; localeText = properties.get(key);
		 * if(localeText != null) { resourceText = localeText; } else { try {
		 * if(getParentMessageSource() != null) { resourceText =
		 * getParentMessageSource().getMessage(code, null, locale); } } catch
		 * (Exception e) { logger.error("Cannot find message with code: " +
		 * code); } } }
		 */

        if (resourceText.contains("\\") && !resourceText.contains("\\\\")) {
            resourceText = resourceText.replace("\\", "\\\\");
        }
        return resourceText;
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String msg = getText(code, locale);
        MessageFormat result = createMessageFormat(msg, locale);
        return result;
    }

    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        String result = getText(code, locale);
        return result;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = (resourceLoader != null ? resourceLoader
                : new DefaultResourceLoader());
    }
}