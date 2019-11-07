package org.mfm.learn.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext ctx)
            throws JobExecutionException {
        MyJob.log.info("hello,job");
    }

}