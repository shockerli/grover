package com.upfor.grover.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.upfor.grover.enums.Gender;
import lombok.*;

/**
 * 用户表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user") // 表名注解，标识实体类对应的表
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends BaseEntity {

    private String username; // 用户名，全表唯一
    private Gender gender; // 性别

}
