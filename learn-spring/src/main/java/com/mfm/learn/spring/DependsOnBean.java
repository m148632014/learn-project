package com.mfm.learn.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.Data;

/**
 * bean的depends-on属性既可以指定初始化时间依赖性，也可以指定单独的 bean，相应的销毁时间依赖性。
 * depends-on在给定的bean本身被销毁之前，首先销毁定义与给定bean 的关系的从属bean 。这样，depends-on也可以控制销毁顺序。
 *
 * @author MengFanmao
 * @since 2018年11月30日
 */
public class DependsOnBean {
    public static final Log LOGGER = LogFactory.getLog(DependsOnBean.class);

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
            "depends-on-bean.xml");
        DependsOnBeanExampleBean beanOne = context.getBean("beanOne",
            DependsOnBeanExampleBean.class);
//        13:43:23.774 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'manager'
//        13:43:23.790 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'beanOne'
//        13:43:23.790 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'accountDao'
//        13:43:23.806 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'beanOne2'
        DependsOnBean.LOGGER.info(beanOne);
        DependsOnBeanExampleBean beanOne2 = context.getBean("beanOne2",
            DependsOnBeanExampleBean.class);
        DependsOnBean.LOGGER.info(beanOne2);
    }
}

@Data
class DependsOnBeanExampleBean {
    private DependsOnBeanManagerBean manager;
}

class DependsOnBeanManagerBean {

}

class DependsOnBeanJdbcAccountDao {

}