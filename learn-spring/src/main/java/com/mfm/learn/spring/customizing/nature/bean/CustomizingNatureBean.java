package com.mfm.learn.spring.customizing.nature.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomizingNatureBean
        implements BeanNameAware, BeanFactoryAware, ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {

        CustomizingNatureBean.log.info(
            "applicationContext={},CustomizingNatureBean={}",
            applicationContext, applicationContext
                .getBean("customizingNatureBean", CustomizingNatureBean.class));
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        CustomizingNatureBean.log.info(
            "beanFactory={},CustomizingNatureBean={}", beanFactory,
            beanFactory.getBean("customizingNatureBean"));

    }

    @Override
    public void setBeanName(String name) {
        CustomizingNatureBean.log.info("name={}", name);
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
            "customizing-nature-bean.xml");
        CustomizingNatureBean.log.info("customizingNatureBean={}",
            ctx.getBean("customizingNatureBean", CustomizingNatureBean.class));
    }

}
