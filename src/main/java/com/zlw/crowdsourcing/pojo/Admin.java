package com.zlw.crowdsourcing.pojo;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员id
     */
    private String adminId;

    /**
     * 用户名
     */
    private String adminName;

    /**
     * 密码
     */
    private String adminPwd;


}
