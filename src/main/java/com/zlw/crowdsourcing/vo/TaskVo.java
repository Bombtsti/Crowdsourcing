package com.zlw.crowdsourcing.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskVo {
    private String taskId;
    private LocalDateTime taskStartTime;
    private LocalDateTime taskEndTime;
    private String taskDesc;
    private String taskIncentive;
    private String taskWorNum;
    private String locationLong;
    private String locationLat;
    private String locationDesc;
    private String employerId;
    private String employerName;
    private LocalDateTime taskCreateTime;
    private LocalDateTime taskFinishTime;
    private String taskStatus;
}
