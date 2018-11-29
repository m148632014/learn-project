package com.mfm.learn.spring;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean的命名
 * 通过类路径中的组件扫描，Spring按照前面描述的规则为未命名的组件生成bean名称：
 * 基本上，采用简单的类名并将其初始字符转换为小写。
 * 但是，在（不常见的）特殊情况下，当有多个字符并且第一个和第二个字符都是大写字母时，原始外壳将被保留。
 * 这些规则与java.beans.Introspector.decapitalize（Spring在此处使用）中定义的规则相同。
 *
 * @author MengFanmao
 * @since 2018年11月29日
 */
public class NamingBean {
    public static final Log LOGGER = LogFactory.getLog(NamingBean.class);

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
            "naming-bean.xml");
        NamingBean.LOGGER
            .info(context.getBean("dataSource", BasicDataSource.class));
        NamingBean.LOGGER
            .info(context.getBean("myDataSource", BasicDataSource.class));
        NamingBean.LOGGER
            .info(context.getBean("heDataSource", BasicDataSource.class));
        NamingBean.LOGGER
            .info(context.getBean("basicDataSource", BasicDataSource.class));
        NamingBean.LOGGER.info(
            context.getBean("subsystemA-dataSource", BasicDataSource.class));
        NamingBean.LOGGER.info(
            context.getBean("subsystemB-dataSource", BasicDataSource.class));
    }
}
