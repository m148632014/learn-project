package org.mfm.learn.quartz.config;

import java.util.Date;

import org.mfm.learn.quartz.job.MyJob;
import org.mfm.learn.quartz.job.MyJob2;
import org.mfm.learn.quartz.job.MyJob3;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                .withSchedule(CronScheduleBuilder.cronSchedule("0/15 * * * * ?"))
                .forJob(myJobDetail).build();
        return trigger;
    }


    @Bean
    public MethodInvokingFactoryBean myJobDetail2(MyJob2 myJob2){
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setTargetObject(myJob2);
        methodInvokingFactoryBean.setTargetMethod("execute");
        return  methodInvokingFactoryBean;
    }


    @Bean
    public Trigger myJobTrigger2(MethodInvokingFactoryBean myJobDetail2) {
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("MyTrigger2", "MyGroup")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .forJob((JobDetail) myJobDetail2).build();
        return trigger;
    }


    @Bean
    public JobDetail myJobDetail3(MyJob2 myJob2){
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("JOB_NAME", "MyJob3");
        return JobBuilder.newJob(MyJob3.class).withIdentity("MyJob3")
                .usingJobData(jobDataMap).storeDurably().build();
    }


    @Bean
    public Trigger myJobTrigger3(JobDetail myJobDetail3) {
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("MyTrigger3", "MyGroup")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .forJob(myJobDetail3).build();
        return trigger;
    }


}