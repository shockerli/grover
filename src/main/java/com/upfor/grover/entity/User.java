package com.upfor.grover.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 用户表
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    /**
     * 用户ID
     * <p>
     * 主键ID, 其他表的user_id
     */
    private Long id;

    /**
     * 用户名称
     * <p>
     * 全表唯一
     */
    private String username;

    /**
     * 数据创建时间
     * <p>
     * 数据第一次创建时的时间，不可更改
     */
    private Long createTime;

    /**
     * 最后修改时间
     * <p>
     * 数据每次被修改都会更新该时间
     */
    private Long updateTime;

}
