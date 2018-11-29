package com.mfm.learn.spring;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.Data;

/**
 * @author MengFanmao
 * @since 2018年11月29日
 */
@Data
public class StraightValuesBean {
    private static final Log LOGGER = LogFactory
        .getLog(StraightValuesBean.class);

    public String targetName;

    public StraightValuesBean() {

    }

    public StraightValuesBean(String targetName) {
        this.targetName = targetName;
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
            "straight-values-bean.xml");
        //1.<property />元素和p:命名空间以及使用properties文件注入简单值
        BasicDataSource myDataSource1 = context.getBean("myDataSource1",
            BasicDataSource.class);
        BasicDataSource myDataSource2 = context.getBean("myDataSource2",
            BasicDataSource.class);
        BasicDataSource myDataSource3 = context.getBean("myDataSource3",
            BasicDataSource.class);
        StraightValuesBean.LOGGER.info(myDataSource1.getUrl());
        StraightValuesBean.LOGGER.info(myDataSource2.getUrl());
        StraightValuesBean.LOGGER.info(myDataSource3.getUrl());

        //2.idref元素使用
        StraightValuesBean theClientBean = context.getBean("theClientBean",
            StraightValuesBean.class);
        //当需要把bean名称注入到某个bean中时候，这种方式很好，会校验bean是否实际存在，如果不存在会报错的，比如myDataSource3改为myDataSource333未运行程序就会报错
        StraightValuesBean.LOGGER.info(theClientBean.getTargetName());
        //等价于
        StraightValuesBean theClientBean2 = context.getBean("theClientBean2",
            StraightValuesBean.class);
        //当需要把bean名称注入到某个bean中时候，这种方式不会校验，就算写成myDataSource333也可以正常输出
        StraightValuesBean.LOGGER.info(theClientBean2.getTargetName());
        //应用：<idref />元素带来值的一个常见位置（至少在Spring 2.0之前的版本中）是在ProxyFactoryBean bean定义中的AOP拦截器的配置中。
        //指定拦截器名称时使用<idref />元素可以防止拼写错误的拦截器ID。
    }
}
