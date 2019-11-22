package org.mfm.learn.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MengFanmao
 * @since 2019年11月14日
 */
@Slf4j
public class MyJob3 implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        MyJob3.log.info("MyJob3 hello java! I'm " +context.getMergedJobDataMap().get("JOB_NAME"));
    }
}
