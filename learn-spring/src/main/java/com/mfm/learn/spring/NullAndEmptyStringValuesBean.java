package com.mfm.learn.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.Data;

/**
 * null-and-empty-string-values.xml
 *
 * @author MengFanmao
 * @since 2018年11月29日
 */
@Data
public class NullAndEmptyStringValuesBean {

    public static final Log LOGGER = LogFactory
        .getLog(NullAndEmptyStringValuesBean.class);

    private String email;

    private String phone;

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
            "null-and-empty-string-values.xml");
        NullAndEmptyStringValuesBean nullAndEmptyStringValuesBean = ctx.getBean(
            "nullAndEmptyStringValuesBean", NullAndEmptyStringValuesBean.class);
        NullAndEmptyStringValuesBean.LOGGER.info(nullAndEmptyStringValuesBean);
    }
}
