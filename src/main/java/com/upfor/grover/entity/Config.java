package com.upfor.grover.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 * 配置表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "config") // 表名注解，标识实体类对应的表
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Config extends BaseEntity {

    private String key; // 配置键（用于获取配置，全局唯一）
    private String value; // 配置值
    private String title; // 标题（用于显示）
    private String meta; // 描述（用于详细描述该配置的格式、可选项等信息）

}
