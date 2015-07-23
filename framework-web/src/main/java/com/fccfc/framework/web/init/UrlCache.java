package com.fccfc.framework.web.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import com.fccfc.framework.cache.core.CacheConstant;
import com.fccfc.framework.cache.core.CacheHelper;
import com.fccfc.framework.web.bean.resource.UrlResourcePojo;
import com.fccfc.framework.web.service.ResourceService;

/***
 * <Description> <br>
 * 
 * @author bai.wenlong<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2015年7月11日 <br>
 * @since V6.11<br>
 * @see com.fccfc.framework.web.cache <br>
 */
public class UrlCache implements InitializingBean {

    /** logger */
    private Logger logger = Logger.getLogger(UrlCache.class);

    /** resourceService */
    @Resource
    private ResourceService resourceService;

    /***
     * Description: <br>
     * 
     * @author bai.wenlong<br>
     * @taskId <br>
     * @throws Exception <br>
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.debug("loading url resource cache start...");
        List<UrlResourcePojo> urlResourcePojoList = resourceService.selectResource();
        if (urlResourcePojoList != null && !urlResourcePojoList.isEmpty()) {
            Map<String, Object> urlResourceMap = new HashMap<String, Object>();
            for (UrlResourcePojo urlResourcePojo : urlResourcePojoList) {
                urlResourceMap.put(urlResourcePojo.getUrl(), urlResourcePojo);
            }
            CacheHelper.getCache().putNode(CacheConstant.URL, urlResourceMap);
        }
        logger.debug("loading url resource cache end...");

    }

}