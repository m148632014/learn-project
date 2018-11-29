package com.mfm.learn.spring;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;

/**
 * *创建容器、使用容器和读取配置
 * org.springframework.context.ApplicationContext接口
 * *表示SpringIoC容器，负责实例化，配置和组装bean。
 * *读取配置的Bean可以从xml，groovy DSL，javaConfig中获取Bean
 *
 * @author MengFanmao
 * @since 2018年11月29日
 */
public class ConfigurationBeanMetaData {
    public static final Log LOGGER = LogFactory
        .getLog(ConfigurationBeanMetaData.class);

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        //这里创建了2个容器，分别从configuration-metadata.groovy，configuration-metadata.xml读取配置的Bean
        ApplicationContext groovyBeancontext = new GenericGroovyApplicationContext(
            "configuration-metadata.groovy"); //Loaded 1 bean definitions from class path resource [configuration-metadata.groovy]
        ApplicationContext xmlBeancontext = new ClassPathXmlApplicationContext(
            "configuration-metadata.xml"); //Loaded 1 bean definitions from class path resource [configuration-metadata.xml]
        ConfigurationBeanMetaData.LOGGER.info(groovyBeancontext
            .getBean("dataSource", BasicDataSource.class).getUrl()); //Creating shared instance of singleton bean 'dataSource'
        ConfigurationBeanMetaData.LOGGER.info(xmlBeancontext
            .getBean("dataSource", BasicDataSource.class).getUrl()); //Creating shared instance of singleton bean 'dataSource'

        //这里创建1个容器，从configuration-metadata.groovy，configuration-metadata.xml读取配置的Bean，发现下相同的bean，会被之后的同名的bean覆盖。
        GenericApplicationContext genericCtx = new GenericApplicationContext();
        new GroovyBeanDefinitionReader(genericCtx)
            .loadBeanDefinitions("configuration-metadata.groovy"); //Loaded 1 bean definitions from class path resource [configuration-metadata.groovy]
        // Overriding bean definition for bean 'dataSource' with a different definition
        new XmlBeanDefinitionReader(genericCtx)
            .loadBeanDefinitions("configuration-metadata.xml"); //Loaded 0 bean definitions from class path resource [configuration-metadata.xml]
        genericCtx.refresh();
        ConfigurationBeanMetaData.LOGGER.info(
            genericCtx.getBean("dataSource", BasicDataSource.class).getUrl());
    }
}
