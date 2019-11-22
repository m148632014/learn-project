package org.mfm.learn.quartz.domain;

import lombok.Data;

@Data
public class JobVO extends  AbstractObject{

    private String jobClassName;

    private String jobGroupName;

    private String cronExpression;
}
