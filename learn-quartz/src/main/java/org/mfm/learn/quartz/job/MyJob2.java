package org.mfm.learn.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author MengFanmao
 * @since 2019年11月14日
 */
@Slf4j
@Service
public class MyJob2 {

    public void execute(){
        MyJob2.log.info("hello java!");
    }
}
