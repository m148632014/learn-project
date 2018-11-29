package com.mfm.learn.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 内部bean的配置
 * 
 * @author MengFanmao
 * @since 2018年11月29日
 */
public class InnerBean {
    public static final Log LOGGER = LogFactory.getLog(InnerBean.class);

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
            "inner-bean.xml");
        StraightValuesBean innerBean = ctx.getBean("outer",
            StraightValuesBean.class);
        InnerBean.LOGGER.info(innerBean);
    }
}
