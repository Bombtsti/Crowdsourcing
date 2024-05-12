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
public class Employer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 请求者id
     */
    private String employerId;

    /**
     * 用户名
     */
    private String employerName;

    /**
     * 密码
     */
    private String employerPwd;

    /**
     * 手机号
     */
    private String employerPhone;


}
