package org.mfm.learn.quartz.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public class FixSerialIdListableBeanFactory extends DefaultListableBeanFactory {
    /**
     * serial Version UID
     */
    private static final long serialVersionUID = 1L;

    public FixSerialIdListableBeanFactory() {
        super();
        this.setSerializationId(); //NOPMD
    }

    public FixSerialIdListableBeanFactory(BeanFactory parentBeanFactory) {
        super(parentBeanFactory);
        this.setSerializationId(); //NOPMD
    }

    protected void setSerializationId() {
        String serializationId = this.getSerializationId();
        if (serializationId == null) {
            super.setSerializationId(
                FixSerialIdListableBeanFactory.class.getName() + "@"
                    + FixSerialIdListableBeanFactory.serialVersionUID);
            return;
        }

        int index = serializationId.indexOf('@');
        if (index < 0) {
            super.setSerializationId(serializationId);
            return;
        }

        String serializationIdNew = serializationId.substring(0, index) + "@"
            + FixSerialIdListableBeanFactory.serialVersionUID;

        super.setSerializationId(serializationIdNew);
    }
}
