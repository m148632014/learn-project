package org.mfm.learn.quartz.config;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.impl.JobDetailImpl;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

import java.io.Serializable;
import java.lang.reflect.Method;
@Slf4j
public class MethodInvokingFactoryBean
        extends MethodInvokingJobDetailFactoryBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String targetObjectName;
    private String targetMethodName;
    private BeanFactory beanFactory;
    private transient Method targetMethod;
    private transient Object targetObject;
    private transient String factoryClassName = FixSerialIdListableBeanFactory.class.getName();
    private transient boolean requestRecover;

    public void setFactoryClassName(String factoryClassName) {
        this.factoryClassName = factoryClassName;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        try {
            BeanFactory fixSerialIdBeanFactory = (BeanFactory) BeanUtils
                .instantiateClass(Class.forName(this.factoryClassName)
                    .getConstructor(BeanFactory.class), beanFactory);
            super.setBeanFactory(fixSerialIdBeanFactory);
            this.beanFactory = fixSerialIdBeanFactory;
        } catch (BeanInstantiationException | SecurityException
                | NoSuchMethodException | ClassNotFoundException e) {
           log.error("please check the factoryClassName." + e.getMessage());
        }
    }

    @Override
    public String getTargetMethod() {
        if (super.getTargetMethod() == null && this.targetMethodName != null) {
            return this.targetMethodName;
        }

        return super.getTargetMethod();
    }

    public void setTargetMethodName(String targetMethodName) {
        super.setTargetMethod(targetMethodName);
        this.targetMethodName = targetMethodName;
    }

    public String getTargetMethodName() {
        return this.targetMethodName;
    }

    public String getTargetObjectName() {
        return this.targetObjectName;
    }

    public void setTargetObjectName(String targetObjectName) {
        super.setTargetBeanName(targetObjectName);
        this.targetObjectName = targetObjectName;
    }

    @Override
    public Object getTargetObject() {
        try {
            if (this.targetObject == null) {
                this.targetObject = this.beanFactory
                    .getBean(this.targetObjectName);
            }
        } catch (Exception e) {
           log.error("[Quartz]：初始化-对象【{}】失败。",
                this.targetObjectName);
        }

        return this.targetObject;
    }

    @Override
    public Method getPreparedMethod() {
        try {
            if (this.targetMethod == null) {
                this.targetMethod = this.getTargetObject().getClass()
                    .getMethod(this.targetMethodName, new Class<?>[0]);
            }
        } catch (Exception e) {
            log.error("[Quartz]：任务执行，初始化-方法【{}】失败。", this.targetMethodName);
        }

        return this.targetMethod;
    }

    @Override
    protected void postProcessJobDetail(JobDetail jobDetail) {
        ((JobDetailImpl) jobDetail).setRequestsRecovery(this.requestRecover);
    }

    public boolean isRequestRecover() {
        return this.requestRecover;
    }

    public void setRequestRecover(boolean requestRecover) {
        this.requestRecover = requestRecover;
    }

}
