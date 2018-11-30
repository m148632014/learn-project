package com.mfm.learn.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.Data;

public class LazyInitializedBean {
    public static final Log LOGGER = LogFactory
        .getLog(LazyInitializedBean.class);

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
            "lazy-initialized-bean.xml");
//        13:53:08.883 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'not.lazy'
//        13:53:08.898 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'refLazyBean'
//        13:53:08.898 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'lazyBean'
//        13:53:08.945 [main] INFO com.mfm.learn.spring.LazyInitializedBean - com.mfm.learn.spring.AnotherBean@1786f9d5
//        13:53:08.945 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'lazy'
//        13:53:08.945 [main] INFO com.mfm.learn.spring.LazyInitializedBean - com.mfm.learn.spring.ExpensiveToCreateBean@3578436e
//        13:53:08.945 [main] INFO com.mfm.learn.spring.LazyInitializedBean - AnotherRefBean(lazyBean=com.mfm.learn.spring.ExpensiveToCreateBean@706a04ae)
        AnotherBean notLazy = context.getBean("not.lazy", AnotherBean.class);
        LazyInitializedBean.LOGGER.info(notLazy);
        ExpensiveToCreateBean lazy = context.getBean("lazy",
            ExpensiveToCreateBean.class);
        LazyInitializedBean.LOGGER.info(lazy);

        AnotherRefBean refLazyBean = context.getBean("refLazyBean",
            AnotherRefBean.class);
        LazyInitializedBean.LOGGER.info(refLazyBean);
    }
}

class ExpensiveToCreateBean {

}

class AnotherBean {

}

@Data
class AnotherRefBean {
    private ExpensiveToCreateBean lazyBean;
}