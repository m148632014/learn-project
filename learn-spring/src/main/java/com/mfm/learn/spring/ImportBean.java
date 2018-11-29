package com.mfm.learn.spring;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 导入不同文件配置的bean
 * 
 * @author MengFanmao
 * @since 2018年11月29日
 */
public class ImportBean {
    public static final Log LOGGER = LogFactory.getLog(ImportBean.class);

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
            "import-bean.xml");
        ImportBean.LOGGER.info(ctx.getBean("outer", StraightValuesBean.class));
        ImportBean.LOGGER
            .info(ctx.getBean("myDataSource1", BasicDataSource.class).getUrl());
    }
}
