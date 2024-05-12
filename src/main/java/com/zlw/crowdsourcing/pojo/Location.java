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
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 位置信息id
     */
    private String locationId;

    /**
     * 经度
     */
    private String locationLong;

    /**
     * 纬度
     */
    private String locationLat;

    /**
     * 位置描述
     */
    private String locationDesc;


}
