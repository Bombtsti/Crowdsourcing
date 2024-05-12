package com.zlw.crowdsourcing.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerVo {

    private String workerId;
    private String workerName;
    private String workerPwd;
    private String workerPhone;
    private String workerReliable;
    private String locationId;
    private String locationLong;
    private String locationLat;
    private String locationDesc;

}
