package org.mfm.learn.quartz.config;

import java.util.Date;

import org.mfm.learn.quartz.job.MyJob;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {

    @Bean
    public JobDetail myJobDetail() {
        JobDataMap jobDataMap = new JobDataMap();
        return JobBuilder.newJob(MyJob.class).withIdentity("MyJob")
            .usingJobData(jobDataMap).storeDurably().build();
    }

    @Bean
    public Trigger myJobTrigger(JobDetail myJobDetail) {

        //创建触发器 每3秒钟执行一次(有开始时间和结束时间)
        long now = new Date().getTime();
        Date start = new Date(now + 3000);
        Date end = new Date(now + 12000);
        //创建触发器 每3秒钟执行一次
        Trigger trigger = TriggerBuilder.newTrigger()
            .withIdentity("MyTrigger", "MyGroup").startAt(start).endAt(end)
            .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(3).repeatForever())
            .forJob(myJobDetail).build();
        return trigger;
    }
}