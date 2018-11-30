package com.mfm.learn.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.Data;

/**
 * 复合属性赋值
 * 
 * @author MengFanmao
 * @since 2018年11月30日
 */
@Data
public class CompoundPropertyBean {
    public static final Log LOGGER = LogFactory
        .getLog(CompoundPropertyBean.class);
    private People people;

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
            "compound-property-bean.xml");
        CompoundPropertyBean someone = context.getBean("someone",
            CompoundPropertyBean.class);
        CompoundPropertyBean.LOGGER.info(someone);
    }

}

@Data
class People {
    private String name;
    private People friend;
}
