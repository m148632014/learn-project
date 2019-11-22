package org.mfm.learn.quartz.controller;

import lombok.extern.slf4j.Slf4j;
import org.mfm.learn.quartz.domain.CloneDirection;
import org.mfm.learn.quartz.domain.JobDTO;
import org.mfm.learn.quartz.domain.JobVO;
import org.mfm.learn.quartz.service.JobTriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/job")
@Slf4j
public class JobController {
    @Autowired
    private JobTriggerService JobTriggerService;

    @PostMapping("/add")
    public Boolean save(@RequestBody JobVO job) {
        try {
            JobDTO jobDTO = job.clone(JobDTO.class,
                    CloneDirection.FORWARD);
            JobTriggerService.addJob(jobDTO);
            return true;
        } catch (Exception e) {
            log.error("error", e);
            return false;
        }
    }
}
