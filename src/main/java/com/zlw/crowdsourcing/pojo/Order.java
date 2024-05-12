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
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务记录id
     */
    private String orderId;

    /**
     * 创建时间
     */
    private LocalDateTime orderCreate;

    /**
     * 数据提交时间
     */
    private LocalDateTime orderFinish;

    /**
     * 状态
     */
    private String orderStatus;

    /**
     * 任务id
     */
    private String taskId;

    /**
     * 工人id
     */
    private String workerId;

    private String employerId;

}
