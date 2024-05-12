package com.zlw.crowdsourcing.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerTaskVo {
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
    private String orderStatus;
}
