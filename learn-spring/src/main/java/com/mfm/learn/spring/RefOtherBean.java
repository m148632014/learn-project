package com.mfm.learn.spring;

import java.beans.ConstructorProperties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * bean依赖其他bean
 * bean依赖的其他bean可以是在同一个容器，也可以是在不同的容器，如父子容器。
 ** 容器执行bean依赖性解析，如下所示：
 ** 1）ApplicationContext是使用描述所有bean的配置元数据创建和初始化的。配置元数据可以由XML，Java代码或注释指定。
 ** 2）对于每个bean，它的依赖关系以属性，构造函数参数或static-factory方法的参数的形式表示（如果使用它而不是普通的构造函数）。实际创建bean时，会将这些依赖项提供给bean。
 ** 3）每个属性或构造函数参数都是要设置的值的实际定义，或者是对容器中另一个bean的引用。
 ** 4）作为值的每个属性或构造函数参数都从其指定的格式转换为该属性或构造函数参数的实际类型。默认情况下，Spring可以将以字符串格式提供的值转换为所有内置类型，例如int，long，String，boolean等。
 *
 * @author MengFanmao
 * @since 2018年11月29日
 */
public class RefOtherBean {
    private static final Log LOGGER = LogFactory.getLog(RefOtherBean.class);

    @SuppressWarnings("resource")
    public static void main(String[] args) {

        //要分析父子容器之间是如何建立联系的，需要分析的两个主要的入口类：ContextLoaderListener（的contextInitialized方法初始化父容器），DispatcherServlet（的父类initWebApplicationContext方法，初始化子容器）
        //具体参考：http://www.pandan.xyz/2017/12/21/Spring%20%E7%88%B6%E5%AD%90%E5%AE%B9%E5%99%A8/
        //协作原则：子容器可以获取到父容器中定义的所有Bean，但是反过来，父容器则不能获取到子容器中定义的Bean。
        ClassPathXmlApplicationContext parentCtx = new ClassPathXmlApplicationContext(
            "ref-other-bean-parent.xml");
        ClassPathXmlApplicationContext childCtx = new ClassPathXmlApplicationContext(
            new String[] { "ref-other-bean.xml" }, parentCtx);
        RefOtherBean.LOGGER.info(childCtx.getParent());
        StraightValuesBean parentBean = parentCtx.getBean("straightValuesBean",
            StraightValuesBean.class);
        RefOtherBean.LOGGER.info(parentBean.getTargetName());
        StraightValuesBean childBean = childCtx.getBean("straightValuesBean",
            StraightValuesBean.class);
        RefOtherBean.LOGGER.info(childBean);

        SimpleMovieLister simpleMovieLister = childCtx
            .getBean("simpleMovieLister", SimpleMovieLister.class);
        SimpleMovieLister simpleMovieLister2 = childCtx
            .getBean("simpleMovieLister2", SimpleMovieLister.class);
        RefOtherBean.LOGGER.info(simpleMovieLister);
        RefOtherBean.LOGGER.info(simpleMovieLister2);
        ExampleBean exampleBean1 = childCtx.getBean("exampleBean1",
            ExampleBean.class);
        ExampleBean exampleBean2 = childCtx.getBean("exampleBean2",
            ExampleBean.class);
        ExampleBean exampleBean3 = childCtx.getBean("exampleBean3",
            ExampleBean.class);
        RefOtherBean.LOGGER.info(exampleBean1);
        RefOtherBean.LOGGER.info(exampleBean2);
        RefOtherBean.LOGGER.info(exampleBean3);
    }
}

/**
 * 使用构造函数初始化bean
 * 使用setter初始化bean
 *
 * @author MengFanmao
 * @since 2018年11月29日
 */
class MovieFinder {

}

/**
 * 合理
 *
 * @author MengFanmao
 * @since 2018年11月29日
 */
class SimpleMovieLister {

    // the SimpleMovieLister has a dependency on a MovieFinder
    @SuppressWarnings("unused")
    private MovieFinder movieFinder;

    public SimpleMovieLister() {
    }

    // a constructor so that the Spring container can inject a MovieFinder
    public SimpleMovieLister(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }

    // business logic that actually uses the injected MovieFinder is omitted...

    // a setter method so that the Spring container can inject a MovieFinder
    // Bean基于Setter的依赖注入（Setter-based Dependency Injection）
    public void setMovieFinder(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }
}

/**
 * 构造函数参数解析 Constructor Argument Resolution
 *
 * @author MengFanmao
 * @since 2018年11月29日
 */
class ThingTwo {
}

class ThingThree {
}

class ThingOne {

    @SuppressWarnings("unused")
    public ThingOne(ThingTwo thingTwo, ThingThree thingThree) {
        // ...
    }
}

/**
 * 构造函数参数类型匹配 Constructor argument type matching
 * 构造函数参数索引 Constructor argument index
 * 构造函数参数名称 Constructor argument name
 *
 * @author MengFanmao
 * @since 2018年11月29日
 */
class ExampleBean {

    // Number of years to calculate the Ultimate Answer
    @SuppressWarnings("unused")
    private int years;

    // The Answer to Life, the Universe, and Everything
    @SuppressWarnings("unused")
    private String ultimateAnswer;

    //您还可以使用构造函数参数名称进行值消歧,请记住，为了使这项工作开箱即用，必须在启用调试标志的情况下编译代码，以便Spring可以从构造函数中查找参数名称。
    //如果您不能或不想使用debug标志编译代码，则可以使用@ConstructorProperties JDK批注显式命名构造函数参数。
    @ConstructorProperties({ "years", "ultimateAnswer" })
    public ExampleBean(int years, String ultimateAnswer) {
        this.years = years;
        this.ultimateAnswer = ultimateAnswer;
    }
}
