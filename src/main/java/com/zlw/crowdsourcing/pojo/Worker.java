package com.zlw.crowdsourcing.pojo;

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
public class Worker implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 工人id
     */
    private String workerId;

    /**
     * 用户名
     */
    private String workerName;

    /**
     * 密码
     */
    private String workerPwd;

    /**
     * 联系电话
     */
    private String workerPhone;

    /**
     * 可信度
     */
    private String workerReliable;

    private String locationId;


}
