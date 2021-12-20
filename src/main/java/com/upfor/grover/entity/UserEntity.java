package com.upfor.grover.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEntity extends BaseEntity {

    /**
     * 用户名称
     * <p>
     * 全表唯一
     */
    @JsonProperty(value = "username")
    private String username;

}
