package com.mfm.learn.spring;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 实例化Bean
 * 1.构造函数实例化
 * 2.静态工厂方法实例化
 * 3.实例工厂方法实例化
 *
 * @author MengFanmao
 * @since 2018年11月29日
 */
public class InstantiatingBean {
    public static final Log LOGGER = LogFactory.getLog(NamingBean.class);

    private static InstantiatingBean instantiatingBean = new InstantiatingBean();

    private InstantiatingBean() {
    }

    /**
     * 静态工厂方法实例化
     *
     * @return
     */
    public static InstantiatingBean createInstance() {
        return InstantiatingBean.instantiatingBean;
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
            "instantiating-bean.xml");
        InstantiatingBean.LOGGER
            .info(ctx.getBean("dataSource", BasicDataSource.class));
        InstantiatingBean.LOGGER
            .info(ctx.getBean("myDataSource", BasicDataSource.class));
        InstantiatingBean.LOGGER
            .info(ctx.getBean("instantiatingBean", InstantiatingBean.class));
        InstantiatingBean.LOGGER
            .info(ctx.getBean("serviceLocator", DefaultServiceLocator.class));
        InstantiatingBean.LOGGER
            .info(ctx.getBean("clientService", ClientService.class));
        InstantiatingBean.LOGGER
            .info(ctx.getBean("accountService", AccountService.class));
    }
}

class ClientService {
}

class AccountService {
}

class DefaultServiceLocator {
    private static ClientService clientService = new ClientService();
    private static AccountService accountService = new AccountService();

    public ClientService createClientServiceInstance() {
        return DefaultServiceLocator.clientService;
    }

    public AccountService createAccountServiceInstance() {
        return DefaultServiceLocator.accountService;
    }
}