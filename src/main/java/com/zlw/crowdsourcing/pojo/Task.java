package com.zlw.crowdsourcing.pojo;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author zlw
 * @since 2022-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
    private String taskId;

    /**
     * 任务日期
     */
    private LocalDateTime taskStartTime;
    private LocalDateTime taskEndTime;
    /**
     * 任务描述
     */
    private String taskDesc;

    /**
     * 报酬
     */
    private String taskIncentive;

    /**
     * 所需工人数量
     */
    private String taskWorNum;

    /**
     * 任务位置信息id
     */
    private String locationId;
    private String employerId;
    private LocalDateTime taskCreateTime;
    private LocalDateTime taskFinishTime;
    private String taskStatus;

}
