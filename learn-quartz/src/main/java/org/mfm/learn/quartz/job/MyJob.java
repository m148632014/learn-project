package org.mfm.learn.quartz.job;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

@Slf4j
public class MyJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext ctx)
            throws JobExecutionException {
        JobDataMap jobDataMap = ctx.getMergedJobDataMap();
        log.info("MyJob hello Redis! I'm " +jobDataMap.get("JOB_NAME"));
    }

}