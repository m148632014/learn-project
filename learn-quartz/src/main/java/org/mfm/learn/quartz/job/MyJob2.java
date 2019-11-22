package org.mfm.learn.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.stereotype.Service;

/**
 * @author MengFanmao
 * @since 2019年11月14日
 */
@Slf4j
@Service
public class MyJob2 {
    @Autowired
    private CronTriggerFactoryBean myJobTrigger2;

    public void execute(){
        MyJob2.log.info("MyJob2 hello Spring! I'm "+myJobTrigger2.getJobDataMap().get("JOB_NAME"));
    }
}
