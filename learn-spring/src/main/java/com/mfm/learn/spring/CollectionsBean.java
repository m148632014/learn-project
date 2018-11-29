package com.mfm.learn.spring;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.Data;

@Data
public class CollectionsBean {
    public static final Log LOGGER = LogFactory.getLog(CollectionsBean.class);

    private Properties adminEmails;

    @SuppressWarnings("rawtypes")
    private List someList;

    @SuppressWarnings("rawtypes")
    private Map someMap;

    @SuppressWarnings("rawtypes")
    private Set someSet;

    private Map<String, Float> accounts;

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
            "collections-bean.xml");
        CollectionsBean collectionsBean = context.getBean("collectionsBean",
            CollectionsBean.class);
        //映射键或值的值或设置值也可以是以下任何元素：
        //bean | ref | idref | list | set | map | props | value | null
        CollectionsBean.LOGGER.info(collectionsBean.getAdminEmails());
        CollectionsBean.LOGGER.info(collectionsBean.getSomeList());
        CollectionsBean.LOGGER.info(collectionsBean.getSomeMap());
        CollectionsBean.LOGGER.info(collectionsBean.getSomeSet());

        // 集合属性的自动合并Collection Merging
        CollectionsBean child = context.getBean("child", CollectionsBean.class);
        CollectionsBean.LOGGER.info(child.getAdminEmails());
        //带泛型的集合
        CollectionsBean something = context.getBean("something",
            CollectionsBean.class);
        CollectionsBean.LOGGER.info(something.getAccounts());
    }

}
