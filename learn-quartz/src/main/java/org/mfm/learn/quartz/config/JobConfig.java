package org.mfm.learn.quartz.config;

import java.util.Date;

import org.mfm.learn.quartz.job.MyJob;
import org.mfm.learn.quartz.job.MyJob2;
import org.mfm.learn.quartz.job.MyJob3;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

@Configuration
public class JobConfig {

    @Bean
    public JobDetail myJobDetail() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("JOB_NAME", "MyJob");
        return JobBuilder.newJob(MyJob.class).withIdentity("MyJob")
            .usingJobData(jobDataMap).storeDurably().build();
    }


    @Bean
    public Trigger myJobTrigger(JobDetail myJobDetail) {
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("MyTrigger", "MyGroup")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/13 * * * * ?"))
                .forJob(myJobDetail).build();
        return trigger;
    }


    @Bean
    public MethodInvokingFactoryBean myJobDetail2(MyJob2 myJob2){
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setTargetObjectName("myJob2");
        methodInvokingFactoryBean.setTargetMethodName("execute");
        return methodInvokingFactoryBean;
    }


    @Bean
    public CronTriggerFactoryBean myJobTrigger2(JobDetail myJobDetail2) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("JOB_NAME", "MyJob2");
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(myJobDetail2);
        cronTriggerFactoryBean.setCronExpression("0/14 * * * * ?");
        cronTriggerFactoryBean.setGroup(Scheduler.DEFAULT_GROUP);
        cronTriggerFactoryBean
                .setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
        cronTriggerFactoryBean.setJobDataMap(jobDataMap);
        return cronTriggerFactoryBean;
    }


    @Bean
    public JobDetail myJobDetail3(){
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("JOB_NAME", "MyJob3");
        return JobBuilder.newJob(MyJob3.class).withIdentity("MyJob3")
                .usingJobData(jobDataMap).storeDurably().build();
    }


    @Bean
    public Trigger myJobTrigger3(JobDetail myJobDetail3) {
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("MyTrigger3", "MyGroup")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/15 * * * * ?"))
                .forJob(myJobDetail3).build();
        return trigger;
    }


}