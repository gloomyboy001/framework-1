package com.fccfc.framework.web.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.fccfc.framework.cache.core.CacheConstant;
import com.fccfc.framework.cache.core.CacheHelper;
import com.fccfc.framework.common.Initialization;
import com.fccfc.framework.web.bean.event.EventPojo;
import com.fccfc.framework.web.service.EventService;

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
public class EventCache implements Initialization {

    /** logger */
    private Logger logger = Logger.getLogger(EventCache.class);

    /** eventService */
    @Resource
    private EventService eventService;

    /***
     * Description: <br>
     * 
     * @author bai.wenlong<br>
     * @taskId <br>
     * @throws Exception <br>
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.debug("loading event cache start...");
        List<EventPojo> eventPojoList = eventService.selectList();
        if (eventPojoList != null && !eventPojoList.isEmpty()) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (EventPojo eventPojo : eventPojoList) {
                map.put(eventPojo.getEventId(), eventPojo);
            }
            CacheHelper.getCache().putNode(CacheConstant.EVENT, map);
        }
        logger.debug("loading event cache end...");

    }

    /**
     * Description: <br>
     * 
     * @author 王伟<br>
     * @taskId <br>
     * @throws Exception <br>
     */
    @Override
    public void destroy() throws Exception {
        CacheHelper.getCache().removeNode(CacheConstant.EVENT);
    }

}
