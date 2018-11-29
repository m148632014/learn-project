package com.mfm.learn.spring;

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
public class PnamespaceAndCnamespaceBean {
    public static final Log LOGGER = LogFactory
        .getLog(PnamespaceAndCnamespaceBean.class);

    private String email;

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
            "p-namespace-and-c-namespace.xml");
        PnamespaceAndCnamespaceBean classic = ctx.getBean("classic",
            PnamespaceAndCnamespaceBean.class);
        PnamespaceAndCnamespaceBean.LOGGER.info(classic);
        PnamespaceAndCnamespaceBean pnamespace = ctx.getBean("pnamespace",
            PnamespaceAndCnamespaceBean.class);
        PnamespaceAndCnamespaceBean.LOGGER.info(pnamespace);
        Person johnClassic = ctx.getBean("john-classic", Person.class);
        PnamespaceAndCnamespaceBean.LOGGER.info(johnClassic);
        Person johnModern = ctx.getBean("john-modern", Person.class);
        PnamespaceAndCnamespaceBean.LOGGER.info(johnModern);

        CnamespaceThingOne thingOne1 = ctx.getBean("thingOne1",
            CnamespaceThingOne.class);
        PnamespaceAndCnamespaceBean.LOGGER.info(thingOne1);
        CnamespaceThingOne thingOne2 = ctx.getBean("thingOne2",
            CnamespaceThingOne.class);
        PnamespaceAndCnamespaceBean.LOGGER.info(thingOne2);
        CnamespaceThingOne thingOne3 = ctx.getBean("thingOne3",
            CnamespaceThingOne.class);
        PnamespaceAndCnamespaceBean.LOGGER.info(thingOne3);
    }
}

@Data
class Person {
    private String name;
    private Person spouse;
}

@Data
class CnamespaceThingOne {
    private String email;
    CnamespaceThingTwo two;
    CnamespaceThingThree three;

    CnamespaceThingOne() {
    }

    CnamespaceThingOne(CnamespaceThingTwo thingTwo,
            CnamespaceThingThree thingThree, String email) {
        this.two = thingTwo;
        this.three = thingThree;
        this.email = email;
    }

    CnamespaceThingOne(CnamespaceThingTwo two, CnamespaceThingThree three) {
        this.two = two;
        this.three = three;
    }
}

@Data
class CnamespaceThingTwo {
}

@Data
class CnamespaceThingThree {
}
