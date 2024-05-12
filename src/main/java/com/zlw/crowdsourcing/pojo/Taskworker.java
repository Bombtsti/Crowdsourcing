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
 * @since 2022-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Taskworker implements Serializable {

    private static final long serialVersionUID = 1L;

    private String taskId;

    private String workerId;

    private String orderStatus;


}
