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
public class ResData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据id
     */
    private String dataId;

    /**
     * 数据等级
     */
    private String dataGrade;

    /**
     * 主观评价
     */
    private String dataDesc;

    /**
     * 实景图片
     */
    private String dataImg;
    /**
     * 任务id
     */
    private String taskId;
    private String workerId;


}
