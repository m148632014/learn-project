package org.mfm.learn.quartz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class LearnQuartzApplicationTest {
    @Autowired
    private Scheduler scheduler;

    @Test
    public void testQuartz() throws SchedulerException, InterruptedException {
        this.scheduler.start();
        Thread.sleep(15000);
    }
}
