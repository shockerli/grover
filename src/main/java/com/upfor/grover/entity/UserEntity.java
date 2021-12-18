package com.upfor.grover.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEntity extends BaseEntity {

    /**
     * 用户名称
     * <p>
     * 全表唯一
     */
    private String username;

}
